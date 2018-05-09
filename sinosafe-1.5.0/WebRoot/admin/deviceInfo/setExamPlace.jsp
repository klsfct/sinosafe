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
		<s:form id="form1" method="post" action="setExamPlace" target="frmright"
			namespace="/admin/deviceInfo">
			
			<table class="tableStyle" formMode="line">
			<s:hidden name="deviceGpsInfoVo.gpsId"></s:hidden>
			<s:hidden name="deviceGpsInfoVo.entityName"></s:hidden>
				<tr>
					<td>选择考点：</td>
					<td>
					<div id="pro"></div>
					<div id="city"></div>
					<div id="examPlace"></div>
					<span id="text"></span>
					
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input id="ok" type="button" value=" 提 交 "  onclick="validateForm('#form1');"/>
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
	$(function(){
		var ss = "";
		$.get("${basePath}/admin/deviceInfo/getBaseProvince.action",function(data){
			var json = eval("(" + data + ")");
			ss = ss + "<select onchange='getAllCity(this)' >";
			ss = ss + "<option>== 请选择省、市 ==</option>";
			for ( var i = 0; i < json.length; i++) {
				ss = ss + "<option value="+json[i].id+">" + json[i].name;
				ss = ss + "</option>";
			}
			ss = ss + "</select>";
			$("div#pro").html(ss);
		});
	})
	
	function getAllCity(obj) {
		var ss = "";
		$.get("${basePath}/admin/deviceInfo/getBaseArea.action?previousId=" + obj.value , function(data) {
			var json = eval("(" + data + ")");
			ss +="<select onchange='getExamPalce(this)' >";
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
		var ss="";
		$.get("${basePath}/admin/deviceInfo/getBaseExamPalce.action?examArea="+obj.value,function(data){
			var json=eval("(" + data + ")");
			ss +="<select name='deviceGpsInfoVo.examPlaceVo.examPlace' onchange='doVerify()' id='typeSelect'>";
			ss +="<option>== 请选择考点 ==</option>";
			for(var i=0;i<json.length;i++){
				ss += "<option value="+json[i].examPlace+">" + json[i].examPlaceName;
				ss += "</option>";
			}
			ss = ss + "</select>";
			$("div#examPlace").html(ss);
		});
	
		}
	function doVerify(){
       var deviceType = $("#typeSelect option:selected").val();
       var text = document.getElementById("text");
       if(deviceType !=""){
            $.ajax({
                url:"${basePath}/admin/deviceInfo/verifyExamPlace.action",
                data:{"deviceGpsInfoVo.examPlaceVo.examPlace": deviceType},
                type: "post",
                success: function(msg){
                    if("true" !=msg){
                        text.innerHTML = "<font color='red'>该考点已绑定设备，请更换考点！</font>"
                        $("#ok").attr({"disabled":"disabled"});
                    }
                    else{
                        text.innerHTML = "<font color='green'>当前考点可以选择！</font>"
                        $("#ok").removeAttr("disabled");
                    }
                }
            });
        }
    }
	
</script>
</body>
</html>
