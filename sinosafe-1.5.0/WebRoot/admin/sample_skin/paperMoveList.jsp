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
<!-- <link rel="stylesheet" type="text/css" id="customSkin" /> -->
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
					<li><span>考点名称 ：</span></li>
					<li><input type="text" name="query[0]_examPlace.examPlaceName_like" style="width:120px"/></li>
					<li>
						<div class="box_tool_line"></div>
					</li>
					<li><span>交接人姓名 ：</span></li>
					<li><input type="text" name="query[0]_linkManName_like" style="width:120px"/></li>
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
		var name_space = "../sh_linkman/";
		
		//列表url
		var url_data = name_space +"findPaperMoveList.action";
		//新建页面url
		var url_addDialog = name_space +"gotoAddLinkManLogin.action";
		//修改页面url
		var url_editDialog = name_space + "gotoModifyLinkManLogin.action";
		//删除方法url
		var url_delete= name_space + "removeLinkManLogin.action";
		
		//ID变量名称
		var paramIdName = "linkManLoginVo.loginId";
		
		
		function initComplete() {
			//当提交表单刷新本页面时关闭弹窗
			top.Dialog.close();
			grid = $("#dataBasic")
					.quiGrid(
							{
								columns : [
										{
											display : "考点",
											name : "examPlace.examPlaceName",
											width:160
										},
										{
											display : "科目名称 ",
											name : "subjectName",
											width:120
										},
										{
											display : "试卷运输状态 ",
											name : "moveTypeName",
											width:100
										},
										{
											display : "交接人姓名 ",
											name : "linkManName",
											width:120
										},
										{
											display : "交接时间 ",
											name : "careateDate",
											width:160
										},
										{
											display : "交接地点描述 ",
											name : "moveAddr",
											width:160
										},
										{
											display : "交接地点纬度 ",
											name : "moveLat",
											width:160
										},
										{
											display : "交接地点经度",
											name : "moveLng",
											width:160
										}
										/* ,
										{
											display : "操作",
											isAllowHide : false,
											width:180,
											render : function(rowdata,
													rowindex, value, column) {
												return " <a href=\"javascript:onEdit(\'"+ rowdata.id + "\')\" title='修改' class='icon_edit hand'>修改</a>"
														+ "<a href=\"javascript:onDelete(\'"+ rowdata.id + "\')\" title='删除' class='icon_remove hand'>删除</a>";
											} 
										}*/ ],
								url : url_data,
								sortName : "id",
								rownumbers : true,
								height : "100%",
								width : "100%",
								pageSize:20 /*,
							     toolbar:{
							    	 items:[
							    		  {text: "新建工作人员2", click: addData,iconClass: "icon_add"},
							    		  { line : true }
							    		]
							     	} */
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
			addDialog.Title = "新建";
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
			editDialog.Title = "修改";
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
			if (confirm("确认删除分组吗？")) {
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
		
		//查看
		function onView(rowid) {
			top.Dialog.open({
				URL : url_viewDialog + "?"+paramIdName+"=" + rowid,
				Title : "查看",
				Width : 600,
				Height : 130
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

