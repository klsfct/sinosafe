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
<script src="../libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="../libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
</head>
<body>
	<div class="padding_top10 padding_bottom5 padding_left5 padding_right5">
		<s:form id="form1" method="post" action="updatelinkMan" target="frmright"
			namespace="/admin/sms">
			<s:hidden name="linkManVo.id" />
			<table class="tableStyle" formMode="line">
				<tr>
					<th colspan="2">修改联系人信息</th>
				</tr>
				<tr>
					<td>联系人名：</td>
					<td><s:textfield name="linkManVo.name"  cssClass="validate[required,length[0,100]]" /></td>
				</tr>
				<tr>
					<td>分组：</td>
					<td>
					<div>
					<s:select list="linkGroupList"  cssClass="validate[required]"
							name="linkManVo.linkGroup.id" listKey="id" listValue="name"
							headerKey="" headerValue="===请选择==="></s:select>
					</div>		
					</td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><s:textfield name="linkManVo.phone"  cssClass="validate[required,custom[mobilephone],length[0,100]]" /></td>
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
</script>
</body>
</html>
