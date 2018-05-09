package com.sinotn.examsafety.vo;


import java.util.Date;
import java.util.List;

import com.sinotn.examsafety.po.SysUsers;

public class SysUsersVo {

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
	private int level;
	private String menu;
	private List<BaseCodeVo> areaList;
	private Long examPlaceId;
	private String leftFlag;
	// Constructors

	/** default constructor */
	public SysUsersVo() {
	}

	/** minimal constructor */
	public SysUsersVo(String userId, String password, String areaId) {
		this.userId = userId;
		this.password = password;
		this.areaId = areaId;
	}

	/** full constructor */
	public SysUsersVo(SysUsers sysUsers) {
		this.userId = sysUsers.getUserId();
		this.password = sysUsers.getPassword();
		this.areaId = sysUsers.getAreaId();
		this.areaName = sysUsers.getAreaName();
		this.createDate = sysUsers.getCreateDate();
		this.loginDate = sysUsers.getLoginDate();
		this.loginIp = sysUsers.getLoginIp();
		this.logoutDate = sysUsers.getLogoutDate();
		this.isEnabled = sysUsers.getIsEnabled();
		this.principal = sysUsers.getPrincipal();
		this.mobile = sysUsers.getMobile();
		this.examPlaceId = sysUsers.getExamPlace();
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<BaseCodeVo> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<BaseCodeVo> areaList) {
		this.areaList = areaList;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	
	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	public String getLeftFlag() {
		return leftFlag;
	}

	public void setLeftFlag(String leftFlag) {
		this.leftFlag = leftFlag;
	}

	@Override
	public String toString() {
		return "SysUsersVo [userId=" + userId + ", password=" + password
				+ ", areaId=" + areaId + ", areaName=" + areaName
				+ ", createDate=" + createDate + ", loginDate=" + loginDate
				+ ", loginIp=" + loginIp + ", logoutDate=" + logoutDate
				+ ", isEnabled=" + isEnabled + ", principal=" + principal
				+ ", mobile=" + mobile + ", level=" + level + ", menu=" + menu
				+ ", areaList=" + areaList + ", examPlaceId=" + examPlaceId
				+ ", leftFlag=" + leftFlag + "]";
	}

	
}