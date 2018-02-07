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
.modal-content>canvas {
	width: 100% !important;
	height: auto;
}
</style>
</head>
<body id="myOrder">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" id="code"
				style="padding:48px;text-align:center;">
				<p style="margin-bottom:15px;font-size:1.2em;">
					本二维码用于在柜台取号使用<br />（如有多个预约，扫描一次即可）
				</p>
			</div>
		</div>
	</div>

	<div class="top">
		<div class="col-xs-3">
			<a href="<c:url value='/static/myAccount'/>">
				<div class="leftArrow"></div> </a>
		</div>
		<div class="col-xs-6">我的预约</div>
	</div>
	<div class="content">
		<ul>
			<c:if test="${empty orderRecords }">
				<li>
					<p>
						您还未预约过<a href="<c:url value='/static/agreement'/>"
							style="position:relative;margin-top:10px">我要预约</a>
					</p></li>
			</c:if>
			<c:forEach items="${orderRecords }" var="item">
				<li>
					<p>
						状态：

						<c:if test="${item.status eq 10}">由于当前人数较多，正在排队中...</c:if>
						<c:if test="${item.status eq 11}">排队结束，您未能排队成功</c:if>
						<c:if test="${item.status eq 1}">待审核</c:if>

						<c:if test="${item.status eq 2}">
							<span style="color:green;">审核通过，等待办理</span>
							<a href="#foo" data-toggle="modal" data-target="#myModal"
								style="position:relative;margin-top:5px">我的预约二维码</a>
						</c:if>
						<c:if test="${item.status eq 3}">已取消</c:if>
						<c:if test="${item.status eq 4}">已办结</c:if>
						<c:if test="${item.status eq 5}">爽约</c:if>
						<c:if test="${item.status eq 6}">补正（需重新预约）</c:if>
						<c:if test="${item.status eq 8}">审核后取消</c:if>
						<c:if test="${item.status eq 9}">
							<span style="color:red;">审核失败（请提交正确信息，并重新预约）</span>
						</c:if>
					</p>
					<p>
						预约时间：<span>${item.transactDate } ${item.transactTime }</span>
					</p>
					<p>
						不动产坐落：<span>${item.hourseAddress }</span>
						<c:if test="${item.status eq 1 || item.status eq 2}">
							<a href="javascript:void(0);"
								onclick="shenfm(${item.id}, '${item.transactDate }');">取消</a>
						</c:if>
					</p>
					<p>
						业务类型：<span>${item.transactTypeName }</span>
					</p>
					<p>
						办理机构：<span>${item.transactOrgName }</span>
					</p></li>
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
<script type="text/javascript">
	$(function(){
		
		var idcardmd5 = '${idcardmd5}';
		$("#code").qrcode({ 
		    render: "canvas", //table/canvas方式 
	  	    text: "http://www.tjsbdcdjzx.com/hardware/u/"+ idcardmd5
			//text: "http://192.168.1.56:9088/projectmasterweb/hardware/u/"+ idcardmd5
		});
	});
	
	function shenfm(orderId, transactDate) {  
		if (!confirm("确认取消预约？")) {  
			window.event.returnValue = false;  
		}else{
			var mydate = new Date();
			var month = mydate.getMonth() + 1;
			var date = mydate.getDate();
			
			if(month >= 1 && month <= 9){
				month = "0"+month;
			}
			if(date >= 1 && date <= 9){
				date = "0"+date;
			}
			var todayTime = mydate.getFullYear()+"-"+month+"-"+date;
			
			if(todayTime == transactDate){
			   	alert("办理当天不可取消！");
				return;
			}
			
			if(todayTime > transactDate){
			   	alert("当前时间不可取消！");
				return;
			}
			
			//预约日期换算成毫秒
			var applyyear = transactDate.split("-")[0];
			var applymonth = transactDate.split("-")[1]-1;
			var applyday = transactDate.split("-")[2]; 
			var applydate1 = new Date(applyyear,applymonth,applyday);
			var checkNum = applydate1.getTime() - mydate.getTime();
			
			var hour8 = 28800000; //8小时换成毫秒
			if(checkNum < hour8){
				if (mydate.getHours() > 15) {
					alert("办理前一天下午16:00以后不可取消");
					return;
				}
			}
			
			var url = '<c:url value="/cancelOrder/"/>'+orderId;
			$.get(url, function(result){
				if(result == 'success'){
					alert("取消成功！");
					var url ="http://www.tjsbdcdjzx.com/projectmasteradmin/heepayNotify/cancelBook/" + orderId;
					$.post(url, {}, function(result){});
					window.location.href=window.location.href;
				}
				else if(result=='failure'){
				alert("取消失败！请稍后重试");
				}
				else
				{
				var url ="http://www.tjsbdcdjzx.com/projectmasteradmin/heepayNotify/cancelBook/" + orderId;
					$.post(url, {}, function(result){});
					window.location.href=result;
				}
			});
		}
	}
</script>
</html>