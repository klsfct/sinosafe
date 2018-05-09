<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper"%>
<%@ page import="java.util.concurrent.locks.*"%>
<%@ page import="com.sinotn.common.PropertiesReader"%>
<%@ page import="java.io.*"%>
<%@ page import="org.json.simple.*"%>
<%@page import="com.sinotn.common.FileUtils"%>
<%@page import="org.apache.struts2.ServletActionContext" %>
<%
	//Struts2 请求 包装过滤器
	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
	//获得上传的文件名
	String fileName = wrapper.getFileNames("imgFile")[0];
	//获得未见过滤器
	File file = wrapper.getFiles("imgFile")[0];
	//定义允许上传的文件扩展名
	HashMap<String, String> extMap = new HashMap<String, String>();
	extMap.put("image", "gif,jpg,jpeg,png,bmp");
	extMap.put("file", "doc,docx,xls,xlsx,zip,rar");
	//检查扩展名
	String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
			.toLowerCase();
	boolean bFlag = false;
	//获得相对目录
	String uploadPath = null;
	for (String extFilter : extMap.keySet()) {
		if (Arrays.<String> asList(extMap.get(extFilter).split(","))
				.contains(fileExt)) {
			bFlag = true;
			String websiteContext = PropertiesReader.getParaValue("cms.content.path");
			uploadPath = websiteContext + "/attached/" + extFilter + "/";
			
			FileUtils.mkDir(ServletActionContext.getServletContext().getRealPath(uploadPath));
		}
	}
	if (!bFlag) {
		out.println(getError("上传文件扩展名是不允许的扩展名。\n上传图片，格式只允许"
				+ extMap.get("image") + "格式！\n上传附件，格式只允许"
				+ extMap.get("file") + "格式！"));
		return;
	}
	//验证文件大小
	//最大文件大小
	long maxSize = 4 * 1024 * 1024;
	long realitySize = file.length();
	if (realitySize > maxSize) {
		out.println(getError("上传图片文件或者附件大小不能大于4M！"));
		return;
	}
	//----------重新构建上传文件名---------
	final Lock lock = new ReentrantLock();
	String newName = null;
	lock.lock();
	try {
		//加锁为防止文件名重复
		newName = System.currentTimeMillis()
				+ fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
	} finally {
		lock.unlock();
	}//------锁结束---------
	//获取文件输出流
	FileOutputStream fos = new FileOutputStream(request.getSession()
			.getServletContext().getRealPath("/")
			+ uploadPath + newName);
	//设置 KE 中的图片文件地址
	String newFileName = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/" + uploadPath + newName;
	byte[] buffer = new byte[1024];
	//获取内存中当前文件输入流
	InputStream in = new FileInputStream(file);
	try {
		int num = 0;
		while ((num = in.read(buffer)) > 0) {
			fos.write(buffer, 0, num);
		}
	} catch (Exception e) {
		e.printStackTrace(System.err);
	} finally {
		in.close();
		fos.close();
	}//发送给 KE 
	JSONObject obj = new JSONObject();
	obj.put("error", 0);
	obj.put("url", newFileName);
	out.println(obj.toString());
%>
<%!private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}%>