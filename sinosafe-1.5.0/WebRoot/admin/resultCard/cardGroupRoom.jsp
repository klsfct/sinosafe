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
<link rel="stylesheet" type="text/css" id="skin" prePath="../"
	scrollerY="true" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<!--框架必需end-->
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="groupCardByArea" namespace="/admin/resultCard">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="paramterAreaId" name="areaId"></s:hidden>
		<s:hidden id="examPlaceId" name="examPlaceId"></s:hidden>

		<span>选择场次：</span>
		<s:select list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}"
			name="subject" listKey="key" listValue="value"></s:select>

		<div class="box_tool_line"></div>


		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>




	</s:form>
	<div class="padding_right5 padding_top5">
		<div id="scrollContent">
			<table class="tableStyle" mode="list" fixedCellHeight="true">
				<tr>
					<th width="6%">序号</th>
					<th width="30%">考点名称</th>
					<th width="8%">考场</th>
					<th width="8%">已扫题卡数</th>
				</tr>
				<s:iterator value="resultList" status="st1">
					<tr>
						<td><s:property value="#st1.count" /></td>
						<td><s:property value="resultList[#st1.index][1]" /></td>
						<td><s:property value="resultList[#st1.index][2]" /></td>
						<td><s:property value="resultList[#st1.index][3]" /></td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function setArea(areaId) {
			$("#paramterAreaId").val(areaId);
			queryData();
		}
		function queryData() {
			$("#paramFormId").submit();
		}
		function groupCardByRoom(examPlaceId) {
			$("#paramFormId").attr("action", "groupCardByRoom.action");
			$("#examPlaceId").val(examPlaceId);
			$("#paramFormId").submit();
		}
	</script>
</body>
</html>
