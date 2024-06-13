package com.tornado4651.lmix.common.exception;

/**
 * @author tornado4651
 * @date 2024/5/24 10:34
 */
public class AlertException extends RuntimeException{

    /**
     * 状态码：500
     */
    private Integer code;

    /**
     * 提示语
     */
    private String msg;

    public AlertException(String msg) {
        super(msg);
        this.code = 500;
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
