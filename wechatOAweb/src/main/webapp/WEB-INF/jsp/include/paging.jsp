<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="clear inv-pagedown text-center">
	<c:if test="${totalPage > 10 }">
		<span><a
			href="<c:url value='/static/1/news.htm'/>">首页</a></span>
	</c:if>
	<c:if test="${currentPage > 1 }">
		<span><a
			href="<c:url value='/static/${currentPage - 1 }/news.htm'/>">上一页</a></span>
	</c:if>
	<c:forEach var="i" begin="1" end="${totalPage }" step="1">
		<c:if test="${(currentPage <= 5 && i <= 10) ||
			(currentPage > totalPage - 5 && i > totalPage - 10)||
			(i >= currentPage - 4 && i <= currentPage + 5 && currentPage > 5)}">
			<span><a href="<c:url value='/static/${i}/news.htm'/>"
			<c:if test="${i eq currentPage }">class="occur"</c:if>>${i }</a></span>
		</c:if>				
	</c:forEach>
	<c:if test="${currentPage < totalPage }">
		<span><a
			href="<c:url value='/static/${currentPage + 1 }/news.htm'/>">下一页</a></span>
	</c:if>
	<c:if test="${totalPage > 10 }">
		<span><a
			href="<c:url value='/static/${totalPage }/news.htm'/>">末页</a></span>
	</c:if>
</div>
