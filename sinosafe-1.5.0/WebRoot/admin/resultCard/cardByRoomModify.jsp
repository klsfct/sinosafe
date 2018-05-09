<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统登录</title>
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
		action="findCardByRoom" namespace="/admin/resultCard">
		<s:hidden id="examRoomId" name="examRoom"></s:hidden>

		<s:if test="examPlaceList !=null">
			<span>选择考点：</span>
			<s:select list="examPlaceList" name="examPlaceVo.examPlace"
				listKey="examPlace" listValue="examPlaceName"></s:select>
		</s:if>
		<s:else>
			<s:hidden name="examPlaceVo.examPlace"></s:hidden>
		</s:else>
		<span>选择场次：</span>
		<s:select list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}"
			name="subject" listKey="key" listValue="value" headerKey=""
			headerValue="==请选择=="></s:select>


		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
		<br /><span style="color:red;font-size:18px">如遇到答题卡条形码破损或其他原因无法扫描登记，请使用本功能将该考场题卡补全，使用本功能必须确认手持机数据都已经上传完毕。</span>
	</s:form>
	<div id="scrollContent" class="padding_right5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>序号</th>
				<th>考点</th>
				<th>考场</th>
				<th>已扫题卡数</th>
				<th>处理</th>
			</tr>
			<s:iterator value="resultList" status="st1">
				<tr>
					<td><s:property value="#st1.count" /></td>
					<td><s:property value="resultList[#st1.index][1]" /></td>
					<td><s:property value="resultList[#st1.index][2]" /></td>
					<td><s:property value="resultList[#st1.index][3]" /></td>
					<td><a
						href="javascript:modifyCardByRoom(<s:property value="resultList[#st1.index][2]"/>);">点击补卡</a></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<script type="text/javascript">
		function queryData() {
			$("#paramFormId").submit();
		}
		//打印
		function modifyCardByRoom(examRoom) {
			$("#examRoomId").val(examRoom);
			$("#paramFormId").attr("action", "modifyCardByRoom.action");
			$("#paramFormId").submit();
		}
	</script>
</body>
</html>
