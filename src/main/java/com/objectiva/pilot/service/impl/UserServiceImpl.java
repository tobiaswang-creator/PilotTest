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
		JSONObject jsonObj = JSONObject.fromObject(rawData);

		String userName = (String) jsonObj.get("username");
		String password = (String) jsonObj.get("password");

		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			logger.error("->The user login param is empty,userName: " + userName + " ,password: " + password);
			return ResultUtil.error(ResultEnum.CODE_404);
		}

		SysUser findUser = userMapper.selectUserByName(userName);

		if (ObjectUtils.isEmpty(findUser)) {
			logger.error("->The user is not find in the database: {}", findUser);
			return ResultUtil.error(ResultEnum.CODE_407);
		}
		if (!ObjectUtils.isEmpty(findUser) && password.equals(findUser.getPassword())) {
			session.setAttribute(PTConstants.PERMISSION_LEVEL, findUser.getLevel());
			session.setMaxInactiveInterval((int) (PTConstants.EXPIRE_TIME / 5));

			logger.info("->User successful login to the system: {}", findUser);
			return ResultUtil.OTSResult(findUser.getUserName());
		}

		logger.info("->password error: {}", findUser);
		return ResultUtil.error(ResultEnum.CODE_402);
	}

}
