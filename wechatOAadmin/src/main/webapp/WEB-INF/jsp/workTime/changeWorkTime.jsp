<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>类型限制列表</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<jsp:include page="../include/headTag.jsp" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery-ui-1.9.2.custom.min.css"/>"
	type="text/css"></link>

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js"/>"></script>



<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
		// Hover states on the static widgets
		$("#dialog-link, #icons li").hover(function() {
			$(this).addClass("ui-state-hover");
		}, function() {
			$(this).removeClass("ui-state-hover");
		});
	});
</script>
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->
	<jsp:include page="../include/header.jsp" />

	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/changeWorkTime/1" name="menu" />
	</jsp:include>
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span
				class="divider">/</span></li>
			<li class="active">修改时间</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					<div id="tabs" style="width:100%;margin:0 auto;">
						<span>请选择设置几个时间段</span>
						<ul>
							<li><a href="#tabs-1">一个时间段</a></li>
							<li><a href="#tabs-2">两个时间段</a></li>
							<li><a href="#tabs-3">三个时间段</a></li>
							<li><a href="#tabs-4">四个时间段</a></li>
						</ul>
						<div id="tabs-1">
							<form action='<c:url value="/changeWorkTime/1"/>' method="post">
								<table>
									<tr>
										<td><input type="text" name="time11" id="time11" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time11" class="text-error"></span> <input type="text"
											name="time12" id="time12" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time12"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time1Num" id="time1Num"
											value="" />
										</td>
									</tr>
								</table>
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>类型名称</th>
											<th>时段1</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${childType}" var="item" varStatus="status">
											<tr
												<c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
												<td>${(status.index + 1)}</td>
												<td>${item.noticeName }</td>
												<td><input type="text" name="${item.id}typeNum1" />
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<input type="submit" value="提交" />
							</form>
						</div>
						<div id="tabs-2">
							<form action='<c:url value="/changeWorkTime/2"/>' method="post">
								<table>
									<tr>
										<td><input type="text" name="time11" id="time11" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time11" class="text-error"></span> <input type="text"
											name="time12" id="time12" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time12"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time1Num" id="time1Num"
											value="" />
										</td>
									</tr>
									<tr>
										<td><input type="text" name="time21" id="time21" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time21" class="text-error"></span> <input type="text"
											name="time22" id="time22" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time22"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time2Num" id="time2Num"
											value="" />
										</td>
									</tr>
								</table>
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>类型名称</th>
											<th>时段1</th>
											<th>时段2</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${childType}" var="item" varStatus="status">
											<tr
												<c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
												<td>${(status.index + 1)}</td>
												<td>${item.noticeName }</td>
												<td><input type="text" name="${item.id}typeNum1" /></td>
												<td><input type="text" name="${item.id}typeNum2" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<input type="submit" value="提交" />
							</form>
						</div>
						<div id="tabs-3">
							<form action='<c:url value="/changeWorkTime/3"/>' method="post">
								<table>
									<tr>
										<td><input type="text" name="time11" id="time11" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time11" class="text-error"></span> <input type="text"
											name="time12" id="time12" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time12"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time1Num" id="time1Num"
											value="" />
										</td>
									</tr>
									<tr>
										<td><input type="text" name="time21" id="time21" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time21" class="text-error"></span> <input type="text"
											name="time22" id="time22" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time22"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time2Num" id="time2Num"
											value="" />
										</td>
									</tr>
									<tr>
										<td><input type="text" name="time31" id="time31" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time31" class="text-error"></span> <input type="text"
											name="time32" id="time32" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time32"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time3Num" id="time3Num"
											value="" />
										</td>
									</tr>
								</table>
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>类型名称</th>
											<th>时段1</th>
											<th>时段2</th>
											<th>时段3</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${childType}" var="item" varStatus="status">
											<tr
												<c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
												<td>${(status.index + 1)}</td>
												<td>${item.noticeName }</td>
												<td><input type="text" name="${item.id}typeNum1" /></td>
												<td><input type="text" name="${item.id}typeNum2" /></td>
												<td><input type="text" name="${item.id}typeNum3" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<input type="submit" value="提交" />
							</form>
						</div>
						<div id="tabs-4">
							<form action='<c:url value="/changeWorkTime/4"/>' method="post">
								<table>
									<tr>
										<td><input type="text" name="time11" id="time11" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time11" class="text-error"></span> <input type="text"
											name="time12" id="time12" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time12"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time1Num" id="time1Num"
											value="" />
										</td>
									</tr>
									<tr>
										<td><input type="text" name="time21" id="time21" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time21" class="text-error"></span> <input type="text"
											name="time22" id="time22" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time22"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time2Num" id="time2Num"
											value="" />
										</td>
									</tr>
									<tr>
										<td><input type="text" name="time31" id="time31" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time31" class="text-error"></span> <input type="text"
											name="time32" id="time32" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time32"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time3Num" id="time3Num"
											value="" />
										</td>
									</tr>
									<tr>
										<td><input type="text" name="time41" id="time41" value=""
											class="Wdate" onClick="WdatePicker({dateFmt:'H:mm'})" /> <span
											id="time41" class="text-error"></span> <input type="text"
											name="time42" id="time42" value="" class="Wdate"
											onClick="WdatePicker({dateFmt:'H:mm'})" /> <span id="time42"
											class="text-error"></span>
										</td>
										<td><input type="text" name="time4Num" id="time4Num"
											value="" />
										</td>
									</tr>
								</table>
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>类型名称</th>
											<th>时段1</th>
											<th>时段2</th>
											<th>时段3</th>
											<th>时段4</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${childType}" var="item" varStatus="status">
											<tr
												<c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
												<td>${(status.index + 1)}</td>
												<td>${item.noticeName }</td>
												<td><input type="text" name="${item.id}typeNum1" /></td>
												<td><input type="text" name="${item.id}typeNum2" /></td>
												<td><input type="text" name="${item.id}typeNum3" /></td>
												<td><input type="text" name="${item.id}typeNum4" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<input type="submit" value="提交" />
							</form>
						</div>

					</div>
				</div>
				<jsp:include page="../include/footer.jsp" />
			</div>
		</div>
	</div>

</body>
<script type="text/javascript"
	src="<c:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>

<script type="text/javascript">
	//为了提交tab中显示的form
	function submitForm() {
		var $tabs = $('#tabs').tabs();
		var selected = $tabs.tabs('option', 'selected');
		$(selected).children("form").submit();
	}
	$(function() {

	});
</script>
</html>


