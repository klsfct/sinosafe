package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.PaperMove;
import com.sinotn.examsafety.service.ConstantsService;

/**
 * PaperMove entity. @author MyEclipse Persistence Tools
 */

public class PaperMoveVo implements java.io.Serializable {

	// Fields

	private String id;
	private ExamPlaceVo examPlace;
	private String subjectName;
	private String moveType;
	private Long linkManId;
	private String linkManName;
	private Date careateDate;
	private String moveLat;
	private String moveLng;
	private String moveAddr;
	private String moveTypeName;

	// Constructors

	/** default constructor */
	public PaperMoveVo() {
	}

	/** minimal constructor */
	public PaperMoveVo(String id) {
		this.id = id;
	}

	/** full constructor */
	public PaperMoveVo(String id, ExamPlaceVo examPlace, String subjectName,
			String moveType, Long linkManId, String linkManName,String moveTypeName,
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
		this.moveTypeName = moveTypeName;
	}
	
	public PaperMoveVo(PaperMove paperMove) {
		this.id = paperMove.getId();
		if(paperMove.getExamPlace() != null){
			this.examPlace = new ExamPlaceVo(paperMove.getExamPlace());
		}
		this.subjectName = paperMove.getSubjectName();
		this.moveType = paperMove.getMoveType();
		this.linkManId = paperMove.getLinkManId();
		this.linkManName = paperMove.getLinkManName();
		this.careateDate = paperMove.getCareateDate();
		this.moveLat = paperMove.getMoveLat();
		this.moveLng = paperMove.getMoveLng();
		this.moveAddr =paperMove.getMoveAddr();
		this.moveTypeName = paperMove.getMoveType();
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExamPlaceVo getExamPlace() {
		return this.examPlace;
	}

	public void setExamPlace(ExamPlaceVo examPlace) {
		this.examPlace = examPlace;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getMoveType() {
		return moveType;
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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

	public String getMoveTypeName() {
		return ConstantsService.getMoveTypeMap().get(moveTypeName);
	}

	public void setMoveTypeName(String moveTypeName) {
		this.moveTypeName = moveTypeName;
	}


}