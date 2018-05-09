package com.sinotn.examsafety.vo;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.LinkGroup;
import com.sinotn.examsafety.service.ConstantsService;



public class LinkGroupVo{

	// Fields

	private Long id;
	private String name;
	private String remark;
	private String creater;
	private Date createDate;
	private String examArea;
	private String roleCode;
	private String roleCodeName;
	private List<LinkManVo> linkMans;

	// Constructors

	/** default constructor */
	public LinkGroupVo() {
	}

	/** minimal constructor */
	public LinkGroupVo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public LinkGroupVo(Long id, String name, String remark, String creater,String roleCodeName,
			Date createDate, String examArea, List<LinkManVo> linkMans,String roleCode) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.creater = creater;
		this.createDate = createDate;
		this.examArea = examArea;
		this.linkMans = linkMans;
		this.roleCode = roleCode;
		this.roleCodeName = roleCodeName;
	}

	public LinkGroupVo(LinkGroup linkGroup) {
		this.id = linkGroup.getId();
		this.name = linkGroup.getName();
		this.remark = linkGroup.getRemark();
		this.creater = linkGroup.getCreater();
		this.createDate = linkGroup.getCreateDate();
		this.examArea = linkGroup.getExamArea();
		this.roleCode = linkGroup.getRoleCode();
		this.roleCodeName = linkGroup.getRoleCode();
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

	public List<LinkManVo> getLinkMans() {
		return linkMans;
	}

	public void setLinkMans(List<LinkManVo> linkMans) {
		this.linkMans = linkMans;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleCodeName() {
		return ConstantsService.getRoleCode().get(roleCodeName);
	}

	public void setRoleCodeName(String roleCodeName) {
		this.roleCodeName = roleCodeName;
	}


}