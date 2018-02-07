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
<meta name="description" content="天津市不动产登记中心" />
<meta name="keywords" content="天津市不动产登记中心" />
<%@ include file="include/commoncss.jsp"%>
<style type="text/css"> 
.confirmbtn a:first-of-type {
    background: #17b9ff;
    color: #fff;
    float: left;
}
.confirmbtn a {
    width: 90px;
    height: 35px;
    border-radius: 4px;
    line-height: 35px;
    text-align: center;
    display: inline-block;
}
.confirmbtn a:last-of-type {
    background: #fff;
    border: 1px solid #17b9ff;
    float: right;
}
</style>
</head>
<body id="agreement">
	<div class="top">
		<div class="col-xs-3">
			<a href="javascript:history.go(-1);">
				<div class="leftArrow"></div> </a>
		</div>
		<div class="col-xs-6"></div>
	</div>
	<div class="content" style="margin-bottom:20px;">
		<p>
			<img src="<c:url value='/resources/img/titleIco2.png'/>">预约协议及须知
		</p>
		<p>一、为方便企业群众办理各类不动产登记业务，我市开通网上预约服务系统，请仔细阅读本须知，了解注意事项。</p>
		<p>二、请确保您已在本预约系统“办件指引”版块中了解了预约申请流程以及相关登记业务所需的材料。</p>
		<p>三、本系统采取实名预约方式，为确保您能顺利完成预约，请务必正确填写个人资料。</p>
		<p>四、个人业务预约应为不动产权利人，企业业务预约应为相关经办人，我们在后台将对预约申请进行严格的数据筛查和人工审核，最迟于办理日前一天通知预约结果。预约成功后无法按时前来办理的，
			请务必于预约日期的前一个工作日16：00前取消预约，16:00之后预约将不能取消。</p>
		<p>五、预约成功用户将会收到微信提示，请务必准时携带身份证及相关资料前往不动产所在区的登记大厅办理业务。</p>
		<p>六、在任何情况下您均不得使用各类机器人程序软件、黑客程序软件或其它未经授权的第三方软件干扰本预约服务平台的正常运行，影响不动产登记秩序，本团队将不遗余力地通过技术手段及行政手段对上述行为进行严肃查处。请不要将自己的微信账号密码交给不可信赖的第三方用于使用平台，以免您的个人信息被不法分子进行利用，进而影响您在平台的信誉。</p>
		
	</div>
	<div class="confirmBtn" style="margin-bottom:60px;">
			<a href="<c:url value="/static/book/index"/>">接受</a> <a
				href="<c:url value="/"/>">不接受</a>
		</div>
	<%@include file="./include/footer.jsp"%>
	<script>
		$('#myTabs a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
	</script>
</html>