<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>类型限制列表</title>
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
	<jsp:include page="../include/header.jsp" />

	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/typeLimit/1" name="menu" />
	</jsp:include>
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span
				class="divider">/</span>
			</li>
			<li class="active">类型限制列表-定时器自动生成</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					区县：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:choose>
						<c:when test="${not empty areaCode }">

						</c:when>
						<c:otherwise>
							<c:forEach items="${areas }" var="item" varStatus="status">
								<label class="checkbox inline"> <input type="radio"
									name="radio"
									onclick="javascript:window.location.href='<c:url value="/typeLimit/${item.areaCode }"/>'" />${item.areaDesc
									}</label>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>类型限制日期</th>
								<th>限制数量</th>
								<th>已预约数量</th>
								<th>时间段</th>
								<th>类型名称</th>

								<!-- 								<th>子业务类型</th> -->
								<th style="width: 50px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${typeLimitList}" var="item" varStatus="status">
								<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
									<td>${(status.index + 1) + (pages.currentPage - 1) *
										pages.pageRecord }</td>
									<td>${item.date }</td>
									<td>${item.orderlimit }</td>
									<td>${item.orderednum }</td>
									<td>${item.time}</td>
									<td>${item.noticename }</td>
									<%-- 								<td>${item.noticeName }</td> --%>
									<td><a
										href="<c:url value="/typeLimitForm/${areaId}/${item.numlimitid }"/>"><i
											class="icon-pencil"></i>
									</a> <%-- 	              					<a href="#myModal${item.id}" role="button" data-toggle="modal"><i class="icon-remove"></i></a> --%>
										<div class="modal small hide fade"
											id="myModal${item.numlimitid}" tabindex="-1" role="dialog"
											aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">×</button>
												<h3 id="myModalLabel">确认删除</h3>
											</div>
											<div class="modal-body">
												<p class="error-text">
													<i class="icon-warning-sign modal-icon"></i> 确定删除该记录?
												</p>
											</div>
											<div class="modal-footer">
												<form id="deleteNews${item.numlimitid}" method="post"
													action="<c:url value="/delWorkTime/${item.numlimitid}"/>">
													<input type="hidden" name="page" value="1" />
													<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
													<input type="submit" class="btn btn-danger" value="删除" />
												</form>
											</div>
										</div></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<c:if test="${pages.totalPage > 0}">
						<ul>
							<c:if test="${pages.currentPage > 1}">
								<li><a href="<c:url value="/notices/${pages.prePage }"/>">Prev</a>
								</li>
							</c:if>
							<c:forEach begin="${pages.pageNumStart }"
								end="${pages.pageNumEnd }" varStatus="status">
								<li
									<c:if test="${status.index == pages.currentPage }">class="active"</c:if>>
									<a href="<c:url value="/notices/${status.index }"/>">
										${status.index } </a></li>
							</c:forEach>
							<c:if test="${pages.currentPage< pages.totalPage}">
								<li><a href="<c:url value="/notices/${pages.nextPage }"/>">Next</a>
								</li>
							</c:if>
							<li class="disabled"><a>共${pages.totalPage}页(${pages.totalRecord
									}条数据)</a>
							</li>
						</ul>
					</c:if>
				</div>
				<jsp:include page="../include/footer.jsp" />
			</div>
		</div>
	</div>
</body>
</html>


