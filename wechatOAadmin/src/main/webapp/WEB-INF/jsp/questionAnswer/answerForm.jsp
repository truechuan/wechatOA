<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>add answer</title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="<c:url value="/resources/js/ckeditor/contents.css"/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet" type="text/css" media="screen">
	
	<script src="<c:url value="/resources/js/ckeditor/ckeditor.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/js/ckfinder/ckfinder.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/js/ajaxfileupload.js"/>" type="text/javascript"></script>
		
    <jsp:include page="../include/headTag.jsp" />
		
</head>
<body class="">
	<!--<![endif]-->
	<c:set var="operator" value="/createQuestion"/>
	<c:if test="${answer.id > 0 }">
		<c:set var="operator" value="/questions/1"/>
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
			<li class="active">添加回答</li>
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
					<button class="btn btn-primary" id="saveAnswer">
						<i class="icon-save"></i> 保存
					</button>
					<div class="btn-group">
						<a href="<c:url value="/questions/1"/>" data-toggle="modal" class="btn">返回问题列表</a>
					</div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							问题描述：${question.questionName }<p>
						     <form:form modelAttribute="answer" id="answerForm" method="post">
							     <input type="hidden" name="id" value="${answer.id }"/>
							     <input type="hidden" name="questionId" value="${questionId }"/>
								<label>答案</label>
							        
							    <textarea rows="30" cols="70" id="content" name="theAnswer">${answer.theAnswer }</textarea>
								<script type="text/javascript">
									if (CKEDITOR.instances['content']) {
										CKEDITOR.remove(CKEDITOR.instances['content']);
									} //解决 例外被抛出且未被接住 问题

									var editor = CKEDITOR.replace("content",
									{
										height: '500px',
										filebrowserBrowseUrl : '<c:url value="/ckfinder/ckfinder.html"/>',
										filebrowserImageBrowseUrl : '<c:url value="/ckfinder/ckfinder.html?type=Images"/>',
										filebrowserFlashBrowseUrl : '<c:url value="/ckfinder/ckfinder.html?type=Flash"/>',
										filebrowserUploadUrl : '<c:url value="/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files"/>',
										filebrowserImageUploadUrl : '<c:url value="/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files"/>',
										filebrowserImageUploadUrl : '<c:url value="/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images"/>',
										filebrowserFlashUploadUrl : '<c:url value="/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash"/>'
									});//引号中的字符串要和文本域中name的值一致
									CKFinder.setupCKEditor(editor,
											'<c:url value="/ckfinder/"/>');
								
								</script>
								<span id="theAnswerValueError" class="text-error"></span>
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
		$("#theAnswer").focus();
		$('#saveAnswer').click(function() {
			if ($("#theAnswer").val() == "") {

				$("#theAnswerValueError").html("回答不能为空");
				return false;
			}
			$('#answerForm').submit();
			return false;
		});
	});
</script>
</html>


