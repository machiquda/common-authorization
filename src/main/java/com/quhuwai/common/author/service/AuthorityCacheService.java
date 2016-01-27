package com.quhuwai.common.author.service;

import java.util.concurrent.ExecutionException;

/**
 * Created by fys on 2016/1/13.
 */
public interface AuthorityCacheService {


    /**
     * 初始化缓存服务
     */
    void init();

    /**
     * 根据权限URL来验证角色是否有该条权限
     *
     * @param url    权限url
     * @param roleId 角色Id
     * @return true ： 拥有权限
     */
    boolean verifyAuthorityOfRoleByUrl(String url, Long roleId) throws ExecutionException;

    /**
     * 根据权限URL来验证用户是否有该条权限
     *
     * @param url    权限url
     * @param userId 用户Id
     * @return true ： 拥有权限
     */
    boolean verifyAuthorityOfUserByUrl(String url, Long userId)throws ExecutionException;


    /**
     * 移除指定Id对应的角色信息
     * @param roleId  角色Id
     */
    void removeRoleInfoById(Long roleId);

    /**
     *  移出指定Id对应的用户信息
     * @param userId
     */
    void removeUserInfoById(Long userId);

    /**
     * 移出全部角色缓存
     */
    void removeAllRoleInfo();

    /**
     * 移出全部用户缓存
     */
    void removeAllUserInfo();

}
