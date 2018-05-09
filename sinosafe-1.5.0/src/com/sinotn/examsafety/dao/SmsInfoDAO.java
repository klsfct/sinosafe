package com.sinotn.examsafety.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SmsInfo;
import com.sinotn.examsafety.service.ISmsService;


public class SmsInfoDAO extends BaseObjectDAO<SmsInfo, String> {
	/**
	 * 根据联系人ID获取微信消息列表 
	 */
	public List<SmsInfo> findWxSmsInfoByLinkManId(Long linkManId) {
		Date date = Calendar.getInstance().getTime();
		String hql = "FROM SmsInfo t "
				+ " WHERE t.smsTask.waitSendDate <=:date "
				+ " AND t.taskType =:taskType "
				+ " AND t.linkManId =:linkManId "
				+ " ORDER BY t.smsTask.waitSendDate DESC";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setTimestamp("date", date);
		query.setString("taskType", ISmsService.TASK_TYPE_SMS);
		query.setLong("linkManId", linkManId);
		return query.list();
	}

}
