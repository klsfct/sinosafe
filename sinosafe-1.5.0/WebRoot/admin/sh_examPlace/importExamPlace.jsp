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
<script src="../libs/js/validation/js/jquery.validationEngine.js"
	type="text/javascript"></script>
<script src="../libs/js/validation/js/jquery.validationEngine-zh_CN.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="../libs/js/validation/css/validationEngine.jquery.css" />
<!-- 表单验证end -->
<style type="text/css">
.speed_box{ width: 90%; height: 20px; display: none; border: 1px solid #0091f2; border-radius: 10px; overflow: hidden;}
#speed{ width: 0; height: 100%; background: #0091f2; color: white; text-align: center; line-height: 20px; font-size: 16px;}
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
	<div class="padding_top10 padding_bottom5 padding_left5 padding_right5">
		
		<form action="importResultExaminee.action" id="mainForm"
			method="post" enctype="multipart/form-data" namesapce="">
			<table class="tableStyle" formMode="line">
			<input type="hidden" id="excelFilePath" name="excelFilePath" value="" />
			<input type="hidden" name="fileResourceVo.frFileType" value=".xlsx" />
			
				<tr>
					<td>选择文件：</td>
					<td><input type="file" name="uploadFile" id="excelFile" accept=".xlsx,.xls" onchange="checkFileType(this)"></input></td>				</tr>
				<tr>
					<td><font color="red"> * </font>文件描述：</td>
					<td><s:textarea name="fileResourceVo.frFileRemarks"
							cssClass="validate[required,maxSize[50]]"  style="width: 200px;resize:none;" id="remark"
							placeholder="描述文件名称和作用"></s:textarea></td>
						
				</tr>
				<tr>
					<td></td>
					<td> <input type="button" value="上传" id="upload" onclick="fileUpload()" disabled="true">
					&nbsp;
					<input type="button" value="导入" id="btnOk" disabled="true" onclick="importEmp()"/>
					</td>
				</tr>
				 <div>
					<span>
					导入说明：<br/>
					<%-- 1、点击下载<a href="${basePath}/public/importTemplate/考生信息导入模板.xlsx"><font color="red">《考生信息导入模板》</font>。</a>  <br/>
					2、点击下载导入操作指南<a href="${basePath}/public/importTemplate/考生信息导入说明.docx"><font color="red">《导入说明》</font>。</a><br /> --%>
					1、仅支持导入<font color="red">.xls .xlsx</font>文件！<br />
					2、<font color="red">上传单个文件大小不能大于100MB!</font>
					</span>
				</div>
					<tr>
					<td>上传进度：</td>
						<td>
							<div class="speed_box"><div id="speed">0%</div></div>
						</td>
					</tr>
					

				</table>
				<div id="loading" class="loadingdiv" >  
       				 		<img src="../libs/images/loading/loading4.gif" alt="图片加载中···" />  
				</div> 
		</form>
	</div>
<script type="text/javascript">
	var maxSize = 100*1024*1024;//100M 
	var errMsg = "上传的附件文件不能超过100M！";  
	var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。"; 
	/* 文件类型检测 */
	function checkFileType(data){
		// 隐藏上传按钮
		$("#btnOk").attr("disabled",true);
		// 获取文件后缀名 
		var fileName = data.value;
		var fileType = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length);
		// 判断是否相等
		// 大小检测
		var fileLength = data.files[0].size; 
		if(fileLength > maxSize){
			alert(errMsg);
		}else{
			if(fileType == 'xlsx' || fileType == 'xls'){
				$("#upload").attr("disabled",false);
			}else{
				alert("仅支持上传xls和xlsx文件！");
				$("#upload").attr("disabled",true);
				$("#btnOk").attr("disabled",true);
			}
		}
		
	}
	//侦查附件上传情况 ,这个方法大概0.05-0.1秒执行一次
	function OnProgRess(event) {
		var event = event || window.event;
		//console.log(event);  //事件对象
		//console.log("已经上传："+ event.loaded); //已经上传大小情况(已上传大小，上传完毕后就 等于 附件总大小)
		//console.log(event.total);  //附件总大小(固定不变)
		var loaded = Math.floor(100 * (event.loaded / event.total)); //已经上传的百分比  
		$("#speed").html(loaded + "%").css("width", loaded + "%");
	};
	// 文件上传
	function fileUpload(){
		// 检查文件描述是否为空
		var remark = $("#remark").val();
		if(remark == ""){
			alert("请填写文件描述！");
		}else{
			$('.speed_box').show();
			$("#btnOk").attr("disabled",true);
	    	var uploadFileUrl = "${basePath}/public/uploadFile.action";
			var formData = new FormData($("#mainForm")[0]);
			var xhr = $.ajaxSettings.xhr(); 
			$.ajax({
				type: "POST",  
	            url: uploadFileUrl, 
	            data: formData,
	            cache: false,
	            async: true,
	            processData: false,  
	            contentType: false, 
	            dataType : "json",
	            xhr: function() {
					if(OnProgRess && xhr.upload) {
						xhr.upload.addEventListener("progress", OnProgRess, false);
						return xhr;
					}
				},
	            success: function(data){
	            	if(null != data){
	            		if (data.dataStatus == "1") {
	            			$("#btnOk").attr("disabled",false);
	            			$("#excelFilePath").attr("value",data.dataMain.frLocalPath);
	            			$("#speed").html("<font>上传完成！</font>");
						} else {
							if (data.errorMsg != null
									&& data.errorMsg.length > 0) {
								alert(data.errorMsg);
								$("#uploadMsg").html("<font color='red'>" +data.errorMsg + "请仔细阅读操作说明！</font>");
							}
						}
	            	}
	            },
	            error : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert("系统异常[" + textStatus + "]:" + errorThrown);
				}
			});
		}
		
	}
	
	//Excel文件导入到数据库中       
	function importEmp(){  
		var importExcelURL = "${basePath}/public/importExamPlace.action";
		var excelFilePath = $("#excelFilePath").val();
		$.ajax({
			type: "POST",  
            url: importExcelURL, 
            data: {"excelFilePath":excelFilePath},
            cache: false,
            async: true,
            dataType : "json",
            success: function(data){
            	if(null != data){
            		if (data.dataStatus == "1") {
            			$("#btnOk").attr("disabled",false);
            			alert("本次成功导入考点："+ data.dataMain +"个，请认真核对数量，如导入有误请联系管理员！");
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
