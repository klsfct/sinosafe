package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SocketExaminee6;


public class SocketExaminee6DAO extends BaseDAO<SocketExaminee6, Long> {

	/**
	 * 抓取流水未处理的数据
	 * @param recordNumber
	 * @return
	 */
	public List<SocketExaminee6> findSocketExaminee6List(int recordNumber){
		String hql = "FROM SocketExaminee6 T WHERE T.isProcess = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setBoolean(0, false);
		query.setFirstResult(0);
		query.setMaxResults(recordNumber);
		return query.list();
	}
}
