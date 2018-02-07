<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/nav.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="description" content="天津市不动产登记中心" />
<meta name="keywords" content="天津市不动产登记中心" />
<link href="<c:url value='/resources/css/main.css'/>" type="text/css" rel="stylesheet">
<link href="<c:url value='/resources/css/style.css'/>" type="text/css" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap.min.css'/>" type="text/css" rel="stylesheet">
</head>
<body id="index">
	<section class="index-head">
	<div class="switch-panel">
		<!--轮播-->
		<div class="tempWrap">
			<ul class="bd">
				<li class="touchslide-content-item"><img src="<c:url value='/resources/img/indexBanner.jpg'/>" class="index_img"></li>
			</ul>
		</div>
	</div>
	</section>
	<div>
		<!--办事图标-->
		<div class="index_tb table">
			<ul class="table-row">
				<li class="table-cell index_li"><a
					href="<c:url value='/static/noticeEntry.html'/>"> <img
						src="<c:url value='/resources/img/banshi1.png'/>">
						<p class="affairs-sorts-item-txt">办件指引</p> </a></li>
				<li class="table-cell index_li"><a
					href="<c:url value='/static/agreement'/>"> <img
						src="<c:url value='/resources/img/banshi2.png'/>">
						<p class="affairs-sorts-item-txt">登记预约</p> </a></li>
				<li class="table-cell index_li"><a
					href="<c:url value='/static/0/announcements.html'/>"> <img
						src="<c:url value='/resources/img/banshi3.png'/>">
						<p class="affairs-sorts-item-txt">通知公告</p> </a></li>
			</ul>
			<ul class="table-row">
				<li class="table-cell index_li"><a href="${MapUrl}"> <img
						src="<c:url value='/resources/img/banshi4.png'/>">
						<p class="affairs-sorts-item-txt">办理地点</p> </a></li>
				<li class="table-cell index_li"><a
					href="javascript:showTipsForService();"> <img
						src="<c:url value='/resources/img/banshi5.png'/>">
						<p class="affairs-sorts-item-txt">客服入口</p> </a></li>
				<li class="table-cell index_li"><a
					href="<c:url value='/static/myAccount'/>"> <img
						src="<c:url value='/resources/img/banshi6.png'/>">
						<p class="affairs-sorts-item-txt">个人中心</p> </a></li>
			</ul>
		</div>
		<!--办事图标END-->
		    <!--通知公告-->
    <div class="lhead" style="position: relative;">通 知 公 告<span class="more" style="font-size: smaller; font-weight: 500; position: absolute; right:10px;" onclick="javascript:window.location.href='<c:url value='/announcementList'/>'">更多&gt;&gt;</span></div>
    <div class="maquee">
        <ul class="tzlist">
        <c:forEach var="item"   items="${announcements }">
        <li onclick="javascript:window.location.href='${item.url }'">${item.title }</li>
        </c:forEach>
<!--             <li onclick="javascript:window.location.href='<c:url value='/static/355/announcement.html'/>'">关于房屋建筑安全的公告</li> -->
<!--             <li onclick="javascript:window.location.href='<c:url value='/static/329/announcement.html'/>'">预约平台恢复正常运营</li> -->
<!--             <li onclick="javascript:window.location.href='<c:url value='/static/302/announcement.html'/>'">声明</li> -->
<!--             <li onclick="javascript:window.location.href='http://mp.weixin.qq.com/s/NLLVlhnYd-b699wAAlYbPg'">天津市不动产登记预约服务平台使用说明</li> -->
<!--             <li onclick="javascript:window.location.href='http://mp.weixin.qq.com/s/9gTiL9G5_N5A-oQlnE4U_A'">关于天津市实施不动产统一登记有关事项的公告</li> -->
<!--             <li> </li> -->
<!--             <li> </li> -->
<!--             <li> </li> -->
        </ul>
    </div>
		<!--拓展功能-->
		<a href="<c:url value='/queryFileStatus'/>" class="index_a"><img src="<c:url value='/resources/img/tzgn.jpg'/>"
			class="index_img">
		</a>
		<!--底部按钮-->
	<div style="margin-bottom:60px;width:100px;height:100px;"></div>
		<!--底部按钮-->
    <%@include file="./include/footer.jsp"%>
    <!--底部按钮-->
	</div>
</body>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	function showTipsForService() {
		alert("如需联系客服请在天津市不动产登记预约公众号界面下方选择键盘回复即可");
		WeixinJSBridge.call('closeWindow');
	}
	    function autoScroll(obj){
        $(obj).find("ul.tzlist").animate({
            marginTop : "-156px"
        },500,function(){
            $(this).css({marginTop : "0px"}).find("li:lt(4)").appendTo(this);
        })
    }
    $(function(){
        setInterval('autoScroll(".maquee")',5000);
    })
</script>
</html>