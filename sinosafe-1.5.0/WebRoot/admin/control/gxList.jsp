<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String baseIP = request.getScheme() + "://"
			+ request.getServerName();
	request.setAttribute("basePath", basePath);
	request.setAttribute("baseIP", baseIP);
%>
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
<!--基本选项卡start-->
<script type="text/javascript"
	src="../libs/js/nav/basicTab.js"></script>
<!--基本选项卡end-->
</head>
<body>
	<div class="padding5">
		<div class="basicTab">
			<div name="组织机构">
				<table class="tableStyle" formMode="view">
					<tr>
						<td width="20%">考点名称：</td>
						<td>广西建设职业技术学院罗文校区</td>
					</tr>
					<tr>
						<td>组织机构</td>
						<td>
							<img src="./img/2.jpeg" style="width: 80%;"/>
						</td>
					</tr>
				</table>
			</div>
			<div name="考务人员名单">
				<table class="tableStyle" formMode="view">
					<tr>
						<td width="20%">考点名称：</td>
						<td>广西建设职业技术学院罗文校区</td>
					</tr>
					<tr>
						<td>人员名单</td>
						<td>
							<img src="./img/1.png" style="width: 80%;"/>
						</td>
					</tr>
				</table>
			</div>
			<div name="工作准备情况">
				<table class="tableStyle" formMode="view">
					<tr>
						<td width="20%">考点名称：</td>
						<td>广西建设职业技术学院罗文校区</td>
					</tr>
					<tr>
						<td>安检通道1</td>
						<td>
							<img src="./img/3.jpeg" style="width: 80%;"/>
						</td>
					</tr>
					<tr>
						<td>安检通道2</td>
						<td>
							<img src="./img/6.jpeg" style="width: 80%;"/>
						</td>
					</tr>
					<tr>
						<td>机房1</td>
						<td>
							<img src="./img/4.jpeg" style="width: 80%;"/>
						</td>
					</tr>
					<tr>
						<td>机房2</td>
						<td>
							<img src="./img/5.jpeg" style="width: 80%;"/>
						</td>
					</tr>
					<tr>
						<td>机房3</td>
						<td>
							<img src="./img/11.jpeg" style="width: 80%;"/>
						</td>
					</tr>
					<tr>
						<td>机房4</td>
						<td>
							<img src="./img/12.jpeg" style="width: 80%;"/>
						</td>
					</tr>
				</table>
			</div>
			<div name="考场现场监控">
				<table class="tableStyle" formMode="list">
					<tr>
						<th class="th" width="5%">序号</th>
						<th class="th" width="20%">考点名称</th>
						<th class="th" width="40%">考场名称</th>
						<th class="th">操作</th>
					</tr>
					<tr>
						<td>1</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1901</td>
						<td><a href="javascript:openVideo();"><span class="icon_view">查看视频</span></a></td>
					</tr>
					<tr>
						<td>2</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1902</td>
						<td><a href="javascript:openVideo();"><span class="icon_view">查看视频</span></a></td>
					</tr>
					<tr>
						<td>3</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1903</td>
						<td><a href="javascript:openVideo();"><span class="icon_view">查看视频</span></a></td>
					</tr>
				</table>
			</div>
			<div name="人脸识别数据监控">
				<table class="tableStyle" formMode="list">
					<tr>
						<th class="th" width="5%">序号</th>
						<th class="th" width="15%">考点名称</th>
						<th class="th" width="15%">考场名称</th>
						<th class="th" width="6%">姓名</th>
						<th class="th" width="10%">准考证号</th>
						<th class="th" width="5%">座位号</th>
						<th class="th" width="10%">科目</th>
						<th class="th" width="10%">比对结果</th>
						<th class="th">比对时间</th>
					</tr>
					<tr>
						<td>1</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1901</td>
						<td>李洁</td>
						<td>13111721202183</td>
						<td>15</td>
						<td>第一场</td>
						<td><span class="icon_ok">通过</span></td>
						<td>2017-11-01 08:12</td>
					</tr>
					<tr>
						<td>2</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1901</td>
						<td>李洁</td>
						<td>13111721202183</td>
						<td>15</td>
						<td>第一场</td>
						<td><span class="icon_ok">通过</span></td>
						<td>2017-11-01 08:12</td>
					</tr>
					<tr>
						<td>3</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1901</td>
						<td>李洁</td>
						<td>13111721202183</td>
						<td>15</td>
						<td>第一场</td>
						<td><span class="icon_ok">通过</span></td>
						<td>2017-11-01 08:12</td>
					</tr>
					<tr>
						<td>4</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1901</td>
						<td>李洁</td>
						<td>13111721202183</td>
						<td>15</td>
						<td>第一场</td>
						<td><span class="icon_ok">通过</span></td>
						<td>2017-11-01 08:12</td>
					</tr>
					<tr>
						<td>5</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1901</td>
						<td>李洁</td>
						<td>13111721202183</td>
						<td>15</td>
						<td>第一场</td>
						<td><span class="icon_ok">通过</span></td>
						<td>2017-11-01 08:12</td>
					</tr>
				</table>
			</div>
			<div name="试卷数据下发监控">
				<table class="tableStyle" formMode="list">
					<tr>
						<th class="th" width="5%">序号</th>
						<th class="th" width="15%">考点名称</th>
						<th class="th" width="15%">考场名称</th>
						<th class="th" width="10%">主数据包</th>
						<th class="th" width="10%">切片</th>
						<th class="th" width="10%">秘钥</th>
						<th class="th" width="10%">监考员</th>
						<th class="th">联系方式</th>
					</tr>
					<tr>
						<td>1</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1901</td>
						<td><span class="img_ok"></span></td>
						<td><span class="img_delete"></span></td>
						<td><span class="img_delete"></span></td>
						<td>白晨星</td>
						<td>13522233431</td>
					</tr>
					<tr>
						<td>2</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1902</td>
						<td><span class="img_ok"></span></td>
						<td><span class="img_delete"></span></td>
						<td><span class="img_delete"></span></td>
						<td>白晨星</td>
						<td>13522233431</td>
					</tr>
					<tr>
						<td>3</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1903</td>
						<td><span class="img_ok"></span></td>
						<td><span class="img_delete"></span></td>
						<td><span class="img_delete"></span></td>
						<td>白晨星</td>
						<td>13522233431</td>
					</tr>
					<tr>
						<td>4</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1904</td>
						<td><span class="img_ok"></span></td>
						<td><span class="img_delete"></span></td>
						<td><span class="img_delete"></span></td>
						<td>白晨星</td>
						<td>13522233431</td>
					</tr>
					<tr>
						<td>5</td>
						<td>广西建设职业技术学院罗文校区</td>
						<td>实训南楼19楼1905</td>
						<td><span class="img_ok"></span></td>
						<td><span class="img_delete"></span></td>
						<td><span class="img_delete"></span></td>
						<td>白晨星</td>
						<td>13522233431</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var openVideo = function(){
			ys7.ExecMe("${basePath}/public/video/gotoVideo2.action");
		}
	</script>
</body>
</html>
