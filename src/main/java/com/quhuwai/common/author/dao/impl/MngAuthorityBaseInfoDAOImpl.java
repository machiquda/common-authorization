package com.quhuwai.common.author.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.quhuwai.common.author.domain.MngAuthorityBaseInfoDO;
import com.quhuwai.common.author.domain.Query;
import com.quhuwai.common.author.dao.MngAuthorityBaseInfoDAO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mng_authority_base_info表的DAO层实现类
 * Date 2016-01-11 15:55:45
 * @author liwei
 */
@Component
public class MngAuthorityBaseInfoDAOImpl implements MngAuthorityBaseInfoDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;




	public List<MngAuthorityBaseInfoDO> queryMngAuthorityBaseInfo(MngAuthorityBaseInfoDO condition) {
		if(condition == null){
            condition = new MngAuthorityBaseInfoDO();
		}
		List<MngAuthorityBaseInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.queryMngAuthorityBaseInfo",condition);
		return list;
	}


	public MngAuthorityBaseInfoDO querySingleMngAuthorityBaseInfo(MngAuthorityBaseInfoDO condition){
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
		List<MngAuthorityBaseInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.querySingleMngAuthorityBaseInfo",condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}



	public List<MngAuthorityBaseInfoDO> queryMngAuthorityBaseInfoByPage(Query<MngAuthorityBaseInfoDO> query) {
		if(query == null){
			throw new IllegalArgumentException("query is null.");
		}
		if(query.getModule()==null){
        	query.setModule(new MngAuthorityBaseInfoDO());
		}
		Map<String, Object> params = convertQuery2Param(query);
		int count = countMngAuthorityBaseInfo(query.getModule());
		query.setTotalSize(count);
        if(count <= 0){
            return Collections.emptyList();
        }

		List<MngAuthorityBaseInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.queryMngAuthorityBaseInfoByPage",params);

		return list;
	}


    public int countMngAuthorityBaseInfo(MngAuthorityBaseInfoDO condition) {
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
        Integer count = (Integer)sqlSession.selectOne("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.countMngAuthorityBaseInfo",condition);

        return count==null ? -1 : count;
    }





	public int updateMngAuthorityBaseInfoByPk(MngAuthorityBaseInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
		if(instance.getId()==null){
			throw new IllegalArgumentException("instance.getId is null.");
		}


		return  sqlSession.update("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.updateMngAuthorityBaseInfoByPk",instance);
	}

    public int updateMngAuthorityBaseInfoByCondition(MngAuthorityBaseInfoDO instance,MngAuthorityBaseInfoDO condition){
        if(instance == null){
            throw new IllegalArgumentException("instance is null.");
        }
        if(condition == null){
            throw new IllegalArgumentException("condition is null.");
        }

        Map<String,Object> params = new HashMap<String, Object>(2,1.0f);
        params.put("instance",instance);
        params.put("condition",condition);

        return sqlSession.update("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.updateMngAuthorityBaseInfoByCondition",params);


    }

	public int insertMngAuthorityBaseInfo(MngAuthorityBaseInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
        int cnt= sqlSession.insert("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.insertMngAuthorityBaseInfo",instance);
		return cnt;
	}


    public void insertBatchMngAuthorityBaseInfo(List<MngAuthorityBaseInfoDO> list) throws Exception{
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list is null or empty.");
        }
        SqlSession batchSqlSession = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        try {
            for (MngAuthorityBaseInfoDO instance : list) {
                 batchSqlSession.insert("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.insertMngAuthorityBaseInfo",instance);
            }
            batchSqlSession.commit();
        } catch(Exception e) {
            batchSqlSession.rollback();
            throw e;
        } finally {
            batchSqlSession.close();
        }
    }


	public MngAuthorityBaseInfoDO queryMngAuthorityBaseInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
		MngAuthorityBaseInfoDO condition = new MngAuthorityBaseInfoDO();
		condition.setId(id);
		List<MngAuthorityBaseInfoDO> list = queryMngAuthorityBaseInfo(condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}





    public int deleteByCondition(MngAuthorityBaseInfoDO condition){
        if(condition == null){
			throw new IllegalArgumentException("condition is null.");
        }
        return  sqlSession.delete("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.deleteByCondition",condition);
    }


    public boolean deleteMngAuthorityBaseInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
        MngAuthorityBaseInfoDO condition = new MngAuthorityBaseInfoDO();
        condition.setId(id);
        int cnt = deleteByCondition(condition);
        return cnt>0;
    }


	private Map<String, Object> convertQuery2Param(Query<MngAuthorityBaseInfoDO> query){
		MngAuthorityBaseInfoDO instance = query.getModule();
        if(instance == null ){
            instance = new MngAuthorityBaseInfoDO();
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

	@Override
	public List<MngAuthorityBaseInfoDO> getRoleAutListById(Long roleId) {
		if(roleId == null){
			throw new IllegalArgumentException("roleId is null.");
		}

		Map<String, Object> params = new HashMap<String, Object>(1,1.0f);
		params.put("roleId", roleId);


		List<MngAuthorityBaseInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.unQueryMngAuthorityBaseInfo",params);

		return list;
	}

	@Override
	public List<MngAuthorityBaseInfoDO> getRoleActiveAutListById(Long roleId) {


		if(roleId == null){
			throw new IllegalArgumentException("roleId is null.");
		}

		Map<String, Object> params = new HashMap<String, Object>(1,1.0f);
		params.put("roleId", roleId);


		List<MngAuthorityBaseInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAuthorityBaseInfoMapper.unQueryActiveMngAuthorityBaseInfo",params);

		return list;
	}
}
