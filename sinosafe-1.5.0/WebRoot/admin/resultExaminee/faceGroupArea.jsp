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
	<s:form id="paramFormId" name="paramForm" method="post"
		action="groupFaceByArea" namespace="/admin/resultExaminee">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="paramterAreaId" name="areaId"></s:hidden>
		<%-- <span>选择试卷：</span>
		<s:select list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}"
			name="subject" listKey="key" listValue="value"></s:select> --%>
			<span>当前场次：</span><select id='subjectId' name='subject'></select>
			<span style="padding-left:1em;"></span>
		<s:if test="level==2">
			<span>选择分组条件：</span>
			<s:select list="#{1:'按考区分组',2:'按考点分组'}" name="groupField"
				listKey="key" listValue="value" headerKey="" headerValue="==请选择=="></s:select>
			<span style="padding-left:1em;"></span>
		</s:if>
		 <button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
	</s:form>
	<div class="padding_right5 padding_top5">
		<div id="scrollContent">
			<table class="tableStyle" mode="list" fixedCellHeight="true">
				<tr>
					<th width="3%">序号</th>
					<th width="15%">地区</th>
					<th width="8%">人数</th>
					<th width="8%">进场人数</th>
					<th width="8%">已通过</th>
					<th width="8%">未通过</th>
					<th width="8%">已处理</th>
					<th width="8%">未处理</th>
					<th width="8%">通过比例</th>
					<th>图例</th>
				</tr>
				<s:iterator value="resultList" status="st1">
					<tr>
						<td><s:property value="#st1.count" /></td>
						<td><s:property value="resultList[#st1.index][1]" /></td>
						<td><s:property value="resultList[#st1.index][2]" /></td>
						<td><s:property value="resultList[#st1.index][3]" /></td>
						<td><span style="color:green"><s:property
									value="resultList[#st1.index][4]" /></span></td>
						<td><span style="color:red"><s:property
									value="resultList[#st1.index][5]" /></span></td>
						<td><s:property value="resultList[#st1.index][7]" /></td>
						<td><s:property value="resultList[#st1.index][6]" /></td>
						<td><s:property value="resultList[#st1.index][8]" /></td>
						<td><s:if test="#st1.isOdd()">
								<div
									style="width:<s:property value="resultList[#st1.index][8]" />;height:20px;border-style:solid;border-width:1px;border-color:black;background-color:#7cb5ec"></div>
							</s:if> <s:else>
								<div
									style="width:<s:property value="resultList[#st1.index][8]" />;height:20px;border-style:solid;border-width:1px;border-color:black;background-color:#90ed7d"></div>
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
