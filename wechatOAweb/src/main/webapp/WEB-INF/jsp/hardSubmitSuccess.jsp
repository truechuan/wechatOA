<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/nav.jsp"%>
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
<%@ include file="include/commoncss.jsp"%>
<style type="text/css">
html {
	height: 960px;
}

label {
	width: 270px;
	height: 40px;
}

.content>p {
	font-size: 30px;
}

.content>span {
	font-size: 30px;
}

body {
	padding: 0;
	font-size: 30px;
	text-align: center;
}

.top {
	width: 100%;
	height: 60px;
	line-height: 40px;
	background: #487bba;
	font-size: 25px;
	color: #fff;
	text-align: center;
	position: relative;
	top: 0px;
}

#order label {
	font-size: 30px;
	width: 70%;
}

.divLeft {
	font-size: 30px;
	width: 15%;
	float: left;
	margin-left: 30%;
}

.divRight {
	font-size: 30px;
	width: 30%;
	float: left;
}

.divRight label {
	margin-top: 12px;
	margin-bottom: 12px;
}

.bottomBtn>a {
	font-size: 30px;
}
</style>
</head>
<body id="order">
	<div class="top" style="">
		<!-- 		<div class="col-xs-3"> -->
		<!-- 			<a  href="javascript:history.go(-1);"> -->
		<!-- 				<div class="leftArrow"></div> -->
		<!-- 			</a> -->
		<!-- 		</div> -->
		<div class="col-xs-12">预约登记</div>
	</div>
	<div class="content">
		<div>
			<p>
				<img src="<c:url value='/resources/img/titleIco2.png'/>">预约
			</p>
		</div>
		<div class="btn-group">
			<div class="divLeft" style="">
				办理机构<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<label>${transactOrgName }</label>
			</div>
		</div>
		<div class="btn-group">
			<div class="divLeft" style="">
				子业务类型<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<label>${transactTypeName }</label>
			</div>
		</div>
		<div class="btn-group">
			<div class="divLeft" style="">
				办理日期<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<label>${transactDate }</label>
			</div>
		</div>

		<div class="btn-group">
			<div class="divLeft" style="">
				办理时间段<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<label>${transactTime }</label>
			</div>
		</div>
		<div>
			<p>
				<img src="<c:url value='/resources/img/titleIco1.png'/>">个人信息
			</p>
		</div>
		<div class="btn-group">
			<div class="divLeft" style="">
				姓名<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<label>${name }</label>
			</div>
		</div>

		<div class="btn-group">
			<div class="divLeft" style="">
				身份证号<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<label>${idcard }</label>
			</div>
		</div>

		<div class="btn-group">
			<div class="divLeft" style="">
				合同号/证号<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<label>${hourseNumber }</label>
			</div>
		</div>

		<!-- 		<div class="btn-group"> -->
		<!-- 			<div class="divLeft" style=""> -->
		<!-- 				移动电话<span style="color:red;">*</span> -->
		<!-- 			</div> -->
		<!-- 			<div class="divRight" style=""> -->
		<!-- 				<label>${mobile }</label> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<span>提示<br>一个身份证号码，一天只能预约一次，办理前一天下午的16:00前可以取消。</span> <input
			type="hidden" id="hideData" value="${strData }" /> <input
			type="hidden" id="myname" /> <input type="hidden" id="idcard" /> <input
			type="hidden" id="hourseNumber" /> <input type="hidden"
			id="hourseAddress" />
	</div>
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>