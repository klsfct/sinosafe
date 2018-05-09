package com.sinotn.examsafety.vo;


/**
 * LinkMan entity. @author MyEclipse Persistence Tools
 */

public class WxLinkManVo implements java.io.Serializable {

	// Fields

	private Long linkManId;
	private String familyName;
	private String name;
	private String tel;
	private Boolean isPersonShow;
	private Boolean isPersonSel;

	// Constructors

	/** default constructor */
	public WxLinkManVo() {
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Boolean getIsPersonShow() {
		return isPersonShow;
	}

	public void setIsPersonShow(Boolean isPersonShow) {
		this.isPersonShow = isPersonShow;
	}

	public Boolean getIsPersonSel() {
		return isPersonSel;
	}

	public void setIsPersonSel(Boolean isPersonSel) {
		this.isPersonSel = isPersonSel;
	}

	public Long getLinkManId() {
		return linkManId;
	}

	public void setLinkManId(Long linkManId) {
		this.linkManId = linkManId;
	}
	
	

}