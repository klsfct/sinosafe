package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SocketDeviceHand;

public class SocketDeviceHandDAO extends BaseDAO<SocketDeviceHand, Long> {
	public List<SocketDeviceHand> findSocketDeviceHandList(int recordNumber ){
		String hql = "FROM SocketDeviceHand T WHERE T.isProcess = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setBoolean(0, false);
		query.setMaxResults(recordNumber);
		return query.list();
	}
}
