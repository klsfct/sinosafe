<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
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

<!--图片预览start-->
<script type="text/javascript" src="../libs/js/pic/imgPreview.js"></script>
<!--图片预览end-->
</head>
<body>
	<s:form id="paramFormId" name="paramForm" method="post" enctype="multipart/form-data"
		action="modifyQResultExaminee" namespace="/admin/resultExaminee">
		<s:hidden name="subject"></s:hidden>
		<s:hidden name="examPlaceVo.examPlace"></s:hidden>
		<s:hidden name="beginRoom"></s:hidden>
		<s:hidden name="endRoom"></s:hidden>
		<s:hidden name="interval"></s:hidden>
		<s:hidden id="licenceId" name="licence"></s:hidden>
		<div class="padding_right5 padding_top5">
		<span style="color:red;font-size:18px">*如有未经过安检而进入考场的考生，可修改核查结果；实到考生使用安检通道再次核验或拍照上传。</span>
		</div>
		<%-- <div class="padding_right5 padding_top5">
		<span style="color:red;font-size:18px">2、鼠标挪至“姓名”可预览照片。</span>
		</div> --%>
		<div class="padding_right5 padding_top5">
		<span style="color:red;font-size:18px">
			《第<s:property value="subject" />场》
	<s:if test="null!=resultExamineeVoList && resultExamineeVoList.size()>0">
							《<s:property value="resultExamineeVoList[0].examPlaceName" />》
						</s:if>《
		第<s:property value="beginRoom" />&nbsp;至&nbsp;<s:property
								value="endRoom" />考场》</span>
		<button type="button" onclick="submitData();">
			<span class="icon_save">批量上报核查结果</span>
		</button>
		</div>
		<div id="scrollContent" class="padding_right5 padding_top5">
			<table class="tableStyle" mode="list">
				<tr>
					<!-- <th>当前考试</th> -->
					<th>考区</th>
					<th>考场号</th>
					<th>座位号</th>
					<th>姓名</th>
					<th>准考证号</th>
					<th>身份证号</th>
					<th>处理结果</th>
					<th>上报状态</th>
					<th>上报时间</th>
					<th>上传照片</th>
					<th>照片状态</th>
				</tr>
				<s:iterator value="resultExamineeVoList" status="st1">
					<tr>
						<td><s:property
								value="examAreaName" /></td>
						<td><s:property value="examRoom" /></td>
						<td><s:property value="seatNumber" /></td>
						<td><a href="javascript:showExamineeInofo('<s:property value="licence" />');" 
					     ref="<s:property value="photoPath" />"
					     ref2="<s:property value="absolutePath" />"
					     class=" imgPreview"><span class="icon_img"><s:property value="examineeName" /></span></a></td>
						<td><s:property value="licence" /></td>
						<td><s:property value="identity" /></td>
						<td><s:select
								name="resultExamineeVoList[%{#st1.index}].verifyResult"
								list="#{'缺考':'缺考','实到(无违纪)':'实到(无违纪)','无法确认':'无法确认','违纪':'违纪'}"></s:select> <s:hidden
								name="resultExamineeVoList[%{#st1.index}].id" value="%{id}"></s:hidden>
								<s:hidden
								name="resultExamineeVoList[%{#st1.index}].licence" value="%{licence}"></s:hidden>
						</td>
						<td>
						<s:if test="isVerify==true">
								<span class="icon_ok" style="color:green">已上报</span>
							</s:if><s:else>
								未上报
							</s:else>
							</td>
						<td><s:if test="null==verifyDate"></s:if><s:date name="verifyDate" format="yyyy-MM-dd HH:mm" /></td>
						
						<td><s:file name="resultExamineeVoList[%{#st1.index}].upload"  />
						</td>
						<td><s:if test="null==absolutePath">
								--
							</s:if>
							<s:else>
								<span style="color:green">已上传</span>
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</s:form>
	<div id="mydiv1" style="width:320px;height:180px;position:absolute;display:none;">
		<table>
			<tr>
				<td>
					<img id="photo1Id" src="" style="width:132px;" />
				</td>
				<td>
					<img id="photo2Id" src="" style="width:132px;" />
				</td>
			</tr>
		</table>
	</div> 
	<script type="text/javascript">
		function submitData() {
			if (confirm("确认填写信息无误，提交上报缺考人员处理信息？")) {
				$("#paramFormId").submit();
			} else {
				return false;
			}

		}
		function view(licence){
			$("#licenceId").val(licence);
			$("#paramFormId").attr("action","findResultExaminee.action");
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
	</script>
</body>
</html>
