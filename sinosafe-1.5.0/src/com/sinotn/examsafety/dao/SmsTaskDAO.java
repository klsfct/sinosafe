package com.sinotn.examsafety.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SmsTask;


public class SmsTaskDAO extends BaseObjectDAO<SmsTask, String>  {
	/**
	 * 根据当前时间和发送标志获取指令集合
	 * @return
	 */
	public List<SmsTask> findSmsTaskVoListBySednFlagAndWaitDate() {
		Date date = Calendar.getInstance().getTime();
		String hql = "FROM SmsTask t "
				+ " WHERE t.waitSendDate <=:date "
				+ " AND t.acceptFlag=:acceptFlag "
				+ " ORDER BY t.waitSendDate DESC";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setTimestamp("date", date);
		query.setBoolean("acceptFlag", false);
		return query.list();
	}
	
}