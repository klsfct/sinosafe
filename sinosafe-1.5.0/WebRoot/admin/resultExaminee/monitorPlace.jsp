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
<link href="../libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="../"
	scrollerY="true" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<!--框架必需end-->

</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="paramterAreaId" name="areaId"></s:hidden>
		<%-- <s:select list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}"
			name="subject" listKey="key" listValue="value"></s:select> --%>
			<span>当前场次：</span><select id='subjectId' name='subject'></select>
			<span style="padding-left:1em;"></span>
		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
	</s:form>
	<div id="scrollContent" class="padding_right5 padding_top5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>考点名称</th>
				<th>通道IMEI</th>
				<!-- <th>通道类型</th> -->
				<!-- <th>精度设置</th> -->
				<th>安检人数</th>
				<th>通过人数</th>
				<th>未通过人数</th>
			</tr>
			<s:iterator value="resultList" status="st1">
				<tr>
					<td><s:property value="resultList[#st1.index][0]" /></td>
					<td><s:property value="resultList[#st1.index][1]" /></td>
					<!-- <td><s:property value="resultList[#st1.index][2]" /></td> -->
					<!-- <td><s:property value="resultList[#st1.index][3]" /></td> -->
					<td><s:property value="resultList[#st1.index][4]" /></td>
					<td><span style="color:green"><s:property
								value="resultList[#st1.index][6]" /></span></td>
					<td><span style="color:red"><s:property
								value="resultList[#st1.index][5]" /></span></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<script type="text/javascript">
		function setArea(areaId) {
			$("#paramterAreaId").val(areaId);
			queryData();
		}
		function queryData() {
			$("#paramFormId").submit();
		}
		$(function(){
			var subejctSelectHtml = "";
			$.get("${basePath}/admin/resultExaminee/getSubjectData.action",function(data){
				var subjectList = eval("(" + data + ")");
				for ( var i = 0; i < subjectList.length; i++) {
					subejctSelectHtml +="<option value="+subjectList[i].id+">" + subjectList[i].name;
					subejctSelectHtml +="</option>";
				}
				$("#subjectId").html(subejctSelectHtml);
			});
		}) 
		 
	</script>
</body>
</html>
