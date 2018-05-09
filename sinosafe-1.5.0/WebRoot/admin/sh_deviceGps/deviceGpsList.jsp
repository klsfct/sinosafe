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
					<li><span>设备名称：</span></li>
					<li>
					<s:textfield name="query[0]_entityName_like" maxlength="20"
						cssStyle="width:180px"></s:textfield>
					</li>
					<li>
						<div class="box_tool_line"></div>
					</li>
					<li><span>所属考点：</span></li>
					<li>
					<s:textfield name="query[0]_examPlace.examPlaceName_like" maxlength="20"
						cssStyle="width:180px"></s:textfield>
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
		var name_space = "../sh_deviceGps/";
		
		//列表url
		var url_data = name_space + "findDeviceGpsList.action";
		
		//绑定考点页面url
		var url_setExamPlace = name_space + "gotoSetExamPlace.action";
		
		//编辑联系人信息url
		var url_updateLinkMan= name_space + "gotoUpdateLinkMan.action";
		
		//获取设备url
		var url_getDeviceGps = name_space + "getDeviceGps.action";
		
		//批量设置起点坐标url
		var url_setBeginPonit = name_space + "gotoSetBeginPonit.action";
		
		//同步联系人url
		var url_syncLinkMan = name_space + "syncLinkMan.action";
		
		//ID变量名称
		var paramIdName = "deviceGpsInfoVo.gpsId";
		
		
		function initComplete() {
			//当提交表单刷新本页面时关闭弹窗
			top.Dialog.close();
			grid = $("#dataBasic")
					.quiGrid(
							{
								columns : [
										{
											display : "设备名称 ",
											name : "entityName",
											width:150
										},
										{
											display : "设备描述 ",
											name : "entityDesc",
											width:300
										},
										{
											display : "所属考点",
											name : "examPlaceVo.examPlaceName",
											width:180
										},
										{
											display : "起点名称 ",
											name : "beginName",
											width:180
										},
										{
											display : "起点围栏",
											name : "beginFenceId",
											width:60,
											render : function(rowdata,
													rowindex, value, column) {
												if(value == null){
													return "<font color='red'>未设置</font>";
												}
												else{
													return "<font color='green'>已设置</font>";
												}
											} 
										},
										{
											display : "终点围栏",
											name : "endFenceId",
											width:60,
											render : function(rowdata,
													rowindex, value, column) {
												if(value == null){
													return "<font color='red'>未设置</font>";
												}
												else{
													return "<font color='green'>已设置</font>";
												}
											} 
										},
										{
											display : "操作",
											isAllowHide : false,
											width:182,
											render : function(rowdata,
													rowindex, value, column) {
												return"<a href=\"javascript:setExamPlace(\'"+ rowdata.gpsId + "\')\" title='绑定考点' class='icon_edit hand'>'绑定考点</a>"
												+ "<a href=\"javascript:updateLinkMan(\'"+ rowdata.gpsId + "\')\" title='编辑联系人' class='icon_remove hand'>编辑联系人</a>";
										} 
										}
										],
								url : url_data,
								sortName : "id",
								rownumbers : true,
								height : "100%",
								width : "100%",
								pageSize:20,
							     toolbar:{
							    	 items:[
							    		  {text: "获取设备", click: getDeviceGps,iconClass: "icon_add"},
							    		  { line : true },
							    		  {text: "批量设置起点坐标", click: setBeginPonit,iconClass: "icon_add"},
							    		  { line : true },
							    		  {text: "同步联系人", click: syncLinkMan,iconClass: "icon_add"},
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
		
		//绑定考点
		function setExamPlace(rowid) {
			editDialog = new top.Dialog();
			editDialog.Title = "绑定考点";
			editDialog.URL = url_setExamPlace+ "?"+paramIdName+"=" + rowid;
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
		//编辑联系人
		function updateLinkMan(rowid) {
			editDialog = new top.Dialog();
			editDialog.Title = "更新联系人信息";
			editDialog.URL = url_updateLinkMan+ "?"+paramIdName+"=" + rowid;
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
		//获取设备
		function getDeviceGps() {
				$.ajax({
					type : "POST",
					url : url_getDeviceGps,
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
		//同步联系人
		function syncLinkMan() {
				$.ajax({
					type : "POST",
					url : url_syncLinkMan,
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
		//批量设置起点坐标
		function setBeginPonit() {
			addDialog = new top.Dialog();
			addDialog.Title = "批量设置起点坐标";
			addDialog.URL = url_setBeginPonit;
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

