package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sinotn.common.exception.DaoException;
import com.sinotn.examsafety.po.DeviceFaceInfo;

public class DeviceFaceInfoDAO extends BaseDAO<DeviceFaceInfo, String> {
	/**
	 * 按照省地区分组统计已注册人脸识别设备信息
	 * @param areaId
	 * @return
	 */
	public List<?> groupDeviceFaceByProvinceArea(String areaId) {
		String sql = "select t.EXAM_PROVINCE id,t.EXAM_PROVINCE_NAME name,count(*) total "
				+ "from DEVICE_FACE_INFO t where t.EXAM_PROVINCE like ? group by t.EXAM_PROVINCE,t.EXAM_PROVINCE_NAME "
				+ "order by t.EXAM_PROVINCE";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0,areaId);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
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
	public List<?> groupDeviceFaceByExamArea(String areaId) {
		String sql = "select t.EXAM_AREA id,t.EXAM_AREA_NAME name,count(*) total "
				+ "from DEVICE_FACE_INFO t where t.EXAM_PROVINCE = ? group by t.EXAM_AREA,t.EXAM_AREA_NAME "
				+ "order by t.EXAM_AREA";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0,areaId);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
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
	public List<?> groupDeviceFaceByExamPlace(String areaId) {
		String sql = "select t.EXAM_PLACE id,t.EXAM_PLACE_NAME name,count(*) total "
				+ "from DEVICE_FACE_INFO t where t.EXAM_AREA like ? group by t.EXAM_PLACE,t.EXAM_PLACE_NAME "
				+ "order by t.EXAM_PLACE";
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0,areaId);
			query.addScalar("id", new org.hibernate.type.StringType());
			query.addScalar("name", new org.hibernate.type.StringType());
			query.addScalar("total", new org.hibernate.type.IntegerType());
			return query.list();
		} catch (HibernateException he) {
			throw new DaoException(he.getMessage());
		}
	}
}
