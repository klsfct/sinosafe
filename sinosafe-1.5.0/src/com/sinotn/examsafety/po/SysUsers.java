package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * SysUsers entity. @author MyEclipse Persistence Tools
 */

public class SysUsers implements java.io.Serializable {

	// Fields

	private String userId;
	private String password;
	private String areaId;
	private String areaName;
	private Date createDate;
	private Date loginDate;
	private String loginIp;
	private Date logoutDate;
	private Boolean isEnabled;
	private String principal;
	private String mobile;
	private Long examPlace;

	// Constructors

	/** default constructor */
	public SysUsers() {
	}

	/** minimal constructor */
	public SysUsers(String userId, String password, String areaId) {
		this.userId = userId;
		this.password = password;
		this.areaId = areaId;
	}

	/** full constructor */
	public SysUsers(String userId, String password, String areaId,
			String areaName, Date createDate, Date loginDate, String loginIp,
			Date logoutDate, Boolean isEnabled, String principal, String mobile, Long examPlace) {
		this.userId = userId;
		this.password = password;
		this.areaId = areaId;
		this.areaName = areaName;
		this.createDate = createDate;
		this.loginDate = loginDate;
		this.loginIp = loginIp;
		this.logoutDate = logoutDate;
		this.isEnabled = isEnabled;
		this.principal = principal;
		this.mobile = mobile;
		this.examPlace = examPlace;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLogoutDate() {
		return this.logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(Long examPlace) {
		this.examPlace = examPlace;
	}

	@Override
	public String toString() {
		return "SysUsers [userId=" + userId + ", password=" + password
				+ ", areaId=" + areaId + ", areaName=" + areaName
				+ ", createDate=" + createDate + ", loginDate=" + loginDate
				+ ", loginIp=" + loginIp + ", logoutDate=" + logoutDate
				+ ", isEnabled=" + isEnabled + ", principal=" + principal
				+ ", mobile=" + mobile + ", examPlace=" + examPlace + "]";
	}
	
}