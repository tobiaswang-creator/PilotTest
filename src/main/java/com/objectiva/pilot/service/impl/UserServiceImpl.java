package com.objectiva.pilot.service.impl;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.objectiva.pilot.constants.PTConstants;
import com.objectiva.pilot.constants.Result;
import com.objectiva.pilot.constants.ResultEnum;
import com.objectiva.pilot.constants.ResultUtil;
import com.objectiva.pilot.dao.UserDao;
import com.objectiva.pilot.model.SysUser;
import com.objectiva.pilot.service.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author TobiasWang 2020/11/16
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final static String TOKEN = "token";

	@Resource
	private UserDao userMapper;

	@Override
	public Result login(String rawData, HttpSession session) {

		String userName = (String) JSONObject.fromObject(rawData).get("username");
		String password = (String) JSONObject.fromObject(rawData).get("password");

		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			return ResultUtil.error(ResultEnum.INPUT_EMPTY);
		}

		SysUser findUser = userMapper.selectUserByName(userName);

		if (ObjectUtils.isEmpty(findUser)) {
			logger.error("->The user is not find in the database: {}", findUser);
			return ResultUtil.error(ResultEnum.USER_NOT_FOUND);
		}
		if (!password.equals(findUser.getPassword())) {
			logger.info("->password error: {}", findUser);
			return ResultUtil.error(ResultEnum.WRONG_PASS);
		}
		if (null != session.getAttribute("userName")) {
			logger.error("->The user can not login twice error, {} is still on line", session.getAttribute("userName"));
			return ResultUtil.error(ResultEnum.REPEAT_LOGIN);
		}
		session.setAttribute("userName", userName);
		session.setAttribute(PTConstants.PERMISSION_LEVEL, findUser.getLevel());
		session.setMaxInactiveInterval((int) (PTConstants.EXPIRE_TIME));
		logger.info("->User successful login to the system: {}", findUser);
		return ResultUtil.OTSResult(findUser.getUserName());

	}

}
