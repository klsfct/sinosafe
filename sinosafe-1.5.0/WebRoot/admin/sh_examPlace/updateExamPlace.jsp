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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<!--框架必需start-->
<script type="text/javascript" src="../libs/js/jquery.js"></script>
<script type="text/javascript" src="../libs/js/framework.js"></script>
<link href="../libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="../" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<!--框架必需end-->

<!-- 表单验证start -->
<script src="../libs/js/validation/js/jquery.validationEngine.js"
	type="text/javascript"></script>
<script src="../libs/js/validation/js/jquery.validationEngine-zh_CN.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="../libs/js/validation/css/validationEngine.jquery.css" />
<!-- 表单验证end -->

</head>
<body>
	<div class="padding_top10 padding_bottom5 padding_left5 padding_right5">
		<s:form id="mainForm" method="post" action="updatePoint" target="frmright"
			namespace="/admin/sh_examPlace">
			
			
			<table class="tableStyle" formMode="line">
			<s:hidden name="examPlaceVo.examPlace"></s:hidden>
				<tr>
					<td>考点名称：</td>
					<td><s:textfield name="examPlaceVo.examPlaceName" cssClass="validate[required,length[0,100]]" style="width:250px;" maxlength="20" /></td>
				</tr>
				<tr>
					<td>考点人数：</td>
					<td><s:textfield name="examPlaceVo.examineeSum" cssClass="validate[length[0,100]]" style="width:250px;" maxlength="20"/></td>
				</tr>
				<tr>
					<td>考点地址：</td>
					<td><s:textfield name="examPlaceVo.examAddr" cssClass="validate[required,length[0,100]]" style="width:250px;" maxlength="20"/></td>
				</tr>
				<tr>
					<td>坐标位置：</td>
					<td><s:textfield name="examPlaceVo.point" cssClass="validate[required,length[0,100]]" style="width:250px;" maxlength="50"/></td>
				</tr>
				<tr>
					<td>考点联系人姓名：</td>
					<td><s:textfield  name="examPlaceVo.linkManName"  cssClass="validate[length[0,100]]" style="width:200px;" maxlength="20" /></td>
				</tr>
				
				<tr>
					<td>考点联系人电话：</td>
					<td><s:textfield  name="examPlaceVo.linkManPhone"  cssClass="validate[length[0,100]]" style="width:200px;" maxlength="20" /></td>
				</tr>
				<div>
					<span>
					操作说明：<br/>
					1、点击打开<a href="http://api.map.baidu.com/lbsapi/getpoint/?qq-pf-to=pcqq.c2c" target="_blank"><font color="red">《坐标拾取系统》</font>。</a>  <br/>
					2、将考点地址中的内容复制，粘贴到坐标拾取系统的搜索框中，点击百度一下。<br />
						&nbsp; &nbsp; &nbsp; 也可复制坐标位置中的内容勾选坐标反查,查询地理位置是否正确<br/>
					3、在网页右侧选择具体地址，或者手动点击地图上的地理位置。<br/>
					4、在网页右上角找到坐标，点击复制，粘贴到坐标位置框中。点击保存。<br/>
					</span>
				</div>
			</table>
		</s:form>
	</div>
<script type="text/javascript">
	var _formId = "mainForm";
	function initComplete() {
		$("#" + _formId).validationEngine('attach', {
			promptPosition : 'bottomLeft',
			scroll : false,
			autoHidePrompt : true,
			autoHideDelay : 3000,
			autoPositionUpdate : true,
			addFailureCssClassToField : "error-field"
		});
	}
	//表单验证
	function formValidate() {
		var _validateFlag = $("#" + _formId).validationEngine("validate");
		return _validateFlag;
	}
	
	function getformData() {
		return $("#" + _formId).serialize();
	}
	function getformAction() {
		return $("#" + _formId).attr("action");
	}
</script>
</body>
</html>
