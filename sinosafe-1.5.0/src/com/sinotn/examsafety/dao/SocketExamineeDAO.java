package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.common.exception.DaoException;
import com.sinotn.examsafety.po.SocketExaminee;


public class SocketExamineeDAO extends BaseDAO<SocketExaminee, Long> {

	private String tempSql = "(select t.exam_province province,t.exam_province_name provinceName,t.exam_area examArea,t.exam_area_name examAreaName,t.exam_place place,t.exam_place_name placeName,t.imei_no imei,count(*) total from SOCKET_EXAMINEE t "
			+ "group by t.exam_province,t.exam_province_name,t.exam_area,t.exam_area_name,t.exam_place,t.exam_place_name,t.imei_no) temp ";
	
	
	/**
	 * 按照省地区分组统计已注册人脸识别设备信息
	 * @param areaId
	 * @return
	 */
	public List<?> groupFaceTestByProvinceArea(String areaId) {
		String sql = "select temp.province v1,temp.provinceName v2,count(*) v3,sum(total) v4 "
				+ "from  " + tempSql  + " where temp.province like ? group by temp.province,temp.provinceName " 
				+ "order by temp.province";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0,areaId);
			query.addScalar("v1", new org.hibernate.type.StringType());
			query.addScalar("v2", new org.hibernate.type.StringType());
			query.addScalar("v3", new org.hibernate.type.IntegerType());
			query.addScalar("v4", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}
	/**
	 * 按照考区分组统计已注册人脸识别设备信息
	 * @param areaId
	 * @return
	 */
	public List<?> groupFaceTestByExamArea(String areaId) {
		String sql = "select temp.examArea v1,temp.examAreaName v2,count(*) v3,sum(total) v4 "
				+ "from  " + tempSql  + " where temp.province = ? group by temp.examArea,temp.examAreaName " 
				+ "order by temp.examArea";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0,areaId);
			query.addScalar("v1", new org.hibernate.type.StringType());
			query.addScalar("v2", new org.hibernate.type.StringType());
			query.addScalar("v3", new org.hibernate.type.IntegerType());
			query.addScalar("v4", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 按照考区分组统计已注册人脸识别设备信息
	 * @param areaId
	 * @return
	 */
	public List<?> groupFaceTestByExamPlace(String areaId) {
		String sql = "select temp.place v1,temp.placeName v2,count(*) v3,sum(total) v4 "
				+ "from  " + tempSql  + " where temp.examArea like ? group by temp.place,temp.placeName " 
				+ "order by temp.place";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0,areaId);
			query.addScalar("v1", new org.hibernate.type.StringType());
			query.addScalar("v2", new org.hibernate.type.StringType());
			query.addScalar("v3", new org.hibernate.type.IntegerType());
			query.addScalar("v4", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}
	public void truncateAllSocket(String tableName) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			String sql = "delete " + tableName;
			Query query = session.createQuery(sql);
			query.executeUpdate();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
		
	}
	public void initResultExaminee(String tableName) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			String sql = "update " + tableName + " t set t.imeiNo=null,t.relativePath=null,"
					+ "t.absolutePath=null,t.createDate=null,t.modifyDate=null,"
					+ "t.photoResult=null,t.isPass=null,t.isVerify=0,t.verifyDate=null,"
					+ "t.verifyResult=null,t.faceExamineeName=null,t.faceIndetityDate=null,"
					+ "t.filename=null,t.isProcess=0,t.photoPrecision=null";
			Query query = session.createQuery(sql);
			query.executeUpdate();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}
	public void initResultExamineeSum(String tableName) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			String sql = "update " + tableName + " t set t.passSum=0,t.passNormalSum=0,t.passSpecialSum=0,"
					+ "t.passExitSum=0,t.photoSumY=0,t.photoSumN = 0,t.photoSumNn=0,t.photoSumNy=0,"
					+ "t.identitySumY=0,t.identitySumN=0,t.cardSum=0,t.realSum=0";
			Query query = session.createQuery(sql);
			query.executeUpdate();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
		
	}
}
