<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<link href="../libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="../" />
<link rel="stylesheet" type="text/css" id="customSkin" />

<!--框架必需end-->
<!--数字分页start-->
<script type="text/javascript" src="../libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findDeviceFaceList" namespace="/admin/deviceInfo">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="areaId" name="query[0]_examPlace.examArea_like"></s:hidden>

		<span>识别精度：</span>
		<s:select list="#{1:'较低',2:'中等',3:'较高',4:'极高'}"
			name="query[0]_verifyPrecision" listKey="key" listValue="value"
			headerKey="" headerValue="===请选择==="></s:select>


		<span>当前考试：</span>
		<s:select list="#{1:'科目一',2:'科目二',3:'科目三',4:'科目四'}"
			name="query[0]_subject" listKey="key" listValue="value" headerKey=""
			headerValue="===请选择==="></s:select>


		<%-- <span>安检通道：</span>
		<s:select
			list="#{0:'普通通道',1:'特殊通道',2:'测试普通通道',3:'测试特殊通道',5:'退场通道',6:'测试退场通道'}"
			name="query[0]_passType" listKey="key" listValue="value" headerKey=""
			headerValue="===请选择==="></s:select> --%>

		<div class="box_tool_line"></div>

		<span>考点名称：</span>
		<s:textfield name="query[0]_examPlaceName_like" maxlength="20"
			cssStyle="width:180px"></s:textfield>
		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>

	</s:form>
	<div id="scrollContent" class="padding_right5">
		<table class="tableStyle" mode="list">
			<tr>
				<th width="8%">设备IMEI编号</th>
				<th width="8%">省（区、市）</th>
				<th width="12%">考区</th>
				<th width="20%">考点</th>
				<th width="8%">识别精度</th>
				<th width="8%">当前考试</th>
				<th width="10%">安检通道</th>
				<th>激活时间</th>
				<th>上报时间</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					<td><s:property value="imeiNo" /></td>
					<td><s:property value="examProvinceName" /></td>
					<td><s:property value="examAreaName" /></td>
					<td><s:property value="examPlaceName" /></td>
					<td><s:if test="verifyPrecision==1">
						较低
					</s:if> <s:elseif test="verifyPrecision==2">
						中等
					</s:elseif> <s:elseif test="verifyPrecision==3">
						较高
					</s:elseif> <s:elseif test="verifyPrecision==4">
						极高
					</s:elseif></td>
					<td><s:if test="subject==1">
						科目一
					</s:if> <s:elseif test="subject==2">
						科目二
					</s:elseif> <s:elseif test="subject==3">
						科目三
					</s:elseif> 
					<s:elseif test="subject==4">
						科目四
					</s:elseif></td>
					<td><s:if test="passType==0">
						普通通道
					</s:if> <s:elseif test="passType==1">
						特殊通道
					</s:elseif> <s:elseif test="passType==2">
						测试普通通道
					</s:elseif> <s:elseif test="passType==3">
						测试特殊通道
					</s:elseif> <s:elseif test="passType==5">
						退场通道
					</s:elseif> <s:elseif test="passType==6">
						测试退场通道
					</s:elseif></td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td><s:date name="modifyDate" format="yyyy-MM-dd HH:mm:ss" />
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
	</script>
</body>
</html>
