package com.objectiva.pilot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.objectiva.pilot.constants.Result;
import com.objectiva.pilot.constants.ResultEnum;
import com.objectiva.pilot.constants.ResultUtil;
import com.objectiva.pilot.model.SysUser;
import com.objectiva.pilot.service.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Api(value = "User Controller")
@RestController
@RequestMapping(value="v1")
public class UserController {

    @Resource
    private IUserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);   
	
    @ApiOperation(value = "Login")
    @ApiImplicitParams
     ({ 
          @ApiImplicitParam(name = "rawData",paramType = "body",  value = "request", required = true, defaultValue="{userName:\"\";password:\"\";") 
     })
    @PostMapping(value = "/login")
    public Result login(@RequestBody String rawData, HttpSession session)
    {
    	logger.info("->User start to login the system, the login info is：" +rawData);
    	    	
        if (StringUtils.isEmpty(rawData))
        {
        	logger.error("->User start to login system error, the login info is：" +rawData);
            return ResultUtil.error(ResultEnum.CODE_409);
        }
              
        return userService.login(rawData, session);
    }
	
	@RequestMapping("/logout")
	public String logout(SysUser user){
		return "index";
	}
	
}
