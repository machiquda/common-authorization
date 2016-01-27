package com.quhuwai.common.author.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.quhuwai.common.author.domain.MngUserBaseInfoDO;
import com.quhuwai.common.author.domain.Query;
import com.quhuwai.common.author.dao.MngUserBaseInfoDAO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mng_user_base_info表的DAO层实现类
 * Date 2016-01-11 15:55:45
 * @author liwei
 */
@Component
public class MngUserBaseInfoDAOImpl implements MngUserBaseInfoDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;




	public List<MngUserBaseInfoDO> queryMngUserBaseInfo(MngUserBaseInfoDO condition) {
		if(condition == null){
            condition = new MngUserBaseInfoDO();
		}
		List<MngUserBaseInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.queryMngUserBaseInfo",condition);
		return list;
	}


	public MngUserBaseInfoDO querySingleMngUserBaseInfo(MngUserBaseInfoDO condition){
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
		List<MngUserBaseInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.querySingleMngUserBaseInfo",condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}



	public List<MngUserBaseInfoDO> queryMngUserBaseInfoByPage(Query<MngUserBaseInfoDO> query) {
		if(query == null){
			throw new IllegalArgumentException("query is null.");
		}
		if(query.getModule()==null){
        	query.setModule(new MngUserBaseInfoDO());
		}
		Map<String, Object> params = convertQuery2Param(query);
		int count = countMngUserBaseInfo(query.getModule());
		query.setTotalSize(count);
        if(count <= 0){
            return Collections.emptyList();
        }

		List<MngUserBaseInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.queryMngUserBaseInfoByPage",params);

		return list;
	}


    public int countMngUserBaseInfo(MngUserBaseInfoDO condition) {
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
        Integer count = (Integer)sqlSession.selectOne("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.countMngUserBaseInfo",condition);

        return count==null ? -1 : count;
    }





	public int updateMngUserBaseInfoByPk(MngUserBaseInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
		if(instance.getId()==null){
			throw new IllegalArgumentException("instance.getId is null.");
		}


		return  sqlSession.update("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.updateMngUserBaseInfoByPk",instance);
	}

    public int updateMngUserBaseInfoByCondition(MngUserBaseInfoDO instance,MngUserBaseInfoDO condition){
        if(instance == null){
            throw new IllegalArgumentException("instance is null.");
        }
        if(condition == null){
            throw new IllegalArgumentException("condition is null.");
        }

        Map<String,Object> params = new HashMap<String, Object>(2,1.0f);
        params.put("instance",instance);
        params.put("condition",condition);

        return sqlSession.update("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.updateMngUserBaseInfoByCondition",params);


    }

	public int insertMngUserBaseInfo(MngUserBaseInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
        int cnt= sqlSession.insert("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.insertMngUserBaseInfo",instance);
		return cnt;
	}


    public void insertBatchMngUserBaseInfo(List<MngUserBaseInfoDO> list) throws Exception{
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list is null or empty.");
        }
        SqlSession batchSqlSession = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        try {
            for (MngUserBaseInfoDO instance : list) {
                 batchSqlSession.insert("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.insertMngUserBaseInfo",instance);
            }
            batchSqlSession.commit();
        } catch(Exception e) {
            batchSqlSession.rollback();
            throw e;
        } finally {
            batchSqlSession.close();
        }
    }


	public MngUserBaseInfoDO queryMngUserBaseInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
		MngUserBaseInfoDO condition = new MngUserBaseInfoDO();
		condition.setId(id);
		List<MngUserBaseInfoDO> list = queryMngUserBaseInfo(condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}





    public int deleteByCondition(MngUserBaseInfoDO condition){
        if(condition == null){
			throw new IllegalArgumentException("condition is null.");
        }
        return  sqlSession.delete("com.quhuwai.common.author.domain.MngUserBaseInfoMapper.deleteByCondition",condition);
    }


    public boolean deleteMngUserBaseInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
        MngUserBaseInfoDO condition = new MngUserBaseInfoDO();
        condition.setId(id);
        int cnt = deleteByCondition(condition);
        return cnt>0;
    }


	private Map<String, Object> convertQuery2Param(Query<MngUserBaseInfoDO> query){
		MngUserBaseInfoDO instance = query.getModule();
        if(instance == null ){
            instance = new MngUserBaseInfoDO();
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
