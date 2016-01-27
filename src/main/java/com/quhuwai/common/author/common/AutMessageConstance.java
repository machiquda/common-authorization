package com.quhuwai.common.author.common;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 返回信息常量类
 * <ul>
 *          <li>9xxx 系统级别错误
 *          <li>0xxx 参数级别错误
 *          <il>1xxx~8xxx 业务逻辑级别错误
 *</ul>
 * Created by fys on 2016/1/8.
 */
public class AutMessageConstance {

    /**
     * 参数有误
     */
    public static String AUT_PARAM_ERR = "0001";
    /**
     * 插入信息失败
     */
    public static  String AUT_ADD_USER_ERROR = "1001";
    /**
     * 删除用户失败
     */
    public static  String AUT_DELETE_USER_ERROR = "1002";
    /**
     * 修改用户失败
     */
    public static  String AUT_RESET_USER_ERROR = "1003";

    /**
     * 插入权限失败
     */
    public static  String AUT_ADD_AUT_ERROR = "1004";
    /**
     * 删除权限失败
     */
    public static  String AUT_DELETE_AUT_ERROR = "1005";
    /**
     * 修改权限失败
     */
    public static  String AUT_RESET_AUT_ERROR = "1006";
    /**
     * 获取权限列表失败
     */
    public static  String AUT_GET_AUTLIST_ERROR = "1007";
    /**
     * 权限不存在
     */
    public static  String AUT_NOT_EXSIT_ERROR = "1008";

    /**
     * 插入角色失败
     */
    public static  String AUT_ADD_ROLE_ERROR = "1009";
    /**
     * 删除角色失败
     */
    public static  String AUT_DELETE_ROLE_ERROR = "1010";
    /**
     * 修改角色失败
     */
    public static  String AUT_RESET_ROLE_ERROR = "1011";
    /**
     * 获取角色列表失败
     */
    public static  String AUT_GET_ROLELIST_ERROR = "1012";
    /**
     * 角色不存在
     */
    public static  String ROLE_NOT_EXSIT_ERROR = "1013";


    public static final Map<String, String> AUT_MSGS = new HashMap<String, String>();

    static {
        AUT_MSGS.put("0001", "参数有误");
        AUT_MSGS.put("1001", "插入信息失败");
        AUT_MSGS.put("1002", "删除用户失败");
        AUT_MSGS.put("1003", "修改用户失败");
        AUT_MSGS.put("1004", "插入权限失败");
        AUT_MSGS.put("1005", "删除权限失败");
        AUT_MSGS.put("1006", "修改权限失败");
        AUT_MSGS.put("1007", "获取权限列表失败");
        AUT_MSGS.put("1008", "权限不存在");
        AUT_MSGS.put("1009", "插入角色失败");
        AUT_MSGS.put("1010", "删除角色失败");
        AUT_MSGS.put("1011", "修改角色失败");
        AUT_MSGS.put("1012", "获取角色列表失败");
        AUT_MSGS.put("1013", "角色不存在");
    }
}
