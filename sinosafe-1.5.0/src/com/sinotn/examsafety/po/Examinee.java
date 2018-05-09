package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * Examinee entity. @author MyEclipse Persistence Tools
 */

public class Examinee implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5875474504059999599L;
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
	private Long examRoomCbt;
	private String examRoomNameCbt;
	private Byte seatNumberCbt;

	
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
	public Examinee() {
	}

	/** minimal constructor */
	public Examinee(String licence) {
		this.licence = licence;
	}

	/** full constructor */
	public Examinee(String licence, String examYear, String examProvince,
			String examProvinceName, String examRegister,
			String examRegisterName, String examArea, String examAreaName,
			Long examPlace, String examPlaceName, Long examRoom,
			Byte seatNumber, String examineeName, String sex, String birthday,
			String identity, String photoPath, String absolutePath,
			String character, Long examRoomCbt, String examRoomNameCbt,
			Byte seatNumberCbt, Long examineeId, String phone,
			String nationality, String party, String practition,
			String learing, String identityType, String collegeName,
			String registerProvince, String registerCity,
			String registerCounty, Date signDate, String waitCheckReason,
			String professional, String graduation, String workUnit,
			String postalAddressName) {
		super();
		this.licence = licence;
		this.examYear = examYear;
		this.examProvince = examProvince;
		this.examProvinceName = examProvinceName;
		this.examRegister = examRegister;
		this.examRegisterName = examRegisterName;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
		this.examPlace = examPlace;
		this.examPlaceName = examPlaceName;
		this.examRoom = examRoom;
		this.seatNumber = seatNumber;
		this.examineeName = examineeName;
		this.sex = sex;
		this.birthday = birthday;
		this.identity = identity;
		this.photoPath = photoPath;
		this.absolutePath = absolutePath;
		this.character = character;
		this.examRoomCbt = examRoomCbt;
		this.examRoomNameCbt = examRoomNameCbt;
		this.seatNumberCbt = seatNumberCbt;
		this.examineeId = examineeId;
		this.phone = phone;
		this.nationality = nationality;
		this.party = party;
		this.practition = practition;
		this.learing = learing;
		this.identityType = identityType;
		this.collegeName = collegeName;
		this.registerProvince = registerProvince;
		this.registerCity = registerCity;
		this.registerCounty = registerCounty;
		this.signDate = signDate;
		this.waitCheckReason = waitCheckReason;
		this.professional = professional;
		this.graduation = graduation;
		this.workUnit = workUnit;
		this.postalAddressName = postalAddressName;
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

	public Long getExamRoomCbt() {
		return examRoomCbt;
	}

	public void setExamRoomCbt(Long examRoomCbt) {
		this.examRoomCbt = examRoomCbt;
	}

	public String getExamRoomNameCbt() {
		return examRoomNameCbt;
	}

	public void setExamRoomNameCbt(String examRoomNameCbt) {
		this.examRoomNameCbt = examRoomNameCbt;
	}

	public Byte getSeatNumberCbt() {
		return seatNumberCbt;
	}

	public void setSeatNumberCbt(Byte seatNumberCbt) {
		this.seatNumberCbt = seatNumberCbt;
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

	@Override
	public String toString() {
		return "Examinee [licence=" + licence + ", examYear=" + examYear
				+ ", examProvince=" + examProvince + ", examProvinceName="
				+ examProvinceName + ", examRegister=" + examRegister
				+ ", examRegisterName=" + examRegisterName + ", examArea="
				+ examArea + ", examAreaName=" + examAreaName + ", examPlace="
				+ examPlace + ", examPlaceName=" + examPlaceName
				+ ", examRoom=" + examRoom + ", seatNumber=" + seatNumber
				+ ", examineeName=" + examineeName + ", sex=" + sex
				+ ", birthday=" + birthday + ", identity=" + identity
				+ ", photoPath=" + photoPath + ", absolutePath=" + absolutePath
				+ ", character=" + character + ", examRoomCbt=" + examRoomCbt
				+ ", examRoomNameCbt=" + examRoomNameCbt + ", seatNumberCbt="
				+ seatNumberCbt + ", examineeId=" + examineeId + ", phone="
				+ phone + ", nationality=" + nationality + ", party=" + party
				+ ", practition=" + practition + ", learing=" + learing
				+ ", identityType=" + identityType + ", collegeName="
				+ collegeName + ", registerProvince=" + registerProvince
				+ ", registerCity=" + registerCity + ", registerCounty="
				+ registerCounty + ", signDate=" + signDate
				+ ", waitCheckReason=" + waitCheckReason + ", professional="
				+ professional + ", graduation=" + graduation + ", workUnit="
				+ workUnit + ", postalAddressName=" + postalAddressName + "]";
	}
	
	

}