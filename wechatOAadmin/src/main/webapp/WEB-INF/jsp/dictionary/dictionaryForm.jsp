<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>add dictionary</title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="<c:url value="/resources/js/ckeditor/contents.css"/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet" type="text/css" media="screen">

		
		<jsp:include page="../include/headTag.jsp" />
		
</head>
<body class="">
	
	<c:set var="operator" value="/dictionary/new"/>
	<c:if test="${dictionary.id > 0 && page > 0 }">
	<c:set var="operator" value="/dictionarys/1" />
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
				 <c:when test="${dictionary.id > 0 && page > 0 }">修改</c:when>
				 <c:otherwise>新增</c:otherwise>
			</c:choose>
			字典值</li>
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
					<button class="btn btn-primary" id="saveDictionary">
						<i class="icon-save"></i> 保存
					</button>
					<div class="btn-group">
						<a href="<c:url value="/dictionarys/1"/>" data-toggle="modal" class="btn">返回字典列表</a>
					</div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							 <form:form modelAttribute="dictionary" id="dictionaryForm" method="post">
							 	<input type="hidden" name="id" value="${dictionary.id}" />
								<label>所属分类</label>
								 <select name="dictionaryCategoryid" class="input-xlarge">
								 <c:forEach items="${dictionaryCategorys}" var="dictionaryCategory">
									<option value="${dictionaryCategory.id}" <c:if test="${dictionaryCategory.id==dictionary.dictionaryCategoryid}">selected</c:if>>
										${dictionaryCategory.categoryDesc} -
										<c:choose> 
										  <c:when test="${dictionaryCategory.handleOrgId eq 1034}">市区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1035}">河北区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1037}">静海区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1038}">和平区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1039}">南开区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1040}">河东区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1041}">河西区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1042}">红桥区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1043}">东丽区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1044}">西青区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1045}">北辰区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1046}">津南区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1047}">武清区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1048}">宝坻区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1049}">宁河区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1050}">蓟州区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1051}">滨海区</c:when>
										  
										  <c:when test="${dictionaryCategory.handleOrgId eq 1101}">滨海第一中心</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1102}">滨海第二中心</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1103}">滨海第三中心</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1104}">滨海第四中心</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1105}">开发区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1106}">高新区</c:when>
										  <c:when test="${dictionaryCategory.handleOrgId eq 1108}">东疆港</c:when>
										  <c:otherwise>无</c:otherwise>
										</c:choose>
									</option>
								  </c:forEach>
							      </select>
							    <label>字典值</label>
						          <input type="text" id="dictionaryValue" name="dictionaryValue" value="${dictionary.dictionaryValue}" class="input-xlarge">
						          <span id="dictionaryValueError" class="text-error"></span>
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

		$('#saveDictionary').click(function() {

			if ($("#dictionaryValue").val() == "") {

				$("#dictionaryValueError").html("字典值不能为空");
				return false;
			}

			$('#dictionaryForm').submit();
			return false;
		});
	});
</script>
</html>


