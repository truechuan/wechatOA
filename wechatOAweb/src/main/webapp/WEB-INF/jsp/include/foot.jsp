<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.table-row {
    display: table-row
}

.table-cell {
    display: table-cell;
    text-align: center;
    padding: 13px 1px;
    width: 20%
}.nav {
    width: 100%;
    overflow: hidden;
    z-index: 20000;
    height: 50px;
    padding-top:10px;
}

div.nav {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    overflow: hidden;
    z-index: 20000;
    height: 50px;
    padding-top: 25px
}

.nav .table-cell {
    padding: 10px
}

.nav-icon-box {
    position: relative;
    width: 22px;
    height: 20px;
    overflow: hidden;
    margin: 0 auto
}

.nav-icon {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%
}

.nav .active .nav-icon {
    top: 0
}

.nav .active .nav-txt {
    color: #27beff
}

.nav a:link, .nav a:visited {
    color: #333
}

.nav-icon-item01 {
    width: 100%
}

.nav-txt {
    font-size: 1.3rem;
}
.nav .nav-txt {
    color: #949494
}

.nav_ul {
    background: #fff;
    border-top: 1px solid #fff
}

.nav .nav-txt2 {
    color: #0a6ba3;
    font-size: 1.0rem
}
ul.table-row li.bc-btn-li {
    padding-top: 20px;
    padding-bottom: 20px
}

ul.table-row li.bc-btn-li:first-child {
    padding-left: 19px;
    padding-right: 10px
}

ul.table-row li.bc-btn-li:last-child {
    padding-right: 19px;
    padding-left: 10px
}
</style>
	<!--底部按钮-->
    <nav class="nav table" >
        <ul class="table-row nav_ul">
            <li class="table-cell">
                <a href="<c:url value='/newarea/${area }'/>" class="active">
                    <div><img src="<c:url value='/resources/img/b1.png'/>" width="25" ></div>
                    <p class="nav-txt">首页</p>
                </a>
            </li>
            <li class="table-cell">
                <a href="<c:url value='/static/agreement'/>">
                    <div><img src="<c:url value='/resources/img/b2.png'/>" width="25" ></div>
                    <p class="nav-txt">预约</p>
                </a>
            </li>
            <li class="table-cell">
                <a href="<c:url value='/static/0/announcements.html'/>">
                    <div><img src="<c:url value='/resources/img/b3.png'/>" width="25" > </div>
                    <p class="nav-txt">公告</p>
                </a>
            </li>
            <li class="table-cell">
                <a href="<c:url value='/static/myAccount'/>">
                    <div><img src="<c:url value='/resources/img/b4.png'/>" width="25" ></div>
                    <p class="nav-txt">我的</p>
                </a>
            </li>
        </ul>
    </nav>
    <!--底部按钮-->