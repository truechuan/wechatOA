<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="description"  content="" />
    <meta name="keywords"  content=""/>
    <%@ include file="../include/commoncss.jsp"%>
</head>
<body id="proessList">
	<div class="top">
		<div class="col-xs-3">
			<a href="javascript:history.go(-1);">
				<div class="leftArrow"></div>
			</a>
		</div>
		<div class="col-xs-6">系统使用说明</div>
	</div>
	<c:forEach items="${notices }" var="item" varStatus="status">
	<c:if test="${item.dictionaryValue eq '使用说明' }">
	<dl>
		<dt>${item.dictionaryValue }</dt>
		<c:forEach items="${item.entityList }" var="notice" varStatus="vStatus">
		<dd>
			<a href="<c:url value='/static/${notice.id }/noticeInfo.html'/>"><p>${vStatus.index + 1}. ${notice.noticeName }</p><div class="rightArrow"></div></a>
		</dd>
		</c:forEach>
	</dl>
	</c:if>
	</c:forEach>
	<%@ include file="../include/footer.jsp"%>
	<%@ include file="../include/commonjs.jsp"%>
</body>
</html>