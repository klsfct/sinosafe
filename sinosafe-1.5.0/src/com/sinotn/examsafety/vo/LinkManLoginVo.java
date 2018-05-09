package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.LinkManLogin;

/**
 * LinkManVoLogin entity. @author MyEclipse Persistence Tools
 */

public class LinkManLoginVo {

	// Fields

	private String loginId;
	private LinkManVo linkManVo;
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
	public LinkManLoginVo() {
	}

	/** minimal constructor */
	public LinkManLoginVo(String loginId) {
		this.loginId = loginId;
	}

	/** full constructor */
	public LinkManLoginVo(String loginId, LinkManVo linkManVo, Date loginDate,
			String loginAddr, String loginLat, String loginLng, String loginIp,
			String loginPhone, String longinOs, String longinWeixinId) {
		this.loginId = loginId;
		this.linkManVo = linkManVo;
		this.loginDate = loginDate;
		this.loginAddr = loginAddr;
		this.loginLat = loginLat;
		this.loginLng = loginLng;
		this.loginIp = loginIp;
		this.loginPhone = loginPhone;
		this.longinOs = longinOs;
		this.longinWeixinId = longinWeixinId;
	}
	
	public LinkManLoginVo(LinkManLogin linkManLogin) {
		this.loginId = linkManLogin.getLoginId();
		this.linkManVo = new LinkManVo(linkManLogin.getLinkMan());
		this.loginDate =linkManLogin.getLoginDate();
		this.loginAddr = linkManLogin.getLoginAddr();
		this.loginLat = linkManLogin.getLoginLat();
		this.loginLng = linkManLogin.getLoginLng();
		this.loginIp = linkManLogin.getLoginIp();
		this.loginPhone = linkManLogin.getLoginPhone();
		this.longinOs = linkManLogin.getLonginOs();
		this.longinWeixinId = linkManLogin.getLonginWeixinId();
	}

	// Property accessors

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public LinkManVo getLinkManVo() {
		return this.linkManVo;
	}

	public void setLinkManVo(LinkManVo linkManVo) {
		this.linkManVo = linkManVo;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
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