//离线人数
var offOnLine ="";
//改变显示次数
var iconCount = 0;
//初始化视频第一次在线数和总数
var onlineNumFirst =[];
var onlineNum = 0;
var allNum = 0;
//1.appId加载萤石运行库，软件开始时调用一次就可以   ys7.Init(szAppId: string)
//2.设置Token，如果不改变AccessToken只需要设置一次
var appId = "";
var token = "";

//存入选中节点的id和name总共的
var checkedIdArray = [];
var checkedTextArray = [];

var treeObj;
//初始化播放视频

//页面播放画面数
var playPage = 4;
//视频状态
var isViewPlayStart = false;
//第一次请求树结构返回的数据
var url_tree_list = "../sh_deviceVideo/getDeviceVideoJson.action";
//语音对讲   1不能能对讲  0可以语音对讲前提能能听到对方声音
var openSound = 1; 
//存放上次选中的session
var boforeSelectSessitionArr=[]; 
$(function() {
	// 初始化开始 
	initWindow();
	initSession();
	initTree();
	
	//初始化树结构1秒1请求
    setInterval(updateNode, 60000);

    //窗口变化视频大小改变
    $(window).resize(function() {
    	initWindow();
    });
    
    //播放按钮
    $("#play_btn").click(function(){
    	if($(this).attr("class") == "startButton"){
    		$(this).removeClass("startButton").addClass("stopButton").html("停止");
    		videoPlay();
        	//重新播放 去掉 控制台 语音对讲 按钮去掉蓝色 及关闭控制台
        	changgeConsoleViocePlayTalk();
    	}else{
    		//按钮暂停
    		$(this).removeClass("stopButton").addClass("startButton").html("播放");
    		videoStop();
        	//停止播放 去掉 控制台 语音对讲 按钮去掉蓝色 及关闭控制台
        	changgeConsoleViocePlayTalk();
    	}	
    });
    
    //页面底部切换视频个数按钮 1,4,9,16,25,36,49,64
    $(".footerButtonRight a").click(function() {
    	$(".footerButtonRight a").each(function() {
            if ($(this).attr("class") == "buttonSelect") {
                $(this).removeClass("buttonSelect");
            }
        }) 
    	$(this).addClass("buttonSelect");
    	//切换页面 去掉 控制台 语音对讲 按钮去掉蓝色 及关闭控制台
    	changgeConsoleViocePlayTalk();
    	var _count = $(this).html();
    	if(_count.length<1){
    		_count = 6;
    	}
        //改变页面视频数也随之改变
        changePage(_count);
        
    })
    
    //点击进入全屏
    $(".fullScreen").click(function(){
    	$(".title").hide();
    	$(".footer").hide();
    	$(".conRight").hide();
    	//放大后屏幕宽度及高度
        var leftWidth = window.innerWidth-10;
        var conHeight = window.innerHeight-10;
        var leftWidth = conHeight * 16 / 9;
       
        $(".content").width(leftWidth);
        $(".conLeft").width(leftWidth);
        $(".content").height(conHeight);
        $(".content").css("margin","0 auto");
        if($(".buttonSelect").html() == ""){
        	var count = 6;
        }else{
        	var count = parseInt($(".buttonSelect").html());
        }
        changePageWin(count);
    })
    //退出全屏
    $(window).keydown(function (event) {
        if (event.keyCode == 27) {
        	$(".title").show();
        	$(".footer").show();
        	$(".conRight").show();
        	//放大后屏幕宽度及高度
            $(".content").css("margin","0");
            if($(".buttonSelect").html() == ""){
            	var count = 6;
            }else{
            	var count = parseInt($(".buttonSelect").html());
            }
            initWindow();
            //changePageWin(count);
        }
    });
    //云控制台
    $(".console").click(function(){
    	//改变样式
    	if(!$(this).hasClass("voiceSelect")){
    		$(this).addClass("voiceSelect");
    	}else{
    		$(this).removeClass("voiceSelect");
    	}
    	
    	if($("#operate").css("display") == "none"){
    		$("#operate").show();
    	}else{
    		$("#operate").hide();
    	}
    })
    //对讲   开启时：先开语音再开对讲 ，关闭只关闭对讲
    $("#voiceTalk").click(function(){
    	if(!$(this).hasClass("voiceSelect")){
    		$(this).addClass("voiceSelect");
    		boforeSelectSessitionArr=[]; 
    		$(".leftBox").each(function() {
            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
            		var boxId = $(this).find("embed").attr("box");
        			var deviceId = $(this).find("embed").attr("deviceId");
        			var setVolume = ys7.SetVolume(boxId,100);
        			openSound = ys7.OpenSound(boxId);
        			$("#voicePlay").addClass("voiceSelect");
        			var startVoiceTalkEx = ys7.StartVoiceTalkEx(boxId,deviceId,1);
        			boforeSelectSessitionArr.push(boxId);
            		//$("#test1").append("声音对讲开始："+startVoiceTalkEx);
            	}
            }) 
    	}else{
    		$(this).removeClass("voiceSelect");
    		$(".leftBox").each(function() {
            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
            		var boxId = $(this).find("embed").attr("box");
        			var deviceId= $(this).find("embed").attr("deviceId");
        			var stopVoiceTalk = ys7.StopVoiceTalk(boxId);
            		if($("#voicePlay").hasClass("voiceSelect")){
            			var CloseSound = ys7.CloseSound(boxId);
            			var openSound = ys7.OpenSound(boxId);
            		}
            	}
            }) 
    	}
    })
    //语音 开启时只开语音，关闭先关语音再关对讲
    $("#voicePlay").click(function(){
    	if(!$(this).hasClass("voiceSelect")){
    		$(this).addClass("voiceSelect");
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
    		$(this).removeClass("voiceSelect");
    		$(".leftBox").each(function() {
            	if($(this).find("embed").attr("class")== "leftBoxSelect"){
            		var boxId = $(this).find("embed").attr("box");
        			var deviceId = $(this).find("embed").attr("deviceId");
        			if($("#voiceTalk").hasClass("voiceSelect")){
        				var stopVoiceTalk = ys7.StopVoiceTalk(boxId);
            			$("#voiceTalk").removeClass("voiceSelect");
            		}
            		var CloseSound = ys7.CloseSound(boxId);
            	}
            }) 
    	}
    })
    
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
    //中间高度
    var conHeight = window.innerHeight - 85;
    //左面的宽度
    var leftWidth = conHeight * 16 / 9;
    //alert("conHeight--"+conHeight+"--leftWidth--"+leftWidth);
    //计算屏幕宽度是正方形
    if (window.innerWidth - leftWidth > 260) {
        $("body").width(window.innerWidth + "px");
        $(".content").width(window.innerWidth - 10 + "px");
        $(".content").height(conHeight - 10 + "px");
        $(".conLeft").width(leftWidth + "px");
        $(".conRight").width($(".content").width() - $(".conLeft").width() - 10 + "px");
        $(".title").width(window.innerWidth + "px");
    } else if (leftWidth - 10 + 260 >= document.body.clientWidth || $(".conRight").width() <= 260) {
        var contentWidth = leftWidth + 260;
        $("body").width(contentWidth + 10 + "px");
        $(".content").width(contentWidth + "px");
        $(".content").height(conHeight - 10 + "px");

        $(".conLeft").width(leftWidth + "px");
        $(".conRight").width("260px");
        $(".title").width($("body").width());
        $("body").css("overflow", "auto");
    } else {
        $("body").width(window.innerWidth + "px");
        $(".content").width(window.innerWidth - 10 + "px");
        $(".content").height(conHeight - 10 + "px");

        $(".conLeft").width(leftWidth + "px");
        $(".conRight").width($(".content").width() - $(".conLeft").width() - 3 + "px");
    }
    //视频宽高
    if($(".buttonSelect").html() == ""){
   		 var showNum = 6;
   	}else{
   		var showNum = parseInt($(".buttonSelect").html());
   	}
    
    $(".leftBox").each(function(i){
    	if(i>= showNum){
    		$(this).css("display","none");
		}else{
			$(this).css("display","block");
		}
    });
    
	
    if ($(".buttonSelect").html() == "1") {
        $(".leftBox").width(leftWidth-4  + "px");
        $(".leftBox").height(conHeight-12  + "px");
    } else if ($(".buttonSelect").html() == "4") {
        $(".leftBox").width((leftWidth - 10) / 2 + "px");
        $(".leftBox").height((conHeight - 18) / 2 + "px");
    } else if ($(".buttonSelect").html() == "9") {
        $(".leftBox").width(($(".conLeft").width() - 14) / 3 + "px");
        $(".leftBox").height((conHeight  - 21) / 3 + "px");
    } else if ($(".buttonSelect").html() == "16") {
        $(".leftBox").width(($(".conLeft").width() - 18) / 4 + "px");
        $(".leftBox").height((conHeight - 26) / 4 + "px");
    } else if ($(".buttonSelect").html() == "25") {
        $(".leftBox").width(($(".conLeft").width() - 22) / 5 + "px");
        $(".leftBox").height((conHeight - 30) / 5 + "px");
    } else if ($(".buttonSelect").html() == "36") {
        $(".leftBox").width(($(".conLeft").width() - 26) / 6 + "px");
        $(".leftBox").height((conHeight - 34) / 6 + "px");
    } else if ($(".buttonSelect").html() == "49") {
        $(".leftBox").width(($(".conLeft").width() - 30) / 7 + "px");
        $(".leftBox").height((conHeight - 38) / 7 + "px");
    } else if ($(".buttonSelect").html() == "64") {
        $(".leftBox").width(($(".conLeft").width() - 34) / 8 + "px");
        $(".leftBox").height((conHeight -42) / 8 + "px");
    } else if ($(".buttonSelect").html() == "") {
    	//分屏左大五个小视频
        for (i = 0; i < 6; i++) {
            if (i == 0) {
            	$($(".leftBox")[i])
                var splitWidth = ($(".conLeft").width() - 8) / 3 * 2 + "px";
                var splitHeight = (conHeight - 10) / 3 * 2 + "px";
                $($(".leftBox")[i]).css("width",splitWidth);
                $($(".leftBox")[i]).css("height",splitHeight);
            } else {
            	$($(".leftBox")[i]).css("width", ($(".conLeft").width() - 14) / 3);
            	$($(".leftBox")[i]).css("height", (conHeight - 14) / 3);
            }
        }
    }
    //树结构高度
    $(".ztree").height(conHeight - 60 + "px");
    
}
var initSession = function() {
    //请求appurl
    var url_initToken = "../sh_deviceVideo/getDeviceVideoTocken.action";
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
					 var app1 =  ys7.SetAccessToken(token);
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

var initTree = function(){
    //树结构数据加载
    var setting = {
		view: {
			showLine: false
		},
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }/*,callback: {
    		onClick: zTreeOnClick
    	}*/
    };
    //请求树结构
    $.ajax({
			type : "POST",
			url : url_tree_list,
			cache : true,
			dataType : "json",
			success : function(data) {
				if (data != null) {
					if (data.dataStatus == "1") {
						zNodes = data.dataMain;
						treeObj =  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
						//计算每层的监控个数及在线个数
						count(zNodes);
						$(".node_name").each(function(){
							var nodeName = $(this).parent().find("span:first").css("background");
							if(nodeName.indexOf("blue.png") != -1){
								$(this).css("color","#fff");
							}
						})
						
						$('.scrollbar-inner').scrollbar();
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

/*视频没出现 单击鼠标加载
var zTreeOnClick = function(event, treeId, treeNode) {
	if(treeNode.pId != null){
		var _tempId = treeNode.id;
		$("embed").each(function(){
			 var _deviceId = $(this).attr("deviceId");
			 if(undefined != _deviceId && _deviceId==_tempId){
				
				 var _boxId = $(this).attr("box");
				 //$("#test1").html("");
				 //$("#test1").html(_boxId);
				 if(undefined != _boxId && _boxId.length>0){
					 vedioStopMethod();
					 var i =  ys7.StartRealPlayEx(_boxId, _boxId, _deviceId, 1);
		 		     //设置清晰度szDevSerial 设备号, iChannelNo 通道号, iVideoLevel 0-2清晰度  0不清晰  2 清晰
					 var o = ys7.SetVideoLevel(_deviceId,1,0);
		 		     
					// $("#test1").html("单个刷新："+_deviceId+";"+_session+";"+_boxId+"返回"+i);
				 }
				 //$(this).hide().show();
			 }
		 });
		
	}
}
*/
//具体摄像头改变后的状态
var updateNode = function() {
    var  onlineNum = onlineNumFirst[0];
    //多次次请求的数据
    //请求树结构
    $.ajax({
		type : "POST",
		url : url_tree_list,
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
			        var treeIds = $("#treeDemo").find("li");
			        
			        for(k=0;k<treeIds.length;k++){
			        	var treeIdOne =  $(treeIds[k]).attr("id");
			        	var nodes= zTree.getNodeByTId(treeIdOne).children;
			        	//计算视频总数和在线数
			        	if (nodes != undefined) {
			              	if (nodes.length != "0") {
			              		//循环原来每一层数据和新更新的数据作对比 i：原来数据  j: 更新数据
			      	        	for (var i = 0;i<nodes.length; i++) {
			      	              	//刷新数据是视频在播放状态更改原来的图标
			      	              	if(nodes[i].icon == "css/zTreeStyle/img/diy/spot.png"){
			      	              		nodes[i].icon ="css/zTreeStyle/img/diy/blue.png";
			      	              	}
			      	              	for(j=3;j<zNodes.length;j++){
			      	              		 if (nodes[i].id == zNodes[j].id && nodes[i].icon != zNodes[j].icon) {
			      	                         iconCount++;
			      	                         if (nodes[i].icon == "css/zTreeStyle/img/diy/blue.png") {
			      	                             offOnLine += nodes[i].name + "掉线。";
			      	                         } else {
			      	                             offOnLine += nodes[i].name + "在线 。";
			      	                         }
			      	                         nodes[i].icon = zNodes[j].icon;
			      	                         zTree.updateNode(nodes[i]);
			      	                     }
			      	                     //提示有几次变动
			      	                     //$(".voiceTips").html(iconCount);
			      	              	}
			      	                 
			      	             }
			              	 }
			             }
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
function count(treeNodes){
	var onlineNum1 = 0;
    var allNum1 = 0;
    var onlineNum2 = 0;
    var allNum2 = 0;
    var onlineNum3 = 0;
    var allNum3 = 0;
	//计算每层的监控个数及在线个数
    if (treeNodes.length > 3) {
        for (k = 0; k < treeNodes.length; k++) {
        	if(treeNodes[k].pId =="1"){
        		allNum1++;
        		if (treeNodes[k].icon == "css/zTreeStyle/img/diy/blue.png") {
	                onlineNum1++;
	            }
        	}else if(treeNodes[k].pId =="2"){
        		allNum2++;
        		if (treeNodes[k].icon == "css/zTreeStyle/img/diy/blue.png") {
	                onlineNum2++;
	            }
        	}else if(treeNodes[k].pId =="3"){
        		allNum3++;
        		if (treeNodes[k].icon == "css/zTreeStyle/img/diy/blue.png") {
	                onlineNum3++;
	            }
        	}
        }
        
        allNum= treeNodes.length-3;
        
        onlineNum= onlineNum1+onlineNum2+onlineNum3;
        onlineNumFirst.push(onlineNum);
        $(".videoOnline").html(onlineNum);
        $(".videoAll").html(allNum);
        var treeDemo = $("#treeDemo").children("li");
        for (j = 1; j < treeDemo.length+1; j++) {
        	if(j==1){
        		var onlineChangeNum = onlineNum1;
        		var allChangeNum = allNum1;
        	}else if(j==2){
        		var onlineChangeNum = onlineNum2;
        		var allChangeNum = allNum2;
        	}else if(j==3){
        		var onlineChangeNum = onlineNum3;
        		var allChangeNum = allNum3;
        	}
        	if($($("#treeDemo").children("li")[j-1]).find(".fontRight").length>0){
        		$($("#treeDemo").children("li")[j-1]).find(".fontRight").remove();
        	}
        	var videoOnlineAll='<div class="fontRight paddingRight15">['+
								'<span class="videoOnline'+j+'">'+onlineChangeNum+'</span>/'+
								'<span class="videoAll'+j+'">'+allChangeNum+'</span>'+
								']</div>';
        	
    		$($("#treeDemo").children("li")[j-1]).append(videoOnlineAll);
        	
        }
        
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
			nodes[i].icon="css/zTreeStyle/img/diy/spot.png";
			zTree.updateNode(nodes[i]);
			//$("#test1").append(nodes[i].id+"；");
		}
	}
	//vedioStopMethod();
	videoStart(_index);
};

//切换页面
function changePage(count) {
	//$("#test1").append("count"+count+"；");
	playPage = count;
	//空闲没播放的改为1像素
	changePageStart(count);
	//开始视频
	videoPlay();
	//vedioStopMethod();
	//changePageWin(count);
	
	/*if(isViewPlayStart){
		videoPlay();
    }*/
	

}

//切换页面计算盒子大小
var changePageWin = function(count){
	$(".leftBox").each(function(i){
		if(i<count){
			$(this).show();
		}else{
			$(this).hide();
		}
	});

	var conHeight = $(".content").height();
    //左面大盒子的宽度
    var leftWidth = $(".conLeft").width();
    
	//计算小视频盒子的宽高
    if (count == 1) {
    	 $(".leftBox").css("width", leftWidth - 4);
         $(".leftBox").css("height", $(".content").height() - 2);
    }else if (count == 4) {
    	 $(".leftBox").css("width", (leftWidth - 10) / 2);
         $(".leftBox").css("height", (conHeight - 8) / 2);
    }else if (count == 9) {
    	$(".leftBox").css("width", (leftWidth - 14) / 3);
        $(".leftBox").css("height", (conHeight - 12) / 3);
    }else if (count == 16) {
    	$(".leftBox").css("width", (leftWidth - 18) / 4);
        $(".leftBox").css("height", (conHeight - 16) / 4);
    }else if (count == 25) {
    	 $(".leftBox").css("width", (leftWidth - 22) / 5);
         $(".leftBox").css("height", (conHeight - 20) / 5);
    }else if (count == 36) {
    	$(".leftBox").css("width", (leftWidth - 26) / 6);
        $(".leftBox").css("height", (conHeight - 24) / 6);
    }else if (count == 49) {
        $(".leftBox").width(($(".conLeft").width() - 30) / 7 + "px");
        $(".leftBox").height((conHeight - 28) / 7 + "px");
    }else if (count == 64) {
    	$(".leftBox").css("width", (leftWidth - 34) / 8);
        $(".leftBox").css("height", (conHeight - 32) / 8);
    } else if (count == 6) {
    	//分屏左大五个小视频
        for (i = 0; i < 6; i++) {
            if (i == 0) {
            	$($(".leftBox")[i])
                var splitWidth = (leftWidth - 8) / 3 * 2 + "px";
                var splitHeight = (conHeight - 10) / 3 * 2 + "px";
                $($(".leftBox")[i]).css("width",splitWidth);
                $($(".leftBox")[i]).css("height",splitHeight);
            } else {
            	$($(".leftBox")[i]).css("width", (leftWidth - 14) / 3);
            	$($(".leftBox")[i]).css("height", (conHeight - 14) / 3);
            }
        }
    }
}
//切换页面计算盒子大小视频开启状态
var changePageStart = function(count){
	$(".leftBox").each(function(i){
		if(i<count){
			$(this).show();
			
		}else{
			$(this).hide();
			$(this).css("width","1px").css("height","1px");
		}
	});

	var conHeight = $(".content").height();
    //左面大盒子的宽度
    var leftWidth = $(".conLeft").width();
    
	//计算小视频盒子的宽高
    if (count == 1) {
    	 $(".leftBox").css("width", leftWidth - 4);
         $(".leftBox").css("height", $(".content").height() - 2);
    }else if (count == 4) {
    	 $(".leftBox").css("width", (leftWidth - 10) / 2);
         $(".leftBox").css("height", (conHeight - 8) / 2);
    }else if (count == 9) {
    	$(".leftBox").css("width", (leftWidth - 14) / 3);
        $(".leftBox").css("height", (conHeight - 12) / 3);
    }else if (count == 16) {
    	$(".leftBox").css("width", (leftWidth - 18) / 4);
        $(".leftBox").css("height", (conHeight - 16) / 4);
    }else if (count == 25) {
    	 $(".leftBox").css("width", (leftWidth - 22) / 5);
         $(".leftBox").css("height", (conHeight - 20) / 5);
    }else if (count == 36) {
    	$(".leftBox").css("width", (leftWidth - 26) / 6);
        $(".leftBox").css("height", (conHeight - 24) / 6);
    }else if (count == 49) {
        $(".leftBox").width(($(".conLeft").width() - 30) / 7 + "px");
        $(".leftBox").height((conHeight - 28) / 7 + "px");
    }else if (count == 64) {
    	$(".leftBox").css("width", (leftWidth - 34) / 8);
        $(".leftBox").css("height", (conHeight - 32) / 8);
    } else if (count == 6) {
    	//分屏左大五个小视频
        for (i = 0; i < 6; i++) {
            if (i == 0) {
            	$($(".leftBox")[i])
                var splitWidth = (leftWidth - 8) / 3 * 2 + "px";
                var splitHeight = (conHeight - 10) / 3 * 2 + "px";
                $($(".leftBox")[i]).css("width",splitWidth);
                $($(".leftBox")[i]).css("height",splitHeight);
            } else {
            	$($(".leftBox")[i]).css("width", (leftWidth - 14) / 3);
            	$($(".leftBox")[i]).css("height", (conHeight - 14) / 3);
            }
        }
    }
    $(".leftBox").each(function(i){
		if(i>=count){
			$(this).css("width","1px").css("height","1px");
		}
	});
}

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
		 if(undefined != _deviceId && _deviceId.length>0){
			 //$("#test1").append("停止播放："+_deviceId+";");
			 var _boxId = $(this).attr("box");
			 //隐藏视频名字及变白
	     	 $($("embed")[i]).parent().children(".videoNameFrame").css("display","none");
			 if(undefined != _boxId && _boxId.length>0){
				 ys7.StopRealPlayEx(_boxId);
			 }
			 $(this).attr("deviceId","");
			 $(this).hide().show();
		 }
	 });
}
//参数 盒子号：boxNum,当前页面数videoNum
var setFontSize = function(videoNum){
	//$("#test1").append("videoNum"+videoNum);
	for(k=0;k<videoNum;k++){
		var boxNum = k+1;
		$("#iframe"+boxNum).css("display","block");
		if($(".buttonSelect").html() == ""){
        	var selectNum = 6;
        }else{
        	var selectNum = parseInt($(".buttonSelect").html());
        }
		$("#iframe"+boxNum).contents().find("body").find("#font").attr("class","fontSize"+selectNum);
		
	  	var fontSize= parseInt($("#iframe"+boxNum).contents().find("body").find("#font").css("font-size").replace("px",""));
	  	//$("#test1").append("fontSize"+fontSize);
	  	var fontSize5 = fontSize*7;
	  	$("#iframe"+boxNum).css("width",fontSize5+"px");
	  	$("#iframe"+boxNum).css("height",fontSize+"px");
	  	$("#iframe"+boxNum).contents().find("body").find("#font").width(fontSize5);
	  	var str = checkedTextArray[k];
	  	//验证是否都是中文 ，不是中文两个算一个字 
	  		var re = /^[0-9]+$/;
			//数字长度
			var indexNum = 0;
			//文字长度
			var indexStr = 0;
			for(i=0;i<str.length;i++){ 
				var strOne = str.charAt(i);
				//验证第一个字符串
				if (re.test(strOne)) {
					indexNum++;
				}else{
					indexStr++;
				}
				
			}
			if(indexNum%2 == 0){
				var numLength = indexNum/2;
			}else{
				var numLength = parseInt(indexNum/2)+1;
			}
			
		var lengthAll = indexStr + numLength;
		//$("#test1").append("总长度"+lengthAll+";  文字长度"+indexStr+";  数字长度"+indexNum);
		
	   	if(lengthAll>7 && lengthAll <= 14 ){
	 		$("#iframe"+boxNum).height(fontSize*2);
	 		$("#iframe"+boxNum).contents().find("body").find("#font").height(fontSize*2);
	 	}else if(lengthAll>14 && lengthAll <= 21 ){
	 		$("#iframe"+boxNum).height(fontSize*3);
	 		$("#iframe"+boxNum).contents().find("body").find("#font").height(fontSize*3);
	 	}else if(lengthAll>21 && lengthAll <= 28){
	 		$("#iframe"+boxNum).height(fontSize*4);
	 		$("#iframe"+boxNum).contents().find("body").find("#font").height(fontSize*4);
	 	}else if(lengthAll <= 7){
	 		$("#iframe"+boxNum).contents().find("body").find("#font").height(fontSize);
	 	}
	 	//设置视频的名字
	    //$("#test1").append("名字"+str);
	 	$("#iframe"+boxNum).contents().find("body").find("#font").html(str);
	 	//设置视频的名字结束
	}
	
}
//切换选中盒子 切换页面  重新播放  去掉 控制台 语音对讲 按钮去掉蓝色 及关闭控制台
var changgeConsoleViocePlayTalk= function(){
	//切换页面     语音按钮 去掉选中蓝色 关闭语音
	if($("#voicePlay").hasClass("voiceSelect")){
		$("#voicePlay").removeClass("voiceSelect");
		var CloseSound = ys7.CloseSound(boforeSelectSessitionArr[0]);
	}
	//切换页面   对讲按钮去掉选中蓝色 关闭对讲
	if($("#voiceTalk").hasClass("voiceSelect")){
		$("#voiceTalk").removeClass("voiceSelect");
		var stopVoiceTalk = ys7.StopVoiceTalk(boforeSelectSessitionArr[0]);
	
	}
	//切换页面云台控制 去掉选中蓝色 隐藏控制台
	if($(".console").hasClass("voiceSelect")){	
		$(".console").removeClass("voiceSelect");
    	$("#operate").hide();
		
	}
}
//单事件i=1 点击事件 1=2 双击事件    j是box盒子数
//播放视频鼠标单击事件去加边框  双击最大屏幕
function Onys7MouseEvent(i,j) {
	$(".leftBox").find("embed").attr("class","leftBoxNoSelect");
	//i=1 点击事件 1=2 双击事件    j是box盒子数
	if(i==1){
		//单机加边框
		$(".leftBox").each(function() {
        	if($(this).css("display")== "block"){
                if($(this).find("embed").attr("box") == j){
                	$(this).find("embed").attr("class","leftBoxSelect");
                	//$("#test1").append("class"+$(this).find("embed").attr("class"));
                	//头部显示设备名
                	if($(this).find("embed").attr("deviceId") != undefined){
                		var deviceName =$(this).find("iframe").contents().find("body").find("#font").html();
                		$(".deviceName").css("display","block");
                		$(".deviceName").find("span").html(deviceName);
                		//$("#test1").append("这次"+deviceName);
                	}else{
                		$(".deviceName").find("span").html("");
                		$(".deviceName").css("display","none");
                		
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
		$(".leftBox").each(function() {
        	if($(this).css("display") == "block" && $(this).css("width") != "1px"){
        		leftBoxDisplayNum++;
        	}
        })
       	//双击盒子放大 
		if(leftBoxDisplayNum != 1 || $(".buttonSelect").html() == "1"){
			$(".leftBox").each(function(k) {
	        	if($(this).css("display")== "block"){
	                if($(this).find("embed").attr("box") == j){
	                	//左面的宽度
	                	var leftWidth = $(".conLeft").width()-4;
	                	var conHeight = $(".content").height()-4;
	                	$(this).width(leftWidth);
	                	$(this).height(conHeight);
	                	var deviceId = $(this).find("embed").attr("deviceId");
	                	//头部显示设备名
	                	if(deviceId != undefined){
	                		var deviceName =$(this).find("iframe").contents().find("body").find("#font").html();
	                		$(".deviceName").css("display","block");
	                		$(".deviceName").find("span").html(deviceName);
	                		//$("#test1").append("这次"+deviceName);
	                	}else{
	                		$(".deviceName").find("span").html("");
	                		$(".deviceName").css("display","none");
	                		
	                	}
	                	//清晰度 deviceId 设备号, iChannelNo 通道号, iVideoLevel 0-2清晰度  0不清晰  2 清晰
	                	var o = ys7.SetVideoLevel(deviceId,1,2);
	                	 // $("#test1").append(deviceId+"---"+o+"---。");
	            	}else{
	            		//双击放大 停掉sesstion 其他隐藏
	        			 var _boxId =$(this).find("embed").attr("box");
	        			 //ys7.StopRealPlayEx(_boxId);
		       			 $(this).css("width","1px").css("height","1px");
		       			 $(this).find("iframe").hide();
	            	}
	        	}
	        }) 
		}else{
			//双击缩小且 还原盒子的个数
            if($(".buttonSelect").html()== ""){
       			var count = 6;
       		}else{
       			var count = parseInt($(".buttonSelect").html()); 
       		}
            //左面大盒子的宽度高度
            var leftWidth = $(".conLeft").width()-4;  
            var conHeight = $(".content").height();
            //重新生成盒子去除选中的边框
            $(".leftBox").each(function(i){
        		if(i<count){
        			$(this).find("embed").attr("class","leftBoxNoSelect");
        		}
        	});
            
	        $(".leftBox").each(function() {
	        	if($(this).css("display")== "block"){
	        		//选中边框及头部显示设备名
                    if($(this).find("embed").attr("box") == j){
                    	$(this).find("embed").attr("class","leftBoxSelect");
                    	//头部显示设备名
	                	if($(this).find("embed").attr("deviceId") != undefined){
	                		var deviceName =$(this).find("iframe").contents().find("body").find("#font").html();
	                		$(".deviceName").css("display","block");
	                		$(".deviceName").find("span").html(deviceName);
	                		//$("#test1").append("这次"+deviceName);
	                	}else{
	                		$(".deviceName").find("span").html("");
	                		$(".deviceName").css("display","none");
	                		
	                	}
                	}
                    //名字展示
		            if($(this).find("embed").attr("deviceId") != undefined){
		                 $(this).find("iframe").show();
		            }
		            
                	//计算小视频盒子的宽高
                    if (count == 1) {
                    	 $(this).css("width", leftWidth - 4);
                         $(this).css("height", $(".content").height() - 2);
                    }else if (count == 4) {
                    	 $(this).css("width", (leftWidth - 10) / 2);
                         $(this).css("height", (conHeight - 8) / 2);
                    }else if (count == 9) {
                    	$(this).css("width", (leftWidth - 14) / 3);
                        $(this).css("height", (conHeight - 12) / 3);
                    }else if (count == 16) {
                    	$(this).css("width", (leftWidth - 18) / 4);
                        $(this).css("height", (conHeight - 16) / 4);
                    }else if (count == 25) {
                    	 $(this).css("width", (leftWidth - 22) / 5);
                         $(this).css("height", (conHeight - 20) / 5);
                    }else if (count == 36) {
                    	$(this).css("width", (leftWidth - 26) / 6);
                        $(this).css("height", (conHeight - 24) / 6);
                    }else if (count == 49) {
                        $(this).width(($(".conLeft").width() - 30) / 7 + "px");
                        $(this).height((conHeight - 28) / 7 + "px");
                    }
                   
	            }
	        }) 
	        if (count == 6) {
            	//分屏左大五个小视频
                for (i = 0; i < 6; i++) {
                    if (i == 0) {
                    	$($(".leftBox")[i])
                        var splitWidth = (leftWidth - 8) / 3 * 2 + "px";
                        var splitHeight = (conHeight - 10) / 3 * 2 + "px";
                        $($(".leftBox")[i]).css("width",splitWidth);
                        $($(".leftBox")[i]).css("height",splitHeight);
                    } else {
                    	$($(".leftBox")[i]).css("width", (leftWidth - 14) / 3);
                    	$($(".leftBox")[i]).css("height", (conHeight - 14) / 3);
                    }
                }
            }
	        
	    }    
	}
}