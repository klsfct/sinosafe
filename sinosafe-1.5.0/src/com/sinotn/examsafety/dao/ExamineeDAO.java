package com.sinotn.examsafety.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.Examinee;

public class ExamineeDAO extends BaseDAO<Examinee, String> {

	public List<?> findExamRoomList(long examPlaceId) {
		String hql = "select t.examRoom,count(*) from Examinee t "
				+ "where t.examPlace = ? and t.character = '01' group by t.examRoom order by t.examRoom";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		return query.list();
	}

	public List<?> findExamCardPlaceList(String examArea) {
		String hql = "select t.examPlace,t.examPlaceName from Examinee t "
				+ "where t.examArea = ? and t.character = '01' "
				+ "group by t.examPlace,t.examPlaceName order by t.examPlace";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, examArea);
		return query.list();
	}

	public List<?> findExamineePlaceList(String examArea) {
		String hql = "select t.examPlace,t.examPlaceName from Examinee t "
				+ "where t.examArea = ? "
				+ "group by t.examPlace,t.examPlaceName order by t.examPlace";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, examArea);
		return query.list();
	}

	public List<Examinee> findExaminee(long examPlaceId) {
		String hql = "from Examinee t " + "where t.examPlace = ? ";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		return query.list();
	}

	/**
	 * 
	 * TODO 新增按科目导出
	 * 
	 * @param examPlaceId
	 * @param subject
	 * @return
	 * @auth <a href="mailto:15662327016@126.com">Libin</a>
	 * @date 2017年12月11日 上午11:01:21
	 */
	public List<Examinee> findExaminee(long examPlaceId, String subject) {
		String subject1 = "1";
		String subject2 = "2";
		String subject3 = "3";
		String subject4 = "4";
		String subject5 = "5";
		String subject6 = "6";
		String subjectTableName = "";

		if (subject1.equals(subject)) {
			subjectTableName = "ResultExaminee1";
		} else if (subject2.equals(subject)) {
			subjectTableName = "ResultExaminee2";
		} else if (subject3.equals(subject)) {
			subjectTableName = "ResultExaminee3";
		} else if (subject4.equals(subject)) {
			subjectTableName = "ResultExaminee4";
		} else if (subject5.equals(subject)) {
			subjectTableName = "ResultExaminee5";
		} else if (subject6.equals(subject)) {
			subjectTableName = "ResultExaminee6";
		} else {
			return null;
		}

		String hql = "select t.licence,t.identity,"
				+ "t.examineeName,t.examRoom,t.seatNumber " + "from "
				+ subjectTableName + " t " + "where t.examPlace = ? ";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		List<Examinee> list = new ArrayList<Examinee>();
		Examinee examinee = null;
		List<?> aaList = query.list();
		for (Object temp : aaList) {
			Object[] value = (Object[]) temp;
			examinee = new Examinee();
			examinee.setLicence((String) value[0]);
			examinee.setIdentity((String) value[1]);
			examinee.setExamineeName((String) value[2]);
			examinee.setExamRoom((Long) value[3]);
			examinee.setSeatNumber((Byte) value[4]);
			list.add(examinee);

		}
		return list;
	}

	public long findStandRoomExaminees(long examPlaceId, int examRoom) {
		String hql = "select count(*) from Examinee t where t.examPlace = ? and t.examRoom = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		query.setInteger(1, examRoom);
		List list = query.list();
		return (Long) list.get(0);
	}

	public List<?> findListByHql(String hql) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);// "from Examinee  where licence = '1645010100001'"
		List list = query.list();
		return list;
	}

	public List<?> findExamineeListBySubject(BigDecimal subject) {
		String subjectStr = subject.toString();
		String subject1 = "1";
		String subject2 = "2";
		String subject3 = "3";
		String subject4 = "4";
		String subject5 = "5";
		String subject6 = "6";
		String subjectTableName = "";

		if (subject1.equals(subjectStr)) {
			subjectTableName = "ResultExaminee1";
		} else if (subject2.equals(subjectStr)) {
			subjectTableName = "ResultExaminee2";
		} else if (subject3.equals(subjectStr)) {
			subjectTableName = "ResultExaminee3";
		} else if (subject4.equals(subjectStr)) {
			subjectTableName = "ResultExaminee4";
		} else if (subject5.equals(subjectStr)) {
			subjectTableName = "ResultExaminee5";
		} else if (subject6.equals(subjectStr)) {
			subjectTableName = "ResultExaminee6";
		} else {
			return null;
		}


		String hql = "select t.examineeName,t.identity,t.licence,"
				+ "t.examPlaceName,t.examRoom,t.seatNumber,t.examPlace " + "from "
				+ subjectTableName + " t ";

		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

	public List<?> findExamineePlaceList(String examArea, Long examPlaceId) {
		String hql = "select t.examPlace,t.examPlaceName from Examinee t "
				+ "where t.examArea = ? and t.examPlace = ? "
				+ "group by t.examPlace,t.examPlaceName order by t.examPlace";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, examArea);
		query.setLong(1, examPlaceId);
		return query.list();
	}

	public List<?> findExamineeListBySubject(BigDecimal subject, Long examPlaceId) {
		String subjectStr = subject.toString();
		String subject1 = "1";
		String subject2 = "2";
		String subject3 = "3";
		String subject4 = "4";
		String subject5 = "5";
		String subject6 = "6";
		String subjectTableName = "";

		if (subject1.equals(subjectStr)) {
			subjectTableName = "ResultExaminee1";
		} else if (subject2.equals(subjectStr)) {
			subjectTableName = "ResultExaminee2";
		} else if (subject3.equals(subjectStr)) {
			subjectTableName = "ResultExaminee3";
		} else if (subject4.equals(subjectStr)) {
			subjectTableName = "ResultExaminee4";
		} else if (subject5.equals(subjectStr)) {
			subjectTableName = "ResultExaminee5";
		} else if (subject6.equals(subjectStr)) {
			subjectTableName = "ResultExaminee6";
		} else {
			return null;
		}


		String hql = "select t.examineeName,t.identity,t.licence,"
				+ "t.examPlaceName,t.examRoom,t.seatNumber,t.examPlace " + "from "
				+ subjectTableName + " t where t.examPlace = ? ";
		
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		return query.list();
	}

}
