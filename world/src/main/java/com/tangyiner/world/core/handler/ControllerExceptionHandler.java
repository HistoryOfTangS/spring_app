package com.tangyiner.world.core.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理, 如果发生异常, 返回异常对象, 所有Controller中发生的异常, 会由ControllerAdvice注解类来处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 全局异常处理
     * @param request 请求
     * @param ex 异常
     * @return 发生的异常对象
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Exception handleException(HttpServletRequest request, Exception ex) {
        // 全局异常处理, 将异常对象作为body返回, 在ResponseBodyHandler中做包装返回给前端
        return ex;
    }
}