package com.objectiva.pilot.constants;
/**
 * @author TobiasWang
 * 2020/11/16
 */
public class ResultUtil
{
    public static Result OTSResult(Object object)
    {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success()
    {
        return OTSResult(null);
    }

    public static Result error(String code, String msg)
    {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(ResultEnum resultEnum)
    {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
    
    public static Result OTSResult(Object object, String msg)
    {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
}
