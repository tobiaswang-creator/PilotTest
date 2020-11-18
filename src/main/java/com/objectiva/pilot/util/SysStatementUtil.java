package com.objectiva.pilot.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.objectiva.pilot.model.Statement;
import com.objectiva.pilot.model.SysStatement;

public class SysStatementUtil {

	private final static int DAY = 0;
	private final static int MONTH = 1;
	private final static int YEAR = 2;

	public static List<SysStatement> convert(List<Statement> statements) {
		List<SysStatement> result = new ArrayList<SysStatement>();
		statements.forEach(statement -> {
			SysStatement sysStatement = new SysStatement();
			sysStatement.setId(statement.getId());
			sysStatement.setAccountId(statement.getAccountId());
			sysStatement.setDateField(changeToDate(statement.getDateField()));
			sysStatement.setAmount(Float.parseFloat(statement.getAmount()));
			result.add(sysStatement);
		});

		return result;
	}

	public static LocalDate changeToDate(String dateField) {
		String[] number = dateField.split("\\.");
		return LocalDate.of(Integer.parseInt(number[YEAR]), Integer.parseInt(number[MONTH]),
				Integer.parseInt(number[DAY]));
	}

	public static boolean withinLastThreeMonth(LocalDate dateField) {
		return LocalDate.now().minusMonths(3).isAfter(dateField) ? false : true;
	}

	public static boolean isDateFormat(String str) {
		if (StringUtils.isEmpty(str)) {
			return true;
		}
		String format = "\\d{2}.\\d{2}.\\d{4}";
		Pattern p = Pattern.compile(format);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	public static boolean isAmount(String str) {
		if (StringUtils.isEmpty(str)) {
			return true;
		}
		String reg = "^[0-9000]+(.[0-9999]+)?$";
		return str.matches(reg);
	}
}
