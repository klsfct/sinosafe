package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.SmsTask;

/**
 * SmsTask entity. @author MyEclipse Persistence Tools
 */

public class SmsTaskVo{

	public enum tyskType{
		ZORE("0","微信"),ONE("1","短信");
		private String code;
		private String text;
		
		private tyskType(String code, String text) {
			this.code = code;
			this.text = text;
		}
		public String getCode() {
			return code;
		}
		public String getText() {
			return text;
		}
		
	}
	
	
	// Fields

	private String taskId;
	private String smsDescribe;
	private String taskType;
	private Long smsLength;
	private Long sendCount;
	private Long successCount;
	private Boolean acceptFlag;
	private Date acceptDate;
	private Date waitSendDate;
	private String receiverList;
	private String creater;
	private String createIp;
    private String examArea;
    private String examAreaName;

	// Constructors

	/** default constructor */
	public SmsTaskVo() {
	}

	/** minimal constructor */
	public SmsTaskVo(String taskId) {
		this.taskId = taskId;
	}
	
	public SmsTaskVo(SmsTask smsTask) {
		this.taskId = smsTask.getTaskId();
		this.smsDescribe = smsTask.getSmsDescribe();
		this.taskType = smsTask.getTaskType();
		this.smsLength = smsTask.getSmsLength();
		this.sendCount = smsTask.getSendCount();
		this.successCount = smsTask.getSuccessCount();
		this.acceptFlag = smsTask.getAcceptFlag();
		this.acceptDate = smsTask.getAcceptDate();
		this.waitSendDate = smsTask.getWaitSendDate();
		this.examArea = smsTask.getExamArea();
		this.examAreaName = smsTask.getExamAreaName();
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getAcceptDate() {
		return this.acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getWaitSendDate() {
		return this.waitSendDate;
	}

	public void setWaitSendDate(Date waitSendDate) {
		this.waitSendDate = waitSendDate;
	}

	public String getReceiverList() {
		return receiverList;
	}

	public void setReceiverList(String receiverList) {
		this.receiverList = receiverList;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateIp() {
		return createIp;
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

	public SmsTaskVo(String taskId, String smsDescribe, String taskType,
			Long smsLength, Long sendCount, Long successCount,
			Boolean acceptFlag, Date acceptDate, Date waitSendDate,
			String receiverList, String creater, String createIp,
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
		this.receiverList = receiverList;
		this.creater = creater;
		this.createIp = createIp;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
	}

	

}