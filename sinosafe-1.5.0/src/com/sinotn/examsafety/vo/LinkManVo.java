package com.sinotn.examsafety.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.po.LinkMan;

/**
 * LinkMan entity. @author MyEclipse Persistence Tools
 */

public class LinkManVo implements java.io.Serializable {

	// Fields

	private Long id;
	private LinkGroupVo linkGroupVo;
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
	
	private String phoneModel;
	private String phoneOs;
	private String weixinName;
	
	private ExamPlaceVo examPlaceVo;

	private String signStatus;
	// Constructors

	/** default constructor */
	public LinkManVo() {
	}

	/** minimal constructor */
	public LinkManVo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public LinkManVo(Long id, LinkGroupVo linkGroupVo, String name,
			String phone, String remark, String creater, Date createDate,
			String examArea, Date lastLoginDate, String lastLoginAddr,
			String lastLoginLat, String lastLoginLng, Set linkManLogins
			,String password,String weixinId,String phoneModel
			,String phoneOs,String weixinName,ExamPlaceVo examPlaceVo,
			String signStatus) {
		this.id = id;
		this.linkGroupVo = linkGroupVo;
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
		
		this.phoneModel = phoneModel;
		this.phoneOs = phoneOs;
		this.weixinName = weixinName;
		
		this.examPlaceVo = examPlaceVo;
		this.signStatus = signStatus;
	}
	
	public LinkManVo(LinkMan linkMan) {
		this.id = linkMan.getId();
		this.linkGroupVo = new LinkGroupVo(linkMan.getLinkGroup());
		this.name = linkMan.getName();
		this.phone = linkMan.getPhone();
		this.remark = linkMan.getRemark();
		this.creater = linkMan.getCreater();
		this.createDate = linkMan.getCreateDate();
		this.examArea = linkMan.getExamArea();
		this.lastLoginDate = linkMan.getLastLoginDate();
		this.lastLoginAddr = linkMan.getLastLoginAddr();
		this.lastLoginLat = linkMan.getLastLoginLat();
		this.lastLoginLng = linkMan.getLastLoginLng();
		this.password = linkMan.getPassword();
		this.weixinId = linkMan.getWeixinId();
		this.examPlaceVo = new ExamPlaceVo(linkMan.getExamPlace());
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LinkGroupVo getLinkGroup() {
		return this.linkGroupVo;
	}

	public void setLinkGroup(LinkGroupVo linkGroupVo) {
		this.linkGroupVo = linkGroupVo;
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
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getPhoneOs() {
		return phoneOs;
	}

	public void setPhoneOs(String phoneOs) {
		this.phoneOs = phoneOs;
	}

	public String getWeixinName() {
		return weixinName;
	}

	public void setWeixinName(String weixinName) {
		this.weixinName = weixinName;
	}

	public LinkGroupVo getLinkGroupVo() {
		return linkGroupVo;
	}

	public void setLinkGroupVo(LinkGroupVo linkGroupVo) {
		this.linkGroupVo = linkGroupVo;
	}

	public ExamPlaceVo getExamPlaceVo() {
		return examPlaceVo;
	}

	public void setExamPlaceVo(ExamPlaceVo examPlaceVo) {
		this.examPlaceVo = examPlaceVo;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}


}