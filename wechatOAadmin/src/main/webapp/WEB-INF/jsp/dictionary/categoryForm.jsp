<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>add category</title>
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
	<c:set var="operator" value="/category/new"/>
		<c:if test="${dictionaryCategory.id > 0 && page > 0 }">
		<c:set var="operator" value="/categorys/1" />
	</c:if>

	<jsp:include page="../include/header.jsp"/>	
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="${operator }" name="menu" />
	</jsp:include>
	<div class="content">
		<div class="alert alert-error" id="alertError" style="display: none;"></div>
		<div class="alert alert-success" id="alertSuccess" style="display: none;"></div>
		
		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">
			<c:choose>
				<c:when test="${dictionaryCategory.id > 0 && page > 0 }">修改</c:when>
				<c:otherwise>新增</c:otherwise>
			</c:choose>
			 字典分类</li>
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
					<button class="btn btn-primary" id="saveDictionaryCategory">
						<i class="icon-save"></i> 保存
					</button>
					<div class="btn-group">
						<a href="<c:url value="/categorys/1"/>" data-toggle="modal" class="btn">返回分类列表</a>
					</div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							 <form:form modelAttribute="dictionaryCategory" id="categoryForm" method="post">
							 	<input type="hidden" name="id" value="${dictionaryCategory.id}" />
								<label>分类名称</label>
								 <input type="text" id="categoryCode" name="categoryCode" value="${dictionaryCategory.categoryCode}" >
								 <span id="categoryCodeError" class="text-error"></span>
							    <label>分类描述</label>
						          <input type="text" id="categoryDesc" name="categoryDesc" value="${dictionaryCategory.categoryDesc}" >
						          <span id="categoryDescError" class="text-error"></span>
						        <label>所属区县</label>
									<input id="handleOrgId" type="hidden" name="handleOrgId" value="0">
									<c:forEach items="${areas }" var="item" varStatus="status">
										<label class="checkbox inline">
										<input type="radio" name="handleOrgId" value="${item.handleOrgId }"/>${item.areaDesc }</label>
										<c:if test="${status.index eq 7}"><br/></c:if>
									</c:forEach>
							 </form:form>
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
$(function() {

	var handleOrgId = "<c:out value='${dictionaryCategory.handleOrgId}'/>";
	$("input:radio[value="+handleOrgId+"]").attr('checked','true');
	
});

$('#saveDictionaryCategory').click(function() {

	if ($("#categoryCode").val() == "") {

		$("#categoryCodeError").html("请输入分类名称");
		return false;
	}
	if ($("#categoryDesc").val() == "") {

		$("#categoryDescError").html("请输入分类描述");
		return false;
	}
	
	var handleOrgId = $('input:radio:checked').val();
	if(handleOrgId == null){
		 $("#handleOrgId").val(0);
	}else{
		$("#handleOrgId").val(handleOrgId);
	}
    

	$('#categoryForm').submit();
	return false;
});
</script>
</html>


