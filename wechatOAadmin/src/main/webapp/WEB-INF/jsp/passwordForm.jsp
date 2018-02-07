<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>HFB Admin</title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<jsp:include page="include/headTag.jsp" />

</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->

	<jsp:include page="include/header.jsp"/>
	
	<jsp:include page="include/menues.jsp">
		<jsp:param value="/password" name="menu"/>
	</jsp:include>
	
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">
				修改密码
			</li>
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
								<c:choose>
									<c:when test="${not empty message}">
										${message }
									</c:when>
									<c:otherwise>
										保存失败, 请检查输入项！
									</c:otherwise>
								</c:choose>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div class="btn-toolbar">
					<button class="btn btn-primary" id="saveAdminUser">
						<i class="icon-save"></i> 保存
					</button>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="basicInfoForm" method="post">
								<label>原密码</label> 
								<input type="password" id="oldPassword" name="oldPassword" class="input-xlarge"/>
								<span id="oldPasswordError" class="text-error"></span>
								
								<label>新密码</label> 
								<input type="password" id="newPassword" name="newPassword" placeholder="输入您的新密码" class="input-xlarge"/>
								<label>确认新密码</label> 
								<input type="password" id="newPasswordConfirm" name="newPasswordConfirm" placeholder="重复输入新密码" class="input-xlarge"/>
								<span id="newPasswordConfirmError" class="text-error"></span>
							</form>
						</div>
					</div>

				</div>

				<jsp:include page="include/footer.jsp"/>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			
			$('#saveAdminUser').click(function() {
				if($("#oldPassword").val() == ""){
					
					$("#oldPasswordError").html("请输入原密码");
					return false;
				}
				
				if($("#newPassword").val() != $("#newPasswordConfirm").val()){
					
					$("#newPasswordConfirmError").html("两次输入的密码不相同");
					return false;
				}
				
				$('#basicInfoForm').submit();
				return false;
			});
		});
	</script>
</body>
</html>