package com.sinotn.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtils {
	/**
	 * 处理验证
	 * @param request
	 * @return
	 */
	public static boolean isCheckNum(HttpServletRequest request) {
		//获取已经存在的session
		HttpSession session = request.getSession(false);
		
		if(session == null){
			return false;
		}
		
		String check_number_key = (String)session.getAttribute("CHECK_NUMBER_KEY");
		if(StringUtils.isBlank(check_number_key)){
			return false;
		}
		
		//获取jsp页面文本框中输入的?
		//<input name="checkNum" type="text" value="" id="checkNum" style="width: 80">
		String saved = request.getParameter("checkNum");
		if(StringUtils.isBlank(saved)){
			return false;
		}
		
		//比对session中存放的值和页面文本框输入的验证码的?
		return check_number_key.equalsIgnoreCase(saved);	
	}
	
	/*public static void addCookie(String name, String password, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
		if(StringUtils.isNotBlank(name)&&StringUtils.isNotBlank(password)){
			
			//创建Cookie
			Cookie nameCookie=new Cookie("name",java.net.URLEncoder.encode(name,"utf-8"));
			Cookie passwordCookie=new Cookie("password",password);
			
			//设置Cookie的父路径
			//System.out.println("request.getContextPath()="+request.getServletPath());
			nameCookie.setPath(request.getContextPath()+"/");
			passwordCookie.setPath(request.getContextPath()+"/");
			
			//获取是否保存Cookie
			String rememberMe=request.getParameter("rememberMe");
			if(rememberMe==null){//不保存Cookie
				nameCookie.setMaxAge(0);
				passwordCookie.setMaxAge(0);
			}else{  //保存Cookie
				nameCookie.setMaxAge(7*24*60*60);
				passwordCookie.setMaxAge(7*24*60*60);
			}
			//加入Cookie到响应头
			response.addCookie(nameCookie);
			response.addCookie(passwordCookie);
		}
	}*/
}
