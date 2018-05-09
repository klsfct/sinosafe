package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SocketDeviceFace;

public class SocketDeviceFaceDAO extends BaseDAO<SocketDeviceFace, Long> {
	
	public List<SocketDeviceFace> findSocketDeviceFaceList(){
		String hql = "FROM SocketDeviceFace T WHERE T.isProcess = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setBoolean(0, false);
		return query.list();
	}
}
