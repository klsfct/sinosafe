<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	<div class="padding_top5 padding_right5">
		<form action="../sh_linkman/modifyLinkGroup.action" id="mainForm"
			method="post">
			<table class="tableStyle" formMode="view">
				<tr>
					<td width="30%">联系人分组名称 ：</td>
					<td><s:textfield name="linkGroupVo.name"
							style="width: 250px;" cssClass="validate[required,maxSize[32]]"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="30%">联系人分组备注 ：</td>
					<td><s:textfield name="linkGroupVo.remark"
							style="width: 250px;"
							cssClass="validate[required]"></s:textfield></td>
				</tr>
			</table>
		</form>
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
