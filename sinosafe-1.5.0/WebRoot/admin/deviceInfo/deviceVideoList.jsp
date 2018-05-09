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
		action="findDeviceVideoList" namespace="/admin/deviceInfo">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="areaId" name="query[0]_examPlace.examArea_like"></s:hidden>

		<span>设备类别：</span>
		<s:select list="@com.examsafety.service.ConstantsService@getVideoDeviceTypeMap()"
			name="query[0]_deviceType" listKey="key" listValue="value" cssStyle="height:23px;"
			headerKey="" headerValue="===请选择==="></s:select>
		<div class="box_tool_line"></div>
		<span>考点名称：</span>
		<s:textfield name="query[0]_examPlace.examPlaceName_like" maxlength="20"
			cssStyle="width:180px"></s:textfield>
		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
		<div class="box_tool_line"></div>
		<button type="button" onclick="addData()">
			<span class="icon_add">新增</span>
		</button>
		<button type="button" onclick="batchRegData()">
			<span class="icon_add">批量注册</span>
		</button>
		<button type="button" onclick="videoMonitor()">
			<span class="icon_add">视频监控</span>
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
				<th>设备序列号</th>
				<th>设备名称</th>
				<th>设备编码</th>
				<th>省（区、市）</th>
				<th>考区</th>
				<th>考点</th>
				<th>类别</th>
				<th>是否注册</th>
				<th>录入时间</th>
				<th>注册时间</th>
				<th>在线状态</th>
				<th>操作</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr align="center">
					<td><s:property value="deviceSerial" /></td>
					<td><s:property value="deviceName" /></td>
					<td><s:property value="deviceNumber" /></td>
					<td><s:property value="examPlaceVo.examProvinceName" /></td>
					<td><s:property value="examPlaceVo.examAreaName" /></td>
					<td><s:property value="examPlaceVo.examPlaceName" /></td>
					<td><s:property value="deviceTypeName" /></td>
					<td>
						<s:if test="regFlag==1">
							<font color="green">已注册</font>
						</s:if> 
						<s:if test="regFlag==0">
							未注册
						</s:if>
						<s:if test="regFlag==2">
							<font color="red">注册失败</font>
						</s:if>
					</td>
					<td><s:date name="inputTime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td><s:date name="regTime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td><s:if test="devStatus==0">
							不在线
						</s:if> 
						<s:if test="devStatus==1">
							<font color="green">在线</font>
						</s:if>
						</td>
					<td>
					<s:if test="regFlag==1">
							<a href="#">注册</a> |
						</s:if> 
						<s:if test="regFlag==0">
							<a href="javascript:regData(<s:property value="deviceSerial"/>);">注册</a> |
						</s:if>
						<s:if test="regFlag==2">
							<a href="javascript:regData(<s:property value="deviceSerial"/>);">注册</a> |
					</s:if>
					 
						
						<s:if test="regFlag==1">
							<a href="#">删除</a>  | 
						</s:if> 
						<s:if test="regFlag==2 || regFlag==0">
							<a href="javascript:delData(<s:property value="deviceSerial"/>);">删除</a>  | 
						</s:if>
						
					<a href="javascript:editData(<s:property value="deviceSerial"/>);">修改</a></td>
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
		function addData() {
			var diag = new Dialog();
			diag.Title = "新增视频设备信息";
			diag.URL = "../deviceInfo/gotoSaveDeviceVideo.action";
			diag.show();
		}
		function editData(dataId) {
			var diag = new Dialog();
			diag.Title = "修改视频设备信息";
			diag.URL = "../deviceInfo/gotoUpdateDeviceVideo.action?deviceVideoInfoVo.deviceSerial=" + dataId;
			diag.show();
		}
		// 注册设备
		function regData(serialData){
			window.location.href = "${basePath}/admin/deviceInfo/regDeviceVideo.action?deviceVideoInfoVo.deviceSerial=" + serialData;
		}
		// 删除设备
		function delData(serialData){
			window.location.href = "${basePath}/admin/deviceInfo/delDeviceVideo.action?serial=" + serialData;
		}
		// 批量注册
		function batchRegData(){
			window.location.href = "${basePath}/admin/deviceInfo/batchRegDeviceVideo.action";
		}
		//视频监控
		function videoMonitor(){
			window.open("${basePath}/admin/video/video.html");
		} 
		
	</script>
</body>
</html>
