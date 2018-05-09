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
<!--系统start-->
<script src="../libs/js/sinoSystem.js" type="text/javascript"></script>
<!--系统end-->
<!--弹窗组件start-->
<script type="text/javascript" src="../libs/js/popup/drag.js"></script>
<script type="text/javascript" src="../libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findExamPlaceList" namespace="/admin/examPlaceInfo">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="areaId" name="query[0]_examPlace.examArea_like"></s:hidden>
		<button type="button" onclick="batchGetPoint()">
			<span class="icon_add">批量获取坐标</span>
		</button>
	</s:form>
	<div id="scrollContent" class="padding_right5">
		<table class="tableStyle" mode="list">
			<tr>
				<!-- <th width="6%">设备序列号</th>
				<th width="9%">设备名称</th>
				<th width="4%">设备编码</th>
				<th width="10%">省（区、市）</th>
				<th width="9%">考区</th>
				<th width="15%">考点</th>
				<th width="5%">类别</th>
				<th width="5%">是否注册</th>
				<th width="12%">录入时间</th>
				<th width="12%">注册时间</th>
				<th width="3%">在线状态</th>
				<th width="9%">操作</th> -->
				<th>考点代码</th>
				<th>省名称</th>
				<th>考区名称</th>
				<th>考点名称</th>
				<th>考点人数</th>
				<th>考点地址</th>
				<th>经度</th>
				<th>纬度</th>
				<th>是否启用</th>
				<th>操作</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr align="center">
					<td><s:property value="examPlace" /></td>
					<td><s:property value="examProvinceName" /></td>
					<td><s:property value="examAreaName" /></td>
					<td><s:property value="examPlaceName" /></td>
					<td><s:property value="examineeSum" /></td>
					<td><s:property value="examAddr" /></td>
					<td><s:property value="pointLng" /></td>
					<td><s:property value="pointLat" /></td>
					<td>
						<s:if test="isEnabled==0">
							未启用
						</s:if> 
						<s:if test="isEnabled==1">
							<font color="green">启用</font>
						</s:if>
						</td>
					<td>
					<%-- <a href="javascript:editData(<s:property value="deviceSerial"/>);">修改</a> --%>
					<a href="javascript:getPoint(<s:property value="examPlace"/>);">获取坐标</a> |
					<a href="http://api.map.baidu.com/lbsapi/getpoint/?qq-pf-to=pcqq.c2c" target="_blank">人工校对</a>
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
		function getPoint(examPlace){
			if(examPlace!=""){
				window.location.href = "${basePath}/admin/examPlaceInfo/getPointByExamAddr.action?examPlaceVo.examPlace=" + examPlace;
			}
			else{
				alert("考点代码为空！");
			}
		}
		
		function batchGetPoint(){
			window.location.href = "${basePath}/admin/examPlaceInfo/batchGetPoint.action";
		}
	</script>
</body>
</html>
