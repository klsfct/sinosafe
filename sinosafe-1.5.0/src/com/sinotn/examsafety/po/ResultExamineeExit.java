package com.sinotn.examsafety.po;

import java.util.Date;

@SuppressWarnings("serial")
public class ResultExamineeExit implements java.io.Serializable {
	private Long id;
	private String licence;
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
	private String identity;
	private String photoPath;

	// Constructors

	/** default constructor */
	public ResultExamineeExit() {
	}

	/** minimal constructor */
	public ResultExamineeExit(Long id) {
		this.id = id;
	}

	/** full constructor */
	public ResultExamineeExit(String licence, String subject, String imeiNo,
			String relativePath, String absolutePath, Date createDate,
			Date modifyDate, Integer photoResult, Boolean isPass,
			String faceExamineeName, String faceIndetityDate, String filename,
			Integer photoPrecision, String examProvince,
			String examProvinceName, String examArea, String examAreaName,
			Long examPlace, String examPlaceName, Long examRoom,
			Byte seatNumber, Long id,String examineeName,String identity,String photoPath) {
		this.licence = licence;
		this.subject = subject;
		this.imeiNo = imeiNo;
		this.relativePath = relativePath;
		this.absolutePath = absolutePath;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.photoResult = photoResult;
		this.isPass = isPass;
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
		this.examRoom = examRoom;
		this.seatNumber = seatNumber;
		this.id = id;
		this.examineeName = examineeName;
		this.identity = identity;
		this.photoPath = photoPath;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
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

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExamineeName() {
		return examineeName;
	}

	public void setExamineeName(String examineeName) {
		this.examineeName = examineeName;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

}