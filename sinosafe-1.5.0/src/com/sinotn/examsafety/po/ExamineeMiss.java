package com.sinotn.examsafety.po;

import java.util.Date;

/**
 * ExamineeMiss entity. @author MyEclipse Persistence Tools
 */

public class ExamineeMiss implements java.io.Serializable {
	
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3339519007651962455L;
	private String id;
	private String examArea;
	private Long examPlaceId;
	private String examPlaceName;
	private Integer examineeSum;
	private Integer missSum1;
	private String creater;
	private Date createDate;
	private Integer missSum2;
	private Integer missSum3;
	private Integer missSum4;

	// Constructors

	/** default constructor */
	public ExamineeMiss() {
	}

	public ExamineeMiss(String id, String examArea, Long examPlaceId,
			String examPlaceName, Integer examineeSum, Integer missSum1,
			String creater, Date createDate, Integer missSum2,
			Integer missSum3, Integer missSum4) {
		this.id = id;
		this.examArea = examArea;
		this.examPlaceId = examPlaceId;
		this.examPlaceName = examPlaceName;
		this.examineeSum = examineeSum;
		this.missSum1 = missSum1;
		this.creater = creater;
		this.createDate = createDate;
		this.missSum2 = missSum2;
		this.missSum3 = missSum3;
		this.missSum4 = missSum4;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExamArea() {
		return examArea;
	}

	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}

	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public Integer getExamineeSum() {
		return examineeSum;
	}

	public void setExamineeSum(Integer examineeSum) {
		this.examineeSum = examineeSum;
	}

	public Integer getMissSum1() {
		return missSum1;
	}

	public void setMissSum1(Integer missSum1) {
		this.missSum1 = missSum1;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getMissSum2() {
		return missSum2;
	}

	public void setMissSum2(Integer missSum2) {
		this.missSum2 = missSum2;
	}

	public Integer getMissSum3() {
		return missSum3;
	}

	public void setMissSum3(Integer missSum3) {
		this.missSum3 = missSum3;
	}

	public Integer getMissSum4() {
		return missSum4;
	}

	public void setMissSum4(Integer missSum4) {
		this.missSum4 = missSum4;
	}

	@Override
	public String toString() {
		return "ExamineeMiss [id=" + id + ", examArea=" + examArea
				+ ", examPlaceId=" + examPlaceId + ", examPlaceName="
				+ examPlaceName + ", examineeSum=" + examineeSum
				+ ", missSum1=" + missSum1 + ", creater=" + creater
				+ ", createDate=" + createDate + ", missSum2=" + missSum2
				+ ", missSum3=" + missSum3 + ", missSum4=" + missSum4 + "]";
	}


}