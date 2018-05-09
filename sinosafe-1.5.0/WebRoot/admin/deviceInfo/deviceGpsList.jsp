<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache"></meta>
<meta http-equiv="cache-control" content="no-cache"></meta>
<meta http-equiv="expires" content="0"></meta>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
<meta http-equiv="description" content="This is my page"></meta>
<!--框架必需start-->
<script type="text/javascript" src="../libs/js/jquery.js"></script>
<script type="text/javascript" src="../libs/js/framework.js"></script>
<link href="../libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="../" />
<link rel="stylesheet" type="text/css" id="customSkin" />

<!--框架必需end-->
<!--数字分页start-->
<script type="text/javascript" src="../libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->
<!--表单异步提交start-->
<script src="../libs/js/form/form.js" type="text/javascript"></script>
<!--表单异步提交end-->
<!--弹窗组件start-->
<script type="text/javascript" src="../libs/js/popup/drag.js"></script>
<script type="text/javascript" src="../libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findDeviceGpsList" namespace="/admin/deviceInfo">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="areaId" name="query[0]_examPlace.examArea_like"></s:hidden>
		
		<button type="button" onclick="getDeviceGps()">
			<span class="icon_add">获取设备</span>
		</button>
		<button type="button" onclick="setBeginPonit()">
			<span class="icon_add">批量设置起点坐标</span>
		</button>
		
	</s:form>
	<div id="scrollContent" class="padding_right5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>设备名称</th>
				<th>设备描述</th>
				<th>所属考点</th>
				<th>起点名称</th>
				<th>起点围栏</th>
				<th>终点围栏</th>
				<th>操作</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr align="center">
					<td><s:property value="entityName" /></td>
					<td><s:property value="entityDesc" /></td>
					<td><s:property value="examPlaceVo.examPlaceName" /></td>
					<td><s:property value="beginName" /></td>
					<td>
						<s:if test="beginFenceId==null">
							<font color="red">未设置</font>
						</s:if> 
						<s:else>
							<font color="green">已设置</font>
						</s:else>
					</td>
					<td>
						<s:if test="endFenceId==null">
							<font color="red">未设置</font>
						</s:if> 
						<s:else>
							<font color="green">已设置</font>
						</s:else>
					</td>
					<td>
					<a href="javascript:setExamPlace('<s:property value="gpsId"/>');">绑定考点</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<s:include value="../frame/pageInfo.jsp"></s:include>
	<script type="text/javascript">
		function setArea(areaId) {
			$("#areaId").val(areaId);
			queryData();
		}
		function queryData() {
			$("#paramFormId").submit();
		}
		function getDeviceGps(){
			window.location.href = "${basePath}/admin/deviceInfo/getDeviceGps.action";
		}
		function setExamPlace(dataId){
			var diag = new Dialog();
			diag.Title = "绑定考点";
			diag.URL = "../deviceInfo/gotoSetExamPlace.action?deviceGpsInfoVo.gpsId=" + dataId;
			diag.show();
		}
		function setBeginPonit(){
			var diag = new Dialog();
			diag.Title = "批量设置起点坐标";
			diag.URL = "../deviceInfo/gotoSetBeginPonit.action";
			diag.show();
		}
		
	</script>
</body>
</html>
