package com.objectiva.pilot.service;

import javax.servlet.http.HttpSession;

import com.objectiva.pilot.constants.Result;

/**
 * 
 * @author TobiasWang 2020/11/16
 */
public interface IUserService {
	Result login(String rawData, HttpSession session);

}
