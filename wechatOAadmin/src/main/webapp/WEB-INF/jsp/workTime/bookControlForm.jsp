<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>预约数修改</title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<jsp:include page="../include/headTag.jsp" />

</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->
	<c:set var="operator" value="/workTime"/>
	<c:if test="${adminUser.id > 0 && page > 0 }">
		<c:set var="operator" value="/workTimeList"/>
	</c:if>

	<jsp:include page="../include/header.jsp"/>
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/bookControls/1" name="menu"/>
	</jsp:include>
	
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">
			<c:choose>
				<c:when test="${book.id > 0}">
				修改
				</c:when>
				<c:otherwise>
				新增
				</c:otherwise>
			</c:choose>预约数
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
					<button class="btn btn-primary" id="save">
						<i class="icon-save"></i> 保存
					</button>
					<c:if test="${workTime.id gt 0}">
						<c:if test="${empty page || page == 0 }">
							<c:set var="page" value="1"/>
						</c:if>
						<a href="<c:url value="/bookControls/"/>" data-toggle="modal" class="btn">返回预约数列表</a>
					</c:if>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form:form modelAttribute="bookControl" id="basicInfoForm" method="post">
								<label>区域</label>
								<input type="text" value="${book.areaName }" readonly="readonly">
								<label>预约时间</label> 
						        <input type="text" value="${book.date}" readonly="readonly"/>
								<label>发放预约数</label> 
								<input type="text" name="members" value="${book.members}" class="input-xlarge" style="width:100px" maxlength="3"/>
								<span id="members" class="text-error"></span>
								<label>已预约数</label> 
								<input type="text" name="bookMember" value="${book.bookMember }" class="input-xlarge" style="width:100px" maxlength="3"/>
								<span id="bookMemebers" class="text-error"></span>
								
								<input type="hidden" name="id" value="${book.id }"/>
							</form:form>
						</div>
					</div>

				</div>

				<jsp:include page="../include/footer.jsp"/>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>
	<script type="text/javascript">
		$("#save").click(function(){
			
			$("#basicInfoForm").submit();
		});
	</script>
</body>
</html>