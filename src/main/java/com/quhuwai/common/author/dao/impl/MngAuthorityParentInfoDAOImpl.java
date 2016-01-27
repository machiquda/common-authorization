package com.quhuwai.common.author.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.quhuwai.common.author.domain.MngAuthorityParentInfoDO;
import com.quhuwai.common.author.domain.Query;
import com.quhuwai.common.author.dao.MngAuthorityParentInfoDAO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mng_authority_parent_info表的DAO层实现类
 * Date 2016-01-11 15:55:45
 * @author liwei
 */
@Component
public class MngAuthorityParentInfoDAOImpl implements MngAuthorityParentInfoDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;




	public List<MngAuthorityParentInfoDO> queryMngAuthorityParentInfo(MngAuthorityParentInfoDO condition) {
		if(condition == null){
            condition = new MngAuthorityParentInfoDO();
		}
		List<MngAuthorityParentInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.queryMngAuthorityParentInfo",condition);
		return list;
	}


	public MngAuthorityParentInfoDO querySingleMngAuthorityParentInfo(MngAuthorityParentInfoDO condition){
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
		List<MngAuthorityParentInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.querySingleMngAuthorityParentInfo",condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}



	public List<MngAuthorityParentInfoDO> queryMngAuthorityParentInfoByPage(Query<MngAuthorityParentInfoDO> query) {
		if(query == null){
			throw new IllegalArgumentException("query is null.");
		}
		if(query.getModule()==null){
        	query.setModule(new MngAuthorityParentInfoDO());
		}
		Map<String, Object> params = convertQuery2Param(query);
		int count = countMngAuthorityParentInfo(query.getModule());
		query.setTotalSize(count);
        if(count <= 0){
            return Collections.emptyList();
        }

		List<MngAuthorityParentInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.queryMngAuthorityParentInfoByPage",params);

		return list;
	}


    public int countMngAuthorityParentInfo(MngAuthorityParentInfoDO condition) {
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
        Integer count = (Integer)sqlSession.selectOne("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.countMngAuthorityParentInfo",condition);

        return count==null ? -1 : count;
    }





	public int updateMngAuthorityParentInfoByPk(MngAuthorityParentInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
		if(instance.getId()==null){
			throw new IllegalArgumentException("instance.getId is null.");
		}


		return  sqlSession.update("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.updateMngAuthorityParentInfoByPk",instance);
	}

    public int updateMngAuthorityParentInfoByCondition(MngAuthorityParentInfoDO instance,MngAuthorityParentInfoDO condition){
        if(instance == null){
            throw new IllegalArgumentException("instance is null.");
        }
        if(condition == null){
            throw new IllegalArgumentException("condition is null.");
        }

        Map<String,Object> params = new HashMap<String, Object>(2,1.0f);
        params.put("instance",instance);
        params.put("condition",condition);

        return sqlSession.update("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.updateMngAuthorityParentInfoByCondition",params);


    }

	public int insertMngAuthorityParentInfo(MngAuthorityParentInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
        int cnt= sqlSession.insert("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.insertMngAuthorityParentInfo",instance);
		return cnt;
	}


    public void insertBatchMngAuthorityParentInfo(List<MngAuthorityParentInfoDO> list) throws Exception{
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list is null or empty.");
        }
        SqlSession batchSqlSession = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        try {
            for (MngAuthorityParentInfoDO instance : list) {
                 batchSqlSession.insert("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.insertMngAuthorityParentInfo",instance);
            }
            batchSqlSession.commit();
        } catch(Exception e) {
            batchSqlSession.rollback();
            throw e;
        } finally {
            batchSqlSession.close();
        }
    }


	public MngAuthorityParentInfoDO queryMngAuthorityParentInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
		MngAuthorityParentInfoDO condition = new MngAuthorityParentInfoDO();
		condition.setId(id);
		List<MngAuthorityParentInfoDO> list = queryMngAuthorityParentInfo(condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}





    public int deleteByCondition(MngAuthorityParentInfoDO condition){
        if(condition == null){
			throw new IllegalArgumentException("condition is null.");
        }
        return  sqlSession.delete("com.quhuwai.common.author.domain.MngAuthorityParentInfoMapper.deleteByCondition",condition);
    }


    public boolean deleteMngAuthorityParentInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
        MngAuthorityParentInfoDO condition = new MngAuthorityParentInfoDO();
        condition.setId(id);
        int cnt = deleteByCondition(condition);
        return cnt>0;
    }


	private Map<String, Object> convertQuery2Param(Query<MngAuthorityParentInfoDO> query){
		MngAuthorityParentInfoDO instance = query.getModule();
        if(instance == null ){
            instance = new MngAuthorityParentInfoDO();
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
