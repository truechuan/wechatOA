<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/nav.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<body id="register">
	<div class="top">
		<div class="col-xs-3">
			<a href="javascript:history.go(-1);">
				<div class="leftArrow"></div> </a>
		</div>
		<div class="col-xs-6">查询办件信息</div>
	</div>

	<!-- 	<label style="color: red;">请在证件号码处填写权利人证件号码</label> -->
	<div class="row" style="margin:0">
		<div class="col-xs-3">
			<label style="height: 45px; line-height: 45px;font-size: 16px; ">收件号</label>
		</div>
		<div class="col-xs-9">
			<input type="text" name="strFileID" placeholder="请输入业务收件号" value="" />
		</div>
	</div>
	<div class="row" style="margin:0">
		<div class="col-xs-3" style="padding-right:0px;">
			<label style="height: 45px; line-height: 45px;font-size: 16px;">身份证号</label>
		</div>
		<div class="col-xs-9">
			<input type="text" name="strZjhm" placeholder="请输入权利人证件号码" value="">
		</div>
	</div>



	<!-- 	<input type="text" id="textMsg" value="状态：待查询" style="color: red;"> -->

	<div class="bottomBtn">
		<a href="javascript:void(0)" onclick="queryFileStatus(this)"> 查询</a>
	</div>
	<c:choose>
		<c:when test="${sessionScope.isTencent == 'true' }">
			<%@include file="./include/tencentFooter.jsp"%>
		</c:when>
		<c:otherwise>
			<%@include file="./include/footer.jsp"%>
		</c:otherwise>
	</c:choose>
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript">
		function queryFileStatus(obj) {
			var strFileID = $("input[name='strFileID']").val();
			var strZjhm = $("input[name='strZjhm']").val();
			if (strFileID == '' || isNaN(strFileID)) {
				alert('收件号不能为空！');
				return false;
			}
			if (strZjhm == '') {
				alert('身份证号不能为空！');
				return false;
			} else {
				if (!checkCertificateNo(strZjhm)) {
					return false;
				}
			}
			if (!confirm("请确认您填写的查询信息是否正确，确认提交？")) {
				return false;
			}
			var url = '<c:url value="/queryFileStatusSubmit"/>';
			$(obj).attr("onclick", "");
			$
					.ajax({
						type : 'GET',
						url : url,
						data : {
							'strFileID' : strFileID,
							'strZjhm' : strZjhm
						},
						success : function(result) {
							if (result.rtnCode == '9') {
								alert(result.rtnMsg);
								$(obj).attr("onclick", "queryFileStatus(this)");
							} else {
								alert(result.rtnMsg);
								//$("#textMsg").val("状态："+result.rtnMsg);
								window.location.href = '<c:url value="/queryFileStatus"/>';
							}
						}
					});
		}
		function checkCertificateNo(idcard) {
			var certificateNo = idcard;//身份证号码
			if (certificateNo.length == 15) {
				return true;
			} else if (certificateNo.length != 18) {
				alert("身份证号码无效，请填写正确的身份证号！！！");
				return false;
			} else {
				var address = certificateNo.substring(0, 6);//6位，地区代码
				var birthday = certificateNo.substring(6, 14);//8位，出生日期
				var sequenceCode = certificateNo.substring(14, 17);//3位，顺序码：奇为男，偶为女
				var checkCode = certificateNo.substring(17);//1位，校验码：检验位
				console.log("身份证号码:" + certificateNo + "、地区代码:" + address
						+ "、出生日期:" + birthday + "、顺序码:" + sequenceCode
						+ "、校验码:" + checkCode);

				var province = {
					11 : "北京",
					12 : "天津",
					13 : "河北",
					14 : "山西",
					15 : "内蒙古",
					21 : "辽宁",
					22 : "吉林",
					23 : "黑龙江 ",
					31 : "上海",
					32 : "江苏",
					33 : "浙江",
					34 : "安徽",
					35 : "福建",
					36 : "江西",
					37 : "山东",
					41 : "河南",
					42 : "湖北 ",
					43 : "湖南",
					44 : "广东",
					45 : "广西",
					46 : "海南",
					50 : "重庆",
					51 : "四川",
					52 : "贵州",
					53 : "云南",
					54 : "西藏 ",
					61 : "陕西",
					62 : "甘肃",
					63 : "青海",
					64 : "宁夏",
					65 : "新疆",
					71 : "台湾",
					81 : "香港",
					82 : "澳门",
					91 : "国外"
				};

				var year = birthday.substring(0, 4);
				var month = birthday.substring(4, 6);
				var day = birthday.substring(6);
				var tempDate = new Date(year, parseFloat(month) - 1,
						parseFloat(day));
				if (province[parseInt(address.substr(0, 2))] == null
						|| (tempDate.getFullYear() != parseFloat(year)
								|| tempDate.getMonth() != parseFloat(month) - 1 || tempDate
								.getDate() != parseFloat(day))) {//这里用getFullYear()获取年份，避免千年虫问题
					alert("身份证号码无效，请重新输入！！！");
					return false;
				} else {
					var weightedFactors = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7,
							9, 10, 5, 8, 4, 2, 1 ];//加权因子   
					var valideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，其中10代表X
					var certificateNoArray = certificateNo.split("");// 得到身份证数组
					var sum = 0;// 声明加权求和变量
					if (certificateNoArray[17].toLowerCase() == 'x') {
						certificateNoArray[17] = 10;// 将最后位为x的验证码替换为10  
					}
					for ( var i = 0; i < 17; i++) {
						sum += weightedFactors[i] * certificateNoArray[i];// 加权求和   
					}
					valCodePosition = sum % 11;// 得到验证码所在位置
					if (certificateNoArray[17] == valideCode[valCodePosition]) {
						var sex = "男";
						if (sequenceCode % 2 == 0) {
							sex = "女";
						}
						//alert("身份证号码有效，性别为：" + sex + "！");
						return true;
					} else {
						alert("身份证号码无效，请重新输入！！！");
						return false;
					}
				}
			}
		}
	</script>
</body>
</html>