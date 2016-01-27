package com.quhuwai.common.author.common;

/**
 * 权限异常枚举
 * Created by fys on 2016/1/12.
 */
public enum AuthStatusEnum {
    STATUS_OK(1, "正常"),
    STATUS_FORBID(2, "禁止");



    private int code;
    private String message;

    AuthStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

