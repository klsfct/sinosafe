package com.sinotn.common.pagequery;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class CreatCondition {

	/*
	 * 根据查询条件构�?hql语句
	 * 要构造hql与的条件必须以�?bm_”开头，以�?_begin_date，_end_date”结尾，日期类型区间查询�?
	 * 以�?_begin，_end”结尾是数字类型区间查询，以“_like”结尾执行模糊查询�?
	 */
	public static String getWhere(){
		String hqlWhere = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer bf = new StringBuffer();
		String paraName;
		java.util.Enumeration em = request.getParameterNames();
		while (em.hasMoreElements()) {
			paraName = em.nextElement().toString();
			if (paraName.toLowerCase().startsWith("bm_")) {
				if (request.getParameter(paraName) != null
						&& !(request.getParameter(paraName).equals(""))) {
					if (paraName.endsWith("_begin_date")) {
						bf.append(paraName.substring(3, paraName.length() - 11)
								+ " >= '"
								+ request.getParameter(paraName)
								+ "' and ");
					} else if (paraName.endsWith("_end_date")) {
						bf.append(paraName.substring(3, paraName.length() - 9)
								+ " <= '"
								+ request.getParameter(paraName)
								+ "' and ");
					} else if (paraName.endsWith("_begin")) {
						bf.append(paraName.substring(3, paraName.length() - 6)
								+ " >= '" + request.getParameter(paraName)
								+ "' and ");
					} else if (paraName.endsWith("_end")) {
						bf.append(paraName.substring(3, paraName.length() - 4)
								+ " <= '" + request.getParameter(paraName)
								+ "' and ");
					} else if (paraName.endsWith("_like")) {
						bf.append(paraName.substring(3, paraName.length() - 5)
								+ " like '%" + request.getParameter(paraName)
								+ "%' and ");
					} else {
						bf.append(paraName.substring(3,paraName.length()) + " = '" + request.getParameter(paraName) + "' and "); 
					}
				}
			}
			if (bf.length() > 4) {
				hqlWhere = (bf.toString()).replace('$', '.').substring(0,bf.length() - 4);
			} else {
				hqlWhere = (bf.toString()).replace('$', '.');
			}
		}
		return hqlWhere;
	}
	/*
	 * 构�?查询条件
	 */
	public static String getCondition() {
		String condition = "";
		HttpServletRequest request = ServletActionContext.getRequest();	
		StringBuffer bf = new StringBuffer();
		String paraName = null;;
		java.util.Enumeration em = request.getParameterNames();
		try{
			while (em.hasMoreElements()) {
				paraName = em.nextElement().toString();
				if (request.getParameter(paraName) != null
						&& !(request.getParameter(paraName).equals(""))
						&& !(paraName.equals("page"))) {
					condition = condition + "&" + paraName + "="
							+ URLEncoder.encode(request.getParameter(paraName),"UTF-8");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return condition;
	}
}
