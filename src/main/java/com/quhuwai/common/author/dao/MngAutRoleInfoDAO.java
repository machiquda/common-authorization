package com.quhuwai.common.author.dao;

import java.util.List;

import com.quhuwai.common.author.domain.MngAutRoleInfoDO;
import com.quhuwai.common.author.domain.Query;

/**
* mng_aut_role_info表的DAO层接口
* Date 2016-01-11 15:55:45
* @author liwei
*/
public interface MngAutRoleInfoDAO {



    /**
    * 查询多条记录，不分页
    * @param condition MngAutRoleInfoDO
    *
    */
    List<MngAutRoleInfoDO> queryMngAutRoleInfo(MngAutRoleInfoDO condition);




    /**
    * 根据自定义条件查询单条记录。
    * @param condition MngAutRoleInfoDO
    *
    */
    MngAutRoleInfoDO querySingleMngAutRoleInfo(MngAutRoleInfoDO condition);


    /**
    * 根据自定义条件分页查询多条记录
    * @param query 分页查询封装类
    *
    **/
    List<MngAutRoleInfoDO> queryMngAutRoleInfoByPage(Query<MngAutRoleInfoDO> query);


    /**
    * 根据自定义条件统计总记录条数，配合分页查询使用
    * @param condition MngAutRoleInfoDO
    *
    */
    int countMngAutRoleInfo(MngAutRoleInfoDO condition);

    /**
    * 根据主键更新单条记录
    *
    * @param instance MngAutRoleInfoDO
    *
    */
    int updateMngAutRoleInfoByPk(MngAutRoleInfoDO instance);


    /**
    * 根据条件修改记录
    *
    * @param instance MngAutRoleInfoDO set 部分
    * @param condition MngAutRoleInfoDO where 条件
    *
    */
    int updateMngAutRoleInfoByCondition(MngAutRoleInfoDO instance, MngAutRoleInfoDO condition);


	/**
	* 插入单条记录
    *
	* @param instance MngAutRoleInfoDO
    *
	* @return 返回插入记录数
	*/
	int insertMngAutRoleInfo(MngAutRoleInfoDO instance);

    /**
    * 批量插入记录
    * @param list
    *
    */
    void insertBatchMngAutRoleInfo(List<MngAutRoleInfoDO> list) throws Exception;

	/**
	* 根据主键查询单条记录
	* @param id 主键id
	*
	*/
	MngAutRoleInfoDO queryMngAutRoleInfoById(Long id);

    /**
    * 根据条件删除记录
    * @param condition MngAutRoleInfoDO
    *
    */
    int deleteByCondition(MngAutRoleInfoDO condition);


    /**
    * 根据主键逻辑删除单条记录
    * @param id 主键id
    */
    boolean deleteMngAutRoleInfoById(Long id);

    /**
     * 禁用角色的所有权限
     * @param ids
     * @return
     */
    int disableAllAutOfRole(String ids);

}