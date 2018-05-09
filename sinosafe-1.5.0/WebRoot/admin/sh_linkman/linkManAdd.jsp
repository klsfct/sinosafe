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
		<form action="../sh_linkman/addLinkMan.action" id="mainForm"
			method="post">
			<table class="tableStyle" formMode="view">
				<tr>
					<td width="30%">选择分组 ：</td>
					<td>
							<s:select list="linkGroupList" name="linkManVo.linkGroup.id"  cssStyle="font-size: 15px;"
									listKey="id" listValue="name"></s:select>
					</td>
				</tr>
				 <tr>
					<td width="30%">选择考点 ：</td>
					<td>
							<s:select list="examPlaceVoList" name="linkManVo.examPlaceVo.examPlace"  cssStyle="font-size: 15px;"
									listKey="examPlace" listValue="examPlaceName"></s:select>
					</td>
				</tr>
				<tr>
					<td width="30%">工作人员姓名 ：</td>
					<td><s:textfield name="linkManVo.name"
							style="width: 200px;" cssClass="validate[required,maxSize[32]]" maxlength="10"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="30%">电话 ：</td>
					<td><s:textfield name="linkManVo.phone"
							style="width: 200px;" cssClass="validate[custom[mobilephone],required,minSize[11]]" maxlength="11" 
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
                            onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                            ></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="30%">密码：</td>
					<td><s:password name="linkManVo.password" maxlength="6"  placeholder="请输入6位数字密码"
							style="width: 200px;" cssClass="validate[required,maxSize[6],minSize[6],custom[onlyNumberSp]]"></s:password>
					</td>
				</tr>
				<tr>
					<td width="30%">备注 ：</td>
					<td>
						<s:textarea name="linkManVo.remark"
							cssClass="validate[required,maxSize[50]]"  style="width: 200px;resize:none;"
							placeholder="可描述工作人员职务及职责"
						></s:textarea>
					
					</td>
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
