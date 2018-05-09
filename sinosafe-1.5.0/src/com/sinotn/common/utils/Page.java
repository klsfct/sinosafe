package com.sinotn.common.utils;

public class Page {
	/**
	 * 是否有上一页
	 */
	private boolean hasPrePage;
	/**
	 * 是否有下一页
	 */
	private boolean hasNextPage;
	/**
	 * 每页的数量
	 */
	private int everyPage;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 起始点
	 */
	private int beginIndex;
	/**
	 * 总记录数
	 */
	private int totalCount;
	/**
	 * 对应hibernate实体对象名称
	 */
	private String entity = "";
	/**
	 * hibernate实体对象对应的页面展示Vo对象类完整名称例如，java.lang.String
	 */
	private String entityVo = "";
	/**
	 * 数据表格
	 */
	private String sysDataGrid = "";
	/**
	 * 查询条件
	 */
	private String condition = "";
	/**
	 * 初始条件
	 */
	private String initCondition = "";

	/**
	 * 排序条件
	 */
	private String orderBy = "";
	public Page(){
		
	}
	public Page(boolean hasPrePage, boolean hasNextPage, int everyPage,
			int totalPage, int currentPage, int beginIndex, int totalCount,
			String entity, String sysDataGrid, String condition,String orderBy,String entityVo) {
		super();
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
		this.everyPage = everyPage;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.beginIndex = beginIndex;
		this.totalCount = totalCount;
		this.entity = entity;
		this.sysDataGrid = sysDataGrid;
		this.condition = condition;
		this.orderBy = orderBy;
		this.entityVo = entityVo;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getSysDataGrid() {
		return sysDataGrid;
	}

	public void setSysDataGrid(String sysDataGrid) {
		this.sysDataGrid = sysDataGrid;
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Page(int everyPage) {
		this.everyPage = everyPage;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	/**
	 * 设置起始点
	 */
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getEveryPage() {
		return everyPage;
	}

	/**
	 * 设置每页的数量
	 */
	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}

	/**
	 * 是否有下一页
	 */
	public boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * 是否有上一页
	 */
	public boolean getHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置总页数
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getEntityVo() {
		return entityVo;
	}
	public void setEntityVo(String entityVo) {
		this.entityVo = entityVo;
	}
	public String getInitCondition() {
		return initCondition;
	}
	public void setInitCondition(String initCondition) {
		this.initCondition = initCondition;
	}
	
}
