package com.objectiva.pilot.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.objectiva.pilot.constants.Result;

@ControllerAdvice
public class GlobalException {
	
	@ResponseBody
	@ExceptionHandler(Throwable.class)
	public Result exceptionHandler(Throwable e, HttpServletResponse res) {
		
		Result result = new Result();
		result.setMsg(e.getMessage());
		res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return result;
	}

}
