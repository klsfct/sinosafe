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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="../libs/js/jquery.js"></script>
<script type="text/javascript" src="../libs/js/framework.js"></script>
<link href="../libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="../"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->

<!--修正IE6不支持PNG图start-->
<style>
img {
	behavior:url("../libs/js/method/pngFix/pngbehavior.htc");
}
</style>
<!--修正IE6不支持PNG图end-->

<!--动画方式入场效果start-->
<script type="text/javascript" src="../libs/js/pic/jomino.js"></script>
<script>
	 $(function(){
		$(".navIcon").jomino();
	});
	function customHeightSet(contentHeight){
		$("#scrollContent").height(contentHeight);
	}
</script>
<!--动画方式入场效果end-->
</head>
<body>
<div id="scrollContent">
	<table width="100%" height="90%">
		<tr><td class="ali02 ver02">
			<div  style="width:800px;margin:0 auto;">
			<table class="ali01 ver01">
				<tr height="146">
					<td width="260">
						<div class="navIcon" onclick="student();" style="display:none;">
							<div class="navIcon_left"><img src="../libs/icons/png/71.png"/></div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">考生身份识别监控</div>
								<div class="navIcon_right_con">
									监控考生身份识别统计数据
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</td>
					<td width="260">
						<div class="navIcon" onclick="paperTransport();" style="display:none;">
							<div class="navIcon_left"><img src="../libs/icons/png/04.png"/></div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">试卷运输定位监控</div>
								<div class="navIcon_right_con">
									监控试卷运输定位数据
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</td>
					<td width="260">
						<div class="navIcon" onclick="answer();" style="display:none;">
							<div class="navIcon_left"><img src="../libs/icons/png/34.png"/></div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">答题卡回收监控</div>
								<div class="navIcon_right_con">
									监控答题卡回收情况数据
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</td>
				</tr>
				<tr height="146">
					<td>
						<div class="navIcon" onclick="video2('A');" style="display:none;">
							<div class="navIcon_left"><img src="../libs/icons/png/53.png"/></div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">考点安检视频监控</div>
								<div class="navIcon_right_con">
									监控考生进场视频
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</td>
					<td>
						<div class="navIcon" onclick="video2('C');" style="display:none;">
							<div class="navIcon_left"><img src="../libs/icons/png/68.png"/></div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">试卷安全视频监控</div>
								<div class="navIcon_right_con">
									监控试卷运输视频
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</td>
					<td>
						<div class="navIcon" onclick="video('B');" style="display:none;">
							<div class="navIcon_left"><img src="../libs/icons/png/69.png"/></div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">试卷保密室视频监控</div>
								<div class="navIcon_right_con">
									监控试卷保密室视频
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</td>
				</tr>
				<tr height="146">
					<td>
						<div class="navIcon" onclick="openSigninSystem();" style="display:none;">
							<div class="navIcon_left"><img src="../libs/icons/png/50.png"/></div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">工作人员定位监控</div>
								<div class="navIcon_right_con">
									监控工作人员位置
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</td>
					<td>
					</td>
					<td>
					</td>
				</tr>
			</table>
			</div>
		</td></tr>
	</table>
</div>
<script type="text/javascript">

//1.考生身份识别监控
function student(){
	window.open("${basePath}/public/control/gotoMonitorByCity.action","win1","width=0,height=0");
	/* ys7.ExecMe("${basePath}/public/control/studentMap.jsp"); */
}
//2.试卷运输定位监控
function paperTransport(){
	//window.open("${baseIP}:7890/manager.html","win2","width=0,height=0")
	//ys7.ExecMe("${baseIP}:7890/manager.html");
	alert("功能暂未开通！");
}
//3.答题卡回收监控
function answer(){
	//window.open("${basePath}/public/control/answerMap.jsp","win3","width=0,height=0")
	//ys7.ExecMe("${basePath}/public/control/answerMap.jsp");
	alert("功能暂未开通！");
}

//4.考点视频监控
function video(type){
	//alert("${basePath}/public/video/gotoVideo.action");
	//ys7.ExecMe("${basePath}/public/video/gotoVideo.action");
	alert("功能暂未开通！");
}
function video2(type){
	//window.open("${basePath}/public/video/gotoVideo2.action");
	//ys7.ExecMe("${basePath}/public/video/gotoVideo2.action");
	alert("功能暂未开通！");
}
// 工作人员定位监控
function openSigninSystem(){
	//window.open("${basePath}/public/examPlaceInfo/baiduMap.jsp","win8","width=0,height=0");
	//ys7.ExecMe("${basePath}/public/examPlaceInfo/baiduMap.jsp");
	alert("功能暂未开通！");
}
</script>

</body>
</html>