<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/nav.jsp"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="description"  content="" />
    <meta name="keywords"  content=""/>
    <%@ include file="include/commoncss.jsp"%>
</head>
<body id="noticeInfo">
	<div class="top">
		<div class="col-xs-3">
			<a  href="javascript:history.go(-1);">
				<div class="leftArrow"></div>
			</a>
		</div>
		<div class="col-xs-6">公告详情</div>
	</div>
	<div class="info" >
	<p class="title" style="color:rgb(95, 156, 239);font-weight: inherit;font-family: inherit;font-size: 137.5%;box-sizing: border-box;">${announcementInfo.title }</p>
	<p class="date">${announcementInfo.createTime }</p>
	<p class="content">${announcementInfo.detailInfo }</p>
	</div>
	
	<%@ include file="include/footer.jsp"%>
</body>
</html>