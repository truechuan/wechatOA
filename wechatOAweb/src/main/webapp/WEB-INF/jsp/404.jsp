<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link href="resources/images/favicon.ico" rel="SHORTCUT ICON"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>路径不存在 </title>
</head>
<style>
.no_404{width:607px; margin:100px auto; background:url(resources/images/404.png) no-repeat; height:607px;}
.no_404 a{display:block; width:100%; text-align:center; color:#daecee; font-size:14px; line-height:20px; padding-top:570px; text-decoration:none;}
.no_404 a:hover{ text-decoration:underline;}
</style>
<body style=" background-color:#daecee;">
	
<div class="no_404">
	<a href="<c:url value='/'/>">返回首页</a>
</div>
</body>
</html>
