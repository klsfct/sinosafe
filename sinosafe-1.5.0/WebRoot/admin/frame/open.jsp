<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title></title> <!-- Le styles -->
	<link href="../libs/js/open/bootstrap-combined.min.css" rel="stylesheet">
	<link href="../libs/js/open/layoutit.css" rel="stylesheet">
	<script type="text/javascript" src="../libs/js/open/jquery-2.0.0.min.js"></script>
	<script type="text/javascript" src="../libs/js/open/bootstrap.min.js"></script>
	<script type="text/javascript" src="../libs/js/open/scripts.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6">
				<h3>欢迎，${loginSession.principal}</h3>
				<table class="table table-hover table-bordered">
					<tbody>
						<tr>
							<td>用户ID</td>
							<td>${loginSession.userId}</td>
						</tr>
						<tr class="success">
							<td>用户名</td>
							<td>${loginSession.principal}</td>
						</tr>
						<tr class="error">
							<td>所属考区</td>
							<td>${loginSession.areaName}</td>
						</tr>
						<tr class="warning">
							<td>上次登录时间</td>
							<td>${loginSession.loginDate}</td>
						</tr>
						<tr class="info">
							<td>上次登录IP</td>
							<td>${loginSession.loginIp}</td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td>${loginSession.mobile}</td>
						</tr>
					</tbody>
				</table>
				<div class="alert">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<h4>提示!</h4>
					<strong>警告!</strong> 请注意你的个人隐私安全.
				</div>
				<div class="carousel slide" id="carousel-794946">
					<ol class="carousel-indicators">
						<li data-slide-to="0" data-target="#carousel-794946"
							class="active"></li>
						<li data-slide-to="1" data-target="#carousel-794946"></li>
						<li data-slide-to="2" data-target="#carousel-794946"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img alt="" src="./images/slider-1.jpg" />
							<div class="carousel-caption">
								<h4>考试安全</h4>
								<p>
									采用国密级的安全认证体系与安全支撑云平台技术，结合数字加密技术、人像识别、RFID技术为考试全程提供信息安全、考生身份识别、试卷追溯和回收等考试安全保障。
								</p>
							</div>
						</div>
						<div class="item">
							<img alt="" src="./images/slider-2.jpg" />
							<div class="carousel-caption">
								<h4>安全云平台</h4>
								<p>
									为用户提供数据接收、信息比对、统计分析、指令下发、异常上报等信息服务，提供视频监控接入服务。通过数据级联方式将考点、考区、中心管理进行实时数据级联，信息实现实时共享，有效防止考点集体舞弊现象的发生。
								</p>
							</div>
						</div>
						<div class="item">
							<img alt="" src="./images/slider-3.jpg" />
							<div class="carousel-caption">
								<h4>安全业务系统</h4>
								<p>
									安全智能终端（人像安全U盾）对进场考生进行身份信息的安检核验，同时通过云平台将考生、试卷、资格证管理进行绑定，实现了从考点到后台的全程安全管理。
								</p>
							</div>
						</div>
					</div>
					<a class="left carousel-control" data-slide="prev"
						href="#carousel-794946">‹</a> <a class="right carousel-control"
						data-slide="next" href="#carousel-794946">›</a>
				</div>
			</div>
			<div class="span6">
				<div class="hero-unit">
				<s:if test="#session.loginSession.level == 4 ">
					<h2>您可以在这里下载相关文件</h2>
					<p>
						考生数据包是整个考务安全平台的核心数据，为此提供下载：人像识别客户端、人工核查客户端以及相应的操作手册和工具！可以点击下面相应的按钮进行下载。
					</p>
					<p>
						<a class="btn btn-primary btn-large"  href="#"<%-- href="${basePath}/admin/download/document/redBook.docx" --%>>用户操作手册</a>
						<a class="btn btn-primary btn-large" href="${basePath}/admin/download/tools/keydisk.zip">U盾数据拷贝工具</a><br/><br/>
						<a class="btn btn-primary btn-large" href="${basePath}/admin/download/face/FaceClient-${loginSession.userId}.exe">人像识别客户端</a>
						<a class="btn btn-primary btn-large" href="${basePath}/admin/download/hand/HandClient-${loginSession.userId}.exe">人工核查客户端</a><br/><br/>
						
					</p>
					
				</s:if>
				<s:else>
					<h2>信诺软通</h2>
					<h3>专注于考试和教育服务领域,与客户携手开启数字考试新时代</h3>
					<p>
						北京信诺软通信息技术有限公司是一家专注于使用“大数据 + 移动互联网（4G）+ 物联网技术”的新兴互联网高新技术企业，企业为国家部委、行业协会、大型企业提供考试测评和数字化培训服务，涵盖考试组织事务服务、考试安全服务、现代无纸化计算机考试服务（机考服务）、考前自测和数字化培训等服务。
					</p>
				</s:else>
					
				</div>
				<s:if test="#session.loginSession.level == 4 ">
				<div class="alert alert-error">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong>警告!</strong> 请注意保护数据包的信息隐私安全！故意泄露将承担相应的法律责任！
				</div>
				</s:if>
				<s:else>
					<HR style="border:3 double #987cb9" width="80%" color=#987cb9 SIZE=3>
				</s:else>
				
				
				<div class="hero-unit">
					<h3>遇到问题怎么办？</h3>
					<p>云平台技术问题，联系我们：</p>
					<p>邮箱：libin@sinotn.com</p>
					<p>电话：17610879900</p>
					<p>时间：工作日9:00-18:00</p>

				</div>
			</div>
		</div>
	</div>
</body>
</html>

