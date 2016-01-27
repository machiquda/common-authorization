package com.quhuwai.common.author.dao;

import java.util.List;

import com.quhuwai.common.author.domain.MngAuthorityParentInfoDO;
import com.quhuwai.common.author.domain.Query;

/**
* mng_authority_parent_info表的DAO层接口
* Date 2016-01-11 15:55:45
* @author liwei
*/
public interface MngAuthorityParentInfoDAO {



    /**
    * 查询多条记录，不分页
    * @param condition MngAuthorityParentInfoDO
    *
    */
    List<MngAuthorityParentInfoDO> queryMngAuthorityParentInfo(MngAuthorityParentInfoDO condition);




    /**
    * 根据自定义条件查询单条记录。
    * @param condition MngAuthorityParentInfoDO
    *
    */
    MngAuthorityParentInfoDO querySingleMngAuthorityParentInfo(MngAuthorityParentInfoDO condition);


    /**
    * 根据自定义条件分页查询多条记录
    * @param query 分页查询封装类
    *
    **/
    List<MngAuthorityParentInfoDO> queryMngAuthorityParentInfoByPage(Query<MngAuthorityParentInfoDO> query);


    /**
    * 根据自定义条件统计总记录条数，配合分页查询使用
    * @param condition MngAuthorityParentInfoDO
    *
    */
    int countMngAuthorityParentInfo(MngAuthorityParentInfoDO condition);

    /**
    * 根据主键更新单条记录
    *
    * @param instance MngAuthorityParentInfoDO
    *
    */
    int updateMngAuthorityParentInfoByPk(MngAuthorityParentInfoDO instance);


    /**
    * 根据条件修改记录
    *
    * @param instance MngAuthorityParentInfoDO set 部分
    * @param condition MngAuthorityParentInfoDO where 条件
    *
    */
    int updateMngAuthorityParentInfoByCondition(MngAuthorityParentInfoDO instance, MngAuthorityParentInfoDO condition);


	/**
	* 插入单条记录
    *
	* @param instance MngAuthorityParentInfoDO
    *
	* @return 返回插入记录数
	*/
	int insertMngAuthorityParentInfo(MngAuthorityParentInfoDO instance);

    /**
    * 批量插入记录
    * @param list
    *
    */
    void insertBatchMngAuthorityParentInfo(List<MngAuthorityParentInfoDO> list) throws Exception;

	/**
	* 根据主键查询单条记录
	* @param id 主键id
	*
	*/
	MngAuthorityParentInfoDO queryMngAuthorityParentInfoById(Long id);

    /**
    * 根据条件删除记录
    * @param condition MngAuthorityParentInfoDO
    *
    */
    int deleteByCondition(MngAuthorityParentInfoDO condition);


    /**
    * 根据主键逻辑删除单条记录
    * @param id 主键id
    */
    boolean deleteMngAuthorityParentInfoById(Long id);


}