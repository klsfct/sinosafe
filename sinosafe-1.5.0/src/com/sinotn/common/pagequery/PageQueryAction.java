package com.sinotn.common.pagequery;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.sinotn.common.exception.BusinessException;
import com.sinotn.common.exception.EncryptException;
import com.sinotn.common.utils.EncryptUtils;
import com.sinotn.examsafety.action.BaseAction;

/**
 * CopyRright(c)2014 哈尔滨信诺数码科技有限公司
 * 
 * @Description 系统VIEW层的列表查询相关功能ACTION类
 * @Project
 * @Author 臧国成
 * @Create Date 2014-03-28
 * @Modified By <修改人中文名或拼音缩写>
 * @Modified Date <修改日期，格式:YYYY-MM-DD>
 * @Why & What is modified <修改原因描述>
 * @Version V1.0
 */
@SuppressWarnings("serial")
public class PageQueryAction extends BaseAction {
	/**
	 * 对应查询业务类
	 */
	private PageQueryManage pageQueryManage = null;
	/**
	 * 返回结果
	 */
	private List<PageResult> pageResultList = null;

	/**
	 * 配置分页查询基础参数
	 */
	private List<Page> pageParam = null;
	/**
	 * 初始条件动态参数
	 */
	private String paramValue = "";
	/**
	 * 查询条件过滤,在action中配置
	 */
	private String queryFilter;
	
	private Map<Object, Object> ajaxBackData;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String pageQuery() {
		pageResultList = new ArrayList<PageResult>();
		for (int i = 0; i < pageParam.size(); i++) {
			createCondition(i);
			PageResult pageResult = this.getPageQueryManage().pageQuery(
					pageParam.get(i));
			pageResultList.add(pageResult);
			pageParam.set(i, pageResultList.get(i).getPage());
		}
		return SUCCESS;
	}
	
	public String gotoQuery() {
		return SUCCESS;
	}
	
	/**
	 * ajax分页查询
	 * 
	 * @return
	 */
	public String pageQueryAjax() {
		//获取datatables参数
		HttpServletRequest request = this.getRequest();
	    
		//第几页
	    String pageNo = request.getParameter("pager.pageNo");
	    //数据长度
	    String pageSize = request.getParameter("pager.pageSize");
	    //排序字段
	    String sort = request.getParameter("sort");
	    //排序方向
	    String direction = request.getParameter("direction");
	    
		pageResultList = new ArrayList<PageResult>();
		createCondition(0);
		Page tempPage = pageParam.get(0);
		PageUtil.createPageAjax(tempPage, pageNo, pageSize, sort, direction);
		PageResult pageResult = this.getPageQueryManage().pageQueryAjax(tempPage);
		
		
		ajaxBackData = new HashMap<Object, Object>();
		ajaxBackData.put("rows", pageResult.getResultList());
		ajaxBackData.put("pager.pageNo", pageNo);//当前页
		ajaxBackData.put("pager.totalRows", pageResult.getPage().getTotalCount());//总记录
		return SUCCESS;
	}

	

