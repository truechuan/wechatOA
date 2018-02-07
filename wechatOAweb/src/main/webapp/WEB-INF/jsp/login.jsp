<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="zh-cn">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登录-天津市不动产</title>
	<meta name="keywords" content="登录" />
	<link href="<c:url value='/resources/images/favicon.ico'/>" rel="SHORTCUT ICON"/>
	<%@ include file="include/commoncss.jsp"%>
	<style type="text/css">
	input{margin-top:10px;}
	#forgetPwd .modal-body{padding-bottom:0;overflow: hidden; height: 200px;}
	#body1 .row,#body2 .row{margin: 20px -15px;}
	#forgetPwd .modal-body .row .col-lg-4{line-height: 50px; font-size: 18px;text-align:right;padding-right:10px;}
	#forgetPwd button{font-size:14px;}
	#forgetPwd .modal-footer button{width:80px;height:40px; line-height:40px;}
	form > p{text-align:left;font-size:18px;line-height:50px;padding-left:88px;margin-top:10px;}
	</style>
</head>
<body>
	<%@ include file="include/nav.jsp"%>
	
	<div class="content clear" style="margin-bottom:60px;">
		<div class="indexContent absoluteCenter login_bg">
			<div class="login_box text-center" style="float: right;">
				<div class="login_nav">					
					<a class="col-lg-6 text-center lr_chose" href="<c:url value='/register/0'/>" style="border-left:none;border-right:1px solid #ccc ;">30秒注册</a>
					<span class="col-lg-6 text-center lr_choose" >用户登录</span>
				</div>
				<form role="form row" action='<c:url value="/signon"/>' method="POST" onsubmit="return login();">	
				<div class="register_input">
					<div class="register_input_info">
						<img class="float-l register_input_img" src="<c:url value='/resources/images/l-btn1.jpg'/>">		
						<input type="text" id="username" name="j_username" placeholder="请输入您的用户名"  autocomplete="off" class="border1">
					</div>
					<div class="errorInfo" id="error1" style="display: none;"></div>	
				</div>
				<div class="register_input">
					<div class="register_input_info">
						<img class="float-l register_input_img" src="<c:url value='/resources/images/l-btn1.jpg'/>">	
						<input type="password" id="password" name="j_password" placeholder="请输入登录密码" autocomplete="off" class="border2">
					</div>
					<div class="errorInfo" id="error2" style="display: none;"></div> 
				</div>								
				<div class="row" style=" width:343px;margin-top: 40px; margin-bottom:5px;color:#e13333;">
					<span class="tips_up" style="visibility: hidden;">用户名或密码错误！</span>
				</div>
				<button class="blueBtn" type="submit" name="submit" style="width: 294px;font:800 18px '微软雅黑'; margin-top:114px;">登&nbsp;&nbsp;&nbsp;录</button>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="include/footer.jsp"%>
	<%@ include file="include/commonjs.jsp"%>
	<script type="text/javascript" src="<c:url value='/resources/js/page/login.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/page/changePassword.js'/>"></script>
	<script>
	var isMobileExistUrl = "<c:url value='/isMobileExist/'/>";
	var verifyPhoneCodeUrl = null;
	var changePwdUrl = null;
	
	
	$(function(){
		
		var message = "<c:url value='${param.error }'/>";
	
		if(message != '' && message != null){
			$(".tips_up").css("visibility","visible");
		}
	});	
	
	
	
	$("#phone").blur(function(){
		var userName = $("#phone").val();
		checkPhone(userName, false);
	});
	$("#confirmPwd").blur(function(){
		checkconfirmPwd(false);
	});
	
	$("#sendMsgBtn").click(function() {
		var phone = $("#phone").val();
		
		if(!checkPhone(phone, false)) return false;
		
		$("#sendMsgBtn").show();
		$("#sendMsgBtn").html("短信发送中");
		$("#sendMsgBtn").attr("disabled", true);
		$.get("<c:url value='/sendFindPwdCapatch/"+phone+"' />", function(data) {
			if(data.result == true){
				$("#sendMsgBtn").html("短信已发送");
				showTime(1);
			}else{
				return false;
			}
		});
		return true;
	});
	$("#next").click(function(){
		var code=$("#code").val();
		var phone = $("#phone").val();
		verifyPhoneCodeUrl = "<c:url value='/verifyPhoneCode/"+phone+"/"+code+"' />";
		
		if(!checkPhone(phone, false)) return false;
		if(!checkCode(code, false)) return false;
		
		$("#body2").fadeIn(500);
		$("#body1").fadeOut(500);
		$("#bingo").fadeIn(500);
		$("#next").fadeOut(500);
		return true;
	});
	$("#bingo").click(function(){
		var newPwd=$("#newPwd").val();
		changePwdUrl = "<c:url value='/changePwd/"+$("#phone").val()+"/"+newPwd+"' />";
		if(!checkconfirmPwd(false)) return false;
		if(!changePwd()) return false;
		
		$("#body3").fadeIn(500);
		$("#body2").fadeOut(500);
		$("#bingo").fadeOut(500);
		return true;
	}); 
	$("#error1").click(function(){
		$("#error1").hide();
		$("#username").css("border-color", "#ccc");
	});
	$("#error2").click(function(){
		$("#error2").hide();
		$("#password").css("border-color", "#ccc");
	});
	</script>
</body>
</html>