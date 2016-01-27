package com.quhuwai.common.author.domain;

import java.util.Date;

/**
* mng_aut_role_info表对应的DO
* Date 2016-01-12 17:04:55
*/
@SuppressWarnings("serial")
public class MngAutRoleInfoDO extends BaseDO {
    /**
     * mng_aut_role_info.id
     * 
     */
    private Long id;

    /**
     * mng_aut_role_info.role_id
     * 角色Id
     */
    private Long roleId;

    /**
     * mng_aut_role_info.aut_id
     * 权限Id
     */
    private Long autId;

    /**
     * mng_aut_role_info.status
     * 状态 1:正常 2：禁用
     */
    private Integer status;

    /**
     * mng_aut_role_info.operate_id
     * 操作人Id
     */
    private Long operateId;

    /**
     * mng_aut_role_info.gmt_created
     * 新增记录时间
     */
    private Date gmtCreated;

    /**
     * mng_aut_role_info.gmt_modified
     * 更新记录时间
     */
    private Date gmtModified;

    /**
     * 获取 
     * @return the value of mng_aut_role_info.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 
     * @param id the value for mng_aut_role_info.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 角色Id
     * @return the value of mng_aut_role_info.role_id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置 角色Id
     * @param roleId the value for mng_aut_role_info.role_id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取 权限Id
     * @return the value of mng_aut_role_info.aut_id
     */
    public Long getAutId() {
        return autId;
    }

    /**
     * 设置 权限Id
     * @param autId the value for mng_aut_role_info.aut_id
     */
    public void setAutId(Long autId) {
        this.autId = autId;
    }

    /**
     * 获取 状态 1:正常 2：禁用
     * @return the value of mng_aut_role_info.status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 状态 1:正常 2：禁用
     * @param status the value for mng_aut_role_info.status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 操作人Id
     * @return the value of mng_aut_role_info.operate_id
     */
    public Long getOperateId() {
        return operateId;
    }

    /**
     * 设置 操作人Id
     * @param operateId the value for mng_aut_role_info.operate_id
     */
    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    /**
     * 获取 新增记录时间
     * @return the value of mng_aut_role_info.gmt_created
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置 新增记录时间
     * @param gmtCreated the value for mng_aut_role_info.gmt_created
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取 更新记录时间
     * @return the value of mng_aut_role_info.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 更新记录时间
     * @param gmtModified the value for mng_aut_role_info.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}