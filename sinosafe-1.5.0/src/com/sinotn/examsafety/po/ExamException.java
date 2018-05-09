package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * ExamException entity. @author MyEclipse Persistence Tools
 */

public class ExamException implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5760301484850701917L;
	private String exceptionId;
	private BaseCode baseCode;
	private String exceptionName;
	private String exceptionDescript;
	private String photoPath;
	private Date createDate;
	private String createIp;
	private String alterUserName;
	private String examPlaceName;
	private Long linkManId;
	private String loginAddr;
	private String loginLat;
	private String loginLng;
	private Long examPlaceId;

	// Constructors

	/** default constructor */
	public ExamException() {
	}

	/** minimal constructor */
	public ExamException(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	/** full constructor */
	public ExamException(String exceptionId, BaseCode baseCode,
			String exceptionName, String exceptionDescript, String photoPath,
			Date createDate, String createIp, String alterUserName,
			String examPlaceName,Long linkManId,String loginAddr
			,String loginLat,String loginLng, Long examPlaceId) {
		this.exceptionId = exceptionId;
		this.baseCode = baseCode;
		this.exceptionName = exceptionName;
		this.exceptionDescript = exceptionDescript;
		this.photoPath = photoPath;
		this.createDate = createDate;
		this.createIp = createIp;
		this.alterUserName = alterUserName;
		this.examPlaceName = examPlaceName;
		this.linkManId = linkManId;
		this.loginAddr= loginAddr;
		this.loginLat = loginLat;
		this.loginLng = loginLng;
		this.examPlaceId = examPlaceId;
	}

	// Property accessors

	public String getExceptionId() {
		return this.exceptionId;
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	public BaseCode getBaseCode() {
		return this.baseCode;
	}

	public void setBaseCode(BaseCode baseCode) {
		this.baseCode = baseCode;
	}

	public String getExceptionName() {
		return this.exceptionName;
	}

	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	public String getExceptionDescript() {
		return this.exceptionDescript;
	}

	public void setExceptionDescript(String exceptionDescript) {
		this.exceptionDescript = exceptionDescript;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateIp() {
		return this.createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public String getAlterUserName() {
		return this.alterUserName;
	}

	public void setAlterUserName(String alterUserName) {
		this.alterUserName = alterUserName;
	}

	public String getExamPlaceName() {
		return this.examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public Long getLinkManId() {
		return linkManId;
	}

	public void setLinkManId(Long linkManId) {
		this.linkManId = linkManId;
	}

	public String getLoginAddr() {
		return loginAddr;
	}

	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}

	public String getLoginLat() {
		return loginLat;
	}

	public void setLoginLat(String loginLat) {
		this.loginLat = loginLat;
	}

	public String getLoginLng() {
		return loginLng;
	}

	public void setLoginLng(String loginLng) {
		this.loginLng = loginLng;
	}

	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	@Override
	public String toString() {
		return "ExamException [exceptionId=" + exceptionId + ", baseCode="
				+ baseCode + ", exceptionName=" + exceptionName
				+ ", exceptionDescript=" + exceptionDescript + ", photoPath="
				+ photoPath + ", createDate=" + createDate + ", createIp="
				+ createIp + ", alterUserName=" + alterUserName
				+ ", examPlaceName=" + examPlaceName + ", linkManId="
				+ linkManId + ", loginAddr=" + loginAddr + ", loginLat="
				+ loginLat + ", loginLng=" + loginLng + ", examPlaceId="
				+ examPlaceId + "]";
	}

}