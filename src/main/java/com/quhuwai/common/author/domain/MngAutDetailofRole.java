package com.quhuwai.common.author.domain;

import java.util.List;

/**
 *角色所有拥有的权限对应Do
 * Created by fys on 2016/1/12.
 */
public class MngAutDetailofRole extends MngRoleInfoDO {

    /**
     * 角色已分配的权限列表
     */
    private List<MngAuthorityBaseInfoDO> autList;

    public List<MngAuthorityBaseInfoDO> getAutList() {
        return autList;
    }

    public void setAutList(List<MngAuthorityBaseInfoDO> autList) {
        this.autList = autList;
    }
}
