<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>天津市不动产 - Admin</title>
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
		<jsp:param value="/adminUsers/1" name="menu"/>
	</jsp:include>

	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">后台用户列表</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar">
					<a href="<c:url value="/createAdminUser"/>" class="btn btn-primary">
						<i class="icon-plus"></i>添加后台用户
					</a>
				<!-- 	<button class="btn">导入</button>
					<button class="btn">导出</button> -->
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>登录名</th>
								<th>真实姓名</th>
								<th>电话</th>
								<th>创建时间</th>
								<th>更新时间</th>
								<th>所属角色</th>
								<th>状态</th>
								<th style="width: 30px;">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${adminUses}" var="adminUser" varStatus="status">
							<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
								<td>${(status.index + 1) + (pages.currentPage - 1) * pages.pageRecord}</td>
								<td>${adminUser.loginName }</td>
								<td>${adminUser.realName }</td>
								<td>${adminUser.phone }</td>
								<td>${adminUser.createTime }</td>
								<td>${adminUser.updateTime }</td>
								<td>
								<c:forEach items="${adminUser.groups }" var="group" end="4">
									${group.name }
									<br/>
								</c:forEach>
								<c:if test="${fn:length(adminUser.groups) > 5 }">
									<a href="#adminUserGroup${adminUser.id }" data-toggle="collapse">更多</a>
									<div id="adminUserGroup${adminUser.id }" class="collapse">
										<c:forEach items="${adminUser.groups }" var="group" begin="5">
											${group.name }
											<br/>
										</c:forEach>
									</div>
								</c:if>
								</td>
								<td>
									disable: 
									<c:choose>
										<c:when test="${adminUser.userStatus eq '1' }">
											是
										</c:when>
										<c:otherwise>
											否
										</c:otherwise>
									</c:choose> 
								</td>
								<td>
									<c:if test="${adminUser.loginName != superAdminLoginName }">
									<a href="<c:url value="/adminUser/${adminUser.id}/edit/${pages.currentPage}"/>"><i class="icon-pencil"></i></a>
	              					<%-- <a href="#myModal${adminUser.id}" role="button" data-toggle="modal"><i class="icon-remove"></i></a> --%>
	              					<div class="modal small hide fade" id="myModal${adminUser.id}" tabindex="-1"
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
			              					<form id="deleteForm${adminUser.id}" method="post" action="<c:url value="/adminUser/${adminUser.id}/delete"/>">
			              						<input type="hidden" name="page" value="${pages.currentPage }"/>
												<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
												<input type="submit" class="btn btn-danger" value="删除"/>
			              					</form>
										</div>
									</div>
									</c:if>
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
						<li><a href="<c:url value="/adminUsers/${pages.prePage }"/>">Prev</a></li>
					</c:if>
					<c:forEach begin="${pages.pageNumStart }" end="${pages.pageNumEnd }" varStatus="status">
						<li <c:if test="${status.index == pages.currentPage }">class="active"</c:if>>
							<a href="<c:url value="/adminUsers/${status.index }"/>">
								${status.index }
							</a>
						</li>
					</c:forEach>
					<c:if test="${pages.currentPage< pages.totalPage}">
						<li><a href="<c:url value="/adminUsers/${pages.nextPage }"/>">Next</a></li>
					</c:if>
						<li class="disabled"><a>共${pages.totalPage}页(${pages.totalRecord }条数据)</a></li>
					</ul>
				</c:if>
				</div>

				<jsp:include page="include/footer.jsp"/>

			</div>
		</div>
	</div>
</body>
</html>


