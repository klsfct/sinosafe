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

<!--图片预览start-->
<script type="text/javascript" src="../libs/js/pic/imgPreview.js"></script>
<!--图片预览end-->
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findResultExamineeEList" namespace="/admin/resultExaminee">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="headAreaId" name="query[0]_examArea_like"
			value="%{areaId}"></s:hidden>
		<s:hidden id="actionAreaId" name="areaId"></s:hidden>
		<s:hidden id="licenceId" name="licence"></s:hidden>
		<s:hidden id="subjectId" name="subject"></s:hidden>
		<div class="padding_right5 padding_top5">
			<span>试卷：</span>
			<s:select
				list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}"
				id="querySubjectId" name="query[0]_subject" listKey="key"
				listValue="value" headerKey="" headerValue="===请选择===" ></s:select>
			<span>姓名：</span>
			<s:textfield name="query[0]_examineeName_like" maxlength="20"
				cssStyle="width:100px;"></s:textfield>
			<span>准考证号：</span>
			<s:textfield name="query[0]_licence" maxlength="20"
				cssStyle="width:120px;"></s:textfield>
			<span>证件号码：</span>
			<s:textfield name="query[0]_identity" maxlength="20"
				cssStyle="width:180px;"></s:textfield>
			<button type="button" onclick="queryData()">
				<span class="icon_find">查询</span>
			</button>
			<button type="button" onclick="showPhotoList()">
				<span class="icon_img">浏览照片</span>
			</button>
		</div>
		<div class="padding_right5 padding_top5">
			<span style="color:red;">*鼠标挪至“姓名”可预览照片，点击“姓名”可查看详情。</span>
		</div>
	</s:form>
	<div id="scrollContent" class="padding_right5 padding_top5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>当前考试</th>
				<th>考区</th>
				<th>考点</th>
				<th>姓名</th>
				<th>准考证号</th>
				<th>身份证号</th>
				<th>考场号</th>
				<th>座位号</th>
				<!-- <th>识别结果</th> -->
				<!-- <th>识别精度</th> -->
				<!-- <th>安检通道</th> -->
				<th>退场时间</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					<td><s:if test="subject==0">
						测试卷
					</s:if> <s:elseif test="subject==1">
						科目一
					</s:elseif> <s:elseif test="subject==2">
						科目二
					</s:elseif> <s:elseif test="subject==3">
						科目三
					</s:elseif> <s:elseif test="subject==3">
						科目三
					</s:elseif> 
					<s:elseif test="subject==4">
						科目四
					</s:elseif> </td>
					<td><s:property value="examAreaName" /></td>
					<td><s:property value="examPlaceName" /></td>
					<td><a
						href="javascript:showExamineeInofo('<s:property value="licence" />');"
						ref="<s:property value="photoPath" />"
						ref2="<s:property value="absolutePath" />" class=" imgPreview"><span
							class="icon_img"><s:property value="examineeName" /></span></a></td>
					<td><s:property value="licence" /></td>
					<td><s:property value="identity" /></td>
					<td><s:property value="examRoom" /></td>
					<td><s:property value="seatNumber" /></td>
					<!-- <td><s:if test="isPass">
							<span style="color:green">已通过</span>
						</s:if> <s:else>
							<span style="color:red">未通过</span>
						</s:else></td> -->
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<s:include value="../frame/pageInfo.jsp"></s:include>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#querySubjectId").val($("#subjectId").val());
		});
		function setArea(areaId) {
			$("#headAreaId").val(areaId);
			$("#actionAreaId").val(areaId);
			queryData();
		}
		function queryData() {
			$("#subjectId").val($("#querySubjectId").val());
			$("#paramFormId").submit();
		}
		function showPhotoList() {
			//打开窗口
			var diag = new top.Dialog();
			diag.Title = "照片查看";
			diag.URL = "../resultExaminee/findResultExamineeEPhotoList.action?pageParam[0].currentPage="
					+ $("#currentPageId").val()
					+ "&pageParam[0].everyPage="
					+ $("#everyPageId").val()
					+ "&pageParam[0].orderBy="
					+ $("#orderById").val()
					+ "&pageParam[0].condition="
					+ $("#conditionId").val() + "";
			diag.Width = 950;
			diag.Height = 540;
			diag.ShowMaxButton = true;
			diag.ShowButtonRow = false;
			diag.show();
			diag.max();
		}

		function showExamineeInofo(licence) {
			//打开窗口
			var diag = new top.Dialog();
			diag.Title = "考生详细信息查看";
			diag.URL = "../resultExaminee/findResultExaminee.action?licence="
					+ licence;
			diag.Width = 950;
			diag.Height = 540;
			diag.ShowButtonRow = false;
			diag.show();
		}
	</script>
</body>
</html>
