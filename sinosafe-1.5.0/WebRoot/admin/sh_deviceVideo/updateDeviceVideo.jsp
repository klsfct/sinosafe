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
<body onload="initExamPlace()">
	<div class="padding_top10 padding_bottom5 padding_left5 padding_right5">
		<s:form id="mainForm" method="post" action="updateDeviceVideo" target="frmright"
			namespace="/admin/sh_deviceVideo">
			<table class="tableStyle" formMode="line">
			<s:hidden name="deviceVideoInfoVo.regFlag"></s:hidden>
			<s:hidden name="deviceVideoInfoVo.inputTime"></s:hidden>
			<s:hidden name="deviceVideoInfoVo.regTime"></s:hidden>
			<s:hidden name="deviceVideoInfoVo.backCode"></s:hidden>
			<s:hidden name="deviceVideoInfoVo.backMsg"></s:hidden>
			
			<table class="tableStyle" formMode="line">
				<tr>
					<td>设备序列号：</td>
					<td><s:textfield name="deviceVideoInfoVo.deviceSerial"  cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="20" readonly="true" /></td>
				</tr>
				<tr>
					<td>设备验证码：</td>
					<td><s:textfield name="deviceVideoInfoVo.validateCode"  cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="6"/></td>
				</tr>
				
				
				<tr>
					<td>设备编号：</td>
					<td><s:textfield  name="deviceVideoInfoVo.deviceNumber" cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="3" id="number"/></td>
				</tr>
				<tr>
					<td>联系人姓名：</td>
					<td><s:textfield  name="deviceVideoInfoVo.linkManName"  cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="20" id="number"/></td>
				</tr>
				
				<tr>
					<td>联系人电话：</td>
					<td><s:textfield  name="deviceVideoInfoVo.linkManPhone"  cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="20" id="number"/></td>
				</tr>
				<tr>
					<td>所属考点：</td>
					<td>
					<s:hidden id="examProvince" name="deviceVideoInfoVo.examPlaceVo.examProvince" disabled="true"></s:hidden>
					<s:hidden id="examArea" name="deviceVideoInfoVo.examPlaceVo.examArea" disabled="true"></s:hidden>
					<s:hidden id="examPlace" name="deviceVideoInfoVo.examPlaceVo.examPlace" disabled="true"></s:hidden>
					<div id="pro">
					</div>
					<div id="city">
					</div>
					<div id="examPlace" >
					</div>
					</td>
				</tr>
				
				<tr>
					<td>设备类别：</td>
					<td><s:select list="@com.examsafety.service.ConstantsService@getVideoDeviceTypeMap()"
							name="deviceVideoInfoVo.deviceType" listKey="key" listValue="value" cssStyle="height:23px;"
							headerKey="" headerValue="===请选择===" id="typeSelect" onchange="getDeviceName()">
						</s:select>
					</td>
				</tr>
				
				<tr>
					<td>设备名称：</td>
					<td><s:textfield name="deviceVideoInfoVo.deviceName" id="deviceName" cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="50"/></td>
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
	// 考点信息
	function initExamPlace(){
		var examPro = $("#examProvince").val();
		var examArea = $("#examArea").val();
		var examPlace = $("#examPlace").val();
		
		var s1 = "";
		$.get("${basePath}/admin/deviceInfo/getBaseProvince.action",function(data){
			var json = eval("(" + data + ")");
			s1 = s1 + "<select id='proSelect' onchange='getAllCity(this)' style='width:204px;'>";
			s1 = s1 + "<option value=''>===== 请选择省、市 =====</option>";
			for ( var i = 0; i < json.length; i++) {
				s1 = s1 + "<option value="+json[i].id+">" + json[i].name;
				s1 = s1 + "</option>";
			}
			s1 = s1 + "</select>";
			$("div#pro").html(s1);
			$("#proSelect").val(examPro);
		});
		
		var s2 = "";
		$.get("${basePath}/admin/deviceInfo/getBaseArea.action?previousId=" + examPro , function(data) {
			var json = eval("(" + data + ")");
			s2 +="<select id='areaSelect' style='width:204px;'>";
			s2 +="<option value=''>====== 请选择考区 ======</option>";
			for ( var i = 0; i < json.length; i++) {
				s2 += "<option value="+json[i].id+">" + json[i].name;
				s2 += "</option>";
			}
			s2 = s2 + "</select>";
			$("div#city").html(s2);
			$("#areaSelect").val(examArea);
		});
		
		var s3 = "";
		$.get("${basePath}/admin/deviceInfo/getBaseExamPalce.action?examArea=" + examArea,function(data){
			var json=eval("(" + data + ")");
			s3 +="<select name='deviceVideoInfoVo.examPlaceVo.examPlace' id='testSelect' onchange='backType()' style='width:204px;'>";
			s3 +="<option value=''>====== 请选择考点 ======</option>";
			for(var i=0;i<json.length;i++){
				s3 += "<option value="+json[i].examPlace+">" + json[i].examPlaceName;
				s3 += "</option>";
			}
			s3 = s3 + "</select>";
			$("div#examPlace").html(s3);
			$("#testSelect").val(examPlace);
		});
 	}
	
	function getAllCity(obj) {
		var ss = "";
		$.get("${basePath}/admin/deviceInfo/getBaseArea.action?previousId=" + obj.value , function(data) {
			var json = eval("(" + data + ")");
			ss +="<select onchange='getExamPalce(this)' style='width:204px;'>";
			ss +="<option>====== 请选择考区 ======</option>";
			for ( var i = 0; i < json.length; i++) {
				ss += "<option value="+json[i].id+">" + json[i].name;
				ss += "</option>";
			}
			ss = ss + "</select>";
			$("div#city").html(ss);
			$("#deviceName").val("");
		});
	}
	
	function getExamPalce(obj){
		var ss="";
		
		$.get("${basePath}/admin/deviceInfo/getBaseExamPalce.action?examArea="+obj.value,function(data){
			var json=eval("(" + data + ")");
			if(json != null & json != ""){
				ss +="<select name='deviceVideoInfoVo.examPlaceVo.examPlace' id='testSelect' onchange='backType()' style='width:204px;'>";
				ss +="<option value=''>====== 请选择考点 ======</option>";
				for(var i=0;i<json.length;i++){
					ss += "<option value="+json[i].examPlace+">" + json[i].examPlaceName;
					ss += "</option>";
				}
					ss = ss + "</select>";
					$("div#examPlace").html(ss);
			}
			else{
				$("div#examPlace").empty();
				alert("该考区下没有考点，请重新选择考区!");
			}
			
		});
	}
	function backType(){
		$("#typeSelect").val("");
		$("#deviceName").val("");
	}
	function getDeviceName(){
		var deviceType = $("#typeSelect option:selected").val();
		var deviceNumber = $("#number").val();
		var deviceNameVal = $("#testSelect option:selected").val();
		if(deviceType !="" && deviceNameVal !=""){
			/* $.ajax({
				url: "${basePath}/admin/deviceInfo/getDeviceTypeCount.action",
				data: {"deviceVideoInfoVo.deviceType": deviceType},
				type: "post",
				success: function (count){
					// 判断是否修改 
					var deviceName = $("#testSelect option:selected").text();
					if(deviceName != ""){
						$("#deviceName").val(deviceName + "#" + count);
					}else{
						var oldDeviceName = $("#oldDeviceName option").text();
						$("#deviceName").val(oldDeviceName + "#" +deviceType+ count);
					}
					
				}
			}); */
			var deviceName = $("#testSelect option:selected").text();
			if(deviceName != ""){
				$("#deviceName").val(deviceType+ deviceNumber + "#" + deviceName);
			}else{
				var oldDeviceName = $("#oldDeviceName option").text();
				$("#deviceName").val(deviceType+ deviceNumber + "#" + oldDeviceName);
			}
		}else{
			alert("请选择考点信息和设备类别！");
			$("#typeSelect").val("");
		}
	}
</script>
</body>
</html>
