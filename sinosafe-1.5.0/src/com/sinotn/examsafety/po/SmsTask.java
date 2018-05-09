package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * SmsTask entity. @author MyEclipse Persistence Tools
 */

public class SmsTask implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4275781290326147300L;
	/**
	 * 
	 */
	private String taskId;
	private String smsDescribe;
	private String taskType;
	private Long smsLength;
	private Long sendCount;
	private Long successCount;
	private Boolean acceptFlag;
	private Date acceptDate;
	private Date waitSendDate;
	private String examArea;
	private String examAreaName;

	// Constructors

	/** default constructor */
	public SmsTask() {
	}

	/** minimal constructor */
	public SmsTask(String taskId) {
		this.taskId = taskId;
	}

	public SmsTask(String taskId, String smsDescribe, String taskType,
			Long smsLength, Long sendCount, Long successCount,
			Boolean acceptFlag, Date acceptDate, Date waitSendDate,
			String examArea, String examAreaName) {
		super();
		this.taskId = taskId;
		this.smsDescribe = smsDescribe;
		this.taskType = taskType;
		this.smsLength = smsLength;
		this.sendCount = sendCount;
		this.successCount = successCount;
		this.acceptFlag = acceptFlag;
		this.acceptDate = acceptDate;
		this.waitSendDate = waitSendDate;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
	}

	// Property accessors

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getSmsDescribe() {
		return this.smsDescribe;
	}

	public void setSmsDescribe(String smsDescribe) {
		this.smsDescribe = smsDescribe;
	}

	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Long getSmsLength() {
		return this.smsLength;
	}

	public void setSmsLength(Long smsLength) {
		this.smsLength = smsLength;
	}

	public Long getSendCount() {
		return this.sendCount;
	}

	public void setSendCount(Long sendCount) {
		this.sendCount = sendCount;
	}

	public Long getSuccessCount() {
		return this.successCount;
	}

	public void setSuccessCount(Long successCount) {
		this.successCount = successCount;
	}

	public Boolean getAcceptFlag() {
		return this.acceptFlag;
	}

	public void setAcceptFlag(Boolean acceptFlag) {
		this.acceptFlag = acceptFlag;
	}

	public Date getAcceptDate() {
		return this.acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public Date getWaitSendDate() {
		return this.waitSendDate;
	}

	public void setWaitSendDate(Date waitSendDate) {
		this.waitSendDate = waitSendDate;
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

	@Override
	public String toString() {
		return "SmsTask [taskId=" + taskId + ", smsDescribe=" + smsDescribe
				+ ", taskType=" + taskType + ", smsLength=" + smsLength
				+ ", sendCount=" + sendCount + ", successCount=" + successCount
				+ ", acceptFlag=" + acceptFlag + ", acceptDate=" + acceptDate
				+ ", waitSendDate=" + waitSendDate + ", examArea=" + examArea
				+ ", examAreaName=" + examAreaName + "]";
	}

	


}