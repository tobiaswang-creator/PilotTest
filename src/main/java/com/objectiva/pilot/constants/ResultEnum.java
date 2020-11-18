package com.objectiva.pilot.constants;

/**
 * @author TobiasWang
 * 2020/11/16
 */

public enum ResultEnum
{
    CODE_402("402", "Wrong Password"),
    CODE_403("403", "The perssion level only can do a request without paramemters!"),
    CODE_404("404", "Input can not be empty"), 
    CODE_407("407", "User not found"),
    CODE_409("409", "Input is not correct format"), 
    CODE_410("410", "userId is empty"),  
    CODE_411("411", "User not exist"), 
    CODE_413("413", "JSON error"),
    CODE_415("415", "pageSize is null"),
    SUCCESS("200", "Congratulations！Successfully log in");

    private String code;

    private String msg;

    ResultEnum(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
