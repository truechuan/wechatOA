<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<c:set var="operator" value="/group/new"/>
	<c:if test="${group.groupId > 0 && page > 0 }">
		<c:set var="operator" value="/groups/1"/>
	</c:if>

	<jsp:include page="../include/header.jsp"/>
	
	<jsp:include page="../include/menues.jsp">
		<jsp:param value="${operator }" name="menu"/>
	</jsp:include>
	
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">
			<c:choose>
				<c:when test="${limitNum.id > 0 && page>0 }">
				修改
				</c:when>
				<c:otherwise>
				新增
				</c:otherwise>
			</c:choose>处罚限制
			</li>
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
							<c:choose>
								<c:when test="${not empty message}">
									${message }
								</c:when>
								<c:otherwise>
									保存失败, 请检查输入项！
								</c:otherwise>
							</c:choose>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div class="btn-toolbar">
					<button class="btn btn-primary" id="saveGroup">
						<i class="icon-save"></i> 保存
					</button>
					<c:if test="${limitNum.id > 0}">
						<a href="<c:url value="/limitNum/new"/>" class="btn btn-primary">
							<i class="icon-plus"></i>添加处罚限制
						</a>
						
						
					</c:if>
					<c:if test="${empty page || page == 0 }">
							<c:set var="page" value="1"/>
						</c:if>
					<a href="<c:url value="/limitNumShow/${page}"/>" data-toggle="modal" class="btn">返回处罚限制列表</a>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form:form modelAttribute="limitNum" id="limitNumForm" method="post">
								<c:if test="${funflag == 'update'}">
									<label>所属区域：<span style="color:#ff0000;">${limitNum.areaDesc}</span></label>
									<input type="hidden" name="areaCode" value="${limitNum.areaCode}" />
									<c:if test="${limitNum.limittype == 1}">
										<label>限制类型：<span style="color:#ff0000;">爽约次数</span></label>
									</c:if>
									<c:if test="${limitNum.limittype == 2}">
										<label>限制类型：<span style="color:#ff0000;">审核后取消次数</span></label>
									</c:if>
									<input type="hidden" name="limittype" value="${limitNum.limittype}" />
									<label>每*次限制</label> 
									<input type="text" id="limitnum" name="limitnum" value="${limitNum.limitnum}" readonly="readonly" class="input-xlarge"> 
									<span id="nameError" class="text-error"></span>
								</c:if>
								<c:if test="${funflag == 'add'}">
									<label>所属区域：</label>
									<select name="areaCode" class="input-xlarge">
										<c:forEach items="${limitNum.areaList}" var="areaList" varStatus="status">
											<option value="${areaList.areaCode}">${areaList.areaDesc}</option>
										</c:forEach>
									</select>
									<label>限制类型：</label>
									<c:choose>
										<c:when test="${limitNum.limittype == 1}">
											<select name="limittype" class="input-xlarge">
												<option value="1" selected="selected">爽约次数</option>
												<option value="2">审核后取消次数</option>
											</select>
										</c:when>
										<c:when test="${limitNum.limittype == 2}">
											<select name="limittype" class="input-xlarge">
												<option value="1" >爽约次数</option>
												<option value="2" selected="selected">审核后取消次数</option>
											</select>
										</c:when>
										<c:otherwise>
											<select name="limittype" class="input-xlarge">
												<option value="-1" >请选择类别...</option>
												<option value="1" >爽约次数</option>
												<option value="2" >审核后取消次数</option>
											</select>
										</c:otherwise>
									</c:choose>
									<label>每*次限制</label> 
									<input type="text" id="limitnum" name="limitnum" value="${limitNum.limitnum}" class="input-xlarge"> 
									<span id="nameError" class="text-error"></span>
								</c:if>
								
								
								
								<label>每*次限制的天数</label> 
								<input type="text" name="limittimeOnesDays" value="${limitNum.limittimeOnesDays }" class="input-xlarge"/> 
						        <input type="hidden" name="id" value="${limitNum.id }"/>
							</form:form>
						</div>
					</div>

				</div>

				<jsp:include page="../include/footer.jsp"/>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
		$(function() {
			
			$('#saveGroup').click(function() {
				
				//if($("#name").val() == ""){
					//$("#nameError").html("请输入角色名称");
					//return false;
				//}
				
				$('#limitNumForm').submit();
				return false;
			});
		});
	</script>
</body>
</html>