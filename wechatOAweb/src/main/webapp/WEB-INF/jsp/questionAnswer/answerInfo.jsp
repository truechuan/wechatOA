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
<body id="noticeInfo">
	<div class="top">
		<div class="col-xs-3">
			<a  href="javascript:history.go(-1);">
				<div class="leftArrow"></div>
			</a>
		</div>
		<div class="col-xs-6">问答详情</div>
	</div>
	<div class="info">
	<p class="title" style="color:rgb(95, 156, 239);font-weight: inherit;font-family: inherit;font-size: 137.5%;box-sizing: border-box;">${question.questionName }</p>
<%-- 	<p class="date">${announcementInfo.createTime }</p> --%>
	<p class="content" style="word-spacing:8px; letter-spacing: 1px;color:#939393;font-size: 120%;">
	<c:if test="${empty answer.theAnswer }">暂无答案</c:if>
	<c:if test="${not empty answer.theAnswer }">${answer.theAnswer }</c:if>
	</p>
	</div>
	
	<%@ include file="../include/footer.jsp"%>
</body>
</html>