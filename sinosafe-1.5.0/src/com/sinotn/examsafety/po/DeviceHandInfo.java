package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * DeviceHandInfo entity. @author MyEclipse Persistence Tools
 */

public class DeviceHandInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5438451372522652614L;
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

	// Constructors

	/** default constructor */
	public DeviceHandInfo() {
	}

	/** minimal constructor */
	public DeviceHandInfo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	/** full constructor */
	public DeviceHandInfo(String imeiNo, String examProvince,
			String examProvinceName, String examArea, String examAreaName,
			Long examPlace, String examPlaceName, Integer dumpEnergy,
			Integer signalStrength, Date createDate, Date modifyDate,String filename,String deviceType) {
		this.imeiNo = imeiNo;
		this.examProvince = examProvince;
		this.examProvinceName = examProvinceName;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
		this.examPlace = examPlace;
		this.examPlaceName = examPlaceName;
		this.dumpEnergy = dumpEnergy;
		this.signalStrength = signalStrength;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.filename = filename;
		this.deviceType = deviceType;
	}

	// Property accessors

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

	@Override
	public String toString() {
		return "DeviceHandInfo [imeiNo=" + imeiNo + ", examProvince="
				+ examProvince + ", examProvinceName=" + examProvinceName
				+ ", examArea=" + examArea + ", examAreaName=" + examAreaName
				+ ", examPlace=" + examPlace + ", examPlaceName="
				+ examPlaceName + ", dumpEnergy=" + dumpEnergy
				+ ", signalStrength=" + signalStrength + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", filename="
				+ filename + ", deviceType=" + deviceType + "]";
	}

}