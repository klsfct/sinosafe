package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * SocketExaminee entity. @author MyEclipse Persistence Tools
 */

public class SocketExaminee2 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -16448291494373968L;
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
	private Boolean isPass;
	private Boolean isProcess;
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
	// Constructors

	/** default constructor */
	public SocketExaminee2() {
	}

	/** minimal constructor */
	public SocketExaminee2(Long id, String subject, String licence,
			Integer passType, Boolean isException) {
		this.id = id;
		this.subject = subject;
		this.licence = licence;
		this.passType = passType;
		this.isException = isException;
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

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}

	public Boolean getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Boolean isProcess) {
		this.isProcess = isProcess;
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


	public SocketExaminee2(Long id, String subject, String licence,
			String imeiNo, String relativePath, String absolutePath,
			Date createDate, Date modifyDate, Integer passType,
			Integer photoResult, Boolean isPass, Boolean isProcess,
			Boolean isException, String faceExamineeName,
			String faceIndetityDate, String filename, Integer photoPrecision,
			String examProvince, String examProvinceName, String examArea,
			String examAreaName, Long examPlace, String examPlaceName) {
		super();
		this.id = id;
		this.subject = subject;
		this.licence = licence;
		this.imeiNo = imeiNo;
		this.relativePath = relativePath;
		this.absolutePath = absolutePath;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.passType = passType;
		this.photoResult = photoResult;
		this.isPass = isPass;
		this.isProcess = isProcess;
		this.isException = isException;
		this.faceExamineeName = faceExamineeName;
		this.faceIndetityDate = faceIndetityDate;
		this.filename = filename;
		this.photoPrecision = photoPrecision;
		this.examProvince = examProvince;
		this.examProvinceName = examProvinceName;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
		this.examPlace = examPlace;
		this.examPlaceName = examPlaceName;
	}

	@Override
	public String toString() {
		return "SocketExaminee2 [id=" + id + ", subject=" + subject
				+ ", licence=" + licence + ", imeiNo=" + imeiNo
				+ ", relativePath=" + relativePath + ", absolutePath="
				+ absolutePath + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", passType=" + passType + ", photoResult="
				+ photoResult + ", isPass=" + isPass + ", isProcess="
				+ isProcess + ", isException=" + isException
				+ ", faceExamineeName=" + faceExamineeName
				+ ", faceIndetityDate=" + faceIndetityDate + ", filename="
				+ filename + ", photoPrecision=" + photoPrecision
				+ ", examProvince=" + examProvince + ", examProvinceName="
				+ examProvinceName + ", examArea=" + examArea
				+ ", examAreaName=" + examAreaName + ", examPlace=" + examPlace
				+ ", examPlaceName=" + examPlaceName;
	}
	
}