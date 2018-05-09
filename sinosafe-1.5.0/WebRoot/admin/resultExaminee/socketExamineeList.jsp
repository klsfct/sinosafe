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
		action="findSocketExaminee1List" namespace="/admin/resultExaminee">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="headAreaId" name="query[0]_examArea_like" value="%{areaId}"></s:hidden>
		<s:hidden id="actionAreaId"  name="areaId"></s:hidden>
		<div class="padding_right5 padding_top5">
		<span>当前场次：</span><select id='subjectId' name='subject'></select>
		<span>是否通过：</span> 
		<s:select list="#{1:'已通过',0:'未通过'}" name="query[0]_isPass"
			listKey="key" listValue="value" headerKey="" headerValue="请选择"></s:select>
		<span>姓名：</span>
		<s:textfield name="query[0]_faceExamineeName_like" maxlength="20" cssStyle="width:100px;"></s:textfield>
		<span>准考证号：</span>
		<s:textfield name="query[0]_licence" maxlength="20" cssStyle="width:100px;"></s:textfield>
		<%-- <span>证件号码：</span>
		<s:textfield name="query[0]_identity" maxlength="20" cssStyle="width:100px;"></s:textfield> --%>
		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
		</div>
	</s:form>
	<div id="scrollContent" class="padding_right5 padding_top5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>考点</th>
				<th>姓名</th>
				<th>准考证号</th>
				<th>核查时间</th>
				<th>人工核查原因</th>
				<th>人工核查结果</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					<td><s:property value="examPlaceName" />
					<td><a href="javascript:showExamineeInofo('<s:property value="licence" />');" 
					     ref="<s:property value="photoPath" />"
					     class=" imgPreview"><span class="icon_img"><s:property value="faceExamineeName" /></span></a></td>
					<td><s:property value="licence" /></td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" /></td>
					 <td>
						<s:if test="photoPrecision == 1001">
							通道无法识别
						</s:if>
						<s:elseif test="photoPrecision == 1002">
							临时身份证
						</s:elseif>
						<s:elseif test="photoPrecision == 1003">
							身份证信息不符
						</s:elseif>
						<s:elseif test="photoPrecision == 1004">
							忘带身份证
						</s:elseif>
						<s:elseif test="photoPrecision == 1005">
							身份证过期
						</s:elseif>
						<s:elseif test="photoPrecision == 1006">
							忘带准考证
						</s:elseif>
						<s:elseif test="photoPrecision == 1007">
							其他
						</s:elseif>
						<s:else>
							考生身份信息不符
						</s:else>
					</td>
					<td><s:if test="isPass">
						<span style="color:green">已通过</span>
					</s:if> 
					<s:else>
						<span style="color:red">未通过</span>
						</s:else></td>
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
				$("#paramFormId").attr("action","findSocketExaminee1List.action");
			}
			else if(subject=="2"){
				$("#paramFormId").attr("action","findSocketExaminee2List.action");
			}
			else if(subject=="3"){
				$("#paramFormId").attr("action","findSocketExaminee3List.action");
			}
			else if(subject=="4"){
				$("#paramFormId").attr("action","findSocketExaminee4List.action");
			}
			$("#paramFormId").submit();
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
