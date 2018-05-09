<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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

<!--时间start -->
<script type="text/javascript" src="../libs/js/My97DatePicker/WdatePicker.js"></script>
<!--时间件end -->

<!--树组件start -->
<script type="text/javascript" src="../libs/js/tree/ztree/ztree.js"></script>
<link href="../libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!--树组件end -->

<!--表单异步提交start-->
<script src="../libs/js/form/form.js" type="text/javascript"></script>
<!--表单异步提交end-->
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="padding_top5 padding_right5">
		<s:form id="conditionFormId" name="conditionForm"
				action="saveMessageOnly" method="post" namespace="/admin/sh_sms">
			<s:hidden name="deviceGpsInfoVo.linkManName" id="linkManName"></s:hidden>
			<s:hidden name="deviceGpsInfoVo.linkManPhone" id="linkManPhone"></s:hidden>
			<textarea rows="5" cols="25" name="smsTaskVo.smsDescribe"></textarea>
			<input type="submit" value="发送"/>
		</s:form>
		</div>
</body>
</html>