package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.ExamineePunish;

/**
 * ExamineePunishVo entity. @author MyEclipse Persistence Tools
 */

public class ExamineePunishVo {

	// Fields

	private String id;
	private ExamineeVo examineeVo;
	private String examYear;
	private String subject;
	private String punishActionName;
	private Date createDate;
	private String punishName;
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
	public ExamineePunishVo() {
	}

	/** minimal constructor */
	public ExamineePunishVo(String id) {
		this.id = id;
	}

	/** full constructor */
	public ExamineePunishVo(String id, ExamineeVo examineeVo, String examYear,
			String subject, String punishActionName, String punishName,
			Date createDate, String createIp, String alterUserName,
			String remarks, String photoPath, Long linkManId,
			String loginAddr, String loginLat, String loginLng) {
		this.id = id;
		this.examineeVo = examineeVo;
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
	
	public ExamineePunishVo(ExamineePunish examineePunish) {
		this.id = examineePunish.getId();
		this.examineeVo = new ExamineeVo(examineePunish.getExaminee());
		this.examYear = examineePunish.getExamYear();
		this.subject = examineePunish.getSubject();
		this.punishActionName = examineePunish.getPunishActionName();
		this.punishName = examineePunish.getPunishName();
		this.createDate = examineePunish.getCreateDate();
		this.createIp = examineePunish.getCreateIp();
		this.alterUserName = examineePunish.getAlterUserName();
		this.remarks = examineePunish.getRemarks();
		this.photoPath = examineePunish.getPhotoPath();
		this.linkManId = examineePunish.getLinkManId();
		this.loginAddr = examineePunish.getLoginAddr();
		this.loginLat = examineePunish.getLoginLat();
		this.loginLng = examineePunish.getLoginLng();
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExamineeVo getExaminee() {
		return this.examineeVo;
	}

	public void setExaminee(ExamineeVo examineeVo) {
		this.examineeVo = examineeVo;
	}

	public String getExamYear() {
		return this.examYear;
	}

	public ExamineeVo getExamineeVo() {
		return examineeVo;
	}

	public void setExamineeVo(ExamineeVo examineeVo) {
		this.examineeVo = examineeVo;
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

	@JSON(format="yyyy-MM-dd HH:mm:ss")
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

}