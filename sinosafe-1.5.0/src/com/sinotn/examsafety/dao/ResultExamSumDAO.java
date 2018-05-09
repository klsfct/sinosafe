package com.sinotn.examsafety.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.common.exception.DaoException;
import com.sinotn.common.utils.StringUtils;
import com.sinotn.examsafety.po.ResultExamSum;

public class ResultExamSumDAO extends BaseDAO<ResultExamSum, Long> {

	public ResultExamSum findResultExamSum(String subject, Long examPlace) {
		String hql = "from ResultExamSum t where t.subject = ? and t.examPlace.examPlace = ?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, subject);
		query.setLong(1, examPlace);
		@SuppressWarnings("unchecked")
		List<ResultExamSum> list = query.list();
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 按照省地区分组统计扫描提卡统计信息
	 * 
	 * @param areaId
	 * @return
	 */
	public List<?> groupCardByProvinceArea(String areaId, String subject) {
		String sql = "select P.EXAM_PROVINCE id,P.EXAM_PROVINCE_NAME name,SUM(R.TOTAL) total,SUM(R.CARD_SUM) real "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_PROVINCE LIKE ? AND R.SUBJECT = ? "
				+ "GROUP BY P.EXAM_PROVINCE,P.EXAM_PROVINCE_NAME ORDER BY P.EXAM_PROVINCE ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("real", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 按照考区分组统计扫描提卡统计信息
	 * 
	 * @param areaId
	 * @return
	 */
	public List<?> groupCardByExamArea(String areaId, String subject) {
		String sql = "select P.EXAM_AREA id,P.EXAM_AREA_NAME name,SUM(R.TOTAL) total,SUM(R.CARD_SUM) real "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_PROVINCE = ? AND R.SUBJECT = ? "
				+ "GROUP BY P.EXAM_AREA,P.EXAM_AREA_NAME ORDER BY P.EXAM_AREA ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("real", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 按照考区分组统计扫描提卡信息
	 * 
	 * @param areaId
	 * @return
	 */
	public List<?> groupCardByExamPlace(String areaId, String subject) {
		String sql = "select P.EXAM_PLACE id,P.EXAM_PLACE_NAME name,SUM(R.TOTAL) total,SUM(R.CARD_SUM) real "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_AREA LIKE ? AND R.SUBJECT = ? "
				+ "GROUP BY P.EXAM_PLACE,P.EXAM_PLACE_NAME ORDER BY P.EXAM_PLACE ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("real", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public List<?> groupPassByProvinceArea(String areaId, String subject) {
		String sql = "select P.EXAM_PROVINCE id,P.EXAM_PROVINCE_NAME name,SUM(R.TOTAL) total,SUM(R.PASS_SUM) pass,SUM(R.PASS_NORMAL_SUM) psssNormal,"
				+ "SUM(R.PASS_SPECIAL_SUM) passSpecial,SUM(R.PASS_EXIT_SUM) exit "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_PROVINCE LIKE ? AND R.SUBJECT = ?  AND IS_ENABLED = 1 "
				+ "GROUP BY P.EXAM_PROVINCE,P.EXAM_PROVINCE_NAME ORDER BY P.EXAM_PROVINCE ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass", new org.hibernate.type.IntegerType());
			query.addScalar("psssNormal", new org.hibernate.type.IntegerType());
			query.addScalar("passSpecial", new org.hibernate.type.IntegerType());
			query.addScalar("exit", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public List<?> groupPassByExamArea(String areaId, String subject) {
		String sql = "select P.EXAM_AREA id,P.EXAM_AREA_NAME name,SUM(R.TOTAL) total,SUM(R.PASS_SUM) pass,"
				+ "SUM(R.PASS_NORMAL_SUM) psssNormal,SUM(R.PASS_SPECIAL_SUM) passSpecial,SUM(R.PASS_EXIT_SUM) exit "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_PROVINCE = ? AND R.SUBJECT = ?  AND IS_ENABLED = 1 "
				+ "GROUP BY P.EXAM_AREA,P.EXAM_AREA_NAME ORDER BY P.EXAM_AREA ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass", new org.hibernate.type.IntegerType());
			query.addScalar("psssNormal", new org.hibernate.type.IntegerType());
			query.addScalar("passSpecial", new org.hibernate.type.IntegerType());
			query.addScalar("exit", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public List<?> groupPassByExamPlace(String areaId, String subject) {
		String sql = "select P.EXAM_PLACE id,P.EXAM_PLACE_NAME name,SUM(R.TOTAL) total,SUM(R.PASS_SUM) pass,"
				+ "SUM(R.PASS_NORMAL_SUM) psssNormal,SUM(R.PASS_SPECIAL_SUM) passSpecial,SUM(R.PASS_EXIT_SUM) exit "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_AREA like ? AND R.SUBJECT = ? AND IS_ENABLED = 1 "
				+ "GROUP BY P.EXAM_PLACE,P.EXAM_PLACE_NAME ORDER BY P.EXAM_PLACE ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass", new org.hibernate.type.IntegerType());
			query.addScalar("psssNormal", new org.hibernate.type.IntegerType());
			query.addScalar("passSpecial", new org.hibernate.type.IntegerType());
			query.addScalar("exit", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public List<?> groupFaceByProvinceArea(String areaId, String subject) {
		String sql = "select P.EXAM_PROVINCE id,P.EXAM_PROVINCE_NAME name,SUM(R.TOTAL) total,SUM(R.PASS_SUM) pass,SUM(R.PHOTO_SUM_Y) py,"
				+ "SUM(R.PHOTO_SUM_N) pn,SUM(R.PHOTO_SUM_NN) pnn,SUM(R.PHOTO_SUM_NY) pny "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_PROVINCE LIKE ? AND R.SUBJECT = ?  AND IS_ENABLED = 1 "
				+ "GROUP BY P.EXAM_PROVINCE,P.EXAM_PROVINCE_NAME ORDER BY P.EXAM_PROVINCE ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass", new org.hibernate.type.IntegerType());
			query.addScalar("py", new org.hibernate.type.IntegerType());
			query.addScalar("pn", new org.hibernate.type.IntegerType());
			query.addScalar("pnn", new org.hibernate.type.IntegerType());
			query.addScalar("pny", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public List<?> groupFaceByExamArea(String areaId, String subject) {
		String sql = "select P.EXAM_AREA id,P.EXAM_AREA_NAME name,SUM(R.TOTAL) total,SUM(R.PASS_SUM) pass,SUM(R.PHOTO_SUM_Y) py,"
				+ "SUM(R.PHOTO_SUM_N) pn,SUM(R.PHOTO_SUM_NN) pnn,SUM(R.PHOTO_SUM_NY) pny "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_PROVINCE = ? AND R.SUBJECT = ?  AND IS_ENABLED = 1 "
				+ "GROUP BY P.EXAM_AREA,P.EXAM_AREA_NAME ORDER BY P.EXAM_AREA ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass", new org.hibernate.type.IntegerType());
			query.addScalar("py", new org.hibernate.type.IntegerType());
			query.addScalar("pn", new org.hibernate.type.IntegerType());
			query.addScalar("pnn", new org.hibernate.type.IntegerType());
			query.addScalar("pny", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public List<?> groupFaceByExamPlace(String areaId, String subject) {
		String sql = "select P.EXAM_PLACE id,P.EXAM_PLACE_NAME name,SUM(R.TOTAL) total,SUM(R.PASS_SUM) pass,SUM(R.PHOTO_SUM_Y) py,"
				+ "SUM(R.PHOTO_SUM_N) pn,SUM(R.PHOTO_SUM_NN) pnn,SUM(R.PHOTO_SUM_NY) pny "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_AREA like ? AND R.SUBJECT = ? AND IS_ENABLED = 1 "
				+ "GROUP BY P.EXAM_PLACE,P.EXAM_PLACE_NAME ORDER BY P.EXAM_PLACE ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass", new org.hibernate.type.IntegerType());
			query.addScalar("py", new org.hibernate.type.IntegerType());
			query.addScalar("pn", new org.hibernate.type.IntegerType());
			query.addScalar("pnn", new org.hibernate.type.IntegerType());
			query.addScalar("pny", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 全国监控界面
	 * 
	 * @param subject
	 * @return
	 */
	public List<?> monitorByArea(String subject) {
		String sql = "select EP.EXAM_PROVINCE areaId,SUM(RS.TOTAL) total,"
				+ "SUM(RS.PASS_SUM) pass_sum,"
				+ "ROUND(SUM(RS.PASS_SUM)*100/SUM(RS.TOTAL),1) percent1,"
				+ "SUM(RS.PHOTO_SUM_Y) photoSumY,"
				+ "DECODE(SUM(RS.PASS_SUM),0,0,ROUND(SUM(RS.PHOTO_SUM_Y)*100/SUM(RS.PASS_SUM),1)) percent2 "
				+ "from RESULT_EXAM_SUM RS,EXAM_PLACE EP "
				+ "WHERE RS.EXAM_PLACE_ID = EP.EXAM_PLACE AND"
				+ " RS.SUBJECT = ? AND EP.IS_ENABLED = 1 "
				+ "GROUP BY EP.EXAM_PROVINCE ORDER BY percent1 ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, subject);
			query.addScalar("areaId", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass_sum", new org.hibernate.type.IntegerType());
			query.addScalar("percent1", new org.hibernate.type.BigDecimalType());
			query.addScalar("photoSumY", new org.hibernate.type.IntegerType());
			query.addScalar("percent2", new org.hibernate.type.BigDecimalType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 省监控界面
	 * 
	 * @param subject
	 * @return
	 */
	public List<?> monitorByProvince(String areaId, String subject) {
		String sql = "select EP.EXAM_AREA areaId,"
				+ "SUM(RS.TOTAL) total,"
				+ "SUM(RS.PASS_SUM) pass_sum,"
				+ "ROUND(SUM(RS.PASS_SUM)*100/SUM(RS.TOTAL),1) percent1,"
				+ "SUM(RS.PHOTO_SUM_Y) photoSumY,"
				+ "DECODE(SUM(RS.PASS_SUM),0,0,ROUND(SUM(RS.PHOTO_SUM_Y)*100/SUM(RS.PASS_SUM),1)) percent2 "
				+ "from RESULT_EXAM_SUM RS,EXAM_PLACE EP "
				+ "WHERE RS.EXAM_PLACE_ID = EP.EXAM_PLACE "
				+ "AND EP.EXAM_PROVINCE = ? " + "AND RS.SUBJECT = ? "
				+ "AND EP.IS_ENABLED = 1 "
				+ "GROUP BY EP.EXAM_AREA  ORDER BY percent1 ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("areaId", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass_sum", new org.hibernate.type.IntegerType());
			query.addScalar("percent1", new org.hibernate.type.BigDecimalType());
			query.addScalar("photoSumY", new org.hibernate.type.IntegerType());
			query.addScalar("percent2", new org.hibernate.type.BigDecimalType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 考区监控
	 * 
	 * @param areaId
	 * @param subject
	 * @return
	 */
	public List<?> monitorByPlace(String areaId, String subject) {
		String sql = "select t.exam_area areaId,"
				+ "t.exam_place placeId,"
				+ "t.exam_place_name placeName,"
				+ "t.imei_no imeiNo,"
				+ "count(*) total,"
				+ "sum(decode(t.IS_PASS,0,1,0)) passN,"
				+ "sum(decode(t.IS_PASS,1,1,0)) passY "
				+ "from result_examinee_" + subject + " t "
				+ "where t.exam_area like ? "
				+ "and t.is_process = 1 "
				+ "group by t.exam_area,t.exam_place,t.exam_place_name,t.imei_no "
				+ "order by t.exam_area,t.exam_place,t.exam_place_name,t.imei_no";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.addScalar("placeName", new org.hibernate.type.StringType());
			query.addScalar("imeiNo", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("passN", new org.hibernate.type.IntegerType());
			query.addScalar("passY", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}
	
	/**
	 * 上海地图人脸监控监控界面
	 * 
	 * @param subject
	 * @return
	 */
	public List<?> ShMonitorByCity(String areaId, String subject) {
		String sql = "select EP.EXAM_AREA areaId," //0
				+ "EP.EXAM_PLACE examPlace,"//1
				+ "EP.EXAM_PLACE_NAME examPlaceName,"//2
				+ "ep.point_lng pointLng,"//3
				+ "ep.point_lat pointLat,"//4
				+ "SUM(RS.TOTAL) total,"//5报名人数
				+ "SUM(RS.PASS_SUM) pass_sum," //6 参考人数
				+ "ROUND(SUM(RS.PASS_SUM)*100/SUM(RS.TOTAL),1) percent1," //7参考率
				+ "SUM(RS.PHOTO_SUM_Y) photoSumY,"//8通过人数
				+ "DECODE(SUM(RS.PASS_SUM),0,0,ROUND(SUM(RS.PHOTO_SUM_Y)*100/SUM(RS.PASS_SUM),1)) percent2 , " //通过率
				+ "ep.LINK_MAN_NAME linkName, " //联系人
				+ "ep.LINK_MAN_PHONE linkPhone " //联系方式
				+ "from RESULT_EXAM_SUM RS,EXAM_PLACE EP "
				+ "WHERE RS.EXAM_PLACE_ID = EP.EXAM_PLACE "
				+ "AND EP.Exam_Area like ? " + "AND RS.SUBJECT = ? "
				+ "AND EP.IS_ENABLED = 1 "
				+ "GROUP BY EP.EXAM_AREA,EP.EXAM_PLACE,EP.EXAM_PLACE_NAME,ep.point_lng,ep.point_lat,ep.LINK_MAN_NAME,ep.LINK_MAN_PHONE ORDER BY percent1 desc ";
		
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId + "%");
			query.setString(1, subject);
			query.addScalar("areaId", new org.hibernate.type.StringType());
			query.addScalar("examPlace", new org.hibernate.type.StringType());
			query.addScalar("examPlaceName", new org.hibernate.type.StringType());
			query.addScalar("pointLng", new org.hibernate.type.StringType());
			query.addScalar("pointLat", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass_sum", new org.hibernate.type.IntegerType());
			query.addScalar("percent1", new org.hibernate.type.BigDecimalType());
			query.addScalar("photoSumY", new org.hibernate.type.IntegerType());
			query.addScalar("percent2", new org.hibernate.type.BigDecimalType());
			query.addScalar("linkName", new org.hibernate.type.StringType());
			query.addScalar("linkPhone", new org.hibernate.type.StringType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}
	
	/**
	 * 上海地图题卡监控监控界面
	 * 
	 * @param subject
	 * @return
	 */
	public List<?> ShMonitorCradByCity(String areaId, String subject) {
		String sql = "select EP.EXAM_AREA areaId,"//0
				+ "EP.EXAM_PLACE examPlace,"//1
				+ "EP.EXAM_PLACE_NAME examPlaceName,"//2
				+ "ep.point_lng pointLng,"//3
				+ "ep.point_lat pointLat,"//4
				+ "SUM(RS.TOTAL) total,"//5题卡总数
				+ "SUM(RS.CARD_SUM) cardSum,"//6题卡回收数
				+ "ROUND(SUM(RS.CARD_SUM)*100/SUM(RS.TOTAL),1) percent1, "
				+ "ep.LINK_MAN_NAME linkName, " //联系人
				+ "ep.LINK_MAN_PHONE linkPhone " //联系方式
				+ "from RESULT_EXAM_SUM RS,EXAM_PLACE EP "
				+ "WHERE RS.EXAM_PLACE_ID = EP.EXAM_PLACE "
				+ "AND EP.Exam_Area = ? " + "AND RS.SUBJECT = ? "
				+ "AND EP.IS_ENABLED = 1 "
				+ "GROUP BY EP.EXAM_AREA,EP.EXAM_PLACE,EP.EXAM_PLACE_NAME,ep.point_lng,ep.point_lat,ep.LINK_MAN_NAME,ep.LINK_MAN_PHONE ORDER BY percent1 desc ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, areaId);
			query.setString(1, subject);
			query.addScalar("areaId", new org.hibernate.type.StringType());
			query.addScalar("examPlace", new org.hibernate.type.StringType());
			query.addScalar("examPlaceName", new org.hibernate.type.StringType());
			query.addScalar("pointLng", new org.hibernate.type.StringType());
			query.addScalar("pointLat", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("cardSum", new org.hibernate.type.IntegerType());
			query.addScalar("percent1", new org.hibernate.type.BigDecimalType());
			query.addScalar("linkName", new org.hibernate.type.StringType());
			query.addScalar("linkPhone", new org.hibernate.type.StringType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public List<?> countSubjectByExamPlace(String paramPlaceId, String paramAreaId) {

		String sqlStr = "SELECT "
				+ "SUM(CASE WHEN RS.subject = '1' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '2' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '2' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '3' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '3' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' OR RS.subject ='2' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' OR RS.subject ='2' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' OR RS.subject ='3' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' OR RS.subject ='3' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '2' OR RS.subject ='3' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '2' OR RS.subject ='3' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' OR RS.subject ='2' OR RS.subject ='3' OR RS.subject ='4' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' OR RS.subject ='2' OR RS.subject ='3' OR RS.subject ='4' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '4' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '4' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' OR RS.subject ='4' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '1' OR RS.subject ='4' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '2' OR RS.subject ='4' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '2' OR RS.subject ='4' THEN RS.pass_sum ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '3' OR RS.subject ='4' THEN RS.total ELSE 0 END ),"
				+ "SUM(CASE WHEN RS.subject = '3' OR RS.subject ='4' THEN RS.pass_sum ELSE 0 END )"
				+ " FROM RESULT_EXAM_SUM RS  ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			if (!StringUtils.isEmpty(paramPlaceId)) {
				sqlStr += "WHERE RS.EXAM_PLACE_ID = ?";
				SQLQuery query = session.createSQLQuery(sqlStr);
				query.setString(0, paramPlaceId);
				return query.list();
			} 
			if (!StringUtils.isEmpty(paramAreaId)) {
				paramAreaId = (String) paramAreaId.subSequence(paramAreaId.indexOf("_")+1, paramAreaId.length());
				sqlStr += "WHERE RS.EXAM_PLACE_ID LIKE '"+ paramAreaId.substring(0, 2) +"%'";
				SQLQuery query = session.createSQLQuery(sqlStr);
				return query.list();
			}

		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
		return null;

	}

	public List<?> getCountByExamPlace(String paramPlaceId, String subject) {

		String sql = "select t.exam_area areaId,"
				+ "t.exam_place placeId,"
				+ "t.exam_place_name placeName,"
				+ "t.imei_no imeiNo,"
				+ "count(*) total,"
				+ "sum(decode(t.IS_PASS,0,1,0)) passN,"
				+ "sum(decode(t.IS_PASS,1,1,0)) passY "
				+ "from result_examinee_" + subject + " t "
				+ "where  t.is_process = 1 "
				+ "and t.exam_place = ? "
				+ "group by t.exam_area,t.exam_place,t.exam_place_name,t.imei_no "
				+ "order by t.exam_area,t.exam_place,t.exam_place_name,t.imei_no";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, paramPlaceId);
			query.addScalar("placeId", new org.hibernate.type.StringType());
			query.addScalar("placeName", new org.hibernate.type.StringType());
			query.addScalar("imeiNo", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("passN", new org.hibernate.type.IntegerType());
			query.addScalar("passY", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	
	}

	public List<?> getCountByExamArea(String paramAreaId, String subject) {


		String sql = "select t.exam_area areaId,"
				+ "t.exam_place placeId,"
				+ "t.exam_place_name placeName,"
				+ "t.imei_no imeiNo,"
				+ "count(*) total,"
				+ "sum(decode(t.IS_PASS,0,1,0)) passN,"
				+ "sum(decode(t.IS_PASS,1,1,0)) passY "
				+ "from result_examinee_" + subject + " t "
				+ "where  t.is_process = 1 "
				+ "and t.exam_area = ? "
				+ "group by t.exam_area,t.exam_place,t.exam_place_name,t.imei_no "
				+ "order by t.exam_area,t.exam_place,t.exam_place_name,t.imei_no";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, paramAreaId);
			query.addScalar("placeId", new org.hibernate.type.StringType());
			query.addScalar("placeName", new org.hibernate.type.StringType());
			query.addScalar("imeiNo", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("passN", new org.hibernate.type.IntegerType());
			query.addScalar("passY", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	
	
	}

	public List<?> getCountBySubject(String subject) {
		String sql = "select t.exam_area areaId,"
				+ "t.exam_place placeId,"
				+ "t.exam_place_name placeName,"
				+ "t.imei_no imeiNo,"
				+ "count(*) total,"
				+ "sum(decode(t.IS_PASS,0,1,0)) passN,"
				+ "sum(decode(t.IS_PASS,1,1,0)) passY "
				+ "from result_examinee_" + subject + " t "
				+ "where  t.is_process = 1 "
				+ "group by t.exam_area,t.exam_place,t.exam_place_name,t.imei_no "
				+ "order by t.exam_area,t.exam_place,t.exam_place_name,t.imei_no";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("placeId", new org.hibernate.type.StringType());
			query.addScalar("placeName", new org.hibernate.type.StringType());
			query.addScalar("imeiNo", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("passN", new org.hibernate.type.IntegerType());
			query.addScalar("passY", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	
	}

	public int countByExamPlace(String paramPlaceId, String subject) {
		String sql = "select "
				+ "count(*) total "
				+ "from result_examinee_" + subject + " t "
				+ "where t.exam_place = ?";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createSQLQuery(sql);
			query.setString(0, paramPlaceId);
			List<BigDecimal> list = query.list();
		    int count = list.get(0).intValue();
			return count;
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public int countByExamArea(String paramAreaId, String subject) {
		String sql = "select "
				+ "count(*) total "
				+ "from result_examinee_" + subject + " t "
				+ "where t.exam_area = ?";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, paramAreaId);
			query.addScalar("total", new org.hibernate.type.IntegerType());
			List<Integer> list = query.list();
		    int count = list.get(0);
			return count;
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public int countBySubject(String subject) {
		String sql = "select "
				+ "count(*) total "
				+ "from result_examinee_" + subject + " t ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("total", new org.hibernate.type.IntegerType());
			List<Integer> list = query.list();
		    int count = list.get(0);
			return count;
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	public List<?> groupFaceByExamPlace(long examPlaceId, String subject) {
		String sql = "select P.EXAM_PLACE id,P.EXAM_PLACE_NAME name,SUM(R.TOTAL) total,SUM(R.PASS_SUM) pass,SUM(R.PHOTO_SUM_Y) py,"
				+ "SUM(R.PHOTO_SUM_N) pn,SUM(R.PHOTO_SUM_NN) pnn,SUM(R.PHOTO_SUM_NY) pny "
				+ "from RESULT_EXAM_SUM R,EXAM_PLACE P "
				+ "WHERE R.EXAM_PLACE_ID = P.EXAM_PLACE AND P.EXAM_PLACE like ? AND R.SUBJECT = ? AND IS_ENABLED = 1 "
				+ "GROUP BY P.EXAM_PLACE,P.EXAM_PLACE_NAME ORDER BY P.EXAM_PLACE ";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setLong(0, examPlaceId);
			query.setString(1, subject);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			query.addScalar("pass", new org.hibernate.type.IntegerType());
			query.addScalar("py", new org.hibernate.type.IntegerType());
			query.addScalar("pn", new org.hibernate.type.IntegerType());
			query.addScalar("pnn", new org.hibernate.type.IntegerType());
			query.addScalar("pny", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}
}
