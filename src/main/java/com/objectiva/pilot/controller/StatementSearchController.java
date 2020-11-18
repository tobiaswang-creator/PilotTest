package com.objectiva.pilot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.objectiva.pilot.constants.Result;
import com.objectiva.pilot.service.IStatementTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author TobiasWang 2020/11/16
 */

@Api(value = "User Controller")
@RestController
@RequestMapping(value = "v2")
public class StatementSearchController {
	@Resource
	private IStatementTableService statementTableService;

	static final Logger logger = LoggerFactory.getLogger(StatementSearchController.class);

	@ApiOperation(value = "get date Table")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rawData", paramType = "body", value = "request", required = true, defaultValue = "{status:\"\";pageNum:\"\";pageSize:\"\"}") })
	@PostMapping(value = "/searchByDate")
	public Result getOderTable(@RequestBody(required = true) String rawData, HttpSession session) {
		logger.info("->User start to enter controller /getOderTableList, the search info is：rawData:{}", rawData);
		return statementTableService.getStatementTableList(rawData, session);

	}

}