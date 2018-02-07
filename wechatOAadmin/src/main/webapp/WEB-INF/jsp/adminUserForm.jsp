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
	
	<jsp:include page="include/headTag.jsp" />

</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->
	<c:set var="operator" value="/createAdminUser"/>
	<c:if test="${adminUser.id > 0 && page > 0 }">
		<c:set var="operator" value="/adminUsers/1"/>
	</c:if>

	<jsp:include page="include/header.jsp"/>
	
	<jsp:include page="include/menues.jsp">
		<jsp:param value="${operator }" name="menu"/>
	</jsp:include>
	
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active">
			<c:choose>
				<c:when test="${adminUser.id > 0 && page > 0 }">
				修改
				</c:when>
				<c:otherwise>
				新增
				</c:otherwise>
			</c:choose>后台用户
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
					<button class="btn btn-primary" id="saveAdminUser">
						<i class="icon-save"></i> 保存
					</button>
					<c:if test="${adminUser.id gt 0}">
						<a href="<c:url value="/createAdminUser"/>" class="btn btn-primary">
							<i class="icon-plus"></i>添加后台用户
						</a>
						<c:if test="${empty page || page == 0 }">
							<c:set var="page" value="1"/>
						</c:if>
						<a href="<c:url value="/adminUsers/${page}"/>" data-toggle="modal" class="btn">返回用户列表</a>
					</c:if>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form:form modelAttribute="adminUser" id="basicInfoForm" method="post">
								<label style="font-weight:bold;">登录名</label> 
								<input type="text" id="login_name" name="loginName" value="${adminUser.loginName}" class="input-xlarge"> 
								<span id="loginNameError" class="text-error"></span>
								<label style="font-weight:bold;">真实姓名</label> 
								<input type="text" name="realName" value="${adminUser.realName }" class="input-xlarge"/> 
								<label style="font-weight:bold;">密码</label> 
								<input type="text" id="pass_word" name="passWord" value="${adminUser.passWord }" class="input-xlarge"/>
								<span id="passwordError" class="text-error"></span>
								<label style="font-weight:bold;">电话</label> 
								<input type="text" name="phone" value="${adminUser.phone }" class="input-xlarge"/> 
								<label style="font-weight:bold;">所属角色</label>
						        <c:forEach items="${groups }" var="group" varStatus="status">
						        	<label class="checkbox inline">
						        	<!-- 这里是checkbox，可多选，但是修改过来的时候暂时只取了一个值，懒得写了，因为暂时用不上，以后需要记得修改 -->
						        	<input type="checkbox" name="groups" value="${group.groupId },${group.name }" <c:if test="${group.groupId eq userGroup}">checked="checked"</c:if> />${group.name }
									</label>
									<c:if test="${status.count%5==0 }"><br/></c:if>
								</c:forEach>
								
								<p></p>
								<label style="font-weight:bold;">所属区县</label>
								<input id="area" type="hidden" name="area" value="1">
								<c:forEach items="${areas }" var="item" varStatus="status">
									<label class="checkbox inline"><input type="checkbox" name="box" value="${item.areaCode }"/>${item.areaDesc }</label>
									<c:if test="${status.index eq 8}"><br/></c:if>
								</c:forEach>
								 <p></p>
								<label style="font-weight:bold;">备注</label>
								<textarea name="comment" rows="3" class="input-xlarge">${adminUser.comment }</textarea>
						        <input type="hidden" name="id" value="${adminUser.id }"/>
							</form:form>
						</div>
					</div>

				</div>

				<jsp:include page="include/footer.jsp"/>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			var areaValue = "<c:out value='${adminUser.area}'/>";
			var areaTemp = areaValue.split(',');
			for(var i = 0; i < areaTemp.length; i++){
				$("input:checkbox[value='"+areaTemp[i]+"']").attr('checked','true');
			}; 
		});
		
		$('#saveAdminUser').click(function() {
			
			if($("#login_name").val() == ""){
				
				$("#loginNameError").html("请输入登录名");
				return false;
			}
			
			if($("#pass_word").val() == ""){
				
				$("#passwordError").html("请输入密码");
				return false;
			}
			
			var areaTemp = "";
		    $('input:checkbox[name=box]:checked').each(function(i){
		       if(0==i){
		    	   areaTemp = $(this).val();
		       }else{
		    	   areaTemp += (","+$(this).val());
		       }
		    });
		    $("#area").val(areaTemp);
			 
			$('#basicInfoForm').submit();
			return false;
		});
	</script>
</body>
</html>