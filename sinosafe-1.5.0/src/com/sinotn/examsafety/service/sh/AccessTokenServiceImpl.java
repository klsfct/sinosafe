package com.sinotn.examsafety.service.sh;

import java.util.Date;

import net.sf.json.JSONObject;

import com.sinotn.examsafety.dao.AccessTokenDAO;
import com.sinotn.examsafety.po.AccessToken;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     AccessTokenServiceImpl.java
 * @Description:   视频设备令牌业务类
 * 
 * @author:    李斌
 * @version:   V1.0  
 * @Date:      2017年7月14日 下午3:53:17
 */
public class AccessTokenServiceImpl {
	
	/**
	 * 注入视频设备令牌DAO
	 */
	private AccessTokenDAO accessTokenDAO = null;
	
	/**
	 * 获取令牌
	 * @return  acessToken （令牌）
	 */
	public String getAccessTokenForLoad() {
		// 先从本地数据库中查询令牌
		AccessToken accessToken = accessTokenDAO.findObjectById(YsyConsts.APP_KEY);
		// 比较当前时间是否在有效时间内
		long expireTime = accessToken.getExpireTime().getTime();
		Date date = new Date();
		long nowTime = date.getTime();
		// 如果在有效时间内，直接返回令牌
		if (nowTime <= expireTime) {
			return accessToken.getAccessToken();
		}
		else {
			// 否则，则向萤石云远程发送请求获取令牌，
			JSONObject jsonObject = sendGetToken(YsyConsts.APP_KEY, YsyConsts.APP_SECRET);
			if ("200".equals(jsonObject.optString("code"))) {
				// 并更新本地数据库中的令牌和有效时间
				this.updateAccessToken(jsonObject);
				String dataAccessToken = jsonObject.getJSONObject("data").getString("accessToken");
				return dataAccessToken;
			}
		}
		return null;
	}
	/**
	 * 向萤石云发送远端请求（添加）并返回状态码和参数
	 * @param accessTokenStr（令牌）
	 * @param deviceSerial	（设备序列号）
	 * @param validateCode	（设备验证码）
	 */
	public JSONObject sendAddDevice(String accessTokenStr, String deviceSerial,
			String validateCode){
		String params = "accessToken=" + accessTokenStr +"&deviceSerial=" + deviceSerial +
				"&validateCode=" + validateCode;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(YsyConsts.ADD_DEVICE_URL, params);
		return jsonObject;
		
	}
	/**
	 * 远程获取accessToken
	 * @param appKey	（萤石云Key）
	 * @param appSecret	（萤石云Secret）
	 * @return JSONObject	（远程请求返回的JSON）
	 */
	public JSONObject sendGetToken(String appKey, String appSecret){
		String params = "appKey=" + appKey + "&appSecret=" + appSecret;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(YsyConsts.GET_TOKEN_URL, params);
		return jsonObject;
	}
	/**
	 * 从远程获取令牌更新到本地
	 * @param appKey	（萤石云Key）
	 * @param jsonObject	（远程请求返回的JSON）
	 */
	public void updateAccessToken(JSONObject jsonObject) {
			String code = jsonObject.getString("code");
			String msg = jsonObject.getString("msg");
			String dataAccessToken = jsonObject.optJSONObject("data").optString("accessToken");
			String dataExpireTime = jsonObject.optJSONObject("data").optString("expireTime");
			Date date = new Date(new Long(dataExpireTime));
			this.accessTokenDAO.updateAccessToken(dataAccessToken, code, msg, date);
		
	}
	/**
	 * 删除
	 * @param accessToken	（令牌）
	 * @param deviceSerial	（设备序列号）
	 * @return JSONObject	（远程请求返回的JSON）
	 */
	public JSONObject sendDelDevice(String accessToken, String deviceSerial) {
		String params = "accessToken=" + accessToken + "&deviceSerial=" + deviceSerial;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(YsyConsts.DEL_DEVICE_URL, params);
		return jsonObject;
	}
	/**
	 * 向远程发送请求获取设备列表
	 * @param accessTokenStr	（令牌）
	 * @param pageStart 		（分页起始页，从0开始）
	 * @param pageSize 			（分页大小，默认为10，最大为50）
	 * @return JSONObject	（远程请求返回的JSON）
	 */
	public JSONObject sendList(String accessToken, int pageStart, int pageSize) {
		String params = "accessToken=" + accessToken + "&pageStart=" + pageStart +"&pageSize=" + pageSize;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(YsyConsts.LIST_URL, params);
		return jsonObject;
	}
	/**
	 * 向远程发送请求修改设备名称
	 * @param accessTokenStr	（令牌）
	 * @param deviceSerial		（设备序列号）
	 * @param deviceName		（设备名称）
	 * @return
	 */
	public JSONObject senUpdateDeviceName(String accessTokenStr,
			String deviceSerial, String deviceName) {
		String params = "accessToken=" + accessTokenStr + "&deviceSerial=" + deviceSerial +"&deviceName=" + deviceName;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(YsyConsts.UPDATE_DEVICENAME_URL, params);
		return jsonObject;
	}
	
	
		
	/**----------------------------------getter/setter*/
	public AccessTokenDAO getAccessTokenDAO() {
		return accessTokenDAO;
	}

	public void setAccessTokenDAO(AccessTokenDAO accessTokenDAO) {
		this.accessTokenDAO = accessTokenDAO;
	}
	
	
	
	
	
	

	

	
}
