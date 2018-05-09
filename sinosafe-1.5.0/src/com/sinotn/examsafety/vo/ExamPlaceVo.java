package com.sinotn.examsafety.vo;

import com.sinotn.examsafety.po.ExamPlace;



public class ExamPlaceVo  {

	// Fields

	private Long examPlace;
	private String examYear;
	private String examProvince;
	private String examProvinceName;
	private String examArea;
	private String examAreaName;
	private String examPlaceName;
	private Boolean isEnabled;
	private Integer examineeSum;
	private String examAddr;
	private String pointLng;
	private String pointLat;
	private String linkManPhone;
	private String linkManName;
	private String point;
	// Constructors

	/** default constructor */
	public ExamPlaceVo() {
	}

	/** minimal constructor */
	public ExamPlaceVo(Long examPlace) {
		this.examPlace = examPlace;
	}

	/** full constructor */
	public ExamPlaceVo(ExamPlace examPlace) {
		this.examPlace = examPlace.getExamPlace();
		this.examYear = examPlace.getExamYear();
		this.examProvince = examPlace.getExamProvince();
		this.examProvinceName = examPlace.getExamProvinceName();
		this.examArea = examPlace.getExamArea();
		this.examAreaName = examPlace.getExamAreaName();
		this.examPlaceName = examPlace.getExamPlaceName();
		this.isEnabled = examPlace.getIsEnabled();
		this.examineeSum = examPlace.getExamineeSum();
		this.examAddr = examPlace.getExamAddr();
		this.pointLng = examPlace.getPointLng();
		this.pointLat = examPlace.getPointLat();
		this.linkManName = examPlace.getLinkManName();
		this.linkManPhone = examPlace.getLinkManPhone();
		this.point = examPlace.getPointLng() + "," + examPlace.getPointLat();
	}

	public Long getExamPlace() {
		return this.examPlace;
	}

	public void setExamPlace(Long examPlace) {
		this.examPlace = examPlace;
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

	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getExamineeSum() {
		return this.examineeSum;
	}

	public void setExamineeSum(Integer examineeSum) {
		this.examineeSum = examineeSum;
	}

	public String getExamProvince() {
		return examProvince;
	}

	public void setExamProvince(String examProvince) {
		this.examProvince = examProvince;
	}

	public String getExamArea() {
		return examArea;
	}

	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}

	public String getExamAddr() {
		return examAddr;
	}

	public void setExamAddr(String examAddr) {
		this.examAddr = examAddr;
	}

	public String getPointLng() {
		return pointLng;
	}

	public void setPointLng(String pointLng) {
		this.pointLng = pointLng;
	}

	public String getPointLat() {
		return pointLat;
	}

	public void setPointLat(String pointLat) {
		this.pointLat = pointLat;
	}

	public String getLinkManPhone() {
		return linkManPhone;
	}

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
}