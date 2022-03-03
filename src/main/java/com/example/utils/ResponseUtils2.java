package com.example.utils;

import com.example.domain.ResponseResult;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils2 {

    public static ResponseResult<Object> success(int code, String message, Object data) throws Exception{
        return new ResponseResult<Object>(code, message, data);
    }

    public static ResponseResult<Object> success(String message,Object data) throws Exception{
        return success(0,message,data);
    }

    public static ResponseResult<Object> success(Object data) throws Exception {
        return success("成功",data);
    }

    public static ResponseResult<Object> error( int code,String message) throws  Exception{
        return new ResponseResult<Object>(code,message,null);
    }

    public static ResponseResult<Object> error(String message) throws Exception{
        return error(-1, message);
    }

    public static ResponseResult<Object> error(HttpServletResponse resp) throws Exception{
        return error("失败");
    }
}
