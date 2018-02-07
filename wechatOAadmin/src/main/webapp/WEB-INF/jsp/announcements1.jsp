<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>天津市不动产 - 天津市不动产公告列表</title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<jsp:include page="include/headTag.jsp" />
	<script type="text/javascript">
		function tijiao(url){
			$("#searchNews").attr("action","<c:url value='/announcements/"+url+"' />");
			$("#searchNews").submit();
		}
	</script>
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
		<jsp:param value="/announcements/1" name="menu"/>
	</jsp:include>
	<div class="content">

	<ul class="breadcrumb">
		<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
		<li class="active">公告列表</li>
	</ul>
	
		<div class="container-fluid">
			<div class="row-fluid">
			<div class="btn-toolbar">
					<a href="<c:url value="/createAnnouncement"/>" class="btn btn-primary">
						<i class="icon-plus"></i>添加公告
					</a>
				</div>
				<div class="well">
					<div style="display:none;">${pages.currentPage}</div>
					<form id="searchNews" class="form-search" method="post" action="<c:url value='/' />news/${type}/1">
					       公告标题：<input type="text" name="title" value="${name}"/>
					  <button type="button" onclick="javascript:void(0);" class="btn">Search</button>
					</form>
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>标题</th>
<!-- 								<th>摘要</th> -->
<!-- 								<th>关键词</th> -->
<!-- 								<th>描述</th> -->
								<th>状态</th>
<!-- 								<th>关联词</th> -->
								<th>创建时间</th>
<!-- 								<th>图片地址</th> -->
								<th>栏目</th>
								<th>内容</th>		
								<th>所属区县</th>							
								<th style="width: 30px;">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${announcements}" var="item" varStatus="status">
							<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
								<td>${(status.index + 1) + (pages.currentPage - 1) * pages.pageRecord }</td>
								<td>${item.title }</td>
<%-- 								<td>${item.summary }</td> --%>
<%-- 								<td>${item.keyWord }</td> --%>
<%-- 								<td>${item.description }</td> --%>
								<td>${item.status.description }</td>						
<%-- 								<td>${item.ralateWord }</td> --%>
								<td>${item.createTime }</td>
<%-- 								<td>${item.imageUrl }</td> --%>
								<td>${item.lanmu.description }</td>	
								<c:set var="content" value="${item.detailInfo }"></c:set>
								<c:if test="${fn:length(content)>20 }">
									<c:set var="content">
										${fn:substring(content, 0, 20) }..
									</c:set>
								</c:if>
								<td>${content }</td>
								<td>
									<c:if test="${item.area eq 0}">市区</c:if>
									<c:if test="${item.area eq 1}">和平区</c:if>
									<c:if test="${item.area eq 2}">河西区</c:if>
									<c:if test="${item.area eq 3}">河北区</c:if>
									<c:if test="${item.area eq 4}">河东区</c:if>
									<c:if test="${item.area eq 5}">南开区</c:if>
									<c:if test="${item.area eq 6}">红桥区</c:if>
									<c:if test="${item.area eq 7}">东丽区</c:if>
									<c:if test="${item.area eq 8}">西青区</c:if>
									<c:if test="${item.area eq 9}">津南区</c:if>
									<c:if test="${item.area eq 10}">北辰区</c:if>
									<c:if test="${item.area eq 11}">武清区</c:if>
									<c:if test="${item.area eq 12}">宝坻</c:if>
									<c:if test="${item.area eq 13}">蓟县</c:if>
									<c:if test="${item.area eq 14}">静海</c:if>
									<c:if test="${item.area eq 15}">宁河</c:if>
									<c:if test="${item.area eq 16}">滨海新区</c:if>
									<c:if test="${item.area eq 17}">滨海第一中心</c:if>
									<c:if test="${item.area eq 18}">滨海第二中心</c:if>
									<c:if test="${item.area eq 19}">滨海第三中心</c:if>
									<c:if test="${item.area eq 20}">滨海第四中心</c:if>
									<c:if test="${item.area eq 21}">开发区</c:if>
									<c:if test="${item.area eq 22}">高新区</c:if>
									<c:if test="${item.area eq 23}">东疆港</c:if>
								</td>												
								<td>
									<a href="<c:url value="/updateAnnouncement/${item.announcementId}"/>"><i class="icon-pencil"></i></a>
	              					<a href="#myModal${item.announcementId}" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
	              					<div class="modal small hide fade" id="myModal${item.announcementId}" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h3 id="myModalLabel">确认删除</h3>
										</div>
										<div class="modal-body">
											<p class="error-text">
												<i class="icon-warning-sign modal-icon"></i>
												确定删除该记录?
											</p>
										</div>
										<div class="modal-footer">
			              					<form id="delete${item.announcementId}" method="post" action="<c:url value="/deleteAnnouncement/${pages.currentPage}/${item.announcementId}"/>">
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
						<li><a href="javascript:void(0);" onclick="tijiao('${pages.prePage }')">Prev</a></li>
					</c:if>
					<c:forEach begin="${pages.pageNumStart }" end="${pages.pageNumEnd }" varStatus="status">
						<li <c:if test="${status.index == pages.currentPage }">class="active"</c:if>>
							<a href="javascript:void(0);" onclick="tijiao('${status.index }')">
								${status.index }
							</a>
						</li>
					</c:forEach>
					<c:if test="${pages.currentPage< pages.totalPage}">
						<li><a href="javascript:void(0);" onclick="tijiao('${pages.nextPage }')">Next</a></li>
					</c:if>
						<li class="disabled"><a>共${pages.totalPage}页${pages.totalRecord}篇文章</a></li>
					</ul>
				</c:if>
				</div>
				<jsp:include page="include/footer.jsp"/>
			</div>
		</div>
	</div>
</body>
</html>


