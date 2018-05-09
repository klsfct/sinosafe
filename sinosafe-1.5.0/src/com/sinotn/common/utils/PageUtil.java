package com.sinotn.common.utils;

public class PageUtil {
	// Use the origin page to create a new page
	public static Page createPage(Page page, int totalRecords) {
		return createPage(page.getEveryPage(), page.getCurrentPage(), page
				.getEntity(), page.getCondition(), totalRecords,page.getSysDataGrid(),page.getOrderBy(),page.getEntityVo());
	}

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
