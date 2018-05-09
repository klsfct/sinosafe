package com.sinotn.examsafety.vo;

import java.util.Date;

import com.sinotn.examsafety.po.DeviceHandInfo;

public class DeviceHandInfoVo {

	// Fields

	private String imeiNo;
	private String examProvince;
	private String examProvinceName;
	private String examArea;
	private String examAreaName;
	private Long examPlace;
	private String examPlaceName;
	private Integer dumpEnergy;
	private Integer signalStrength;
	private Date createDate;
	private Date modifyDate;
	private String filename;
	private String deviceType;
	private String deviceTypeName;

	// Constructors

	/** default constructor */
	public DeviceHandInfoVo() {
	}

	/** minimal constructor */
	public DeviceHandInfoVo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	/** full constructor */
	public DeviceHandInfoVo(DeviceHandInfo deviceHandInfo) {
		this.imeiNo = deviceHandInfo.getImeiNo();
		this.examProvince = deviceHandInfo.getExamProvince();
		this.examProvinceName = deviceHandInfo.getExamProvinceName();
		this.examArea = deviceHandInfo.getExamArea();
		this.examAreaName = deviceHandInfo.getExamAreaName();
		this.examPlace = deviceHandInfo.getExamPlace();
		this.examPlaceName = deviceHandInfo.getExamPlaceName();
		this.dumpEnergy = deviceHandInfo.getDumpEnergy();
		this.signalStrength = deviceHandInfo.getSignalStrength();
		this.createDate = deviceHandInfo.getCreateDate();
		this.modifyDate = deviceHandInfo.getModifyDate();
		this.filename = deviceHandInfo.getFilename();
		this.deviceType = deviceHandInfo.getDeviceType();
	}

	public String getImeiNo() {
		return this.imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public String getExamProvince() {
		return this.examProvince;
	}

	public void setExamProvince(String examProvince) {
		this.examProvince = examProvince;
	}

	public String getExamProvinceName() {
		return this.examProvinceName;
	}

	public void setExamProvinceName(String examProvinceName) {
		this.examProvinceName = examProvinceName;
	}

	public String getExamArea() {
		return this.examArea;
	}

	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}

	public String getExamAreaName() {
		return this.examAreaName;
	}

	public void setExamAreaName(String examAreaName) {
		this.examAreaName = examAreaName;
	}

	

	public Long getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(Long examPlace) {
		this.examPlace = examPlace;
	}

	public String getExamPlaceName() {
		return this.examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	
	public Integer getDumpEnergy() {
		return dumpEnergy;
	}

	public void setDumpEnergy(Integer dumpEnergy) {
		this.dumpEnergy = dumpEnergy;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

}