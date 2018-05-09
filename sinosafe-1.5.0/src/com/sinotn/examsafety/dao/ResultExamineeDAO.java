package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.examsafety.po.ResultExaminee;

public class ResultExamineeDAO extends BaseDAO<ResultExaminee, Long> {

	public ResultExaminee findResultExaminee(String subject,String licence,boolean isException){
		String[] propertyNames = {"subject","examinee.licence","isException"};
		Object[] values = {subject,licence,isException};
		List<ResultExaminee> resultExamineeList = this.findByPropertyArry(propertyNames, values);
		if(resultExamineeList.isEmpty()){
			return null;
		}
		else{
			return resultExamineeList.get(0);
		}
	}
	
	public List<?> findQRoomByPlace(String subject,long examPlaceId){
		String hql = "select t.exam_Room room,sum(case when t.is_Process = 0 then 1 else 0 end) total from Result_Examinee t "
				+ "where t.subject = ? and t.exam_Place = ?  and t.is_Exception = ? "
				+ "group by t.exam_Room order by t.exam_Room";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(hql);
		query.setString(0, subject);
		query.setLong(1, examPlaceId);
		query.setBoolean(2, false);
		query.addScalar("room", new org.hibernate.type.IntegerType());
		query.addScalar("total", new org.hibernate.type.LongType());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<ResultExaminee> findQResultExamineeRpt(String subject,long examPlaceId,
			int examRoom) {
		String hql = "from ResultExaminee t "
				+ "where t.subject = ? and t.examPlace = ? and t.examRoom =? and t.isProcess = ? and t.isException = ? "
				+ "order by t.seatNumber";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, subject);
		query.setLong(1, examPlaceId);
		query.setInteger(2, examRoom);
		query.setBoolean(3, false);
		query.setBoolean(4, false);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ResultExaminee> findQResultExaminee(String subject,long examPlaceId,int beginRoom,
			int endRoom) {
		String hql = "from ResultExaminee t "
				+ "where t.subject = ? and t.examPlace = ? and t.examRoom >=? and t.examRoom <= ? and t.isProcess = ? and t.isException = ? "
				+ "order by t.examRoom,t.seatNumber";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, subject);
		query.setLong(1, examPlaceId);
		query.setInteger(2, beginRoom);
		query.setInteger(3, endRoom);
		query.setBoolean(4, false);
		query.setBoolean(5, false);
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
	public List<ResultExaminee> findNResultExaminee(String subject,long examPlaceId,
			int beginRoom, int endRoom) {
		String hql = "from ResultExaminee t "
				+ "where t.subject = ? and t.examPlace = ? and t.examRoom >=? and t.examRoom <=? and t.isProcess = ? and t.syspass = ? and t.isException = ? "
				+ "order by t.examRoom, t.seatNumber";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, subject);
		query.setLong(1, examPlaceId);
		query.setInteger(2, beginRoom);
		query.setInteger(3, endRoom);
		query.setBoolean(4, true);
		query.setInteger(5,1);
		query.setBoolean(6, false);
		return query.list();
	}

	public List<ResultExaminee> findResultExamineeList(String licence){
		List<ResultExaminee> resultExamineeList = this.findByProperty("examinee.licence", licence, "subject asc");
		return resultExamineeList;
	}
}
