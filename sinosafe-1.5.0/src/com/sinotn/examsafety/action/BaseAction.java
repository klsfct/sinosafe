package com.sinotn.examsafety.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.opensymphony.xwork2.ActionSupport;
import com.sinotn.common.utils.CreatCondition;
import com.sinotn.common.utils.DateUtils;
import com.sinotn.common.utils.FileUtils;
import com.sinotn.common.utils.PageControl;
import com.sinotn.examsafety.service.impl.BaseCodeUtils;
import com.sinotn.examsafety.vo.BaseCodeVo;
import com.sinotn.examsafety.vo.SysUsersVo;

/**
 * CopyRright(c)2014 哈尔滨信诺数码科技有限公司
 * 
 * @Description 系统VIEW层基础ACTION类，后台登陆所有类的祖先
 * @Project
 * @Author 臧国成
 * @Create Date 2014-03-28
 * @Modified By <修改人中文名或拼音缩写>
 * @Modified Date <修改日期，格式:YYYY-MM-DD>
 * @Why & What is modified <修改原因描述>
 * @Version V1.0
 */

public class BaseAction extends ActionSupport {
	/**
	 * 查询条件
	 */
	protected String condition;
	/**
	 * 显示条件
	 */
	protected String viewCondition;
	/**
	 * 当前页数
	 */
	protected int pageNum;

	/**
	 * 翻页控制
	 */
	private PageControl pageControl;
	/**
	 * 当前页
	 */
	private String page;
	/**
	 * 翻页字符
	 */
	private String pageStr;

	/**
	 * 基础代码分类标志
	 */
	private String categorys;
	/**
	 * 地区ID
	 */
	private String areaId = null;

	/**
	 * 地区级别
	 */
	private int level = 0;

	/**
	 * ajax返回值
	 */
	private Map<Object, Object> ajaxBackData;
	/**
	 * 获得翻页控制对象
	 */
	public PageControl getPageControl() {
		pageControl = new PageControl();
		this.pageControl.setPage(page);
		this.pageControl.setQueryStr(CreatCondition.getCondition());
		return pageControl;
	}

	/**
	 * 获取访问用户IP地址
	 */
	public String getRequestIp() {
		return this.getRequest().getRemoteAddr();
	}

	/**
	 * 获得请求session对象
	 */
	public HttpSession getSession() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return session;
	}

	/**
	 * 获得请求request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request;
	}

	/**
	 * 获得Response对象
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		return response;
	}

	/**
	 * 静�?页面跳转使用
	 */
	public String gotoPage() {
		return SUCCESS;
	}

	public boolean isAjaxRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null != request.getHeader("x-requested-with")
				&& request.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest")) {
			response.setHeader("sessionstatus", "timeout");
			return true;
		}
		return false;
	}

	public static final String AJAX = "ajax";

	/**
	 * 输出js提示信息
	 */
	public String alertException(String msg) {
		PrintWriter out;
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			out.println("<script language='JavaScript'	type='text/JavaScript'>");
			out.println("alert(\"" + msg + "\");");
			out.println("history.go(-1);");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 输出js提示信息,并且隐藏提交表单
	 */
	public String alertForm(String msg, String action, String[][] param) {
		PrintWriter out;
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();

			out.println("<form action=\"" + action
					+ "\" method=\"post\" name=\"thisform\" >");
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					out.println("<INPUT type='hidden' name='" + param[i][0]
							+ "' value='" + param[i][1] + "'>");
				}
			}
			out.println("</form>");
			out.println("<script language='JavaScript' type='text/JavaScript'>");
			out.println("alert(\"" + msg + "\");");
			out.println("thisform.submit();");
			out.println("</script>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SysUsersVo getLoginUser() {
		SysUsersVo userVo = (SysUsersVo) getSession().getAttribute(
				"loginSession");
		return userVo;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageStr() {
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getViewCondition() {
		if (viewCondition == null || viewCondition.equals("")) {
			viewCondition = "全部";
		}
		return viewCondition;
	}

	public void setViewCondition(String viewCondition) {
		this.viewCondition = viewCondition;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	public String getAreaId() {
		if (areaId == null) {
			areaId = this.getLoginUser().getAreaId();
		} else {
			String loginAreaId = this.getLoginUser().getAreaId();
			if (this.getLoginUser().getLevel() == 2) {
				if (!areaId.startsWith(loginAreaId)) {
					return loginAreaId;
				}
			} else if (this.getLoginUser().getLevel() == 3) {
				if (!areaId.equals(loginAreaId)) {
					return loginAreaId;
				}
			}
		}
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public int getLevel() {
		BaseCodeVo area = BaseCodeUtils.findBaseCodeById(areaId);
		if (area == null) {
			return this.getLoginUser().getLevel();
		} else {
			return Integer.parseInt(area.getFlag());
		}
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Map<Object, Object> getAjaxBackData() {
		if (null == ajaxBackData) {
			ajaxBackData = new HashMap<Object, Object>();
			ajaxBackData.put("dataStatus", "1");
		}
		return ajaxBackData;
	}

	public void setAjaxBackData(Map<Object, Object> ajaxBackData) {
		this.ajaxBackData = ajaxBackData;
	}

	/**
	 * 设置ajax返回值异常信息
	 * 
	 * @param Exception
	 *            e
	 */
	public void setAjaxBackDataErrorMsg(Exception e) {
		this.ajaxBackData = new HashMap<Object, Object>();
		ajaxBackData.put("dataStatus", "0");
		ajaxBackData.put("errorMsg", e.getMessage());
	}

	/**
	 * 设置ajax返回值异常信息
	 * 
	 * @param Exception
	 *            e
	 */
	public void setAjaxBackDataErrorMsg(String errMsg) {
		this.ajaxBackData = new HashMap<Object, Object>();
		ajaxBackData.put("dataStatus", "0");
		ajaxBackData.put("errorMsg", errMsg);
	}

	/**
	 * 设置ajax返回值对象信息
	 * 
	 * @param Object
	 *            obj
	 */
	public void setAjaxBackDataMain(Object obj) {
		this.ajaxBackData = new HashMap<Object, Object>();
		ajaxBackData.put("dataStatus", "1");
		ajaxBackData.put("dataMain", obj);
	}
}
