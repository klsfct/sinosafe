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


<!--树组件start -->
<script type="text/javascript" src="../libs/js/tree/ztree/ztree.js"></script>
<link href="../libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!--树组件end -->
</head>
<body>
		<s:form id="conditionFormId" name="conditionForm"
				action="sendMessage" method="post" namespace="/admin/sms">
		<s:hidden name="receiverList" id="receiverList"></s:hidden>
		<table width="100%" cellpadding="0" cellspacing="0"
			class="table_border0">
			<tr>
				<td id="leftContent" class="ver01 ali01" style="height: 600px;">
					<div class="box2" panelTitle="接收人列表" roller="false"
						showStatus="false" panelWidth="300" >
						<div>
						    <ul id="tree-1" class="ztree"></ul>
						</div>
					</div></td>
				<td class="ali01 ver01" width="100%">
					<div class="box1">
						<div class="cusBoxContent2"
							style="overflow-y:auto;overflow-x:hidden;">
							<fieldset>
								<legend>编辑短信内容</legend>
								<table style="width: 100%">
									<tr>
										<td><s:textarea id="smsContent_id" name="smsInfoVo.message"
												cssStyle="width:400px;height:150px;"
												watermark="请编辑短信内容，最多300字，每70字为一条短信（含短信签名）"></s:textarea>
											<div style="width:500px;">
												<div class="maxNum float_left padding5">总字数:0</div>
												<div class="float_right padding5">
													签名:<span id="signVo_singContent">【上海司考办】</span>
												</div>
											</div></td>
									</tr>
									<tr>
										<td>
											<button type="button" onclick="sendMessage()">
												<span class="icon_email">发送短信</span>
											</button></td>
									</tr>
								</table>
							</fieldset>
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
		$(function() {
			$("#smsContent_id").keyup(function() {
				sysNumCheck();
			});
			
			var setting1 = {
			    check: {
			        enable: true
			    }
			};
			
			$.post("findLinkManJsonList.action", {}, function(result){
			    //此处返回的是treeNodes
			    console.log(result);
			    $.fn.zTree.init($("#tree-1"), setting1, result);
			}, "json");
			
		});

		function sysNumCheck() {
			var _signSize = parseInt($("#signVo_singContent").html().length);
			var _smsTextArea = $("#smsContent_id");
			var _smsSize = parseInt(_smsTextArea.val().length);
			var _sendSize = parseInt(_signSize+_smsSize);
			if (_sendSize > _smsMaxSize) {
				_smsTextArea.val(_smsTextArea.val().substr(0, parseInt(_smsMaxSize-_signSize)));
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

		function sendMessage() {
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
					return;
				  }else{
				      msg = msg.substring(1);
				      $("#receiverList").val(msg);
				  }
			
			
			
			var _signSize = parseInt($("#signVo_singContent").html().length);
			var _smsTextArea = $("#smsContent_id");
			_smsTextArea.focus();
			var _smsSize = parseInt(_smsTextArea.val().length);
			var _sendSize = parseInt(_signSize+_smsSize);
			
			if(_smsSize<1){
				alert("短信内容必须填写！");
				return;
			}
			if (_sendSize > _smsMaxSize) {
				alert("超出字数限制了，建议分2批发送。");
				return;
			} 
			var _smslines = Math.ceil((_sendSize) / 70);
			var _sumSize = _smslines*_receiverSize;
			
			var diag = new top.Dialog();
			diag.Title = "发送确认";
			diag.Width = 300;
			diag.Height = 150;
			var _temp_html = "<div class='checkDialing_box'><p>接收人总数："+_receiverSize+"人</p><p>短信字数"+_sendSize+"字（包含签名"+_signSize+"字,分"+_smslines+"条发出）</p><p>本次发送共计 <span class='red'> "+_sumSize+" </span>条短信</p><p>确定发送吗？</p></div>";
			diag.InnerHtml= _temp_html;
			diag.ShowButtonRow = true;
			diag.OkButtonText = "确认无误发送";
			diag.OKEvent = function() {
				diag.close();
				$("#conditionFormId").submit();
			};
			diag.show();
		}

	</script>
</body>
</html>