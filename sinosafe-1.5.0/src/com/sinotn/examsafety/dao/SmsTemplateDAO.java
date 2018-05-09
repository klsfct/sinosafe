package com.sinotn.examsafety.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinotn.examsafety.po.SmsTemplate;

public class SmsTemplateDAO extends  BaseObjectDAO<SmsTemplate, String>   {
	/**
	 * 获取指令模版集合
	 * @return
	 */
	public List<SmsTemplate> findSmsTemplateList() {
		String hql = "from SmsTemplate t order by createDate desc";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
}