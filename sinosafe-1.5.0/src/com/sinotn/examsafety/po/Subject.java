package com.sinotn.examsafety.po;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 科目PO
 * @author Libin
 *
 */
public class Subject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464004534122069453L;
	private BigDecimal id;
	private String examYear;
	private String name;
	private Date beginDate;
	private Date endDate;
	private Boolean isEnabled;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getExamYear() {
		return examYear;
	}
	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subject(BigDecimal id, String examYear, String name, Date beginDate,
			Date endDate, Boolean isEnabled) {
		super();
		this.id = id;
		this.examYear = examYear;
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.isEnabled = isEnabled;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", examYear=" + examYear + ", name="
				+ name + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", isEnabled=" + isEnabled + "]";
	}
	
}
