package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.ExamineeMiss;

/**
 * ExamineeMiss entity. @author MyEclipse Persistence Tools
 */

public class ExamineeMissVo implements java.io.Serializable {

	// Fields

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
	public ExamineeMissVo() {
	}

	public ExamineeMissVo(String id, String examArea, Long examPlaceId,
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

	public ExamineeMissVo(ExamineeMiss examineeMiss) {
		this.id = examineeMiss.getId();
		this.examArea = examineeMiss.getExamArea();
		this.examPlaceId = examineeMiss.getExamPlaceId();
		this.examPlaceName = examineeMiss.getExamPlaceName();
		this.examineeSum = examineeMiss.getExamineeSum();
		this.missSum1 = examineeMiss.getMissSum1();
		this.creater = examineeMiss.getCreater();
		this.createDate = examineeMiss.getCreateDate();
		this.missSum2 = examineeMiss.getMissSum2();
		this.missSum3 = examineeMiss.getMissSum3();
		this.missSum4 = examineeMiss.getMissSum4();
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

	@JSON(format="yyyy-MM-dd HH:mm:ss")
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

}