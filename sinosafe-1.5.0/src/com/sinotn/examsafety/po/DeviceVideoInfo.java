package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * DeviceVideoInfo entity. @author MyEclipse Persistence Tools
 */

public class DeviceVideoInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5522679146234906624L;
	private String deviceSerial;
	private ExamPlace examPlace;
	private String deviceName;
	private String deviceNumber;
	private String deviceType;
	private String regFlag;
	private Date inputTime;
	private Date regTime;
	
	private String backCode;
	private String backMsg;
	private String validateCode; 
	private String linkManName;
	private String linkManPhone;

	// Constructors

	/** default constructor */
	public DeviceVideoInfo() {
	}

	/** minimal constructor */
	public DeviceVideoInfo(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	/** full constructor */
	public DeviceVideoInfo(String deviceSerial, ExamPlace examPlace,
			String deviceName, String deviceNumber, String deviceType,
			String regFlag, Date inputTime, Date regTime, String linkManName,
			String linkManPhone) {
		this.deviceSerial = deviceSerial;
		this.examPlace = examPlace;
		this.deviceName = deviceName;
		this.deviceNumber = deviceNumber;
		this.deviceType = deviceType;
		this.regFlag = regFlag;
		this.inputTime = inputTime;
		this.regTime = regTime;
		this.linkManName = linkManName;
		this.linkManPhone = linkManPhone;
	}

	// Property accessors

	public String getDeviceSerial() {
		return this.deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public ExamPlace getExamPlace() {
		return this.examPlace;
	}

	public void setExamPlace(ExamPlace examPlace) {
		this.examPlace = examPlace;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceNumber() {
		return this.deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getRegFlag() {
		return this.regFlag;
	}

	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}

	public Date getInputTime() {
		return this.inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public String getBackMsg() {
		return backMsg;
	}

	public void setBackMsg(String backMsg) {
		this.backMsg = backMsg;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getLinkManPhone() {
		return linkManPhone;
	}

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}

	@Override
	public String toString() {
		return "DeviceVideoInfo [deviceSerial=" + deviceSerial + ", examPlace="
				+ examPlace + ", deviceName=" + deviceName + ", deviceNumber="
				+ deviceNumber + ", deviceType=" + deviceType + ", regFlag="
				+ regFlag + ", inputTime=" + inputTime + ", regTime=" + regTime
				+ ", backCode=" + backCode + ", backMsg=" + backMsg
				+ ", validateCode=" + validateCode + ", linkManName="
				+ linkManName + ", linkManPhone=" + linkManPhone + "]";
	}
	
}