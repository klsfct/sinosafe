package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.common.exception.DaoException;
import com.sinotn.examsafety.po.ResultCard1;


public class ResultCard1DAO extends BaseDAO<ResultCard1, Long> {

	/**
	 * 根据科目和题卡编号获得指定对象
	 * @param subject
	 * @param examCard
	 * @return
	 */
	public ResultCard1 getResultCard(Long examPlace,Integer examRoom){
		String hql = "from ResultCard1 t where t.examPlace = ? and t.examRoom = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0, examPlace);
		query.setInteger(1, examRoom);
		@SuppressWarnings("unchecked")
		List<ResultCard1> list = query.list();
		if(list.isEmpty() ){
			return null;
		}
		else{
			return list.get(0);
		}
	}
	
	/**
	 * 按考场分组统计题卡数量
	 * @param examPlaceId
	 * @param subject
	 * @return
	 */
	public List<?> groupCardByExamRoom(long examPlaceId,String subject){
		String sql = "select P.EXAM_PLACE id,P.EXAM_PLACE_NAME name,R.EXAM_ROOM room,sum(r.exam_card) real "
				+ "FROM RESULT_CARD_" + subject +" R ,EXAM_PLACE P "
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
		String hql = "select count(*) from ResultCard_" + subject + " t where t.examPlace = ? and t.examRoom = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong(0,examPlaceId);
		query.setInteger(1,examRoom);
		List list = query.list();
		return (Long)list.get(0);
	}
}
