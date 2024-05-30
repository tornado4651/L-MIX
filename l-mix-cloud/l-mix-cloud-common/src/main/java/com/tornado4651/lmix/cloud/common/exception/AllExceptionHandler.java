package com.tornado4651.lmix.cloud.common.exception;

import com.tornado4651.lmix.cloud.common.bean.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tornado4651
 * @date 2024/5/24 14:55
 */
@Slf4j
@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(value = AlertException.class)
    public CommonResult alertResult(AlertException alertException){
        log.error("产生告警异常：{}", alertException.getMessage());
        alertException.printStackTrace();
        return CommonResult.failed(alertException.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult commonResult(Exception e){
        log.error("产生异常：{}", e.getMessage());
        e.printStackTrace();
        return CommonResult.failed(e.getMessage());
    }
}
