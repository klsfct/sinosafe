<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post"
		action="findQRoomByPlace" namespace="/admin/resultExaminee">
		<s:hidden id="beginRoomId" name="beginRoom"></s:hidden>
		<s:hidden id="endRoomId" name="endRoom"></s:hidden>
		<div class="padding_right5 padding_top5">
		
		
		<s:if test="examPlaceList !=null">
			<span>选择考点：</span>
			<s:select list="examPlaceList" name="examPlaceVo.examPlace"  id="examPlaceVo_examPlace"
				listKey="examPlace" listValue="examPlaceName"></s:select>
		</s:if>
		<s:else>
			<s:hidden name="examPlaceVo.examPlace" id="examPlaceVo_examPlace"></s:hidden>
		</s:else>
		
		<s:if test="subjectList !=null">
			<span> 选择场次：</span>
			<s:select list="subjectList" name="subject" id="subjectId"
				listKey="id" listValue="name"></s:select>
		</s:if>
		<s:else>
			<s:hidden name="subjectVo.id" id="subjectId"></s:hidden>
		</s:else>
		<span>打印个数：</span>
		<s:select list="#{1:'===1个===',5:'===5个===',10:'===10个===',20:'===20个==='}"
			name="interval" listKey="key" listValue="value"></s:select>
		<button type="button" onclick="queryData()">
			<span class="icon_find">查询</span>
		</button>
		</div>
		<div class="padding_right5 padding_top3">
		<span style="color:red;font-size:16px;">1、打印报表前请确认所有安检通道人脸识别数据已经上传完毕，如未全部上传将会造成缺考数据不准确。</span>
		</div>
		<div class="padding_right5 padding_top3">
		<span style="color:red;font-size:16px;">2、如发现未通过安检通道进入考场参加考试人员，请选择对应科目和并按1个考场分组查询后进行处理。</span>
		</div>
		<div class="padding_right5 padding_top3">
		<span style="color:red;font-size:16px;">3、打印前请在页面设置中将页边距设置为0，并清空页眉页脚。</span>
		</div>
	</s:form>
	<div id="scrollContent" class="padding_right5 padding_top5">
		<table class="tableStyle" mode="list">
			<tr>
				<th>序号</th>
				<th>考点</th>
				<th>考场</th>
				<th>缺考人数</th>
				<th>打印</th>
			</tr>
			<s:iterator value="resultList" status="st1">
				<tr>
					<td><s:property value="#st1.count" /></td>
					<td><s:property value="examPlaceVo.examPlaceName" /></td>
					<td><s:property value="resultList[#st1.index][0]" />&nbsp;至&nbsp;<s:property
							value="resultList[#st1.index][1]" />考场</td>
					<td><s:property value="resultList[#st1.index][2]" /></td>
					<td><a
						href="javascript:findQResultExamineeForRpt(<s:property value="resultList[#st1.index][0]" />,<s:property value="resultList[#st1.index][1]" />);"><span class="icon_print">打印报表</span></a>
						<%--  <a
						href="javascript:findQResultExamineeForModify(<s:property value="resultList[#st1.index][0]" />,<s:property value="resultList[#st1.index][1]" />);"><span class="icon_ok">处理上报</span></a></a> --%>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<script type="text/javascript">
		function queryData() {
			$("#paramFormId").submit();
		}
		//打印
		function findQResultExamineeForRpt(beginRoom, endRoom){
			//打开窗口
			var diag = new top.Dialog();
			diag.Title = "核查表打印";
			diag.URL = "../resultExaminee/findQResultExamineeForRpt.action?beginRoom="+beginRoom+"&endRoom="+endRoom+"&examPlaceVo.examPlace="+$("#examPlaceVo_examPlace").val()+"&subject="+$("#subjectId").val();
			diag.Width = 950;
			diag.Height = 540;
			diag.ShowButtonRow = false;
			diag.show();
		}
		
		
		//打印
		function findQResultExamineeForModify(beginRoom, endRoom){
			//打开窗口
			var diag = new top.Dialog();
			diag.Title = "核查结果上报";
			diag.URL = "../resultExaminee/findQResultExamineeForModify.action?beginRoom="+beginRoom+"&endRoom="+endRoom+"&examPlaceVo.examPlace="+$("#examPlaceVo_examPlace").val()+"&subject="+$("#subjectId").val();
			diag.Width = 1000;
			diag.Height = 600;
			diag.ShowButtonRow = false;
			diag.show();
		}
	</script>
</body>
</html>
