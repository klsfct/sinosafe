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
		action="findDeviceHandList" namespace="/admin/deviceInfo">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="areaId" name="query[0]_examPlace.examArea_like"></s:hidden>

		<span>IMEI编号：</span>
		<s:textfield name="query[0]_imeiNo" maxlength="20"
			cssStyle="width:180px"></s:textfield>

		<div class="box_tool_line"></div>

		<span>考点名称：</span>
		<s:textfield name="query[0]_examPlaceName_like" maxlength="20"
			cssStyle="width:180px"></s:textfield>

		<div class="box_tool_line"></div>

		<span>电量：</span>
		<s:select list="#{0:'危险电量',33:'一格电量',66:'三格电量',100:'满格电量'}"
			name="query[0]_dumpEnergy" listKey="key" listValue="value"
			headerKey="" headerValue="===请选择==="></s:select>

		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>

	</s:form>
	<div id="scrollContent" class="padding_right5">
		<table class="tableStyle" mode="list">
			<tr>
				<th width="12%">设备IMEI编号</th>
				<th width="10%">省（区、市）</th>
				<th width="15%">考区</th>
				<th width="25%">考点</th>
				<th width="10%">剩余电量</th>
				<th width="10%">信号强度</th>
				<th>激活时间</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					<td><s:property value="imeiNo" /></td>
					<td><s:property value="examProvinceName" /></td>
					<td><s:property value="examAreaName" /></td>
					<td><s:property value="examPlaceName" /></td>
					<td style="text-align: center;"><s:if test="dumpEnergy==0">
							<img src="../libs/user/images/dianliang0.jpg" />
						</s:if> <s:elseif test="dumpEnergy==33">
							<img src="../libs/user/images/dianliang1.jpg" />
						</s:elseif> <s:elseif test="dumpEnergy==66">
							<img src="../libs/user/images/dianliang2.jpg" />
						</s:elseif> <s:elseif test="dumpEnergy==100">
							<img src="../libs/user/images/dianliang3.jpg" />
						</s:elseif></td>
					<td style="text-align: center;"><s:if test="signalStrength==0">
							<img src="../libs/user/images/xinhao0.gif" />
						</s:if> <s:elseif test="signalStrength==1">
							<img src="../libs/user/images/xinhao1.gif" />
						</s:elseif> <s:elseif test="signalStrength==2">
							<img src="../libs/user/images/xinhao2.gif" />
						</s:elseif> <s:elseif test="signalStrength==3">
							<img src="../libs/user/images/xinhao2.gif" />
						</s:elseif> <s:elseif test="signalStrength==4">
							<img src="../libs/user/images/xinhao3.gif" />
						</s:elseif> <s:elseif test="signalStrength==5">
							<img src="../libs/user/images/xinhao3.gif" />
						</s:elseif></td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" />
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
