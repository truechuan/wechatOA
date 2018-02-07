<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>dictionarys</title>
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
		<jsp:param value="/dictionarys/1" name="menu" />
	</jsp:include>
	<div class="content">

	<ul class="breadcrumb">
		<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
		<li class="active">字典列表</li>
	</ul>
	
		<div class="container-fluid">
			<div class="row-fluid">
			<div class="btn-toolbar">
				<a href="<c:url value="/dictionary/new"/>" class="btn btn-primary">
					<i class="icon-plus"></i>创建新字典值
				</a>
			</div>
			<div class="well">
				<div style="display:none;">${pages.currentPage}</div>
				<table class="table">
					<thead>
						<tr>
							<th>序号</th>
							<th>所属分类</th>
							<th>字典值</th>
							<th>所属区县</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${dictionarys}" var="dictionary" varStatus="status">
						<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
							<td>${(status.index + 1) + (pages.currentPage - 1) * pages.pageRecord }</td>
							<td>${dictionary.dictionaryCategoryName }</td>
							<td>${dictionary.dictionaryValue }</td>
							<td>
								<c:choose> 
								  <c:when test="${dictionary.handleOrgId eq 1034}">市区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1035}">河北区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1037}">静海区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1038}">和平区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1039}">南开区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1040}">河东区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1041}">河西区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1042}">红桥区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1043}">东丽区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1044}">西青区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1045}">北辰区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1046}">津南区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1047}">武清区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1048}">宝坻区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1049}">宁河区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1050}">蓟州区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1051}">滨海区</c:when>
								  
								  <c:when test="${dictionary.handleOrgId eq 1101}">滨海第一中心</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1102}">滨海第二中心</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1103}">滨海第三中心</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1104}">滨海第四中心</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1105}">开发区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1106}">高新区</c:when>
								  <c:when test="${dictionary.handleOrgId eq 1108}">东疆港</c:when>
								  <c:otherwise>无</c:otherwise>
								</c:choose>
							</td>
							<td>
							    <a href="<c:url value="/dictionary/${dictionary.id }/edit/${pages.currentPage }"/>" title="修改" class="icon-pencil"></a>
							    <a href="#myModal${dictionary.id}" role="button" data-toggle="modal" title="删除"><i class="icon-remove"></i></a>
	             					<div class="modal small hide fade" id="myModal${dictionary.id}" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">×</button>
										<h3 id="myModalLabel">确认删除</h3>
									</div>
									<div class="modal-body">
										<p class="error-text">
											<i class="icon-warning-sign modal-icon"></i>
											如果删除会造成子类型丢失，你确定要删除吗?
										</p>
									</div>
									<div class="modal-footer">
		              					<form id="deleteForm${dictionary.id}" method="post" action="<c:url value="/dictionary/${dictionary.id}/delete"/>">
		              						<input type="hidden" name="page" value="${pages.currentPage}"/>
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
						<li><a href="<c:url value="/dictionarys/${pages.prePage }"/>">Prev</a></li>
					</c:if>
					<c:forEach begin="${pages.pageNumStart }" end="${pages.pageNumEnd }" varStatus="status">
						<li <c:if test="${status.index == pages.currentPage }">class="active"</c:if>>
							<a href="<c:url value="/dictionarys/${status.index }"/>">
								${status.index }
							</a>
						</li>
					</c:forEach>
					<c:if test="${pages.currentPage< pages.totalPage}">
						<li><a href="<c:url value="/dictionarys/${pages.nextPage }"/>">Next</a></li>
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