	/**
	 * 根据页面查询条件和该数据窗口的初始查询条件构造完整的查询条件
	 */
	private void createCondition(int index) {
		// 已经加载后的条件（包括查询条件）
		String condition = pageParam.get(index).getCondition();
		// 如果第一次访问，检测当前请求是否加载了初始条件，如果为空那么没有加载初始条件，开始解析加载
		if (condition == null || condition.equals("")) {
			// 检查初始条件1
			String initCondition = pageParam.get(index).getInitCondition();
			
			if (initCondition != null && !initCondition.equals("")) {
				// 初始参数不为空
				if (initCondition.indexOf("$") >= 0) {
					// 获取多个动态参数(注：约定好。initCondition中有多个动态参数时，动态参数之间用ands隔开
					// 为了避免非动态参数连接问题如（orgCode like
					// '${session.userInfo.orgCode}%'
					// and orgType='1' and orgLevel = '5'）)
					if (initCondition.indexOf(" ands ") >= 0) {
						String initConditionStr[] = initCondition
								.split(" ands ");
						initCondition = "";
						for (int j = 0; j < initConditionStr.length; j++) {
							String trendsNameAll = initConditionStr[j]
									.substring(
											initConditionStr[j].indexOf("$"),
											initConditionStr[j].indexOf("}") + 1);
							String trendsName = trendsNameAll.substring(
									trendsNameAll.indexOf("{") + 1,
									trendsNameAll.indexOf("}"));
							if (trendsName.equals("paramValue")) {
								initConditionStr[j] = initConditionStr[j]
										.replace(trendsNameAll,
												this.getParamValue());
								initCondition = initCondition
										+ initConditionStr[j] + " and ";

							} else {
								String[] trendsStrs = trendsName.split("\\.");
								if (trendsStrs[0].equals("session")) {
									Object sessionObj = ServletActionContext
											.getRequest().getSession()
											.getAttribute(trendsStrs[1]);
									if (null != sessionObj) {
										try {
											String sessionVal = BeanUtils
													.getProperty(sessionObj,
															trendsStrs[2]);
											initConditionStr[j] = initConditionStr[j]
													.replace(trendsNameAll,
															sessionVal);
											initCondition = initCondition
													+ initConditionStr[j]
													+ " and ";
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (NoSuchMethodException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}

						}
						condition = initCondition.substring(0,
								initCondition.length() - 4);
					} else {
						// 有动态参数(单个动态参数)
						// 截取参数
						String trendsNameAll = initCondition.substring(
								initCondition.indexOf("$"),
								initCondition.indexOf("}") + 1);
						String trendsName = trendsNameAll.substring(
								trendsNameAll.indexOf("{") + 1,
								trendsNameAll.indexOf("}"));
						if (trendsName.equals("paramValue")) {
							condition = initCondition.replace(trendsNameAll,
									this.getParamValue());
						} else {
							String[] trendsStrs = trendsName.split("\\.");
							if (trendsStrs[0].equals("session")) {
								Object sessionObj = ServletActionContext
										.getRequest().getSession()
										.getAttribute(trendsStrs[1]);
								if (null != sessionObj) {
									try {
										String sessionVal = BeanUtils
												.getProperty(sessionObj,
														trendsStrs[2]);
										condition = initCondition.replace(
												trendsNameAll, sessionVal);
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (NoSuchMethodException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}

					}
				} else {
					condition = initCondition;
				}
			}
		} else {// 如果已经加载了初始条件，直接使用隐藏条件
			try {
				// 解密查询条件
				condition = EncryptUtils.decryptAES(condition);
			} catch (EncryptException ex) {
				throw new BusinessException("nvce.url.filter.err");
			}
		}

		// 获得数据窗口外部设置查询条件
		String queryCondition = analyzingQueryParam("query[" + index + "]_");
		if (queryCondition.equals("")) {
			pageParam.get(index).setCondition(condition);
		} else {
			pageParam.get(index).setCondition(
					condition + " and " + queryCondition);
		}
		// --新增加密内容
		// pageParam.get(i).setCondition(EncryptUtils.encryptAES(pageParam.get(i).getCondition()));
		// condition = EncryptUtils.encryptAES(condition);
		// pageParam.setCondition(condition);
	}

	
	
	/**
	 * 根据页面查询条件和该数据窗口的初始查询条件构造完整的查询条件
	 */
	private void createCondition() {
		// 已经加载后的条件（包括查询条件）
		String condition = pageParam.get(0).getCondition();
		// 如果第一次访问，检测当前请求是否加载了初始条件，如果为空那么没有加载初始条件，开始解析加载
		if (condition == null || condition.equals("")) {
			// 检查初始条件
			String initCondition = pageParam.get(0).getInitCondition();
			if (initCondition != null && !initCondition.equals("")) {
				// 初始参数不为空
				if (initCondition.indexOf("$") >= 0) {
					// 获取多个动态参数(注：约定好。initCondition中有多个动态参数时，动态参数之间用ands隔开
					// 为了避免非动态参数连接问题如（orgCode like
					// '${session.userInfo.orgCode}%'
					// and orgType='1' and orgLevel = '5'）)
					if (initCondition.indexOf(" ands ") >= 0) {
						String initConditionStr[] = initCondition
								.split(" ands ");
						initCondition = "";
						for (int j = 0; j < initConditionStr.length; j++) {
							String trendsNameAll = initConditionStr[j]
									.substring(
											initConditionStr[j].indexOf("$"),
											initConditionStr[j].indexOf("}") + 1);
							String trendsName = trendsNameAll.substring(
									trendsNameAll.indexOf("{") + 1,
									trendsNameAll.indexOf("}"));
							if (trendsName.equals("paramValue")) {
								initConditionStr[j] = initConditionStr[j]
										.replace(trendsNameAll,
												this.getParamValue());
								initCondition = initCondition
										+ initConditionStr[j] + " and ";

							} else {
								String[] trendsStrs = trendsName.split("\\.");
								if (trendsStrs[0].equals("session")) {
									Object sessionObj = ServletActionContext
											.getRequest().getSession()
											.getAttribute(trendsStrs[1]);
									if (null != sessionObj) {
										try {
											String sessionVal = BeanUtils
													.getProperty(sessionObj,
															trendsStrs[2]);
											initConditionStr[j] = initConditionStr[j]
													.replace(trendsNameAll,
															sessionVal);
											initCondition = initCondition
													+ initConditionStr[j]
													+ " and ";
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (NoSuchMethodException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}

						}
						condition = initCondition.substring(0,
								initCondition.length() - 4);
					} else {
						// 有动态参数(单个动态参数)
						// 截取参数
						String trendsNameAll = initCondition.substring(
								initCondition.indexOf("$"),
								initCondition.indexOf("}") + 1);
						String trendsName = trendsNameAll.substring(
								trendsNameAll.indexOf("{") + 1,
								trendsNameAll.indexOf("}"));
						if (trendsName.equals("paramValue")) {
							condition = initCondition.replace(trendsNameAll,
									this.getParamValue());
						} else {
							String[] trendsStrs = trendsName.split("\\.");
							if (trendsStrs[0].equals("session")) {
								Object sessionObj = ServletActionContext
										.getRequest().getSession()
										.getAttribute(trendsStrs[1]);
								if (null != sessionObj) {
									try {
										String sessionVal = BeanUtils
												.getProperty(sessionObj,
														trendsStrs[2]);
										if(sessionVal != null){
											condition = initCondition.replace(
													trendsNameAll, sessionVal);
										}
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (NoSuchMethodException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}

					}
				} else {
					condition = initCondition;
				}
			}
		} else {// 如果已经加载了初始条件，直接使用隐藏条件
			try {
				// 解密查询条件
				condition = EncryptUtils.decryptAES(condition);
			} catch (EncryptException ex) {
				throw new BusinessException("nvce.url.filter.err");
			}
		}

		// 获得数据窗口外部设置查询条件
		String queryCondition = analyzingQueryParam("query_");
		if (queryCondition.equals("")) {
			pageParam.get(0).setCondition(condition);
		} else {
			if(condition == null || condition.equals("")){
				pageParam.get(0).setCondition(queryCondition);
			}else{
				pageParam.get(0).setCondition(
						condition + " and " + queryCondition);
			}
			
		}
		// --新增加密内容
		// pageParam.get(i).setCondition(EncryptUtils.encryptAES(pageParam.get(i).getCondition()));
		// condition = EncryptUtils.encryptAES(condition);
		// pageParam.setCondition(condition);
	}
	
	
	
	/**
	 * 查询条件特殊字符串过滤
	 */
	private static final String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|"
			+ "char|declare|sitename|net user|xp_cmdshell|;|or|+|,|like'|and|exec|execute|insert|create|drop|"
			+ "table|from|grant|use|group_concat|column_name|"
			+ "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|"
			+ "chr|mid|master|truncate|char|declare|or|;|--|+|,|like|//|/|%|#";

	/**
	 * 根据页面传入参数解析形成查询条件构造hql语句
	 * 要构造hql与的条件必须以“query_”开头，以“_begin_dateTime，_end_dateTime”结尾，日期类型区间查询。
	 * 以“_begin，_end”结尾是数字类型区间查询，以“_like”结尾执行模糊查询。
	 */
	public String analyzingQueryParam(String prefix) {
		String hqlCondition = "";
		HttpServletRequest request = this.getRequest();
		StringBuffer bf = new StringBuffer();
		String paraName;
		// --新增，参数值完整字符串，用于过滤sql注入内容
		StringBuffer valuesBf = new StringBuffer();
		java.util.Enumeration<?> em = request.getParameterNames();
		while (em.hasMoreElements()) {
			paraName = em.nextElement().toString();
			if (paraName.toLowerCase().startsWith(prefix)) {
				if (request.getParameter(paraName) != null
						&& !(request.getParameter(paraName).trim().equals(""))
						&& this.checkQueryFilter(paraName)) {// --新增过滤参数
					// --获取完整的查询条件值
					String paraVal = request.getParameter(paraName).trim();
					valuesBf.append(paraVal);
					if(paraName.indexOf("$")>0){
						paraName = paraName.replace("$", ".");
					}
					if (paraName.endsWith("_begin_date")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 11)
								+ " >= to_date('"
								+ paraVal
								+ "','yyyy-mm-dd') and ");
					} else if (paraName.endsWith("_end_date")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 9)
								+ " <= to_date('"
								+ paraVal
								+ "','yyyy-mm-dd') and ");
					} else if (paraName.endsWith("_begin")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 6)
								+ " >= "
								+ paraVal
								+ " and ");
					} else if (paraName.endsWith("_end")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 4)
								+ " <= "
								+ paraVal
								+ " and ");
					} else if (paraName.endsWith("_begin_dateTime")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 15)
								+ " >= '"
								+ paraVal
								+ "' and ");
					} else if (paraName.endsWith("_end_dateTime")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 13)
								+ " <= '"
								+ paraVal
								+ "' and ");
					} else if (paraName.endsWith("_like")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 5)
								+ " like '%"
								+ paraVal
								+ "%' and ");
					} else if (paraName.endsWith("_beginlike")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 10)
								+ " like '"
								+ paraVal
								+ "%' and ");
					} else if (paraName.endsWith("_endlike")) {
						bf.append(paraName.substring(prefix.length(),
								paraName.length() - 8)
								+ " like '%"
								+ paraVal
								+ "' and ");
					} else {
						bf.append(paraName.substring(prefix.length(),
								paraName.length())
								+ " = '"
								+ paraVal
								+ "' and ");
					}
				}
			}
			// --验证是否有非法注入字符
			if (this.sqlValidate(valuesBf.toString())) {
				throw new BusinessException("nvce.sql.filter.err");
			}

			if (bf.length() > 4) {
				hqlCondition = bf.toString().substring(0, bf.length() - 4);
			}
		}
		return hqlCondition;
	}

