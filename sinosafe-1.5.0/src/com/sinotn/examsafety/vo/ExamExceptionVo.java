package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.ExamException;

public class ExamExceptionVo implements java.io.Serializable {

	// Fields

	private String exceptionId;
	private BaseCodeVo exceptionTypeId;
	private String exceptionName;
	private String exceptionDescript;
	private String photoPath;
	private Date createDate;
	private String createIp;
	private String alterUserName;
	private String examPlaceName;
	private Long linkManId;
    private String loginAddr;
    private String loginLat;
    private String loginLng;
    private Long examPlaceId;

	// Constructors

	/** default constructor */
	public ExamExceptionVo() {
	}

	/** minimal constructor */
	public ExamExceptionVo(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	/** full constructor */
	public ExamExceptionVo(String exceptionId, BaseCodeVo exceptionTypeId,String exceptionName, String exceptionDescript, String photoPath,
			Date createDate, String createIp, String alterUserName,Long linkManId,
			String examPlaceName,String loginAddr
		      ,String loginLat,String loginLng, Long examPlaceId) {
		this.exceptionId = exceptionId;
		this.exceptionName = exceptionName;
		this.exceptionDescript = exceptionDescript;
		this.photoPath = photoPath;
		this.createDate = createDate;
		this.createIp = createIp;
		this.alterUserName = alterUserName;
		this.examPlaceName = examPlaceName;
		this.linkManId = linkManId;
	    this.loginAddr= loginAddr;
	    this.loginLat = loginLat;
	    this.loginLng = loginLng;
	    this.examPlaceId = examPlaceId;
	  }
	
	public ExamExceptionVo(ExamException examException) {
		this.exceptionId = examException.getExceptionId(); 
		this.exceptionName = examException.getExceptionName();
	    this.exceptionDescript = examException.getExceptionDescript();
	   // this.exceptionTypeId = examException.getExceptionTypeId();
	    this.photoPath = examException.getPhotoPath();
	    this.createDate = examException.getCreateDate();
	    this.createIp = examException.getCreateIp();
	    this.alterUserName = examException.getAlterUserName();
	    this.examPlaceName = examException.getExamPlaceName();
	    this.linkManId = examException.getLinkManId();
	    this.loginAddr= examException.getLoginAddr();
	    this.loginLat = examException.getLoginLat();
	    this.loginLng = examException.getLoginLng();
	    this.examPlaceId = examException.getExamPlaceId();
	}
	// Property accessors

	public String getExceptionName() {
		return this.exceptionName;
	}

	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	public String getExceptionDescript() {
		return this.exceptionDescript;
	}

	public void setExceptionDescript(String exceptionDescript) {
		this.exceptionDescript = exceptionDescript;
	}


	public String getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	public BaseCodeVo getExceptionTypeId() {
		return exceptionTypeId;
	}

	public void setExceptionTypeId(BaseCodeVo exceptionTypeId) {
		this.exceptionTypeId = exceptionTypeId;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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

	public String getExamPlaceName() {
		return this.examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public Long getLinkManId() {
		return linkManId;
	}

	public void setLinkManId(Long linkManId) {
		this.linkManId = linkManId;
	}

	public String getLoginAddr() {
		return loginAddr;
	}

	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}

	public String getLoginLat() {
		return loginLat;
	}

	public void setLoginLat(String loginLat) {
		this.loginLat = loginLat;
	}

	public String getLoginLng() {
		return loginLng;
	}

	public void setLoginLng(String loginLng) {
		this.loginLng = loginLng;
	}

	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

}