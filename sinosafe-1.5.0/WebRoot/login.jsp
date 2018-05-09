<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page language="java" import="java.net.URLDecoder"  pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>Sinotn-数字时代新考试</title>
	<script type="text/javascript" src="./login/js/jquery.js"></script>
	<script type="text/javascript" src="./login/js/layer/layer.js"></script>
    <link rel="stylesheet" href="./login/css/login2017.css" />  
</head>

<body style="background:#f2f2f2  url('./login/images/bg/login.jpg') center 0 no-repeat;"> 
  <div class="wbox">
    <div class="content">
      <!-- 登陆模块start -->
      <div class="con-login">
       <form id="loginFromId" name="loginForm" action="login.action" method="post">
       <input type="hidden" id="errorMsg" value="<s:property value="exception.message"/>"/>
       	<input type="hidden" name="verifyCode" value=""/>
          <div class="userBox">
              <span class="user">用户登录</span>&nbsp;&nbsp;
              <span class="userLogin">userLogin</span>
          </div>
          <div class="loginBox">
            <input type="text" class="loginInput" id="username" name="userVo.userId" value="" placeholder="请输入您的账号" maxlength="20">
            <input type="password" class="loginInput" id="pwd" name="userVo.password" value="" placeholder="请输入您的密码" maxlength="20">
           <input type="text" class="loginInputCode" id="checkNum" name="checkNum" placeholder="请输入验证码" maxlength="4">
            <img id="checkNumImage" src="${pageContext.request.contextPath}/image.jsp" onClick="changeCheckNum()" 
  	           title="点击换一张" class="code"></img>
            <input type="submit" class="loginInput loginButton relativeInputL" value="登录">
            <input type="reset" class="loginInput loginButton relativeInputR" value="重置">
          </div>
       </form>
      </div> 
      <!-- 登陆模块end -->
    </div>
    <!-- 内容end -->
    <!-- 底部版权 -->
    <div class="copy">Copyright（C） 2018 - <a href='http://www.sinotn.com/' target='_blank'>北京信诺软通信息技术有限公司</a>
    </div>
  </div> 
  <script type="text/javascript">
  function changeCheckNum(){
		var checkNumImage_=document.getElementById("checkNumImage");
		checkNumImage_.src="${pageContext.request.contextPath}/image.jsp?timeStamp="+new Date().getTime();
	}
  		$(function(){
  			$("#loginFromId").submit(function(){
  				if($("#username").val().trim()==""){
  					layer.alert("登录账号不可为空，请输入！",function(index){
  						layer.close(index);
  						$("#username").focus();
  					});
  					
  					return false;
  				}
  				else if($("#pwd").val().trim()==""){
  					layer.alert("密码不可为空，请输入！",function(index){
  						layer.close(index);
  						$("#pwd").focus();
  					});
  					return false;
  				}
  				else if($("#checkNum").val().trim()==""){
  					layer.alert("验证码不可为空，请输入！",function(index){
  						layer.close(index);
  						$("#checkNum").focus();
  					});
  					return false;
  				}
  	  		});
  			
  			var errorMsg = $("#errorMsg").val();
  			if(errorMsg.length>0){
  				layer.alert(errorMsg,function(index){
					layer.close(index);
					$("#username").focus();
				});
  			}
  			
  			$("#close_btn").click(function(){
  				if(confirm("是否确认退出？")){
  					ys7.CloseMe();
  					//window.location.href = "../../logout.action";
  				}
  			});
  			
  		});
	</script>
</body>
</html>
