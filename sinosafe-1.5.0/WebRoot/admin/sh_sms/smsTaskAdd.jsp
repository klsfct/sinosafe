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

<!-- 表单验证start -->
<script src="../libs/js/validation/js/jquery.validationEngine.js"
	type="text/javascript"></script>
<script src="../libs/js/validation/js/jquery.validationEngine-zh_CN.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="../libs/js/validation/css/validationEngine.jquery.css" />
<!-- 表单验证end -->

<!--时间start -->
<script type="text/javascript" src="../libs/js/My97DatePicker/WdatePicker.js"></script>
<!--时间件end -->

<!--树组件start -->
<script type="text/javascript" src="../libs/js/tree/ztree/ztree.js"></script>
<link href="../libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!--树组件end -->

<!--表单异步提交start-->
<script src="../libs/js/form/form.js" type="text/javascript"></script>
<!--表单异步提交end-->
</head>
<body>
		<s:form id="conditionFormId" name="conditionForm"
				action="saveSmsTask" method="post" namespace="/admin/sh_sms">
		<s:hidden name="smsTaskVo.receiverList" id="receiverList"></s:hidden>
		<table width="100%" cellpadding="0" cellspacing="0"
			class="table_border0">
			<tr>
				<td id="leftContent" class="ver01 ali01" style="height: 500px;">
					<div class="box2" panelTitle="接收人列表" roller="false"
						showStatus="false" panelWidth="300" style="padding-top: 6px;">
						<div>
						    <ul id="tree-1" class="ztree" style="overflow-y:auto;overflow-x:hidden;height: 480px;"></ul>
						</div>
					</div></td>
				<td class="ali01 ver01" width="100%">
					<div class="box1">
						<div class="cusBoxContent2"
							style="overflow-y:auto;overflow-x:hidden;height: 525px;">
							
								<table style="width: 100%">
								<input type="hidden" value="1" name="smsTaskVo.taskType"/>
									<tr>
										<td style="width: 80px;"><label>消息内容:</label>:</td>
										<td>
											<button type="button" onclick="addTemplate()">
												<span class="icon_email">插入模板</span>
											</button>
											<%-- <button type="button" onclick="openTemplate()">
												<span class="icon_email">测试单条发送</span>
											</button> --%>
											<s:textarea id="smsContent_id" name="smsTaskVo.smsDescribe"
												cssStyle="width:300px;height:150px;" class="validate[required]" ></s:textarea>
										</td>
									</tr>
									
									<tr>
										<td style="width: 80px;"><label>发送时间:</label></td>
										<td>
											<input type="text" type="text" onClick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })" class="validate[required]"  style="width: 305px;"  name="smsTaskVo.waitSendDate" />
										</td>
									</tr>
									
								</table>
							
						</div>
					</div>
				</td>
			</tr>
		</table>
		</s:form>
	<script type="text/javascript">
		var _topDiag;
		var _sms_index = 0;
		var _smsMaxSize = 290;
		var _formId = "conditionFormId";
		
		//命名空间
		var name_space = "../sh_sms/";
		//插入模板url
		var url_addDialog = name_space +  "smsTemplate.jsp"
		$(function() {
			$("#"+_formId).validationEngine('attach',
					{
						validationEventTrigger: "",
						promptPosition : 'bottomLeft',
						scroll : false,
						autoHidePrompt : true,
						autoHideDelay : 3000,
						autoPositionUpdate : true,
						addFailureCssClassToField : "error-field"
				});
			
			 /* $("#smsContent_id").focus(function() {
				var obj=$("#smsContent_id");
				obj.focus();
				var len = obj.value.length;
				if (document.selection) {
					var sel = obj.createTextRange();
					sel.moveStart('character', len);
					sel.collapse();
					sel.select();
				} else if (typeof obj.selectionStart == 'number'
						&& typeof obj.selectionEnd == 'number') {
					obj.selectionStart = obj.selectionEnd = len;
				}
				 
			}); 
			 $("#smsContent_id").keyup(function() {
				sysNumCheck($("#smsContent_id").val());
			}); */ 
			
			var setting1 = {
			    check: {
			        enable: true
			    }
			};
			
			$.post("findLinkManJsonList.action", {}, function(result){
			    //此处返回的是treeNodes
			    
			    $.fn.zTree.init($("#tree-1"), setting1, result);
			}, "json");
			
		});

		function sysNumCheck(val) {
			//var _signSize = parseInt($("#signVo_singContent").html().length);
			var _smsTextArea = $("#smsContent_id");
			var _smsSize = parseInt(_smsTextArea.val().length);
			var _sendSize = parseInt(_smsSize);
			if (_sendSize > _smsMaxSize) {
				_smsTextArea.val(_smsTextArea.val().substr(0, parseInt(_smsMaxSize)));
				_smsTextArea.next("div").children(".maxNum")
						.css("color", "red").html("超出字数上限了！");
			} else {
				var _smslines = Math.ceil((_sendSize) / 70);
				_smsTextArea.next("div").children(".maxNum").css("color",
						"#000000").html(
						"总字数:" + _sendSize + "（" + _smslines + "条短信）");
			}
		}

		function customHeightSet() {
			$(".cusBoxContent1").height($("#scrollContent").height() - 70);
			$(".cusBoxContent2").height($("#scrollContent").height() - 45);
			$("#spliter-1").height($("#scrollContent").height());
		}
		function openTemplate(){
			window.location.href="../sh_sms/goSaveMessageOnly.jsp";
		}
		//插入模板弹框
		function addTemplate(){
			addDialog = new top.Dialog();
			addDialog.Title = "新建";
			addDialog.URL = url_addDialog;
			addDialog.Width=800;
			addDialog.Height=500;
			addDialog.ID="addTemplate",
			addDialog.ShowButtonRow=true;
			addDialog.OkButtonText="保 存";
			addDialog.OKEvent = function(){
				var _contentWindow = addDialog.innerFrame.contentWindow;
				var _contentdata =_contentWindow.sino_getCheckRowVal("templateDescribe");
				if(_contentdata == ""){
					return false;
				}else{
					$("#smsContent_id").html(_contentdata);
					$("#smsContent_id").attr("value",_contentdata);
					addDialog.close();
				}
				
			};
			addDialog.show();
		}
		function checkedId() {
			//获取接收人
			//获取zTree对象
			    var zTree = $.fn.zTree.getZTreeObj("tree-1");
			    //得到选中的数据集
			    var checkedNodes = zTree.getCheckedNodes(true);
			    //得到未选中的数据集
			    var checkedNodes2 = zTree.getCheckedNodes(false);
			    var _receiverSize=0;
			    var msg = "";
			    for(var i = 0; i < checkedNodes.length; i++){
			        if(checkedNodes[i].id>100000000){
			        	_receiverSize++;
			        	msg += "," + checkedNodes[i].id;
			        }
			    }
				  if(msg == ""){
				      alert("接收人必须填写！");
					return false;
				  }else{
				      msg = msg.substring(1);
				      $("#receiverList").val(msg);
				  }
			
			
			
			//var _signSize = parseInt($("#signVo_singContent").html().length);
			var _smsTextArea = $("#smsContent_id");
			
			var _smsSize = parseInt(_smsTextArea.val().length);
			var _sendSize = parseInt(_smsSize);
			
			if(_smsSize<1){
				alert("短信内容必须填写！");
				return false;
			}
			if (_sendSize > _smsMaxSize) {
				alert("超出字数限制了，建议分2批发送。");
				return false;
			} 
			/* var _smslines = Math.ceil((_sendSize) / 70);
			var _sumSize = _smslines*_receiverSize;
			
			var diag = new top.Dialog();
			diag.Title = "发送确认";
			diag.Width = 300;
			diag.Height = 150;
			var _temp_html = "<div class='checkDialing_box'><p>接收人总数："+_receiverSize+"人</p><p>短信字数"+_sendSize+"字,分"+_smslines+"条发出）</p><p>本次发送共计 <span class='red'> "+_sumSize+" </span>条短信</p><p>确定发送吗？</p></div>";
			diag.InnerHtml= _temp_html;
			diag.ShowButtonRow = true;
			diag.OkButtonText = "确认无误发送";
			diag.OKEvent = function() {
				diag.close();
				$("#conditionFormId").submit();
			};
			diag.show(); */
			return true;
		}
				
		function getformData(){
			return $("#"+_formId).formToArray() ;
		}
		//表单验证
		function formValidate() {
			var _validateFlag = $("#"+_formId).validationEngine("validate");
			return _validateFlag;
		}
		function getformAction(){
			return $("#"+_formId).attr("action");
		}
	</script>
</body>
</html>