<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>天津市不动产 - users</title>
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
		<jsp:param value="/users/1" name="menu"/>
	</jsp:include>
	<div class="content">

	<ul class="breadcrumb">
		<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
		<li class="active">用户列表</li>
	</ul>
	
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					<div style="display:none;">${pages.currentPage}</div>
					<form class="form-search" method="post" id="searchNews" action="<c:url value='/users/1' />">
					       用户名：<input type="text" name="name" value="${name }"/>
					       微信id：<input type="text" name="openid" value="${openid }"/>
					       手机号：<input type="text" name="mobile" size="11" value="${mobile }"/>			       
					       身份证号：<input type="text" name="idcard" size="22" value="${idcard }"/>
					       状态：
					       <select name="status">
					       	<option value="99" <c:if test="${status eq '99' || empty status}">selected="selected"</c:if>>全部</option>
					       	<option value="0" <c:if test="${status eq '0'}">selected="selected"</c:if>>待审核</option>
					       	<option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>审核通过</option>
					       	<option value="2" <c:if test="${status eq '2'}">selected="selected"</c:if>>未通过</option>
					       </select>
					        黑名单：
					       <select name="isForbidden">
					       	<option value="99" <c:if test="${isForbidden eq '99' || empty isForbidden}">selected="selected"</c:if>>全部</option>
					       	<option value="1" <c:if test="${isForbidden eq '1'}">selected="selected"</c:if>>黑名单</option>
					       	<option value="0" <c:if test="${isForbidden eq '0'}">selected="selected"</c:if>>正常</option>
					       	</select>
					  <button type="submit" class="btn">Search</button>
					  </form>
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>用户ID</th>
								<th>用户名</th>
								<th>手机号</th>
								<th>身份证号</th>
<!-- 								<th>身份证正</th> -->
								<th>设为黑名单</th>
								<th>类型</th>
								<th>状态</th>
								<th>创建时间</th>
								<th width="160px;">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${users}" var="item" varStatus="status">
							<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
								<td>${(status.index + 1) + (pages.currentPage - 1) * pages.pageRecord }</td>
								<td>${item.openid } </td>
								<td>${item.name }</td>
								<td>${item.mobile }</td>
								<td><a href="javascript:void(0);" onclick="searchSubmit('${item.idcard}');" target="_blank">${item.idcard }</a></td>
								<td>
									<c:if test="${item.isForbidden eq 1}"><a href="<c:url value="/forbiddenUser/${item.id}/0/${pages.currentPage }"/>">黑名单</a></c:if>
									<c:if test="${item.isForbidden eq 0}"><a href="<c:url value="/forbiddenUser/${item.id}/1/${pages.currentPage }"/>">正常</a></c:if>
								</td>
<!-- 								<td><a href="${item.idcardImgUrl2 }" target="_blank">背面照</a></td> -->
								<td>
									<c:if test="${item.type eq 1}">个人</c:if>
									<c:if test="${item.type eq 2}">企业</c:if>
								</td>
								<td>
									<c:if test="${item.status eq 0}"><B style="color: blue;">待审核</B></c:if>
									<c:if test="${item.status eq 1}"><B style="color: green;">审核通过</B></c:if>
									<c:if test="${item.status eq 2}"><B style="color: red;">未通过</B></c:if>
								</td>
								<td>${item.createTime }</td>
								<td>
								    <a href="<c:url value="/updateUser/${item.id}/1/${pages.currentPage }"/>">审核通过</a> / 
								    <a href="<c:url value="/updateUser/${item.id}/2/${pages.currentPage }"/>">审核不通过</a> / &nbsp;
								    <a href="#myModal${item.id}" role="button" data-toggle="modal">删除</a>
								    
	              					<div class="modal small hide fade" id="myModal${item.id}" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h3 id="myModalLabel">确认删除</h3>
										</div>
										<div class="modal-body">
											<p class="error-text">
												<i class="icon-warning-sign modal-icon"></i>
												确定删除该用户？删除后不可恢复，请您再次确认！
											</p>
										</div>
										<div class="modal-footer">
			              					<form id="deleteNews${item.id}" method="post" action="<c:url value="/deleteUser/${item.id}/${pages.currentPage}"/>">
			              						
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
						<li class="disabled"><a>共${pages.totalPage}页${pages.totalRecord}条记录</a></li>
					</ul>
				</c:if>
				</div>
				<jsp:include page="../include/footer.jsp"/>
			</div>
		</div>
	</div>
	
	<form method="post" id="setchInfo" action="<c:url value='/loadOrderRecords/1' />" target="_blank">
		<input type="hidden" name="idcard" id="idcardHidden"/>
	</form>
</body>
<script type="text/javascript">
	function tijiao(url){
		$("#searchNews").attr("action","<c:url value='/users/"+url+"' />");
		$("#searchNews").submit();
	}
	
	function searchSubmit(idcard){
		$("#idcardHidden").val(idcard);
		$("#setchInfo").submit();
	}
</script>
</html>

