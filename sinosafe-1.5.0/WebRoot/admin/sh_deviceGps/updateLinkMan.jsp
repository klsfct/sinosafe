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
		<s:form id="mainForm" method="post" action="updateLinkMan" target="frmright"
			namespace="/admin/sh_deviceGps">
			<s:hidden name="deviceGpsInfoVo.gpsId"></s:hidden>
			<table class="tableStyle" formMode="line">
				<tr>
					<td>联系人姓名：</td>
					<td><s:textfield name="deviceGpsInfoVo.linkManName"  cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="20" /></td>
				</tr>
				<tr>
					<td>联系人电话：</td>
					<td><s:textfield name="deviceGpsInfoVo.linkManPhone"  cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="20" /></td>
				</tr>
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
