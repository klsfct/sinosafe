<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<div style="height:40px;">
	<div class="float_left padding5">
		<div class="pageNumber"
			total="<s:property value="pageResultList[0].page.totalCount"/>"
			pageSize="<s:property value="pageResultList[0].page.everyPage"/>"
			showSelect="true" showInput="true" id="page-7"
			page="<s:property value="pageResultList[0].page.currentPage"/>"></div>
	</div>
	<div class="float_right padding5">
		共
		<s:property value="pageResultList[0].page.totalCount" />
		条记录
	</div>
	<div class="clear"></div>
</div>
<s:form id="pageQueryId" name="pageQuery" method="post">
	<s:hidden id="currentPageId" name="pageParam[0].currentPage"></s:hidden>
	<s:hidden id="everyPageId" name="pageParam[0].everyPage"></s:hidden>
	<s:hidden id="orderById" name="pageParam[0].orderBy"></s:hidden>
	<s:hidden id="conditionId" name="pageParam[0].condition"></s:hidden>
	<s:hidden name="areaId"></s:hidden>
</s:form>