package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * LinkManLogin entity. @author MyEclipse Persistence Tools
 */

public class LinkManLogin implements java.io.Serializable {

	// Fields

	private String loginId;
	private LinkMan linkMan;
	private Date loginDate;
	private String loginAddr;
	private String loginLat;
	private String loginLng;
	private String loginIp;
	private String loginPhone;
	private String longinOs;
	private String longinWeixinId;

	// Constructors

	/** default constructor */
	public LinkManLogin() {
	}

	/** minimal constructor */
	public LinkManLogin(String loginId) {
		this.loginId = loginId;
	}

	/** full constructor */
	public LinkManLogin(String loginId, LinkMan linkMan, Date loginDate,
			String loginAddr, String loginLat, String loginLng, String loginIp,
			String loginPhone, String longinOs, String longinWeixinId) {
		this.loginId = loginId;
		this.linkMan = linkMan;
		this.loginDate = loginDate;
		this.loginAddr = loginAddr;
		this.loginLat = loginLat;
		this.loginLng = loginLng;
		this.loginIp = loginIp;
		this.loginPhone = loginPhone;
		this.longinOs = longinOs;
		this.longinWeixinId = longinWeixinId;
	}

	// Property accessors

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public LinkMan getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(LinkMan linkMan) {
		this.linkMan = linkMan;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginAddr() {
		return this.loginAddr;
	}

	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}

	public String getLoginLat() {
		return this.loginLat;
	}

	public void setLoginLat(String loginLat) {
		this.loginLat = loginLat;
	}

	public String getLoginLng() {
		return this.loginLng;
	}

	public void setLoginLng(String loginLng) {
		this.loginLng = loginLng;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginPhone() {
		return this.loginPhone;
	}

	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}

	public String getLonginOs() {
		return this.longinOs;
	}

	public void setLonginOs(String longinOs) {
		this.longinOs = longinOs;
	}

	public String getLonginWeixinId() {
		return this.longinWeixinId;
	}

	public void setLonginWeixinId(String longinWeixinId) {
		this.longinWeixinId = longinWeixinId;
	}

}