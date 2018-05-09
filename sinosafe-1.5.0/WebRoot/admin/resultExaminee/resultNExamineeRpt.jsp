<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8" />
<title></title>
<style type="text/css">

/*布局*/
body {
	background: #ffffff;
}

#box {
	margin: 0 auto;
	width: 650px;
	padding: 10px 0;
	position: relative;
	font-size: 11.0pt;
}

.print_submit {
	font-size: 14pt;
	text-align: center;
}

.print_submit button {
	font-size: 14px;
	padding: 5px;
	margin: 20px;
	text-align: center;
}

@media print {
	.noprint {
		display: none;
	}
}

.box_table {
	width: 100%;
	border: 0;
	border-collapse: collapse;
}

.box_table td {
	padding: 15px 5px;
	border: 1px dashed #ccc;
}

.info_box {
	width: 100%;
	border: 0;
	border-collapse: collapse;
}

.info_box td {
	border: 1px solid #333;
	text-align: center;
	padding: 5px;
}

.title_table {
	width: 100%;
	border: 0;
	border-collapse: collapse;
}

.title_table td {
	border: 0;
	padding: 0;
	font-weight: bold;
	font-size: 12pt;
}

.info_div {
	float: left;
	margin-right: 5px;
	margin-bottom: 5px;
	margin-top: 5px;
	width: 310px;
}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="box">
		<div class="print_submit" class="noprint">
			<span class="noprint">第<s:property value="beginRoom" />&nbsp;至&nbsp;<s:property
								value="endRoom" />考场</span>
			<button id="print_btn" class="noprint"
				onclick="javascript:window.print();">批量打印</button>
		</div>
		<div
			style="font-size: 15pt;text-align: center;padding: 5px;font-weight: bold;border-bottom:0px double #ccc;padding-bottom: 5px;">
			人脸识别信息核查表 <s:if
							test="resultExamineeVoList.size > 0">
							《<s:if test="resultExamineeVoList[0].subject==0">
								测试卷
							</s:if>
							<s:elseif test="resultExamineeVoList[0].subject==1">
								第1场
							</s:elseif>
							<s:elseif test="resultExamineeVoList[0].subject==2">
								第2场
							</s:elseif>
							<s:elseif test="resultExamineeVoList[0].subject==3">
								第3场
							</s:elseif>
							<s:elseif test="resultExamineeVoList[0].subject==4">
								第4场
							</s:elseif>
							<s:elseif test="resultExamineeVoList[0].subject==5">
								第5场
							</s:elseif>
							<s:elseif test="resultExamineeVoList[0].subject==6">
								第6场
							</s:elseif>
							》
						</s:if></div>
		<div style="padding: 3px;">
			<table class="title_table">
				<tr>
					<td style="text-align: left;">
						<s:if test="null!=resultExamineeVoList && resultExamineeVoList.size()>0">
							<s:property value="resultExamineeVoList[0].examPlaceName" />
						</s:if>
					</td>
					<td style="text-align: left;" width="25%"><span>核查人：</span></td>
				</tr>
			</table>
		</div>
		<s:iterator value="resultExamineeVoList" status="st1">
			<div class="info_div">
				<table class="info_box">
					<%-- <tr>
						<td colspan="2"><s:property value="examPlaceName" /></td>
					</tr> --%>
					<tr>
						<td colspan="2">考场：<b><s:property value="examRoom" /></b>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;座位：<b><s:property value="seatNumber" /></b>
						</td>
					</tr>
					<tr>
						<td colspan="2"><b><s:property value="examineeName" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property
									value="licence" /></b></td>
					</tr>
					<tr>
						<td><img src="<s:property value="photoPath"/> "
							style="width: 126px;height: 180px;" /></td>
						<td><img src="<s:property value="absolutePath"/>"
							style="width: 126px;" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" />核查无误<input
							type="checkbox" />无法确认<input
							type="checkbox" />违纪<input
							type="checkbox" />缺考</td>
					</tr>
				</table>
			</div>
			<s:if test="#st1.count%6==0 && !#st1.last">
				<div
					style="page-break-after:always;padding-bottom: 15px;clear: both;">
				</div>
				<div
			style="font-size: 15pt;text-align: center;padding: 5px;font-weight: bold;border-bottom:0px double #ccc;padding-bottom: 5px;">
			人像识别信息核查表 <s:if
							test="resultExamineeVoList.size > 0">
							《<s:if test="resultExamineeVoList[0].subject==0">
								测试卷
							</s:if>
							<s:elseif test="resultExamineeVoList[0].subject==1">
								科目一
							</s:elseif>
							<s:elseif test="resultExamineeVoList[0].subject==2">
								科目二
							</s:elseif>
							<s:elseif test="resultExamineeVoList[0].subject==3">
								科目三
							</s:elseif>
							<s:elseif test="resultExamineeVoList[0].subject==4">
								科目四
							</s:elseif>
							》
						</s:if></div>
		<div style="padding: 3px;">
			<table class="title_table">
				<tr>
					<td style="text-align: left;">
						<s:if test="null!=resultExamineeVoList && resultExamineeVoList.size()>0">
							<s:property value="resultExamineeVoList[0].examPlaceName" />
						</s:if>
					</td>
					<td style="text-align: left;" width="25%"><span>核查人：</span></td>
				</tr>
			</table>
		</div>
			</s:if>
		</s:iterator>
	</div>
</body>
</html>
