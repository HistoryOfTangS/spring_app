package com.tangyiner.world.core;

public enum ActionResultCode {
    /**
     * 服务调用发生业务异常BusinessException
     */
    BUSINESS_ERROR(1),
    /**
     * 服务调用成功
     */
    SUCCESS(0),
    /**
     * 服务调用发生除业务异常BusinessException外的其他异常
     */
    OTHER_ERROR(-1),
    /**
     * 授权异常
     */
    AUTH_ERROR(-2),

    /**
     * 授权异常, token过期，刷新token还在
     */
    AUTH_TOKEN_ERROR(-3),

    /**
     * 重复提交
     */
    DUPLICATE_SUBMIT(-4);

    private final int value;

    ActionResultCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}