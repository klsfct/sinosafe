package com.sinotn.examsafety.vo;

import java.util.Date;



public class LinkManJsonVo {

	// Fields

	private Long id;
	private Long parentId;
	private String name;
	private String open;

	// Constructors

	/** default constructor */
	public LinkManJsonVo() {
	}

	/** minimal constructor */
	public LinkManJsonVo(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	
	
}