package com.sinotn.examsafety.vo;

import java.io.File;
import java.util.Date;

import com.sinotn.examsafety.po.ResultExamineeExit;

public class ResultExamineeExitVo {

	// Fields

	private Long id;
	private String subject;
	private String imeiNo;
	private String relativePath;
	private String absolutePath;
	private Date createDate;
	private Date modifyDate;
	private Integer photoResult;
	private Boolean isPass;
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
	private Long examRoom;
	private Byte seatNumber;
	private String examineeName;
	private String sex;
	private String birthday;
	private String identity;
	private String licence;
	private File upload;
	private String uploadFileName;
	private String verifyResult;
	private Boolean isVerify;
	private Date verifyDate;
	private String photoPath;
	// Constructors

	/** default constructor */
	public ResultExamineeExitVo() {
	}

	/** full constructor */
	public ResultExamineeExitVo(ResultExamineeExit resultExamineeExit) {
		this.id = resultExamineeExit.getId();
		this.subject = resultExamineeExit.getSubject();
		this.imeiNo = resultExamineeExit.getImeiNo();
		this.relativePath = resultExamineeExit.getRelativePath();
		this.absolutePath = resultExamineeExit.getAbsolutePath();
		this.createDate = resultExamineeExit.getCreateDate();
		this.modifyDate = resultExamineeExit.getModifyDate();
		this.photoResult = resultExamineeExit.getPhotoResult();
		this.isPass = resultExamineeExit.getIsPass();
		this.faceExamineeName = resultExamineeExit.getFaceExamineeName();
		this.faceIndetityDate = resultExamineeExit.getFaceIndetityDate();
		this.filename = resultExamineeExit.getFilename();
		this.photoPrecision = resultExamineeExit.getPhotoPrecision();
		this.examProvince = resultExamineeExit.getExamProvince();
		this.examProvinceName = resultExamineeExit.getExamProvinceName();
		this.examArea = resultExamineeExit.getExamArea();
		this.examAreaName = resultExamineeExit.getExamAreaName();
		this.examPlace = resultExamineeExit.getExamPlace();
		this.examPlaceName = resultExamineeExit.getExamPlaceName();
		this.examRoom = resultExamineeExit.getExamRoom();
		this.seatNumber = resultExamineeExit.getSeatNumber();
		this.examineeName = resultExamineeExit.getExamineeName();
		this.identity = resultExamineeExit.getIdentity();
		this.photoPath = resultExamineeExit.getPhotoPath();
		this.licence = resultExamineeExit.getLicence();
	}

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


	public Integer getPhotoResult() {
		return this.photoResult;
	}

	public void setPhotoResult(Integer photoResult) {
		this.photoResult = photoResult;
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

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
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

	public String getExamineeName() {
		return examineeName;
	}

	public void setExamineeName(String examineeName) {
		this.examineeName = examineeName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getVerifyResult() {
		return verifyResult;
	}

	public void setVerifyResult(String verifyResult) {
		this.verifyResult = verifyResult;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public Boolean getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Boolean isVerify) {
		this.isVerify = isVerify;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

}