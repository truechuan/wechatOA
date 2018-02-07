<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>预约登记列表</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<jsp:include page="../include/headTag.jsp" />
<style type="text/css">
span {
	cursor: pointer;
	color: #0088cc;
}
</style>
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
		<jsp:param value="/loadOrderRecords/1" name="menu" />
	</jsp:include>
	<div class="content" style="margin-left:150px;">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span
				class="divider">/</span></li>
			<li class="active">预约信息</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<div>
					<span style="color:red;">注：每次审核都将给用户推送微信消息，请确保您审核的正确性！以免造成对用户的打扰</span>
					<p>
						<br />
				</div>
				<div class="well">
					<form class="form-search" method="post" id="setchInfo"
						action="<c:url value='/loadOrderRecords/1' />">
						用户名：<input type="text" name="name" value="${name }" /> 身份证号：<input
							type="text" name="idcard" size="22" value="${idcard }" />
						房产登记字号：<input type="text" name="hourseNumber" size="22"
							value="${hourseNumber }" /> 办理日期：<input class="Wdate"
							type="text" name="transactDate"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							value="${transactDate}" /> 预约类型：<input type="text"
							name="transactTypeName" value="${transactTypeName }" /> 手机号：<input
							type="text" name="mobile" size="11" value="${mobile }" /> 办理机构：<input
							type="text" name="transactOrgName" size="22"
							value="${transactOrgName }" />
						预约状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select name="status">
							<option value="0" <c:if test="${status == 0}">selected</c:if>>全部</option>
							<option value="1" <c:if test="${status == 1}">selected</c:if>>待审核</option>
							<option value="2" <c:if test="${status == 2}">selected</c:if>>审核通过</option>
							<option value="3" <c:if test="${status == 3}">selected</c:if>>审核前取消</option>
							<option value="4" <c:if test="${status == 4}">selected</c:if>>办结</option>
							<option value="5" <c:if test="${status == 5}">selected</c:if>>爽约</option>
							<option value="6" <c:if test="${status == 6}">selected</c:if>>补正</option>
							<option value="8" <c:if test="${status == 8}">selected</c:if>>审核后取消</option>
							<option value="9" <c:if test="${status == 9}">selected</c:if>>不通过</option>
						</select>
						<button type="submit" class="btn" id="submitSearch">Search</button>
					</form>
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>用户名</th>
								<th>openid</th>
								<th>身份证号</th>
								<th>业务类型</th>
								<th>办理日期</th>
								<th>时间段</th>
								<th>行政区</th>
								<th>房产登记字号</th>
								<th>房产证地址</th>
								<th>预约时间</th>
								<th>状态</th>
								<th>自动审核结果</th>
								<th>审核操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderRecords}" var="item" varStatus="status">
								<tr <c:if test="${status.index % 2 == 0 }">class="info"</c:if>>
									<td>${(status.index + 1) + (pages.currentPage - 1) *
										pages.pageRecord }</td>
									<td>${item.name }</td>
									<!--<td>${item.mobile }</td>-->
									<td>${fn:substring(item.openid, 0, 15)}<br />${fn:substring(item.openid,
										15, 28)}</td>
									<td style="width:40px">${fn:substring(item.idcard, 0, 8)}<br />${fn:substring(item.idcard,
										8, 18)}</td>
									<!--<td width="90px">${item.transactOrgName }</td>-->
									<td width="90px">${item.transactTypeName }</td>
									<td>${item.transactDate }</td>
									<td>${item.transactTime }</td>
									<td><c:if test="${item.area eq 0}">市区</c:if> <c:if
											test="${item.area eq 1}">和平区</c:if> <c:if
											test="${item.area eq 2}">河西区</c:if> <c:if
											test="${item.area eq 3}">河北区</c:if> <c:if
											test="${item.area eq 4}">河东区</c:if> <c:if
											test="${item.area eq 5}">南开区</c:if> <c:if
											test="${item.area eq 6}">红桥区</c:if> <c:if
											test="${item.area eq 7}">东丽区</c:if> <c:if
											test="${item.area eq 8}">西青区</c:if> <c:if
											test="${item.area eq 9}">津南区</c:if> <c:if
											test="${item.area eq 10}">北辰区</c:if> <c:if
											test="${item.area eq 11}">武清区</c:if> <c:if
											test="${item.area eq 12}">宝坻</c:if> <c:if
											test="${item.area eq 13}">蓟县</c:if> <c:if
											test="${item.area eq 14}">静海</c:if> <c:if
											test="${item.area eq 15}">宁河</c:if> <c:if
											test="${item.area eq 16}">滨海新区</c:if> <c:if
											test="${item.area eq 17}">滨海第一中心</c:if> <c:if
											test="${item.area eq 18}">滨海第二中心</c:if> <c:if
											test="${item.area eq 19}">滨海第三中心</c:if> <c:if
											test="${item.area eq 20}">滨海第四中心</c:if> <c:if
											test="${item.area eq 21}">开发区</c:if> <c:if
											test="${item.area eq 22}">高新区</c:if> <c:if
											test="${item.area eq 23}">东疆港</c:if>
									</td>
									<td>${item.hourseNumber}</td>
									<td width="180px;">${item.hourseAddress}</td>
									<td>${fn:substring(item.createTime, 0, 10)}</td>
									<td id="ItemInfo${item.id}"><c:if
											test="${item.status eq 1}">待审核</c:if> <c:if
											test="${item.status eq 2}">
											<B style="color:green">通过</B>
										</c:if> <c:if test="${item.status eq 3}">
											<B>审核前取消</B>
										</c:if> <c:if test="${item.status eq 4}">
											<B>办结</B>
										</c:if> <c:if test="${item.status eq 5}">
											<B>爽约</B>
										</c:if> <c:if test="${item.status eq 6}">
											<B>补正</B>
										</c:if> <c:if test="${item.status eq 8}">
											<B style="color:red">审核后取消</B>
										</c:if> <c:if test="${item.status eq 9}">
											<B style="color:red">不通过</B>
										</c:if>
									</td>
									<td>
									<c:choose>
									<c:when test="${item.status eq 1 &&item.examineStatus eq 0}">待自动审核</c:when>
									<c:when test="${item.status eq 1 &&item.examineStatus eq 1}">待审核</c:when>
									<c:when test="${item.status eq 1 &&item.examineStatus eq 2}"><span style="color:#DDAA00">系统未过转人工</span></c:when>
									<c:when test="${item.status eq 1 &&item.examineStatus eq 3}"><span style="color:#DDAA00">系统超时转人工</span></c:when>
									
									<c:when test="${item.status eq 2 &&item.examineStatus eq 0}"><B style="color:green">人工审核通过</B></c:when>
									<c:when test="${item.status eq 2 &&item.examineStatus eq 1}"><B style="color:green">自动审核通过</B></c:when>
									<c:when test="${item.status eq 2 &&item.examineStatus eq 2}"><B style="color:green">人工审核通过</B></c:when>
									<c:when test="${item.status eq 2 &&item.examineStatus eq 3}"><B style="color:green">人工审核通过</B></c:when>
									<c:when test="${item.status eq 2 &&item.examineStatus eq 4}"><B style="color:green">人工审核通过</B></c:when>
									
									<c:when test="${item.examineStatus eq 4}"><B style="color:green">自动审核前已完成操作</B></c:when>
									<c:when test="${item.examineStatus eq 3}"><span style="color:#DDAA00">系统超时转人工</span></c:when>
									<c:when test="${item.examineStatus eq 2}"><span style="color:#DDAA00">系统未过转人工</span></c:when>
									<c:when test="${item.examineStatus eq 1}"><B style="color:green">自动审核通过</B></c:when>
									<c:when test="${item.examineStatus eq 0}"><B style="color:green">待自动审核</B></c:when>
									<c:when test="${item.examineStatus eq 5}"><span style="color:#DDAA00">需人工审核的业务</span></c:when>
									<c:otherwise>其他</c:otherwise>
									</c:choose>
									</td>
									<td><a href="<c:url value="/orderInfo/${item.id}"/>"
										target="_blank"><B>查看</B> </a> <c:if
											test="${item.status eq 1}"> / 
									<span class="item${item.id}"
												onclick="shenfm('<c:url value="/updateOrderStatus/${item.id}/2/${pages.currentPage }"/>','${item.id}');"><B>通过</B>
											</span> / 
									<span class="item${item.id}"
												onclick="failReason('${item.id}');"><B>不通过</B>
											</span>
										</c:if> <c:if test="${item.status eq 2}"> / 
									<span class="item${item.id}"
												onclick="shenfm('<c:url value="/updateOrderStatus/${item.id}/4/${pages.currentPage }"/>','${item.id}');"><B>办结</B>
											</span> / 
									<span class="item${item.id}"
												onclick="shuangyue('<c:url value="/limitNumControl/${item.id}"/>','${item.id}')"><B>爽约</B>
											</span> / 
									<!-- 
									<span class="item${item.id}"
												onclick="shenfm('<c:url value="/updateOrderStatus/${item.id}/5/${pages.currentPage }"/>','${item.id}');"><B>爽约</B>
											</span> /  -->
											<span class="item${item.id}"
												onclick="shenfm('<c:url value="/updateOrderStatus/${item.id}/6/${pages.currentPage }"/>','${item.id}');"><B>补正</B>
											</span>
										</c:if> <%-- 	              					<a href="#myModal${item.id}" role="button" data-toggle="modal"><i class="icon-remove"></i></a> --%>
										<div class="modal small hide fade" id="myModal${item.id}"
											tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">×</button>
												<h3 id="myModalLabel">确认删除</h3>
											</div>
											<div class="modal-body">
												<p class="error-text">
													<i class="icon-warning-sign modal-icon"></i> 确定删除该记录?
												</p>
											</div>
											<div class="modal-footer">
												<form id="deleteNews${item.id}" method="post"
													action="<c:url value="/notice/${item.id}/${pages.currentPage }"/>">
													<input type="hidden" name="page" value="1" />
													<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
													<input type="submit" class="btn btn-danger" value="删除" />
												</form>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<c:if test="${pages.totalPage > 0}">
						<ul>
							<c:if test="${pages.currentPage > 1}">
								<li><a href="javascript:void(0);"
									onclick="tijiao('${pages.prePage }')">Prev</a></li>
							</c:if>
							<c:forEach begin="${pages.pageNumStart }"
								end="${pages.pageNumEnd }" varStatus="status">
								<li
									<c:if test="${status.index == pages.currentPage }">class="active"</c:if>>
									<a href="javascript:void(0);"
									onclick="tijiao('${status.index }')"> ${status.index } </a>
								</li>
							</c:forEach>
							<c:if test="${pages.currentPage< pages.totalPage}">
								<li><a href="javascript:void(0);"
									onclick="tijiao('${pages.nextPage }')">Next</a></li>
							</c:if>
							<li class="disabled"><a>共${pages.totalPage}页${pages.totalRecord}条记录</a>
							</li>
						</ul>
					</c:if>
					<br /> <a href="<c:url value="/OrderToExcel/Month"/>">导出当月预约的excel</a>
					<br /> <a href="<c:url value="/OrderToExcel/Week"/>">导出本周预约的excel</a>
					<br /> 选择办理日期后再选择按日期导出<input class="Wdate" type="text"
						id="dateForExcel" name="dateForExcel"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${today }" />
					<button id="OKForExcel" onClick="OKForExcel()">确认</button>
				</div>
				<jsp:include page="../include/footer.jsp" />
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<c:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>

