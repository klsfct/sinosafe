package com.sinotn.examsafety.po;


/**
 * BaseCode entity. @author MyEclipse Persistence Tools
 */

public class BaseCode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1535866560561248196L;
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
	// Constructors

	/** default constructor */
	public BaseCode() {
	}

	/** minimal constructor */
	public BaseCode(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public BaseCode(String id, String name, String aliax1, String aliax2,
			String category, Boolean isEnabled, Integer orderBy,
			String previousId, String flag, String remark, String cityCenterLng, String cityCenterLat) {
		this.id = id;
		this.name = name;
		this.aliax1 = aliax1;
		this.aliax2 = aliax2;
		this.category = category;
		this.isEnabled = isEnabled;
		this.orderBy = orderBy;
		this.previousId = previousId;
		this.flag = flag;
		this.remark = remark;
		this.cityCenterLng = cityCenterLng;
		this.cityCenterLat = cityCenterLat;
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
		return "BaseCode [id=" + id + ", name=" + name + ", aliax1=" + aliax1
				+ ", aliax2=" + aliax2 + ", category=" + category
				+ ", isEnabled=" + isEnabled + ", orderBy=" + orderBy
				+ ", previousId=" + previousId + ", flag=" + flag + ", remark="
				+ remark + ", cityCenterLng=" + cityCenterLng
				+ ", cityCenterLat=" + cityCenterLat + "]";
	}


}