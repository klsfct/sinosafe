package com.sinotn.common.filter;

/**
 * <p>
 * Description:司法考试后台管理平台filter过滤
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: 信诺数码
 * </p>
 * <p>
 * CreateDate:2009-03-23
 * </p>
 * 
 * @author 白春秀
 * @version 1.0
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminFilter extends HttpServlet implements Filter {

	private FilterConfig filterConfig;

	// Handle the passed-in FilterConfig
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	// Process the request/response pair
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) {
		try {
			boolean pass = false;
			String message = "";
			HttpServletRequest h_request = (HttpServletRequest) request;
			HttpServletResponse h_response = (HttpServletResponse) response;
			String url = h_request.getServletPath();
			String rootpath = h_request.getContextPath();
			String msg;
			Object userObj;
			String login_page;
			if (url.startsWith("/admin")) {
				if ((userObj = h_request.getSession().getAttribute("loginSession")) != null) {
					pass = true;
				} else {
					pass = false;
				}

				if (!pass) {
					login_page = rootpath + "/login.jsp";
					msg = "你没有登陆或登陆超时，请重新登陆！";
					RequestDispatcher rd = request
							.getRequestDispatcher(login_page);

					PrintWriter out;
					gotoPage(h_request, response, msg, login_page);
				} else {
					filterChain.doFilter(request, response);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		} catch (Exception e) {
		}
	}

	public void gotoPage(HttpServletRequest h_request,
			ServletResponse response, String msg, String login_page)
			throws IOException {
		// session过期，如果是 AJAX请求，就写回SESSIONSTATE：0
		if (h_request.getHeader("x-requested-with") != null
				&& h_request.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest")) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			PrintWriter out = response.getWriter();
			out.println("{\"sessionTimeout\": true}");
			out.flush();
			out.close();
			return;
		}
		PrintWriter out;
		response.setContentType("text/html;charset=utf-8");
		out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<script language='JavaScript' type='text/JavaScript'>");
		out.println("alert('" + msg + "');");
		out.println("top.location.href=\"" + login_page + "\";");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
	}

	// Clean up resources
	public void destroy() {
	}
}
