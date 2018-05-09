package com.sinotn.examsafety.vo;

import com.sinotn.examsafety.po.BaseCode;

/**
 * BaseCode entity. @author MyEclipse Persistence Tools
 */

public class BaseCodeVo {

	// Fields

	private String id;
	private String name;
	private String aliax1;
	private String aliax2;
	private String category;
	private Boolean isEnabled;
	private Integer orderBy;
	private String previousId;
	private String flag;
	private String remark;
	/**
	 * 2018.3.21
	 * 新增字段：城市中心坐标经纬度
	 */
	private String cityCenterLng;
	private String cityCenterLat;
	
	private String[] centerPoint;
	// Constructors

	/** default constructor */
	public BaseCodeVo() {
	}

	/** minimal constructor */
	public BaseCodeVo(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public BaseCodeVo(BaseCode baseCode) {
		this.id = baseCode.getId();
		this.name = baseCode.getName();
		this.aliax1 = baseCode.getAliax1();
		this.aliax2 = baseCode.getAliax2();
		this.category = baseCode.getCategory();
		this.isEnabled = baseCode.getIsEnabled();
		this.orderBy = baseCode.getOrderBy();
		this.previousId = baseCode.getPreviousId();
		this.flag = baseCode.getFlag();
		this.remark = baseCode.getRemark();
		this.cityCenterLng = baseCode.getCityCenterLng();
		this.cityCenterLat = baseCode.getCityCenterLat();
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliax1() {
		return this.aliax1;
	}

	public void setAliax1(String aliax1) {
		this.aliax1 = aliax1;
	}

	public String getAliax2() {
		return this.aliax2;
	}

	public void setAliax2(String aliax2) {
		this.aliax2 = aliax2;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getPreviousId() {
		return this.previousId;
	}

	public void setPreviousId(String previousId) {
		this.previousId = previousId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCityCenterLng() {
		return cityCenterLng;
	}

	public void setCityCenterLng(String cityCenterLng) {
		this.cityCenterLng = cityCenterLng;
	}

	public String getCityCenterLat() {
		return cityCenterLat;
	}

	public void setCityCenterLat(String cityCenterLat) {
		this.cityCenterLat = cityCenterLat;
	}

	@Override
	public String toString() {
		return "BaseCodeVo [id=" + id + ", name=" + name + ", aliax1=" + aliax1
				+ ", aliax2=" + aliax2 + ", category=" + category
				+ ", isEnabled=" + isEnabled + ", orderBy=" + orderBy
				+ ", previousId=" + previousId + ", flag=" + flag + ", remark="
				+ remark + ", cityCenterLng=" + cityCenterLng
				+ ", cityCenterLat=" + cityCenterLat + "]";
	}

	public String[] getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(String[] centerPoint) {
		this.centerPoint = centerPoint;
	}

}