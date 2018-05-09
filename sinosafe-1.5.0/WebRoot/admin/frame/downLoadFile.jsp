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
		
		<form action="importResultExaminee.action" id="mainForm"
			method="post" enctype="multipart/form-data" namesapce="">
				<table class="tableStyle" formMode="line">
				<tr>
					<td>用户操作手册：</td>
					<td><input type="button" value="点击下载" onclick="downLoadWord()"/></td>
				</tr>
				<tr>
					<td>U盾数据拷贝工具：</td>
					<td><input type="button" value="点击下载" onclick="downLoadTools()"/></td>
				</tr>
				<tr>
					<td>人像识别客户端数据包：</td>
					<td><input type="button" value="点击下载" onclick="downLoadFace('${loginSession.userId}')"/></td>
				</tr>
				<tr>
					<td>人工核查系统数据包:</td>
					<td> 
					<input type="button" value="点击下载" onclick="downLoadHand('${loginSession.userId}')"/>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
<script type="text/javascript">
	function downLoadFace(examPlaceId){
		window.open("${basePath}/admin/download/face/FaceClient-"+ examPlaceId + ".exe");
	}
	function downLoadHand(examPlaceId){
		window.open("${basePath}/admin/download/hand/HandClient-"+ examPlaceId + ".exe");
	}
	/* function downLoadWord(){
		window.open("${basePath}/admin/download/document/用户操作手册.docx");
	} */
	function downLoadTools(){
		window.open("${basePath}/admin/download/tools/keydisk.zip");
	}
</script>
</body>
</html>
