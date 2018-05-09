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
<link rel="stylesheet" type="text/css" id="skin" prePath="../"
	scrollerY="false" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<!--框架必需end-->
<script>
	var fixObjHeight = 0;
	function customHeightSet(contentHeight) {
		$("#scrollContent").height(contentHeight - fixObjHeight);
	}
</script>
</head>
<body>
	<div id="scrollContent">
	<div class="padding_right5 padding_top5">
		<table class="tableStyle" formMode="view">
			<tr>
				<td width="200">姓名：</td>
				<td width="250"><span style="font-weight:bold;font-size:16px"><s:property
							value="resultExamineeVoList[0].examineeName" /></span></td>
				<td width="60">性别：</td>
				<td width="100"><s:property value="resultExamineeVoList[0].sex" /></td>
				<td rowspan="6"><div style="text-align: left;">
						<img
							src="<s:property value="resultExamineeVoList[0].photoPath"/>"
							style="width:126px" />
					</div></td>
			</tr>
			<tr>
				<td>证件号码：</td>
				<td colspan="3"><s:property
						value="resultExamineeVoList[0].identity" /></td>
			</tr>
			<tr>
				<td>考区名称：</td>
				<td colspan="3"><s:property
						value="resultExamineeVoList[0].examProvinceName" /> <s:property
						value="resultExamineeVoList[0].examAreaName" /></td>
			</tr>
			<tr>
				<td>考点名称：</td>
				<td colspan="3"><s:property
						value="resultExamineeVoList[0].examPlaceName" /></td>
			</tr>
			<tr>
				<td>准考证号：</td>
				<td colspan="3"><span style="font-weight:bold;font-size:16px"><s:property
							value="resultExamineeVoList[0].licence" /></span></td>
			</tr>
			<tr>
				<td>考场号：</td>
				<td><span style="font-weight:bold;font-size:16px"><s:property
							value="resultExamineeVoList[0].examRoom" /></span></td>
				<td>座位号：</td>
				<td><span style="font-weight:bold;font-size:16px"><s:property
							value="resultExamineeVoList[0].seatNumber" /></span></td>
			</tr>
		</table>
	</div>
	<div class="padding_right5 padding_top5">
		<s:iterator value="resultExamineeVoList" status="st1">
			<div style="float: left;width: 220px;padding-right: 5px;" >
				<table class="tableStyle" formMode="list">
					<tr>
						<th colspan="2" style="font-size: 16px;font-weight: bold;">
							<s:if test="resultExamineeVoList[#st1.index].subject==0">
								测试卷
							</s:if> <s:elseif test="resultExamineeVoList[#st1.index].subject==1">
								第一场
							</s:elseif> <s:elseif test="resultExamineeVoList[#st1.index].subject==2">
								第二场
							</s:elseif> <s:elseif test="resultExamineeVoList[#st1.index].subject==3">
								第三场
							</s:elseif> 
							<s:elseif test="resultExamineeVoList[#st1.index].subject==4">
								第四场
							</s:elseif> 
							<s:elseif test="resultExamineeVoList[#st1.index].subject==5">
								第五场
							</s:elseif> 
							<s:elseif test="resultExamineeVoList[#st1.index].subject==6">
								第六场
							</s:elseif> 
						</th>
					</tr>
					<tr>
						<td colspan="2" style="vertical-align:middle;text-align: center" width="130">
							<img
									src="<s:property value="resultExamineeVoList[#st1.index].absolutePath"/>"
									style="width:126px" />
						</td>
					</tr>
					<tr>
						<th width="100">
							识别结果
						</th>
						<td class="ali02">
							<s:if test="resultExamineeVoList[#st1.index].isPass">
									<span style="color:green">已通过</span>
								</s:if> 
								 <s:else>
									<span style="color:red">未通过</span>
								</s:else>
						</td>
					</tr>
					<tr>
						<th>
							安检时间
						</th>
						<td class="ali02">
							<s:date
									name="resultExamineeVoList[#st1.index].createDate"
									format="yyyy-MM-dd HH:mm" />
						</td>
					</tr>
					<%-- <tr>
						<th>
							处理结果
						</th>
						<td class="ali02">
							<s:property
							value="resultExamineeVoList[#st1.index].verifyResult" />
						</td>
					</tr>
					<tr>
						<th>
							处理时间
						</th>
						<td class="ali02">
							<s:date
										name="resultExamineeVoList[#st1.index].verifyDate"
										format="yyyy-MM-dd HH:mm"  />
						</td>
					</tr> --%>
				</table>
			</div>
		</s:iterator>
		<div style="clear: both">
		</div>
	</div>
	</div>
</body>
</html>