package com.sinotn.examsafety.vo;

import java.util.List;


public class WxLinkGroupVo{

	// Fields

	private String groupName;
	private String isGroupSel;
	private List<WxLinkManVo> personList;

	// Constructors

	/** default constructor */
	public WxLinkGroupVo() {
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIsGroupSel() {
		return isGroupSel;
	}

	public void setIsGroupSel(String isGroupSel) {
		this.isGroupSel = isGroupSel;
	}

	public List<WxLinkManVo> getPersonList() {
		return personList;
	}

	public void setPersonList(List<WxLinkManVo> personList) {
		this.personList = personList;
	}

	
}