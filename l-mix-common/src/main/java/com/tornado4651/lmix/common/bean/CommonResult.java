package com.tornado4651.lmix.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 控制层通用返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {

    /**
     *  状态码
     */
    private long code;

    /**
     * 提示语
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;


    /**
     * 成功返回结果
     */
    public static CommonResult success() {
        return new CommonResult(CommonResultCode.SUCCESS.getCode(), CommonResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static CommonResult success(Object data) {
        return new CommonResult(CommonResultCode.SUCCESS.getCode(), CommonResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param message 提示信息
     * @param data 获取的数据
     */
    public static CommonResult success(String message,Object data) {
        return new CommonResult(CommonResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static CommonResult failed(CommonResultCode errorCode) {
        return new CommonResult(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     */
    public static CommonResult failed() {
        return failed(CommonResultCode.FAILED);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码枚举
     * @param data 错误数据 / 提示
     */
    public static CommonResult failed(CommonResultCode errorCode, Object data) {
        return new CommonResult(errorCode.getCode(), errorCode.getMessage(), data);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static CommonResult failed(String message) {
        return new CommonResult(CommonResultCode.FAILED.getCode(), message, null);
    }
}

