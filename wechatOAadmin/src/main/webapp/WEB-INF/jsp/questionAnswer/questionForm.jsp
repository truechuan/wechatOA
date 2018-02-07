<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>add question</title>
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
	<c:set var="operator" value="/createQuestion"/>
	<c:if test="${question.id > 0 }">
		<c:set var="operator" value="/questions/1/1"/>
	</c:if>

	<jsp:include page="../include/header.jsp"/>	
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/question/new" name="menu"/>
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
								保存失败, ${message }
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div id="warning" style="display: none;"></div>
				<div class="btn-toolbar">
					<button class="btn btn-primary" id="saveQuestion">
						<i class="icon-save"></i> 保存
					</button>
					<div class="btn-group">
						<a href="<c:url value="/questions/1"/>" data-toggle="modal" class="btn">返回问题列表</a>
					</div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
						     <form:form modelAttribute="question" id="questionForm" method="post">
							     <input type="hidden" name="id" value="${question.id }"/> 
								<label>问题描述</label>
							        <input type="text" name="questionName" value="${question.questionName }" maxlength="100" />
							        <span id="questionNameValueError" class="text-error"></span>
							    <label>状态</label>
						           <select name="status">
										<option value="0" <c:if test="${question.status.value == 0}">selected</c:if>>显示</option>
										<option value="1" <c:if test="${question.status.value == 1}">selected</c:if>>不显示</option>
								    </select>
								<label>问题类型</label>
							        <select name="questionTypeId">
							        	<c:forEach items="${questionTypes }" var="item">
										<option value="${item.id }" <c:if test="${item.id == question.questionTypeId}">selected</c:if>>${item.dictionaryValue }</option>
										</c:forEach>
								    </select>
								<c:if test="${isMoreManger eq 'true' }">
								    <label>所属区县</label>
									<input id="area" type="hidden" name="area" value="1">
									<c:forEach items="${areas }" var="item" varStatus="status">
										<label class="checkbox inline"><input type="checkbox" name="box" value="${item.areaCode }"/>${item.areaDesc }</label>
										<c:if test="${status.index eq 8}"><br/></c:if>
									</c:forEach>
								</c:if>
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
	$(function(){ 
		
		var areaValue = "<c:out value='${question.area}'/>";
		var areaTemp = areaValue.split(',');
		for(var i = 0; i < areaTemp.length; i++){
			$("input:checkbox[value='"+areaTemp[i]+"']").attr('checked','true');
		}; 
	});
	
	$('#saveQuestion').click(function() {
		if ($("#questionName").val() == "") {

			$("#questionNameValueError").html("问题描述不能为空");
			return false;
		}
		
		var areaTemp = "";
	    $('input:checkbox[name=box]:checked').each(function(i){
	       if(0==i){
	    	   areaTemp = $(this).val();
	       }else{
	    	   areaTemp += (","+$(this).val());
	       }
	    });
	    
	    var isMoreManger = "<c:url value='${isMoreManger}'/>";
	    
	    var areaLength = $("input[name=box]:checked").length; 
	  	if(isMoreManger != 'false' && isMoreManger != "" && areaLength != 1){
	  		alert("只能选择一个所属区县");
	  		return;
	  	}
	    
	    $("#area").val(areaTemp);
	    
		$('#questionForm').submit();
		return false;
	});
</script>
</html>


