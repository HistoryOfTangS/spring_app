package com.tangyiner.world.core.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ErrorUtil {

    private ErrorUtil() {
    }

    /**
     * 获取 errors 所有异常信息
     *
     * @param errors 错误集合
     * @return 所有错误信息
     */
    public static String getErrorsMessage(Errors errors) {
        StringBuilder result = new StringBuilder();
        for (ObjectError error : errors.getAllErrors()) {
            result.append(error.getDefaultMessage());
            result.append("\n");
        }
        return result.toString();
    }
}
