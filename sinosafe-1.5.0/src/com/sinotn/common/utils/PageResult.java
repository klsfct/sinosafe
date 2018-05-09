package com.sinotn.common.utils;

import java.util.List;

public class PageResult {
	/**
	 * 分页对象
	 */
	private Page page = null;

	private List<?> resultList = null;

	public PageResult(Page page, List<?> resultList) {
		this.page = page;
		this.resultList = resultList;
	}

	public PageResult() {
		super();
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

}
