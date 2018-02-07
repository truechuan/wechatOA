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

<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0">

<meta name="renderer" content="webkit">
<meta name="description" content="" />
<meta name="keywords" content="" />

<%@ include file="include/commoncss.jsp"%>
<!-- initialize keyboard (required) -->
<script>
	// 	$(function() {
	// 		$('#keyboard').keyboard();
	// 	});
</script>
<style type="text/css">
html {
	height: 960px;
}

select {
	width: 460px;
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

#order input {
	font-size: 30px;
	width: 100%;
}

input::-webkit-input-placeholder { /* WebKit browsers */
	color: red;
}

.divLeft {
	font-size: 30px;
	width: 18%;
	float: left;
	margin-left: 30%;
}

.divRight {
	font-size: 30px;
	width: 30%;
	float: left;
}

.divRight select {
	margin-top: 12px;
	margin-bottom: 12px;
}

.bottomBtn>a {
	font-weight: bolder;
	font-size: 30px;
	line-height: 60px;
	height: 60px;
	font-size: 30px;
}

#demoNumber button {
	width: 75px;
	height: 30px;
	font-size: 20px;
	padding: 0;
	margin: 0;
	border: 0px;
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
		<input type="hidden" name="openid" value="${openid }" />
		<div>
			<p>
				<img src="<c:url value='/resources/img/titleIco2.png'/>">
			</p>
		</div>
		<div class="btn-group">
			<div class="divLeft" style="">
				办理机构<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<select name="transactOrgId"
					style="width:460px; margin-top:12px; margin-bottom:12px;"
					onchange="getWorkTime(this)" disabled="disabled">

					<c:forEach items="${handleOrg }" var="item">
						<option value="${item.id}">${item.dictionaryValue }</option>
					</c:forEach>
				</select>
			</div>
		</div>


		<div class="btn-group">
			<div class="divLeft" style="">
				业务类型<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<select name="bussinessType" style="width:460px; "
					onchange="selectMyChild(this)">
					<option value="0">请选择业务类型</option>
				</select>
			</div>
		</div>




		<div class="btn-group">
			<div class="divLeft" style="">
				子业务类型<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<select name="bussinessTypeChild" style="width:460px; ">
					<option value="0">请选择子业务类型</option>
				</select>
			</div>
		</div>


		<!-- 子业务类型id -->
		<input type="hidden" id="bussinessTypeChildId" />


		<div class="btn-group">
			<div class="divLeft" style="">
				办理日期<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<select name="transactDate" style="width:460px; "
					onchange="getTransactTime();">
					<option value="0">请选择办理日期</option>
				</select>
			</div>
		</div>

		<div class="btn-group">
			<div class="divLeft" style="">
				办理时间段<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<select name="transactTime" style="width:460px; ">
					<option value="0">请选择办理时间段</option>
				</select>
			</div>
		</div>

		<div class="">
			<p>
				<img src="<c:url value='/resources/img/titleIco1.png'/>">个人信息
			</p>
		</div>
		<div class="btn-group">
			<div class="divLeft" style="">
				姓名<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<input type="text" id="myname" name="name" disabled="disabled"
					value="123">
			</div>
		</div>

		<div class="btn-group">
			<div class="divLeft" style="">
				身份证号<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">
				<input type="text" id="idcard" name="idcard" disabled="disabled"
					value="123">
			</div>
		</div>

		<!-- 		<div class="btn-group"> -->
		<!-- 			<div class="divLeft" style=""> -->
		<!-- 				移动电话<span style="color:red;">*</span> -->
		<!-- 			</div> -->
		<!-- 			<div class="divRight" style=""> -->

		<!-- 			</div> -->
		<!-- 		</div> -->
		<!-- 		<input id="mobile" name="mobile" type="hidden"> <input -->
		<!-- 			type="hidden" id="hourseNumber" name="hourseNumber" -->
		<!-- 			placeholder="请输入产权证号或合同号" disabled="disabled" value="123"> -->
		<input type="hidden" id="hourseAddress" name="hourseAddress"
			placeholder="请输入不动产坐落" disabled="disabled" value="123">
		<!--<a href="noticeInfo.html"><p>请选择证件类型</p><div class="rightArrow"></div></a>-->
		<!-- Single button -->
		<div class="btn-group">
			<div class="divLeft" style="">
				产权证/合同号<span style="color:red;">*</span>
			</div>
			<div class="divRight" style="">

				<input type="text" id="hourseNumber" name="hourseNumber"
					placeholder="请输入产权证号或合同号或他项权证号" value="" style="">

			</div>
		</div>

		<div class="btn-group" id="demoNumber" style="display: inline-block;">
			<div class="divLeft" style="
    width: 200px;
