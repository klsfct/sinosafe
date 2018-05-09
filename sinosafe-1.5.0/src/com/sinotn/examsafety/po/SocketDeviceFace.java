package com.sinotn.examsafety.po;

import java.util.Date;


public class SocketDeviceFace implements java.io.Serializable {

	// Fields

	private Long id;
	private String imeiNo;
	private String examProvince;
	private String examProvinceName;
	private String examArea;
	private String examAreaName;
	private Long examPlace;
	private String examPlaceName;
	private Date createDate;
	private Date modifyDate;
	private Integer verifyPrecision;
	private String filename;
	private Integer subject;
	private Boolean isProcess;

	// Constructors

	/** default constructor */
	public SocketDeviceFace() {
	}

	/** minimal constructor */
	public SocketDeviceFace(Long id) {
		this.id = id;
	}

	/** full constructor */
	public SocketDeviceFace(Long id, String imeiNo, String examProvince,
			String examProvinceName, String examArea, String examAreaName,
			Long examPlace, String examPlaceName, Date createDate,
			Date modifyDate, Integer verifyPrecision, String filename,Integer subject,Boolean isProcess) {
		this.id = id;
		this.imeiNo = imeiNo;
		this.examProvince = examProvince;
		this.examProvinceName = examProvinceName;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
		this.examPlace = examPlace;
		this.examPlaceName = examPlaceName;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.verifyPrecision = verifyPrecision;
		this.filename = filename;
		this.subject = subject;
		this.isProcess = isProcess;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getVerifyPrecision() {
		return this.verifyPrecision;
	}

	public void setVerifyPrecision(Integer verifyPrecision) {
		this.verifyPrecision = verifyPrecision;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public Boolean getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Boolean isProcess) {
		this.isProcess = isProcess;
	}

}