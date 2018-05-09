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
<style type="text/css">
.photoDialog_box {
	padding: 5px;
	overflow-y: auto;
}

.photoDialog_box ul {
	list-style: none;
	padding: 0;
}

.photoDialog_box li {
	float: left;
	margin-right: 5px;
	margin-bottom: 5px;
	margin-top: 5px;
	width: 285px;
}

.photoDialog_box table {
	border-collapse: collapse;
	border: 1px solid #ccc;
	width: 100%;
}

.photoDialog_box td {
	border: 1px solid #ccc;
	text-align: center;
	padding: 5px 0;
}
</style>

<script>
	var fixObjHeight = 50;
	function customHeightSet(contentHeight) {
		$("#scrollContent").height(contentHeight - fixObjHeight);
	}
</script>

</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findPhotoNoPass" namespace="/admin/place">
		<div id="scrollContent"
			class="padding_right5 padding_top5 padding_left5"
			style="overflow-x:hidden;">
			<div class="photoDialog_box">
				<ul>
					<s:iterator value="pageResultList[0].resultList" status="st1">
					<s:if test="isPass">
						<li style="border: 3px solid green" >
					</s:if>
					<s:else>
						<li style="border: 3px solid red">
					</s:else>
						<table class="tableStyle" mode="list">
							<tr>
								<th colspan="2">
								<s:if test="subject==0">
									测试卷
								</s:if> <s:elseif test="subject==1">
									科目一
								</s:elseif> <s:elseif test="subject==2">
									科目二
								</s:elseif> <s:elseif test="subject==3">
									科目三
								</s:elseif> 
								<s:elseif test="subject==4">
									科目四
								</s:elseif> &nbsp;&nbsp;
								<s:property value="examineeName" />&nbsp;&nbsp;<s:property
										value="licence" />&nbsp;&nbsp;<b>
									<s:if test="isPass">
										<span style="color:green;">通过</span>
									</s:if> 
									<s:else>
										<span style="color:red;">未通过</span>
									</s:else>
								</b>
								</th>
							</tr>
							<tr>
								<td style="width: 120px;"
									style="height: 190px;vertical-align: middle;"><img
									src="<s:property value="photoPath" />"
									style="width:120px;height: 182px;" />
								</td>
								<td style="width: 120px;"><img
									src="<s:property value="absolutePath" />" style="width:120px" />
								</td>
							</tr>
						</table>
						</li>
					</s:iterator>
				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</s:form>
	<s:include value="../frame/pageInfo.jsp"></s:include>
</body>
</html>
