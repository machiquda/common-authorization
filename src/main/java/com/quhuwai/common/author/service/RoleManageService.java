package com.quhuwai.common.author.service;

import com.quhuwai.common.author.common.Exception.QuAuthorizationException;
import com.quhuwai.common.author.domain.MngAuthorityBaseInfoDO;
import com.quhuwai.common.author.domain.MngRoleInfoDO;
import com.quhuwai.common.author.domain.Query;

import java.util.List;

/**
 * 角色相关类接口
 * Created by fys on 2016/1/8.
 */
public interface RoleManageService {
    /**
     * 添加角色
     *
     * @param role_desc 角色描述
     * @param role_name 角色名
     * @return 是否成功
     */
    boolean addRole(String role_desc, String role_name, Long operateId) throws QuAuthorizationException;

    /**
     * 修改角色
     *
     * @param id        角色id
     * @param role_desc 角色描述
     * @param role_name 角色名
     * @param status    角色状态
     * @return true:修改成功
     */
    boolean resetRole(Long id, String role_desc, String role_name, int status, Long operateId) throws QuAuthorizationException;

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 是否成功
     */
    boolean deleteRole(Long id) throws Exception;

    /**
     * 获取角色详情
     *
     * @param id 角色id
     * @return MngRoleInfoDO 或 null
     */
    MngRoleInfoDO getRoleInfoById(Long id) throws Exception;

    /**
     * 获取角色列表
     *
     * @param query 查询条件
     * @return 角色列表
     */
    List<MngRoleInfoDO> getRoleList(Query<MngRoleInfoDO> query);

    /**
     * 获取角色列表
     *
     * @param MngRoleInfoDO 查询条件
     * @return 角色列表
     */
    List<MngRoleInfoDO> getRoleList(MngRoleInfoDO MngRoleInfoDO);


    /**
     * 为用户分配角色
     *
     * @param userId 用户Id
     * @param roleId 角色Id
     * @param operateId 操作人Id
     * @return
     */
    boolean distributeRoleToUser(Long userId, Long roleId, Long operateId) throws QuAuthorizationException;

    /**
     * 批量为用户分配角色
     *
     * @param ids       用户Ids
     * @param roleId    角色Id
     * @param operateId 操作人Id
     * @return 是否成功
     * @throws Exception
     */
    int distributeRoleToUser(Long[] ids, Long roleId, Long operateId) throws QuAuthorizationException;
    /**
     * 批量为用户分配角色
     *
     * @param ids       用户Ids
     * @param roleId    角色Id
     * @param operateId 操作人Id
     * @return 是否成功
     * @throws Exception
     */
    int distributeRoleToUser(List<Long>ids, Long roleId, Long operateId) throws QuAuthorizationException;
    /**
     * 移除用户的角色
     *
     * @param userId 用户Id
     * @return
     */
    boolean removeRoleOfUser(Long userId, Long operateId);

}
