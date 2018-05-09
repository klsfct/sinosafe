<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="../libs/js/jquery.js"></script>
<script type="text/javascript" src="../libs/js/framework.js"></script>
<link href="../libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="../" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<!--框架必需end-->

<!--弹窗组件start-->
<script type="text/javascript" src="../libs/js/popup/drag.js"></script>
<script type="text/javascript" src="../libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->

<!-- 日期选择框start -->
<script type="text/javascript"
	src="../libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->

<!--数据表格start-->
<script src="../libs/js/table/quiGrid.js" type="text/javascript"></script>
<!--数据表格end-->

<!--表单异步提交start-->
<script src="../libs/js/form/form.js" type="text/javascript"></script>
<!--表单异步提交end-->
</head>
<body>
	<div class="padding_top5 padding_right5 padding_bottom5">
		<div class="box_tool_user">
			<form id="queryForm" method="post">
				<ul>
					<li><span>设备序列号：</span></li>
					<li>
					<s:textfield name="query[0]_deviceSerial_like" maxlength="20"
						cssStyle="width:180px"></s:textfield>
					</li>
					<li><span>考点名称：</span></li>
					<li>
					<s:textfield name="query[0]_examPlace.examPlaceName_like" maxlength="20"
						cssStyle="width:180px"></s:textfield>
					</li>
					<li><span>设备类别：</span></li>
					<li>
					<s:select list="@com.examsafety.service.ConstantsService@getVideoDeviceTypeMap()"
					name="query[0]_deviceType" listKey="key" listValue="value" cssStyle="height:23px;"
					headerKey="" headerValue="===请选择==="></s:select>
					</li>
					<li>
						<div class="box_tool_line"></div>
					</li>
					<li><a href="javascript:searchHandler();"><span
							class="icon_find">查询</span> </a></li>
					<li>
						<div class="box_tool_line"></div>
					</li>
					<li><a href="javascript:resetSearch();"><span
							class="icon_reload">重置</span> </a></li>
					<li>
						<div class="box_tool_line"></div>
					</li>
					
					
				</ul>
			</form>
			<div class="clear"></div>
		</div>
	</div>
	<div class="padding_right5">
		<div id="dataBasic"></div>
	</div>
	<script type="text/javascript">
		//grid
		var grid = null;
		var addDialog = null;
		var editDialog = null;
		
		//命名空间
		var name_space = "../sh_deviceVideo/";
		
		//列表url
		var url_data = name_space + "findDeviceVideoList.action";
		//新建页面url
		var url_addDialog = name_space + "gotoSaveDeviceVideo.action";
		//修改页面url
		var url_editDialog = name_space + "gotoUpdateDeviceVideo.action";
		//删除方法url
		var url_delete= name_space + "delDeviceVideo.action";
		//注册url
		var url_reg = name_space + "regDeviceVideo.action";
		//批量注册url
		var url_batchReg = name_space + "batchRegDeviceVideo.action";
		//ID变量名称
		var paramIdName = "deviceVideoInfoVo.deviceSerial";
		
		
		function initComplete() {
			//当提交表单刷新本页面时关闭弹窗
			top.Dialog.close();
			grid = $("#dataBasic")
					.quiGrid(
							{
								columns : [
										{
											display : "设备序列号 ",
											name : "deviceSerial",
											width:150
										},
										{
											display : "设备名称 ",
											name : "deviceName",
											width:250
										},
										{
											display : "设备编码",
											name : "deviceNumber",
											width:80
										},
										/* {
											display : "省（区、市） ",
											name : "examPlaceVo.examProvinceName",
											width:180
										},
										{
											display : "考区 ",
											name : "examPlaceVo.examAreaName",
											width:155
										}, */
										{
											display : "考点 ",
											name : "examPlaceVo.examPlaceName",
											width:155
										},
										{
											display : "类别 ",
											name : "deviceTypeName",
											width:155
										},
										{
											display : "是否注册",
											name : "regFlag",
											width:155,
											render : function(rowdata,
													rowindex, value, column) {
												if(value == "1"){
													return "<font color='green'>已注册</font>";
												}
												if(value == "0"){
													return "未注册";
												}
												else{
													return "<font color='red'>注册失败</font>";
												}
											} 
										},
										{
											display : "录入时间",
											name : "inputTime",
											width:155
										},
										{
											display : "注册时间",
											name : "regTime",
											width:155
										},
										{
											display : "在线状态",
											name : "devStatus",
											width:155,
											render : function(rowdata,
													rowindex, value, column) {
												if(value == "0"){
													return "不在线";
												}
												else{
													return "<font color='green'>在线</font>";
												}
											} 
										},
										{
											display : "操作",
											isAllowHide : false,
											width:182,
											render : function(rowdata,
													rowindex, value, column) {
												if(rowdata.regFlag == "1"){
													return"<a href=\"javascript:onEdit(\'"+ rowdata.deviceSerial + "\')\" title='修改' class='icon_edit hand'>修改</a>";
													
												}
												else{
													return " <a href=\"javascript:regData(\'"+ rowdata.deviceSerial + "\')\" title='注册' class='icon_edit hand'>注册</a>"
													+ "<a href=\"javascript:onEdit(\'"+ rowdata.deviceSerial + "\')\" title='修改' class='icon_edit hand'>修改</a>"
													+ "<a href=\"javascript:onDelete(\'"+ rowdata.deviceSerial + "\')\" title='删除' class='icon_remove hand'>删除</a>";
												}
												
											}
										} ],
								url : url_data,
								sortName : "id",
								rownumbers : true,
								height : "100%",
								width : "100%",
								pageSize:20,
							     toolbar:{
							    	 items:[
							    		  {text: "新增视频设备", click: addData,iconClass: "icon_add"},
							    		  { line : true },
							    		  {text: "批量注册", click: batchRegData,iconClass: "icon_add"},
							    		  { line : true }
							    		]
							     	}
							});

			//监听查询框的回车事件
			$("#searchInput").keydown(function(event) {
				if (event.keyCode == 13) {
					searchHandler();
				}
			});
			$("#searchPanel").bind("stateChange", function(e, state) {
				grid.resetHeight();
			});
		}
		$.quiDefaults.Grid.formatters["date"] = function(value, column) {
			if (null != value && value.length > 0) {
				return value;
			} else {
				return "-";
			}
		};
		//新增
		function addData() {
			addDialog = new top.Dialog();
			addDialog.Title = "新增视频设备";
			addDialog.URL = url_addDialog;
			addDialog.Width=600;
			addDialog.Height=300;
			addDialog.ID="addDialog",
			addDialog.ShowButtonRow=true;
			addDialog.OkButtonText="保 存";
			addDialog.OKEvent = function(){
				var _contentWindow = addDialog.innerFrame.contentWindow;
				var _formValidateFlag = _contentWindow.formValidate();
				if (_formValidateFlag) {
					//验证通过
					var _formAction = _contentWindow.getformAction();
					var _formData = _contentWindow.getformData();
					$.ajax({
						type : "POST",
						url : _formAction,
						cache : true,
						data : _formData,
						dataType : "json",
						success : function(data) {
							if (data != null) {
								if (data.dataStatus == "1") {
									//保  存成功
									addDialog.close();
									grid.setNewPage(1);
									grid.loadData();//加载数据
								} else {
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
					});
				}
			};
			addDialog.show();
		}
		
		//修改
		function onEdit(rowid) {
			editDialog = new top.Dialog();
			editDialog.Title = "修改视频设备信息";
			editDialog.URL = url_editDialog+ "?"+paramIdName+"=" + rowid;
			editDialog.Width=600;
			editDialog.Height=300;
			editDialog.ID="editDialog",
			editDialog.ShowButtonRow=true;
			editDialog.OkButtonText="保  存";
			editDialog.OKEvent = function(){
				var _contentWindow = editDialog.innerFrame.contentWindow;
				var _formValidateFlag = _contentWindow.formValidate();
				if (_formValidateFlag) {
					//验证通过
					var _formAction = _contentWindow.getformAction();
					var _formData = _contentWindow.getformData();
					$.ajax({
						type : "POST",
						url : _formAction,
						cache : true,
						data : _formData,
						dataType : "json",
						success : function(data) {
							if (data != null) {
								if (data.dataStatus == "1") {
									//保  存成功
									editDialog.close();
									grid.loadData();//加载数据
								} else {
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
					});
				}
			};
			editDialog.show();
		}
		
		//删除
		function onDelete(rowid) {
			if (confirm("确认删除？")) {
				$.ajax({
					type : "POST",
					url : url_delete,
					async : false,
					data : paramIdName + "=" + rowid,
					dataType : "json",
					success : function(data) {
						if (data != null) {
							if (data.dataStatus == "1") {
								//删除成功
								grid.loadData();//加载数据
							} else {
								if (data.errorMsg != null
										&& data.errorMsg.length > 0) {
									alert(data.errorMsg);
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
		
		//注册
		function regData(rowid) {
				$.ajax({
					type : "POST",
					url : url_reg,
					async : false,
					data : paramIdName + "=" + rowid,
					dataType : "json",
					success : function(data) {
						if (data != null) {
							if (data.dataStatus == "1") {
								grid.loadData();//加载数据
							} else {
								if (data.errorMsg != null
										&& data.errorMsg.length > 0) {
									alert(data.errorMsg);
								}
							}
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("系统异常[" + textStatus + "]:" + errorThrown);
					}
				});
		}
		//批量注册
		function batchRegData() {
				$.ajax({
					type : "POST",
					url : url_batchReg,
					async : false,
					dataType : "json",
					success : function(data) {
						if (data != null) {
							if (data.dataStatus == "1") {
								grid.loadData();//加载数据
							} else {
								if (data.errorMsg != null
										&& data.errorMsg.length > 0) {
									alert(data.errorMsg);
								}
							}
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("系统异常[" + textStatus + "]:" + errorThrown);
					}
				});
		}
		
		//查询
		function searchHandler() {
			var query = $("#queryForm").formToArray();
			grid.setOptions({
				params : query
			});
			//页号重置为1
			grid.setNewPage(1);
			grid.loadData();//加载数据
		}

		//重置查询
		function resetSearch() {
			$("#queryForm")[0].reset();
			grid.setOptions({
				params : null
			});
			//页号重置为1
			grid.setNewPage(1);
			grid.loadData();//加载数据
		}
	</script>
</body>
</html>

