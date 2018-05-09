package com.sinotn.examsafety.dao;

import java.util.Date;

import org.hibernate.Session;

import com.sinotn.examsafety.po.AccessToken;
import com.sinotn.examsafety.service.sh.YsyConsts;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     AccessTokenDAO.java
 * @Description:   视频设备令牌DAO
 * 
 * @author:    李斌
 * @version:   V1.0  
 * @Date:      2017年7月14日 下午3:56:58
 */
public class AccessTokenDAO extends BaseDAO<AccessToken, String> {
	/**
	 * 更新数据库令牌
	 * @param dataAccessToken
	 * @param code
	 * @param msg
	 * @param dateStr
	 */
	public void updateAccessToken(String dataAccessToken, String code,
			String msg, Date date) {
		String hqlStr = "UPDATE AccessToken a"
				+ " SET a.accessToken=? ,"
				+ "  a.expireTime=? ,"
				+ "  a.backCode=?, "
				+ "  a.backMsg=? "
				+ " WHERE a.appKey=?";
		Session session = this.getSessionFactory().getCurrentSession();
		session.createQuery(hqlStr).setString(0, dataAccessToken)
			.setTimestamp(1, date).setString(2, code)
			.setString(3, msg).setString(4, YsyConsts.APP_KEY).executeUpdate();
		
	}

}
