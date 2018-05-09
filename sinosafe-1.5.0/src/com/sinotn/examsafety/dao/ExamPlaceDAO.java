package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.ExamPlace;

public class ExamPlaceDAO extends BaseDAO<ExamPlace, Long> {

	/**
	 * 根据省代码获得考区代码和名称集合
	 * @param examProvince
	 * @return
	 */
	public List<?> findExamAreaList(String examProvince){
		String hql = "select t.examArea,t.examAreaName from ExamPlace t "
				+ "where t.examProvince = ?  "
				+ "group by t.examArea,t.examAreaName "
				+ "order by t.examArea";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, examProvince);
		return query.list();
	}
	
	/**
	 * 根据考区代码和考点代码获得考点集合
	 */
	public List<ExamPlace> findExamPlaceList(String examArea, long examPlaceId){
		String hql = "FROM ExamPlace t "
				+ "WHERE t.examArea like ? "
				+ "AND t.examPlace like ? "
				+ "ORDER BY t.examPlace ASC";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, examArea+"%");
		query.setString(1, String.valueOf(examPlaceId)+"%");
		List list = query.list(); 
		return list;
	}
	
	/**
	 * 根据考区代码获得考点集合
	 */
	public List<ExamPlace> findExamPlaceList(String examArea){
		String hql = "FROM ExamPlace t "
				+ "WHERE t.examArea like ? "
				+ "ORDER BY t.examPlace ASC";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, examArea+"%");
		List list = query.list(); 
		return list;
	}
	/**
	 * 获得所有考点集合
	 * @return
	 */
	public List<ExamPlace> findExamPlaceList(){
		String hql = "from ExamPlace t";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	/**
	 * 根据考区代码和启用标志查询考点集合
	 * @param examArea
	 * @param i
	 * @return
	 */
	public List<ExamPlace> findExamPlaceList(String examArea, int i) {
		String hql = "FROM ExamPlace t "
				+ "WHERE t.examArea = ? "
				+ "AND t.isEnabled = ? "
				+ "ORDER BY t.examPlace ASC";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, examArea);
		query.setInteger(1, i);
		List list = query.list(); 
		return list;
	}
}
