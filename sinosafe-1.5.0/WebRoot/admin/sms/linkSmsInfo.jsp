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

<!--数字分页start-->
<script type="text/javascript" src="../libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->

<!--弹窗组件start-->
<script type="text/javascript" src="../libs/js/popup/drag.js"></script>
<script type="text/javascript" src="../libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->

</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
								action="findLinkMan" namespace="/admin/sms">
	<div class="box_tool_mid padding_right5 padding_bottom5">
			<div class="center">
				<div class="left">
					<div class="right">
						<div class="padding_top8 padding_left10">
							<ul>
								<li><span>联系人：</span></li>
								<li><s:textfield name="query[0]_linkman_like" maxlength="20"
											cssStyle="width:60px"></s:textfield></li>
								<li>
									<div class="box_tool_line"></div></li>
								<li><span>组名：</span></li>
								<li><s:textfield name="query[0]_linkGroup.linkgroup_like" maxlength="20"
											cssStyle="width:60px"></s:textfield></li>
								<li>
									<div class="box_tool_line"></div></li>
								<li><span>电话：</span></li>
								<li><s:textfield name="query[0]_phone_like" maxlength="20"
											cssStyle="width:80px"></s:textfield></li>
								<li>
									<div class="box_tool_line"></div></li>
								<li><span>内容：</span></li>
								<li><s:textfield name="query[0]_message_like" maxlength="20"
											cssStyle="width:80px"></s:textfield></li>
								<li>
									<div class="box_tool_line"></div></li>
								
								<li>
									<button type="button" onclick="queryData()">
											<span class="icon_find">查询</span>
										</button></li>
								<li>
									<div class="box_tool_line"></div></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</s:form>
	<div id="scrollContent" class="padding_right5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>序号</th>
				<th>内容</th>
				<th>组名</th>
				<th>联系人</th>
				<th>电话</th>
				<th>长度</th>
				<th>发送状态</th>
				<th>创建人</th>
				<th>创建时间</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList"
				status="st1">
				<tr>
					<td><s:property value="#st1.count" /></td>
					<td><s:property value="message" /></td>
					<td><s:property value="linkgroup" /></td>
					<td><s:property value="linkman" /></td>
					<td><s:property value="phone" /></td>
					<td><s:property value="msgNum" /></td>
					<td>
						<s:if test="sendFlag==true">
							<span style="color:green;">成功</span>
						</s:if> <s:elseif test="sendFlag==false">
							<span style="color:red;">未成功</span>
						</s:elseif> 
					</td>
					<td><s:property value="creater" /></td>
					<td>
						<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<s:include value="../frame/pageInfo.jsp"></s:include>
	<s:form id="sysDataGridId" name="sysDataGrid" action="findLinkGroup" namespace="/admin/sms"
		method="post">
		<s:hidden id="linkManVoId" name="linkManVo.id" ></s:hidden>
	</s:form>
	<script type="text/javascript">
		function queryData() {
			$("#paramFormId").submit();
		}
		
		function addData() {
			var diag = new Dialog();
			diag.Title = "新建联系人信息";
			diag.URL = "../sms/gotoSavelinkMan.action";
			diag.show();
		}
		function deleteData(dataId) {
			if (confirm("确认删除选择的数据吗？")) {
				$("#sysDataGridId").attr("action",
						"../sms/deletelinkMan.action");
				$("#linkManVoId").val(dataId);
				$("#sysDataGridId").submit();
			}
		}
		function editData(dataId) {
			var diag = new Dialog();
			diag.Title = "修改联系人信息";
			diag.URL = "../sms/gotoUpdatelinkMan.action?linkManVo.id="+dataId;
			diag.show();
		}
	</script>
</body>
</html>
