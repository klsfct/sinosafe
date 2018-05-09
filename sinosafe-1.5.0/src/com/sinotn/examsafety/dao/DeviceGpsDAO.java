package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.DeviceGpsInfo;
/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @Description:   定位设备管理DAO
 * @Author:        李斌
 * @Version:       V1.0.0 
 * @Date:          2017年8月1日 上午10:11:07
 */
public class DeviceGpsDAO extends BaseDAO<DeviceGpsInfo, String>{
	/**
	 * 查询定位设备集合
	 * @return
	 */
	public List<DeviceGpsInfo> findDevicecGpsList() {
		String hql = "FROM DeviceGpsInfo t";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	/**
	 * 查询已设置起点和终点围栏的定位设备信息集合
	 * @return
	 */
	public List<DeviceGpsInfo> findByFenceId() {
		String hql = "FROM DeviceGpsInfo t "
				+ "WHERE "
				+ "t.beginFenceId IS NOT NULL "
				+ "AND "
				+ "t.endFenceId IS NOT NULL ";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List<DeviceGpsInfo> findDeviecGpsByExamType(Long examPlace) {
		String hql = "FROM DeviceGpsInfo t "
				+ " WHERE t.examPlace.examPlace=?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql).setParameter(0, examPlace);
		return query.list();
	}
	/**
	 * 根据设备名称查询单个设备信息
	 * @param entityNameStr
	 * @return
	 */
	public DeviceGpsInfo findDeviceGpsByEntityName(String entityNameStr) {
		String hql = "FROM DeviceGpsInfo t "
				+ "WHERE t.entityName =?";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql).setString(0, entityNameStr);
		List<DeviceGpsInfo> list = query.list();
		for (DeviceGpsInfo deviceGpsInfo : list) {
			DeviceGpsInfo deviceGpsInfo2 = list.get(0);
			return deviceGpsInfo2;
		}
		return null;
		
	}

}
