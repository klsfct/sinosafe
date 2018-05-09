package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * SocketPlace entity. @author MyEclipse Persistence Tools
 */

public class SocketDeviceHand implements java.io.Serializable {

	// Fields

	private Long id;
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
	private Boolean isProcess;

	// Constructors

	/** default constructor */
	public SocketDeviceHand() {
	}

	/** minimal constructor */
	public SocketDeviceHand(Long id) {
		this.id = id;
	}

	/** full constructor */
	public SocketDeviceHand(Long id, String imeiNo, String examProvince,
			String examProvinceName, String examArea, String examAreaName,
			Long examPlace, String examPlaceName, Integer dumpEnergy,
			Integer signalStrength, Date createDate, Date modifyDate,String filename,String deviceType,Boolean isProcess) {
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	

	public String getExamPlaceName() {
		return this.examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public Long getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(Long examPlace) {
		this.examPlace = examPlace;
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

	public Boolean getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Boolean isProcess) {
		this.isProcess = isProcess;
	}

}