<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>天津市不动产 - Admin</title>
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
	<c:set var="operator" value="/rcategory/new"/>
	<c:if test="${resourceCategory.rcategoryId > 0 && page > 0 }">
		<c:set var="operator" value="/rcategories/1"/>
	</c:if>

	<jsp:include page="../include/header.jsp"/>
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="${operator }" name="menu"/>
	</jsp:include>
	
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">
			<c:choose>
				<c:when test="${resourceCategory.rcategoryId > 0 && page > 0 }">
				修改
				</c:when>
				<c:otherwise>
				新增
				</c:otherwise>
			</c:choose>
			URL分类</li>
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
					<button class="btn btn-primary" id="saveRcategory">
						<i class="icon-save"></i> 保存
					</button>
					<c:if test="${resourceCategory.rcategoryId > 0}">
						<a href="<c:url value="/rcategory/new"/>" data-toggle="modal" class="btn">添加URL分类</a>
						<c:if test="${empty page || page == 0 }">
							<c:set var="page" value="1"/>
						</c:if>
						<a href="<c:url value="/rcategories/${page}"/>" data-toggle="modal" class="btn">返回URL分类列表</a>
					</c:if>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form:form modelAttribute="resourceCategory" id="rcategoryForm" method="post">
								<label>名称</label> 
								<input type="text" id="name" name="name" value="${resourceCategory.name}" class="input-xlarge"> 
								<span id="nameError" class="text-error"></span>
						        <label>包含URL</label>
						        <c:forEach items="${allResources }" var="resource" varStatus="status">
						        	<label class="checkbox inline">
						        	<form:checkbox path="resources" value="${resource.resourceId },${resource.url }"/>${resource.description }-${resource.url }
						        	</label>
						        	<c:if test="${status.count%5==0 }"><br/></c:if>
						        </c:forEach>
						        <input type="hidden" name="id" value="${resourceCategory.rcategoryId }"/>
							</form:form>
						</div>
					</div>

				</div>

				<jsp:include page="../include/footer.jsp"/>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
		$(function() {
			
			$('#saveRcategory').click(function() {
				
				if($("#name").val() == ""){
					
					$("#nameError").html("请输入URL分类名称");
					return false;
				}
				
				$('#rcategoryForm').submit();
				return false;
			});
		});
	</script>
</body>
</html>