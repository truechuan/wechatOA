<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav pull-right">

			<li>
				<a class="hidden-phone visible-tablet visible-desktop" role="button" href="<c:url value='/user/welcome.htm'/>">
					<i class="icon-home"></i>后台首页
				</a>
			</li>
			<li id="fat-menu" class="dropdown">
				<a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
					<i class="icon-user"></i> <sec:authentication property="name"/>
					<i class="icon-caret-down"></i>
				</a>

				<ul class="dropdown-menu">
					<li><a tabindex="-1" href="<c:url value="/password"/>">修改密码</a></li>
					<li class="divider"></li>
					<li><a tabindex="-1" href="<c:url value="/j_spring_security_logout"/>">退出</a></li>
				</ul>
			</li>
		</ul>
		<a class="brand" href="javascript:void(0);">
			<span class="first">天津市不动产</span>
			<span class="second">后台管理</span></a>
	</div>
</div>