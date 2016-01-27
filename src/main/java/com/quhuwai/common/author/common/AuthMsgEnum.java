package com.quhuwai.common.author.common;

/**
 * 权限异常枚举
 * Created by fys on 2016/1/8.
 */
public enum AuthMsgEnum {

    PARAM_ERROR("0001", "参数错误"),
    AUT_ADD_USER_ERROR("1001", "插入信息失败"),
    AUT_DELETE_USER_ERROR("1002", "删除用户失败"),
    AUT_RESET_USER_ERROR("1003", "修改用户失败"),
    AUT_ADD_AUT_ERROR("1004", "插入权限失败"),
    AUT_DELETE_AUT_ERROR("1005", "删除权限失败"),
    AUT_RESET_AUT_ERROR("1006", "修改权限失败"),
    AUT_GET_AUTLIST_ERROR("1007", "获取权限列表失败"),
    AUT_NOT_EXSIT_ERROR("1008", "权限不存在"),
    AUT_ADD_ROLE_ERROR("1009", "插入角色失败"),
    AUT_DELETE_ROLE_ERROR("1010", "删除角色失败"),
    AUT_RESET_ROLE_ERROR("1011", "修改角色失败"),
    AUT_GET_ROLELIST_ERROR("1012", "获取角色列表失败"),
    ROLE_NOT_EXSIT_ERROR("1013", "角色不存在"),
    ROLE_ALREADY_HAS_THIS_AUT_ERROR("1004", "角色已分配过该权限");


    private String code;
    private String message;

    AuthMsgEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
