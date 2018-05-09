package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sinotn.examsafety.po.ResultExaminee1;

public class ResultExaminee1DAO extends BaseDAO<ResultExaminee1, String> {

	/**
	 * 获得场次一考生对象
	 * @param licence
	 * @return
	 */
	public ResultExaminee1 getResultExaminee(String licence){
		return this.findObjectById(licence);
	}
	
	public List<?> findQRoomByPlace(String subject,long examPlaceId){
		String hql = "select t.exam_Room room,sum(case when t.is_Process = 0 then 1 else 0 end) total from Result_Examinee_" + subject + " t "
				+ "where t.exam_Place = ?  "
				+ "group by t.exam_Room order by t.exam_Room";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(hql);
		query.setLong(0, examPlaceId);
		query.addScalar("room", new org.hibernate.type.IntegerType());
		query.addScalar("total", new org.hibernate.type.LongType());
		return query.list();
	}
	public List<?> findQRoomByPlaceCbt(String subject,long examPlaceId){
		String hql = "select e.exam_Room_cbt roomId,e.exam_room_name_cbt room,sum(case when t.is_Process = 0 then 1 else 0 end) total from Result_Examinee_" + subject + " t,Examinee e "
				+ "where t.licence = e.licence and t.exam_Place = ? "
				+ "group by e.exam_Room_cbt,e.exam_room_name_cbt order by e.exam_Room_cbt";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(hql);
		query.setLong(0, examPlaceId);
		query.addScalar("roomId",new org.hibernate.type.LongType());
		query.addScalar("room", new org.hibernate.type.StringType());
		query.addScalar("total", new org.hibernate.type.LongType());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<ResultExaminee1> findQResultExamineeRpt(String subject,long examPlaceId,
			int examRoom) {
		String hql = "from ResultExaminee1 t "
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
	public List<ResultExaminee1> findQResultExaminee(String subject,long examPlaceId,int beginRoom,
			int endRoom) {
		String hql = "from ResultExaminee1 t "
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
	public List<ResultExaminee1> findQResultExamineeCbt(long examPlaceId,long examRoomCbt){
		String hql = "from ResultExaminee1 t "
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
	public List<?> findNRoomByPlace(String subject,long examPlaceId){
		String sql = "select t.exam_Room room,sum(case when t.is_Pass = 0 then 1 else 0 end) total from Result_Examinee_" + subject + " t "
				+ "where t.exam_Place = ? and t.is_process = ? "
				+ "group by t.exam_Room order by t.exam_Room";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setLong(0, examPlaceId);
		query.setBoolean(1, true);
		query.addScalar("room", new org.hibernate.type.IntegerType());
		query.addScalar("total", new org.hibernate.type.LongType());
		return query.list();
	}
	
	public List<?> findNRoomByPlaceCbt(String subject,long examPlaceId){
		String sql = "select e.exam_Room_cbt roomId,e.exam_room_name_cbt room,sum(case when t.is_Pass = 0 then 1 else 0 end) total from Result_Examinee_" + subject + " t,Examinee e "
				+ "where t.licence = e.licence and t.exam_Place = ? and t.is_process = ? "
				+ "group by e.exam_Room_cbt,e.exam_room_name_cbt order by e.exam_Room_cbt";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setLong(0, examPlaceId);
		query.setBoolean(1, true);
		query.addScalar("roomId",new org.hibernate.type.LongType());
		query.addScalar("room", new org.hibernate.type.StringType());
		query.addScalar("total", new org.hibernate.type.LongType());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ResultExaminee1> findNResultExaminee(String subject,long examPlaceId,
			int beginRoom, int endRoom) {
		String hql = "from ResultExaminee1 t "
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
	public List<ResultExaminee1> findNResultExamineeCbt(long examPlaceId,long roomCbtId) {
		String hql = "from ResultExaminee1 t "
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
	
	public List<ResultExaminee1> findResultExamineeList(String licence){
		List<ResultExaminee1> resultExamineeList = this.findByProperty("licence", licence, "subject asc");
		return resultExamineeList;
	}


	public List<?> getGroupVo() {
		String sql = "select count(*), "
				+ "sum(t.is_process) "
				+ "from Result_Examinee_1 t ";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		return query.list();
	}

	public List<?> findResultExamineeListBySubject(String subject) {
		String sql = "select t.licence, t.exam_Province, t.exam_Province_Name, "
				+ "t.exam_area, exam_area_name, t.exam_place, t.exam_place_name, t.exam_room, "
				+ "t.seat_number, t.examinee_name, t.identity, t.photo_path "
				+ "from Result_Examinee_" + subject + " t ";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		return query.list();
	}

	public List<?> sumDataBySubject(String subject) {
		String sql = "select t.subject,t.exam_place,count(*) "
				+ "from Result_Examinee_" + subject + " t group by t.exam_place,t.subject order by t.exam_place asc";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		return query.list();
	}

	


	
}
