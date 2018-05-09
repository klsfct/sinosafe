<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="./libs/js/jquery.js"></script>
<script type="text/javascript" src="./libs/js/base64.js"></script>
<style type="text/css">
	.phone_box{
		padding: 10px;
	}
	.phone_box table{
		width: 100%;
		border-collapse: collapse;
	}
	
	.phone_box td{
		padding: 10px;
		text-align: left;
		border: 1px solid #ccc;
	}
	.phone_box .lebel{
		text-align: right;
	}
	.phone_box button{
		padding: 10px;
		margin:5px;
		border: 1px solid #ccc;
		cursor: pointer;
	}
</style>
</head>
<body>
	<div class="phone_box">
		<s:form id="conditionFormId" name="conditionForm"
				action="saveMessageByExam" method="post" namespace="/admin/sh_sms">
			<s:hidden name="linkManName" id="linkManName"></s:hidden>
			<s:hidden name="linkManPhone" id="linkManPhone"></s:hidden>
		<table>
			<tr>
				<td width="30%" class="lebel">姓名：</td><td><s:property value="linkManName"/></td>
			</tr>
			<tr>
				<td class="lebel">电话：</td><td><s:property value="linkManPhone"/></td>
			</tr>
			<tr>
				<td class="lebel">短信内容：</td><td><textarea rows="5" style="width: 80%;" id="smsDescribe" name="smsTaskVo.smsDescribe"></textarea></td>
			</tr>
			<tr>
				<td>
				</td>
				<td>
					<button id="phone_btn" type="button">拨打电话</button><button id="down_btn" type="button">挂断</button><button id="send_msg" type="button">发送短信</button>
				</td>
			</tr>
		</table>
		</s:form>
	</div>
	<script type="text/javascript">
		var out_phone = "86,";
		$(function(){
			$("#phone_btn").click(function(){
				var _phone = $("#linkManPhone").val();
				var _phoneBase64 = BASE64.encoder(out_phone+_phone);
				//alert(_phoneBase64);
				ys7.CallTo(23205,_phoneBase64);
			});
			$("#down_btn").click(function(){
				ys7.DownPhone(23205);
			});
			
			$("#send_msg").click(function(){
				var _name = $("#linkManName").val();
				var _phone = $("#linkManPhone").val();
				var _smsDescribe = $("#smsDescribe").val();
				if(_smsDescribe.length<1){
					alert("短信内容必须填写！");
				}else{
					$.ajax({
		        		type : "POST",
		        		url : "saveMessageByExam.action",
		        		cache : true,
		        		data:{"linkManName":_name,"linkManPhone":_phone,"smsTaskVo.smsDescribe":_smsDescribe},//$("#globlisSubject").val()  
		        		dataType : "json",
		        		success : function(data) {
		        			if (data != null) {
		        				if (data.dataStatus == "1") {
		        					alert("短信发送成功！");
		        				}
		        			}
		        		},
		        		error : function(XMLHttpRequest, textStatus, errorThrown) {
		        			alert("系统异常[" + textStatus + "]:" + errorThrown);
		        		}
		        	});
				}
				
				
			});
		});
	</script>
</body>
</html>