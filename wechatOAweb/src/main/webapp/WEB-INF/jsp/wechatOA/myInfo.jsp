<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include/nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>个人信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <%@ include file="../include/commoncss.jsp" %>
</head>
<body>
<div class="">
    <div class="top">
        <div class="col-xs-3">
            <a href="javascript:history.go(-1);">
                <div class="leftArrow"></div>
            </a>
        </div>
        <div class="col-xs-6">个人信息</div>
    </div>
    <div class="container-fluid marginTop mainInfo" style="">
        <div class="myRow" style="height: 90px;">
            <span>头像</span>
            <span class="right" style="line-height: 90px;">
                <img src="${user.avatar}" alt="" width="80" height="80">
            </span>
        </div>
        <div class="myRow">
            <hr>
            <span>姓名</span>
            <span class="right">
                ${user.name}
            </span>
            <hr>
        </div>
        <div class="myRow">
            <span>性别</span>
            <span class="right">
                ${user.genderStr}
            </span>
            <hr>
        </div>
        <%--<div class="myRow">--%>
            <%--<span>二维码</span>--%>
            <%--<span class="right">--%>
                <%--XXX>--%>
            <%--</span>--%>
            <%--<hr>--%>
        <%--</div>--%>
    </div>
    <div class="container-fluid mainInfo" style="margin-top:15px;">
        <div class="myRow">
            <span>手机</span>
            <span class="right">
                ${user.mobile}
            </span>
            <hr>
        </div>
        <%--<div class="myRow">--%>
            <%--<span>座机</span>--%>
            <%--<span class="right">--%>
                <%--XXX>--%>
            <%--</span>--%>
            <%--<hr>--%>
        <%--</div>--%>
        <div class="myRow">
            <span>邮箱</span>
            <span class="right">
                ${user.email}
            </span>
            <hr>
        </div>
    </div>
    <div class="container-fluid mainInfo" style="margin-top:15px;">
        <div class="myRow">
            <c:forEach items="${user.departmentStr}" var="item" varStatus="status">
                <span>${item}</span><br>
            </c:forEach>
            <hr>
        </div>
    </div>
</div>
</body>
</html>