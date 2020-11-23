package com.objectiva.pilot.constants;

/**
 * @author TobiasWang 2020/11/16
 */

public enum ResultEnum {
	INPUT_EMPTY("100", "Input can not be empty"), USER_NOT_FOUND("101", "User not found"),
	WRONG_PASS("102", "Wrong Password"), USER_LOGOUT("103", "User has log out, please re-login to do the search"),
	PERMISSION_DENNY("104", "The permission level only can do a request without paramemters!"),
	REPEAT_LOGIN("105", "you cannot login twice, please go back for logout before you login again"),
	WRONG_PARAM("106", "wrong parameters, please check your the format of your input"),
	SUCCESS("200", "Congratulations");

	private String code;

	private String msg;

	ResultEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
