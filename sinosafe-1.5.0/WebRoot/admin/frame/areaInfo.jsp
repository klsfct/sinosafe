<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<s:if test="#session.loginSession.areaList.size > 1">
	<div class="areaTop" style="padding: 5px;background-color: #FFFFFF;">
		<s:subset source="#session.loginSession.areaList" start="0" count="1" >
			<s:iterator status="st1">
				<span class='font_bold red'><a href="javascript:setArea('<s:property value="id" />')";><s:property value="name"/></a></span>
			</s:iterator>
		</s:subset>
		<s:subset source="#session.loginSession.areaList" start="1">
			<s:iterator>
				<s:if test="isEnabled==true">
				|&nbsp;<a href="javascript:setArea('<s:property value="id" />')"><s:property value="name"/></a>
				</s:if>
			</s:iterator>
		</s:subset>
	</div>
</s:if>