package com.quhuwai.common.author.dao;

import java.util.List;

import com.quhuwai.common.author.domain.MngUserRoleInfoDO;
import com.quhuwai.common.author.domain.Query;

/**
 * mng_user_role_info表的DAO层接口
 * Date 2016-01-11 15:55:45
 *
 * @author liwei
 */
public interface MngUserRoleInfoDAO {


    /**
     * 查询多条记录，不分页
     *
     * @param condition MngUserRoleInfoDO
     */
    List<MngUserRoleInfoDO> queryMngUserRoleInfo(MngUserRoleInfoDO condition);

    /**
     * 根据UserId查询记录
     *
     * @param userIds 用户Id
     * @return
     */
    List<MngUserRoleInfoDO> queryMngUserRoleInfo(Long[] userIds);


    /**
     * 根据自定义条件查询单条记录。
     *
     * @param condition MngUserRoleInfoDO
     */
    MngUserRoleInfoDO querySingleMngUserRoleInfo(MngUserRoleInfoDO condition);


    /**
     * 根据自定义条件分页查询多条记录
     *
     * @param query 分页查询封装类
     **/
    List<MngUserRoleInfoDO> queryMngUserRoleInfoByPage(Query<MngUserRoleInfoDO> query);


    /**
     * 根据自定义条件统计总记录条数，配合分页查询使用
     *
     * @param condition MngUserRoleInfoDO
     */
    int countMngUserRoleInfo(MngUserRoleInfoDO condition);

    /**
     * 根据主键更新单条记录
     *
     * @param instance MngUserRoleInfoDO
     */
    int updateMngUserRoleInfoByPk(MngUserRoleInfoDO instance);


    /**
     * 根据条件修改记录
     *
     * @param instance  MngUserRoleInfoDO set 部分
     * @param condition MngUserRoleInfoDO where 条件
     */
    int updateMngUserRoleInfoByCondition(MngUserRoleInfoDO instance, MngUserRoleInfoDO condition);


    /**
     * 插入单条记录
     *
     * @param instance MngUserRoleInfoDO
     * @return 返回插入记录数
     */
    int insertMngUserRoleInfo(MngUserRoleInfoDO instance);

    /**
     * 批量插入记录
     *
     * @param list
     */
    void insertBatchMngUserRoleInfo(List<MngUserRoleInfoDO> list) throws Exception;

    /**
     * 根据主键查询单条记录
     *
     * @param id 主键id
     */
    MngUserRoleInfoDO queryMngUserRoleInfoById(Long id);

    /**
     * 根据条件删除记录
     *
     * @param condition MngUserRoleInfoDO
     */
    int deleteByCondition(MngUserRoleInfoDO condition);


    /**
     * 根据主键逻辑删除单条记录
     *
     * @param id 主键id
     */
    boolean deleteMngUserRoleInfoById(Long id);

    /**
     * 批量为角色分配权限
     *
     * @param ids     角色Id 逗号分隔
     * @param role_id 角色Id
     * @return
     */
    int distributeRoleToUser(Long[] ids, Long role_id,Long operateId);

    int distributeRoleToUser(List<Long> ids, Long role_id);
}