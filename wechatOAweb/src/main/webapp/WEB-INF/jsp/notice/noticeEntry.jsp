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
<body id="proessGuide">
	<div class="top">
		<div class="col-xs-3">
			<a href="javascript:history.go(-1);">
				<div class="leftArrow"></div>
			</a>
		</div>
		<div class="col-xs-6">分类指引</div>
	</div>
	<div class="contentBtn">
		<a href="<c:url value='/static/instructions.html'/>">
			<img src="<c:url value='/resources/img/proessGuideIco1.png'/>">
		</a>
	</div>
	<div class="contentBtn">
		<a href="<c:url value='/static/notices.html'/>">
			<img src="<c:url value='/resources/img/proessGuideIco2.png'/>">
		</a>
	</div>
	<div class="contentBtn">
		<a href="<c:url value='/static/questions/0.html'/>">
			<img src="<c:url value='/resources/img/proessGuideIco3.png'/>">
		</a>
	</div>
	<%@ include file="../include/footer.jsp"%>
	<%@ include file="../include/commonjs.jsp"%>
</body>
</html>