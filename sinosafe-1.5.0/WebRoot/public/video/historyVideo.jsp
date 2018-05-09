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
    <script type="text/javascript" src="js/layer/layer.js"></script> 
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
</head>
<body> 
	<s:hidden name="deviceVideoInfoVo.deviceType" id="deviceType" value="A"></s:hidden>
	<div class="top">
		<div class="top-top-logo">
			<img src="./images/top-logo.png" />
		</div>
		<div class="top-title">
			<div class="deviceName" id="deviceName">历史录像视频回看</div>
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
		
		<div class="conLeft" id="conLeft">
	   		<!-- 右面start -->
	   		<div id="conRightTime">
				<!-- 日期 -->
			    <div id="date"></div>
			    <!-- 时间 -->
			    <div class="time">
			    	<div class="begain">
			          <label>开始时间：</label>
			          <input type="text"  name="preroom.startTime" id="dateBeginId"  class="timeStyle"
			            onfocus="WdatePicker({dateFmt:'HH:mm:ss',qsEnabled:true})" />                     
			        </div>
			        <div class="end">
			          <label>结束时间：</label> 
			           <input type="text"  name="preroom.endTime" id="dateEndId"  class="timeStyle"
			              onfocus="WdatePicker({dateFmt:'HH:mm:ss',qsEnabled:true})" />
			        </div>
			     </div>
			     <div class="dateSearch">
			        	搜    索
			     </div>
		    </div>
		    <!-- 中间start -->
		    <div id="conCenter">
		    	<div class="box">
		        	<div class="head">视频列表</div>
		        	<ul></ul>
		      	</div>
		    </div>
	   		<!-- 中间end -->
		 </div>
    	<!-- 右面end -->
	</div>
	<!-- 内容end -->
	<!-- 底部版权 -->
	<div class="footer">
		<div class="footerButtonLeft">
			<a href="javascript:void(0)" class="button-s" id="nowVideo">实时视频</a>
			<a href="javascript:void(0)" id="reflush" class="button-s">刷新</a>
		</div>
		<div class="clear"></div>
	</div>
<script>
var dayArr=[];
//初始化视频在线数和总数
var onlineNumFirst =[];
var onlineNum = 0;
var allNum = 0;
//设备Id数组
var deviceIdArr=[];
//第一次请求树结构返回的数据
var url_tree_list = "getDeviceVideoJson.action";
//搜索树结构最后一次输入名字
var lastValue = "";
//搜索树结构节点对象
var nodeList = [];
//img页面右侧搜索时间初始化
var oDate = new Date(); //实例一个时间对象；
var todayMoth =oDate.getMonth()+1;
var beforeMoth = oDate.getMonth();
//在当前时间及前一个月时间段查询 年月日
var today = oDate.getFullYear()+"-" + todayMoth+ "-"+oDate.getDate();
var todayBefore = oDate.getFullYear()+"-" + beforeMoth+ "-"+oDate.getDate();

