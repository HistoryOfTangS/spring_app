package com.tangyiner.world.core;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer errorCode;

    public BusinessException() {
        super("业务规则异常");
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
    }

    public BusinessException(Integer errorCode) {
        super("业务规则异常");
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
        this.setErrorCode(errorCode);
    }

    public BusinessException(Integer errorCode, String message) {
        super(message);
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
        this.setErrorCode(errorCode);
    }

    public BusinessException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
        this.setErrorCode(errorCode);
    }

    public BusinessException(Integer errorCode, Throwable cause) {
        super(cause);
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
        this.setErrorCode(errorCode);
    }

    public BusinessException(Integer errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = ActionResultCode.BUSINESS_ERROR.getValue();
        this.setErrorCode(errorCode);
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        if (errorCode <= 0) {
            throw new IllegalArgumentException("业务规则异常错误码必须大于0");
        } else {
            this.errorCode = errorCode;
        }
    }
}
