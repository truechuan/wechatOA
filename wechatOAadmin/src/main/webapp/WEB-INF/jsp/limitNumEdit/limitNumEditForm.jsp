<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<c:set var="operator" value="/limitNumEdit/1" />
	<c:if test="${noticeViewType.noticeViewTimeId > 0 && page > 0 }">
		<c:set var="operator" value="/limitNumEdit/1" />
	</c:if>

	<jsp:include page="../include/header.jsp" />

	<jsp:include page="../include/menues.jsp">
		<jsp:param value="${operator }" name="menu" />
	</jsp:include>

	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span
				class="divider">/</span>
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
					<button class="btn btn-primary" id="saveLimitNum">
						<i class="icon-save"></i> 保存
					</button>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form:form modelAttribute="noticeViewType" id="basicInfoForm"
								method="post" onkeydown="if(event.keyCode==13)return false;">
								<label>限制数数目</label>
								<input type="text" name="limitNum"
									value="${noticeViewType.limitNum}" class="input-xlarge"
									style="width:100px" maxlength="3" />
								<span id="limitNum" class="text-error"></span>
							</form:form>
						</div>
					</div>

				</div>

				<jsp:include page="../include/footer.jsp" />
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {

		$('#saveLimitNum').click(function() {
			$(".text-error").html("");
			var limitNum = $("input[name='limitNum']").val();
			if (limitNum == "") {

				$("#limitNum").html("请输入限制数数目");
				return false;
			} else {
				var match = /^[1-9][0-9]*[0-9]*$/;
				if (!match.test(limitNum)) {
					$("#limitNum").html("请正确填写限制数数目");
					return false;
				}
			}

			$('#basicInfoForm').submit();
			return false;
		});
	});
</script>
</html>