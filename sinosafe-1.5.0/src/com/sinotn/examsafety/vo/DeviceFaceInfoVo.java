package com.sinotn.examsafety.vo;

import java.util.Date;

import com.sinotn.examsafety.po.DeviceFaceInfo;

public class DeviceFaceInfoVo {

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

	/** default constructor */
	public DeviceFaceInfoVo() {
	}

	/** minimal constructor */
	public DeviceFaceInfoVo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	/** full constructor */
	public DeviceFaceInfoVo(DeviceFaceInfo deviceFaceInfo) {
		this.imeiNo = deviceFaceInfo.getImeiNo();
		this.examProvince = deviceFaceInfo.getExamProvince();
		this.examProvinceName = deviceFaceInfo.getExamProvinceName();
		this.examArea = deviceFaceInfo.getExamArea();
		this.examAreaName = deviceFaceInfo.getExamAreaName();
		this.examPlace = deviceFaceInfo.getExamPlace();
		this.examPlaceName = deviceFaceInfo.getExamPlaceName();
		this.createDate = deviceFaceInfo.getCreateDate();
		this.modifyDate = deviceFaceInfo.getModifyDate();
		this.verifyPrecision = deviceFaceInfo.getVerifyPrecision();
		this.filename = deviceFaceInfo.getFilename();
		this.subject = deviceFaceInfo.getSubject();
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
		return this.examPlace;
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

}