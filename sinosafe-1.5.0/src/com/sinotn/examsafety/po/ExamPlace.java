package com.sinotn.examsafety.po;

import java.util.HashSet;
import java.util.Set;


/**
 * ExamPlace entity. @author MyEclipse Persistence Tools
 */

public class ExamPlace implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7622065553261318526L;
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
	private Set linkMans = new HashSet(0);
	// Constructors

	/** default constructor */
	public ExamPlace() {
	}

	/** minimal constructor */
	public ExamPlace(Long examPlace) {
		this.examPlace = examPlace;
	}

	/** full constructor */
	public ExamPlace(Long examPlace, String examYear,
			String examProvince, String examProvinceName, String examArea,
			String examAreaName, String examPlaceName, Boolean isEnabled,Set linkMans, 
			Integer examineeSum, String examAddr, String pointLng, String pointLat, 
			String linkManPhone, String linkManName) {
		this.examPlace = examPlace;
		this.examYear = examYear;
		this.examProvince = examProvince;
		this.examProvinceName = examProvinceName;
		this.examArea = examArea;
		this.examAreaName = examAreaName;
		this.examPlaceName = examPlaceName;
		this.isEnabled = isEnabled;
		this.examineeSum = examineeSum;
		this.examAddr = examAddr;
		this.pointLng = pointLng;
		this.pointLat = pointLat;
		this.linkMans = linkMans;
		this.linkManPhone = linkManPhone;
		this.linkManName = linkManName;
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

	public Set getLinkMans() {
		return linkMans;
	}

	public void setLinkMans(Set linkMans) {
		this.linkMans = linkMans;
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

	@Override
	public String toString() {
		return "ExamPlace [examPlace=" + examPlace + ", examYear=" + examYear
				+ ", examProvince=" + examProvince + ", examProvinceName="
				+ examProvinceName + ", examArea=" + examArea
				+ ", examAreaName=" + examAreaName + ", examPlaceName="
				+ examPlaceName + ", isEnabled=" + isEnabled + ", examineeSum="
				+ examineeSum + ", examAddr=" + examAddr + ", pointLng="
				+ pointLng + ", pointLat=" + pointLat + ", linkManPhone="
				+ linkManPhone + ", linkManName=" + linkManName + ", linkMans="
				+ linkMans + "]";
	}
	
}