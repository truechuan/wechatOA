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
</head>
<body id="order">
	<div class="top" style="position:absolute;z-index:99999;top:0;">
		<div class="col-xs-3">
			<a href="javascript:history.go(-1);">
				<div class="leftArrow"></div> </a>
		</div>
		<div class="col-xs-6">预约登记</div>
	</div>
	<div class="content">
		<input type="hidden" name="openid"
			value="ov5KSuMZ_s6KgM-h-pYal5ILyhRM" />
		<p>
			<img src="<c:url value='/resources/img/titleIco2.png'/>">预约
		</p>
		<div class="btn-group">
			<p>
				办理机构<span style="color:red;">*</span>
			</p>
			<select name="transactOrgId" style="width:180px; margin-top:12px; "
				onchange="getWorkTime(this)">
				<c:forEach items="${handleOrg }" var="item">
					<option value="${item.id}"
						<c:if test="${area eq item.id}">selected="selected"</c:if>>${item.dictionaryValue
						}</option>
				</c:forEach>
			</select>
		</div>
		<div class="btn-group">
			<p>
				业务类型<span style="color:red;">*</span>
			</p>

			<select name="bussinessType" style="width:180px; margin-top:12px;"
				onchange="selectMyChild(this)">
				<option value="0">请选择业务类型</option>
			</select>
		</div>

		<div class="btn-group">
			<p>
				子业务类型<span style="color:red;">*</span>
			</p>

			<select name="bussinessTypeChild"
				style="width:180px; margin-top:12px;" onchange="selectChild(this)">
				<option value="0">请选择子业务类型</option>
			</select>
			<!-- 子业务类型id -->
			<input type="hidden" id="bussinessTypeChildId" />
		</div>

		<div class="btn-group">
			<p>
				办理日期<span style="color:red;">*</span>
			</p>
			<select name="transactDate" style="width:180px; margin-top:12px;"
				onchange="getTransactTime();">
				<option value="0">请选择办理日期</option>
			</select>
		</div>

		<div class="btn-group">
			<p>
				办理时间段<span style="color:red;">*</span>
			</p>
			<select name="transactTime" style="width:180px; margin-top:12px;">
				<option value="0">请选择办理时间段</option>
			</select>
		</div>
	</div>
	<div class="content">
		<%-- 		<p>移动电话<span style="color:red;">*</span></p><input type="text" style="color:#AAAAAA" name="mobile" value="${userItem.mobile }" readonly="readonly"> --%>
		<p>
			<img src="<c:url value='/resources/img/titleIco1.png'/>">个人信息
		</p>
		<p>
			姓名<span style="color:red;">*</span>
		</p>
		<input type="text" name="name" value="" placeholder="姓名">
		<p>
			身份证号<span style="color:red;">*</span>
		</p>
		<input type="text" name="idcard" value="" placeholder="身份证号">
		<p>
			移动电话<span style="color:red;">*</span>
		</p>
		<input type="text" name="mobile" value="" placeholder="移动电话">
		<!-- Single button -->
		<div class="btn-group">
			<p>
				所属行政区<span style="color:red;">*</span>
			</p>
			<select name="area" style="width:180px; margin-top:12px;">
				<option value="-1">请选择所属行政区</option>

			</select>
		</div>
		<c:if test="${area eq 1035 }">
			<br />
			<span style="color:red; margin-left:13%;margin-right:13%;">二手房过户需在“产权证/合同号”处同时填写产权证号和协议号。</span>
			<br />
		</c:if>

		<p>
			产权证/合同号<span style="color:red;">*</span>
		</p>
		<input type="text" name="hourseNumber" placeholder="请输入产权证号或合同号">
		<p>
			不动产坐落<span style="color:red;">*</span>
		</p>
		<input type="text" name="hourseAddress" placeholder="请输入不动产坐落">
		<c:if test="${area eq 1043 }">
			<p>
				用户类型<span style="color:red;">*</span>
			</p>
			<div class="radioGroup">
				<label><input type="radio" name="usertype" checked="checked"
					value="0">个人 </label><label><input type="radio"
					name="usertype" value="1">企业</label>
			</div>
			<div style="display:none;">
				<p>
					权利人姓名<span style="color:red;">*</span>
				</p>
				<input type="hidden" name="obligeename" placeholder="请输入权利人姓名"
					maxlength="10" value="权利人不填">
				<p>
					权利人身份证号<span style="color:red;">*</span>
				</p>
				<input type="hidden" name="obligeeidcard" placeholder="权利人身份证号"
					maxlength="18" value="111111111111111111">

				<p>
					否是有代理人<span style="color:red;">*</span>
				</p>
				<div class="radioGroup">
					<label><input type="radio" name="isHaveHasagent"
						checked="checked" value="0">是</label><label><input
						type="radio" name="isHaveHasagent" value="1">否</label>
				</div>
			</div>
		</c:if>
		<p>
			验证码<span style="color:red;">*</span>
		</p>
		<input type="text" style="width:30%" name="vCode" placeholder="验证码">
		<span type="text" style="width:35%; "><img id="vimg"
			title="点击更换" onclick="changeCode();" src="<c:url value='/captcha'/>"><br />
		</span>
	</div>
	<div class="content">
		<c:choose>
			<c:when test="${area eq 1035 }">
				<span>提示</span>
				<!--<br />
				<span style="color:red;">1.二手房过户需在“产权证/合同号”处同时填写产权证号和协议号。</span>-->
				<br />
				<span>1.验证码只需填写计算结果，且允许负数。</span>
				<br />
				<span>2.一个身份证号码，一天只能预约一次，办理前一天下午的16:00前可以取消。</span>
			</c:when>
			<c:otherwise>
				<span style="color:red;">提示<br>验证码只需填写计算结果，且允许负数。</span>
				<br />
				<span>提示<br>一个身份证号码，一天只能预约一次，办理前一天下午的16:00前可以取消。</span>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="bottomBtn" style="margin-bottom:60px">
		<a href="javascript:void(0)" onclick="submitData(this)">确定</a>
	</div>
	<c:if test="${isTencent eq 'notTencent' }">
		<%@include file="./include/footer.jsp"%>
	</c:if>
	<input type="text" value="${isTencent }" />
	<%@ include file="include/commonjs.jsp"%>
	<script>
		//刷新验证码
		function changeCode() {
			var imgNode = document.getElementById("vimg");
			imgNode.src = "<c:url value='/captcha'/>?t=" + Math.random(); // 防止浏览器缓存的问题       
		}

		$(function() {
			var areaId = "${area}";
			getWorkTime(areaId);
		});

		function selectChild(obj) {
			$("select[name='transactDate']").get(0).selectedIndex = 0;
		}

		function selectMyChild(obj) {
			var areaId = $("select[name='transactOrgId']").val();
			var param = $(obj).val();

			if (param == 0) {
				return false;
			}
			var url = '<c:url value="/static/book/getMyChild"/>';
			$
					.post(
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
			$.post(url, {
				'areaId' : areaId,
				'date' : date,
				'transactTypeId' : transactTypeId.split("-")[1]
			}, function(result) {
				if (result.rtnCode == '0') {
					var html = '<option value="0">请选择</option>';
					$.each(result.arrTime, function(i, item) {
						if (item.members == 0) {
							html += '<option value="-9">' + item.time
									+ '(已满)</option>';
						} else {
							html += '<option value="'+item.timeId+'">'
									+ item.time + '(剩余：' + item.members
									+ ')</option>';
						}

					});
					$("select[name='transactTime']").empty().append(html);
				}
			});
		}

		function handleOrgChange(areaId) {
			var url = '<c:url value="/static/book/handleOrgChange"/>';
			$.post(url, {
				'areaId' : areaId
			}, function(result) {
				if (result.rtnCode == '0') {
					// 设置所属行政区
					//var html = '<option value="-1">请选择</option>';
					var html = '';
					$.each(result.arrArea, function(i, item) {
						html += '<option value="'+item.areaCode+'">'
								+ item.areaDesc + '</option>';

					});
					$("select[name='area']").empty().append(html);

					// 业务类型设置
					if (result.arrBussinessType != undefined) {
						var html = '';
						$.each(result.arrBussinessType, function(i, item) {
							if (item.dictionaryValue == '使用说明'
									|| item.dictionaryValue == '须知常见问题') {
								return;
							}
							html += '<option value="'+item.id+'">'
									+ item.dictionaryValue + '</option>';

						});
						if (html != '') {
							html = '<option value="0">请选择业务类型</option>' + html;
							$("select[name='bussinessType']").empty().append(
									html);
						}
					}

					// 办理时间设置
					if (result.arrDays != undefined) {
						var html = '';
						$.each(result.arrDays, function(i, item) {
							html += '<option value="'+item.date+'">'
									+ item.date + '</option>';
							//'(剩余预约人数：' + item.member+

						});
						if (html != '') {
							html = '<option value="0">请选择办理时间</option>' + html;
							$("select[name='transactDate']").empty().append(
									html);
						}
					}
				}
			});
		}

		function submitData(obj) {

			var openid = $("input[name='openid']").val();
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
			var vCode = $("input[name='vCode']").val();

			var usertype = $("input[name='usertype']:checked").val();
			var obligeename = $("input[name='obligeename']").val();
			var obligeeidcard = $("input[name='obligeeidcard']").val();
			var isHaveHasagent = $("input[name='isHaveHasagent']:checked")
					.val();

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

			if (areaId == -1) {
				alert("所属行政区不能为空!");
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
				alert("电话不能为空!");
				return false;
			}

			if (hourseNumber == "" || hourseNumber == null) {
				alert("产权证证号不能为空!");
				return false;
			}

			if (hourseAddress == "" || hourseAddress == null) {
				alert("不动产坐落不能为空!");
				return false;
			}

			if (vCode == "" || vCode == null) {
				alert("请输入验证码!");
				return false;
			}

			//东丽区增加字段处理
			var area = "<c:out value='${area}'/>";
			if (area == 1043) { //1043东丽区ID

				if (obligeename == "" || obligeename == null) {
					alert("权利人姓名不能为空!");
					return false;
				}

				var idcardReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
				if (obligeeidcard == "" || obligeeidcard == null) {
					alert("权利人身份证号不能为空!");
					return false;
				} else if (!idcardReg.test(obligeeidcard)) {
					alert('请正确填写身份证号！');
					return false;
				}
			}

			if (!confirm("您的预约信息将进行后台审核，是否确认提交？")) {
				return false;
			}

			$(obj).attr("onclick", "");
			var url = '<c:url value="/static/book/submit"/>';

			var transactTypeTemp = transactTypeId.split("-");
			var transactTypeNameValue = transactTypeTemp[0];
			var transactTypeIdValue = transactTypeTemp[1];

			var usertype = $("input[name='usertype']:checked").val();
			var obligeename = $("input[name='obligeename']").val();
			var obligeeidcard = $("input[name='obligeeidcard']").val();
			var isHaveHasagent = $("input[name='isHaveHasagent']:checked")
					.val();
			$
					.ajax({
						type : 'GET',
						url : url,
						data : {
							"openid" : "ov5KSuMZ_s6KgM-h-pYal5ILyhRM",
							"transactOrgId" : transactOrgId,
							"transactOrgName" : encodeURIComponent(transactOrgName),
							"transactTypeId" : transactTypeIdValue,
							"transactTypeName" : encodeURIComponent(transactTypeNameValue),
							"transactDateId" : transactDateId,
							"transactDate" : transactDate,
							"transactTimeId" : transactTimeId,
							"deadTime" : deadTime,
							"area" : areaId,
							"usertype" : usertype,
							"obligeename" : 123,
							"name" : encodeURIComponent(name),
							"idcard" : idcard,
							"mobile" : mobile,
							"hourseNumber" : encodeURIComponent(hourseNumber),
							"hourseAddress" : encodeURIComponent(hourseAddress
									+ ";现场预约"),
							"vCode" : vCode
						},
						success : function(result) {
							if (result.rtnCode == "9") {
								alert(result.rtnMsg);
								$(obj).attr("onclick", "submitData(this)");
							} else {
								alert("预约申请成功，请等待审核！");
								window.location.href = '<c:url value="/static/bookForWeb/"/>'
										+ transactOrgId;
							}
						}
					});
		}
	</script>
</body>
</html>