<script type="text/javascript">
	function OKForExcel() {
		var date;
		date = $("#dateForExcel").val();
		window.location.href = "<c:url value="/OrderToExcel/"/>"
				+ date.replace(/-/g, "");
	}
	function shenfm(url, id, obj) {
		if (!confirm("审核完将给用户推送微信消息，并且不能修改，是否确认？")) {
			window.event.returnValue = false;
		} else {
			var strStatus;
			$.post(url, {}, function(data) {
				if (data == '1')
					strStatus = "未审核";
				else if (data == '2')
					strStatus = "<B style='color:green'>通过</B>";
				else if (data == '3')
					strStatus = "审核前取消";
				else if (data == '4')
					strStatus = "办结";
				else if (data == '5')
					strStatus = "爽约";
				else if (data == '6')
					strStatus = "补正";
				else if (data == '8')
					strStatus = "审核后取消";
				else if (data == '9')
					strStatus = "<B style='color:red'>不通过</B>";
				$("#ItemInfo" + id).html(strStatus);

			});
			$(".item" + id).attr("onclick", null);
		}
	}
	function tijiao(url) {
		$("#setchInfo").attr("action",
				"<c:url value='/loadOrderRecords/"+url+"' />");
		$("#setchInfo").submit();
	}
</script>
<style>
#showLimit td {
	padding: 10px;
}

