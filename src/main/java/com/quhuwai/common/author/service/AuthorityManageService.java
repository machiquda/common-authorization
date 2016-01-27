package com.quhuwai.common.author.service;

import com.quhuwai.common.author.common.Exception.QuAuthorizationException;
import com.quhuwai.common.author.domain.MngAutRoleInfoDO;
import com.quhuwai.common.author.domain.MngAuthorityBaseInfoDO;
import com.quhuwai.common.author.domain.Query;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 权限管理接口
 * Created by fys on 2016/1/10.
 */
public interface AuthorityManageService {
    /**
     * 为角色分配一条权限
     *
     * @param roleId 角色Id
     * @param autId  权限Id
     */
    boolean distributeAutToRole(Long roleId, Long autId, Long operateId) throws QuAuthorizationException;

    /**
     * 删除角色的权限
     *
     * @param roleId 角色Id
     * @param autId  权限Id
     */
    boolean deleteAutOfRole(Long roleId, Long autId);

    /**
     * 删除一条记录
     *
     * @param id 该条权限分配记录的Id
     * @return
     */
    boolean deletAutOfRole(Long id);

    /**
     * 禁用角色权限
     *
     * @param roleId 角色Id
     * @param autId  权限Id
     */
    boolean disableAutOfRole(Long roleId, Long autId, Long operateId);

    /**
     * 获取给定觉角色的所有权限
     *
     * @param roleId 角色Id
     * @param query  查询条件可以不填
     */
    List<MngAutRoleInfoDO> getAutOfRole(Long roleId, Query<MngAutRoleInfoDO> query);

    /**
     * 禁用角色所有权限
     *
     * @param roleId
     */
    boolean disableAllAutOfRole(Long roleId, Long operateId);

    /**
     * 验证用户是否有给定权限Id的权限
     *
     * @param autId  权限Id
     * @param userId 用户Id
     */
    boolean verifyAuthorityOfUser(Long autId, Long userId);

    /**
     * 验证用户是否有给定权限Url的权限
     *
     * @param autUrl 权限Url
     * @param userId 用户Id
     */
    boolean verifyAuthorityOfUser(String autUrl, Long userId) throws QuAuthorizationException, ExecutionException;

    /**
     * 验证用角色是否有给定权限Id的权限
     *
     * @param autId  权限Id
     * @param roleId 角色Id
     */
    boolean verifyAuthorityOfRole(Long autId, Long roleId) throws QuAuthorizationException;

    /**
     * 验证用角色是否有给定权限url 的权限
     *
     * @param autUrl 权限url
     * @param roleId 角色Id
     */
    boolean verifyAuthorityOfRole(String autUrl, Long roleId) throws QuAuthorizationException, ExecutionException;


    /**
     * 添加权限
     *
     * @param authDesc 权限描述
     * @param authUrl  权限全URL
     * @param parentId 父目录Id
     * @throws Exception
     */
    boolean addAuthority(String authDesc, String authUrl, Long parentId, Long operateId) throws QuAuthorizationException;

    /**
     * 删除权限
     *
     * @param id 权限Id
     * @throws Exception
     */
    boolean deleteAuthority(Long id) throws Exception;

    /**
     * 修改权限
     * <p>三项属性 authDesc,authUrl,parentId 至少填一项
     *
     * @param id       权限id
     * @param authDesc 权限描述
     * @param authUrl  权限全URL
     * @param parentId 父目录Id
     * @throws Exception
     */
    boolean resetAuthority(Long id, String authDesc, String authUrl, Long parentId, Long operateId) throws QuAuthorizationException;


    /**
     * 获取权限列表
     *
     * @param query 查询条件
     * @throws Exception
     */
    List<MngAuthorityBaseInfoDO> getAuthorityList(Query<MngAuthorityBaseInfoDO> query) throws QuAuthorizationException;

    /**
     * 获取权限列表
     *
     * @param mngAuthorityBaseInfoDO 筛选条件
     * @return
     * @throws Exception
     */
    List<MngAuthorityBaseInfoDO> getAuthorityList(MngAuthorityBaseInfoDO mngAuthorityBaseInfoDO) throws QuAuthorizationException;

    /**
     * 根据组件获取单条权限信息
     *
     * @return MngAuthorityBaseInfoDO
     */
    MngAuthorityBaseInfoDO getAuthorityInfo(Long id);
}
