<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
request.setAttribute("menu", request.getParameter("menu"));
%>
<div class="sidebar-nav" style="width:140px;">
<c:set var="activeMenu" value=""/>
<c:forEach items="${menues }" var="entry">
	<c:set var="rcategory" value="${entry.key}" />
	<a href="#${rcategory.rcategoryId}" class="nav-header collapsed" data-toggle="collapse">
		<i class="icon-chevron-right"></i>${rcategory.name}
	</a>
	
	<ul id="${rcategory.rcategoryId}" class="nav nav-list collapse">
	<c:forEach items="${menues[rcategory] }" var="item">
		<li <c:if test="${menu eq item.url }">
				<c:out value="class=active"/>
				<c:set var="activeMenu" value="${rcategory.rcategoryId}"/>
			</c:if>>
			<a href="<c:url value="${item.url }"/>">
				${item.description }
				<c:if test="${item.url eq '/attests/1' && waitAttestCount > 0}">
				<span class="label label-info pull-right">+${waitAttestCount }</span>
				</c:if>
			</a>
		</li>	
	</c:forEach>
	</ul>
</c:forEach>
</div>
<script type="text/javascript">
	$(function(){ 
		var activeMenu = '${activeMenu}';
		if(activeMenu != "" && activeMenu != null){
			$("#"+activeMenu).attr("class","nav nav-list collapse in");
		}else{
			$(".nav-list:first").attr("class","nav nav-list collapse in");
		}
	});
</script>