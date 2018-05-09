package com.sinotn.examsafety.vo;


/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     ZtreeJsonVO.java
 * @Description:   zTreeJson类
 * 
 * @author:    李斌
 * @version:   V1.0  
 * @Date:      2017年7月19日 下午2:32:47
 */
public class ZtreeJsonVO {
	
	public static final String ONLINE_ICON_SRC = "./images/icon/blue.png";
	public static final String NOT_ONLINE_ICON_SRC = "./images/icon/grey.png";
	
	
	private String id;
	private String pId;
	private String name;
	private String icon;
	private String isOnline;
	private String linkManName;
	private String linkManPhone;
	private Boolean open;
	
	public ZtreeJsonVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ZtreeJsonVO(String id, String pId, String name, String icon) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.icon = icon;
	}
	
	public ZtreeJsonVO(String id, String pId, String name, Boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getLinkManPhone() {
		return linkManPhone;
	}

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}
	
	
}
