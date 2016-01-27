package com.quhuwai.common.author.service.impl;

import com.quhuwai.common.author.common.AuthMsgEnum;
import com.quhuwai.common.author.common.AuthStatusEnum;
import com.quhuwai.common.author.common.AuthorityBusinessConstance;
import com.quhuwai.common.author.common.Exception.QuAuthorizationException;
import com.quhuwai.common.author.dao.MngAutRoleInfoDAO;
import com.quhuwai.common.author.dao.MngAuthorityBaseInfoDAO;
import com.quhuwai.common.author.dao.MngRoleInfoDAO;
import com.quhuwai.common.author.dao.MngUserRoleInfoDAO;
import com.quhuwai.common.author.domain.MngAutRoleInfoDO;
import com.quhuwai.common.author.domain.MngAuthorityBaseInfoDO;
import com.quhuwai.common.author.domain.MngUserRoleInfoDO;
import com.quhuwai.common.author.domain.Query;
import com.quhuwai.common.author.service.AuthorityCacheService;
import com.quhuwai.common.author.service.AuthorityManageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 权限管理实现类
 * Created by fys on 2016/1/11.
 */
@Component
public class AuthorityManageServiceImpl implements AuthorityManageService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityManageServiceImpl.class);
    @Autowired
    private MngAutRoleInfoDAO mngAutRoleInfoDAO;
    @Resource
    private TransactionTemplate authorityTransactionTemplate;
    @Autowired
    private MngUserRoleInfoDAO userRoleInfoDAO;
    @Autowired
    private MngAuthorityBaseInfoDAO mngAuthorityBaseInfoDAO;
    @Autowired
    private MngRoleInfoDAO roleInfoDAO;
    @Autowired
    private AuthorityCacheService authorityCacheService;


    @Override
    public boolean distributeAutToRole(Long roleId, Long autId, Long operateId) throws QuAuthorizationException {
        if (roleId == null || roleId < 0) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  distributeAutToRole : roleId is null or roleId < 0");
        }
        if (autId == null || autId < 0) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  distributeAutToRole : autId is null or autId < 0");

        }
        if (roleInfoDAO.queryMngRoleInfoById(roleId) == null) {
            return false;
        }
        if (mngAuthorityBaseInfoDAO.queryMngAuthorityBaseInfoById(autId) == null) {
            return false;
        }
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null ");
        }
        //插入新纪录
        MngAutRoleInfoDO mngAutRoleInfoDO = new MngAutRoleInfoDO();
        mngAutRoleInfoDO.setStatus(AuthStatusEnum.STATUS_OK.getCode());
        mngAutRoleInfoDO.setAutId(autId);
        mngAutRoleInfoDO.setRoleId(roleId);
        mngAutRoleInfoDO.setOperateId(operateId);
        int cnt = 0;
        try {
            cnt = mngAutRoleInfoDAO.insertMngAutRoleInfo(mngAutRoleInfoDO);
        } catch (Exception e) {
            throw new QuAuthorizationException(AuthMsgEnum.AUT_ADD_AUT_ERROR, e);
        }

        //移除之前的缓存
        authorityCacheService.removeRoleInfoById(roleId);
        return cnt == 1;
    }

    @Override
    public boolean deleteAutOfRole(Long roleId, Long autId) {
        if (roleId == null || roleId < 0) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  deleteAutOfRole : roleId is null or roleId < 0");
        }
        if (autId == null || autId < 0) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  deleteAutOfRole : autId is null or autId < 0");

        }
        MngAutRoleInfoDO mngAutRoleInfoDO = new MngAutRoleInfoDO();
        mngAutRoleInfoDO.setAutId(autId);
        mngAutRoleInfoDO.setRoleId(roleId);
        if (mngAutRoleInfoDAO.deleteByCondition(mngAutRoleInfoDO) == 1) {
            authorityCacheService.removeRoleInfoById(roleId);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletAutOfRole(Long id) {
        Long roleId = null;
        if (id != null && id > 0) {
            roleId = mngAutRoleInfoDAO.queryMngAutRoleInfoById(id).getRoleId();
        }
        //移除缓存
        if (mngAutRoleInfoDAO.deleteMngAutRoleInfoById(id)) {
            authorityCacheService.removeRoleInfoById(roleId);
        }
        return mngAutRoleInfoDAO.deleteMngAutRoleInfoById(id);
    }

    @Override
    public boolean disableAutOfRole(Long roleId, Long autId, Long operateId) {
        if (roleId == null || roleId < 0) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  disableAutOfRole : roleId is null or roleId < 0");
        }
        if (autId == null || autId < 0) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  disableAutOfRole : autId is null or autId < 0");
        }
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null or < 0 ");
        }
        MngAutRoleInfoDO mngAutRoleInfoDO = new MngAutRoleInfoDO();
        mngAutRoleInfoDO.setRoleId(roleId);
        mngAutRoleInfoDO.setAutId(autId);
        MngAutRoleInfoDO targetMngAutRoleInfoDO = mngAutRoleInfoDAO.querySingleMngAutRoleInfo(mngAutRoleInfoDO);
        if (targetMngAutRoleInfoDO == null) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  disableAutOfRole : no authority matched given roleId " + roleId + "and autId" + autId);

        }
        targetMngAutRoleInfoDO.setStatus(AuthStatusEnum.STATUS_FORBID.getCode());
        targetMngAutRoleInfoDO.setOperateId(operateId);
        if (mngAutRoleInfoDAO.updateMngAutRoleInfoByPk(targetMngAutRoleInfoDO) == 1) {
            authorityCacheService.removeRoleInfoById(roleId);
            return true;
        }
        return false;
    }

    @Override
    public List<MngAutRoleInfoDO> getAutOfRole(Long roleId, Query<MngAutRoleInfoDO> query) {

        if (roleId == null || roleId < 0) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  getAutOfRole :  roleId is null or roleId < 0 ");
        }
        if (query == null) {
            MngAutRoleInfoDO mngAutRoleInfoDO = new MngAutRoleInfoDO();
            mngAutRoleInfoDO.setRoleId(roleId);
            query.getModule().setRoleId(roleId);
            return mngAutRoleInfoDAO.queryMngAutRoleInfo(mngAutRoleInfoDO);
        } else {
            Query<MngAutRoleInfoDO> deQuery = new Query<MngAutRoleInfoDO>();
            MngAutRoleInfoDO module = new MngAutRoleInfoDO();
            module.setRoleId(roleId);
            deQuery.setModule(module);
            return mngAutRoleInfoDAO.queryMngAutRoleInfoByPage(deQuery);
        }

    }

    @Override
    public boolean disableAllAutOfRole(final Long roleId, final Long operateId) {
        if (roleId == null || roleId < 0) {
            throw new IllegalArgumentException("AuthorityManageServiceImpl  disableAllAutOfRole :  roleId is null or roleId < 0 ");
        }
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null  or < 0 ");
        }

        Exception e = authorityTransactionTemplate.execute(new TransactionCallback<Exception>() {
            @Override
            public Exception doInTransaction(TransactionStatus transactionStatus) {

                try {
                    //用户角色表修改条件
                    MngUserRoleInfoDO userModule = new MngUserRoleInfoDO();
                    userModule.setStatus(AuthStatusEnum.STATUS_FORBID.getCode());
                    userModule.setOperateId(operateId);
                    MngUserRoleInfoDO userCondition = new MngUserRoleInfoDO();
                    userCondition.setRoleId(roleId);
                    //权限角色表修改条件
                    MngAutRoleInfoDO autModule = new MngAutRoleInfoDO();
                    autModule.setStatus(AuthStatusEnum.STATUS_FORBID.getCode());
                    autModule.setOperateId(operateId);
                    MngAutRoleInfoDO autCondition = new MngAutRoleInfoDO();
                    autCondition.setRoleId(roleId);
                    //将用户角色表的对应角色禁用
                    int cnt = userRoleInfoDAO.updateMngUserRoleInfoByCondition(userModule, userCondition);
                    //将权限角色表的对应记录禁用
                    int cnt2 = mngAutRoleInfoDAO.updateMngAutRoleInfoByCondition(autModule, autCondition);

                } catch (Exception e1) {
                    transactionStatus.setRollbackOnly();
                    return e1;
                }
                return null;
            }
        });
        if (e == null) {
            authorityCacheService.removeRoleInfoById(roleId);
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyAuthorityOfUser(Long autId, Long userId) {
        if (userId == null || userId < 0) {
            return false;
        }
        if (autId == null || autId < 0) {
            return false;
        }
        //查询用户角色
        MngUserRoleInfoDO mngUserRoleInfoDO = _getUserRoleInfoByUserId(userId);
        if (mngUserRoleInfoDO == null) {
            return false;
        }
        if (mngUserRoleInfoDO.getRoleId() == null) {
            //查询角色是否有该权限
            return false;
        }
        return verifyAuthorityOfRole(autId, mngUserRoleInfoDO.getRoleId());

    }

    @Override
    public boolean verifyAuthorityOfUser(String autUrl, Long userId) throws QuAuthorizationException, ExecutionException {

        return authorityCacheService.verifyAuthorityOfUserByUrl(autUrl, userId);


        //查询用户角色
//        MngUserRoleInfoDO mngUserRoleInfoDO = _getUserRoleInfoByUserId(userId);
//        if (mngUserRoleInfoDO == null) {
//            return false;
//        }
//        if (mngUserRoleInfoDO.getRoleId() != null) {
//            //根据autUrl 查询该条权限是否存在
//            MngAuthorityBaseInfoDO authorityBaseInfoDO = new MngAuthorityBaseInfoDO();
//            authorityBaseInfoDO.setAuthUrl(autUrl);
//            MngAuthorityBaseInfoDO QAuthorityBaseInfoDO = mngAuthorityBaseInfoDAO.querySingleMngAuthorityBaseInfo(authorityBaseInfoDO);
//            if (QAuthorityBaseInfoDO == null) {
//                throw new QuAuthorizationException(AuthMsgEnum.AUT_NOT_EXSIT_ERROR);
//            }
//            return verifyAuthorityOfRole(QAuthorityBaseInfoDO.getId(), mngUserRoleInfoDO.getRoleId());
//        } else {
//            return false;
//        }


    }

    @Override
    public boolean verifyAuthorityOfRole(Long autId, Long roleId) {

        MngAutRoleInfoDO mngAutRoleInfoDO = new MngAutRoleInfoDO();
        mngAutRoleInfoDO.setRoleId(roleId);
        mngAutRoleInfoDO.setAutId(autId);
        mngAutRoleInfoDO.setStatus(AuthStatusEnum.STATUS_OK.getCode());
        MngAutRoleInfoDO result = mngAutRoleInfoDAO.querySingleMngAutRoleInfo(mngAutRoleInfoDO);
        if (result != null) {
            return false;
        }
        return true;


    }

    @Override
    public boolean verifyAuthorityOfRole(String autUrl, Long roleId) throws QuAuthorizationException, ExecutionException {


        return authorityCacheService.verifyAuthorityOfRoleByUrl(autUrl, roleId);
        //根据autUrl 查询该条权限是否存在
//        MngAuthorityBaseInfoDO authorityBaseInfoDO = new MngAuthorityBaseInfoDO();
//        authorityBaseInfoDO.setAuthUrl(autUrl);
//        MngAuthorityBaseInfoDO QAuthorityBaseInfoDO = mngAuthorityBaseInfoDAO.querySingleMngAuthorityBaseInfo(authorityBaseInfoDO);
//        if (QAuthorityBaseInfoDO == null) {
//            return false;
//        }
//        return verifyAuthorityOfRole(QAuthorityBaseInfoDO.getId(), roleId);

    }

    /**
     * 获取用户角色关联信息
     *
     * @param userId 用户Id
     * @return 用户RoleId or null
     */

    private MngUserRoleInfoDO _getUserRoleInfoByUserId(Long userId) {
        //查询用户角色
        MngUserRoleInfoDO mngUserRoleInfoDO = new MngUserRoleInfoDO();
        mngUserRoleInfoDO.setUserId(userId);
        mngUserRoleInfoDO.setStatus(AuthStatusEnum.STATUS_OK.getCode());
        MngUserRoleInfoDO PmngUserRoleInfoDO = userRoleInfoDAO.querySingleMngUserRoleInfo(mngUserRoleInfoDO);
        if (PmngUserRoleInfoDO != null) {
            return PmngUserRoleInfoDO;
        } else {
            return null;
        }
    }


    @Override
    public boolean addAuthority(String authDesc, String authUrl, Long parentId, Long operateId) throws QuAuthorizationException {
        if (StringUtils.isBlank(authDesc)) {
            throw new IllegalArgumentException("AuthorityServiceImpl addAuthority  :  authDesc can not be null");
        }
        if (StringUtils.isBlank(authUrl)) {
            throw new IllegalArgumentException("AuthorityServiceImpl addAuthority  :  authUrl can not be null");
        }
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null or  < 0 ");
        }
        MngAuthorityBaseInfoDO authorityBaseInfoDO = new MngAuthorityBaseInfoDO();
        authorityBaseInfoDO.setAuthDesc(authDesc);
        authorityBaseInfoDO.setAuthUrl(authUrl);
        authorityBaseInfoDO.setOperateId(operateId);
        if (parentId != null && parentId > 0) {
            authorityBaseInfoDO.setParentId(parentId);
        }
        if (mngAuthorityBaseInfoDAO.insertMngAuthorityBaseInfo(authorityBaseInfoDO) != 1) {
            throw new QuAuthorizationException(AuthMsgEnum.AUT_ADD_AUT_ERROR);
        }
        return true;
    }

    @Override
    public boolean deleteAuthority(final Long id) throws Exception {
        Exception e = authorityTransactionTemplate.execute(new TransactionCallback<Exception>() {
            @Override
            public Exception doInTransaction(TransactionStatus transactionStatus) {

                try {
                    mngAuthorityBaseInfoDAO.deleteMngAuthorityBaseInfoById(id);
                    MngAutRoleInfoDO mngAutRoleInfoDO = new MngAutRoleInfoDO();
                    mngAutRoleInfoDO.setAutId(id);
                    mngAutRoleInfoDAO.deleteByCondition(mngAutRoleInfoDO);

                } catch (Exception e1) {
                    transactionStatus.setRollbackOnly();
                    return e1;
                }
                return null;
            }
        });
        if (e == null) {
            authorityCacheService.removeAllRoleInfo();
            authorityCacheService.removeAllUserInfo();
            return true;
        }
        return false;
    }

    @Override
    public boolean resetAuthority(Long id, String authDesc, String authUrl, Long parentId, Long operateId) throws QuAuthorizationException {

        if (authDesc == null && authUrl == null && parentId == null) {
            throw new IllegalArgumentException("AuthorityServiceImpl reSetAuthority  :  Both authDesc,authUrl,parentId are null  ");
        }
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null or < 0");
        }
        MngAuthorityBaseInfoDO authorityBaseInfoDO = new MngAuthorityBaseInfoDO();
        authorityBaseInfoDO.setId(id);


        if (parentId != null && parentId >= 0) {
            authorityBaseInfoDO.setParentId(parentId);
        }
        authorityBaseInfoDO.setOperateId(operateId);

        if (mngAuthorityBaseInfoDAO.updateMngAuthorityBaseInfoByPk(authorityBaseInfoDO) == 1) {

            return true;
        }
        return false;

    }

    @Override
    public List<MngAuthorityBaseInfoDO> getAuthorityList(Query<MngAuthorityBaseInfoDO> query) throws QuAuthorizationException {
        return mngAuthorityBaseInfoDAO.queryMngAuthorityBaseInfoByPage(query);

    }

    @Override
    public List<MngAuthorityBaseInfoDO> getAuthorityList(MngAuthorityBaseInfoDO mngAuthorityBaseInfoDO) throws QuAuthorizationException {
        Query<MngAuthorityBaseInfoDO> query = new Query<MngAuthorityBaseInfoDO>();
        query.setPageSize(AuthorityBusinessConstance.DEFAULT_PAGE_SIZE);
        query.setModule(mngAuthorityBaseInfoDO);
        return mngAuthorityBaseInfoDAO.queryMngAuthorityBaseInfoByPage(query);
    }

    @Override
    public MngAuthorityBaseInfoDO getAuthorityInfo(Long id) {
        return mngAuthorityBaseInfoDAO.queryMngAuthorityBaseInfoById(id);
    }

}
