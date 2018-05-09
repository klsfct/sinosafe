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

<!--图片预览start-->
<script type="text/javascript" src="../libs/js/pic/imgPreview.js"></script>
<!--图片预览end-->

</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findFileResourceList" namespace="/admin/filePage">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="headAreaId" name="query[0]_frExamArea_like"
			value="%{areaId}"></s:hidden>
		<s:hidden id="actionAreaId" name="areaId"></s:hidden>
		<div class="padding_right5 padding_top5">
			 <span>文件类型：</span>
			<s:select list="#{'.xlsx':'Excel表格','.docx':'Word文档','.zip':'压缩文件'}"
				name="query[0]_frFileType" listKey="key" listValue="value" headerKey=""
				headerValue="===请选择==="></s:select>
			
			<button type="button" onclick="queryData()">
				<span class="icon_find">查询</span>
			</button>
			<span style="color:red;">*鼠标挪至“下载地址”可下载文件</span>
		</div>
	</s:form>
	<div id="scrollContent" class="padding_right5 padding_top5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>上传地区</th>
				<th>文件名称</th>
				<th>文件类型</th>
				<th>文件描述</th>
				<th>文件大小</th>
				<th>下载地址</th>
				<th>上传时间</th>
				<th>上传IP</th>
				<th>操作</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					<td><s:property value="frExamAreaName" /></td>
					<td><s:property value="frFileName" /></td>
					<td><s:property value='frFileType' /></td>
					<td><s:property value="frFileRemarks" /></td>
					<td><s:property value="frFileSize" /> 字节</td>
					<td><a href="<s:property value="frWebPath" />" <span class="icon_btn_down">点击下载</span></a></td>
					<td><s:date name="frCreateDate" format="yyyy-MM-dd HH:mm" /></td>
					<td><s:property value="frUploadIp" /></td>
					<td><a href="javascript:deleteFile('<s:property value="frId" />')"><span class="icon_remove hand">删除文件</span></a></td>
						
				</tr>
			</s:iterator>
		</table>
	</div>
	<s:include value="../frame/pageInfo.jsp"></s:include>
	<script type="text/javascript">
	
	function setArea(areaId) {
		$("#headAreaId").val(areaId);
		$("#actionAreaId").val(areaId);
		queryData();
		
	}
	function queryData(){
		$("#paramFormId").submit();
	}
	function deleteFile(frId){
		if (confirm("确认删除此文件吗？")) {
			window.location.href = "${basePath}/admin/filePage/deleteFile.action?fileResourceVo.frId=" + frId;
		}
	}
	</script>
</body>
</html>
