package com.sinotn.examsafety.vo;

import java.io.File;
import java.util.Date;

import com.sinotn.examsafety.po.ResultExaminee;

public class ResultExamineeVo {

	// Fields

	private Long id;
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
	private String examineeName;
	private String sex;
	private String birthday;
	private String identity;
	private String licence;
	private File upload;
	private String uploadFileName;
	// Constructors

	/** default constructor */
	public ResultExamineeVo() {
	}

	/** full constructor */
	public ResultExamineeVo(ResultExaminee resultExaminee) {
		this.id = resultExaminee.getId();
		this.subject = resultExaminee.getSubject();
		this.imeiNo = resultExaminee.getImeiNo();
		this.relativePath = resultExaminee.getRelativePath();
		this.absolutePath = resultExaminee.getExaminee().getPhotoPath();
		this.createDate = resultExaminee.getCreateDate();
		this.modifyDate = resultExaminee.getModifyDate();
		this.passType = resultExaminee.getPassType();
		this.photoResult = resultExaminee.getPhotoResult();
		this.syspass = resultExaminee.getSyspass();
		this.specialPass = resultExaminee.getSpecialPass();
		this.isVerify = resultExaminee.getIsVerify();
		this.verifyDate = resultExaminee.getVerifyDate();
		this.verifyResult = resultExaminee.getVerifyResult();
		this.isException = resultExaminee.getIsException();
		this.faceExamineeName = resultExaminee.getFaceExamineeName();
		this.faceIndetityDate = resultExaminee.getFaceIndetityDate();
		this.filename = resultExaminee.getFilename();
		this.isProcess = resultExaminee.getIsProcess();
		this.photoPrecision = resultExaminee.getPhotoPrecision();
		this.examProvince = resultExaminee.getExamProvince();
		this.examProvinceName = resultExaminee.getExamProvinceName();
		this.examArea = resultExaminee.getExamArea();
		this.examAreaName = resultExaminee.getExamAreaName();
		this.examPlace = resultExaminee.getExamPlace();
		this.examPlaceName = resultExaminee.getExamPlaceName();
		this.examRoom = resultExaminee.getExamRoom();
		this.seatNumber = resultExaminee.getSeatNumber();
		this.examineeName = resultExaminee.getExaminee().getExamineeName();
		this.identity = resultExaminee.getExaminee().getIdentity();
		this.licence = resultExaminee.getExaminee().getLicence();
		this.sex = resultExaminee.getExaminee().getSex();
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		String type = this.subject;
		if(type.equals("1")){
			return "场次一";
		}
		else if(type.equals("2")){
			return "场次二";
		}
		else if(type.equals("3")){
			return "场次三";
		}
		else if(type.equals("4")){
			return "试卷四";
		}
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

}