<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>预约登记列表</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<jsp:include page="../include/headTag.jsp" />
<style type="text/css">
span {
	cursor: pointer;
	color: #0088cc;
}
</style>
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
		<jsp:param value="/loadOrderRecords/1" name="menu" />
	</jsp:include>
	<div class="content" style="margin-left:150px;">
		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span
				class="divider">/</span></li>
			<li class="active">预约信息</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div>
					<span style="color:red;">注：加号功能数据在预约总列表中不可见</span>
					<p>
						<br />
				</div>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>用户名</th>
								<th>手机号</th>
								<th>openid</th>
								<th>身份证号</th>
								<th>办理机构</th>
								<th>业务类型</th>
								<th>办理日期</th>
								<th>时间段</th>
								<th>行政区</th>
								<th>房产登记字号</th>
								<th>房产证地址</th>
								<th>预约时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderRecords}" var="item" varStatus="status">
								<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
									<td>${(status.index + 1) + (pages.currentPage - 1) *
										pages.pageRecord }</td>
									<td>${item.name }</td>
									<td>${item.mobile }</td>
									<td>${fn:substring(item.openid, 0, 14)}<br/>${fn:substring(item.openid, 15, 28)}</td>
									<td>${item.idcard }</td>
									<td>${item.transactOrgName }</td>
									<td>${item.transactTypeName }</td>
									<td>${item.transactDate }</td>
									<td>${item.transactTime }</td>
									<td><c:if test="${item.area eq 0}">市区</c:if> <c:if
											test="${item.area eq 1}">和平区</c:if> <c:if
											test="${item.area eq 2}">河西区</c:if> <c:if
											test="${item.area eq 3}">河北区</c:if> <c:if
											test="${item.area eq 4}">河东区</c:if> <c:if
											test="${item.area eq 5}">南开区</c:if> <c:if
											test="${item.area eq 6}">红桥区</c:if> <c:if
											test="${item.area eq 7}">东丽区</c:if> <c:if
											test="${item.area eq 8}">西青区</c:if> <c:if
											test="${item.area eq 9}">津南区</c:if> <c:if
											test="${item.area eq 10}">北辰区</c:if> <c:if
											test="${item.area eq 11}">武清区</c:if> <c:if
											test="${item.area eq 12}">宝坻</c:if> <c:if
											test="${item.area eq 13}">蓟县</c:if> <c:if
											test="${item.area eq 14}">静海</c:if> <c:if
											test="${item.area eq 15}">宁河</c:if> <c:if
											test="${item.area eq 16}">滨海新区</c:if> <c:if
											test="${item.area eq 17}">滨海第一中心</c:if> <c:if
											test="${item.area eq 18}">滨海第二中心</c:if> <c:if
											test="${item.area eq 19}">滨海第三中心</c:if> <c:if
											test="${item.area eq 20}">滨海第四中心</c:if> <c:if
											test="${item.area eq 21}">开发区</c:if> <c:if
											test="${item.area eq 22}">高新区</c:if> <c:if
											test="${item.area eq 23}">东疆港</c:if>
									</td>
									<td>${item.hourseNumber }</td>
									<td>${item.hourseAddress }</td>
									<td>${fn:substring(item.createTime, 0, 10)}</td>
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
									onclick="tijiao('${pages.prePage }')">Prev</a></li>
							</c:if>
							<c:forEach begin="${pages.pageNumStart }"
								end="${pages.pageNumEnd }" varStatus="status">
								<li
									<c:if test="${status.index == pages.currentPage }">class="active"</c:if>>
									<a href="javascript:void(0);"
									onclick="tijiao('${status.index }')"> ${status.index } </a>
								</li>
							</c:forEach>
							<c:if test="${pages.currentPage< pages.totalPage}">
								<li><a href="javascript:void(0);"
									onclick="tijiao('${pages.nextPage }')">Next</a></li>
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
<script type="text/javascript"
	src="<c:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>

</html>


