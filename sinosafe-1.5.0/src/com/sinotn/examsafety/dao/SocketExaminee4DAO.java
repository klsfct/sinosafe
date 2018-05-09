package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.common.exception.DaoException;
import com.sinotn.examsafety.po.SocketExaminee2;
import com.sinotn.examsafety.po.SocketExaminee4;


public class SocketExaminee4DAO extends BaseDAO<SocketExaminee4, Long> {

	/**
	 * 抓取流水未处理的数据
	 * @param recordNumber
	 * @return
	 */
	public List<SocketExaminee4> findSocketExaminee4List(int recordNumber){
		String hql = "FROM SocketExaminee4 T WHERE T.isProcess = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setBoolean(0, false);
		query.setFirstResult(0);
		query.setMaxResults(recordNumber);
		return query.list();
	}
}
