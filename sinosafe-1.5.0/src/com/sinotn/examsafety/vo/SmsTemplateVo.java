package com.sinotn.examsafety.vo;

import java.util.Date;

import com.sinotn.examsafety.po.SmsTemplate;


/**
 * SmsTemplateVo entity. @author MyEclipse Persistence Tools
 */

public class SmsTemplateVo{

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
	public SmsTemplateVo() {
	}

	/** minimal constructor */
	public SmsTemplateVo(String templateId) {
		this.templateId = templateId;
	}

	


	public SmsTemplateVo(SmsTemplate smsTemplate) {
		super();
		this.templateId = smsTemplate.getTemplateId();
		this.templateName = smsTemplate.getTemplateName();
		this.templateDescribe = smsTemplate.getTemplateDescribe();
		this.createDate = smsTemplate.getCreateDate();
		this.creater = smsTemplate.getCreater();
		this.createIp = smsTemplate.getCreateIp();
		this.examArea = smsTemplate.getExamArea();
		this.examAreaName = smsTemplate.getExamAreaName();
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