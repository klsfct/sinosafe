package com.sinotn.examsafety.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.Subject;
import com.sinotn.examsafety.vo.SubjectVo;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     AccessTokenDAO.java
 * @Description:   视频设备令牌DAO
 * 
 * @author:    李斌
 * @version:   V1.0  
 * @Date:      2017年7月14日 下午3:56:58
 */
public class SubjectDAO extends BaseDAO<Subject, BigDecimal> {

	public List<Subject> findSubjectList() {
		String hql = "from Subject t where t.isEnabled = 1 order by t.id asc";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
}
