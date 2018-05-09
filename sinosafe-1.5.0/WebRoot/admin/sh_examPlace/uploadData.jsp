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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
.speed_box {
	width: 580px;
	height: 20px;
	display: none;
	border: 1px solid #0091f2;
	border-radius: 10px;
	overflow: hidden;
}

#speed {
	width: 0;
	height: 100%;
	background: #0091f2;
	color: white;
	text-align: center;
	line-height: 20px;
	font-size: 16px;
}
</style>
</head>
<body>
	<div class="padding_top10 padding_bottom5 padding_left5 padding_right5">

		<form action="" id="mainForm" method="post"
			enctype="multipart/form-data" namesapce="">
			<table class="tableStyle" formMode="line">
				<tr>
					<td>文件类型：</td>
					<td><s:select
							list="#{'.xlsx':'Excel表格','.docx':'Word文档','.zip':'压缩文件'}"
							id='fileType' name="fileResourceVo.frFileType" listKey="key"
							listValue="value"></s:select></td>
				</tr>
				<tr>
					<td>选择文件：</td>
					<td><input type="file" name="uploadFile" id="file"
						onchange="checkFileType(this)"></input> <span id="uploadMsg"></span></td>
				</tr>

				<tr>
					<td><font color="red"> * </font>文件描述：</td>
					<td><s:textarea name="fileResourceVo.frFileRemarks"
							cssClass="validate[required,maxSize[50]]"
							style="width: 200px;resize:none;" placeholder="描述文件名称和作用" id="remark"></s:textarea></td>

				</tr>

				<tr>
					<td></td>
					<td><input type="button" value="上传" id="btnOk" disabled="true"
						onclick="fileUpload()"></td>
				</tr>
				<div>
					<span> 导入说明：<br /> 1、支持导入<font color="red">Excel
							2003/2007、Word2003/2007、压缩文件zip </font>文件类型！ <br /> 2、<font color="red">且单个文件不能大于100MB！
					</font>
					</span>
				</div>
			</table>
			<table>
				<tr>
					<td>
						<div class="speed_box">
							<div id="speed">0%</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
	 var maxSize = 100*1024*1024;//100M 
	 var errMsg = "上传的附件文件不能超过100M！";  
     var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。"; 
	/* 文件类型检测 */
	function checkFileType(data) {
		// 隐藏上传按钮
		$("#btnOk").attr("disabled", true);
		// 获取选择框
		var selType = $("#fileType").val();
		// 获取文件后缀名 
		var fileName = data.value;
		var fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length);
		// 大小检测
		var fileLength = data.files[0].size; 
		console.log(fileLength);
		console.log(maxSize);
		if(fileLength > maxSize){
			alert(errMsg);
		}else{
			// 判断是否相等
			if (selType == '.xlsx') {
				if (fileType == 'xlsx' || fileType == 'xls') {
					$("#btnOk").attr("disabled", false);
				} else {
					alert("选择上传类型与文件类型不一致！请重新选择！");
					$("#btnOk").attr("disabled", true);
				}
			}
			if (selType == '.docx') {
				if (fileType == 'doc' || fileType == 'docx') {
					$("#btnOk").attr("disabled", false);
				} else {
					alert("选择上传类型与文件类型不一致！请重新选择！");
					$("#btnOk").attr("disabled", true);
				}
			}
			if (selType == '.zip') {
				if (fileType == 'zip') {
					$("#btnOk").attr("disabled", false);
				} else {
					alert("选择上传类型与文件类型不一致！请重新选择！");
					$("#btnOk").attr("disabled", true);
				}
			}
		}
		

	}
	//侦查附件上传情况 ,这个方法大概0.05-0.1秒执行一次
	function OnProgRess(event) {
		var event = event || window.event;
		console.log(event);  //事件对象
		//console.log("已经上传："+ event.loaded); //已经上传大小情况(已上传大小，上传完毕后就 等于 附件总大小)
		//console.log(event.total);  //附件总大小(固定不变)
		var loaded = Math.floor(100 * (event.loaded / event.total)); //已经上传的百分比  
		$("#speed").html(loaded + "%").css("width", loaded + "%");
	};
	// 文件上传
	function fileUpload() {
		// 检查文件描述是否为空
		var remark = $("#remark").val();
		if(remark == ""){
			alert("请填写文件描述！");
		}else{
			$('.speed_box').show();
			var uploadFileUrl = "${basePath}/public/uploadFile.action";
			var formData = new FormData($("#mainForm")[0]);
			var xhr = $.ajaxSettings.xhr(); //创建并返回XMLHttpRequest对象的回调函数(jQuery中$.ajax中的方法)
			$.ajax({
				type : "POST",
				url : uploadFileUrl,
				data : formData,
				cache : false,
				async : true,
				processData : false,
				contentType : false,
				dataType : "json",
				xhr : function() {
					if (OnProgRess && xhr.upload) {
						xhr.upload.addEventListener("progress", OnProgRess,
								false);
						return xhr;
					}
				},
				success : function(data) {
					if (null != data) {
						if (data.dataStatus == "1") {
							$("#btnOk").attr("disabled", false);
							$("#speed").html("<font>上传完成！</font>");
						} else {
							if (data.errorMsg != null
									&& data.errorMsg.length > 0) {
								alert(data.errorMsg);
								$("#uploadMsg").html(
										"<font color='red'>" + data.errorMsg
												+ "请仔细阅读操作说明！</font>");
							}
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("系统异常[" + textStatus + "]:" + errorThrown);
				}
			});
		}
		
	}
	</script>
</body>
</html>
