package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * PaperMove entity. @author MyEclipse Persistence Tools
 */

public class PaperMove implements java.io.Serializable {

	// Fields

	private String id;
	private ExamPlace examPlace;
	private String subjectName;
	private String moveType;
	private Long linkManId;
	private String linkManName;
	private Date careateDate;
	private String moveLat;
	private String moveLng;
	private String moveAddr;

	// Constructors

	/** default constructor */
	public PaperMove() {
	}

	/** minimal constructor */
	public PaperMove(String id) {
		this.id = id;
	}

	/** full constructor */
	public PaperMove(String id, ExamPlace examPlace, String subjectName,
			String moveType, Long linkManId, String linkManName,
			Date careateDate, String moveLat, String moveLng, String moveAddr) {
		this.id = id;
		this.examPlace = examPlace;
		this.subjectName = subjectName;
		this.moveType = moveType;
		this.linkManId = linkManId;
		this.linkManName = linkManName;
		this.careateDate = careateDate;
		this.moveLat = moveLat;
		this.moveLng = moveLng;
		this.moveAddr = moveAddr;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExamPlace getExamPlace() {
		return this.examPlace;
	}

	public void setExamPlace(ExamPlace examPlace) {
		this.examPlace = examPlace;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getMoveType() {
		return this.moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public Long getLinkManId() {
		return this.linkManId;
	}

	public void setLinkManId(Long linkManId) {
		this.linkManId = linkManId;
	}

	public String getLinkManName() {
		return this.linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public Date getCareateDate() {
		return this.careateDate;
	}

	public void setCareateDate(Date careateDate) {
		this.careateDate = careateDate;
	}

	public String getMoveLat() {
		return this.moveLat;
	}

	public void setMoveLat(String moveLat) {
		this.moveLat = moveLat;
	}

	public String getMoveLng() {
		return this.moveLng;
	}

	public void setMoveLng(String moveLng) {
		this.moveLng = moveLng;
	}

	public String getMoveAddr() {
		return this.moveAddr;
	}

	public void setMoveAddr(String moveAddr) {
		this.moveAddr = moveAddr;
	}

}