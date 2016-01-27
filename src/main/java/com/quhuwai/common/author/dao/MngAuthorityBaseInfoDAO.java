package com.quhuwai.common.author.dao;

import java.util.List;

import com.quhuwai.common.author.domain.MngAuthorityBaseInfoDO;
import com.quhuwai.common.author.domain.Query;

/**
* mng_authority_base_info表的DAO层接口
* Date 2016-01-11 15:55:45
* @author liwei
*/
public interface MngAuthorityBaseInfoDAO {



    /**
    * 查询多条记录，不分页
    * @param condition MngAuthorityBaseInfoDO
    *
    */
    List<MngAuthorityBaseInfoDO> queryMngAuthorityBaseInfo(MngAuthorityBaseInfoDO condition);




    /**
    * 根据自定义条件查询单条记录。
    * @param condition MngAuthorityBaseInfoDO
    *
    */
    MngAuthorityBaseInfoDO querySingleMngAuthorityBaseInfo(MngAuthorityBaseInfoDO condition);


    /**
    * 根据自定义条件分页查询多条记录
    * @param query 分页查询封装类
    *
    **/
    List<MngAuthorityBaseInfoDO> queryMngAuthorityBaseInfoByPage(Query<MngAuthorityBaseInfoDO> query);


    /**
    * 根据自定义条件统计总记录条数，配合分页查询使用
    * @param condition MngAuthorityBaseInfoDO
    *
    */
    int countMngAuthorityBaseInfo(MngAuthorityBaseInfoDO condition);

    /**
    * 根据主键更新单条记录
    *
    * @param instance MngAuthorityBaseInfoDO
    *
    */
    int updateMngAuthorityBaseInfoByPk(MngAuthorityBaseInfoDO instance);


    /**
    * 根据条件修改记录
    *
    * @param instance MngAuthorityBaseInfoDO set 部分
    * @param condition MngAuthorityBaseInfoDO where 条件
    *
    */
    int updateMngAuthorityBaseInfoByCondition(MngAuthorityBaseInfoDO instance, MngAuthorityBaseInfoDO condition);


	/**
	* 插入单条记录
    *
	* @param instance MngAuthorityBaseInfoDO
    *
	* @return 返回插入记录数
	*/
	int insertMngAuthorityBaseInfo(MngAuthorityBaseInfoDO instance);

    /**
    * 批量插入记录
    * @param list
    *
    */
    void insertBatchMngAuthorityBaseInfo(List<MngAuthorityBaseInfoDO> list) throws Exception;

	/**
	* 根据主键查询单条记录
	* @param id 主键id
	*
	*/
	MngAuthorityBaseInfoDO queryMngAuthorityBaseInfoById(Long id);

    /**
    * 根据条件删除记录
    * @param condition MngAuthorityBaseInfoDO
    *
    */
    int deleteByCondition(MngAuthorityBaseInfoDO condition);


    /**
    * 根据主键逻辑删除单条记录
    * @param id 主键id
    */
    boolean deleteMngAuthorityBaseInfoById(Long id);


    /**
     * 获取给定roleId 的 权限列表
     * @param roleId
     * @return
     */
    List<MngAuthorityBaseInfoDO> getRoleAutListById(Long roleId);
    /**
     * 获取给定roleId 的处于正常状态的权限列表
     * @param roleId
     * @return
     */
    List<MngAuthorityBaseInfoDO> getRoleActiveAutListById(Long roleId);
}