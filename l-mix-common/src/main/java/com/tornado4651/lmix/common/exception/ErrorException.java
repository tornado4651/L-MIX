package com.tornado4651.lmix.common.exception;

/**
 * 内部错误异常
 * @author tornado4651
 * @date 2024/5/24 10:34
 */
public class ErrorException extends RuntimeException{

    /**
     * 状态码：501
     */
    private Integer code;

    /**
     * 提示语
     */
    private String msg;

    public ErrorException(String msg) {
        super(msg);
        this.code = 501;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
