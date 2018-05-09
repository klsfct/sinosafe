package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SocketExaminee5;


public class SocketExaminee5DAO extends BaseDAO<SocketExaminee5, Long> {

	/**
	 * 抓取流水未处理的数据
	 * @param recordNumber
	 * @return
	 */
	public List<SocketExaminee5> findSocketExaminee5List(int recordNumber){
		String hql = "FROM SocketExaminee5 T WHERE T.isProcess = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setBoolean(0, false);
		query.setFirstResult(0);
		query.setMaxResults(recordNumber);
		return query.list();
	}
}
