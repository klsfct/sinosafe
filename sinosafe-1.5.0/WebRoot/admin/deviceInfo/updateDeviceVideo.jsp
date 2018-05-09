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
<script src="../libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="../libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->

</head>
<body>
	<div class="padding_top10 padding_bottom5 padding_left5 padding_right5">
		<s:form id="form1" method="post" action="updateDeviceVideo" target="frmright"
			namespace="/admin/deviceInfo">
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
					<td><s:textfield  name="deviceVideoInfoVo.deviceNumber"  cssClass="validate[required,length[0,100]]" style="width:200px;" maxlength="3"/></td>
				</tr>
				
				<tr>
					<td>所属考点：</td>
					<td>
					
					<div id="pro">
					<select onClick="showSelect()" style='width:191px'>
						<option>${deviceVideoInfoVo.examPlaceVo.examProvinceName }</option>
					</select>
					</div>
					<div id="city">
					<select style='width:191px'>
						<option>${deviceVideoInfoVo.examPlaceVo.examAreaName }</option>
					</select>
					</div>
					<div id="examPlace" >
					<select name="deviceVideoInfoVo.examPlaceVo.examPlace" style='width:191px' id="oldDeviceName">
						<option value="${deviceVideoInfoVo.examPlaceVo.examPlace }">${deviceVideoInfoVo.examPlaceVo.examPlaceName }</option>
					</select>
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
				
				<tr>
					<td colspan="2">
						<input type="button" value=" 提 交 "  onclick="validateForm('#form1');"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
<script type="text/javascript">
	function validateForm(containerId){
	    var valid = $(containerId).validationEngine({returnIsValid: true});
		if(valid == true){
			if(confirm("是否确认无误，提交信息？")){
				$(containerId).submit();
				top.Dialog.close();
			}else{
				return false;
			}
		}else{
		    alert("信息填写不正确，请按要求填写！");
		} 
	} 
	// 考点信息三级联动
	function showSelect(){
		$("div#pro").empty();
		var ss = "";
		$.get("${basePath}/admin/deviceInfo/getBaseProvince.action",function(data){
			var json = eval("(" + data + ")");
			ss = ss + "<select onchange='getAllCity(this)'>";
			ss = ss + "<option>== 请选择省、市  ==</option>";
			for ( var i = 0; i < json.length; i++) {
				ss = ss + "<option value="+json[i].id+">" + json[i].name;
				ss = ss + "</option>";
			}
			ss = ss + "</select>";
			$("div#pro").html(ss);
			// 恢复设备类别默认选择状态 
			$("#typeSelect option:first").prop("selected", 'selected');
		});
	}
	function getAllCity(obj) {
		$("div#city").empty();
		var ss = "";
		$.get("${basePath}/admin/deviceInfo/getBaseArea.action?previousId=" + obj.value , function(data) {
			var json = eval("(" + data + ")");
			ss +="<select onchange='getExamPalce(this)'>";
			ss +="<option>== 请选择考区 ==</option>";
			for ( var i = 0; i < json.length; i++) {
				ss += "<option value="+json[i].id+">" + json[i].name;
				ss += "</option>";
			}
			ss = ss + "</select>";
			$("div#city").html(ss);

		});
	}
	
	function getExamPalce(obj){
		$("div#examPlace").empty();
		var ss="";
		$.get("${basePath}/admin/deviceInfo/getBaseExamPalce.action?examArea="+obj.value,function(data){
			var json=eval("(" + data + ")");
			ss +="<select name='deviceVideoInfoVo.examPlaceVo.examPlace' id='testSelect'>";
			ss +="<option>== 请选择考点 ==</option>";
			for(var i=0;i<json.length;i++){
				ss += "<option value="+json[i].examPlace+">" + json[i].examPlaceName;
				ss += "</option>";
			}
			ss = ss + "</select>";
			$("div#examPlace").html(ss);
		});
	}
	function getDeviceName(){
		var deviceType = $("#typeSelect option:selected").val();
		if(deviceType !=""){
			$.ajax({
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
						$("#deviceName").val(oldDeviceName + "#" + count);
					}
					
				}
			});
		}else{
			alert("请选择考点信息和设备类别！");
			
		}
	}
</script>
</body>
</html>
