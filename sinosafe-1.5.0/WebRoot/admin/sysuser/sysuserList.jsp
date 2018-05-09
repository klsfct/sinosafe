<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache"></meta>
<meta http-equiv="cache-control" content="no-cache"></meta>
<meta http-equiv="expires" content="0"></meta>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
<meta http-equiv="description" content="This is my page"></meta>
<!--框架必需start-->
<script type="text/javascript" src="../libs/js/jquery.js"></script>
<script type="text/javascript" src="../libs/js/framework.js"></script>
<link href="../libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="../" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<!--框架必需end-->
<!--数字分页start-->
<script type="text/javascript" src="../libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findSysuserList" namespace="/admin/sysuser">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="userId" name="userVo.userId"></s:hidden>
		<s:hidden id="areaId" name="query[0]_areaId_like"></s:hidden>
	</s:form>
	<div id="scrollContent" class="padding_right5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>地区</th>
				<th>用户ID</th>
				<th>状态</th>
				<th>联系人</th>
				<th>手机</th>
				<th>登录时间</th>
				<th>登录IP</th>
				<th>操作</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					<td><s:property value="areaName" /></td>
					<td><s:property value="userId" /></td>
					<td><s:if test="isEnabled">
						启用
					</s:if>
					<s:else>
						初始
					</s:else></td>
					<td><s:property value="principal" /></td>
					<td><s:property value="mobile" /></td>
					<td><s:date name="loginDate" format="yyyy-MM-dd HH:MM:ss" />
					</td>
					<td><s:property value="loginIp" /></td>
					<td><a
						href="javascript:resetPassword('<s:property value="userId" />');">重置密码</a>&nbsp;|&nbsp;<a
						href="javascript:getUser('<s:property value="userId" />');">修改信息</a></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<s:include value="../frame/pageInfo.jsp"></s:include>
	<script type="text/javascript">
		function setArea(areaId) {
			$("#areaId").val(areaId);
			queryData();
		}
		function queryData() {
			$("#paramFormId").submit();
		}
		function resetPassword(userId) {
			if (confirm("确认重置初始密码么？")) {
				$("#userId").val(userId);
				$("#paramFormId").attr("action", "resetPassword.action");
				$("#paramFormId").submit();
			} else {
				return false;
			}

		}
		function getUser(userId) {
			$("#userId").val(userId);
			$("#paramFormId").attr("action", "getUser.action");
			$("#paramFormId").submit();
		}
	</script>
</body>
</html>
