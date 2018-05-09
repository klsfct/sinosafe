package com.sinotn.common.utils;


/****************************************************
 * 功能:为列表分页显示提供基础类
 * 公司:哈尔滨信诺数码
 * 作者:李志东
 * 时间:2006-3-15
 */
public class PageControl {
	private int PageSize = 20;   //一页显示的记录数
	private int RowCount = 0;    //记录总数
	private int PageCount = 0;   //总页数
	private int Page = 1;        //待显示页码
	private int startRow = 0;    //查询起始页
	
	String action = "";		//查询指定提交action
	String queryStr = "";   //查询条件字符串
  
	/*
	 * 设置一页显示的记录数
	 */
	public void setPageSize(int PageSize) {
		this.PageSize = PageSize;
	}
	/*
	 * 获取一页显示的记录数
	 */
	public int getPageSize() {
		return PageSize;
	}
	/*
	 * 设置记录总数
	 */
	public void setRowCount(int RowCount) {
		this.RowCount = RowCount;
		this.PageCount = (this.RowCount + this.PageSize - 1) / this.PageSize;
		if (this.Page > this.PageCount)
			this.Page = this.PageCount;
	}
	/*
	 * 获取记录总数
	 */
	public int getRowCount() {
		return RowCount;
	}
	/*
	 * 获取总页数
	 */
	public int getPageCount() {
	  return this.PageCount;
	}
	/*
	 * 设置显示的页码
	 */
	public void setPage(String Page) {
		if (Page == null || Page.equals("")) {
			this.Page = 1;
		} else {
			this.Page = Integer.parseInt(Page);
        
			if (this.Page < 1) this.Page = 1;
		}
		
	}
	//获取当前页码
	public int getPage() {
		return Page;
	}
	/*
	 * 获得查询起始行
	 */
	public int getStartRow(){
		this.startRow = (this.getPage() - 1) * this.getPageSize();
		return startRow;
	}
	//---------------------------------设置查询条件-----------------------------
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	//------------------------------------------------------------------------
	
