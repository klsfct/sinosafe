package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * DiviceGpsInfo entity. @author MyEclipse Persistence Tools
 */

public class DeviceGpsInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6164594073934421049L;
	private String gpsId;
	private ExamPlace examPlace;
	private String entityName;
	private String entityDesc;
	private String entityNumber;
	private String beginLat;
	private String beginLng;
	private String beginFenceId;
	private String endLat;
	private String endLng;
	private String endFenceId;
	private Date inputTime;
	private String isEnabled;
	private String beginName;
	private String latestLocation;
	private String latestLng;
	private String latestLat;
	private String reachFlag;
	private Date lastTime;
	private String linkManName;
	private String linkManPhone;
	// Constructors

	/** default constructor */
	public DeviceGpsInfo() {
	}

	/** full constructor */
	public DeviceGpsInfo(ExamPlace examPlace, String entityName,
			String entityDesc, String entityNumber, String beginLat,
			String beginLng, String beginFenceId, String endLat, String endLng,
			String endFenceId, Date inputTime, String isEnabled,
			String beginName, String latestLocation,  String latestLng, String latestLat, 
			String reachFlag, Date lastTime, String linkManName, String linkManPhone) {
		this.examPlace = examPlace;
		this.entityName = entityName;
		this.entityDesc = entityDesc;
		this.entityNumber = entityNumber;
		this.beginLat = beginLat;
		this.beginLng = beginLng;
		this.beginFenceId = beginFenceId;
		this.endLat = endLat;
		this.endLng = endLng;
		this.endFenceId = endFenceId;
		this.inputTime = inputTime;
		this.isEnabled = isEnabled;
		this.beginName = beginName;
		this.latestLocation = latestLocation;
		this.latestLng = latestLng;
		this.latestLat = latestLat;
		this.reachFlag = reachFlag;
		this.lastTime = lastTime;
		this.linkManName = linkManName;
		this.linkManPhone = linkManPhone;
	}

	// Property accessors

	public String getGpsId() {
		return this.gpsId;
	}

	public void setGpsId(String gpsId) {
		this.gpsId = gpsId;
	}

	public ExamPlace getExamPlace() {
		return this.examPlace;
	}

	public void setExamPlace(ExamPlace examPlace) {
		this.examPlace = examPlace;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityDesc() {
		return this.entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public String getEntityNumber() {
		return this.entityNumber;
	}

	public void setEntityNumber(String entityNumber) {
		this.entityNumber = entityNumber;
	}

	public String getBeginLat() {
		return this.beginLat;
	}

	public void setBeginLat(String beginLat) {
		this.beginLat = beginLat;
	}

	public String getBeginLng() {
		return this.beginLng;
	}

	public void setBeginLng(String beginLng) {
		this.beginLng = beginLng;
	}

	public String getBeginFenceId() {
		return this.beginFenceId;
	}

	public void setBeginFenceId(String beginFenceId) {
		this.beginFenceId = beginFenceId;
	}

	public String getEndLat() {
		return this.endLat;
	}

	public void setEndLat(String endLat) {
		this.endLat = endLat;
	}

	public String getEndLng() {
		return this.endLng;
	}

	public void setEndLng(String endLng) {
		this.endLng = endLng;
	}

	public String getEndFenceId() {
		return this.endFenceId;
	}

	public void setEndFenceId(String endFenceId) {
		this.endFenceId = endFenceId;
	}

	public Date getInputTime() {
		return this.inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getBeginName() {
		return this.beginName;
	}

	public void setBeginName(String beginName) {
		this.beginName = beginName;
	}

	public String getLatestLocation() {
		return latestLocation;
	}

	public void setLatestLocation(String latestLocation) {
		this.latestLocation = latestLocation;
	}

	public String getLatestLng() {
		return latestLng;
	}

	public void setLatestLng(String latestLng) {
		this.latestLng = latestLng;
	}

	public String getLatestLat() {
		return latestLat;
	}

	public void setLatestLat(String latestLat) {
		this.latestLat = latestLat;
	}

	public String getReachFlag() {
		return reachFlag;
	}

	public void setReachFlag(String reachFlag) {
		this.reachFlag = reachFlag;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
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
	
}