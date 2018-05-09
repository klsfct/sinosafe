package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * SmsInfo entity. @author MyEclipse Persistence Tools
 */

public class SmsInfo implements java.io.Serializable {

	// Fields

	private String id;
	private SmsTask smsTask;
	private String examArea;
	private String message;
	private String phone;
	private String linkman;
	private String linkgroup;
	private String creater;
	private Date createDate;
	private Long msgNum;
	private Boolean sendFlag;
	private String linkManId;
	private String wexinId;
	private Boolean readFlag;
	private String replyMsg;
	private String taskType;
	// Constructors

	/** default constructor */
	public SmsInfo() {
	}

	/** minimal constructor */
	public SmsInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public SmsInfo(String id, SmsTask smsTask, String examArea, String message,
			String phone, String linkman, String linkgroup, String creater,
			Date createDate, Long msgNum, Boolean sendFlag, String linkManId, String wexinId,
			Boolean readFlag, String replyMsg, String taskType) {
		this.id = id;
		this.smsTask = smsTask;
		this.examArea = examArea;
		this.message = message;
		this.phone = phone;
		this.linkman = linkman;
		this.linkgroup = linkgroup;
		this.creater = creater;
		this.createDate = createDate;
		this.msgNum = msgNum;
		this.sendFlag = sendFlag;
		this.linkManId = linkManId;
		this.wexinId = wexinId;
		this.readFlag = readFlag;
		this.replyMsg = replyMsg;
		this.taskType = taskType;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SmsTask getSmsTask() {
		return this.smsTask;
	}

	public void setSmsTask(SmsTask smsTask) {
		this.smsTask = smsTask;
	}

	public String getExamArea() {
		return this.examArea;
	}

	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkgroup() {
		return this.linkgroup;
	}

	public void setLinkgroup(String linkgroup) {
		this.linkgroup = linkgroup;
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

	public Long getMsgNum() {
		return this.msgNum;
	}

	public void setMsgNum(Long msgNum) {
		this.msgNum = msgNum;
	}

	public Boolean getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(Boolean sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getLinkManId() {
		return linkManId;
	}

	public void setLinkManId(String linkManId) {
		this.linkManId = linkManId;
	}

	public String getWexinId() {
		return wexinId;
	}

	public void setWexinId(String wexinId) {
		this.wexinId = wexinId;
	}

	public Boolean getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Boolean readFlag) {
		this.readFlag = readFlag;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
}