	/*
	 * 获得分页字符串，此字符串样式为《分页>> 首页 上页 下页 尾页 页次：1/5页 20条记录/页 共100条记录 跳转>><select>》
	 * 获得此分页字符串前要执行setRowCount(int RowCount)设置记录的总数，setAction(String action)设置翻页要提交的action
	 * setQueryStr(String queryStr)设置查询条件字符串
	 */
	public String getPageInfo() {
	    StringBuffer sb = new StringBuffer();
	    sb.append("<b>分页>></b>&nbsp;");
	    if (Page > 1)
			sb.append("<a href='" + action + "&page=1" + queryStr + "'>");
			sb.append("首页</a>&nbsp;");
	    if (Page > 1)
			sb.append("<a href='" + action + "&page=" + Integer.toString(Page - 1) + queryStr + "'>");
			sb.append("上页</a>&nbsp;");
	    if (Page < PageCount)
			sb.append("<a href='" + action + "&page=" + Integer.toString(Page + 1) + queryStr + "'>");
			sb.append("下页</a>&nbsp;");
	    if (Page < PageCount)
			sb.append("<a href='" + action + "&page=" + Integer.toString(PageCount) + queryStr + "'>");
			sb.append("尾页</a>&nbsp;页次：");
			sb.append("<b>"
	              + Integer.toString(Page)
	              + "</b>/<b>"
	              + Integer.toString(PageCount)
	              + "</b>页&nbsp;");
			sb.append("<b>" + Integer.toString(PageSize) + "</b>条记录/页&nbsp;");
			sb.append("共<b>"+ Integer.toString(RowCount) +"</b>条记录&nbsp;");
			sb.append("<font color=#0000FF>跳转>></font>");
			sb.append("<select name='yehao' onchange=\"javascript:location='" + action + "&page='+this.options[this.selectedIndex].value + '"
	              + queryStr
	              + "'\">");
			for (int i = 1; i <= PageCount; i++) {
				if (i == Page)
					sb.append("<option value='" + i + "' selected>" + i + "</option>");
				else
					sb.append("<option value='" + i + "'>" + i + "</option>");
			}
			sb.append("</select>");
			return sb.toString();
	}
	/*
	 * 获得分页字符串，此字符串样式为《分页>> 首页 上页 下页 尾页 页次：1/5页 20条记录/页 共100条记录 跳转>><go【】>》
	 * 获得此分页字符串前要执行setRowCount(int RowCount)设置记录的总数，setAction(String action)设置翻页要提交的action
	 * setQueryStr(String queryStr)设置查询条件字符串
	 */
	public String getPageInfoGo() {
	    StringBuffer sb = new StringBuffer();
	    sb.append("<script language='JavaScript' type='text/JavaScript'>\n");
	    sb.append("function fucCheckNUM(NUM){\n");
	    sb.append("var i,j,strTemp;strTemp='0123456789';\n");
	    sb.append("if(NUM.length== 0) return 1;\n");
	    sb.append("for (i=0;i<NUM.length;i++){\n");
	    sb.append("j=strTemp.indexOf(NUM.charAt(i));\n");
	    sb.append("if (j==-1){return false;}}return true;}\n");
	    
	    sb.append("function fucCheckInput(){\n");
	    sb.append("var inputValue=document.getElementById('yehao').value;\n");
	    sb.append("var maxPage=" + PageCount +";\n");
	    sb.append("if(fucCheckNUM(inputValue)){\n");
	    sb.append("if(inputValue>maxPage){inputValue=maxPage;}\n");
	    sb.append("if(inputValue==''||inputValue <=0){inputValue = '1';}\n");
	    sb.append("location.href='" + action + "page='+inputValue+'" + queryStr + "';}\n");
	    sb.append("else{alert('请输入正确的页数！'); return false;}}\n");
	    sb.append("</script>\n");

	    sb.append("<b>分页>></b>&nbsp;");
	    if (Page > 1)
			sb.append("<a href='" + action + "page=1" + queryStr + "'>");
			sb.append("首页</a>&nbsp;");
	    if (Page > 1)
			sb.append("<a href='" + action + "page=" + Integer.toString(Page - 1) + queryStr + "'>");
			sb.append("上页</a>&nbsp;");
	    if (Page < PageCount)
			sb.append("<a href='" + action + "page=" + Integer.toString(Page + 1) + queryStr + "'>");
			sb.append("下页</a>&nbsp;");
	    if (Page < PageCount)
			sb.append("<a href='" + action + "page=" + Integer.toString(PageCount) + queryStr + "'>");
			sb.append("尾页</a>&nbsp;页次：");
			sb.append("<b>"
	              + Integer.toString(Page)
	              + "</b>/<b>"
	              + Integer.toString(PageCount)
	              + "</b>页&nbsp;");
			sb.append("<b>" + Integer.toString(PageSize) + "</b>条记录/页&nbsp;");
			sb.append("共<b>"+ Integer.toString(RowCount) +"</b>条记录&nbsp;");
			sb.append("<font color=#0000FF>跳转>></font>");
			sb.append("<INPUT TYPE='text' NAME='yehao' size='4' style='width:30px'/>");
			sb.append("<a href='#' onClick=\"fucCheckInput();\">GO</a>");
			return sb.toString();
	}
	/*
	 * 获得分页字符串，此字符串样式为《分页>> 首页 上页 下页 尾页 页次：1/5页 共100条记录》
	 * 获得此分页字符串前要执行setRowCount(int RowCount)设置记录的总数，setAction(String action)设置翻页要提交的action
	 * setQueryStr(String queryStr)设置查询条件字符串
	 */
	public String getShortPageInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("<b>分页>></b>&nbsp;");
		if (Page > 1)
		  sb.append("<a href='?page=1" + queryStr + "'>");
		sb.append("首页</a>&nbsp;");
		if (Page > 1)
		  sb.append("<a href='?page=" + Integer.toString(Page - 1) + queryStr + "'>");
		sb.append("上页</a>&nbsp;");
		if (Page < PageCount)
		  sb.append("<a href='?page=" + Integer.toString(Page + 1) + queryStr + "'>");
		sb.append("下页</a>&nbsp;");
		if (Page < PageCount)
		  sb.append("<a href='?page=" + Integer.toString(PageCount) + queryStr + "'>");
		sb.append("尾页</a>&nbsp;页次：");
		sb.append("<b>"
				  + Integer.toString(Page)
				  + "</b>/<b>"
				  + Integer.toString(PageCount)
				  + "</b>页&nbsp;");
		sb.append("共<b>"+ Integer.toString(RowCount) +"</b>条记录&nbsp;");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		PageControl p = new PageControl();
		p.setRowCount(100);
		System.out.print(p.getPageInfoGo());
	}
}

