package com.objectiva.pilot.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import com.objectiva.pilot.constants.Result;

public interface IStatementTableService {
	Result getStatementTableList(String email, HttpSession session);
}
