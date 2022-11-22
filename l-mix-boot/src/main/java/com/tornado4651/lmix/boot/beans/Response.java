package com.tornado4651.lmix.boot.beans;

import com.tornado4651.lmix.boot.common.ResponseConstants;
import lombok.Data;

@Data
public class Response {
    /**
     * 相应码
     */
    private Integer code;
    /**
     * 相应信息
     */
    private String msg;
    /**
     * 相应数据
     */
    private Object data;

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response success(String msg, Object data){
        return new Response(ResponseConstants.SUCCESS_CODE, msg, data);
    }

    public static Response success(Object data){
        return new Response(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, data);
    }

    public static Response success(String msg){
        return new Response(ResponseConstants.SUCCESS_CODE, msg, null);
    }

    public static Response success(){
        return new Response(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);
    }

    public static Response failed(Integer code,String msg, Object data){
        return new Response(code, msg, data);
    }

    public static Response failed(Integer code,String msg){
        return new Response(code, msg, null);
    }

    public static Response failed(String msg){
        return new Response(ResponseConstants.FAILED_CODE, msg, null);
    }

    public static Response failed(){
        return new Response(ResponseConstants.FAILED_CODE, ResponseConstants.FAILED_MSG, null);
    }
}
