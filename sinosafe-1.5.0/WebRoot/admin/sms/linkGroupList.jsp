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
								action="findLinkGroup" namespace="/admin/sms">
	<div class="box_tool_mid padding_right5 padding_bottom5">
			<div class="center">
				<div class="left">
					<div class="right">
						<div class="padding_top8 padding_left10">
							<ul>
								<li><span>组名：</span></li>
								<li><s:textfield name="query[0]_name_like" maxlength="20"
											cssStyle="width:120px"></s:textfield></li>
								<li>
									<div class="box_tool_line"></div></li>
								<li>
									<button type="button" onclick="queryData()">
											<span class="icon_find">查询</span>
										</button></li>
								<li>
									<div class="box_tool_line"></div></li>
								<li>
									<button type="button" onclick="addData()">
									<span class="icon_add">新建</span>
								</button></li>
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
				<th>组名</th>
				<th>备注</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList"
				status="st1">
				<tr>
					<td><s:property value="#st1.count" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="remark" /></td>
					<td><s:property value="creater" /></td>
					<td>
						<s:date name="createDate" format="yyyy-MM-dd HH:mm" />
					</td>
						<td><a
							href="javascript:editData(<s:property value="id"/>);">修改</a>&nbsp;|&nbsp;
							<a href="javascript:deleteData(<s:property value="id" />);">删除</a>
						</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<s:include value="../frame/pageInfo.jsp"></s:include>
	<s:form id="sysDataGridId" name="sysDataGrid" action="findLinkGroup" namespace="/admin/sms"
		method="post">
		<s:hidden id="linkGroupVoId" name="linkGroupVo.id" ></s:hidden>
	</s:form>
	<script type="text/javascript">
		function queryData() {
			$("#paramFormId").submit();
		}
		
		function addData() {
			var diag = new Dialog();
			diag.Title = "新建组信息";
			diag.URL = "../sms/gotoSavelinkGroup.action";
			diag.show();
		}
		function deleteData(dataId) {
			if (confirm("确认删除选择的数据吗？")) {
				$("#sysDataGridId").attr("action",
						"../sms/deletelinkGroup.action");
				$("#linkGroupVoId").val(dataId);
				$("#sysDataGridId").submit();
			}
		}
		function editData(dataId) {
			var diag = new Dialog();
			diag.Title = "修改组信息";
			diag.URL = "../sms/gotoUpdatelinkGroup.action?linkGroupVo.id="+dataId;
			diag.show();
		}
	</script>
</body>
</html>
