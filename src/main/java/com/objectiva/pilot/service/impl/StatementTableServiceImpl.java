package com.objectiva.pilot.service.impl;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.objectiva.pilot.constants.PTConstants;
import com.objectiva.pilot.constants.Result;
import com.objectiva.pilot.constants.ResultEnum;
import com.objectiva.pilot.constants.ResultUtil;
import com.objectiva.pilot.dao.StatementDao;
import com.objectiva.pilot.dao.UserDao;
import com.objectiva.pilot.model.SysStatement;
import com.objectiva.pilot.model.dto.DisplayDto;
import com.objectiva.pilot.model.dto.SearchDto;
import com.objectiva.pilot.service.IStatementTableService;
import com.objectiva.pilot.util.CommonUtils;
import com.objectiva.pilot.util.SysStatementUtil;
import jdk.internal.org.jline.utils.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author TobiasWang 2020/11/17
 */
@Service(value = "orderTableService")
public class StatementTableServiceImpl implements IStatementTableService {
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public SimpleDateFormat simpleDateFormatDB = new SimpleDateFormat("dd.MM.yyyy");
	public SimpleDateFormat simpleDateFormatUI = new SimpleDateFormat("yyyy-MM-dd");

	@Resource
	private StatementDao statementMapper;

	@Resource
	private UserDao userMapper;

	public Result getStatementTableList(String input, HttpSession session) {
		JSONObject jsonObj = JSONObject.fromObject(input);
		String searchStartDate = jsonObj.getString("searchStartDate");
		String searchEndDate = jsonObj.getString("searchEndDate");
		String searchStartAmount = jsonObj.getString("searchStartAmount");
		String searchEndAmount = jsonObj.getString("searchEndAmount");

		if (!StringUtils.isEmpty(searchStartDate)) {
			try {
				searchStartDate = simpleDateFormatDB.format(simpleDateFormatUI.parse(searchStartDate));
			} catch (ParseException e) {
				e.printStackTrace();
				Log.error("->the date can not be parsed, the detail is searchStartDate:{}", searchStartDate);
			}
		}
		if (!StringUtils.isEmpty(searchEndDate)) {
			try {
				searchEndDate = simpleDateFormatDB.format(simpleDateFormatUI.parse(searchEndDate));
			} catch (ParseException e) {
				e.printStackTrace();
				Log.error("->the date can not be parsed, the detail is searchEndDate:{}", searchEndDate);
			}
		}

		SearchDto searchDto = new SearchDto(searchStartDate, searchEndDate, searchStartAmount, searchEndAmount);

		List<SysStatement> allStatements = SysStatementUtil.convert(statementMapper.selectAllStatements());

		if (allParamEmpty(searchDto)) {
			logger.error("-> rawData allempty => will return default result for 3 months");
			List<SysStatement> list = allStatements.stream()
					.filter(s -> SysStatementUtil.withinLastThreeMonth(s.getDateField()) == true)
					.collect(Collectors.toList());
			return ResultUtil.OTSResult(findDisplayDto(list), PTConstants.THREE_MONTH_DETAILS);
		}

		if (!((int) session.getAttribute(PTConstants.PERMISSION_LEVEL) == PTConstants.ADMIN)) {
			return ResultUtil.error(ResultEnum.PERMISSION_DENNY);
		}

		if (!isValid(searchDto)) {
			logger.error("->search wrong parameters error, the detail is：input:{}", searchDto);
			return ResultUtil.error(ResultEnum.WRONG_PARAM);
		}
		return conditionalSearch(allStatements, searchDto);
	}

	private List<DisplayDto> findDisplayDto(List<SysStatement> statements) {
		List<DisplayDto> dtos = new ArrayList<DisplayDto>();
		statements.forEach(statement -> {
			DisplayDto dto = new DisplayDto();
			dto.setAccountId(statement.getAccountId());
			dto.setAccountNumber(CommonUtils.toHash(userMapper.findAccountById(statement.getAccountId())));
			dto.setDateField(statement.getDateField().toString());
			dto.setAmount("" + statement.getAmount());
			dtos.add(dto);
		});
		return dtos;
	}

	private boolean allParamEmpty(SearchDto dto) {
		return StringUtils.isEmpty(dto.getSearchStartDate()) && StringUtils.isEmpty(dto.getSearchEndDate())
				&& StringUtils.isEmpty(dto.getSearchStartAmount()) && StringUtils.isEmpty(dto.getSearchEndAmount());
	}

	private boolean isValid(SearchDto searchDto) {
		return SysStatementUtil.isDateFormat(searchDto.getSearchStartDate())
				&& SysStatementUtil.isDateFormat(searchDto.getSearchEndDate())
				&& SysStatementUtil.isAmount(searchDto.getSearchStartAmount())
				&& SysStatementUtil.isAmount(searchDto.getSearchEndAmount());
	}

	private Result conditionalSearch(List<SysStatement> allStatements, SearchDto dto) {
		List<SysStatement> list = allStatements;
		if (!StringUtils.isEmpty(dto.getSearchStartDate())) {
			list = list.stream()
					.filter(s -> s.getDateField()
							.isAfter(SysStatementUtil.changeToDate(dto.getSearchStartDate()).minus(1, ChronoUnit.DAYS)))
					.collect(Collectors.toList());
		}
		if ((!StringUtils.isEmpty(dto.getSearchEndDate()))) {
			list = list.stream()
					.filter(s -> s.getDateField()
							.isBefore(SysStatementUtil.changeToDate(dto.getSearchEndDate()).plus(1, ChronoUnit.DAYS)))
					.collect(Collectors.toList());
		}
		if (!StringUtils.isEmpty(dto.getSearchStartAmount())) {
			list = list.stream().filter(s -> s.getAmount() >= Float.parseFloat(dto.getSearchStartAmount()))
					.collect(Collectors.toList());
		}
		if (!StringUtils.isEmpty(dto.getSearchEndAmount())) {
			list = list.stream().filter(s -> s.getAmount() <= Float.parseFloat(dto.getSearchEndAmount()))
					.collect(Collectors.toList());
		}
		return ResultUtil.OTSResult(findDisplayDto(list), PTConstants.STATEMENT_DETAILS);
	}
}
