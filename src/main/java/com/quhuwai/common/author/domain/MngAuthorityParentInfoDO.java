package com.quhuwai.common.author.domain;

import java.util.Date;

/**
* mng_authority_parent_info表对应的DO
* Date 2016-01-12 17:04:55
*/
@SuppressWarnings("serial")
public class MngAuthorityParentInfoDO extends BaseDO {
    /**
     * mng_authority_parent_info.id
     * 
     */
    private Long id;

    /**
     * mng_authority_parent_info.authority_parent_desc
     * 权限目录描述
     */
    private String authorityParentDesc;

    /**
     * mng_authority_parent_info.operate_id
     * 操作人Id
     */
    private Long operateId;

    /**
     * mng_authority_parent_info.gmt_created
     * 新增记录时间
     */
    private Date gmtCreated;

    /**
     * mng_authority_parent_info.gmt_modified
     * 更新记录时间
     */
    private Date gmtModified;

    /**
     * 获取 
     * @return the value of mng_authority_parent_info.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 
     * @param id the value for mng_authority_parent_info.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 权限目录描述
     * @return the value of mng_authority_parent_info.authority_parent_desc
     */
    public String getAuthorityParentDesc() {
        return authorityParentDesc;
    }

    /**
     * 设置 权限目录描述
     * @param authorityParentDesc the value for mng_authority_parent_info.authority_parent_desc
     */
    public void setAuthorityParentDesc(String authorityParentDesc) {
        this.authorityParentDesc = authorityParentDesc == null ? null : authorityParentDesc.trim();
    }

    /**
     * 获取 操作人Id
     * @return the value of mng_authority_parent_info.operate_id
     */
    public Long getOperateId() {
        return operateId;
    }

    /**
     * 设置 操作人Id
     * @param operateId the value for mng_authority_parent_info.operate_id
     */
    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    /**
     * 获取 新增记录时间
     * @return the value of mng_authority_parent_info.gmt_created
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置 新增记录时间
     * @param gmtCreated the value for mng_authority_parent_info.gmt_created
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取 更新记录时间
     * @return the value of mng_authority_parent_info.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 更新记录时间
     * @param gmtModified the value for mng_authority_parent_info.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}