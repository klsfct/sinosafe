package com.sinotn.examsafety.po;

import java.util.Date;




/**
 * AccessToken entity. @author MyEclipse Persistence Tools
 */

public class AccessToken implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4530395032055434453L;
	// Fields

	private String appKey;
	private String appSecret;
	private String accessToken;
	private Date expireTime;
	private String backCode;
	private String backMsg;

	// Constructors

	/** default constructor */
	public AccessToken() {
	}

	/** minimal constructor */
	public AccessToken(String appKey) {
		this.appKey = appKey;
	}

	/** full constructor */
	public AccessToken(String appKey, String appSecret, String accessToken,
			Date expireTime, String backCode, String backMsg) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.accessToken = accessToken;
		this.expireTime = expireTime;
		this.backCode = backCode;
		this.backMsg = backMsg;
	}

	// Property accessors

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getBackCode() {
		return this.backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public String getBackMsg() {
		return this.backMsg;
	}

	public void setBackMsg(String backMsg) {
		this.backMsg = backMsg;
	}

	@Override
	public String toString() {
		return "AccessToken [appKey=" + appKey + ", appSecret=" + appSecret
				+ ", accessToken=" + accessToken + ", expireTime=" + expireTime
				+ ", backCode=" + backCode + ", backMsg=" + backMsg + "]";
	}

}