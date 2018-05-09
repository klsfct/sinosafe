package com.sinotn.examsafety.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ResultExaminee entity. @author MyEclipse Persistence Tools
 */

public class ResultExaminee implements java.io.Serializable {

	// Fields

	private Long id;
	private Examinee examinee;
	private String subject;
	private String imeiNo;
	private String relativePath;
	private String absolutePath;
	private Date createDate;
	private Date modifyDate;
	private Integer passType;
	private Integer photoResult;
	private Integer syspass;
	private Integer specialPass;
	private Boolean isVerify;
	private Date verifyDate;
	private String verifyResult;
	private Boolean isException;
	private String faceExamineeName;
	private String faceIndetityDate;
	private String filename;
	private Boolean isProcess;
	private Integer photoPrecision;
	private String examProvince;
	private String examProvinceName;
	private String examArea;
	private String examAreaName;
	private Long examPlace;
	private String examPlaceName;
	private Long examRoom;
	private Byte seatNumber;

	// Constructors

	/** default constructor */
	public ResultExaminee() {
	}

	/** minimal constructor */
	public ResultExaminee(Long id, Examinee examinee, String subject,
			Integer passType, Boolean isException) {
		this.id = id;
		this.examinee = examinee;
		this.subject = subject;
		this.passType = passType;
		this.isException = isException;
	}

	/** full constructor */
	public ResultExaminee(Long id, Examinee examinee, String subject,
			String imeiNo, String relativePath, String absolutePath,
			Date createDate, Date modifyDate, Integer passType,
			Integer photoResult, Integer syspass, Integer specialPass,
			Boolean isVerify, Date verifyDate, String verifyResult,
			Boolean isException, String faceExamineeName,
			String faceIndetityDate, String filename, Boolean isProcess,
			Integer photoPrecision, String examProvince,
			String examProvinceName, String examArea, String examAreaName,
			Long examPlace, String examPlaceName,Long examRoom,Byte seatNumber) {
		this.id = id;
		this.examinee = examinee;
		this.subject = subject;
		this.imeiNo = imeiNo;
		this.relativePath = relativePath;
		this.absolutePath = absolutePath;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.passType = passType;
		this.photoResult = photoResult;
		this.syspass = syspass;
		this.specialPass = specialPass;
		this.isVerify = isVerify;
		this.verifyDate = verifyDate;
		this.verifyResult = verifyResult;
		this.isException = isException;
		this.faceExamineeName = faceExamineeName;
		this.faceIndetityDate = faceIndetityDate;
		this.filename = filename;
		this.isProcess = isProcess;
		this.photoPrecision = photoPrecision;
		this.examProvince = examProvince;
		this.examProvinceName = examProvinceName;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
		this.examPlace = examPlace;
		this.examPlaceName = examPlaceName;
		this.examRoom = examRoom;
		this.seatNumber = seatNumber;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Examinee getExaminee() {
		return this.examinee;
	}

	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public Boolean getIsVerify() {
		return this.isVerify;
	}

	public void setIsVerify(Boolean isVerify) {
		this.isVerify = isVerify;
	}

	public Date getVerifyDate() {
		return this.verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getVerifyResult() {
		return this.verifyResult;
	}

	public void setVerifyResult(String verifyResult) {
		this.verifyResult = verifyResult;
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

	public Boolean getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Boolean isProcess) {
		this.isProcess = isProcess;
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

	public Long getExamRoom() {
		return examRoom;
	}

	public void setExamRoom(Long examRoom) {
		this.examRoom = examRoom;
	}

	public Byte getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Byte seatNumber) {
		this.seatNumber = seatNumber;
	}

}