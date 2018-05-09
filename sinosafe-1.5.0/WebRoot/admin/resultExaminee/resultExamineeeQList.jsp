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

<!--图片预览start-->
<script type="text/javascript" src="../libs/js/pic/imgPreview.js"></script>
<!--图片预览end-->
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findResultExaminee1QList" namespace="/admin/resultExaminee">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="headAreaId" name="query[0]_examArea_like" value="%{areaId}"></s:hidden>
		<s:hidden id="actionAreaId"  name="areaId"></s:hidden>
		<div class="padding_right5 padding_top5">
		<%-- <span>试卷：</span>
		<s:select list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}"
			id="subjectId" name="subject" listKey="key" listValue="value"></s:select> --%>
			<span>当前场次：</span><select id='subjectId' name='subject'></select>
			<span>选择考点：</span><select id='examPlaceId' name='query[0]_examPlace'></select>
		<span>是否处理：</span> 
		<s:select list="#{1:'===已处理===',0:'===未处理==='}" name="query[0]_isVerify"
			listKey="key" listValue="value" headerKey="" headerValue="==请选择=="></s:select>
			</div>
			<div class="padding_right5 padding_top5">
		<span>姓名：</span>
		<s:textfield name="query[0]_examineeName_like" maxlength="20" cssStyle="width:100px;"></s:textfield>
		<span>准考证号：</span>
		<s:textfield name="query[0]_licence" maxlength="20" cssStyle="width:100px;"></s:textfield>
		<span>证件号码：</span>
		<s:textfield name="query[0]_identity" maxlength="20" cssStyle="width:100px;"></s:textfield>
		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
		<%-- <button type="button" onclick="exportExcel()">
			<span class="icon_find">导出</span>
		</button> --%>
		</div>
	</s:form>
	<div id="scrollContent" class="padding_right5 padding_top5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>考区</th>
				<th>考点</th>
				<th>姓名</th>
				<th>准考证号</th>
				<th>身份证号</th>
				<th>考场号</th>
				<th>座位号</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					 <td><s:property value="examAreaName" /></td>
					<td><s:property value="examPlaceName" /></td>
					<td><a href="javascript:showExamineeInofo('<s:property value="licence" />');" 
					     ref="<s:property value="photoPath" />"
					     class=" imgPreview"><span class="icon_img"><s:property value="examineeName" /></span></a></td>
					<td><s:property value="licence" /></td>
					<td><s:property value="identity" /></td>
					<td><s:property value="examRoom" /></td>
					<td><s:property value="seatNumber" /></td>
					
				</tr>
			</s:iterator>
		</table>
	</div>
	<s:include value="../frame/pageInfo.jsp"></s:include>
	<script type="text/javascript">
		function setArea(areaId) {
			$("#headAreaId").val(areaId);
			$("#actionAreaId").val(areaId);
			queryData();
		}
		function queryData() {
			var subject = $("#subjectId").val();
			if(subject=="1"){
				$("#paramFormId").attr("action","findResultExaminee1QList.action");
			}
			else if(subject=="2"){
				$("#paramFormId").attr("action","findResultExaminee2QList.action");
			}
			else if(subject=="3"){
				$("#paramFormId").attr("action","findResultExaminee3QList.action");
			}
			else if(subject=="4"){
				$("#paramFormId").attr("action","findResultExaminee4QList.action");
			}
			else if(subject=="5"){
				$("#paramFormId").attr("action","findResultExaminee5QList.action");
			}
			else if(subject=="6"){
				$("#paramFormId").attr("action","findResultExaminee6QList.action");
			}
			$("#paramFormId").submit();
		}
		/* 2018.1.31 新增缺考人员导出 */
		function exportExcel(){
			window.location.href = "../resultExaminee/examineeExportExcel.action?flag=0";
		}
		function showExamineeInofo(licence){
			//打开窗口
			var diag = new top.Dialog();
			diag.Title = "考生详细信息查看";
			diag.URL = "../resultExaminee/findResultExaminee.action?licence="+licence;
			diag.Width = 950;
			diag.Height = 540;
			diag.ShowButtonRow = false;
			diag.show();
		}
		$(function(){
			var subejctSelectHtml = "<option value=''>===请选择===</option>";
			$.get("${basePath}/admin/resultExaminee/getSubjectData.action",function(data){
				var subjectList = eval("(" + data + ")");
				for ( var i = 0; i < subjectList.length; i++) {
					subejctSelectHtml +="<option value="+subjectList[i].id+">" + subjectList[i].name;
					subejctSelectHtml +="</option>";
				}
				$("#subjectId").html(subejctSelectHtml);
			});
			var examPlaceSelectHtml = "<option value=''>===请选择===</option>";
			$.get("${basePath}/admin/resultExaminee/getExamPlaceData.action?",function(data){
				var examPlaceList = eval("(" + data + ")");
				for ( var i = 0; i < examPlaceList.length; i++) {
					examPlaceSelectHtml +="<option value="+examPlaceList[i].examPlace+">" + examPlaceList[i].examPlaceName;
					examPlaceSelectHtml +="</option>";
				}
				$("#examPlaceId").html(examPlaceSelectHtml);
			});
		}) 
	</script>
</body>
</html>
