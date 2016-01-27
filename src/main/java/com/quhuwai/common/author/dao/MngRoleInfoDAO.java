package com.quhuwai.common.author.dao;

import java.util.List;

import com.quhuwai.common.author.domain.MngAuthorityBaseInfoDO;
import com.quhuwai.common.author.domain.MngRoleInfoDO;
import com.quhuwai.common.author.domain.Query;

/**
* mng_role_info表的DAO层接口
* Date 2016-01-11 15:55:45
* @author liwei
*/
public interface MngRoleInfoDAO {



    /**
    * 查询多条记录，不分页
    * @param condition MngRoleInfoDO
    *
    */
    List<MngRoleInfoDO> queryMngRoleInfo(MngRoleInfoDO condition);




    /**
    * 根据自定义条件查询单条记录。
    * @param condition MngRoleInfoDO
    *
    */
    MngRoleInfoDO querySingleMngRoleInfo(MngRoleInfoDO condition);


    /**
    * 根据自定义条件分页查询多条记录
    * @param query 分页查询封装类
    *
    **/
    List<MngRoleInfoDO> queryMngRoleInfoByPage(Query<MngRoleInfoDO> query);


    /**
    * 根据自定义条件统计总记录条数，配合分页查询使用
    * @param condition MngRoleInfoDO
    *
    */
    int countMngRoleInfo(MngRoleInfoDO condition);

    /**
    * 根据主键更新单条记录
    *
    * @param instance MngRoleInfoDO
    *
    */
    int updateMngRoleInfoByPk(MngRoleInfoDO instance);


    /**
    * 根据条件修改记录
    *
    * @param instance MngRoleInfoDO set 部分
    * @param condition MngRoleInfoDO where 条件
    *
    */
    int updateMngRoleInfoByCondition(MngRoleInfoDO instance, MngRoleInfoDO condition);


	/**
	* 插入单条记录
    *
	* @param instance MngRoleInfoDO
    *
	* @return 返回插入记录数
	*/
	int insertMngRoleInfo(MngRoleInfoDO instance);

    /**
    * 批量插入记录
    * @param list
    *
    */
    void insertBatchMngRoleInfo(List<MngRoleInfoDO> list) throws Exception;

	/**
	* 根据主键查询单条记录
	* @param id 主键id
	*
	*/
	MngRoleInfoDO queryMngRoleInfoById(Long id);

    /**
    * 根据条件删除记录
    * @param condition MngRoleInfoDO
    *
    */
    int deleteByCondition(MngRoleInfoDO condition);


    /**
    * 根据主键逻辑删除单条记录
    * @param id 主键id
    */
    boolean deleteMngRoleInfoById(Long id);


}