package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SocketCard1;

public class SocketCard1DAO extends BaseDAO<SocketCard1, Long> {
	public List<SocketCard1> findSocketCard1List(int recordNumber){
		String hql = "FROM SocketCard1 T WHERE T.isProcess = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setBoolean(0, false);
		query.setMaxResults(recordNumber);
		return query.list();
	}
}
