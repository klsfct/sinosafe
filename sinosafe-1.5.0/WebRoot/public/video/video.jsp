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
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>远程监控</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link rel="stylesheet" href="css/common.css" type="text/css">
<link rel="stylesheet" href="css/buttons.css" type="text/css">
<link rel="stylesheet" href="js/zTree_v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet" href="js/scrollbar/jquery.mCustomScrollbar.css"
	type="text/css">
<script type="text/javascript" src="js/zTree_v3/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="js/zTree_v3/js/jquery.ztree.excheck.js"></script>
<script src="js/scrollbar/jquery.mCustomScrollbar.js"></script>
</head>
<body>
<!-- <div style="position: absolute;bottom: 0;left: 0;color: red;width: 100%;height: 50px;z-index: 9999;overflow: scroll; display:block" id="test1"></div> -->
	<s:hidden name="deviceVideoInfoVo.deviceType" id="deviceType"></s:hidden>
	<div class="top">
		<div class="top-top-logo">
			<img src="./images/top-logo.png" />
		</div>
		<div class="top-title">
			<div class="deviceName" id="deviceName">实时视频监控直播</div>
		</div>
	</div>
	<!-- 内容start -->
	<div class="content" id="content">
		<!-- 右面start -->
		<div class="conRight" id="conRight">
			<div class="conRight-box">
				<div class="conRight-border">
					<div class="rightTitle">
						监控状态：[ <span class="videoOnline"></span> / <span class="videoAll"></span>]
					</div>
					<div class="conRight-con">
						<div id="treeDemo" class="ztree scrollbar-inner"></div>
					</div>
					<div class="operate" id="operate" > 
				  	  <div class="playCenter"></div>  
				      <a title="上" class="goUp"></a>
				      <a title="左" class="goLeft"></a>
				      <a title="右" class="goRight"></a>
				      <a title="下" class="goDown"></a>
				    </div>
				</div>
			</div>
		</div>
		<!-- 右面end -->
		<!-- 左面start -->
		<div class="conLeft" id="conLeft">
			<div class="page1">
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe1" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="1" lign="CENTER"
								class="leftBoxNoSelect" width="100%" height="100%" name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe2" class="videoNameFrame" src="font.html"></iframe>
							<EMBED TYPE="application/x-window-plugin" box="2" lign="CENTER"
								class="leftBoxNoSelect" width="100%" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe3" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="3" lign="CENTER"
								class="leftBoxNoSelect" width="100%" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe4" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="4" lign="CENTER"
								class="leftBoxNoSelect" width="100%" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe5" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="5" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe6" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="6" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe7" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="7" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe8" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="8" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe9" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="9" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe10" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="10" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe11" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="11" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe12" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="12" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe13" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="13" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe14" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="14" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe15" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="15" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe16" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="16" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe17" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="17" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe18" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="18" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe19" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="19" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe20" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="20" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe21" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="21" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe22" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="22" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe23" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="23" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe24" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="24" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe25" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="25" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe26" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="26" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe27" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="27" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe28" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="28" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe29" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="29" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe30" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="30" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe31" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="31" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe32" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="32" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe33" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="33" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe34" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="34" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe35" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="35" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe36" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="36" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe37" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="37" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe38" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="38" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe39" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="39" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe40" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="40" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe41" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="41" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe42" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="42" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe43" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="43" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe44" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="44" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe45" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="45" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe46" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="46" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe47" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="47" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe48" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="48" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
				<div class="leftBox">
					<div class="small-box">
						<div class="small-con">
							<iframe id="iframe49" class="videoNameFrame" src="font.html" ></iframe>
							<EMBED TYPE="application/x-window-plugin" box="49" lign="CENTER"
								width="100%" class="leftBoxNoSelect" height="100%"
								name="flipper" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 左面end -->
	</div>
	<!-- 内容end -->
	<!-- 底部版权 -->
	<div class="footer">
		<div class="footerButtonLeft">
			<a href="javascript:void(0)" id="play_btn" class="button-s play" >播放</a>
			<a href="historyVideo.jsp" class="button-s">历史录像</a>
			<a href="javascript:void(0)" id="console" class="button-s">云台</a>
			<a href="javascript:void(0)" id="voicePlay" class="button-s">声音</a>
			<!-- <a href="javascript:void(0)" id="voiceTalk" class="button-s">对讲</a>
			<a href="javascript:void(0)" id="msg_btn" class="button-s">指令</a> -->
			<a href="javascript:void(0)" id="reflush" class="button-s">刷新</a>
			<a href="javascript:void(0)" id="close" class="button-s">关闭</a>
		</div>
		<div class="footerButtonRight">
			<a href="javascript:selectShowNo(1)">1</a>
			<a href="javascript:selectShowNo(4)" class="buttonSelect">4</a> 
			<a href="javascript:selectShowNo(6)">6</a> 
			<a href="javascript:selectShowNo(9)">9</a> 
			<a href="javascript:selectShowNo(16)">16</a>
			<a href="javascript:selectShowNo(25)">25</a>
			<a href="javascript:selectShowNo(36)">36</a>
			<a href="javascript:void(0)" id="fullScreen" class="fullScreen">全</a>
		</div>
		<div class="clear"></div>
	</div>
	<script type="text/javascript">
		//离线人数
		var offOnLine = "";
		//改变显示次数
		var iconCount = 0;
		//初始化视频第一次在线数和总数
		var onlineNumFirst = [];
		var onlineNum = 0;
		var allNum = 0;
		//1.appId加载萤石运行库，软件开始时调用一次就可以   ys7.Init(szAppId: string)
		//2.设置Token，如果不改变AccessToken只需要设置一次
		var appId = "";
		var token = "";

		//存入选中节点的id和name总共的
		var checkedIdArray = [];
		var checkedTextArray = [];

		var videoArray = [];
		var videoWidthArray = [];
		var videoHeightArray = [];
		
		var treeObj;
		//初始化播放视频

		//页面播放画面数
		var playPage = 4;
		//视频状态
		var isViewPlayStart = false;
		//第一次请求树结构返回的数据
		var url_tree_list = "getDeviceVideoJson.action";
		var url_initToken = "getDeviceVideoTocken.action";
		//语音对讲   1不能能对讲  0可以语音对讲前提能能听到对方声音
		var openSound = 1;
		//存放上次选中的session
		var boforeSelectSessitionArr = [];
		//重新加载数据要有盒子号和设备id
		var reloadData ={};
		$(function() {
			initWindow();
			initSession();
			initTree();
			
			//窗口变化视频大小改变
			$(window).resize(function() {
				initWindow();
			});
			
			//初始化树结构1秒1请求
		    setInterval(updateNode, 10000);
		  	//播放按钮
		    $("#play_btn").click(function(){
		    	if($(this).attr("disabled") == "disabled"){
		    		return false;
		    	}
		    	$(this).attr("disabled","disabled");
		    	setTimeout(function(){
		    		 $("#play_btn").removeAttr("disabled");
	    		},3000);
		    	if(!$(this).hasClass("button-s-select")){
		    		//播放
		    		$(this).addClass("button-s-select").html("停止");
		    		$(this).removeClass("play").addClass("star");
		    		
		    		videoPlay();
		        	//重新播放 去掉 控制台 语音对讲 按钮去掉蓝色 及关闭控制台
		        	changgeConsoleViocePlayTalk();
		    	}else{
		    		//按钮暂停
		    		$(this).removeClass("button-s-select").html("播放");
		    		$(this).addClass("play").removeClass("star");
		    		videoStop();
		        	//停止播放 去掉 控制台 语音对讲 按钮去掉蓝色 及关闭控制台
		        	changgeConsoleViocePlayTalk();
		    	}	
		    });
		    
		    
		    //点击进入全屏
		    $("#fullScreen").click(function(){
		    	$(".top").hide();
		    	$(".footer").hide();
		    	$(".conRight").hide();
		    	$(".conLeft").css("margin-left","3px");
		    	initWindow();
		    })
		    //退出全屏
		    $(window).keydown(function (event) {
		        if (event.keyCode == 27) {
		        	$(".top").show();
		        	$(".footer").show();
		        	$(".conRight").show();
		        	//放大后屏幕宽度及高度
		            $(".conLeft").css("margin-left","300px");
		            initWindow();
		        }
		    });
		    //云控制台
		    $("#console").click(function(){
		    	if(!$(this).hasClass("button-s-select")){
		    		//播放
		    		$(this).addClass("button-s-select");
		    		$("#operate").show();
		    	}else{
		    		//按钮
		    		$(this).removeClass("button-s-select");
		    		$("#operate").hide();
		    	}	
		    })
		   //语音 开启时只开语音，关闭先关语音再关对讲
		    $("#voicePlay").click(function(){
		    	if(!$(this).hasClass("button-s-select")){
		    		//播放
		    		$(this).addClass("button-s-select");
		    		boforeSelectSessitionArr=[]; 
		    		$(".leftBox").each(function() {
		            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		            		var boxId = $(this).find("embed").attr("box");
		        			var deviceId = $(this).find("embed").attr("deviceId");
		        			var setVolume = ys7.SetVolume(boxId,100);
		        			//$("#test1").append("设置音量方法是否成功："+setVolume);
		        			openSound = ys7.OpenSound(boxId)
		            		//$("#test1").append("声音对讲开始："+startVoiceTalkEx);
		        			boforeSelectSessitionArr.push(boxId);
		            	}
		            }) 
		    	}else{
		    		//按钮
		    		$(this).removeClass("button-s-select");
		    		$(".leftBox").each(function() {
		            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		            		var boxId = $(this).find("embed").attr("box");
		        			var deviceId = $(this).find("embed").attr("deviceId");
		        			$("#voiceTalk").removeClass("button-s-select");
		        			var stopVoiceTalk = ys7.StopVoiceTalk(boxId);
		            		var CloseSound = ys7.CloseSound(boxId);
		            	}
		            }) 
		    	}	
		    })
		    
		    
		    //对讲   开启时：先开语音再开对讲 ，关闭只关闭对讲
		    $("#voiceTalk").click(function(){
		    	if(!$(this).hasClass("button-s-select")){
		    		//播放
		    		$(this).addClass("button-s-select");
		    		boforeSelectSessitionArr=[]; 
		    		$(".leftBox").each(function() {
		            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		            		var boxId = $(this).find("embed").attr("box");
		        			var deviceId = $(this).find("embed").attr("deviceId");
		        			var setVolume = ys7.SetVolume(boxId,100);
		        			openSound = ys7.OpenSound(boxId);
		        			//$("#test1").append("deviceId"+deviceId+"---boxId"+boxId);
		        			$("#voicePlay").addClass("button-s-select");
		        			//$("#test1").append("声音开始："+openSound);
		        			var startVoiceTalkEx = ys7.StartVoiceTalkEx(boxId,deviceId,1);
		        			boforeSelectSessitionArr.push(boxId);
		            		//$("#test1").append("对讲开始："+startVoiceTalkEx);
		            	}
		            });
		    	}else{
		    		$(this).removeClass("button-s-select");
		    		$(".leftBox").each(function() {
		            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		            		var boxId = $(this).find("embed").attr("box");
		        			var deviceId= $(this).find("embed").attr("deviceId");
		        			var stopVoiceTalk = ys7.StopVoiceTalk(boxId);
		        			if($("#voicePlay").hasClass("button-s-select")){
		            			var CloseSound = ys7.CloseSound(boxId);
		            			var openSound = ys7.OpenSound(boxId);
		            		}
		            	}
		            }) 
		    	}	
		    })
		      $("#msg_btn").click(function(){
		    	  $(".leftBox").each(function() {
		            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		            		var boxId = $(this).find("embed").attr("box");
		        			var deviceId= $(this).find("embed").attr("deviceId");
		        			var nodes = treeObj.getNodesByParam("id", deviceId, null);
	                		var node = nodes[0];
	                		var _linkManName = node.linkManName;
	                		var _linManPhone = node.linkManPhone;
	                		window.open("${basePath}/public/goSaveMessageOnly.action?linkManName="+_linkManName+"&linkManPhone="+_linManPhone+"","win10","top=200,left=100,width=380,height=200");
	                		
		            	}
		            }) 
		      });  
		    $("#reflush").click(function(){
		    	$(".leftBox").each(function() {
	            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
	            		var boxId = $(this).find("embed").attr("box");
	        			var deviceId= $(this).find("embed").attr("deviceId");
	        			//alert(boxId+"--"+deviceId);
					    ys7.StopRealPlayEx(boxId);
						//调用客户端方法播放视频
						setTimeout(function(){
							var i =  ys7.StartRealPlayEx(boxId, boxId, deviceId, 1);
						    //$("#test1").append("播放视频 "+i);
						    //设置清晰度szDevSerial 设备号, iChannelNo 通道号, iVideoLevel 0-2清晰度  0不清晰  2 清晰
						    var o = ys7.SetVideoLevel(deviceId,1,0);
			    		},3000);
	            	}
	            }) 
		    	
		    	
		    	/* //重新加载没有出现视频的盒子
		    	var _box = reloadData.box;
		    	var _deviceId =reloadData.deviceId;
		    	//$("#test1").append("--盒子 "+_box+"设备"+_deviceId);
		    	if(_box != undefined && _deviceId != undefined){
		    		 //绑定设备号
				    $($("embed")[_box-1]).attr("deviceId",_deviceId);
				    ys7.StopRealPlayEx(_box);
					//调用客户端方法播放视频
				    var i =  ys7.StartRealPlayEx(_box, _box, _deviceId, 1);
				    //$("#test1").append("播放视频 "+i);
				    //设置清晰度szDevSerial 设备号, iChannelNo 通道号, iVideoLevel 0-2清晰度  0不清晰  2 清晰
				    var o = ys7.SetVideoLevel(_deviceId,1,0);
		    	}	 */	    	
		    });
		    $("#close").click(function(){
		    	if(confirm("是否确认关闭监控界面？")){
		    		videoStop();
		    		ys7.CloseMe();
		    	}
		    	 
		    });
		    //云控制台向上
		    $(".goUp").mousedown(function() {
		    	$(".leftBox").each(function() {
		        	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		        		var boxId = $(this).find("embed").attr("box");
		        		var deviceId = $(this).find("embed").attr("deviceId");
		        		i = ys7.PTZCtrlEx(boxId,deviceId,1,0,0,4);
		        	}
		        }) 
		    }).mouseup(function() {
		    	$(".leftBox").each(function() {
		        	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		        		var boxId = $(this).find("embed").attr("box");
		        		var deviceId = $(this).find("embed").attr("deviceId");
		        		i = ys7.PTZCtrlEx(boxId,deviceId,1,0,1,4);
		        	}
		        }) 
		    })

		    //云控制台向左侧
		    $(".goLeft").mousedown(function() {
		    	$(".leftBox").each(function() {
		        	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		        		var boxId = $(this).find("embed").attr("box");
		        		var deviceId = $(this).find("embed").attr("deviceId");
		        		i = ys7.PTZCtrlEx(boxId,deviceId,1,2,0,4);
		        	}
		        }) 
		    }).mouseup(function() {
		    	$(".leftBox").each(function() {
		        	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		        		var boxId = $(this).find("embed").attr("box");
		        		var deviceId = $(this).find("embed").attr("deviceId");
		        		i = ys7.PTZCtrlEx(boxId,deviceId,1,2,1,4);
		        	}
		        }) 
		    })
		    //云控制台向右侧
		    $(".goRight").mousedown(function() {
		    	$(".leftBox").each(function() {
		        	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		        		var boxId = $(this).find("embed").attr("box");
		    			var deviceId = $(this).find("embed").attr("deviceId");
		    			i = ys7.PTZCtrlEx(boxId,deviceId,1,3,0,4);
		        	}
		        }) 
				
		    }).mouseup(function() {
		    	$(".leftBox").each(function() {
		        	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		        		var boxId = $(this).find("embed").attr("box");
		        		var deviceId = $(this).find("embed").attr("deviceId");
		        		i = ys7.PTZCtrlEx(boxId,deviceId,1,3,1,4);
		        	}
		        }) 
		    	
		    })
		    //云控制台向下
		    $(".goDown").mousedown(function() {
		    	$(".leftBox").each(function() {
		        	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		        		var boxId = $(this).find("embed").attr("box");
		        		var deviceId = $(this).find("embed").attr("deviceId");
		        		i = ys7.PTZCtrlEx(boxId,deviceId,1,1,0,4);
		        	}
		        }) 
		    	
		    }).mouseup(function() {
		    	$(".leftBox").each(function() {
		        	if($(this).find("embed").attr("class")== "leftBoxSelect"){
		        		var boxId = $(this).find("embed").attr("box");
		        		var deviceId = $(this).find("embed").attr("deviceId");
		        		i = ys7.PTZCtrlEx(boxId,deviceId,1,1,1,4);
		        	}
		        }) 
		    	
		    })
			
		})

		var initWindow = function() {
			//窗体宽度
			var x1 = $("#content").width();
			//右侧宽度
			var x2 = $("#conRight").width();
			if($("#conRight").is(":hidden")){
				x2=0;
			}
			//左侧宽度
			var x3 = x1 - x2;
			//左侧高度
			var y3 = x3 * 9 / 16 + 8;
			$("#conLeft").height(y3);
			$("#conRight").height(y3);
			//右侧树内部窗口高度,4为上下边距
			var y7 = y3 - 50;
			$(".conRight-con").height(y7)//.mCustomScrollbar();
			
			var _selectNumber = $(".footerButtonRight .buttonSelect").html();
	    	initSmallWin(_selectNumber);
		}
		

		var initSmallWin = function(count) {
			playPage = count;
			//窗体宽度
			var x1 = $("#content").width();
			//右侧宽度
			var x2 = $("#conRight").width();
			if($("#conRight").is(":hidden")){
				x2=0;
			}
			//左侧宽度
			var x3 = x1 - x2;
			//计算小窗口高度
			var x4 = 0;
			//小窗宽度，减1取整误差
			if(count==1){
				x4 = x3 - 5;
			}else if(count==4){
				x4 = x3 / 2 - 2;
			}else if(count==9){
				x4 = x3 / 3 - 2;
			}else if(count==16){
				x4 = x3 / 4 - 2;
			}else if(count==25){
				x4 = x3 / 5 - 2;
			}else if(count==36){
				x4 = x3 / 6 - 2;
			}else if(count==49){
				x4 = x3 / 7 - 2;
			}else if(count==6){
				x4 = x3 / 3 - 2;
			}
			//小窗视频宽度，减4为左右边框
			var x5 = x4 - 4;
			//小窗视频高度
			var y5 = x5 * 9 / 16;
			
			$(".leftBox").each(function(i){
				if(i<count){
					var _tempObj = $(this).find(".small-con");
					if(count==6){
						if(i==0){
							_tempObj.width(x5*2+4);
							_tempObj.height(y5*2+4);
						}else{
							_tempObj.width(x5);
							_tempObj.height(y5);
						}
					}else{
						_tempObj.width(x5);
						_tempObj.height(y5);
					}
					$(this).show();
				}else{
					$(this).hide();
					//$(this).css("width","1px").css("height","1px");
				}
			});
			
		}

		var initSession = function() {
			//请求appurl
			$.ajax({
				type : "POST",
				url : url_initToken,
				async : true,
				dataType : "json",
				success : function(data) {
					if (data != null) {
						if (data.dataStatus == "1") {
							appId = data.dataMain.appkey;
							token = data.dataMain.accessToken;
							var app = ys7.Init(appId);
							var app1 = ys7.SetAccessToken(token);
							var _index = 0;
							/* $(".leftBox").each(function(i){
								s = ys7.AllocSessionEx();
								//$("#test1").append("生成session"+s+"；");
								sessitionArr[_index++]=s;
								$(this).find("EMBED").attr("session",s);
								
							 });*/
						} else {
							if (data.errorMsg != null
									&& data.errorMsg.length > 0) {
								//alert(data.errorMsg);
							}
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					//alert("系统异常[" + textStatus + "]:" + errorThrown);
				}
			});

		}

		var initTree = function() {
			//树结构数据加载
			var setting = {
				view : {
					showLine : false
				},
				check : {
					enable : true
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			/*,callback: {
			    	    		onClick: zTreeOnClick
			    	    	}*/
			};
			//请求树结构
			var _deviceType = $("#deviceType").val();
			$.ajax({
				type : "POST",
				url : url_tree_list,
				data : {
					"deviceVideoInfoVo.deviceType" : _deviceType
				},
				cache : true,
				dataType : "json",
				success : function(data) {
					if (data != null) {
						if (data.dataStatus == "1") {
							zNodes = data.dataMain;
							treeObj = $.fn.zTree.init($("#treeDemo"), setting,
									zNodes);
							//计算每层的监控个数及在线个数
							count(zNodes);
							/* $(".node_name").each(function(){
								var nodeName = $(this).parent().find("span:first").css("background");
								if(nodeName.indexOf("blue.png") != -1){
									$(this).css("color","#fff");
								}
							}) */
						} else {
							if (data.errorMsg != null
									&& data.errorMsg.length > 0) {
								//alert(data.errorMsg);
							}
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					//alert("系统异常[" + textStatus + "]:" + errorThrown);
				}
			});
		}

		//具体摄像头改变后的状态
		var updateNode = function() {
			var _deviceType = $("#deviceType").val();
		    //请求树结构
		    $.ajax({
				type : "POST",
				url : url_tree_list,
				data : {
					"deviceVideoInfoVo.deviceType" : _deviceType
				},
				cache : true,
				dataType : "json",
				success : function(data) {
					if (data != null) {
						if (data.dataStatus == "1") {
							//ztree数据
							var zNodes = data.dataMain;
							//计算每层的监控个数及在线个数
					        count(zNodes);
							//nodes是获取原来的数据有三层在更新
					        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					        for(var i=0;i<zNodes.length;i++){
					        	var _tempzNode = zNodes[i];
					        	var nodes = treeObj.getNodesByParam("id", _tempzNode.id, null);
					        	var node = nodes[0];
					        	node.icon = _tempzNode.icon;
	      	              	 	zTree.updateNode(node);
					        	/* //播放状态不更新
					        	if(node.icon == "./images/icon/spot.png"){
		      	              		
		      	              	}else{
		      	              		node = _tempzNode;
		      	              	 	zTree.updateNode(node);
		      	              	} */
					        }
						} else {
							if (data.errorMsg != null
									&& data.errorMsg.length > 0) {
								//alert(data.errorMsg);
							}
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					//alert("系统异常[" + textStatus + "]:" + errorThrown);
				}
			});
		}
		
		//计算在线数字
		var count = function(treeNodes) {
			var _onlineNum = 0;
			var _allNum = 0;
			//计算每层的监控个数及在线个数
			if (treeNodes.length > 1) {
				for (k = 0; k < treeNodes.length; k++) {
					if (treeNodes[k].pId != "0") {
						_allNum++;
						if (treeNodes[k].isOnline == "1") {
							_onlineNum++;
						}
					}
				}
			}

			allNum = _allNum;
			onlineNum = _onlineNum;
			$(".videoOnline").html(onlineNum);
			$(".videoAll").html(allNum);
		}
		//切换页面
		var selectShowNo = function(number){
			$(".footerButtonRight a").each(function() {
	             $(this).removeClass("buttonSelect");
	             if($(this).html()==number){
	            	 $(this).addClass("buttonSelect");
	             }
	        }) 
			initSmallWin(number);
			
			changgeConsoleViocePlayTalk();
		}
		//参数 盒子号：boxNum,当前页面数videoNum
		var setFontSize = function(videoNum){	
			for(k=0;k<videoNum;k++){
				var boxNum = k+1;
		        var selectNum = parseInt($(".buttonSelect").html());
				$("#iframe"+boxNum).contents().find("body").find("#font").attr("class","fontSize"+selectNum);
			  	var fontSize= parseInt($("#iframe"+boxNum).contents().find("body").find("#font").css("font-size").replace("px",""));
			  	var fontSize8 = fontSize*8;
			  	$("#iframe"+boxNum).css("width",fontSize8+"px");
			  	$("#iframe"+boxNum).css("height",fontSize+"px");
			  	$("#iframe"+boxNum).contents().find("body").find("#font").width(fontSize8);
			  	var str = checkedTextArray[k].substr(4, 8);
			 	//设置视频的名字
			 	$("#iframe"+boxNum).contents().find("body").find("#font").html(str);
			 	$("#iframe"+boxNum).css("display","block");
			 	//设置视频的名字结束
			}
			
		}
		//切换选中盒子 切换页面  重新播放  去掉 控制台 语音对讲 按钮去掉蓝色 及关闭控制台
		var changgeConsoleViocePlayTalk= function(){
			if($("#voicePlay").hasClass("button-s-select")){
				$("#voicePlay").removeClass("button-s-select");
				var CloseSound = ys7.CloseSound(boforeSelectSessitionArr[0]);
			}
			//切换页面   对讲按钮去掉选中蓝色 关闭对讲
			if($("#voiceTalk").hasClass("button-s-select")){
				$("#voiceTalk").removeClass("button-s-select");
				var stopVoiceTalk = ys7.StopVoiceTalk(boforeSelectSessitionArr[0]);
			}
			//切换页面云台控制 去掉选中蓝色 隐藏控制台
			if($("#console").hasClass("button-s-select")){	
				$("#console").removeClass("button-s-select");
		    	$("#operate").hide();
			}
		}
		
		
		//播放视频
		var videoPlay = function(){
			isViewPlayStart =true;
			var nodes = treeObj.getCheckedNodes(true); 
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			//存入选中节点的id和name总共的
			checkedIdArray = [];
			checkedTextArray = [];
			var _index = 0;
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].pId != null){
					checkedIdArray[_index]=nodes[i].id;
					checkedTextArray[_index]=nodes[i].name;
					_index++;
					/* nodes[i].icon="./images/icon/spot.png";
					zTree.updateNode(nodes[i]); */
					//$("#test1").append(nodes[i].id+"；");
				}
			}
			//vedioStopMethod();
			videoStart(_index);
		};

		//开始播放视频客户端方法及展示名字
		var videoStart = function(videoNum){
			//选中播发放视频大于 页面数  按当前页面数加载视频画布
			 if(playPage<videoNum){
				 videoNum = playPage;
			 }
			 //$("#test1").html("");
			 //初始化播放视频
			 for(k=0;k<videoNum;k++){
			 	//盒子编号
		     	var boxNum = k+1;
			    //绑定设备号
			    $($("embed")[k]).attr("deviceId",checkedIdArray[k]);
				//调用客户端方法播放视频
				//alert(boxNum);
			    var i =  ys7.StartRealPlayEx(boxNum, boxNum, checkedIdArray[k], 1);
			    //$("#test1").append("播放视频"+i+"--盒子 "+boxNum+"设备"+checkedIdArray[k]);
			    //设置清晰度szDevSerial 设备号, iChannelNo 通道号, iVideoLevel 0-2清晰度  0不清晰  2 清晰
			    var o = ys7.SetVideoLevel(checkedIdArray[k],1,0);
			 }
			 //设置视频iframe的宽高以及盒子的宽高视频64个就不出现文字
			 setFontSize(videoNum);
		}

		//暂停视频 checkedText 每层选中要暂停的视频名字
		var videoStop = function(){
			 isViewPlayStart =false;
			 vedioStopMethod();
		}

		//暂停视频客户端方法及隐藏名字
		var vedioStopMethod = function(){
			 $("embed").each(function(i){
				 var _deviceId = $(this).attr("deviceId");
				 //隐藏视频名字及变白
		     	 $(this).parent().children(".videoNameFrame").css("display","none");
				 if(undefined != _deviceId && _deviceId.length>0){
					 //$("#test1").append("停止播放："+_deviceId+";");
					 var _boxId = $(this).attr("box");
					 if(undefined != _boxId && _boxId.length>0){
						 ys7.StopRealPlayEx(_boxId);
					 }
					 $(this).attr("deviceId","");
					 $(this).hide().show();
				 }
			 });
		}
		
		//单事件i=1 点击事件 1=2 双击事件    j是box盒子数
		//播放视频鼠标单击事件去加边框  双击最大屏幕
		function Onys7MouseEvent(i,j) {
			$(".leftBox").removeClass("check-box");
			$(".leftBox").find("embed").attr("class","leftBoxNoSelect");
			//i=1 点击事件 1=2 双击事件    j是box盒子数
			if(i==1){
				//单机加边框
				$(".leftBox").each(function() {
		        	if($(this).css("display")== "block"){
		                if($(this).find("embed").attr("box") == j){
		                	$(this).addClass("check-box");
		                	$(this).find("embed").attr("class","leftBoxSelect");
		                	//头部显示设备名
		                	if($(this).find("embed").attr("deviceId") != undefined){
		                		var _id = $(this).find("embed").attr("deviceId");
		                		reloadData.box = j;
		                		reloadData.deviceId =_id;
		                		var nodes = treeObj.getNodesByParam("id", _id, null);
		                		var node = nodes[0];
		                		var _html = "考点名称："+ node.name +" | 联系人："+node.linkManName +"（"+ node.linkManPhone +"）";
		                		$("#deviceName").html(_html);
		                		//var deviceName =$(this).find("iframe").contents().find("body").find("#font").html();
		                		/* $(".deviceName").css("display","block");
		                		$(".deviceName").find("span").html(deviceName); */
		                		//$("#test1").append("这次"+deviceName);
		                	}else{
		                		$("#deviceName").html("");
		                		/* $(".deviceName").find("span").html("");
		                		$(".deviceName").css("display","none"); */
		                	}
		                	//选中与上次不同才会停止语音对讲 关闭语音 关闭对讲
		                	//$("#test1").append("这次"+$(this).find("embed").attr("session")+"上次选中的的"+boforeSelectSessitionArr[0]);
		                	if(boforeSelectSessitionArr[0] == undefined || $(this).find("embed").attr("box") != boforeSelectSessitionArr[0]){
		                		//切换选中盒子 切换页面  重新播放  去掉 控制台 语音对讲 按钮去掉蓝色 及关闭控制台
		                		changgeConsoleViocePlayTalk();
		                	}
		            	}
		        	}
		        }) 
			}else{
				//双击页面盒子的个数
				var leftBoxDisplayNum = 0;
				//leftBoxDisplayNum=1还原盒子个数 ，不等于1放大盒子数
				var _index = 0;
				$(".leftBox").each(function() {
		        	if($(this).css("display") == "block" &&  $(this).find(".small-con").width()>1){
		        		leftBoxDisplayNum++;
		        	}
		        })
		        //$("#test1").append("---"+leftBoxDisplayNum)
		       	//双击盒子放大 
				if(leftBoxDisplayNum>1){
					$(".leftBox").each(function(k) {
			        	if($(this).css("display")== "block" &&  $(this).find(".small-con").width()>1 ){
			                if($(this).find("embed").attr("box") == j){
			                	//左面的宽度
			                	//窗体宽度
			        			var x1 = $("#content").width();
			        			//右侧宽度
			        			var x2 = $("#conRight").width();
			        			if($("#conRight").is(":hidden")){
			        				x2=0;
			        			}
			        			//左侧宽度
			        			var x3 = x1 - x2;
			        			//计算小窗口高度
			        			var x4 = x3 - 5;
			        			//小窗视频宽度，减4为左右边框
			        			var x5 = x4 - 4;
			        			//小窗视频高度
			        			var y5 = x5 * 9 / 16;
			        			var _tempObj = $(this).find(".small-con");
			        			_tempObj.width(x5);
								_tempObj.height(y5);
			                	
			                	var deviceId = $(this).find("embed").attr("deviceId");
			                	//头部显示设备名
			                	if($(this).find("embed").attr("deviceId") != undefined){
			                		var _id = $(this).find("embed").attr("deviceId");
			                		var nodes = treeObj.getNodesByParam("id", _id, null);
			                		var node = nodes[0];
			                		var _html = "考点名称："+ node.name +" | 联系人："+node.linkManName +"（"+ node.linkManPhone +"）";
			                		$("#deviceName").html(_html);
			                	}else{
			                		$("#deviceName").html("");
			                	}
			                	//清晰度 deviceId 设备号, iChannelNo 通道号, iVideoLevel 0-2清晰度  0不清晰  2 清晰
			                	var o = ys7.SetVideoLevel(deviceId,1,2);
			                	 // $("#test1").append(deviceId+"---"+o+"---。");
			            	}else{
			            		//$(this).hide();
			            		var _tempObj = $(this).find(".small-con")
			            		_tempObj.css("width","1px").css("height","1px");
			            		$(this).find("iframe").hide();
			            	}
			        	}
			        });
				}else{
					//还原缩小
					var _selectNumber = $(".footerButtonRight .buttonSelect").html();
			    	initSmallWin(_selectNumber);
			    	//videoPlay();
			    	//播放的视频名字展示
			    	$(".leftBox").each(function() {
			            if($(this).find("embed").attr("deviceId") != undefined){
			                 $(this).find("iframe").show();
			            }
			    	})

			    }    
			}
		}
	</script>
</body>
</html>
