package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * ExamineePunish entity. @author MyEclipse Persistence Tools
 */

public class ExamineePunish implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3209890866873616975L;
	private String id;
	private Examinee examinee;
	private String examYear;
	private String subject;
	private String punishActionName;
	private String punishName;
	private Date createDate;
	private String createIp;
	private String alterUserName;
	private String remarks;
	private String photoPath;
	private Long linkManId;
	private String loginAddr;
	private String loginLat;
	private String loginLng;

	// Constructors

	/** default constructor */
	public ExamineePunish() {
	}

	/** minimal constructor */
	public ExamineePunish(String id) {
		this.id = id;
	}

	/** full constructor */
	public ExamineePunish(String id, Examinee examinee, String examYear,
			String subject, String punishActionName, String punishName,
			Date createDate, String createIp, String alterUserName,
			String remarks, String photoPath, Long linkManId,
			String loginAddr, String loginLat, String loginLng) {
		this.id = id;
		this.examinee = examinee;
		this.examYear = examYear;
		this.subject = subject;
		this.punishActionName = punishActionName;
		this.punishName = punishName;
		this.createDate = createDate;
		this.createIp = createIp;
		this.alterUserName = alterUserName;
		this.remarks = remarks;
		this.photoPath = photoPath;
		this.linkManId = linkManId;
		this.loginAddr = loginAddr;
		this.loginLat = loginLat;
		this.loginLng = loginLng;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Examinee getExaminee() {
		return this.examinee;
	}

	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}

	public String getExamYear() {
		return this.examYear;
	}

	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPunishActionName() {
		return this.punishActionName;
	}

	public void setPunishActionName(String punishActionName) {
		this.punishActionName = punishActionName;
	}

	public String getPunishName() {
		return this.punishName;
	}

	public void setPunishName(String punishName) {
		this.punishName = punishName;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Long getLinkManId() {
		return this.linkManId;
	}

	public void setLinkManId(Long linkManId) {
		this.linkManId = linkManId;
	}

	public String getLoginAddr() {
		return this.loginAddr;
	}

	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}

	public String getLoginLat() {
		return this.loginLat;
	}

	public void setLoginLat(String loginLat) {
		this.loginLat = loginLat;
	}

	public String getLoginLng() {
		return this.loginLng;
	}

	public void setLoginLng(String loginLng) {
		this.loginLng = loginLng;
	}

	@Override
	public String toString() {
		return "ExamineePunish [id=" + id + ", examinee=" + examinee
				+ ", examYear=" + examYear + ", subject=" + subject
				+ ", punishActionName=" + punishActionName + ", punishName="
				+ punishName + ", createDate=" + createDate + ", createIp="
				+ createIp + ", alterUserName=" + alterUserName + ", remarks="
				+ remarks + ", photoPath=" + photoPath + ", linkManId="
				+ linkManId + ", loginAddr=" + loginAddr + ", loginLat="
				+ loginLat + ", loginLng=" + loginLng + "]";
	}

}