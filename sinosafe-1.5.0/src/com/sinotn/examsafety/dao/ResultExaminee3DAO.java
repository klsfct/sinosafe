package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.examsafety.po.ResultExaminee1;
import com.sinotn.examsafety.po.ResultExaminee2;
import com.sinotn.examsafety.po.ResultExaminee3;

public class ResultExaminee3DAO extends BaseDAO<ResultExaminee3, String> {

	/**
	 * 获得场次三考生对象
	 * 
	 * @param licence
	 * @return
	 */
	public ResultExaminee3 getResultExaminee(String licence) {
		return this.findObjectById(licence);
	}

	public List<?> findQRoomByPlace(String subject, long examPlaceId) {
		String hql = "select t.exam_Room room,sum(case when t.is_Process = 0 then 1 else 0 end) total from Result_Examinee_3 t "
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
	public List<ResultExaminee3> findQResultExamineeRpt(String subject,
			long examPlaceId, int examRoom) {
		String hql = "from ResultExaminee3 t "
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
	public List<ResultExaminee3> findQResultExaminee(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		String hql = "from ResultExaminee3 t "
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

	public List<ResultExaminee3> findQResultExamineeCbt(long examPlaceId,
			long examRoomCbt) {
		String hql = "from ResultExaminee3 t "
				+ "where t.examPlace = ? and t.isProcess = ? "
				+ "and t.licence in (select e.licence from Examinee e where e.examRoomCbt = ?) "
				+ "order by t.examRoom,t.seatNumber";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		query.setBoolean(1, false);
		query.setLong(2, examRoomCbt);
		return query.list();
	}

	public List<?> findNRoomByPlace(String subject, long examPlaceId) {
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
	public List<ResultExaminee3> findNResultExaminee(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		String hql = "from ResultExaminee3 t "
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

	@SuppressWarnings("unchecked")
	public List<ResultExaminee3> findNResultExamineeCbt(long examPlaceId,
			long roomCbtId) {
		String hql = "from ResultExaminee3 t "
				+ "where t.examPlace = ? and t.isProcess = ? and t.isPass = ? "
				+ "and t.licence in (select e.licence from Examinee e where e.examRoomCbt = ?)"
				+ "order by t.examRoom, t.seatNumber";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		query.setBoolean(1, true);
		query.setBoolean(2, false);
		query.setLong(3, roomCbtId);
		return query.list();
	}

	public List<ResultExaminee3> findResultExamineeList(String licence) {
		List<ResultExaminee3> resultExamineeList = this.findByProperty(
				"licence", licence, "subject asc");
		return resultExamineeList;
	}
}
