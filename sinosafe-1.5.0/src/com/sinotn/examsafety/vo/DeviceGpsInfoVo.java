package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.DeviceGpsInfo;

/**
 * DiviceGpsInfo entity. @author MyEclipse Persistence Tools
 */

public class DeviceGpsInfoVo implements java.io.Serializable {

	// Fields

	private String gpsId;
	private ExamPlaceVo examPlaceVo;
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
	public DeviceGpsInfoVo() {
	}
	
	public DeviceGpsInfoVo(String gpsId) {
		this.gpsId = gpsId;
	}

	/** full constructor */
	public DeviceGpsInfoVo(DeviceGpsInfo deviceGpsInfo) {
		this.examPlaceVo = new ExamPlaceVo(deviceGpsInfo.getExamPlace());
		this.entityName = deviceGpsInfo.getEntityName();
		this.entityDesc = deviceGpsInfo.getEntityDesc();
		this.entityNumber = deviceGpsInfo.getEntityNumber();
		this.beginLat = deviceGpsInfo.getBeginLat();
		this.beginLng = deviceGpsInfo.getBeginLng();
		this.beginFenceId = deviceGpsInfo.getBeginFenceId();
		this.endLat = deviceGpsInfo.getEndLat();
		this.endLng = deviceGpsInfo.getEndLng();
		this.endFenceId = deviceGpsInfo.getEndFenceId();
		this.inputTime = deviceGpsInfo.getInputTime();
		this.isEnabled = deviceGpsInfo.getIsEnabled();
		this.beginName = deviceGpsInfo.getBeginName();
		this.gpsId = deviceGpsInfo.getGpsId();
		this.latestLocation =  deviceGpsInfo.getLatestLocation();
		this.latestLng = deviceGpsInfo.getLatestLng();
		this.latestLat = deviceGpsInfo.getLatestLat();
		this.reachFlag = deviceGpsInfo.getReachFlag();
		this.lastTime = deviceGpsInfo.getLastTime();
		this.linkManName = deviceGpsInfo.getLinkManName();
		this.linkManPhone = deviceGpsInfo.getLinkManPhone();
	}

	// Property accessors

	public String getGpsId() {
		return this.gpsId;
	}

	public void setGpsId(String gpsId) {
		this.gpsId = gpsId;
	}

	

	public ExamPlaceVo getExamPlaceVo() {
		return examPlaceVo;
	}

	public void setExamPlaceVo(ExamPlaceVo examPlaceVo) {
		this.examPlaceVo = examPlaceVo;
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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