package com.quhuwai.common.author.service.impl;

import com.quhuwai.common.author.common.AuthMsgEnum;
import com.quhuwai.common.author.common.AuthStatusEnum;
import com.quhuwai.common.author.common.AuthorityBusinessConstance;
import com.quhuwai.common.author.common.Exception.QuAuthorizationException;
import com.quhuwai.common.author.dao.MngAutRoleInfoDAO;
import com.quhuwai.common.author.dao.MngAuthorityBaseInfoDAO;
import com.quhuwai.common.author.dao.MngRoleInfoDAO;
import com.quhuwai.common.author.dao.MngUserRoleInfoDAO;
import com.quhuwai.common.author.domain.*;
import com.quhuwai.common.author.service.AuthorityCacheService;
import com.quhuwai.common.author.service.RoleManageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 角色管理实现类
 * Created by fys on 2016/1/8.
 */
@Component
public class RoleManageImpl implements RoleManageService {
    @Resource
    private TransactionTemplate authorityTransactionTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RoleManageImpl.class);
    @Autowired
    private MngRoleInfoDAO mngRoleInfoDAO;
    @Autowired
    private MngUserRoleInfoDAO userRoleInfoDAO;
    @Autowired
    private MngAuthorityBaseInfoDAO mngAuthorityBaseInfoDAO;
    @Autowired
    private MngAutRoleInfoDAO mngAutRoleInfoDAO;
    @Autowired
    private AuthorityCacheService authorityCacheService;

    @Override
    public boolean addRole(String role_desc, String role_name, Long operateId) throws QuAuthorizationException {
        if (StringUtils.isBlank(role_desc)) {
            throw new IllegalArgumentException("RoleManageImpl addRole  :  role_desc can not be null");
        }
        if (StringUtils.isBlank(role_name)) {
            throw new IllegalArgumentException("RoleManageImpl addRole  :  role_name can not be null");
        }
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null or < 0 ");
        }
        MngRoleInfoDO roleInfoDO = new MngRoleInfoDO();
        roleInfoDO.setRoleDesc(role_desc);
        roleInfoDO.setRoleName(role_name);
        roleInfoDO.setOperateId(operateId);
        if (mngRoleInfoDAO.insertMngRoleInfo(roleInfoDO) != 1) {
            throw new QuAuthorizationException(AuthMsgEnum.AUT_ADD_ROLE_ERROR);
        }
        return true;
    }

    @Override
    public boolean deleteRole(final Long id) {
        Exception e = authorityTransactionTemplate.execute(new TransactionCallback<Exception>() {
            @Override
            public Exception doInTransaction(TransactionStatus transactionStatus) {

                try {

                    mngRoleInfoDAO.deleteMngRoleInfoById(id);

                    //删除role-aut表数据
                    MngAutRoleInfoDO mngAutRoleInfoDO = new MngAutRoleInfoDO();
                    mngAutRoleInfoDO.setRoleId(id);
                    mngAutRoleInfoDAO.deleteByCondition(mngAutRoleInfoDO);
                    //删除role-user表数据
                    MngUserRoleInfoDO mngUserRoleInfoDO = new MngUserRoleInfoDO();
                    mngUserRoleInfoDO.setRoleId(id);
                    userRoleInfoDAO.deleteByCondition(mngUserRoleInfoDO);


                } catch (Exception e1) {
                    transactionStatus.setRollbackOnly();
                    return e1;
                }
                return null;
            }
        });
        if (e == null) {
            authorityCacheService.removeRoleInfoById(id);
            authorityCacheService.removeAllUserInfo();
            return true;
        }
        return false;
    }

    @Override
    public List<MngRoleInfoDO> getRoleList(Query<MngRoleInfoDO> query) {
        return mngRoleInfoDAO.queryMngRoleInfoByPage(query);
    }

    @Override
    public boolean resetRole(final Long id, final String role_desc, final String role_name, final int status, final Long operateId) {

        if (role_desc == null && StringUtils.isBlank(role_name)) {
            throw new IllegalArgumentException("RoleManageImpl resetRole  :  Both role_desc,role_desc are null  ");
        }
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null or < 0");
        }


        Exception e = authorityTransactionTemplate.execute(new TransactionCallback<Exception>() {
            @Override
            public Exception doInTransaction(TransactionStatus transactionStatus) {

                try {


                    MngRoleInfoDO roleInfoDO = new MngRoleInfoDO();
                    roleInfoDO.setId(id);
                    roleInfoDO.setRoleDesc(role_desc);
                    roleInfoDO.setOperateId(operateId);
                    if (status == AuthStatusEnum.STATUS_FORBID.getCode() || status == AuthStatusEnum.STATUS_OK.getCode()) {

                        roleInfoDO.setStatus(status);
                    }


                    mngRoleInfoDAO.updateMngRoleInfoByPk(roleInfoDO);

                    if (status == AuthStatusEnum.STATUS_FORBID.getCode()) {

                        //权限角色表修改条件
                        MngAutRoleInfoDO autModule = new MngAutRoleInfoDO();
                        autModule.setStatus(AuthStatusEnum.STATUS_FORBID.getCode());
                        autModule.setOperateId(operateId);
                        MngAutRoleInfoDO autCondition = new MngAutRoleInfoDO();
                        autCondition.setRoleId(id);


                        //将权限角色表的对应记录禁用
                        int cnt2 = mngAutRoleInfoDAO.updateMngAutRoleInfoByCondition(autModule, autCondition);
                    }

                } catch (Exception e1) {
                    transactionStatus.setRollbackOnly();
                    return e1;
                }
                return null;
            }
        });
        if (e == null) {
            authorityCacheService.removeRoleInfoById(id);
            return true;
        }
        return false;
    }

    @Override
    public MngAutDetailofRole getRoleInfoById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException(" roleId is null or roleId < 0 ");
        }
        MngAutDetailofRole mngAutDetailofRole = new MngAutDetailofRole();
        //查询角色已分配的权限信息
        List<MngAuthorityBaseInfoDO> mngAutRoleInfoDOsList = mngAuthorityBaseInfoDAO.getRoleAutListById(id);
        //查询角色详情
        MngRoleInfoDO mngRoleInfoDO = mngRoleInfoDAO.queryMngRoleInfoById(id);
        BeanUtils.copyProperties(mngRoleInfoDO, mngAutDetailofRole);
        mngAutDetailofRole.setAutList(mngAutRoleInfoDOsList);

        return mngAutDetailofRole;
    }

    @Override
    public List<MngRoleInfoDO> getRoleList(MngRoleInfoDO MngRoleInfoDO) {
        Query<MngRoleInfoDO> query = new Query<MngRoleInfoDO>();
        query.setPageSize(AuthorityBusinessConstance.DEFAULT_PAGE_SIZE);
        query.setModule(MngRoleInfoDO);
        return mngRoleInfoDAO.queryMngRoleInfoByPage(query);
    }


    @Override
    public boolean distributeRoleToUser(Long userId, Long roleId, Long operateId) throws QuAuthorizationException {
        if (userId == null || userId < 0) {
            throw new IllegalArgumentException("AuthorityUserServiceImpl  distributeRoleToUser : userId is null or userId < 0");
        }
        if (roleId == null || roleId < 0) {
            throw new IllegalArgumentException("AuthorityUserServiceImpl  distributeRoleToUser : roleId is null or roleId < 0");
        }
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null or < 0");
        }
        //检查角色是否存在
        if (mngRoleInfoDAO.queryMngRoleInfoById(roleId) == null) {
            throw new QuAuthorizationException(AuthMsgEnum.ROLE_NOT_EXSIT_ERROR);
        }

        //查询是否之前该User已经分配过角色
        MngUserRoleInfoDO mngUserRoleInfoDO = new MngUserRoleInfoDO();
        mngUserRoleInfoDO.setUserId(userId);
        MngUserRoleInfoDO PmngUserRoleInfoDO = userRoleInfoDAO.querySingleMngUserRoleInfo(mngUserRoleInfoDO);
        if (PmngUserRoleInfoDO != null) {
            //分配过修改
            PmngUserRoleInfoDO.setRoleId(roleId);
            PmngUserRoleInfoDO.setOperateId(operateId);
            if (userRoleInfoDAO.updateMngUserRoleInfoByPk(PmngUserRoleInfoDO) != 1) {
                return false;
            }
            return true;


        }
        //未分配过插入新纪录
        PmngUserRoleInfoDO = new MngUserRoleInfoDO();
        PmngUserRoleInfoDO.setRoleId(roleId);
        PmngUserRoleInfoDO.setUserId(userId);
        PmngUserRoleInfoDO.setOperateId(operateId);
        if (userRoleInfoDAO.insertMngUserRoleInfo(PmngUserRoleInfoDO) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public int distributeRoleToUser(final Long[] ids, final Long roleId, final Long operateId) throws QuAuthorizationException {
        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null ");
        }
        if (ids == null || ids.length == 0) {
            throw new IllegalArgumentException("ids is null or size = 0 ");
        }
        if (roleId == null || roleId < 0) {
            throw new IllegalArgumentException("roleId is null or < 0 ");
        }
        //检查角色是否存在
        if (mngRoleInfoDAO.queryMngRoleInfoById(roleId) == null) {
            return 0;
        }

        //查询那些UserId 已经分配过角色
        final List<MngUserRoleInfoDO> needUpdateList = userRoleInfoDAO.queryMngUserRoleInfo(ids);
        if (needUpdateList != null && needUpdateList.size() > 0) {
            Exception e = authorityTransactionTemplate.execute(new TransactionCallback<Exception>() {
                @Override
                public Exception doInTransaction(TransactionStatus transactionStatus) {

                    try {
                        List<Long> idList = new ArrayList<Long>();
                        idList.addAll(Arrays.<Long>asList(ids));

                        Long[] needUpdateIds = new Long[needUpdateList.size()];
                        //剔除已经分配过的UseriD
                        for (int i = 0; i < needUpdateList.size(); i++) {
                            idList.remove(needUpdateList.get(i).getUserId());
                            needUpdateIds[i] = needUpdateList.get(i).getUserId();
                        }
                        //已分配过的更新记录
                        userRoleInfoDAO.distributeRoleToUser(needUpdateIds, roleId, operateId);

                        List<MngUserRoleInfoDO> insertList = new ArrayList<MngUserRoleInfoDO>();
                        for (int i = 0; i < idList.size(); i++) {
                            MngUserRoleInfoDO data = new MngUserRoleInfoDO();
                            data.setUserId(idList.get(i));
                            data.setRoleId(roleId);
                            data.setStatus(AuthStatusEnum.STATUS_OK.getCode());
                            data.setOperateId(operateId);
                            insertList.add(data);
                        }
                        //没分配过插入记录
                        userRoleInfoDAO.insertBatchMngUserRoleInfo(insertList);

                    } catch (Exception e1) {
                        transactionStatus.setRollbackOnly();
                        return e1;
                    }
                    return null;
                }
            });
            if (e == null) {
                return ids.length;
            }
            throw new QuAuthorizationException(AuthMsgEnum.AUT_ADD_ROLE_ERROR, e);

        }
        //如果都没有分配过  插入新纪录
        List<MngUserRoleInfoDO> insertList = new ArrayList<MngUserRoleInfoDO>();
        for (int i = 0; i < ids.length; i++) {
            MngUserRoleInfoDO data = new MngUserRoleInfoDO();
            data.setUserId(ids[i]);
            data.setRoleId(roleId);
            data.setStatus(AuthStatusEnum.STATUS_OK.getCode());
            data.setOperateId(operateId);
            insertList.add(data);
        }
        try {
            userRoleInfoDAO.insertBatchMngUserRoleInfo(insertList);

        } catch (Exception e) {
            throw new QuAuthorizationException(AuthMsgEnum.AUT_ADD_ROLE_ERROR, e);
        }
        return ids.length;


    }


    @Override
    public int distributeRoleToUser(List<Long> ids, Long roleId, Long operateId) throws QuAuthorizationException {

        if (operateId == null || operateId < 0) {
            throw new IllegalArgumentException("operateId is null ");
        }
        if (ids == null || ids.size() == 0) {
            throw new IllegalArgumentException("ids is null or size = 0 ");
        }
        if (roleId == null || roleId < 0) {
            throw new IllegalArgumentException("roleId is null or < 0 ");
        }
        Long[] id = new Long[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            id[i] = ids.get(i);
        }
        return distributeRoleToUser(id, roleId, operateId);
    }

    @Override
    public boolean removeRoleOfUser(Long userId, Long operateId) {

        MngUserRoleInfoDO mngUserRoleInfoDO = new MngUserRoleInfoDO();
        mngUserRoleInfoDO.setUserId(userId);

        if (userRoleInfoDAO.deleteByCondition(mngUserRoleInfoDO) == 1) {
            return true;
        }
        return false;
    }


}


