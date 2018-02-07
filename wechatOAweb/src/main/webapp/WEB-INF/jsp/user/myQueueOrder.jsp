<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="description" content="" />
<meta name="keywords" content="" />
<%@ include file="../include/commoncss.jsp"%>
<style>
</style>
</head>
<body id="myOrder">
	<div class="top" style="background:#FF8247;">
		<div class="col-xs-3">
			<a href="<c:url value='/static/myAccount'/>">
				<div class="leftArrow"></div> </a>
		</div>
		<div class="col-xs-6">排队结果</div>
	</div>
	<div class="content">
		<ul>
			<c:if test="${empty orderRecords }">
				<li>
					<p>
						没有需要排队的预约<a href="<c:url value='/static/agreement'/>"
							style="position:relative;margin-top:10px">我要预约</a>
					</p>
				</li>
			</c:if>
			<c:forEach items="${orderRecords }" var="item">
				<li>
					<p>
						状态：
						<c:if test="${item.isFlushOver eq 1}">
							<span style="color:green;">排队成功，等待审核</span>
						</c:if>
						<c:if test="${item.isFlushOver eq 2}">
							<span style="color:red;">排队失败</span>
						</c:if>
						<c:if test="${item.isFlushOver eq 0}">排队处理中......</c:if>
					</p>
					<p>
						预约时间：<span>${item.transactDate } ${item.transactTime }</span>
					</p>
					<p>
						<c:choose>
							<c:when test="${item.isFlushOver eq 0}">
							<span style="color:red;">
								若已预约超过10分钟，默认为排队失败,请重新预约
								</span>
							</c:when>
							<c:otherwise>
								不动产坐落：<span>${item.hourseAddress }</span>
							</c:otherwise>
						</c:choose>
					</p>
					<p>
						业务类型：<span>${item.transactTypeName }</span>
					</p>
					<p>
						办理机构：<span>${item.transactOrgName }</span>
					</p>
				</li>
			</c:forEach>
		</ul>
	</div>
	<c:choose>
		<c:when test="${sessionScope.isTencent == 'true' }">
			<%@include file="../include/tencentFooter.jsp"%>
		</c:when>
		<c:otherwise>
			<%@include file="../include/footer.jsp"%>
		</c:otherwise>
	</c:choose>
	<%@ include file="../include/commonjs.jsp"%>
	<script src="http://cdn.bootcss.com/jquery/3.0.0-beta1/jquery.js"></script>
	<script
		src="http://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
</body>



</html>