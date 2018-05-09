package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.SocketExaminee;

/**
 * SocketExaminee entity. @author MyEclipse Persistence Tools
 */

public class SocketExamineeVo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3614909860315059539L;
	private Long id;
	private String subject;
	private String licence;
	private String imeiNo;
	private String relativePath;
	private String absolutePath;
	private Date createDate;
	private Date modifyDate;
	private Integer passType;
	private Integer photoResult;
	private Integer syspass;
	private Integer specialPass;
	private Boolean isException;
	private String faceExamineeName;
	private String faceIndetityDate;
	private String filename;
	private Integer photoPrecision;
	private String examProvince;
	private String examProvinceName;
	private String examArea;
	private String examAreaName;
	private Long examPlace;
	private String examPlaceName;
	private Boolean isPass;
	// Constructors

	/** default constructor */
	public SocketExamineeVo() {
	}

	/** minimal constructor */
	public SocketExamineeVo(Long id, String subject, String licence,
			Integer passType, Boolean isException) {
		this.id = id;
		this.subject = subject;
		this.licence = licence;
		this.passType = passType;
		this.isException = isException;
	}

	/** full constructor */
	public SocketExamineeVo(SocketExaminee socketExaminee) {
		this.id = socketExaminee.getId();
		this.subject = socketExaminee.getSubject();
		this.licence = socketExaminee.getLicence();
		this.imeiNo = socketExaminee.getImeiNo();
		this.relativePath = socketExaminee.getRelativePath();
		this.absolutePath = socketExaminee.getAbsolutePath();
		this.createDate = socketExaminee.getCreateDate();
		this.modifyDate = socketExaminee.getModifyDate();
		this.passType = socketExaminee.getPassType();
		this.photoResult = socketExaminee.getPhotoResult();
		this.isException = socketExaminee.getIsException();
		this.faceExamineeName = socketExaminee.getFaceExamineeName();
		this.faceIndetityDate = socketExaminee.getFaceIndetityDate();
		this.filename = socketExaminee.getFilename();
		this.photoPrecision = socketExaminee.getPhotoPrecision();
		this.examProvince = socketExaminee.getExamProvince();
		this.examProvinceName = socketExaminee.getExamProvinceName();
		this.examArea = socketExaminee.getExamArea();
		this.examAreaName = socketExaminee.getExamAreaName();
		this.examPlace = socketExaminee.getExamPlace();
		this.examPlaceName = socketExaminee.getExamPlaceName();
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getImeiNo() {
		return this.imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public String getRelativePath() {
		return this.relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getAbsolutePath() {
		return this.absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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

	public Integer getPassType() {
		return this.passType;
	}

	public void setPassType(Integer passType) {
		this.passType = passType;
	}

	public Integer getPhotoResult() {
		return this.photoResult;
	}

	public void setPhotoResult(Integer photoResult) {
		this.photoResult = photoResult;
	}

	public Integer getSyspass() {
		return this.syspass;
	}

	public void setSyspass(Integer syspass) {
		this.syspass = syspass;
	}

	public Integer getSpecialPass() {
		return this.specialPass;
	}

	public void setSpecialPass(Integer specialPass) {
		this.specialPass = specialPass;
	}

	public Boolean getIsException() {
		return this.isException;
	}

	public void setIsException(Boolean isException) {
		this.isException = isException;
	}

	public String getFaceExamineeName() {
		return this.faceExamineeName;
	}

	public void setFaceExamineeName(String faceExamineeName) {
		this.faceExamineeName = faceExamineeName;
	}

	public String getFaceIndetityDate() {
		return this.faceIndetityDate;
	}

	public void setFaceIndetityDate(String faceIndetityDate) {
		this.faceIndetityDate = faceIndetityDate;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getPhotoPrecision() {
		return photoPrecision;
	}

	public void setPhotoPrecision(Integer photoPrecision) {
		this.photoPrecision = photoPrecision;
	}

	public String getExamProvince() {
		return examProvince;
	}

	public void setExamProvince(String examProvince) {
		this.examProvince = examProvince;
	}

	public String getExamProvinceName() {
		return examProvinceName;
	}

	public void setExamProvinceName(String examProvinceName) {
		this.examProvinceName = examProvinceName;
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

	public Long getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(Long examPlace) {
		this.examPlace = examPlace;
	}

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	@Override
	public String toString() {
		return "SocketExamineeVo [id=" + id + ", subject=" + subject
				+ ", licence=" + licence + ", imeiNo=" + imeiNo
				+ ", relativePath=" + relativePath + ", absolutePath="
				+ absolutePath + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", passType=" + passType + ", photoResult="
				+ photoResult + ", syspass=" + syspass + ", specialPass="
				+ specialPass + ", isException=" + isException
				+ ", faceExamineeName=" + faceExamineeName
				+ ", faceIndetityDate=" + faceIndetityDate + ", filename="
				+ filename + ", photoPrecision=" + photoPrecision
				+ ", examProvince=" + examProvince + ", examProvinceName="
				+ examProvinceName + ", examArea=" + examArea
				+ ", examAreaName=" + examAreaName + ", examPlace=" + examPlace
				+ ", examPlaceName=" + examPlaceName + ", isPass=" + isPass
				+ "]";
	}

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}
	
}