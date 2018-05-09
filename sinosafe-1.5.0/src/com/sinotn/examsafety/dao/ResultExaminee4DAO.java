package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.examsafety.po.ResultExaminee4;

public class ResultExaminee4DAO extends BaseDAO<ResultExaminee4, String> {

	/**
	 * 获得试卷四考生对象
	 * @param licence
	 * @return
	 */
	public ResultExaminee4 getResultExaminee(String licence){
		return this.findObjectById(licence);
	}
	
	public List<?> findQRoomByPlace(String subject,long examPlaceId){
		String hql = "select t.exam_Room room,sum(case when t.is_Process = 0 then 1 else 0 end) total from Result_Examinee_4 t "
				+ "where t.exam_Place = ?  "
				+ "group by t.exam_Room order by t.exam_Room";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(hql);
		query.setLong(0, examPlaceId);
		query.addScalar("room", new org.hibernate.type.IntegerType());
		query.addScalar("total", new org.hibernate.type.LongType());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<ResultExaminee4> findQResultExamineeRpt(String subject,long examPlaceId,
			int examRoom) {
		String hql = "from ResultExaminee4 t "
				+ "where t.examPlace = ? and t.examRoom =? and t.isProcess = ? "
				+ "order by t.seatNumber";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		query.setInteger(1, examRoom);
		query.setBoolean(2, false);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ResultExaminee4> findQResultExaminee(String subject,long examPlaceId,int beginRoom,
			int endRoom) {
		String hql = "from ResultExaminee4 t "
				+ "where t.examPlace = ? and t.examRoom >=? and t.examRoom <= ? and t.isProcess = ? "
				+ "order by t.examRoom,t.seatNumber";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		query.setInteger(1, beginRoom);
		query.setInteger(2, endRoom);
		query.setBoolean(3, false);
		return query.list();
	}
	
	public List<?> findNRoomByPlace(String subject,long examPlaceId){
		String sql = "select t.exam_Room room,sum(case when t.syspass = 1 then 1 else 0 end) total from Result_Examinee t "
				+ "where t.subject = ? and t.exam_Place = ? and t.is_Exception = ? "
				+ "group by t.exam_Room order by t.exam_Room";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, subject);
		query.setLong(1, examPlaceId);
		query.setBoolean(2, false);
		query.addScalar("room", new org.hibernate.type.IntegerType());
		query.addScalar("total", new org.hibernate.type.LongType());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ResultExaminee4> findNResultExaminee(String subject,long examPlaceId,
			int beginRoom, int endRoom) {
		String hql = "from ResultExaminee4 t "
				+ "where t.examPlace = ? and t.examRoom >=? and t.examRoom <=? and t.isProcess = ? and t.isPass = ? "
				+ "order by t.examRoom, t.seatNumber";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		query.setInteger(1, beginRoom);
		query.setInteger(2, endRoom);
		query.setBoolean(3, true);
		query.setBoolean(4, false);
		return query.list();
	}

	public List<ResultExaminee4> findResultExamineeList(String licence){
		List<ResultExaminee4> resultExamineeList = this.findByProperty("licence", licence, "subject asc");
		return resultExamineeList;
	}
}
