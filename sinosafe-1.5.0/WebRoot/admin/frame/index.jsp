<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sinotn-数字时代新考试</title>
<!--框架必需start-->
<link href="../libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="../libs/skins/modernBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="modernBlue" positionTarget="positionContent" selInputHeight="28" selButtonWidth="29" gridHeaderHeight="32" gridRowHeight="30"/>
<link href="skin/style.css" rel="stylesheet" type="text/css" id="skin" skinPath="/frame/skin/"/>
<script type="text/javascript" src="../libs/js/jquery.js"></script>
<script type="text/javascript" src="../libs/js/main.js"></script>
<!--框架必需end-->

<!--弹窗组件start-->
<script type="text/javascript" src="../libs/js/popup/drag.js"></script>
<script type="text/javascript" src="../libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->

<!--分隔条start-->
<script type="text/javascript" src="../libs/js/nav/spliter.js"></script>
<!--分隔条end-->
<!-- 垂直选项卡start-->
<script type="text/javascript" src="../libs/js/nav/verticalTabModern.js"></script>
<!-- 垂直选项卡end-->
<script>
function bookmarksite(title, url){
    if (window.sidebar) // firefox
        window.sidebar.addPanel(title, url, "");
    else 
        if (window.opera && window.print) { // opera
            var elem = document.createElement('a');
            elem.setAttribute('href', url);
            elem.setAttribute('title', title);
            elem.setAttribute('rel', 'sidebar');
            elem.click();
        }
        else 
            if (document.all)// ie
                window.external.AddFavorite(url, title);
}
function windowClose(){
	window.opener=null;
	  window.open('', '_self'); //IE7必需的.
	  window.close();
}
function backHome(){
	window.document.getElementById("frmright").contentWindow.location="open.jsp";
	document.getElementById("frmleft").contentWindow.homeHandler();
	top.positionType="simple";
	top.positionContent="【当前位置：系统主页】";
}
$(function(){
	//var tabData={"list":[{"name":"安全考务指挥平台"},{"name":"服务平台系统"},{"name":"微信管理系统"},{"name":"法律职业资格系统"}]};
	var tabData={"list":[{"name":"安全考务指挥平台"}]};
	$("#vTab").data("data",tabData);
	$("#vTab").verticalTabModernRender();
	$("#vTab").bind("actived",function(e,i){
		loginOtherSystem(i);
		//存储点击tab索引
		//jQuery.jCookie('leftvtabIdx',i.toString());
	})
	mainResizeHandler();
	
	$("#mainSpliter").bind("stateChange",function(e,state){
		if(state=="hide"){
			$("#vTab").hide();
		}
		else if(state=="show"){
			$("#vTab").show();
		}
	})
})
function mainResizeHandler(){
	var currentheight =document.documentElement.clientHeight;
	$(".verticalTabModern_top").height(currentheight-126);
}

function exit(){
	if(confirm("是否确认退出？")){
		//ys7.CloseMe();
		window.location.href = "../../logout.action";
	}
}


function loginOtherSystem(index){
	if(index==0){
		document.getElementById("frmleft").contentWindow.initTree(index);
	}else if(index==1){
		var httpIp = "/SHSF/";
		loginSysMethod1(httpIp+"loginAjax.action","shanghai01","123456",function(data){
			console.log(data);
			document.getElementById("frmleft").contentWindow.initTree(index,httpIp);
		});
	}else if(index==2){
		var httpIp = "/sinoweixin/";
		loginSysMethod2(httpIp+"weixinLoginAjax.action","shanghai01","123456",function(data){
			console.log(data);
			document.getElementById("frmleft").contentWindow.initTree(index,httpIp);
		});
	}else if(index==3){
		var httpIp = "/lawreg/";
		loginSysMethod3(httpIp+"loginAjax.jspx","shanghai01","123456",function(data){
			console.log(data);
			document.getElementById("frmleft").contentWindow.initTree(index,httpIp);
		});
	}
}
function loginSysMethod1(url,username,pwd,sucFun){
	$.ajax({
		type : "POST",
		url : url,
		cache : true,
		data : {"sysUsersVo.loginName":username,"sysUsersVo.password":pwd},
		dataType: "json",
		success : sucFun,
		error : function(XMLHttpRequest, textStatus,
				errorThrown) {
			alert("系统异常[" + textStatus + "]:" + errorThrown);
		}
	});
}

function loginSysMethod2(url,username,pwd,sucFun){
	$.ajax({
		type : "POST",
		url : url,
		cache : true,
		data : {"weixinConfigVo.loginName":username,"weixinConfigVo.loginPassword":pwd},
		dataType: "json",
		success : sucFun,
		error : function(XMLHttpRequest, textStatus,
				errorThrown) {
			alert("系统异常[" + textStatus + "]:" + errorThrown);
		}
	});
}