//搜索到的样式
var fontCss = {};
$(function(){
	$("#dateBeginId").val(oDate.getHours()+":"+oDate.getMinutes()+":"+oDate.getSeconds());
	$("#dateEndId").val(oDate.getHours()+1+":"+oDate.getMinutes()+":"+oDate.getSeconds());
	// 初始化开始 计算中间宽
    init();
    initTree();
    
    //窗口变化视频大小改变
    $(window).resize(function() {
      init()
    });
    //实时视频
    $("#nowVideo").click(function(){
    	var _deviceType = $("#deviceType").val();	
    	window.location.href="gotoVideo.action?deviceVideoInfoVo.deviceType="+_deviceType;
    });
    
  	//刷新
    $("#reflush").click(function(){
    	location.reload();
    });
    //获取日期
	WdatePicker({
	    eCont:'date',
	    skin:'twoer',
	    onpicking:function(dp){
	    	dayArr.push(dp.cal.getNewDateStr()); 
	  	}
	})
	//树结构搜索按钮
	$(".searcBtn").click(function(){
		searchNode();
	}) 
	//树结构搜索按钮 回车
    $(window).keydown(function (event) {
        if (event.keyCode == 13) {
        	searchNode();
        }
    });

	//点击搜索按钮  时间初始化
	$(".dateSearch").click(function(){
		if(dayArr.length == 0){
			 dayArr.push(today); 	
		}
		var szStartTime = dayArr[dayArr.length-1] +" "+ $("#dateBeginId").val();
		var szStopTime = dayArr[dayArr.length-1] + " "+$("#dateEndId").val();
		//判断是否有选中的视频
    	if(deviceIdArr.length == 1){
    		//关闭弹框重新生成盒子
    		if($("#conCenter").find("EMBED").length == 0 ){
    			$("#conCenter").append('<EMBED TYPE="application/x-window-plugin" box="1"  id="boxId" lign="CENTER" style="width:1px;height:1px;" name ="flipper"> ');
    		}
    		//查询视频个数信息列表
    		var boxId= $("#boxId").attr("box");
    		//查询视频个数信息列表
    		var i = ys7.StartSearchEx(boxId,deviceIdArr[0], 1, szStartTime, szStopTime);
    		//$("#test1").html("Search函数返回值"+i+"szStartTime"+szStartTime+"szStopTime"+szStopTime+"---boxId"+boxId);
    	}else if(deviceIdArr.length == 0){
    		layer.alert('请选择要查找的设备!', {
    		    skin: 'layui-layer-lan',
    		    closeBtn: 0
    		});
    	}
    	//返回没有查询到视频
    	if(i != 0){
    		layer.alert('请选择要查找的设备!', {
    		    skin: 'layui-layer-lan',
    		    closeBtn: 0
    		});
    	}
	 }) 
   
})
 	
 
    //点击左侧树结构 中间出现详解 以及回掉函数弹框视频
    function onClick(event, treeId, treeNode, clickFlag) {
     	if(treeNode.pId == "1" || treeNode.pId == "2" || treeNode.pId == "3"){
     		//每次点击要清空
     		deviceIdArr=[];
    	 	deviceIdArr.push(treeNode.id);
    		//session = ys7.AllocSessionEx();
    		//关闭弹框重新生成盒子
    		if($("#conCenter").find("EMBED").length == 0 ){
    			$("#conCenter").append('<EMBED TYPE="application/x-window-plugin" box="1"  id="boxId" lign="CENTER" style="width:1px;height:1px;" name ="flipper"> ');
    		}
    		//查询视频个数信息列表
    		var boxId= $("#boxId").attr("box");
    	 	var Search =  ys7.StartSearchEx(boxId,treeNode.id, 1, todayBefore+" "+$("#dateBeginId").val(), today+" "+$("#dateBeginId").val());        	
    	 	//$("#test1").html("Search函数返回值"+Search+"--boxId"+boxId+"--设备--"+treeNode.id);
    	 	//返回没有查询到视频
        	if(Search != 0){
        		layer.alert('未搜索到视频!', {
        		    skin: 'layui-layer-lan',
        		    closeBtn: 0
        		}); 
        	}	
     	}
    }
	//客户端回调函数 获取设备所有视频信息
	function Onys7Backcall(msgid, boxid){
		var data = ys7.GetMessageInfo(msgid);
		//$("#test1").append("data-"+data);
		var data1 = data.replace("20,0,","");
	    //var data = '{"FileList":[{"EndTime":"2017-07-07 12:39:57","StartTime":"2017-07-07 12:38:29"},{"EndTime":"2017-07-07 12:43:14","StartTime":"2017-07-07 12:40:45"},{"EndTime":"2017-07-07 12:51:19","StartTime":"2017-07-07 12:46:59"},{"EndTime":"2017-07-07 12:55:05","StartTime":"2017-07-07 12:52:55"},{"EndTime":"2017-07-07 13:06:58","StartTime":"2017-07-07 13:01:32"},{"EndTime":"2017-07-07 13:09:27","StartTime":"2017-07-07 13:07:18"},{"EndTime":"2017-07-07 13:15:55","StartTime":"2017-07-07 13:12:24"},{"EndTime":"2017-07-07 13:27:14","StartTime":"2017-07-07 13:18:15"},{"EndTime":"2017-07-07 13:31:06","StartTime":"2017-07-07 13:27:38"},{"EndTime":"2017-07-07 13:34:58","StartTime":"2017-07-07 13:31:50"},{"EndTime":"2017-07-07 13:39:15","StartTime":"2017-07-07 13:37:06"},{"EndTime":"2017-07-07 13:45:00","StartTime":"2017-07-07 13:39:51"},{"EndTime":"2017-07-07 13:51:37","StartTime":"2017-07-07 13:47:55"},{"EndTime":"2017-07-07 13:57:30","StartTime":"2017-07-07 13:51:57"},{"EndTime":"2017-07-07 14:24:34","StartTime":"2017-07-07 13:57:38"},{"EndTime":"2017-07-07 14:29:30","StartTime":"2017-07-07 14:26:17"},{"EndTime":"2017-07-07 14:59:49","StartTime":"2017-07-07 14:30:46"},{"EndTime":"2017-07-07 15:06:59","StartTime":"2017-07-07 15:00:45"},{"EndTime":"2017-07-07 15:15:31","StartTime":"2017-07-07 15:13:11"},{"EndTime":"2017-07-07 15:22:03","StartTime":"2017-07-07 15:19:51"},{"EndTime":"2017-07-07 15:26:02","StartTime":"2017-07-07 15:22:23"},{"EndTime":"2017-07-07 15:33:34","StartTime":"2017-07-07 15:29:34"},{"EndTime":"2017-07-07 15:36:59","StartTime":"2017-07-07 15:33:54"},{"EndTime":"2017-07-07 15:48:14","StartTime":"2017-07-07 15:37:39"},{"EndTime":"2017-07-07 15:52:38","StartTime":"2017-07-07 15:49:54"},{"EndTime":"2017-07-07 15:55:23","StartTime":"2017-07-07 15:52:38"},{"EndTime":"2017-07-07 16:04:08","StartTime":"2017-07-07 15:55:31"},{"EndTime":"2017-07-07 16:11:02","StartTime":"2017-07-07 16:08:47"},{"EndTime":"2017-07-07 16:22:02","StartTime":"2017-07-07 16:18:18"},{"EndTime":"2017-07-07 16:24:56","StartTime":"2017-07-07 16:22:42"},{"EndTime":"2017-07-07 16:31:57","StartTime":"2017-07-07 16:26:23"},{"EndTime":"2017-07-07 16:39:38","StartTime":"2017-07-07 16:36:05"},{"EndTime":"2017-07-07 16:45:16","StartTime":"2017-07-07 16:41:43"},{"EndTime":"2017-07-07 16:57:01","StartTime":"2017-07-07 16:53:23"},{"EndTime":"2017-07-07 16:59:54","StartTime":"2017-07-07 16:57:13"},{"EndTime":"2017-07-07 17:00:18","StartTime":"2017-07-07 16:59:54"},{"EndTime":"2017-07-07 17:02:50","StartTime":"2017-07-07 17:00:42"},{"EndTime":"2017-07-07 17:10:21","StartTime":"2017-07-07 17:05:56"},{"EndTime":"2017-07-07 17:20:50","StartTime":"2017-07-07 17:18:41"},{"EndTime":"2017-07-07 17:32:12","StartTime":"2017-07-07 17:21:26"},{"EndTime":"2017-07-07 17:44:37","StartTime":"2017-07-07 17:32:16"},{"EndTime":"2017-07-07 17:57:03","StartTime":"2017-07-07 17:52:01"},{"EndTime":"2017-07-07 18:16:40","StartTime":"2017-07-07 18:06:42"},{"EndTime":"2017-07-07 18:29:04","StartTime":"2017-07-07 18:19:00"},{"EndTime":"2017-07-07 18:32:48","StartTime":"2017-07-07 18:29:40"},{"EndTime":"2017-07-07 18:36:12","StartTime":"2017-07-07 18:34:00"},{"EndTime":"2017-07-07 18:40:51","StartTime":"2017-07-07 18:36:12"},{"EndTime":"2017-07-07 19:02:12","StartTime":"2017-07-07 18:59:40"},{"EndTime":"2017-07-07 19:23:50","StartTime":"2017-07-07 19:21:37"},{"EndTime":"2017-07-07 19:26:00","StartTime":"2017-07-07 19:23:50"},{"EndTime":"2017-07-07 20:10:38","StartTime":"2017-07-07 20:08:17"},{"EndTime":"2017-07-07 20:18:03","StartTime":"2017-07-07 20:11:10"},{"EndTime":"2017-07-07 20:20:30","StartTime":"2017-07-07 20:18:11"},{"EndTime":"2017-07-07 20:25:04","StartTime":"2017-07-07 20:22:34"},{"EndTime":"2017-07-07 20:29:07","StartTime":"2017-07-07 20:25:12"},{"EndTime":"2017-07-07 20:37:05","StartTime":"2017-07-07 20:33:43"},{"EndTime":"2017-07-07 20:43:55","StartTime":"2017-07-07 20:37:29"},{"EndTime":"2017-07-07 20:48:56","StartTime":"2017-07-07 20:43:55"},{"EndTime":"2017-07-07 21:01:43","StartTime":"2017-07-07 20:58:12"},{"EndTime":"2017-07-07 21:03:14","StartTime":"2017-07-07 21:01:59"},{"EndTime":"2017-07-07 21:48:51","StartTime":"2017-07-07 21:46:36"},{"EndTime":"2017-07-07 21:57:50","StartTime":"2017-07-07 21:54:19"},{"EndTime":"2017-07-08 07:20:29","StartTime":"2017-07-08 07:13:16"},{"EndTime":"2017-07-08 07:33:11","StartTime":"2017-07-08 07:30:09"},{"EndTime":"2017-07-08 07:34:03","StartTime":"2017-07-08 07:33:11"},{"EndTime":"2017-07-08 07:41:25","StartTime":"2017-07-08 07:38:43"},{"EndTime":"2017-07-08 07:48:47","StartTime":"2017-07-08 07:44:20"},{"EndTime":"2017-07-08 07:54:51","StartTime":"2017-07-08 07:52:30"},{"EndTime":"2017-07-08 08:03:46","StartTime":"2017-07-08 07:58:44"},{"EndTime":"2017-07-08 08:28:21","StartTime":"2017-07-08 08:25:42"},{"EndTime":"2017-07-08 08:39:37","StartTime":"2017-07-08 08:36:57"},{"EndTime":"2017-07-08 10:31:59","StartTime":"2017-07-08 10:28:59"},{"EndTime":"2017-07-08 10:41:00","StartTime":"2017-07-08 10:38:52"},{"EndTime":"2017-07-08 11:00:20","StartTime":"2017-07-08 10:58:12"},{"EndTime":"2017-07-08 11:42:16","StartTime":"2017-07-08 11:39:58"},{"EndTime":"2017-07-08 11:45:28","StartTime":"2017-07-08 11:43:00"},{"EndTime":"2017-07-08 11:51:40","StartTime":"2017-07-08 11:47:35"},{"EndTime":"2017-07-08 11:59:31","StartTime":"2017-07-08 11:57:12"},{"EndTime":"2017-07-08 12:12:41","StartTime":"2017-07-08 12:08:52"},{"EndTime":"2017-07-08 12:42:43","StartTime":"2017-07-08 12:37:40"},{"EndTime":"2017-07-08 13:31:45","StartTime":"2017-07-08 13:28:13"},{"EndTime":"2017-07-08 13:46:53","StartTime":"2017-07-08 13:44:17"},{"EndTime":"2017-07-08 14:57:48","StartTime":"2017-07-08 14:53:35"},{"EndTime":"2017-07-08 17:03:48","StartTime":"2017-07-08 17:01:37"},{"EndTime":"2017-07-08 17:13:11","StartTime":"2017-07-08 17:06:51"},{"EndTime":"2017-07-08 18:53:29","StartTime":"2017-07-08 18:50:39"},{"EndTime":"2017-07-08 19:31:38","StartTime":"2017-07-08 19:25:41"},{"EndTime":"2017-07-08 21:13:54","StartTime":"2017-07-08 21:11:25"},{"EndTime":"2017-07-08 21:17:07","StartTime":"2017-07-08 21:14:54"},{"EndTime":"2017-07-08 21:22:25","StartTime":"2017-07-08 21:20:15"},{"EndTime":"2017-07-08 22:12:09","StartTime":"2017-07-08 22:08:51"},{"EndTime":"2017-07-09 08:13:58","StartTime":"2017-07-09 08:01:41"},{"EndTime":"2017-07-09 08:29:09","StartTime":"2017-07-09 08:26:53"},{"EndTime":"2017-07-09 08:32:22","StartTime":"2017-07-09 08:30:09"},{"EndTime":"2017-07-09 08:39:43","StartTime":"2017-07-09 08:36:19"},{"EndTime":"2017-07-09 08:53:59","StartTime":"2017-07-09 08:52:06"},{"EndTime":"2017-07-09 08:58:47","StartTime":"2017-07-09 08:53:59"},{"EndTime":"2017-07-09 09:09:27","StartTime":"2017-07-09 09:07:03"},{"EndTime":"2017-07-09 10:50:45","StartTime":"2017-07-09 10:48:28"},{"EndTime":"2017-07-09 11:03:34","StartTime":"2017-07-09 10:58:33"},{"EndTime":"2017-07-09 11:55:19","StartTime":"2017-07-09 11:52:38"},{"EndTime":"2017-07-09 14:00:35","StartTime":"2017-07-09 13:56:11"},{"EndTime":"2017-07-09 14:04:46","StartTime":"2017-07-09 14:02:35"},{"EndTime":"2017-07-09 17:14:54","StartTime":"2017-07-09 17:12:43"},{"EndTime":"2017-07-09 17:17:09","StartTime":"2017-07-09 17:15:02"},{"EndTime":"2017-07-09 18:23:12","StartTime":"2017-07-09 18:19:42"},{"EndTime":"2017-07-09 20:08:45","StartTime":"2017-07-09 20:05:39"},{"EndTime":"2017-07-09 20:17:33","StartTime":"2017-07-09 20:15:22"},{"EndTime":"2017-07-10 00:50:32","StartTime":"2017-07-10 00:48:18"},{"EndTime":"2017-07-10 07:47:34","StartTime":"2017-07-10 07:43:41"},{"EndTime":"2017-07-10 07:49:54","StartTime":"2017-07-10 07:47:34"},{"EndTime":"2017-07-10 08:21:27","StartTime":"2017-07-10 08:18:41"},{"EndTime":"2017-07-10 08:40:15","StartTime":"2017-07-10 08:37:06"},{"EndTime":"2017-07-10 08:46:07","StartTime":"2017-07-10 08:41:43"},{"EndTime":"2017-07-10 08:56:13","StartTime":"2017-07-10 08:49:19"},{"EndTime":"2017-07-10 09:40:04","StartTime":"2017-07-10 08:57:26"},{"EndTime":"2017-07-10 10:10:03","StartTime":"2017-07-10 09:40:04"}],"FileSize":117}'
		var FileSize = JSON.parse(data1).FileSize;
		if(FileSize == 0){
			layer.alert('未搜索到视频!', {
    		    skin: 'layui-layer-lan',
    		    closeBtn: 0
    		}); 
			$("#conCenter li").remove();
		}else{
			var dataList = JSON.parse(data1).FileList;
			if(dataList != undefined){
				$("#conCenter li").remove();
			}
		}
		
		//$("#test1").append("长度"+dataList.length);
		for(i=0;i<dataList.length;i++){
			var StartTime = dataList[i].StartTime;
			var EndTime = dataList[i].EndTime;
			//查询出来的时间段显示在中间
			$("#conCenter ul").append("<li class='li"+ i+"'>"+StartTime+"------" +EndTime +"</li>");
       		//点击播放视频
	   	  	 $(".li"+i).click(function(){
	   	  		 var timeStart = $(this).html().split("------")[0];
	   	  		 var timeStop = $(this).html().split("------")[1];
	   	  		 //$("#test1").append("timeStart-"+timeStart+"timeStop-"+timeStop);
	   	  		 var videoHtml ='<div class="leftBox leftBoxSelect"  style="height: 450px;width: 98%;margin:3px 8px"><EMBED TYPE="application/x-window-plugin" style="z-index:-1;" box="1" lign="CENTER" width="100%" height="100%" name ="flipper"></div>';
	   	  		 layer.open({
	   	  			    type: 1,
	   	  			    title: '欢迎回看视频',
	   	  			    maxmin: false,
	   	  			    skin: 'layui-layer-lan',
	   	  			    area: ['800px', '500px'],
	   	  			    content: videoHtml,
		   	  			cancel: function(){
		   	  				$("#conCenter").find("EMBED").remove();
			   	  		}
	   	  		});
	   	  		i = ys7.StartPlayBackEx(1, 1, deviceIdArr[0], 1, timeStart, timeStop);
		   })
		      
		}
	}
	
	
