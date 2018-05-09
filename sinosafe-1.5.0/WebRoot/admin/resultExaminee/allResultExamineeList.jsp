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
<!--弹窗组件start-->
<script type="text/javascript" src="../libs/js/popup/drag.js"></script>
<script type="text/javascript" src="../libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
<!--图片预览start-->
<script type="text/javascript" src="../libs/js/pic/imgPreview.js"></script>
<!--图片预览end-->
<style type="text/css">
/*图片加载中div图层，用于遮挡页面*/  
.loadingdiv  
{  
    position:absolute;  
    text-align:center;  
    left:0px;  
    top:0px;  
    z-index:70;  
    background-color:#ffffff;  
    opacity: 0.7;/*透明#CCCCCC*/  
    display:none;  
    }     
/*加载中图片*/  
.loadingdiv img  
{  
    position:absolute;  
    left:0px;  
    top:0px;  
    z-index:80; 
    vertical-align:center; 
   
    }  
</style>
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findAllResultExaminee1List" namespace="/admin/resultExaminee">
		<s:include value="../frame/areaInfo.jsp"></s:include>
		<s:hidden id="headAreaId" name="query[0]_examArea_like" value="%{areaId}"></s:hidden>
		<s:hidden id="actionAreaId"  name="areaId"></s:hidden>
		<div class="padding_right5 padding_top5">
			<span>当前场次：</span><select id='subjectId' name='subject'></select>
			<span>考点：</span>
			<s:textfield name="query[0]_examPlaceName_like" maxlength="20" cssStyle="width:120px;"></s:textfield>
		<span>姓名：</span>
		<s:textfield name="query[0]_examineeName_like" maxlength="20" cssStyle="width:100px;"></s:textfield>
		<span>准考证号：</span>
		<s:textfield name="query[0]_licence" maxlength="20" cssStyle="width:100px;"></s:textfield>
		<span>证件号码：</span>
		<s:textfield name="query[0]_identity" maxlength="20" cssStyle="width:100px;"></s:textfield>
		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
		</div>
		<div class="padding_right5 padding_top5">
		<span>数据操作：</span>
		<button type="button" onclick="showImportExcel()">
			<span class="icon_import">1.导入考生信息</span>
		</button>
		<button type="button" onclick="importExaminee()">
			<span class="icon_refresh">2.同步考生信息</span>
		</button>
		<button type="button" onclick="sumTable()">
			<span class="icon_refresh">3.同步统计表</span>
		</button>
		</div>
	</s:form>
	<div id="scrollContent" class="padding_right5 padding_top5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>考区</th>
				<th>考点</th>
				<th>姓名</th>
				<th>准考证号</th>
				<th>身份证号</th>
				<th>考场号</th>
				<th>座位号</th>
			</tr>
			<s:iterator value="pageResultList[0].resultList" status="st1">
				<tr>
					 <td><s:property value="examAreaName" /></td>
					<td><s:property value="examPlaceName" /></td>
					<td><a href="javascript:showExamineeInofo('<s:property value="licence" />');" 
					     ref="<s:property value="photoPath" />"
					     class=" imgPreview"><span class="icon_img"><s:property value="examineeName" /></span></a></td>
					<td><s:property value="licence" /></td>
					<td><s:property value="identity" /></td>
					<td><s:property value="examRoom" /></td>
					<td><s:property value="seatNumber" /></td>
					
				</tr>
			</s:iterator>
		</table>
		
	</div>
	<s:include value="../frame/pageInfo.jsp"></s:include>
	<div id="loading" class="loadingdiv" >  
       				 		<img src="../libs/images/loading/loading4.gif" alt="图片加载中···" />  
				</div>
	<script type="text/javascript">
		function setArea(areaId) {
			$("#headAreaId").val(areaId);
			$("#actionAreaId").val(areaId);
			queryData();
		}
		function queryData() {
			var subject = $("#subjectId").val();
			if(subject=="1"){
				$("#paramFormId").attr("action","findAllResultExaminee1List.action");
			}
			else if(subject=="2"){
				$("#paramFormId").attr("action","findAllResultExaminee2List.action");
			}
			else if(subject=="3"){
				$("#paramFormId").attr("action","findAllResultExaminee3List.action");
			}
			else if(subject=="4"){
				$("#paramFormId").attr("action","findAllResultExaminee4List.action");
			}
			else if(subject=="5"){
				$("#paramFormId").attr("action","findAllResultExaminee5List.action");
			}
			else if(subject=="6"){
				$("#paramFormId").attr("action","findAllResultExaminee6List.action");
			}
			$("#paramFormId").submit();
		}
		
		function showExamineeInofo(licence){
			//打开窗口
			var diag = new top.Dialog();
			diag.Title = "考生详细信息查看";
			diag.URL = "../resultExaminee/findResultExaminee.action?licence="+licence;
			diag.Width = 950;
			diag.Height = 540;
			diag.ShowButtonRow = false;
			diag.show();
		}
		
		 function showImportExcel(){
			var addDialog = new top.Dialog();
			addDialog.Title = "导入考生";
			addDialog.URL = "../resultExaminee/importResultExaminee.jsp";
			addDialog.Width=600;
			addDialog.Height=350;
			addDialog.ShowButtonRow = false;
			addDialog.show();
		} 
		
		
		function importExaminee(){
			var importExamineeURL = "${basePath}/public/importExaminee.action";
			$.ajax({
				type: "POST",  
	            url: importExamineeURL, 
	            cache: false,
	            async: true,
	            dataType : "json",
	            success: function(data){
	            	if(null != data){
	            		if (data.dataStatus == "1") {
	            			alert("同步考生数量：" + data.dataMain);
	            		}else{
	            			if (data.errorMsg != null
									&& data.errorMsg.length > 0) {
								alert(data.errorMsg);
							}
	            		}
	            	}
	            },
	            error : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert("系统异常[" + textStatus + "]:" + errorThrown);
				}
				
			})
		}
		function sumTable(){
			var sumTableURL = "${basePath}/public/sumTable.action";
			$.ajax({
				type: "POST",  
	            url: sumTableURL, 
	            cache: false,
	            async: true,
	            dataType : "json",
	            success: function(data){
	            	if(null != data){
	            		if (data.dataStatus == "1") {
	            			alert("同步完成！");
	            		}else{
	            			if (data.errorMsg != null
									&& data.errorMsg.length > 0) {
								alert(data.errorMsg);
							}
	            		}
	            	}
	            },
	            error : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert("系统异常[" + textStatus + "]:" + errorThrown);
				}
				
			})
		}
		$(function(){
			var subejctSelectHtml = "";
			$.get("${basePath}/admin/resultExaminee/getSubjectData.action",function(data){
				var subjectList = eval("(" + data + ")");
				for ( var i = 0; i < subjectList.length; i++) {
					subejctSelectHtml +="<option value="+subjectList[i].id+">" + subjectList[i].name;
					subejctSelectHtml +="</option>";
				}
				$("#subjectId").html(subejctSelectHtml);
			});
		})
		//ajax请求过程中，显示加载中图片并显示图层，请求完成隐藏图片  
	$(function () {  
	    //注册ajax加载事件  
	    $("#loading").ajaxStart(function () {  
	        //一个div，用来遮挡页面，请求过程中，不可操作页面  
	        var lockwin = $(this);  
	        //div占满整个页面  
	        lockwin.css("width", "100%");  
	        lockwin.css("display", "block");  
	        lockwin.css("height", $(window).height() + $(window).scrollTop());  
	        //设置图片居中  
	        $("#loading img").css("display", "block");  
	        $("#loading img").css("left", ($(window).width() - 88) / 2 -20);  
	        $("#loading img").css("top", ($(window).height() + $(window).scrollTop()) / 2 -20);  
	    });  
	  
	    $("#loading").ajaxStop(function () {  
	        //隐藏div  
	        var lockwin = $(this);  
	        lockwin.css("width", "0");  
	        lockwin.css("display", "none");  
	        lockwin.css("height", "0");  
	        //设置图片隐藏  
	        $("#loading img").css("display", "none");  
	    });  
	});  
	</script>
</body>
</html>
