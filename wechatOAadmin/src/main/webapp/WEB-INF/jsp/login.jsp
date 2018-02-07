<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
<meta charset="utf-8">
<title>天津市不动产 -Admin</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<jsp:include page="include/headTag.jsp"/>

</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->

	<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">

			</ul>
			<a class="brand" href="javascript:void(0);">
			<span class="first">天津市不动产登记中心</span>
			<span class="second">后台管理系统</span></a>
		</div>
	</div>

	<div class="row-fluid">
		<div class="dialog">
			<div class="block">
				<p class="block-heading">管理员登录</p>
				<c:if test="${not empty error}">
					<div class="alert alert-error">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${error }
					</div>
				</c:if>
				<div class="block-body">
					<form action='<c:url value="/j_spring_security_check"/>' method="POST">
						<label>用户名</label> <input type="text" class="span12" name="j_username">
						<label>密码</label> <input type="password" class="span12" name="j_password">
						<input class="btn btn-primary pull-right" type="submit" name="submit" value="登录"/>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


