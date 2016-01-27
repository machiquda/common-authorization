package com.quhuwai.common.author.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quhuwai.common.author.domain.MngAuthorityBaseInfoDO;
import com.quhuwai.common.author.domain.MngRoleInfoDO;
import com.quhuwai.common.author.domain.Query;
import com.quhuwai.common.author.dao.MngRoleInfoDAO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mng_role_info表的DAO层实现类
 * Date 2016-01-11 15:55:45
 * @author liwei
 */
@Component
public class MngRoleInfoDAOImpl implements MngRoleInfoDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;




	public List<MngRoleInfoDO> queryMngRoleInfo(MngRoleInfoDO condition) {
		if(condition == null){
            condition = new MngRoleInfoDO();
		}
		List<MngRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngRoleInfoMapper.queryMngRoleInfo",condition);
		return list;
	}


	public MngRoleInfoDO querySingleMngRoleInfo(MngRoleInfoDO condition){
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
		List<MngRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngRoleInfoMapper.querySingleMngRoleInfo",condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}



	public List<MngRoleInfoDO> queryMngRoleInfoByPage(Query<MngRoleInfoDO> query) {
		if(query == null){
			throw new IllegalArgumentException("query is null.");
		}
		if(query.getModule()==null){
        	query.setModule(new MngRoleInfoDO());
		}
		Map<String, Object> params = convertQuery2Param(query);
		int count = countMngRoleInfo(query.getModule());
		query.setTotalSize(count);
        if(count <= 0){
            return Collections.emptyList();
        }

		List<MngRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngRoleInfoMapper.queryMngRoleInfoByPage",params);

		return list;
	}


    public int countMngRoleInfo(MngRoleInfoDO condition) {
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
        Integer count = (Integer)sqlSession.selectOne("com.quhuwai.common.author.domain.MngRoleInfoMapper.countMngRoleInfo",condition);

        return count==null ? -1 : count;
    }





	public int updateMngRoleInfoByPk(MngRoleInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
		if(instance.getId()==null){
			throw new IllegalArgumentException("instance.getId is null.");
		}


		return  sqlSession.update("com.quhuwai.common.author.domain.MngRoleInfoMapper.updateMngRoleInfoByPk",instance);
	}

    public int updateMngRoleInfoByCondition(MngRoleInfoDO instance,MngRoleInfoDO condition){
        if(instance == null){
            throw new IllegalArgumentException("instance is null.");
        }
        if(condition == null){
            throw new IllegalArgumentException("condition is null.");
        }

        Map<String,Object> params = new HashMap<String, Object>(2,1.0f);
        params.put("instance",instance);
        params.put("condition",condition);

        return sqlSession.update("com.quhuwai.common.author.domain.MngRoleInfoMapper.updateMngRoleInfoByCondition",params);


    }

	public int insertMngRoleInfo(MngRoleInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
        int cnt= sqlSession.insert("com.quhuwai.common.author.domain.MngRoleInfoMapper.insertMngRoleInfo",instance);
		return cnt;
	}


    public void insertBatchMngRoleInfo(List<MngRoleInfoDO> list) throws Exception{
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list is null or empty.");
        }
        SqlSession batchSqlSession = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        try {
            for (MngRoleInfoDO instance : list) {
                 batchSqlSession.insert("com.quhuwai.common.author.domain.MngRoleInfoMapper.insertMngRoleInfo",instance);
            }
            batchSqlSession.commit();
        } catch(Exception e) {
            batchSqlSession.rollback();
            throw e;
        } finally {
            batchSqlSession.close();
        }
    }


	public MngRoleInfoDO queryMngRoleInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
		MngRoleInfoDO condition = new MngRoleInfoDO();
		condition.setId(id);
		List<MngRoleInfoDO> list = queryMngRoleInfo(condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}





    public int deleteByCondition(MngRoleInfoDO condition){
        if(condition == null){
			throw new IllegalArgumentException("condition is null.");
        }
        return  sqlSession.delete("com.quhuwai.common.author.domain.MngRoleInfoMapper.deleteByCondition",condition);
    }


    public boolean deleteMngRoleInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
        MngRoleInfoDO condition = new MngRoleInfoDO();
        condition.setId(id);
        int cnt = deleteByCondition(condition);
        return cnt>0;
    }


	private Map<String, Object> convertQuery2Param(Query<MngRoleInfoDO> query){
		MngRoleInfoDO instance = query.getModule();
        if(instance == null ){
            instance = new MngRoleInfoDO();
        }
		Map<String, Object> params = new HashMap<String, Object>(6,1.0f);

		params.put("_order", query.getOrderBy());
		params.put("module", instance);
		params.put("_limit_start", query.getStart()-1);
		params.put("_limit_size", query.getPageSize());
		params.put("_limit_end", query.getStart()+query.getPageSize());
		params.put("module", instance);

		return params;
	}


}
