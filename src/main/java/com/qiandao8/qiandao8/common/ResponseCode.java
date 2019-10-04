package com.qiandao8.qiandao8.common;

/**
 * @author Bert Q
 * ClassName : ResponseCode
 * Description TODO
 */
public enum  ResponseCode {
    /**
     * 返回成功的状态码
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 返回失败的状态码
     */
    ERROR(1, "ERROR"),
    /**
     * 需要登录的状态码
     */
    NEED_LOGIN(10, "NEED_LOGIN"),
    /**
     * 非法参数的状态码
     */
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String description;

    ResponseCode(int code,String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
