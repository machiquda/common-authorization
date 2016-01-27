package com.quhuwai.common.author.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.quhuwai.common.author.domain.MngAutRoleInfoDO;
import com.quhuwai.common.author.domain.Query;
import com.quhuwai.common.author.dao.MngAutRoleInfoDAO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mng_aut_role_info表的DAO层实现类
 * Date 2016-01-11 15:55:45
 * @author liwei
 */
@Component
public class MngAutRoleInfoDAOImpl implements MngAutRoleInfoDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;




	public List<MngAutRoleInfoDO> queryMngAutRoleInfo(MngAutRoleInfoDO condition) {
		if(condition == null){
            condition = new MngAutRoleInfoDO();
		}
		List<MngAutRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.queryMngAutRoleInfo",condition);
		return list;
	}


	public MngAutRoleInfoDO querySingleMngAutRoleInfo(MngAutRoleInfoDO condition){
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
		List<MngAutRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.querySingleMngAutRoleInfo",condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}



	public List<MngAutRoleInfoDO> queryMngAutRoleInfoByPage(Query<MngAutRoleInfoDO> query) {
		if(query == null){
			throw new IllegalArgumentException("query is null.");
		}
		if(query.getModule()==null){
        	query.setModule(new MngAutRoleInfoDO());
		}
		Map<String, Object> params = convertQuery2Param(query);
		int count = countMngAutRoleInfo(query.getModule());
		query.setTotalSize(count);
        if(count <= 0){
            return Collections.emptyList();
        }

		List<MngAutRoleInfoDO> list = sqlSession.selectList("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.queryMngAutRoleInfoByPage",params);

		return list;
	}


    public int countMngAutRoleInfo(MngAutRoleInfoDO condition) {
		if(condition == null){
			throw new IllegalArgumentException("condition is null.");
		}
        Integer count = (Integer)sqlSession.selectOne("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.countMngAutRoleInfo",condition);

        return count==null ? -1 : count;
    }





	public int updateMngAutRoleInfoByPk(MngAutRoleInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
		if(instance.getId()==null){
			throw new IllegalArgumentException("instance.getId is null.");
		}


		return  sqlSession.update("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.updateMngAutRoleInfoByPk",instance);
	}

    public int updateMngAutRoleInfoByCondition(MngAutRoleInfoDO instance,MngAutRoleInfoDO condition){
        if(instance == null){
            throw new IllegalArgumentException("instance is null.");
        }
        if(condition == null){
            throw new IllegalArgumentException("condition is null.");
        }

        Map<String,Object> params = new HashMap<String, Object>(2,1.0f);
        params.put("instance",instance);
        params.put("condition",condition);

        return sqlSession.update("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.updateMngAutRoleInfoByCondition",params);


    }

	public int insertMngAutRoleInfo(MngAutRoleInfoDO instance) {
		if(instance == null){
			throw new IllegalArgumentException("instance is null.");
		}
        int cnt= sqlSession.insert("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.insertMngAutRoleInfo",instance);
		return cnt;
	}


    public void insertBatchMngAutRoleInfo(List<MngAutRoleInfoDO> list) throws Exception{
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list is null or empty.");
        }
        SqlSession batchSqlSession = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        try {
            for (MngAutRoleInfoDO instance : list) {
                 batchSqlSession.insert("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.insertMngAutRoleInfo",instance);
            }
            batchSqlSession.commit();
        } catch(Exception e) {
            batchSqlSession.rollback();
            throw e;
        } finally {
            batchSqlSession.close();
        }
    }


	public MngAutRoleInfoDO queryMngAutRoleInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
		MngAutRoleInfoDO condition = new MngAutRoleInfoDO();
		condition.setId(id);
		List<MngAutRoleInfoDO> list = queryMngAutRoleInfo(condition);

		return (list==null||list.size()==0) ? null : list.get(0);
	}





    public int deleteByCondition(MngAutRoleInfoDO condition){
        if(condition == null){
			throw new IllegalArgumentException("condition is null.");
        }
        return  sqlSession.delete("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.deleteByCondition",condition);
    }


    public boolean deleteMngAutRoleInfoById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("id is null.");
		}
        MngAutRoleInfoDO condition = new MngAutRoleInfoDO();
        condition.setId(id);
        int cnt = deleteByCondition(condition);
        return cnt>0;
    }


	private Map<String, Object> convertQuery2Param(Query<MngAutRoleInfoDO> query){
		MngAutRoleInfoDO instance = query.getModule();
        if(instance == null ){
            instance = new MngAutRoleInfoDO();
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
	public int disableAllAutOfRole(String ids) {
		if(ids == null){
			throw new IllegalArgumentException("condition is null.");
		}
		return  sqlSession.update("com.quhuwai.common.author.domain.MngAutRoleInfoMapper.disableAllAutOfRole", ids);

	}
}
