<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>预约详情</title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="<c:url value="/resources/js/ckeditor/contents.css"/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet" type="text/css" media="screen">
    <jsp:include page="../include/headTag.jsp" />
</head>
<body class="">

	<jsp:include page="../include/header.jsp"/>	
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/loadOrderRecords/1" name="menu"/>
	</jsp:include>
	<div class="content">
		<div class="alert alert-error" id="alertError" style="display: none;"></div>
		<div class="alert alert-success" id="alertSuccess" style="display: none;"></div>
		
		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">预约信息</li>
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
<!-- 					<button class="btn btn-primary" id="saveUser"> -->
<!-- 						<i class="icon-save"></i> 保存 -->
<!-- 					</button> -->
					<div class="btn-group"></div>
				</div>
								
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							 <form id="userForm" method="post" method="post" action="<c:url value='/updateUser/${user.id }'/>">
							     <input type="hidden" name="id" value="${orderRecord.id }"/> 
							     							     <label>行政区：
						        	<c:if test="${orderRecord.area eq 0}">市区</c:if>
									<c:if test="${orderRecord.area eq 1}">和平区</c:if>
									<c:if test="${orderRecord.area eq 2}">河西区</c:if>
									<c:if test="${orderRecord.area eq 3}">河北区</c:if>
									<c:if test="${orderRecord.area eq 4}">河东区</c:if>
									<c:if test="${orderRecord.area eq 5}">南开区</c:if>
									<c:if test="${orderRecord.area eq 6}">红桥区</c:if>
									<c:if test="${orderRecord.area eq 7}">东丽区</c:if>
									<c:if test="${orderRecord.area eq 8}">西青区</c:if>
									<c:if test="${orderRecord.area eq 9}">津南区</c:if>
									<c:if test="${orderRecord.area eq 10}">北辰区</c:if>
									<c:if test="${orderRecord.area eq 11}">武清区</c:if>
									<c:if test="${orderRecord.area eq 12}">宝坻</c:if>
									<c:if test="${orderRecord.area eq 13}">蓟县</c:if>
									<c:if test="${orderRecord.area eq 14}">静海</c:if>
									<c:if test="${orderRecord.area eq 15}">宁河</c:if>
									<c:if test="${orderRecord.area eq 16}">滨海新区</c:if>
									<c:if test="${orderRecord.area eq 17}">滨海第一中心</c:if>
									<c:if test="${orderRecord.area eq 18}">滨海第二中心</c:if>
									<c:if test="${orderRecord.area eq 19}">滨海第三中心</c:if>
									<c:if test="${orderRecord.area eq 20}">滨海第四中心</c:if>
									<c:if test="${orderRecord.area eq 21}">开发区</c:if>
									<c:if test="${orderRecord.area eq 22}">高新区</c:if>
									<c:if test="${orderRecord.area eq 23}">东疆港</c:if>
								</label>
								<label>状态：
						        	<c:if test="${orderRecord.status eq 1}">待审核</c:if>
									<c:if test="${orderRecord.status eq 2}"><B style="color:green">审核通过</B></c:if>
									<c:if test="${orderRecord.status eq 3}"><B>取消预约</B></c:if>
									<c:if test="${orderRecord.status eq 4}"><B>办结</B></c:if>
									<c:if test="${orderRecord.status eq 5}"><B>爽约</B></c:if>
									<c:if test="${orderRecord.status eq 6}"><B>补正</B></c:if>
									<c:if test="${orderRecord.status eq 9}"><B style="color:red">不通过</B></c:if>
								 </label>
								<label>用户名</label>
							        <input type="text" name="name" value="${orderRecord.name }" maxlength="100" readOnly />
									<label>openid</label>
							        <input type="text" name="openid" value="${orderRecord.openid }" maxlength="150" style="width:300px;" readOnly />
							    <label>手机号</label>
						            <input type="text" name="mobile" value="${orderRecord.mobile }" maxlength="100" readOnly/>
								<label>身份证号</label>
							        <input type="text" name="idcard" value="${orderRecord.idcard }" maxlength="100" readOnly/>
							    <label>办理机构</label>
							        <input type="text" name="transactOrgName" value="${orderRecord.transactOrgName }" maxlength="100" readOnly/>
							    <label>业务类型</label>
							        <input type="text" name="transactTypeName" value="${orderRecord.transactTypeName }" maxlength="100" readOnly/> 
							    <label>办理日期</label>
							        <input type="text" name="transactDate" value="${orderRecord.transactDate }" maxlength="18" readOnly/>
							    <label>时间段</label>
						        	<input type="text" name="transactTime" value="${orderRecord.transactTime }" maxlength="18" readOnly/>  
						        <label>房产登记字号</label>
						        	<input type="text" name="hourseNumber" value="${orderRecord.hourseNumber }" maxlength="18" readOnly/>  
						        <label>房产证地址</label>
						        	<input type="text" name="hourseAddress" value="${orderRecord.hourseAddress }" maxlength="18" readOnly/>  
						        <label>预约时间</label>
						        	<input type="text" name="createTime" value="${orderRecord.createTime }" maxlength="18" readOnly/>
						        <!-- 处理东丽区特殊字段  7是东丽区ID -->
						        <c:if test="${orderRecord.area eq 7}">
						        <label>用户类型：
						        	<c:if test="${orderRecord.usertype eq 0}">个人</c:if><c:if test="${orderRecord.usertype eq 1}">企业</c:if>
						        </label>
						        <label>权利人姓名</label>
						        	<input type="text" name="obligeename" value="${orderRecord.obligeename }" maxlength="18" readOnly/>  
						        <label>权利人身份证号</label>
						        	<input type="text" name="obligeeidcard" value="${orderRecord.obligeeidcard }" maxlength="18" readOnly/>  
						        <label>是否有代理人：
						        	<c:if test="${orderRecord.isHaveHasagent eq 0}">是</c:if><c:if test="${orderRecord.isHaveHasagent eq 1}">否</c:if>
						        </label>
						        </c:if>
							 </form>
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
		$('#saveUser').click(function() {
			$('#userForm').submit();
			return false;
		});
	});
</script>
</html>


