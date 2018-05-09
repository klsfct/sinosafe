package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.DeviceVideoInfo;
/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     DeviceVideoInfoDAO.java
 * @Description:   视频设备信息DAO
 * 
 * @author:    李斌
 * @version:   V1.0  
 * @Date:      2017年7月12日 下午1:41:47
 */
public class DeviceVideoInfoDAO extends BaseDAO<DeviceVideoInfo, String> {
	/**
	 * 根据注册状态查询符合条件的数量
	 * @param regFlag （注册状态码）
	 * @return long （符合条件的数量）
	 */
	public long findDeviceVideoCountByRegFlag(String regFlag) {
		String hql = "select count(*) from DeviceVideoInfo t where t.regFlag = ? ";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, regFlag);
		List list = query.list();
		return (Long)list.get(0);
		
	}
	/**
	 * 根据注册类别查询符合条件的数量
	 * @param deviceType （设备类别）
	 * @return long （符合条件的数量）
	 */
	public long findDeviceVideoCountByDeviceType(String deviceType) {
		String hql = "select count(*) from DeviceVideoInfo t where t.deviceType = ? ";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, deviceType);
		List list = query.list();
		return (Long)list.get(0);
	}
	/**
	 * 查询所有注册成功视频设备信息
	 * @param regFlag	（注册状态码）
	 * @return List<DeviceVideoInfo> （数据集合）
	 */
	public List<DeviceVideoInfo> findDeviceVideoInfo(String regFlag) {
		String hql = "from DeviceVideoInfo t where t.regFlag = ? order by t.examPlace.examPlace asc";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, regFlag);
		List list = query.list();
		return list;
	}
	/**
	 * 根据视频类别查询视频集合
	 * @param regFlag	（注册状态码）
	 * @param deviceType
	 * @return
	 */
	public List<DeviceVideoInfo> findDeviceVideoInfoByVideoType(
			String regFlag, String deviceType) {
		String hql = "from DeviceVideoInfo t "
				+ "where t.regFlag = ? "
				+ "and t.deviceType = ? "
				+ "order by t.examPlace.examPlace asc";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, regFlag);
		query.setString(1, deviceType);
		List list = query.list();
		return list;
	}


}