	/**
	 * 检查查询字符串是否是有效的条件
	 */
	private boolean checkQueryFilter(String queryParam) {
		boolean retn = false;
		if (this.queryFilter == null || this.queryFilter.equals("")) {
			retn = true;
		} else {
			String[] strs = this.queryFilter.split(",");
			for (String a : strs) {
				if (a.equals(queryParam)) {
					retn = true;
					break;
				}
			}
		}
		return retn;
	}

	/**
	 * 查询条件内容过滤
	 */
	private boolean sqlValidate(String queryParam) {
		queryParam = queryParam.toLowerCase();// 统一转为小写

		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (queryParam.indexOf(badStrs[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	
	
	public List<PageResult> getPageResultList() {
		return pageResultList;
	}

	public void setPageResultList(List<PageResult> pageResultList) {
		this.pageResultList = pageResultList;
	}

	public List<Page> getPageParam() {
		return pageParam;
	}

	public void setPageParam(List<Page> pageParam) {
		this.pageParam = pageParam;
	}

	public PageQueryManage getPageQueryManage() {
		return pageQueryManage;
	}

	public void setPageQueryManage(PageQueryManage pageQueryManage) {
		this.pageQueryManage = pageQueryManage;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Map<Object, Object> getAjaxBackData() {
		return ajaxBackData;
	}

	public void setAjaxBackData(Map<Object, Object> ajaxBackData) {
		this.ajaxBackData = ajaxBackData;
	}
	
}
