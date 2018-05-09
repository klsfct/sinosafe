package com.sinotn.common.pagequery;


public class PageUtil {
	// Use the origin page to create a new page
	public static Page createPage(Page page, int totalRecords) {
		return createPage(page.getEveryPage(), page.getCurrentPage(), page
				.getEntity(), page.getCondition(), totalRecords,page.getSysDataGrid(),page.getOrderBy(),page.getEntityVo());
	}

	/**
	 * 
	 * @param everyPage 每页的数量
	 * @param currentPage 当前页
	 * @param entity 对应hibernate实体对象名称
	 * @param condition 查询条件
	 * @param totalRecords 总记录数
	 * @param sysDataGrid 数据表格
	 * @param orderBy 排序条件
	 * @param entityVo hibernate实体对象对应的页面展示Vo对象类
	 * @return
	 */
	public static Page createPage(int everyPage, int currentPage,
			String entity, String condition, int totalRecords,String sysDataGrid,String orderBy,String entityVo) {
		everyPage = getEveryPage(everyPage);
		currentPage = getCurrentPage(currentPage);
		int totalPage = getTotalPage(everyPage, totalRecords);
		//--新增，判断当前页如果大于总页数，利用总页数作为当前页
		if(currentPage >= totalPage){
			currentPage = totalPage-1;
		}
		if(currentPage  < 0){
			currentPage = 0;
		}
		int beginIndex = getBeginIndex(everyPage, currentPage);
		boolean hasNextPage = hasNextPage(currentPage, totalPage);
		boolean hasPrePage = hasPrePage(currentPage);
		
		return new Page(hasPrePage, hasNextPage, everyPage, totalPage,
				currentPage, beginIndex, totalRecords, entity, sysDataGrid,condition,orderBy,entityVo);
	}
	
	
	/**
	 * 初始化分页参数，配合页面传参
	 * @param pageNo 传递当前应该是第几页
	 * @param pageSize 传递当前每页多少条记录
	 * @param sort 传递当前排序的字段名
	 * @param direction 传递当前的排序方向
	 * @return
	 * entity 实体对象
	 * condition 查询条件
	 * orderBy 排序条件
	 * beginIndex 起始点
	 * currentPage 每页的数量
	 */
	public static Page createPageAjax(Page page,String pageNo,String pageSize,String sort,String direction){
		
		int pageNo_number = 1;
		int pageSize_number =10;
		if(null!=pageNo && pageNo.length()>0){
			pageNo_number = Integer.parseInt(pageNo);
		}
		if(null!=pageSize && pageSize.length()>0){
			pageSize_number = Integer.parseInt(pageSize);
		}else{
			if(page.getCurrentPage()!=0){
				pageSize_number = page.getCurrentPage();
			}
		}
		
		
		int beginIndex = (pageNo_number-1)*pageSize_number;
		page.setBeginIndex(beginIndex);
		page.setEveryPage(pageSize_number);
		
		if(null!=sort && sort.length()>0){
			String orderBy = " order by "+sort+ " " +direction;
			page.setOrderBy(orderBy);
		}
		
		return page;
	}
	

	private static int getEveryPage(int everyPage) {
		return everyPage == 0 ? 10 : everyPage;
	}

	private static int getCurrentPage(int currentPage) {
		//return currentPage == 0 ? 1 : currentPage;
		return currentPage;
	}

	private static int getBeginIndex(int everyPage, int currentPage) {
		//return (currentPage - 1) * everyPage;
		return currentPage * everyPage;
	}

	private static int getTotalPage(int everyPage, int totalRecords) {
		int totalPage = 0;

		if (totalRecords % everyPage == 0)
			totalPage = totalRecords / everyPage;
		else
			totalPage = totalRecords / everyPage + 1;

		return totalPage;
	}

	private static boolean hasPrePage(int currentPage) {
		return currentPage == 0 ? false : true;
	}

	private static boolean hasNextPage(int currentPage, int totalPage) {
		return currentPage == (totalPage-1) || totalPage == 0 ? false : true;
	}
}
