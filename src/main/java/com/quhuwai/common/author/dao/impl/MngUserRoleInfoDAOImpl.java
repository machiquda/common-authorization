package com.quhuwai.common.author.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quhuwai.common.author.domain.MngUserRoleInfoDO;
import com.quhuwai.common.author.domain.Query;
import com.quhuwai.common.author.dao.MngUserRoleInfoDAO;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mng_user_role_info表的DAO层实现类
 * Date 2016-01-11 15:55:45
 *
 * @author liwei
 */
@Component
public class MngUserRoleInfoDAOImpl implements MngUserRoleInfoDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;


    public List<MngUserRoleInfoDO> queryMngUserRoleInfo(MngUserRoleInfoDO condition) {
        if (condition == null) {
            condition = new MngUserRoleInfoDO();
        }
        List<MngUserRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.queryMngUserRoleInfo", condition);
        return list;
    }


    public MngUserRoleInfoDO querySingleMngUserRoleInfo(MngUserRoleInfoDO condition) {
        if (condition == null) {
            throw new IllegalArgumentException("condition is null.");
        }
        List<MngUserRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.querySingleMngUserRoleInfo", condition);

        return (list == null || list.size() == 0) ? null : list.get(0);
    }


    public List<MngUserRoleInfoDO> queryMngUserRoleInfoByPage(Query<MngUserRoleInfoDO> query) {
        if (query == null) {
            throw new IllegalArgumentException("query is null.");
        }
        if (query.getModule() == null) {
            query.setModule(new MngUserRoleInfoDO());
        }
        Map<String, Object> params = convertQuery2Param(query);
        int count = countMngUserRoleInfo(query.getModule());
        query.setTotalSize(count);
        if (count <= 0) {
            return Collections.emptyList();
        }

        List<MngUserRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.queryMngUserRoleInfoByPage", params);

        return list;
    }


    public int countMngUserRoleInfo(MngUserRoleInfoDO condition) {
        if (condition == null) {
            throw new IllegalArgumentException("condition is null.");
        }
        Integer count = (Integer) sqlSession.selectOne("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.countMngUserRoleInfo", condition);

        return count == null ? -1 : count;
    }


    public int updateMngUserRoleInfoByPk(MngUserRoleInfoDO instance) {
        if (instance == null) {
            throw new IllegalArgumentException("instance is null.");
        }
        if (instance.getId() == null) {
            throw new IllegalArgumentException("instance.getId is null.");
        }


        return sqlSession.update("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.updateMngUserRoleInfoByPk", instance);
    }

    public int updateMngUserRoleInfoByCondition(MngUserRoleInfoDO instance, MngUserRoleInfoDO condition) {
        if (instance == null) {
            throw new IllegalArgumentException("instance is null.");
        }
        if (condition == null) {
            throw new IllegalArgumentException("condition is null.");
        }

        Map<String, Object> params = new HashMap<String, Object>(2, 1.0f);
        params.put("instance", instance);
        params.put("condition", condition);

        return sqlSession.update("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.updateMngUserRoleInfoByCondition", params);


    }

    public int insertMngUserRoleInfo(MngUserRoleInfoDO instance) {
        if (instance == null) {
            throw new IllegalArgumentException("instance is null.");
        }
        int cnt = sqlSession.insert("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.insertMngUserRoleInfo", instance);
        return cnt;
    }


    public void insertBatchMngUserRoleInfo(List<MngUserRoleInfoDO> list) throws Exception {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list is null or empty.");
        }
        SqlSession batchSqlSession = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        try {
            for (MngUserRoleInfoDO instance : list) {
                batchSqlSession.insert("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.insertMngUserRoleInfo", instance);
            }
            batchSqlSession.commit();
        } catch (Exception e) {
            batchSqlSession.rollback();
            throw e;
        } finally {
            batchSqlSession.close();
        }
    }


    public MngUserRoleInfoDO queryMngUserRoleInfoById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null.");
        }
        MngUserRoleInfoDO condition = new MngUserRoleInfoDO();
        condition.setId(id);
        List<MngUserRoleInfoDO> list = queryMngUserRoleInfo(condition);

        return (list == null || list.size() == 0) ? null : list.get(0);
    }


    public int deleteByCondition(MngUserRoleInfoDO condition) {
        if (condition == null) {
            throw new IllegalArgumentException("condition is null.");
        }
        return sqlSession.delete("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.deleteByCondition", condition);
    }


    public boolean deleteMngUserRoleInfoById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null.");
        }
        MngUserRoleInfoDO condition = new MngUserRoleInfoDO();
        condition.setId(id);
        int cnt = deleteByCondition(condition);
        return cnt > 0;
    }

    @Override
    public int distributeRoleToUser(Long[] ids, Long role_id,Long operateId) {
        if (ids == null || ids.length == 0) {
            throw new IllegalArgumentException("ids is null.");
        }
        if (role_id == null) {
            throw new IllegalArgumentException("role_id is null.");
        }
        if (operateId == null) {
            throw new IllegalArgumentException("operateId is null.");
        }

        Map<String, Object> params = new HashMap<String, Object>(3, 1.0f);
        params.put("ids", ids);
        params.put("roleId", role_id);
        params.put("operateId", operateId);
        return sqlSession.update("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.distributeRoleToUser", params);


    }

    @Override
    public int distributeRoleToUser(List<Long> ids, Long role_id) {
        if (ids == null || ids.size() == 0) {
            throw new IllegalArgumentException("ids is null.");
        }
        if (role_id == null) {
            throw new IllegalArgumentException("role_id is null.");
        }
        Long[] id = new Long[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            id[i] = ids.get(i);
        }
        Map<String, Object> params = new HashMap<String, Object>(2, 1.0f);
        params.put("ids", id);
        params.put("roleId", role_id);

        return sqlSession.update("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.distributeRoleToUser", params);


    }

    @Override
    public List<MngUserRoleInfoDO> queryMngUserRoleInfo(Long[] userIds) {
        if (userIds == null) {
            throw new IllegalArgumentException("ids is null.");
        }
        Map<String, Object> params = new HashMap<String, Object>(1, 1.0f);
        params.put("ids", userIds);
        List<MngUserRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngUserRoleInfoMapper.conditionQueryMngUserRoleInfo", params);
        return list;
    }

    private Map<String, Object> convertQuery2Param(Query<MngUserRoleInfoDO> query) {
        MngUserRoleInfoDO instance = query.getModule();
        if (instance == null) {
            instance = new MngUserRoleInfoDO();
        }
        Map<String, Object> params = new HashMap<String, Object>(6, 1.0f);

        params.put("_order", query.getOrderBy());
        params.put("module", instance);
        params.put("_limit_start", query.getStart() - 1);
        params.put("_limit_size", query.getPageSize());
        params.put("_limit_end", query.getStart() + query.getPageSize());
        params.put("module", instance);

        return params;
    }

}
