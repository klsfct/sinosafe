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
					<li><span>考点名称：</span></li>
					<li>
					<s:textfield name="query[0]_examPlaceName_like" maxlength="20"
						cssStyle="width:180px"></s:textfield>
					</li>
					<li>
						<div class="box_tool_line"></div>
					</li>
					<li><span>考点地址：</span></li>
					<li>
					<s:textfield name="query[0]_examAddr_like" maxlength="20"
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
					<s:if test="#session.loginSession.level == 4">
					<li><a href="javascript:downLoadFile();"><span
							class="icon_btn_down">考点数据包下载</span> </a></li>
					</s:if>
					
					<s:if test="#session.loginSession.level == 2">
					
					<li><a href="javascript:upLoadFile();"><span
							class="icon_btn_up">考点相关文件上传</span> </a></li>
					</s:if>
					<s:if test="#session.loginSession.level == 1">
						<li><a href="javascript:showImportExcel();"><span
							class="icon_import">考点导入</span> </a></li>
					</s:if>
					
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
		var name_space = "../sh_examPlace/";
		
		//列表url
		var url_data = name_space + "findExamPlaceList.action";
		
		//列表url
		var url_add_data = name_space + "gotoAddExamPlace.action";
		//获取坐标url
		var url_getPoint = name_space + "getPointByExamAddr.action";
		
		//批量获取坐标url
		var url_batchGetPoint= name_space + "batchGetPoint.action";
		
		//人工校对URL
		var url_updatePoint = name_space + "gotoUpdatePoint.action";
		//ID变量名称
		var paramIdName = "examPlaceVo.examPlace";
		
		
		function initComplete() {
			//当提交表单刷新本页面时关闭弹窗
			top.Dialog.close();
			grid = $("#dataBasic")
					.quiGrid(
							{
								columns : [
										{
											display : "省名称 ",
											name : "examProvinceName",
											width:100
										},
										{
											display : "考区名称",
											name : "examAreaName",
											width:80
										},
										{
											display : "考点名称 ",
											name : "examPlaceName",
											width:180
										},
										{
											display : "考点人数 ",
											name : "examineeSum",
											width:60
										},
										{
											display : "考点地址 ",
											name : "examAddr",
											width:200
										},
										{
											display : "经度 ",
											name : "pointLng",
											width:155
										},
										{
											display : "纬度 ",
											name : "pointLat",
											width:155
										},
										{
											display : "是否启用",
											name : "isEnabled",
											width:80,
											render : function(rowdata,
													rowindex, value, column) {
												if(value == "1"){
													return "<font color='green'>启用</font>";
												}
												
												else{
													return "<font color='red'>未启用</font>";
												}
											} 
										},
										{
											display : "操作",
											isAllowHide : false,
											width:182,
											render : function(rowdata,
													rowindex, value, column) {
												
													return " <a href=\"javascript:getPoint(\'"+ rowdata.examPlace + "\')\" title='获取坐标' class='icon_edit hand'>获取坐标</a>"
													+ "<a href=\"javascript:updatePoint(\'"+ rowdata.examPlace + "\')\" title='修改' class='icon_edit hand'>修改</a>"
													/* + "<a href=\"javascript:deleteExamPlace(\'"+ rowdata.examPlace + "\')\" title='删除' class='icon_edit hand'>删除</a>" */
												
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
							    		 {text: "批量获取坐标", click: batchGetPoint,iconClass: "icon_add"},
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
		
		//获取坐标
		function getPoint(rowid) {
				$.ajax({
					type : "POST",
					url : url_getPoint,
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
		//批量获取坐标
		function batchGetPoint() {
				$.ajax({
					type : "POST",
					url : url_batchGetPoint,
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
		//修改
		function updatePoint(rowid) {
			editDialog = new top.Dialog();
			editDialog.Title = "人工校对";
			editDialog.URL = url_updatePoint+ "?"+paramIdName+"=" + rowid;
			editDialog.Width=600;
			editDialog.Height=400;
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
		function downLoadFile(){
			//打开窗口
			var diag = new top.Dialog();
			diag.Title = "考点数据下载";
			diag.URL = "downLoadFile.jsp";
			diag.Width = 450;
			diag.Height = 300;
			diag.ShowButtonRow = false;
			diag.show();
		}
		function upLoadFile(){
			var addDialog = new top.Dialog();
			addDialog.Title = "上传数据";
			addDialog.URL = "../sh_examPlace/uploadData.jsp";
			addDialog.Width=600;
			addDialog.Height=350;
			addDialog.ShowButtonRow = false;
			addDialog.show();
		}
		// 考点信息导入
		 function showImportExcel(){
				var addDialog = new top.Dialog();
				addDialog.Title = "导入考点";
				addDialog.URL = "../sh_examPlace/importExamPlace.jsp";
				addDialog.Width=600;
				addDialog.Height=350;
				addDialog.ShowButtonRow = false;
				addDialog.show();
			} 
	</script>
</body>
</html>

