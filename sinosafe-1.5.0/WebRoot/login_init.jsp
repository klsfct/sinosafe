<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>初始化信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script type="text/javascript" src="admin/libs/js/jquery.js"></script>
<script type="text/javascript" src="admin/libs/js/login.js"></script>
<script type="text/javascript" src="admin/libs/js/verfication.js"></script>
<link rel="stylesheet" type="text/css" href="login/css/style.css" media="screen" />
</head>
	
  <span href="#" class="button" id="toggle-login">初始化信息</span>

<div id="login">
  <div id="triangle"></div>
  <h1>为了您的信息安全，请耐心填写</h1>
  
		<s:form id="formId" name="modifyInitSysUsersForm" action="modifyInitSysUsers"
			namespace="/" method="post">
				<table>
					<tr>
						<td>登录账号：</td>
						<td><s:property value="userVo.userId" /></td>
					</tr>
					<tr>
					<td>地区名称：</td>
					<td><s:property value="userVo.areaName" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td>
					<input type="password" id="passwordId" name="userVo.password" maxlength="20" placeholder="6~16个字符，区分大小写"/>
					</td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input type="password" id="confirmPasswordId" name="confirmPassword" maxlength="20" placeholder="6~16个字符，区分大小写"/></td>
				</tr>
				<tr>
					<td>联系人：</td>
					<td><input type="text" id="principalId" name="userVo.principal" maxlength="15" placeholder="请输入联系人姓名"></td>
				</tr>
				<tr>
					<td>联系人电话：</td>
					<td><input type="text" id="mobileId" name="userVo.mobile" maxlength="11" placeholder="请输入联系人手机号"></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="button" name="btn" onclick="javascript:confirmInfo();" value="确定"/>
						<s:property value='exception.message' />
					</td>
				</tr>
				</table>
				
		</s:form>
	</div>
	<script type="text/javascript">
		function confirmInfo(){
			if(!isNull("passwordId","请填写密码！")) return false;
			if(!isPassWd("passwordId","密码填写格式有误，请重新填写！")) return false;
			if(!isNull("confirmPasswordId","请再次填写密码！")) return false;
			if(!isPassWd("confirmPasswordId","确认密码填写格式有误，请重新填写！")) return false;
			if($("#passwordId").val() != $("#confirmPasswordId").val()){
				alert("两次填写的密码不一致，请重新填写！");
				return false;
			}
			if(!isNull("principalId","请填写联系人姓名！")) return false;
			if(!isNull("mobileId","请填写联系人手机！")) return false;
			if(!isMobile("mobileId","手机填写格式有误，请重新填写！")) return false;
			$("#formId").submit();
			
		}
	</script>
</body>
</html>