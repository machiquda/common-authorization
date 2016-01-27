package com.quhuwai.common.author.domain;

import java.util.Date;

/**
* mng_user_base_info表对应的DO
* Date 2016-01-12 17:04:55
*/
@SuppressWarnings("serial")
public class MngUserBaseInfoDO extends BaseDO {
    /**
     * mng_user_base_info.id
     * 
     */
    private Long id;

    /**
     * mng_user_base_info.user_name
     * 用户名
     */
    private String userName;

    /**
     * mng_user_base_info.gmt_created
     * 新增记录时间
     */
    private Date gmtCreated;

    /**
     * mng_user_base_info.gmt_modified
     * 更新记录时间
     */
    private Date gmtModified;

    /**
     * 获取 
     * @return the value of mng_user_base_info.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 
     * @param id the value for mng_user_base_info.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 用户名
     * @return the value of mng_user_base_info.user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名
     * @param userName the value for mng_user_base_info.user_name
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取 新增记录时间
     * @return the value of mng_user_base_info.gmt_created
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置 新增记录时间
     * @param gmtCreated the value for mng_user_base_info.gmt_created
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取 更新记录时间
     * @return the value of mng_user_base_info.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 更新记录时间
     * @param gmtModified the value for mng_user_base_info.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}