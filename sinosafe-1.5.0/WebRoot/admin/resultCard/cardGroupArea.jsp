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
		<s:hidden id="examPlaceId" name="examPlaceVo.examPlace"></s:hidden>
		<div class="padding_right5 padding_top5">
		<span>场次：</span>
		<s:select list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}"
			name="subject" listKey="key" listValue="value"></s:select>
		<s:if test="level==2">
			<span>选择分组条件：</span>
			<s:select list="#{1:'按考区分组',2:'按考点分组'}" name="groupField"
				listKey="key" listValue="value" headerKey="" headerValue="==请选择=="></s:select>
		</s:if>
		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
		<span style="color:red;">*点击考点名称可以统计考点内每个考场的扫描答题卡数量。</span>
		</div>
	</s:form>
	<div class="padding_right5 padding_top5">
		<div id="scrollContent">
			<table class="tableStyle" mode="list" fixedCellHeight="true">
				<tr>
					<th width="3%">序号</th>
					<th width="12%">地区</th>
					<th width="8%">总题卡数</th>
					<th width="8%">已扫题卡数</th>
					<th width="10%">已扫比例</th>
					<th>图例</th>
				</tr>
				<s:iterator value="resultList" status="st1">
					<tr>
						<td><s:property value="#st1.count" /></td>
						<td><s:if test="groupField==2 || #session.loginSession.level==3">
								<a
									href="javascript:groupCardByRoom(<s:property value="resultList[#st1.index][0]" />);"><s:property
										value="resultList[#st1.index][1]" /></a>
							</s:if> <s:else>
								<s:property
										value="resultList[#st1.index][1]" />
							</s:else></td>
						<td><s:property value="resultList[#st1.index][2]" /></td>
						<td><s:property value="resultList[#st1.index][3]" /></td>
						<td><s:property value="resultList[#st1.index][4]" /></td>
						<td><s:if test="#st1.isOdd()">
								<div
									style="width:<s:property value="resultList[#st1.index][4]" />;height:20px;border-style:solid;border-width:1px;border-color:black;background-color:#7cb5ec"></div>
							</s:if> <s:else>
								<div
									style="width:<s:property value="resultList[#st1.index][4]" />;height:20px;border-style:solid;border-width:1px;border-color:black;background-color:#90ed7d"></div>
							</s:else></td>
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
