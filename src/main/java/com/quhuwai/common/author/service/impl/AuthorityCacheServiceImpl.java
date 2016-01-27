package com.quhuwai.common.author.service.impl;

import com.google.common.cache.*;
import com.quhuwai.common.author.common.AuthStatusEnum;
import com.quhuwai.common.author.dao.MngAuthorityBaseInfoDAO;
import com.quhuwai.common.author.dao.MngRoleInfoDAO;
import com.quhuwai.common.author.dao.MngUserRoleInfoDAO;
import com.quhuwai.common.author.domain.*;
import com.quhuwai.common.author.service.AuthorityCacheService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *  权限缓存实现类
 * Created by fys on 2016/1/13.
 */
@Component
public class AuthorityCacheServiceImpl implements AuthorityCacheService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityCacheServiceImpl.class);
    private static LoadingCache<Long, MngRoleCacheInfoDo> roleInfoCache = null;
    private static LoadingCache<Long, Long> userInfoCache = null;
    @Autowired
    private MngAuthorityBaseInfoDAO mngAuthorityBaseInfoDAO;
    @Autowired
    private MngRoleInfoDAO mngRoleInfoDAO;
    @Autowired
    private MngUserRoleInfoDAO userRoleInfoDAO;

    @Override
    @PostConstruct
    public void init() {
        if (roleInfoCache == null) {
            roleInfoCache = CacheBuilder.newBuilder().expireAfterAccess(12, TimeUnit.HOURS)
                    .maximumSize(1000).removalListener(new RemovalListener<Long, MngRoleCacheInfoDo>() {
                        @Override
                        public void onRemoval(RemovalNotification<Long, MngRoleCacheInfoDo> removalNotification) {

                        }
                    })
                    .build(new CacheLoader<Long, MngRoleCacheInfoDo>() {
                        @Override
                        public MngRoleCacheInfoDo load(Long roleId) throws Exception {


                            MngRoleCacheInfoDo mngRoleCacheInfoDo = new MngRoleCacheInfoDo();
                            //查询角色已分配的权限信息
                            List<MngAuthorityBaseInfoDO> mngAutRoleInfoDOList = mngAuthorityBaseInfoDAO.getRoleActiveAutListById(roleId);
                            //查询角色详情
                            MngRoleInfoDO mngRoleInfoDO = mngRoleInfoDAO.queryMngRoleInfoById(roleId);
                            BeanUtils.copyProperties(mngRoleInfoDO, mngRoleCacheInfoDo);
                            mngRoleCacheInfoDo.setUrlSet(mngAutRoleInfoDOList);
                            return mngRoleCacheInfoDo;
                        }
                    });
        }

        if (userInfoCache == null) {
            userInfoCache = CacheBuilder.newBuilder().expireAfterAccess(12, TimeUnit.HOURS)
                    .maximumSize(1000).removalListener(new RemovalListener<Long, Long>() {
                        @Override
                        public void onRemoval(RemovalNotification<Long, Long> removalNotification) {

                        }
                    })
                    .build(new CacheLoader<Long, Long>() {
                        @Override
                        public Long load(Long userId) throws Exception {
                            MngUserRoleInfoDO mngUserRoleInfoDO = new MngUserRoleInfoDO();
                            mngUserRoleInfoDO.setUserId(userId);
                            mngUserRoleInfoDO.setStatus(AuthStatusEnum.STATUS_OK.getCode());
                            MngUserRoleInfoDO PmngUserRoleInfoDO = userRoleInfoDAO.querySingleMngUserRoleInfo(mngUserRoleInfoDO);
                            if (PmngUserRoleInfoDO != null) {
                                return PmngUserRoleInfoDO.getRoleId();
                            }
                            return -1l;


                        }
                    });
        }
    }

    @Override
    public boolean verifyAuthorityOfRoleByUrl(String url, Long roleId) throws ExecutionException {

        if (StringUtils.isBlank(url)) {
            return false;
        }
        if (roleId == null || roleId < 0) {
            return false;
        }

        return roleInfoCache.get(roleId).isAllowed(url);
    }

    @Override
    public boolean verifyAuthorityOfUserByUrl(String url, Long userId) throws ExecutionException {


        if (StringUtils.isBlank(url)) {
            return false;
        }
        if (userId == null || userId < 0) {
            return false;
        }
        Long roleId = userInfoCache.get(userId);
        if( roleId != -1l){
            return verifyAuthorityOfRoleByUrl(url,roleId);
        }

        return false;
    }

    @Override
    public void removeRoleInfoById(Long roleId) {
        if (roleId == null) {
            return;
        }
        roleInfoCache.invalidate(roleId);
    }

    @Override
    public void removeUserInfoById(Long userId) {
        if (userId == null) {
            return;
        }
        userInfoCache.invalidate(userId);
    }

    @Override
    public void removeAllRoleInfo() {
        roleInfoCache.cleanUp();
    }

    @Override
    public void removeAllUserInfo() {
        userInfoCache.cleanUp();
    }
}
