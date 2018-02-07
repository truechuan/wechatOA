<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>后台增号</title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="<c:url value="/resources/js/ckeditor/contents.css"/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet" type="text/css" media="screen">
    <jsp:include page="../include/headTag.jsp" />
</head>
<body class="">
	<c:set var="operator" value="/createOrder"/>
	<c:if test="${addOrder.addOrderId > 0 }">
		<c:set var="operator" value="/updateAddOrder/1"/>
	</c:if>
	<jsp:include page="../include/header.jsp"/>	
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/createOrder/1" name="menu"/>
	</jsp:include>
	<div class="content">
		<div class="alert alert-error" id="alertError" style="display: none;"></div>
		<div class="alert alert-success" id="alertSuccess" style="display: none;"></div>
		
		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active"><c:choose>
					<c:when test="${addOrder.addOrderId  > 0 }">
					编辑
				</c:when>
					<c:otherwise>
					添加
				</c:otherwise>
				</c:choose>填写预约信息</li>
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
 					<button class="btn btn-primary" id="saveAddOrder">
 						<i class="icon-save"></i> 保存 
 					</button> 
 					<c:if test="${addOrder.addOrderId gt 0}">
						<a href="<c:url value="/createNotice"/>" data-toggle="modal"
							class="btn">继续添加</a>
					</c:if>
					<a href="<c:url value="/addOrders/1"/>" class="btn btn-primary">
						返回
					</a>
					<div class="btn-group"></div>
				</div>
								
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							 <form id="addOrderForm" method="post" modelAttribute="AddOrder">
							     <input type="hidden" name="addOrderId" value="${addOrder.addOrderId }"/> 
							    <input type="hidden" name="transactOrgId" value="${addOrder.transactOrgId }"/>
							    <input type="hidden" name="area" value="${addOrder.area }"/>
							    <input type="hidden" name="transactOrgName" value="${addOrder.transactOrgName }"/>
								<label>用户名</label>
							        <input type="text" name="name" value="${addOrder.name }" maxlength="100"  />
							    <label>手机号</label>
						            <input type="text" name="mobile" value="${addOrder.mobile }" maxlength="100" />
								<label>身份证号</label>
							        <input type="text" name="idcard" value="${addOrder.idcard }" maxlength="100" />
							    <label>业务类型</label>
							    	<select name="transactTypeId" id="transactTypeId" onchange="selectChild(this)">
							    	<c:forEach items="${childType }" var="item">
										<option value="${item.id }" >${item.noticeName }</option>
										</c:forEach>
							    	</select>
							    	<input type="hidden" id="transactTypeName" name="transactTypeName" value="${addOrder.transactTypeName }" /> 
							    <label>办理日期</label>
							    <select name="transactDate" id="transactDate" onchange="">
							    	<c:forEach items="${arrDays }" var="item">
										<option value="${item }" >${item }</option>
										</c:forEach>
							    	</select>
						
							    <label>时间段</label>
							    
							    <select name="transactTime" id="transactTime" onchange="">
							    	<c:forEach items="${workTimes }" var="item">
										<option value="${item.startTime }-${item.endTime }" >${item.startTime }-${item.endTime }</option>
										</c:forEach>
							    	</select>
						        	 
						        <label>房产登记字号</label>
						        	<input type="text" name="hourseNumber" value="${addOrder.hourseNumber }" maxlength="18" />  
						        <label>房产证地址</label>
						        	<input type="text" name="hourseAddress" value="${addOrder.hourseAddress }" maxlength="18" />  
						        
						        
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
		$('#saveAddOrder').click(function() {
			$('#addOrderForm').submit();
			return false;
		});
	});
	//子业务类型选择
	function selectChild(obj)
	{
		$("input[name='transactTypeName']").val($(obj).find("option:selected").text());
	}
</script>
</html>


