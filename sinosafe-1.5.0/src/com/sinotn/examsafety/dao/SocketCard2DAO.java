package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SocketCard2;

public class SocketCard2DAO extends BaseDAO<SocketCard2, Long> {
	public List<SocketCard2> findSocketCard2List(int recordNumber){
		String hql = "FROM SocketCard2 T WHERE T.isProcess = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setBoolean(0, false);
		query.setMaxResults(recordNumber);
		return query.list();
	}
}
