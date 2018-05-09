package com.sinotn.examsafety.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.common.exception.DaoException;
import com.sinotn.examsafety.po.ResultCard;

public class ResultCardDAO extends BaseDAO<ResultCard, Long> {

	/**
	 * 根据科目和题卡编号获得指定对象
	 * @param subject
	 * @param examCard
	 * @return
	 */
	public ResultCard findResultCard(Long examPlace,String subject,String examCard){
		String hql = "from ResultCard t where t.examPlace = ? and t.subject = ? and t.examCard = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlace);
		query.setString(1, subject);
		query.setString(2, examCard);
		@SuppressWarnings("unchecked")
		List<ResultCard> list = query.list();
		if(list.isEmpty() ){
			return null;
		}
		else{
			return list.get(0);
		}
	}
	
	public int deleteCard(long examPlaceId,int examRoom,String subject){
		String hql = "delete from ResultCard t where t.examPlace = ? and t.examRoom = ? and t.subject = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		query.setInteger(1, examRoom);
		query.setString(2, subject);
		return query.executeUpdate();
	}
	
	public Date findResultDate(long examPlaceId,int examRoom,String subject){
		String hql = "select t.scanDate from ResultCard t where t.examPlace = ? and t.examRoom = ? and t.subject = ? group by t.scanDate";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlaceId);
		query.setInteger(1, examRoom);
		query.setString(2, subject);
		List<?> list = query.list();
		if(list.isEmpty()){
			return null;
		}
		else{
			Object temp = list.get(0);
			return (Date)temp;
		}
	}
	
	/**
	 * 按考场分组统计题卡数量
	 * @param examPlaceId
	 * @param subject
	 * @return
	 */
	public List<?> groupCardByExamRoom(long examPlaceId,String subject){
		String sql = "select P.EXAM_PLACE id,P.EXAM_PLACE_NAME name,R.EXAM_ROOM room,COUNT(*) real "
				+ "FROM RESULT_CARD R ,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE = P.EXAM_PLACE AND R.EXAM_PLACE = ? AND R.SUBJECT = ? "
				+ "GROUP BY P.EXAM_PLACE,P.EXAM_PLACE_NAME,R.EXAM_ROOM "
				+ "ORDER BY R.EXAM_ROOM";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setLong(0, examPlaceId);
			query.setString(1,subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("room", new org.hibernate.type.IntegerType());
			query.addScalar("real", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}
	
	public long findCardsByRoom(String subject,long examPlaceId,int examRoom){
		String hql = "select count(*) from ResultCard t where t.subject = ? and t.examPlace = ? and t.examRoom = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, subject);
		query.setLong(1,examPlaceId);
		query.setInteger(2,examRoom);
		List list = query.list();
		return (Long)list.get(0);
	}
}
