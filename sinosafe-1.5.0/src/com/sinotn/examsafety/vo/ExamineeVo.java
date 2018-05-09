package com.sinotn.examsafety.vo;

import java.util.Date;

import com.sinotn.examsafety.po.Examinee;

public class ExamineeVo  {

	// Fields

	private String licence;
	private String examYear;
	private String examProvince;
	private String examProvinceName;
	private String examRegister;
	private String examRegisterName;
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
	private String photoPath;
	private String absolutePath;
	private String character;
	
	private Long examineeId; 
	private String phone;
	private String nationality;
	private String party;
	private String practition;
	private String learing;
	private String identityType;
	private String collegeName;
	private String registerProvince;
	private String registerCity;
	private String registerCounty;
	private Date signDate;
	private String waitCheckReason;
	private String professional;
	private String graduation;
	private String workUnit;
	private String postalAddressName;

	// Constructors

	/** default constructor */
	public ExamineeVo() {
	}

	/** full constructor */
	public ExamineeVo(Examinee examinee) {
		this.licence = examinee.getLicence();
		this.examYear = examinee.getExamYear();
		this.examProvince = examinee.getExamProvince();
		this.examProvinceName = examinee.getExamPlaceName();
		this.examRegister = examinee.getExamRegister();
		this.examRegisterName = examinee.getExamRegisterName();
		this.examArea = examinee.getExamArea();
		this.examAreaName = examinee.getExamAreaName();
		this.examPlace = examinee.getExamPlace();
		this.examPlaceName = examinee.getExamPlaceName();
		this.examRoom = examinee.getExamRoom();
		this.seatNumber = examinee.getSeatNumber();
		this.examineeName = examinee.getExamineeName();
		this.sex = examinee.getSex();
		this.birthday = examinee.getBirthday();
		this.identity = examinee.getIdentity();
		this.photoPath = examinee.getPhotoPath();
		this.absolutePath = examinee.getAbsolutePath();
		this.character = examinee.getCharacter();
		this.examineeId = examinee.getExamineeId(); 
		this.phone = examinee.getPhone();
		this.nationality = examinee.getNationality();
		this.party = examinee.getParty();
		this.practition = examinee.getPractition();
		this.learing = examinee.getLearing();
		this.identityType = examinee.getIdentityType();
		this.collegeName = examinee.getCollegeName();
		this.registerProvince = examinee.getRegisterProvince();
		this.registerCity = examinee.getRegisterCity();
		this.registerCounty = examinee.getRegisterCounty();
		this.signDate = examinee.getSignDate();
		this.waitCheckReason = examinee.getWaitCheckReason();
		this.professional = examinee.getProfessional();
		this.graduation = examinee.getGraduation();
		this.workUnit = examinee.getWorkUnit();
		this.postalAddressName = examinee.getPostalAddressName();
	}

	// Property accessors

	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getExamYear() {
		return this.examYear;
	}

	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}

	public String getExamProvinceName() {
		return this.examProvinceName;
	}

	public void setExamProvinceName(String examProvinceName) {
		this.examProvinceName = examProvinceName;
	}

	public String getExamRegisterName() {
		return this.examRegisterName;
	}

	public void setExamRegisterName(String examRegisterName) {
		this.examRegisterName = examRegisterName;
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

	public Long getExamRoom() {
		return this.examRoom;
	}

	public void setExamRoom(Long examRoom) {
		this.examRoom = examRoom;
	}

	public Byte getSeatNumber() {
		return this.seatNumber;
	}

	public void setSeatNumber(Byte seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getExamineeName() {
		return this.examineeName;
	}

	public void setExamineeName(String examineeName) {
		this.examineeName = examineeName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getAbsolutePath() {
		return this.absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getExamProvince() {
		return examProvince;
	}

	public void setExamProvince(String examProvince) {
		this.examProvince = examProvince;
	}

	public String getExamRegister() {
		return examRegister;
	}

	public void setExamRegister(String examRegister) {
		this.examRegister = examRegister;
	}

	public String getExamArea() {
		return examArea;
	}

	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}

	public Long getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(Long examPlace) {
		this.examPlace = examPlace;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public Long getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(Long examineeId) {
		this.examineeId = examineeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLearing() {
		return learing;
	}

	public void setLearing(String learing) {
		this.learing = learing;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getRegisterProvince() {
		return registerProvince;
	}

	public void setRegisterProvince(String registerProvince) {
		this.registerProvince = registerProvince;
	}

	public String getRegisterCity() {
		return registerCity;
	}

	public void setRegisterCity(String registerCity) {
		this.registerCity = registerCity;
	}

	public String getRegisterCounty() {
		return registerCounty;
	}

	public void setRegisterCounty(String registerCounty) {
		this.registerCounty = registerCounty;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getWaitCheckReason() {
		return waitCheckReason;
	}

	public void setWaitCheckReason(String waitCheckReason) {
		this.waitCheckReason = waitCheckReason;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getPostalAddressName() {
		return postalAddressName;
	}

	public void setPostalAddressName(String postalAddressName) {
		this.postalAddressName = postalAddressName;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getPractition() {
		return practition;
	}

	public void setPractition(String practition) {
		this.practition = practition;
	}

}