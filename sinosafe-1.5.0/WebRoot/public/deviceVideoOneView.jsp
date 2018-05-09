<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title><s:property value="deviceVideoInfoVo.deviceName"/> </title>   
	<script type="text/javascript" src="../admin/libs/js/jquery.js"></script>
<style type="text/css">
.content{
	width: 100%;
}

</style>
</head>
<body> 
	<s:hidden id="appKey" name="appKey"></s:hidden>
	<s:hidden id="token" name="accessToken"></s:hidden>
	<s:hidden id="deviceId" name="deviceVideoInfoVo.deviceSerial"></s:hidden>
	<!-- 内容start -->
	<div class="content">
		<EMBED TYPE="application/x-window-plugin" box="1" lign="CENTER" width="100%"  height="100%" name ="flipper">  
	</div>
	<!-- 内容end -->
<script>
$(function() {
	//计算视频高度
    var conWidth = window.innerWidth;
    var conheight = conWidth * 9 / 16;
	$(".content").height(conheight);
	
	//获取页面三个隐藏值
	var appKey = $("#appKey").val();
	var token = $("#token").val();
	var deviceId = $("#deviceId").val();
	
	//alert(appKey+"---"+token+"---"+deviceId);
	 ys7.Init(appKey);
	 ys7.SetAccessToken(token);
	 _session = ys7.AllocSessionEx();
	 //alert(_session+"---"+deviceId);
	 ys7.StartRealPlayEx(_session, 1, deviceId, 1);
    
})
</script>
</body>
</html>
