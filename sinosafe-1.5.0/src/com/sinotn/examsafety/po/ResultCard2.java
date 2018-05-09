package com.sinotn.examsafety.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ResultCard entity. @author MyEclipse Persistence Tools
 */

public class ResultCard2 implements java.io.Serializable {

	// Fields

	private Long id;
	private String imeiNo;
	private String subject;
	private Long examPlace;
	private Integer examRoom;
	private Integer examCard;
	private Date batchDate;
	private Date scanDate;
	private Date receiveDate;
	private Boolean isProcess;
	private Date processDate;
	private Byte times;
	private String filename;

	// Constructors

	/** default constructor */
	public ResultCard2() {
	}

	/** minimal constructor */
	public ResultCard2(Long id) {
		this.id = id;
	}

	/** full constructor */
	public ResultCard2(Long id, String imeiNo, String subject,
			Long examPlace, Integer examRoom, Integer examCard,
			Date batchDate, Date scanDate, Date receiveDate, Boolean isProcess,
			Date processDate, Byte times,String filename) {
		this.id = id;
		this.imeiNo = imeiNo;
		this.subject = subject;
		this.examPlace = examPlace;
		this.examRoom = examRoom;
		this.examCard = examCard;
		this.batchDate = batchDate;
		this.scanDate = scanDate;
		this.receiveDate = receiveDate;
		this.isProcess = isProcess;
		this.processDate = processDate;
		this.times = times;
		this.filename = filename;
	}

	// Property accessors

	
	public String getImeiNo() {
		return this.imeiNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(Long examPlace) {
		this.examPlace = examPlace;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getExamRoom() {
		return this.examRoom;
	}

	public void setExamRoom(Integer examRoom) {
		this.examRoom = examRoom;
	}

	public Integer getExamCard() {
		return examCard;
	}

	public void setExamCard(Integer examCard) {
		this.examCard = examCard;
	}

	public Date getBatchDate() {
		return this.batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public Date getScanDate() {
		return this.scanDate;
	}

	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}

	public Date getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Boolean getIsProcess() {
		return this.isProcess;
	}

	public void setIsProcess(Boolean isProcess) {
		this.isProcess = isProcess;
	}

	public Date getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public Byte getTimes() {
		return this.times;
	}

	public void setTimes(Byte times) {
		this.times = times;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}