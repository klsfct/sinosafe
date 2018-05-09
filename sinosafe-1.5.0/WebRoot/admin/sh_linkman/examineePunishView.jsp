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
			<table class="tableStyle" formMode="view">
				<tr>
					<td width="20%">考点名称 ：</td>
					<td width="55%"><s:property value="examineePunishVo.examineeVo.examPlaceName" /></td>
					<td rowspan="6">
						<img src="<s:property value="examineePunishVo.photoPath" />" style="width: 128px" />
					</td>
				</tr>
				<tr>
					<td>考生姓名 ：</td>
					<td><s:property value="examineePunishVo.examineeVo.examineeName" /></td>
				</tr>
				<tr>
					<td>准考证号：</td>
					<td><s:property value="examineePunishVo.examineeVo.licence" /></td>
				</tr>
				<tr>
					<td>证件号码：</td>
					<td><s:property value="examineePunishVo.examineeVo.identity" /></td>
				</tr>
				<tr>
					<td>科目：</td>
					<td><s:property value="examineePunishVo.subject" />
					</td>
				</tr>
				<tr>
					<td>违纪名称：</td>
					<td><s:property value="examineePunishVo.punishActionName" />
					</td>
				</tr>
				<tr>
					<td>处理结果：</td>
					<td><s:property value="examineePunishVo.punishName" />
					</td>
				</tr>
				<tr>
					<td>地点描述：</td>
					<td><s:property value="examineePunishVo.loginAddr" />
					</td>
				</tr>
				<tr>
					<td>经纬度：</td>
					<td>[<s:property value="examineePunishVo.loginLng" />：<s:property value="examineePunishVo.loginLat" />]
					</td>
				</tr>
				<tr>
					<td>录入时间：</td>
					<td><s:property value="examineePunishVo.createDate" />
					</td>
				</tr>
			</table>
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
