<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include/nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>公告</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <%@ include file="../include/commoncss.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>">
</head>
<body>
<section class="index-head">
    <span>公告</span>
    <a href="/notice/list" class="return"><img src="/resources/img/return.png" alt="" width="30" height="30">返回列表</a>
</section>
<div class="detail">
    <p class="dtitle">欢迎使用电子与通信工程学院办公系统</p>
    <span class="dtime">2018/02/11</span><span class="duser">admin</span>
    <p class="dab">欢迎使用电子与通信工程学院办公系统</p>
    <div class="dbottom">
        <span class="dread">阅读 3</span> <span class="dlike">
        <img class="like1" src="/resources/img/like1.png" alt="" width="18" height="18">
        <img class="like2" src="/resources/img/like2.png" alt="" width="18" height="18" style="display: none;"></span>
    </div>
</div>
</body>
<script type="text/javascript"
        src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".like1").click(function () {
            $(this).hide();
            $(".like2").show();
        });
        $(".like2").click(function () {
            $(this).hide();
            $(".like1").show();
        });
    });
</script>
</html>