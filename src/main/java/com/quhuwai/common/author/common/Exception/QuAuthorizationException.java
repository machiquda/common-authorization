package com.quhuwai.common.author.common.Exception;

import com.quhuwai.common.author.common.AuthMsgEnum;

/**
 * 权限自定义异常
 * Created by fys on 2016/1/8.
 */
public class QuAuthorizationException extends Exception {
    private String code;


    public QuAuthorizationException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
    public QuAuthorizationException(AuthMsgEnum authMsgEnum) {
       this(authMsgEnum,null);
    }
    public QuAuthorizationException(AuthMsgEnum authMsgEnum, Throwable cause) {
        super(authMsgEnum.getMessage(),cause);
        this.code = authMsgEnum.getCode();
    }
    public QuAuthorizationException(String code, String message) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



}
