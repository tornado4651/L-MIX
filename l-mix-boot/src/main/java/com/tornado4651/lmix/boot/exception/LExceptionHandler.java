package com.tornado4651.lmix.boot.exception;

import com.tornado4651.lmix.boot.beans.Response;
import com.tornado4651.lmix.boot.common.ResponseConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class LExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = UserException.class)
    public Response userException(UserException userException){
        return Response.failed(ResponseConstants.FAILED_CODE, userException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = LoginException.class)
    public Response userException(LoginException loginException){
        return Response.failed(ResponseConstants.TOKEN_EXPIRED_CODE, loginException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Response userException(RuntimeException e){
        e.printStackTrace();
        return Response.failed(ResponseConstants.FAILED_CODE, "系统统一异常拦截："+e.getMessage());
    }
}
