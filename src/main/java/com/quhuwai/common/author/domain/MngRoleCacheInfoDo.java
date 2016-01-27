package com.quhuwai.common.author.domain;

import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *角色缓存对应的DO
 * Created by fys on 2016/1/13.
 */
public class MngRoleCacheInfoDo extends MngRoleInfoDO {

    /**
     * 角色已经分配过的权限缓存
     */
    private Set<String> urlSet = new HashSet<String>();

    public Set<String> getSet() {


        return urlSet;
    }

    public void setUrlSet(Set<String> set) {
        this.urlSet = set;
    }

    public void setUrlSet(List<MngAuthorityBaseInfoDO> mngAuthorityBaseInfoDOs) {
        if (mngAuthorityBaseInfoDOs != null) {
            for (MngAuthorityBaseInfoDO mngAuthorityBaseInfoDO : mngAuthorityBaseInfoDOs) {
                urlSet.add(mngAuthorityBaseInfoDO.getAuthUrl());
            }
        }
    }

    /**
     * 验证该角色是否有指定url的权限
     *
     * @param url 权限url
     * @return true ： 有
     */
    public boolean isAllowed(String url) {
        if (!StringUtils.isBlank(url)) {
            return urlSet.contains(url);
        }
        return false;
    }
}


