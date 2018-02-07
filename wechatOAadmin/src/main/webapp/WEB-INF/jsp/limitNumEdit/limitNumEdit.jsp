<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>修改限制数数目列表</title>
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
		<jsp:param value="/limitNumEdit/1" name="menu" />
	</jsp:include>
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span
				class="divider">/</span>
			</li>
			<li class="active">修改限制数数目 &nbsp;&nbsp;&nbsp;</li>

		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					<form class="form-search" method="post" id="setchInfo"
						action="<c:url value='/limitNumEdit/1' />">
						办理区域：<input type="text" name="areaName" value="${areaName }" />
						子业务类型：<input type="text" name="noticeName" value="${noticeName }" />
						</select>
						<button type="submit" class="btn" id="submitSearch">查找</button>
					</form>
					<div style="display:none;">${pages.currentPage}</div>
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>办理区域</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>子业务类型</th>
								<th>限制数数目</th>
								<!-- 								<th>子业务类型</th> -->
								<th style="width: 50px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${arrItem}" var="item" varStatus="status">
								<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
									<td>${(status.index + 1) + (pages.currentPage - 1) *
										pages.pageRecord }</td>
									<td>${item.areaName }</td>
									<td>${item.startTime }</td>
									<td>${item.endTime }</td>
									<td>${item.noticeName }</td>
									<td>${item.limitNum }</td>
									<td><a
										href="<c:url value="/updateLimitNum/${item.noticeViewTimeId}"/>"><i
											class="icon-pencil"></i> </a>
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
								<li><a href="javascript:void(0);"
									onclick="tijiao('${pages.prePage }')">Prev</a>
								</li>
							</c:if>
							<c:forEach begin="${pages.pageNumStart }"
								end="${pages.pageNumEnd }" varStatus="status">
								<li
									<c:if test="${status.index == pages.currentPage }">class="active"</c:if>>
									<a href="javascript:void(0);"
									onclick="tijiao('${status.index }')"> ${status.index } </a></li>
							</c:forEach>
							<c:if test="${pages.currentPage< pages.totalPage}">
								<li><a href="javascript:void(0);"
									onclick="tijiao('${pages.nextPage }')">Next</a>
								</li>
							</c:if>
							<li class="disabled"><a>共${pages.totalPage}页${pages.totalRecord}条记录</a>
							</li>
						</ul>
					</c:if>
				</div>
				<jsp:include page="../include/footer.jsp" />
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function tijiao(url) {
		$("#setchInfo").attr("action",
				"<c:url value='/limitNumEdit/"+url+"' />");
		$("#setchInfo").submit();
	}
</script>
</html>