">数字键盘</div>
			<div class="divRight" style="padding-left:50px;">

				<div style="display: block;width:250px;">
					<button onclick="demo(this,1)">7</button>
					<button onclick="demo(this,1)">8</button>
					<button onclick="demo(this,1)">9</button>
					<button onclick="demo(this,1)">4</button>
					<button onclick="demo(this,1)">5</button>
					<button onclick="demo(this,1)">6</button>
					<button onclick="demo(this,1)">1</button>
					<button onclick="demo(this,1)">2</button>
					<button onclick="demo(this,1)">3</button>
					<button onclick="demo(this,2)">清除</button>
					<button onclick="demo(this,1)">0</button>
					<button onclick="demo(this,3)">回删</button>
				</div>
			</div>
		</div>
		<br />


		<!-- 		<div class="btn-group"> -->
		<!-- 			<div class="divLeft" style=""> -->
		<!-- 				不动产坐落<span style="color:red;">*</span> -->
		<!-- 			</div> -->
		<!-- 			<div class="divRight" style=""> -->

		<!-- 			</div> -->
		<!-- 		</div> -->
		<span>提示一个身份证号码，一天只能预约一次，办理前一天下午的16:00前可以取消。</span>
		<div class="bottomBtn">
			<a href="javascript:void(0)" onclick="submitData(this)">确定</a>
		</div>

	</div>
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script>
		$(function() {
			$("#demoNumber").hide();
			$("#hourseNumber").click(function() {
				$("#demoNumber").toggle();
			});
		});
		//当点击合同号input时候显示数字键盘

		function demo(obj, tip) {
			if (tip == 1) {
				var con = document.getElementById('hourseNumber').value;
				document.getElementById('hourseNumber').value = con
						+ obj.innerHTML;
			} else if (tip == 2) {
				document.getElementById('hourseNumber').value = "";
			} else if (tip == 3) {
				var con = document.getElementById('hourseNumber').value;
				document.getElementById('hourseNumber').value = con
						.slice(0, -1);
			}
		}
	</script>
	<script>
		$(function() {
			var areaId = $("select[name='transactOrgId']").val();
			getWorkTime(areaId);
			//$("select[name='transactOrgId']").trigger("click");
		});
		function selectMyChild(obj) {
			var areaId = $("select[name='transactOrgId']").val();
			var param = $(obj).val();
			if (param == 0) {
				return false;
			}
			var url = '<c:url value="/static/book/getMyChild"/>';
			$
					.get(
							url,
							{
								'param' : $(obj).val(),
								'handleOrgId' : areaId
							},
							function(result) {
								if (result.rtnCode == '0') {
									var html = '<option value="0">请选择</option>';
									$
											.each(
													result.arrItem,
													function(i, item) {
														html += '<option value="'+item.noticeName+'-'+ item.id + '">'
																+ item.noticeName
																+ '</option>';

													});
									$("select[name='bussinessTypeChild']")
											.empty().append(html);
								}
							});
		}
