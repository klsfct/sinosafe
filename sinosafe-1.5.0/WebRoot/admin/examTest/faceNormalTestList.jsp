<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统登录</title>
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
		action="findNormalTestList" namespace="/admin/examTest">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="areaId" name="query[0]_examPlace.examArea_like"></s:hidden>
	</s:form>
	<span style="color:red;font-size:18px">鼠标挪至“查看”链接可显示照片</span>
	<div id="scrollContent" class="padding_right5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>省（区、市）</th>
				<th>考区</th>
				<th>考点</th>
				<th>当前考试</th>
				<th>姓名</th>
				<th>安检通道</th>
				<th>识别结果</th>
				<th>上传时间</th>
				<th>照片</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					<td><s:property value="examProvinceName" /></td>
					<td><s:property value="examAreaName" /></td>
					<td><s:property value="examPlaceName" /></td>
					<td><s:if test="subject==0">
						测场次
					</s:if> <s:elseif test="subject==1">
						科目一
					</s:elseif> <s:elseif test="subject==2">
						科目二
					</s:elseif> <s:elseif test="subject==3">
						科目三
					</s:elseif> 
					<s:elseif test="subject==4">
						科目四
					</s:elseif></td>
					<td><s:property value="faceExamineeName" /></td>
					<td><s:if test="passType==0">
						普通通道
					</s:if> <s:elseif test="passType==1">
						特殊通道
					</s:elseif> <s:elseif test="passType==2">
						测试普通通道
					</s:elseif> <s:elseif test="passType==3">
						测试特殊通道
					</s:elseif> <s:elseif test="passType==5">
						退场通道
					</s:elseif> <s:elseif test="passType==6">
						测试退场通道
					</s:elseif></td>
					<td><s:if test="syspass ==1">
							<span style="color:red">未通过</span>
						</s:if> <s:elseif test="syspass==2">
							<span style="color:green">已通过</span>
						</s:elseif></td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" />
					
					</td>
					<td><a href="#" onmouseover="show(this,'mydiv1','<s:property value="relativePath" />');"
						onmouseout="hide(this,'mydiv1');">查看</a></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div id="mydiv1" style="width:132px;height:176px;position:absolute;display:none;"> 
		<img id="photoId" src="" style="width:132px;" />
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
		function show(obj, id,relativePath) {
			var objDiv = $("#" + id + "");
			$("#photoId").attr("src",relativePath);
			$(objDiv).css("display", "block");
			$(objDiv).css("left", event.clientX-180);
			$(objDiv).css("top", event.clientY);
		}
		function hide(obj, id) {
			var objDiv = $("#" + id + "");
			$("#photoId").attr("src","");
			$(objDiv).css("display", "none");
		}
	</script>
</body>
</html>