//初始化中间宽度
var init = function(){
    //img页面中间的宽度
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
	var y3 = x3 * 9 / 16 ;
	$("#content").height(y3);
	$("#conRightTime").height(y3-4);
	$("#conRight").height(y3-4);
	$("#conCenter").height(y3-4);
	$(".box").height(y3-20); 
	//右侧树内部窗口高度,4为上下边距
	var y7 = y3 - 38;
	$(".conRight-con").height(y7);
    $("#conCenter").width(document.body.clientWidth-614+"px");
  
    //$(".ztreeImg").height($(".contentImg").height()-90+"px");
   // $(".box").height(document.body.clientHeight-90+"px"); 
}

//初始化树结构
var initTree = function(){
	 //树结构数据加载
 	var setting = {
      data: {
	        simpleData: {
	          enable: true
	        }
	      },
		  view: {
				fontCss: getFontCss,
				showLine: false
		  },
	      callback: {
	        onClick: onClick
	      }
    };
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
						zNodes = data.dataMain;
						treeObj =  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
						$(".loading").hide();
						//计算每层的监控个数及在线个数
						count(zNodes);	
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

//计算每层的监控个数及在线个数
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
//搜索关键字 字体变红
var searchNode = function(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var value = $("#inputSearch").val().trim();
	updateNodes(false);
	if(value ==""){
		alert("请填写关键字");
		return false;
	}
	nodeList = zTree.getNodesByParamFuzzy("name", value);
	updateNodes(true);
}
function updateNodes(highlight) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
	}
}
function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"red", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}
</script>
</body>
</html>
