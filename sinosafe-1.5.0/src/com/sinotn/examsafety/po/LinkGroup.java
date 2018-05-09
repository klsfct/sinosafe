package com.sinotn.examsafety.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LinkGroup entity. @author MyEclipse Persistence Tools
 */

public class LinkGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 788067491868008962L;
	private Long id;
	private String name;
	private String remark;
	private String creater;
	private Date createDate;
	private String examArea;
	private String roleCode;
	private Set linkMans = new HashSet(0);

	// Constructors

	/** default constructor */
	public LinkGroup() {
	}

	/** minimal constructor */
	public LinkGroup(Long id) {
		this.id = id;
	}

	/** full constructor */
	public LinkGroup(Long id, String name, String remark, String creater,
			Date createDate, String examArea, Set linkMans, String roleCode) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.creater = creater;
		this.createDate = createDate;
		this.examArea = examArea;
		this.linkMans = linkMans;
		this.roleCode = roleCode;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set getLinkMans() {
		return this.linkMans;
	}

	public void setLinkMans(Set linkMans) {
		this.linkMans = linkMans;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Override
	public String toString() {
		return "LinkGroup [id=" + id + ", name=" + name + ", remark=" + remark
				+ ", creater=" + creater + ", createDate=" + createDate
				+ ", examArea=" + examArea + ", roleCode=" + roleCode
				+ ", linkMans=" + linkMans + "]";
	}

}