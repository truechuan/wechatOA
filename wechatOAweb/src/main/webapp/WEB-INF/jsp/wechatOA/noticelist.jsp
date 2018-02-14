<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include/nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>公告列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <%@ include file="../include/commoncss.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>">
</head>
<body>
<section class="index-head">
    <span>公告列表</span>
    <a href="#" class="return"><img src="/resources/img/return.png" alt="" width="30" height="30">返回</a>
</section>
<div class="notice">
    <ul>
        <li class="noticelist">
            <a href="/notice/detail/1">
                <p class="ntitle">欢迎使用电子与通信工程学院办公系统</p>
                <p class="nab">欢迎使用电子与通信工程学院办公系统</p>
                <span class="ntime">2018/02/11</span><span class="nuser">admin</span>
            </a>
        </li>
        <li class="noticelist">
            <a href="/notice/detail/2">
                <p class="ntitle">欢迎使用电子与通信工程学院办公系统111</p>
                <p class="nab">欢迎使用电子与通信工程学院办公系统111</p>
                <span class="ntime">2018/02/11</span><span class="nuser">admin</span>
            </a>
        </li>
    </ul>

</div>
</body>
<script type="text/javascript"
        src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">

</script>
</html>