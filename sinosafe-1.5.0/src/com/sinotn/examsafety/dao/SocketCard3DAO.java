package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SocketCard3;

public class SocketCard3DAO extends BaseDAO<SocketCard3, Long> {
	public List<SocketCard3> findSocketCard3List(int recordNumber){
		String hql = "FROM SocketCard3 T WHERE T.isProcess = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setBoolean(0, false);
		query.setMaxResults(recordNumber);
		return query.list();
	}
}