#btnSubmit {
	margin: 0;
	padding: 0;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	background: #01AAED;
	width: 60px;
	padding: 4px;
	text-align: center;
	color: #FFFFFF;
}
</style>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-1.8.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/layer/layer.js'/>"></script>
<script type="text/javascript">
	 //弹出一个页面层
	var openLayerIndex;
	function shuangyue(url, id) {

		$
				.ajax({
					url : url,
					type : 'POST',
					dataType : 'text',
					success : function(data) {
						console.log(data);
						var json = JSON.parse(data); //数组 
						console.log(json);
						console.log(json.no_promise_num);
						console.log(json.checked_cancel_num);
						console.log(json.resDays);

						var htmlStr = '<div id="showLimit">'
								+ '	<table cellpadding="0" cellspacing="0" style="border: #D9EDF7 1px solid; width: 100%;">'
								+ '		<tr style="background: #D9EDF7;">'
								+ '			<td width="300px;">历史爽约次数：</td>'
								+ '			<td>'
								+ json.no_promise_num
								+ '</td>'
								+ '		</tr>'
								+ '		<tr>'
								+ '			<td>历史通过审核后取消次数：</td>'
								+ '			<td>'
								+ json.checked_cancel_num
								+ '</td>'
								+ '		</tr>'
								+ '		<tr style="background: #D9EDF7;">'
								+ '			<td>本次爽约自动处罚天数：</td>'
								+ '			<td><input type="text" name="stopDayNum" id="stopDayNum" value="'+json.resDays+'" />天</td>'
								+ '		</tr>'
								+ '		<tr>'
								+ '			<td><input type="hidden" name="orderid" id="orderid" value="'+json.orderId+'" /></td>'
								+ '			<td><div id="btnSubmit" onclick="submitLimit()" >确认</div></td>'
								+ '		</tr>' + '	</table>' + '</div>';

						openLayerIndex = layer.open({
							type : 1,
							title : '用户处罚限制时间',
							maxmin : true,
							shadeClose : true, //点击遮罩关闭层
							area : [ '800px', '520px' ],
							content : htmlStr
						});

					},
					error : function(errMessage) {
						//alert("errMessage"+errMessage);                 
					}
				});

	}

	function submitLimit(orderid) {
		orderid = $("#orderid").val();
		limitDays = $("#stopDayNum").val();
		if (limitDays == "") {
			alert("请输入有效天数！");
			return;
		} else {
			var reg = new RegExp("^[0-9]*$");
			if (!reg.test(limitDays)) {
				alert("请输入有效数字！");
				return;
			}
		}
		//return ;
		$.ajax({
			url : '<c:url value="/limitNumControlSave" />/' + orderid + '/'
					+ limitDays,
			type : 'GET',
			//data:{"orderId":orderid, "limitDays":limitDays},
			dataType : 'text',
			success : function(data) {
				console.log(data);
				var json = JSON.parse(data); //数组 
				console.log(json);
				console.log(json.resCode);
				console.log(json.message);

				layer.close(openLayerIndex);
			},
			error : function(errMessage) {
				//alert("errMessage"+errMessage);                 
			}
		});

	} 

	// 	================================弹出输入框，输入不通过原因==================================
	// 	弹出一个页面层
	var openLayerIndex;
	function failReason(id) {
		var htmlStr = '<div id="showLimit">'
				+ '	<table cellpadding="0" cellspacing="0" style="border: #D9EDF7 1px solid; width: 100%;">'
				+ '		<tr>'
				+ '			<td width="300px;">不通过原因：</td>'
				+ '			<td><input type="text" name="failReason" id="failReason" value="您的信息填写有误，请更正后，重新预约" style="width:500px;" /></td>'
				+ '		</tr>'
				+ '		<tr>'
				+ '			<td><input type="hidden" name="id" id="id" value="'+id+'" /></td><td><div id="btnSubmit" onclick="submitReason();" >确认</div></td>'
				+ '		</tr>' + '	</table>' + '</div>';
		openLayerIndex = layer.open({
			type : 1,
			title : '不通过原因',
			maxmin : true,
			shadeClose : true, //点击遮罩关闭层
			area : [ '700px', '220px' ],
			content : htmlStr
		});
	}
	
	//在弹出框中提交失败理由
	function submitReason() {
		var id = $("#id").val();
		var failReason = $("#failReason").val();
		if (failReason == "") {
			alert("请输入不通过原因！");
			return;
		}
		//return ;
		$.ajax({
			url : '<c:url value="/updateFailReasonSave" />' ,
			type : 'POST',
			data:{"id":id, "status":9,"failReason":failReason},
			dataType : 'text',
			success : function() {
				layer.close(openLayerIndex);
				strStatus = "<B style='color:red'>不通过</B>";
				$("#ItemInfo" + id).html(strStatus);
				//alert(id);
				//$(".item" + id).attr("onclick", null);
			},
			error : function(errMessage) {
				//alert("errMessage"+errMessage);                 
			}
		});
	}
	
</script>


</html>