function loginSysMethod3(url,username,pwd,sucFun){
	$.ajax({
		type : "POST",
		url : url,
		cache : true,
		data : {"account":username,"password":pwd,"loginAreaId":"31","verifyCode":""},
		dataType: "json",
		success : sucFun,
		error : function(XMLHttpRequest, textStatus,
				errorThrown) {
			alert("系统异常[" + textStatus + "]:" + errorThrown);
		}
	});
}

var refushLsft = function(){
	document.getElementById('frmleft').contentWindow.location.reload(true);
}

</script>
<style>
a {
	behavior:url(../libs/js/method/focus.htc)
}
</style>
</head>
<body>
<div id="mainFrame">

<div class="verticalTabModern" style="position:absolute;top:75px;" id="vTab"  iframeMode="true">
		<div style="width:0px;border:0;">
		</div>
	</div>	
<!--头部与导航start-->
<div id="hbox">
	<div id="bs_bannercenter">
	<div id="bs_bannerright">
	<div id="bs_bannerleft">	
		<input type="button" value="" class="functionBtn1" onclick="backHome()"/>
		<input type="button" value="" class="functionBtn2" onclick="exit()"/>
	</div>
	</div>
	</div>
	<div id="bs_navcenter">
	<div id="bs_navleft">
	<div id="bs_navright">
		<div class="bs_nav">
			 <div class="bs_navleft">
				<li class="fontTitle">字号:</li>
				<li class="fontChange"><span><a href="javascript:;" setFont="16">大</a></span></li>
				<li class="fontChange"><span><a href="javascript:;" setFont="14">中</a></span></li>
				<li class="fontChange"><span><a href="javascript:;" setFont="12">小</a></span></li>
				<div class="clear"></div>	
			</div>
			<div class="user_info2">
				<div class="icon_label_5" onclick="refushLsft()">欢迎：${loginSession.principal} ${examPlaceId}</div>
				<div class="icon_label_6">
					【今天是
				<script>
					var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
					var now = new Date();
				    var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate()
				    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
					document.write(currentime)
				</script>】
				</div>
				<div class="clear"></div>
			</div>
			<div class="user_info5" id="positionContent"></div>
		</div>
	</div>
	</div>
	</div>
</div>
<!--头部与导航end-->


<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
	<tr>
		<!--左侧区域start-->
		<td id="hideCon" class="ver01 ali01">
							<div id="lbox">
								<div id="lbox_topcenter">
								<div id="lbox_topleft">
								<div id="lbox_topright">
								</div>
								</div>
								</div>
								<div id="lbox_middlecenter">
								<div id="lbox_middleleft">
								<div id="lbox_middleright">
										<div id="bs_left">
										<IFRAME height="100%" width="100%"  frameBorder=0 id=frmleft name=frmleft src="${loginSession.leftFlag}"  allowTransparency="true"></IFRAME>
										</div>
										<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
								</div>
								</div>
								</div>
								<div id="lbox_bottomcenter">
								<div id="lbox_bottomleft">
								<div id="lbox_bottomright">
									<div class="lbox_foot"></div>
								</div>
								</div>
								</div>
							</div>
		</td>
		<!--左侧区域end-->
		
		<!--分隔栏区域start-->
		<td class="spliter main_shutiao" id="mainSpliter" targetId="hideCon" beforeClickTip="收缩面板" afterClickTip="展开面板" beforeClickClass="bs_leftArr" afterClickClass="bs_rightArr">
		</td>
		<!--分隔栏区域end-->
		
		<!--右侧区域start-->
		<td class="ali01 ver01"  width="100%">
							<div id="rbox">
								<div id="rbox_topcenter">
								<div id="rbox_topleft">
								<div id="rbox_topright">
								</div>
								</div>
								</div>
								<div id="rbox_middlecenter">
								<div id="rbox_middleleft">
								<div id="rbox_middleright">
									<div id="bs_right">
									       <IFRAME height="100%" width="100%" frameBorder=0 id=frmright name=frmright src="open.jsp"  allowTransparency="true"></IFRAME>
									</div>
								</div>
								</div>
								</div>
								<div id="rbox_bottomcenter" >
								<div id="rbox_bottomleft">
								<div id="rbox_bottomright">

								</div>
								</div>
								</div>
							</div>
		</td>
		<!--右侧区域end-->
	</tr>
</table>

<!--尾部区域start-->
 <div id="fbox">
	<div id="bs_footcenter">
	<div id="bs_footleft">
	<div id="bs_footright">
		Copyright（C） 2018 - 北京信诺软通信息技术有限公司</a>
	</div>
	</div>
	</div>
</div>
</div>
<!--尾部区域end-->


<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--窗口任务栏区域start-->
<div id="dialogTask" class="dialogTaskBg" style="display:none;"></div>
<!--窗口任务栏区域end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->
</body>
</html>

