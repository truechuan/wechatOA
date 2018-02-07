<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/nav.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<title>区域选择</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<style type="text/css">
.rowclass {
	margin: 10px 0px;
}
</style>
</head>
<body id="area">
	<div class="top">
		<div class="col-xs-3"></div>
		<div class="col-xs-6">区域选择</div>
	</div>

	<div class="btn-group">
			<div class="content">
			<p>市登记中心</p>
			<div class="col-xs-3">
				<button type="button" class="btn btn-default"
					onclick="javascript:window.location.href='<c:url value='/areaForTencent/1034'/>'"
					style="border-color: #487bba;color:#487bba">市登记中心</button>
			</div>
		</div>
		<hr />
		<div class="content">
			<p>市内六区</p>

			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">和平区</button>--%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1038'/>'" style="border-color: #487bba;color:#487bba">和平区</button> 
			</div>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">河东区</button> --%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1040'/>'" style="border-color: #487bba;color:#487bba">河东区</button> 
			</div>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">河西区</button>--%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1041'/>'" style="border-color: #487bba;color:#487bba">河西区</button> 
			</div>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee;">南开区</button>--%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1039'/>'"  style="border-color: #487bba;color:#487bba">南开区</button> 
			</div>
			<div class="col-xs-3">
				<button type="button" class="btn btn-default"
					onclick="javascript:window.location.href='<c:url value='/areaForTencent/1035'/>'"
					style="border-color: #487bba;color:#487bba">河北区</button>
			</div>
			<div class="col-xs-3">
				<button type="button" class="btn btn-default"
					onclick="javascript:window.location.href='<c:url value='/areaForTencent/1042'/>'"
					style="border-color: #487bba;color:#487bba">红桥区</button>
			</div>
		</div>
		<hr>
		<div class="content">
			<p>环城四区</p>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">东丽区</button>--%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1043'/>'"  style="border-color: #487bba;color:#487bba">东丽区</button> 
			</div>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">西青区</button> --%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1044'/>'" style="border-color: #487bba;color:#487bba">西青区</button>
			</div>
			<div class="col-xs-3">
				<%-- <button type="button" class="btn btn-default"
					style="background:#eee">津南区</button>--%>
								<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1046'/>'" style="border-color: #487bba;color:#487bba">津南区</button> 
			</div>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">北辰区</button>--%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1045'/>'"  style="border-color: #487bba;color:#487bba">北辰区</button> 
			</div>

		</div>
		<hr>
		<div class="content">
			<p>新五区</p>
			<div class="col-xs-3">
				<button type="button" class="btn btn-default"
					onclick="javascript:window.location.href='<c:url value='/areaForTencent/1037'/>'"
					style="border-color: #487bba;color:#487bba">静海区</button>
			</div>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">武清区</button>--%> 
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1047'/>'" style="border-color: #487bba;color:#487bba">武清区</button>
			</div>
			<div class="col-xs-3">
				<%-- <button type="button" class="btn btn-default"
					style="background:#eee">宝坻区</button>--%>
								<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1048'/>'" style="border-color: #487bba;color:#487bba">宝坻区</button> 
			</div>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">宁河区</button>--%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1049'/>'"  style="border-color: #487bba;color:#487bba">宁河区</button> 
			</div>
			<div class="col-xs-3">
				<%--<button type="button" class="btn btn-default"
					style="background:#eee">蓟州区</button>--%>
				 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/areaForTencent/1050'/>'" style="border-color: #487bba;color:#487bba">蓟州区</button> 
			</div>
		</div>
		<hr>
		<div class="content">
			<p>滨海新区</p>
			<div class="row rowclass">
				<div class="col-xs-6">
					<%--<button type="button" class="btn btn-default"
						style="background:#eee">登记中心</button>--%>
					 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/area/1051'/>'"  style="border-color: #487bba;color:#487bba">登记中心</button> 
				</div>
				<div class="col-xs-6">
					<button type="button" class="btn btn-default"
						style="background:#eee">开发区</button>
					<%-- 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/area/1105'/>'">开发区</button> --%>
				</div>
				</div>
				<div class="row rowclass">
				<div class="col-xs-6">
					<button type="button" class="btn btn-default"
						style="background:#eee">高新区</button>
					<%-- 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/area/1106'/>'">高新区</button> --%>
				</div>
				<div class="col-xs-6">
					<%--<button type="button" class="btn btn-default"
						style="background:#eee">东疆港</button>--%>
					 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/area/1108'/>'"   style="border-color: #487bba;color:#487bba">东疆港</button> 
				</div>
				
			</div>
			<div class="row rowclass">
			<div class="col-xs-6">
					<%--<button type="button" class="btn btn-default"
						style="background:#eee">滨海第一中心塘沽</button>--%>
					 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/area/1101'/>'" style="border-color: #487bba;color:#487bba">第一中心塘沽</button> 
				</div>
				<div class="col-xs-6">
					<%--<button type="button" class="btn btn-default"
						style="background:#eee">滨海第二中心汉沽</button>--%>
					 				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/area/1102'/>'"  style="border-color: #487bba;color:#487bba">第二中心汉沽</button> 
				</div>
				
			</div>
			<div class="row rowclass">
			<div class="col-xs-6">
					<%-- <button type="button" class="btn btn-default"
						style="background:#eee">滨海第三中心大港</button> --%>
									<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/area/1103'/>'"  style="border-color: #487bba;color:#487bba">第三中心大港</button>
				</div>
				<div class="col-xs-6">
					<%-- <button type="button" class="btn btn-default"
						style="background:#eee">滨海第四中心油田</button>--%>
									<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<c:url value='/area/1104'/>'"  style="border-color: #487bba;color:#487bba">第四中心油田</button> 
				</div>
				
			</div>

		</div>
		<hr>
		<div class="content">
			<p class="title">说明:</p>
			<span>提示: 目前置灰的区县暂未开通，后续会陆续开通，敬请期待!</span>
		</div>
	</div>
	<hr>
	<div class="bottomBtn">
		<!-- 		<a href="#">确认选择</a> -->
	</div>
<!-- 	<%@include file="./include/footer.jsp"%> -->
	<%@ include file="include/commonjs.jsp"%>
	<%@include file="./include/tencentFooter.jsp"%>
	<script>
		$('#myTabs a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		});
	</script>
</body>
</html>