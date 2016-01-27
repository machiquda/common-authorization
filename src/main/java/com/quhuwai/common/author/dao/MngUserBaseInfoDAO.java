package com.quhuwai.common.author.dao;

import java.util.List;

import com.quhuwai.common.author.domain.MngUserBaseInfoDO;
import com.quhuwai.common.author.domain.Query;

/**
* mng_user_base_info表的DAO层接口
* Date 2016-01-11 15:55:45
* @author liwei
*/
public interface MngUserBaseInfoDAO {



    /**
    * 查询多条记录，不分页
    * @param condition MngUserBaseInfoDO
    *
    */
    List<MngUserBaseInfoDO> queryMngUserBaseInfo(MngUserBaseInfoDO condition);




    /**
    * 根据自定义条件查询单条记录。
    * @param condition MngUserBaseInfoDO
    *
    */
    MngUserBaseInfoDO querySingleMngUserBaseInfo(MngUserBaseInfoDO condition);


    /**
    * 根据自定义条件分页查询多条记录
    * @param query 分页查询封装类
    *
    **/
    List<MngUserBaseInfoDO> queryMngUserBaseInfoByPage(Query<MngUserBaseInfoDO> query);


    /**
    * 根据自定义条件统计总记录条数，配合分页查询使用
    * @param condition MngUserBaseInfoDO
    *
    */
    int countMngUserBaseInfo(MngUserBaseInfoDO condition);

    /**
    * 根据主键更新单条记录
    *
    * @param instance MngUserBaseInfoDO
    *
    */
    int updateMngUserBaseInfoByPk(MngUserBaseInfoDO instance);


    /**
    * 根据条件修改记录
    *
    * @param instance MngUserBaseInfoDO set 部分
    * @param condition MngUserBaseInfoDO where 条件
    *
    */
    int updateMngUserBaseInfoByCondition(MngUserBaseInfoDO instance, MngUserBaseInfoDO condition);


	/**
	* 插入单条记录
    *
	* @param instance MngUserBaseInfoDO
    *
	* @return 返回插入记录数
	*/
	int insertMngUserBaseInfo(MngUserBaseInfoDO instance);

    /**
    * 批量插入记录
    * @param list
    *
    */
    void insertBatchMngUserBaseInfo(List<MngUserBaseInfoDO> list) throws Exception;

	/**
	* 根据主键查询单条记录
	* @param id 主键id
	*
	*/
	MngUserBaseInfoDO queryMngUserBaseInfoById(Long id);

    /**
    * 根据条件删除记录
    * @param condition MngUserBaseInfoDO
    *
    */
    int deleteByCondition(MngUserBaseInfoDO condition);


    /**
    * 根据主键逻辑删除单条记录
    * @param id 主键id
    */
    boolean deleteMngUserBaseInfoById(Long id);


}