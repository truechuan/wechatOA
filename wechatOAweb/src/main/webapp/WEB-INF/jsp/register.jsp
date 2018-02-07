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
		<div class="col-xs-6">个人信息</div>
	</div>
	<div class="row" style="margin:0">
		<div class="col-xs-3">
			<label style="height: 45px; line-height: 45px;font-size: 16px; ">姓名</label>
		</div>
		<div class="col-xs-9">
			<input type="text" name="name" placeholder="请输入真实姓名"
				value="${user.name }" />
		</div>
	</div>
	<div class="row" style="margin:0">
		<div class="col-xs-3">
			<label style="height: 45px; line-height: 45px;font-size: 16px;">手机号</label>
		</div>
		<div class="col-xs-9">
			<input type="text" name="mobile" placeholder="请输入手机号码"
				value="${user.mobile }">
		</div>
	</div>

	<c:if test="${empty user }">
		<input type="hidden" name="openid" value="${openid }" />
	</c:if>
	<c:if test="${not empty user }">
		<input type="hidden" name="openid" value="${user.openid }" />
		<input type="hidden" name="id" value="${user.id }" />
	</c:if>
	<!--<a href="<c:url value='noticeInfo.html'/>"><p>请选择证件类型</p><div class="rightArrow"></div></a>-->
	<!-- Single button -->
	<!-- 	<div class="btn-group"> -->
	<!-- 	  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
	<!-- 		<p>证件类型：身份证</p><span class="caret"></span> -->
	<!-- 	  </button> -->
	<!-- 	</div> -->
	<div class="row" style="margin:0">
		<div class="col-xs-3" style="padding-right:0">
			<label style="height: 45px; line-height: 45px;font-size: 16px;">身份证号</label>
		</div>
		<div class="col-xs-9">
			<input type="text" name="idcard" placeholder="请输入身份证号"
				value="${user.idcard }">
		</div>
	</div>

	<c:if test="${not empty user }">
		<c:if test="${user.status eq 0}">
			<input type="text" value="状态：审核中" style="color: blue;">
		</c:if>
		<c:if test="${user.status eq 1}">
			<input type="text" value="状态：审核通过" style="color: green;">
		</c:if>
		<c:if test="${user.status eq 2}">
			<input type="text" value="状态：未通过，请修改您的信息" style="color: red;">
		</c:if>
	</c:if>

	<div class="addPhoto" style="display: none;">
		<div class="col-xs-4 text-center">
			<img src="<c:url value='/resources/img/idcard.png'/>">
			<p>上传示例</p>
		</div>
		<div class="col-xs-4 text-center">
			<div id="formfile">
				<input id="file" type="file" name="file" onchange="submitFile(this)"
					style="display:none" />
				<c:if test="${empty user }">
					<a href="javascript:void(0)" onclick="choiceFile(this)">
						<div>添加</div> </a>
				</c:if>
				<c:if test="${not empty user }">
					<img id="img-file" src="${user.idcardImgUrl }"
						onclick="choiceFile(this)">
				</c:if>
				<p>身份证正面</p>
			</div>
		</div>
		<div class="col-xs-4 text-center">
			<div>
				<input id="file2" type="file" name="file2"
					onchange="submitFile(this)" style="display:none" />
				<c:if test="${empty user }">
					<a href="javascript:void(0)" onclick="choiceFile(this)">
						<div>添加</div> </a>
				</c:if>

				<c:if test="${not empty user }">
					<img id="img-file2" src="${user.idcardImgUrl2 }"
						onclick="choiceFile(this)">
				</c:if>
				<p>身份证反照</p>
			</div>
		</div>
	</div>
	<div class="bottomBtn">
		<a href="javascript:void(0)" onclick="registerCheck(this)"> <c:if
				test="${empty user }">
				保存
			</c:if> <c:if test="${not empty user }">
				修改
			</c:if> </a>
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
	<script src="<c:url value='/resources/js/ajaxfileupload.js'/>"></script>
	<script>
		$('#myTabs a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		});

		function choiceFile(obj) {
			$(obj).prev().click();
		}

		function submitFile(obj) {
			var fileName = $(obj).attr('name');
			var formObj = $(obj).parent();
			var myFileName = $(obj).val();
			var match = /(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.PNG|.png|.BMP|.bmp)$/i;
			if (!match.test(myFileName)) {
				alert("图片类型必须是.gif/jpeg/jpg/png/bmp中的一种！并且小于5M");
				return false;
			}
			var url = '<c:url value="/static/uploadHeaderPicture/0?paramFile="/>'
					+ fileName;
			var file = this.file;
			$.ajaxFileUpload({
				url : url,
				secureuri : false,
				fileElementId : fileName,//file标签的id  
				dataType : 'json',//返回数据的类型  
				success : function(data, status) {
					if (data.indexOf("ok") != -1) {
						var imgUrl = data.split("-")[1];
						var html = '<img id="img-' + fileName
								+ '"src="http://www.tjsbdcdjzx.com/' + imgUrl
								+ '" onclick="choiceFile(this)">';
						formObj.children("a").remove();
						formObj.children("img").remove();
						formObj.children("input").after(html);
					}
				},
				error : function(data, status, e) {
					alert(e);
				}
			});

		}

		function completeHandler() {
			alert('success')
		}

		function registerCheck(obj) {

			var openid = $("input[name='openid']").val();
			var name = $("input[name='name']").val();
			var mobile = $("input[name='mobile']").val();
			var idcard = $("input[name='idcard']").val();
			var idcardImgUrl = $("#img-file").attr("src");
			var idcardImgUrl2 = $("#img-file2").attr("src");
			var id = $("input[name='id']").val();

			if (id == '' || id == undefined) {
				id = 0;
			}

			if (openid == '') {
				alert('请重新访问页面！');
				window.close();
			}

			if (name == '') {
				alert('姓名不能为空！');
				return false;
			}

			var match = /^1[0-9]{10}$/;
			if (mobile == '') {
				alert('手机号不能为空！');
				return false;
			} else if (!match.test(mobile)) {
				alert('手机号有误，请重新输入！');
				return false;
			}

			//var idcardReg = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;

			if (idcard == '') {
				alert('身份证号不能为空！');
				return false;
			} else {
				if(!checkCertificateNo(idcard)){return false;}
			}

			// 	if(idcardImgUrl == undefined || idcardImgUrl2 == undefined){
			// 		alert('请选择证件正反面照片！');
			// 		return false;
			// 	}

			if (!confirm("请确认您填写的个人信息是否正确，确认提交？")) {
				return false;
			}
			$(obj).attr("onclick", "");
			var userId = "<c:out value='${user.id}'/>";
			var url = '<c:url value="/static/register/submit"/>';
			if (userId != null && userId != "") {
				var url = '<c:url value="/static/register/update"/>';
			}
			$
					.ajax({
						type : 'GET',
						url : url,
						data : {
							'id' : id,
							'openid' : openid,
							'name' : encodeURIComponent(name),
							'mobile' : mobile,
							'idcard' : idcard,
							'idcardImgUrl' : idcardImgUrl,
							'idcardImgUrl2' : idcardImgUrl2
						},
						success : function(result) {
							if (result.rtnCode == '9') {
								alert(result.rtnMsg);
								$(obj).attr("onclick", "registerCheck(this)");
							} else {
								alert('操作成功！可继续预约');
								window.location.href = '<c:url value="/static/book/index"/>';
							}
						}
					});

		}
		function checkCertificateNo(idcard) {
			var certificateNo = idcard;//身份证号码
			if (certificateNo.length != 18) {
				alert("身份证号码无效，请使用第二代身份证！！！");
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