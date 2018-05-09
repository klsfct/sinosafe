package com.sinotn.common.pagequery;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sinotn.common.exception.DaoException;

public class PageQueryDAO {
	SessionFactory sessionFactory = null;

	/**
	 * 分页查询公告DAO接口
	 * 
	 * @param 
	 * entity 实体对象
	 * condition 查询条件
	 * orderBy 排序条件
	 * beginIndex 起始点
	 * EveryPage 每页的数量
	 * 
	 * @return 分页查询数据列表
	 */
	public List<?> getResultByPage(final Page page) {
		String dynamicSql = "";
		if (!page.getCondition().trim().equals("")) {
			dynamicSql = " from " + page.getEntity() + " where "
					+ page.getCondition() + " " + page.getOrderBy();
		} else {
			dynamicSql = " from " + page.getEntity() + " " + page.getOrderBy();
		}
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(dynamicSql);
			query.setFirstResult(page.getBeginIndex());
			query.setMaxResults(page.getEveryPage());
			query.setCacheable(true);
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}

	}

	/**
	 * 根据分页信息获取查询的记录数
	 * 
	 * @param page
	 *            分页基础类
	 * @return 记录数
	 */
	public int getResultCount(Page page) {
		String dynamicSql = "select count(*) ";
		if (!page.getCondition().trim().equals("")) {
			dynamicSql = dynamicSql + " from " + page.getEntity() + " where "
					+ page.getCondition();
		} else {
			dynamicSql = dynamicSql + " from " + page.getEntity();
		}
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(dynamicSql);
			query.setCacheable(true);
			return Integer.valueOf(query.list().get(0).toString());
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 获取全部数据信息，不进行分页查询
	 * 
	 * @param page
	 *            分页基础类
	 * @return 全部数据类表
	 */
	public List<?> getResults(Page page) {
		String dynamicSql = "";
		if (!page.getCondition().trim().equals("")) {
			dynamicSql = " from " + page.getEntity() + " where "
					+ page.getCondition() + " " + page.getOrderBy();
		} else {
			dynamicSql = " from " + page.getEntity() + " " + page.getOrderBy();
		}
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(dynamicSql);
			query.setCacheable(true);
			return query.list();
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new DaoException(he.getMessage());
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
