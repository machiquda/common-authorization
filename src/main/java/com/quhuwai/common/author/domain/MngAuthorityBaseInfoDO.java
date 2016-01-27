package com.quhuwai.common.author.domain;

import java.util.Date;

/**
* mng_authority_base_info表对应的DO
* Date 2016-01-12 17:04:55
*/
@SuppressWarnings("serial")
public class MngAuthorityBaseInfoDO extends BaseDO {
    /**
     * mng_authority_base_info.id
     * 
     */
    private Long id;

    /**
     * mng_authority_base_info.auth_desc
     * 权限描述
     */
    private String authDesc;

    /**
     * mng_authority_base_info.auth_url
     * 权限全URL
     */
    private String authUrl;

    /**
     * mng_authority_base_info.parent_id
     * 父目录Id  0：全部
     */
    private Long parentId;

    /**
     * mng_authority_base_info.status
     * 权限状态 1:正常 2：禁用
     */
    private Integer status;

    /**
     * mng_authority_base_info.operate_id
     * 操作人Id
     */
    private Long operateId;

    /**
     * mng_authority_base_info.gmt_created
     * 新增记录时间
     */
    private Date gmtCreated;

    /**
     * mng_authority_base_info.gmt_modified
     * 更新记录时间
     */
    private Date gmtModified;

    /**
     * 获取 
     * @return the value of mng_authority_base_info.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 
     * @param id the value for mng_authority_base_info.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 权限描述
     * @return the value of mng_authority_base_info.auth_desc
     */
    public String getAuthDesc() {
        return authDesc;
    }

    /**
     * 设置 权限描述
     * @param authDesc the value for mng_authority_base_info.auth_desc
     */
    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc == null ? null : authDesc.trim();
    }

    /**
     * 获取 权限全URL
     * @return the value of mng_authority_base_info.auth_url
     */
    public String getAuthUrl() {
        return authUrl;
    }

    /**
     * 设置 权限全URL
     * @param authUrl the value for mng_authority_base_info.auth_url
     */
    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl == null ? null : authUrl.trim();
    }

    /**
     * 获取 父目录Id  0：全部
     * @return the value of mng_authority_base_info.parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置 父目录Id  0：全部
     * @param parentId the value for mng_authority_base_info.parent_id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 权限状态 1:正常 2：禁用
     * @return the value of mng_authority_base_info.status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 权限状态 1:正常 2：禁用
     * @param status the value for mng_authority_base_info.status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 操作人Id
     * @return the value of mng_authority_base_info.operate_id
     */
    public Long getOperateId() {
        return operateId;
    }

    /**
     * 设置 操作人Id
     * @param operateId the value for mng_authority_base_info.operate_id
     */
    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    /**
     * 获取 新增记录时间
     * @return the value of mng_authority_base_info.gmt_created
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置 新增记录时间
     * @param gmtCreated the value for mng_authority_base_info.gmt_created
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取 更新记录时间
     * @return the value of mng_authority_base_info.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 更新记录时间
     * @param gmtModified the value for mng_authority_base_info.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}