function onclickWorkTime() {
			var transactTypeId = $("select[name='bussinessTypeChild']").val();
			var areaId = $("select[name='transactOrgId']").val();
			var date = $("select[name='transactDate']").val();
			if (areaId == 0) {
				alert('请先选择办理机构！');
				return;
			}
			if (date == 0) {
				alert('请先选择办理日期！');
				return;
			}
			if (transactTypeId == 0) {
				alert('请先选择类型！');
				return;
			}
		}
		function getWorkTime(areaId) {
			if (areaId == 0) {
				var html = '<option value="0">请选择</option>';
				$("select[name='transactTime']").empty().append(html);
				var html = '<option value="-1">请选择</option>';
				$("select[name='area']").empty().append(html);
				return;
			}
			handleOrgChange(areaId);
		}

		function getTransactTime() {

			var transactTypeId = $("select[name='bussinessTypeChild']").val();
			var areaId = "${area}";
			var date = $("select[name='transactDate']").val();

			if (areaId == "") {
				alert("页面已过期，请重新选区");
				return;
			}
			if (date == 0) {
				alert("请选择办理日期");
				return;
			}
			if (transactTypeId == 0) {
				alert("请选择类型");
				return;
			}

			var url = '<c:url value="/static/book/getWorkTime"/>';
			$.get(url, {
				'areaId' : areaId,
				'date' : date,
				'transactTypeId' : transactTypeId.split("-")[1],
				'ran':Math.random()
			}, function(result) {
				if (result.rtnCode == '0') {
					var html2 = '<option value="0">请选择</option>';
					$.each(result.arrTime, function(i, item) {
						if (item.members == 0) {
							html2 += '<option value="-9">' + item.time
									+ '(已满)</option>';
						} else {
							html2 += '<option value="'+item.timeId+'">'
									+ item.time + '(剩余：' + item.members
									+ ')</option>';
						}
					});
					
					$("select[name='transactTime']").empty().append(html2);
				}
			});
		}

		function handleOrgChange(areaId) {
			var url = '<c:url value="/static/book/handleOrgChange"/>';
			$
					.get(
							url,
							{
								'areaId' : areaId
							},
							function(result) {
								if (result.rtnCode == '0') {
									// 设置所属行政区
									//var html = '<option value="-1">请选择</option>';
									var html = '';
									$
											.each(
													result.arrArea,
													function(i, item) {
														html += '<option value="'+item.areaCode+'">'
																+ item.areaDesc
																+ '</option>';

													});
									$("select[name='area']").empty().append(
											html);

									// 业务类型设置
									if (result.arrBussinessType != undefined) {
										var html = '';
										$
												.each(
														result.arrBussinessType,
														function(i, item) {
															if (item.dictionaryValue == '使用说明'
																	|| item.dictionaryValue == '须知常见问题') {
																return;
															}
															if ((areaId == 1034 && item.dictionaryValue == '其他登记')
																	|| areaId != 1034) {
																html += '<option value="'+item.id+'">'
																		+ item.dictionaryValue
																		+ '</option>';
															}
														});
										if (html != '') {
											html = '<option value="0">请选择业务类型</option>'
													+ html;
											$("select[name='bussinessType']")
													.empty().append(html);
										}
									}

									// 办理时间设置
									if (result.arrDays != undefined) {
										var html = '';
										$
												.each(
														result.arrDays,
														function(i, item) {
															html += '<option value="'+item.date+'">'
																	+ item.date
																	+ '</option>';
															//'(剩余预约人数：' + item.member+

														});
										if (html != '') {
											html = '<option value="0">请选择办理时间</option>'
													+ html;
											$("select[name='transactDate']")
													.empty().append(html);
										}
									}
								}
							});
		}

		function submitData(obj) {

			var openid = "ov5KSuMZ_s6KgM-h-pYal5ILyhRM";
			var transactOrgId = $("select[name='transactOrgId']").val();
			var transactOrgName = $("select[name='transactOrgId']").find(
					"option:selected").text();
			var transactTypeId = $("select[name='bussinessTypeChild']").val();
			var transactTypeName = $("select[name='bussinessTypeChild']").find(
					"option:selected").text();
			var transactDateId = $("select[name='transactDate']").val();
			var transactDate = $("select[name='transactDate']").find(
					"option:selected").text();
			var transactTimeId = $("select[name='transactTime']").val();
			var transactTime = $("select[name='transactTime']").find(
					"option:selected").text();
			var deadTime = transactTimeId;
			var name = $("input[name='name']").val();
			var idcard = $("input[name='idcard']").val();
			var mobile = $("input[name='mobile']").val();
			var areaId = $("select[name='area']").val();
			var area = $("select[name='area']").find("option:selected").text();
			var hourseNumber = $("input[name='hourseNumber']").val();
			var hourseAddress = $("input[name='hourseAddress']").val();

			//$("#hideData").val(strData);
			if (transactOrgId == 0) {
				alert("办理机构不能为空!");
				return false;
			}

			if (transactTypeId == 0) {
				alert("子业务类型不能为空!");
				return false;
			}

			if (transactDateId == 0) {
				alert("办理日期不能为空!");
				return false;
			} else {
				transactDate = transactDate.split("(")[0];
			}

			if (transactTimeId == 0) {
				alert("办理时间段不能为空!");
				return false;
			} else if (transactTimeId == -9) {
				alert("办理时间段预约人数已满不能选择!");
				return false;
			}

			if (name == "" || name == null) {
				alert("真实姓名不能为空!");
				return false;
			}

			if (idcard == "" || idcard == null) {
				alert("身份证号不能为空!");
				return false;
			}

			if (mobile == "" || mobile == null) {
				$("input[name='mobile']").val("12345667");
				mobile = 123456;
			}

			if (hourseNumber == "" || hourseNumber == null) {
				alert("产权证证号不能为空!");
				return false;
			}

			if (hourseAddress == "" || hourseAddress == null) {
				alert("不动产坐落不能为空!");
				return false;
			}

			if (!confirm("您的预约信息将进行后台审核，是否确认提交？")) {
				return false;
			}

			$(obj).attr("disabled", "disabled");
			var url = '<c:url value="/static/book/hardSubmit"/>';

			var transactTypeTemp = transactTypeId.split("-");
			var transactTypeNameValue = transactTypeTemp[0];
			var transactTypeIdValue = transactTypeTemp[1];
			//把数据拼接起来，放在input当中，用于硬件调取到
			var strData = transactOrgName + "," + transactTypeName + ","
					+ transactDate + "," + transactTime + "," + name + ","
					+ idcard + "," + mobile;
			$
					.ajax({
						type : 'GET',
						url : url,
						data : {
							"openid" : openid,
							"transactOrgId" : transactOrgId,
							"transactOrgName" : encodeURIComponent(transactOrgName),
							"transactTypeId" : transactTypeIdValue,
							"transactTypeName" : encodeURIComponent(transactTypeNameValue),
							"transactDateId" : transactDateId,
							"transactDate" : transactDate,
							"transactTimeId" : transactTimeId,
							"deadTime" : deadTime,
							"area" : areaId,
							"name" : encodeURIComponent(name),
							"idcard" : idcard,
							"mobile" : mobile,
							"hourseNumber" : encodeURIComponent(hourseNumber),
							"hourseAddress" : encodeURIComponent(hourseAddress),
							"strData" : strData,
							'random':Math.random()
						},
						success : function(result) {
							if (result.rtnCode == "9") {
								alert(result.rtnMsg);
							} else {
								//alert("预约申请成功！请等待审核");
								window.location.href = '<c:url value="/book/hardSubmitSuccess" />';
							}
						},
						error : function() {
							alert("异常！");
						}
					});
		}
	</script>
</body>
</html>