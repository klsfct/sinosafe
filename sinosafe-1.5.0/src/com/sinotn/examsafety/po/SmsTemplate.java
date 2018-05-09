package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * SmsTemplate entity. @author MyEclipse Persistence Tools
 */

public class SmsTemplate implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private String templateId;
	private String templateName;
	private String templateDescribe;
	private Date createDate;
	private String creater;
	private String createIp;
	private String examArea;
	private String examAreaName;

	// Constructors

	/** default constructor */
	public SmsTemplate() {
	}

	/** minimal constructor */
	public SmsTemplate(String templateId) {
		this.templateId = templateId;
	}

	


	public SmsTemplate(String templateId, String templateName,
			String templateDescribe, Date createDate, String creater,
			String createIp, String examArea, String examAreaName) {
		super();
		this.templateId = templateId;
		this.templateName = templateName;
		this.templateDescribe = templateDescribe;
		this.createDate = createDate;
		this.creater = creater;
		this.createIp = createIp;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
	}

	// Property accessors

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateDescribe() {
		return this.templateDescribe;
	}

	public void setTemplateDescribe(String templateDescribe) {
		this.templateDescribe = templateDescribe;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateIp() {
		return this.createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public String getExamArea() {
		return examArea;
	}

	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}

	public String getExamAreaName() {
		return examAreaName;
	}

	public void setExamAreaName(String examAreaName) {
		this.examAreaName = examAreaName;
	}

	

}