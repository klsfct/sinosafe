package com.sinotn.examsafety.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LinkMan entity. @author MyEclipse Persistence Tools
 */

public class LinkMan implements java.io.Serializable {

	// Fields

	private Long id;
	private LinkGroup linkGroup;
	private ExamPlace examPlace;
	private String name;
	private String phone;
	private String remark;
	private String creater;
	private Date createDate;
	private String examArea;
	private Date lastLoginDate;
	private String lastLoginAddr;
	private String lastLoginLat;
	private String lastLoginLng;
	private String password;
	private String weixinId;

	// Constructors

	/** default constructor */
	public LinkMan() {
	}

	/** minimal constructor */
	public LinkMan(Long id) {
		this.id = id;
	}

	/** full constructor */
	public LinkMan(Long id, LinkGroup linkGroup, String name,
			String phone, String remark, String creater, Date createDate,
			String examArea, Date lastLoginDate, String lastLoginAddr,
			String lastLoginLat, String lastLoginLng, Set linkManLogins,
			String password,String weixinId,ExamPlace examPlace) {
		this.id = id;
		this.linkGroup = linkGroup;
		this.name = name;
		this.phone = phone;
		this.remark = remark;
		this.creater = creater;
		this.createDate = createDate;
		this.examArea = examArea;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginAddr = lastLoginAddr;
		this.lastLoginLat = lastLoginLat;
		this.lastLoginLng = lastLoginLng;
		this.password = password;
		this.weixinId = weixinId;
		this.examPlace = examPlace;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LinkGroup getLinkGroup() {
		return this.linkGroup;
	}

	public void setLinkGroup(LinkGroup linkGroup) {
		this.linkGroup = linkGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getExamArea() {
		return this.examArea;
	}

	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginAddr() {
		return this.lastLoginAddr;
	}

	public void setLastLoginAddr(String lastLoginAddr) {
		this.lastLoginAddr = lastLoginAddr;
	}

	public String getLastLoginLat() {
		return this.lastLoginLat;
	}

	public void setLastLoginLat(String lastLoginLat) {
		this.lastLoginLat = lastLoginLat;
	}

	public String getLastLoginLng() {
		return this.lastLoginLng;
	}

	public void setLastLoginLng(String lastLoginLng) {
		this.lastLoginLng = lastLoginLng;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public ExamPlace getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(ExamPlace examPlace) {
		this.examPlace = examPlace;
	}
	
}