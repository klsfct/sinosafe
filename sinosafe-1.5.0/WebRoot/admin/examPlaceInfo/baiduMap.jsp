<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>工作人员定位监控</title>
<meta http-equiv="pragma" content="no-cache"></meta>
<meta http-equiv="cache-control" content="no-cache"></meta>
<meta http-equiv="expires" content="0"></meta>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
<meta http-equiv="description" content="This is my page"></meta>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="../libs/js/jquery.js"></script>

 <!-- baiduMap api -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=mWfqDsMxe8HLhmIFt7NmFkXG9cfNjhkr"></script>
<script type="text/javascript" src="../libs/map/map.js"></script>
<link rel="stylesheet" href="../libs/map/css/common.css" type="text/css"/>
</head>
<body>
<div class="main">
	<div class="trunk" id="trunk">
		<div class="trackControl visible">
			<div class="map" id="allmap" style="overflow: hidden;background-color: rgb(243, 241, 236);color: rgb(0, 0, 0);text-align: left;z-index: 0;"><div>
			<!-- 左面搜索框start -->
  			<div class="manageControl">
			    <div class="manageTop">
				    <div class="serviceName">
				     工作人员定位监控
				    </div>
			    	<div class="toggleInManage" ></div>
			   	</div>
			   	<!-- 搜索框内容start -->
			   	<div class="collapse in" id="manageBottom">
			    	<div class="manageBottom">
			     		<div class="monitor visible">
			     			<!-- 搜索框input  start -->
			      			<div class="monitorSearch">
			       				<input class="searchInputMonitor" placeholder="请输入关键字" />
			       				<img src="../libs/map/images/searchicon.png" class="searchBtnMonitor" />
			       				<div class="lineMonitor"></div>
			       				<img src="../libs/map/images/clearsearch.png" class="clearSearchBtnMonitor hideCommon" />
			      			</div>
			      			<!-- 搜索框input  end -->
			      			<!-- 搜索框全部 已签到 未签到 start-->
			      			<div class="monitorList">
			       				<div class="monitorAll boderBottom">
			        				全部( <span id="allNum"> </span> )
			      
			       				</div>
			       				<div class="monitorOnline">
						        	已签到(  <span id="onlineNum"> </span> )
						       	</div>
						       	<div class="monitorOffline">
						       		未签到( <span id="offlineNum"> </span> )
						       	</div>
						       	<div class="monitorBottomLineLeft"></div>
						    </div>
							<!-- 搜索框全部 已签到 未签到 end-->
							<!-- 搜索框全部页面 start-->
						    <div class="monitorAllContent visible">
								<div class="monitorFrame"></div>  
						    </div>
							<!-- 搜索框已签到页面 start-->
						    <div class="monitorOnlineContent hidden">
						    	<div class="monitorFrame"></div> 
						    </div>
							<!-- 搜索框未签到页面 start-->
						    <div class="monitorOfflineContent hidden">
						       <div class="monitorFrame"></div>
						    </div>
			     		</div>
			    	</div>
			    </div><!-- 搜索框内容end-->
			</div><!-- 左面搜索框end -->
		</div>
	</div>
</div>
</body>
</html>