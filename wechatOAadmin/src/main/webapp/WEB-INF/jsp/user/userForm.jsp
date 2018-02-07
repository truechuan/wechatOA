<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>add user</title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="<c:url value="/resources/js/ckeditor/contents.css"/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet" type="text/css" media="screen">

		
		<jsp:include page="../include/headTag.jsp" />
		
</head>
<body class="">
	<!--<![endif]-->
	<c:set var="operator" value="/createUser"/>
	<c:if test="${user.id > 0 }">
		<c:set var="operator" value="/users/1"/>
	</c:if>

	<jsp:include page="../include/header.jsp"/>	
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/users/1" name="menu"/>
	</jsp:include>
	<div class="content">
		<div class="alert alert-error" id="alertError" style="display: none;"></div>
		<div class="alert alert-success" id="alertSuccess" style="display: none;"></div>
		
		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">编辑</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<c:if test="${not empty result }">
					<c:choose>
						<c:when test="${result eq 'success' }">
							<div class="alert alert-success">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								保存成功！
							</div>
						</c:when>
						<c:otherwise>
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								保存失败, 请检查输入项！
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div id="warning" style="display: none;"></div>
				<div class="btn-toolbar">
					<button class="btn btn-primary" id="saveUser">
						<i class="icon-save"></i> 保存
					</button>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							 <form id="userForm" method="post" method="post" action="<c:url value='/updateUser/${user.id }'/>">
							     <input type="hidden" name="userId" value="${user.id }"/> 
								<label>用户名</label>
							        <input type="text" name="username" value="${user.username }" maxlength="100" readOnly />
							    <label>真实姓名</label>
						            <input type="text" name="realName" value="${user.userInfo.realName }" maxlength="100"/>
								<label>手机号</label>
							        <input type="text" name="mobile" value="${userInfo.mobile }" maxlength="100"/>
							    <label>邮箱</label>
							        <input type="text" name="email" value="${userInfo.email }" maxlength="100"/> 
							    <label>身份证号码</label>
							        <input type="text" name="identification" value="${userInfo.identification }" maxlength="18"/>       
								<label>类型</label>
								<select name="userType">
								<option value="1" <c:if test="${user.type.value == 1}">selected</c:if>>普通用户</option>
								<option value="2" <c:if test="${user.type.value == 2}">selected</c:if>>VIP用户</option>
								</select>
								<label>状态</label>
							   		<select name="userStatus">
								   	<option value="0" <c:if test="${user.status.value == 0}">selected</c:if>>邮箱未验证</option>
									<option value="1" <c:if test="${user.status.value == 1}">selected</c:if>>正常</option>
									<option  value="2" <c:if test="${user.status.value == 2}">selected</c:if>>被锁定</option>
								    </select>				
							 </form>
						</div>
					</div>
				</div>

				<jsp:include page="../include/footer.jsp"/>
			</div>
		</div>
	</div>

</body>
<script src="<c:url value='/resources/datetimepicker/bootstrap-datetimepicker.min.js'/>"></script> 
<script src="<c:url value='/resources/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js'/>"></script>
<script type="text/javascript">
	$(function(){ 
		$('#saveUser').click(function() {
			$('#userForm').submit();
			return false;
		});
	});
</script>
</html>


