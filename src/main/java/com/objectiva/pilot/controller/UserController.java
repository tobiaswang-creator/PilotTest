package com.objectiva.pilot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.objectiva.pilot.constants.Result;
import com.objectiva.pilot.constants.ResultEnum;
import com.objectiva.pilot.constants.ResultUtil;import com.objectiva.pilot.service.IUserService;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(value = "User Controller")
@RestController
@RequestMapping(value = "v1")
public class UserController {

	@Resource
	private IUserService userService;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "Login")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rawData", paramType = "body", value = "request", required = true, defaultValue = "{userName:\"\";password:\"\";") })
	@PostMapping(value = "/login")
	public Result login(@RequestBody String rawData, HttpSession session) {
		logger.info("->User start to login the system, the login info is：" + rawData);

		if (StringUtils.isEmpty(rawData)) {
			return ResultUtil.error(ResultEnum.WRONG_PARAM);
		}

		return userService.login(rawData, session);
	}

	@ApiOperation(value = "Logout")
	@ApiImplicitParams({ @ApiImplicitParam(name = "logout", value = "request", required = false, defaultValue = "") })
	@GetMapping(value = "/logout")
	public void logout(HttpServletResponse res, HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		logger.info("->User start to logout, user is：" + userName);
		session.invalidate();
		try {
			res.sendRedirect("http://localhost:8080/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
