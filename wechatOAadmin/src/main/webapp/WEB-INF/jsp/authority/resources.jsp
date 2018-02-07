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

	<jsp:include page="../include/headTag.jsp" />

</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->

	<jsp:include page="../include/header.jsp"/>	
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/urls/1" name="menu"/>
	</jsp:include>

	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">URL列表</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar">
					<a href="<c:url value="/url/new"/>" class="btn btn-primary">
						<i class="icon-plus"></i>添加URL
					</a>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>URL</th>
								<th>描述</th>
								<th>菜单栏显示</th>
								<th style="width: 30px;">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${resources}" var="resource" varStatus="status">
							<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
								<td>${(status.index + 1) + (pages.currentPage - 1) * pages.pageRecord}</td>
								<td>${resource.url }</td>
								<td>${resource.description }</td>
								<td>
								<c:choose>
									<c:when test="${resource.menu }">
										<span class="text-info">是</span>
									</c:when>
									<c:otherwise>
										否
									</c:otherwise>
								</c:choose>
								</td>
								<td>
									<a href="<c:url value="/url/${resource.resourceId}/edit/${pages.currentPage }"/>"><i class="icon-pencil"></i></a>
	              					<a href="#myModal${resource.resourceId}" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
	              					<div class="modal small hide fade" id="myModal${resource.resourceId}" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h3 id="myModalLabel">确认删除</h3>
										</div>
										<div class="modal-body">
											<p class="error-text">
												<i class="icon-warning-sign modal-icon"></i>
												确定删除该用户?
											</p>
										</div>
										<div class="modal-footer">
			              					<form id="deleteForm${resource.resourceId}" method="post" action="<c:url value="/url/${resource.resourceId}/delete"/>">
			              						<input type="hidden" name="page" value="${pages.currentPage }"/>
												<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
												<input type="submit" class="btn btn-danger" value="删除"/>
			              					</form>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
				<c:if test="${pages.totalPage > 0}">
					<ul>
					<c:if test="${pages.currentPage > 1}">
						<li><a href="<c:url value="/urls/${pages.prePage }"/>">Prev</a></li>
					</c:if>
					<c:forEach begin="${pages.pageNumStart }" end="${pages.pageNumEnd }" varStatus="status">
						<li <c:if test="${status.index == pages.currentPage }">class="active"</c:if>>
							<a href="<c:url value="/urls/${status.index }"/>">
								${status.index }
							</a>
						</li>
					</c:forEach>
					<c:if test="${pages.currentPage< pages.totalPage}">
						<li><a href="<c:url value="/urls/${pages.nextPage }"/>">Next</a></li>
					</c:if>
						<li class="disabled"><a>共${pages.totalPage}页(${pages.totalRecord }条数据)</a></li>
					</ul>
				</c:if>
				</div>

				<jsp:include page="../include/footer.jsp"/>

			</div>
		</div>
	</div>
</body>
</html>


