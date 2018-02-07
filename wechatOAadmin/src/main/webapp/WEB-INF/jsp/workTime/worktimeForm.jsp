<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>天津市不动产 - Admin</title>
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
	<c:set var="operator" value="/workTime"/>
	<c:if test="${adminUser.id > 0 && page > 0 }">
		<c:set var="operator" value="/workTimeList"/>
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
				<c:when test="${workTime.id > 0 && page > 0 }">
				修改
				</c:when>
				<c:otherwise>
				新增
				</c:otherwise>
			</c:choose>办理时间
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
					<button class="btn btn-primary" id="save">
						<i class="icon-save"></i> 保存
					</button>
					<c:if test="${workTime.id gt 0}">
						<a href="<c:url value="/workTime"/>" class="btn btn-primary">
							<i class="icon-plus"></i>添加办理时间
						</a>
						<c:if test="${empty page || page == 0 }">
							<c:set var="page" value="1"/>
						</c:if>
						<a href="<c:url value="/workTimeList"/>" data-toggle="modal" class="btn">返回办理时间列表</a>
					</c:if>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form:form modelAttribute="workTime" id="basicInfoForm" method="post">
								<label>开始时间</label> 
						        <input class="Wdate" type="text" name="startTime" onClick="WdatePicker({dateFmt:'HH:mm'})" value="${workTime.startTime}"/>
								<span id="startTime" class="text-error"></span>
								<label>结束时间</label> 
								<input type="text" name="endTime" value="${workTime.endTime }" class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})"/>
								<span id="endTime" class="text-error"></span>
								<label>预约最大人数</label> 
								<input type="text" name="members" value="${workTime.members}" class="input-xlarge" style="width:100px" maxlength="3"/>
								<span id="members" class="text-error"></span>
								<label>最终截止时间</label> 
								<input type="text" name="deadTime" value="${workTime.deadTime }" class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})"/> 
								<span id="deadTime" class="text-error"></span>
								<label>区域</label>
								<select name="areaId" style="width:300px">
									<option value="0" selected="selected">请选择</option>
									<c:forEach items="${handleOrg }" var="item">
										<option value="${item.id }" <c:if test="${workTime.areaId eq item.dictionaryCategoryid }">selected=selected</c:if>>${item.dictionaryValue }</option>
									</c:forEach>
								</select> 
								<span id="areaId" class="text-error"></span>
						        <input type="hidden" name="id" value="${empty workTime.id ? 0 : workTime.id}"/>
						        <input type="hidden" name="areaName" value="${workTime.areaName }"/>
							</form:form>
						</div>
					</div>

				</div>

				<jsp:include page="../include/footer.jsp"/>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>
	<script type="text/javascript">
		$(function() {
			
			$('#save').click(function() {
				$(".text-error").html("");
				var startTime = $("input[name='startTime']").val();
				var endTtime = $("input[name='endTime']").val();
				var deadTime = $("input[name='deadTime']").val();
				if(startTime == ""){
					
					$("#startTime").html("请输入开始时间");
					return false;
				}
				
				if(endTtime == ""){
					
					$("#endTime").html("请输入结束时间");
					return false;
				}else{
					var intStartTime = parseInt(startTime.split(":"), 10);
					var intEndTime = parseInt(endTtime.split(":"), 10);
					if(intStartTime > intEndTime){
						$("#endTime").html("结束时间不能小于开始时间");
						return false;
					}
				}
				
				var members = $("input[name='members']").val();
				if(members == ""){
					
					$("#members").html("请输入最大预约人数");
					return false;
				}else{
					var match = /^[1-9][0-9]*[0-9]*$/;
					if(!match.test(members)){
						$("#members").html("请正确填写预约人数");
						return false;
					}
				}
				
				if(deadTime == ""){
					
					$("#deadTime").html("最终截止时间");
					return false;
				}else{
					var intStartTime = parseInt(startTime.split(":"), 10);
					var intEndTime = parseInt(endTtime.split(":"), 10);
					var intDeadTime = parseInt(deadTime.split(":"), 10);
					
					if(intDeadTime < intStartTime || intDeadTime > intEndTime){
						$("#endTime").html("最终截止时间必须在开始时间和结束时间之间");
						return false;
					}
				}
				
				if($("select[name='areaId']").val() == "0"){
					
					$("#areaId").html("请选择区域");
					return false;
				}else{
					$("input[name='areaName']").val($("select[name='areaId']").find("option:selected").text());
				}
				
				$('#basicInfoForm').submit();
				return false;
			});
		});
	</script>
</body>
</html>