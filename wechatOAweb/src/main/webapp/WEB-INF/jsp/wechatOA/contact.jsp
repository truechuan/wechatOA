<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include/nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>通讯录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <%@ include file="../include/commoncss.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>">
</head>
<body>
<section class="index-head">
    <span>${departmentName}</span>
    <span><img class="useradd1" src="/resources/img/useradd1.png" alt="" width="30" height="30"></span>
</section>
<div class="container-fluid">
    <div class="row">
        <div class="form-group mysear">
            <input type="text" class="form-control" id="search" placeholder="搜索">
        </div>
    </div>
</div>
<div class="contact">
    <div class="row list nobor">
        <div class="col-xs-2"><img src="/resources/img/useradd2.png" alt="" width="45" height="45"></div>
        <div class="col-xs-7"><span class="myfont">新的联系人</span></div>
    </div>
</div>
<div class="row">
    <div class="txl">企业通讯录</div>
</div>
<div class="contact">
    <c:forEach items="${users}" var="item" varStatus="status">
        <div class="row list">
            <div class="col-xs-2"><img src="${item.avatar}" alt="" width="45" height="45"></div>
            <div class="col-xs-7"><span class="myfont">${item.name}</span></div>
        </div>
    </c:forEach>

    <c:forEach items="${departments}" var="item" varStatus="status">
        <div class="contact">
            <div class="row list departments_div">
                <input class="dep_id" type="hidden" value="${item.id}"/>
                <div class="col-xs-2"><img class="departments" src="/resources/img/folder.png" alt="" width="45"
                                           height="45"></div>
                <div class="col-xs-7"><span class="myfont departments">${item.name}</span></div>
            </div>
        </div>
    </c:forEach>
    <div class="row list">
        <div class="col-xs-2"><a href="#"><img src="/resources/img/plus.png" alt="" width="45" height="45"></a></div>
        <div class="col-xs-7"><a href="#" class="myfont blue">邀请同事加入…</a></div>
    </div>
</div>
</body>
<script type="text/javascript"
        src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(document).on('click','.departments', function () {
            var parent = $(this).parents(".departments_div");
            var dep_id = parent.find(".dep_id").val();

            if ($(parent).parent(".contact").find(".selectshow1").length > 0) {
                $(parent).parent(".contact").find(".selectshow1").remove();
                return;
            }

            getSigleDepartment(dep_id, $(parent).parent(".contact"));
            //$("div.selectshow1").slideToggle(300);
        });
    });

    function getSigleDepartment(departmentId, parent) {
        $.ajax({
            type: 'GET',
            url: "/contact/singleDepartment",
            data: {
                'departmentId': departmentId
            },
            success: function (result) {
                var usersStr = '';
                var departmentsStr = '';
                if (result.users.length > 0) {
                    for (var i = 0; i < result.users.length; i++) {
                        var item = result.users[i]
                        usersStr += '<div class="row list"><div class="col-xs-2"><img src="' + item.avatar + '" alt="" width="45" height="45">' +
                                '</div> <div class="col-xs-7"><span class="myfont">' + item.name + '</span></div> </div>';
                    }
                }
                if (result.departments.length > 0) {
                    for (var i = 0; i < result.departments.length; i++) {
                        var item = result.departments[i]
                        departmentsStr += '<div class="contact"> <div class="row list departments_div"> <input class="dep_id" type="hidden" value="' + item.id + '"/> ' +
                                '<div class="col-xs-2"><img class="departments" src="/resources/img/folder.png" alt="" width="45" height="45"></div> ' +
                                '<div class="col-xs-7"><span class="myfont departments">' + item.name + '</span></div> </div></div>';
                    }
                }
                if (usersStr.length > 0 || departmentsStr.length > 0) {
                    var htmlStr = '<div class="selectshow1">' + usersStr + departmentsStr + '</div>';
                    $(parent).append(htmlStr);
                }
            }
        });
    }
</script>
</html>