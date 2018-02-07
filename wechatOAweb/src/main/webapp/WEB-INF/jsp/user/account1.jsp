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
<body id="userInfo">
	
	<div class="top">
		<div class="col-xs-3">
			<a  href="javascript:history.go(-1);">
				<div class="leftArrow"></div>
			</a>
		</div>
		<div class="col-xs-6">个人中心</div>
	</div>
	<div class="content">
		<p><img src="<c:url value='/resources/img/titleIco1.png'/>">个人信息</p>
		<a href="javascript:void(0);"><span class="col-xs-4">姓名</span><span class="col-xs-8">${user.name }</span></a>
		<a href="javascript:void(0);"><span class="col-xs-4">身份证号</span><span class="col-xs-8">${user.idcard }</span></a>
		<a href="javascript:void(0);"><span class="col-xs-4">移动电话</span><span class="col-xs-8">${user.mobile }</span></a>
<!-- 		<a href="javascript:void(0);"><span class="col-xs-4">状态</span><span class="col-xs-8"> -->
<!-- 		<c:if test="${not empty user }"> -->
<!-- 			<c:if test="${user.status eq 0}"><span style="color:blue;">审核中</span></c:if> -->
<!-- 			<c:if test="${user.status eq 1}"><span style="color:green;">审核通过</span></c:if> -->
<!-- 			<c:if test="${user.status eq 2}"><span style="color:red;">未通过，请修改您的信息</span></c:if> -->
<!-- 		</c:if></span></a> -->
<!-- 		<div class="col-xs-6"> -->
<%-- 			<img src="${user.idcardImgUrl }" style="height:100px;"> --%>
<!-- 			<p class="text-center">身份证正面</p> -->
<!-- 		</div> -->
<!-- 		<div class="col-xs-6"> -->
<%-- 			<img src="${user.idcardImgUrl2 }" style="height:100px;"> --%>
<!-- 			<p class="text-center">身份证背面</p> -->
<!-- 		</div> -->
	</div>
	<p><br/>
	<div class="bottomBtn Account">
		<a href="<c:url value='/static/myOrder/${user.openid }'/>">我的预约</a>
	</div>
	<div class="bottomBtn">
		<a style="background:#7cc88e;" href="<c:url value='/static/register'/>">修改个人信息</a>
	</div>
	<%@include file="../include/footer.jsp" %>	
<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

</body>
</html>