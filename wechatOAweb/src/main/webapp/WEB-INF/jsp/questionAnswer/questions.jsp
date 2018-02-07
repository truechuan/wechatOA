<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="description"  content="" />
    <meta name="keywords"  content=""/>
    <%@ include file="../include/commoncss.jsp"%>
</head>
<body id="qaList">
	<div class="top">
		<div class="col-xs-3">
			<a  href="javascript:history.go(-1);">
				<div class="leftArrow"></div>
			</a>
		</div>
		<div class="col-xs-6">常见问题</div>
	</div>

  <!-- Nav tabs -->
<!--   <div class="btnsBg"> -->
<!-- 	<div class="btns"> -->
<!-- 		<ul role="tablist"> -->
<!-- 			<li role="presentation" class="active"> -->
<!-- 				<a href="#content1" aria-controls="content1" role="tab" data-toggle="tab" class="text-right">常见问题</a> -->
<!-- 			</li> -->
<!-- 			<li role="presentation"> -->
<!-- 				<a href="#content2" aria-controls="content1" role="tab" data-toggle="tab">我的问题</a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
<!-- 	</div> -->
<!--   </div> -->
  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="content1">
		<div class="seachBg" style="display: none;">
			<input type="text" placeholder="请输入房产相关问题">
		</div>
		<div class="qaHot">
			<p class="qaHotTitle">问题分类</p>
			<div style="padding:5px;">
			<button class="qaQuick" onclick="javascript:window.location.href='<c:url value="/static/questions/0.html"/>'">全部</button>
				<c:forEach items="${questionTypes }" var="item">
					<button class="qaQuick" onclick="javascript:window.location.href='<c:url value="/static/questions/${item.id }.html"/>'">${item.dictionaryValue }</button>
				</c:forEach>
			</div>
		</div>
		<ul>
			<p class="qaTitle">常见问题</p>
			<c:forEach items="${questions }" var="item">
				<li>
					<a href="<c:url value='/static/answer/${item.answerId}/${item.id}'/>"><p>${item.questionName }</p><span>${item.createTime }</span></a>
				</li>
			</c:forEach>
		</ul>
	</div>
    <div role="tabpanel" class="tab-pane" id="content2">
<!-- 		<p class="qaTitle">我的问题</p> -->
<!-- 		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"> -->
		
<!-- 			<div class="panel panel-default"> -->
<!-- 				<div class="panel-heading" role="tab" id="heading2"> -->
<!-- 				  <h4 class="panel-title"> -->
<!-- 					<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="false" aria-controls="collapse2"> -->
<!-- 					  <p>字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数</p><div class="rightArrow"></div><span class="greenTxt">已解答</span> -->
<!-- 					</a> -->
<!-- 				  </h4> -->
<!-- 				</div> -->
<!-- 				<div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading2"> -->
<!-- 				  <div class="panel-body"> -->
<!-- 					字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数			  </div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="panel panel-default"> -->
<!-- 				<div class="panel-heading" role="tab" id="heading3"> -->
<!-- 				  <h4 class="panel-title"> -->
<!-- 					<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse3" aria-expanded="false" aria-controls="collapse3"> -->
<!-- 					  <p>字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数</p><div class="rightArrow"></div><span class="redTxt">未解答</span> -->
<!-- 					</a> -->
<!-- 				  </h4> -->
<!-- 				</div> -->
<!-- 				<div id="collapse3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading3"> -->
<!-- 				  <div class="panel-body"> -->
<!-- 					字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数字数</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
	
		<div class="qaTitle">提问</div>
		<textarea id="questionDescription" rows="3" cols="20" maxlength="100" autofocus placeholder="请输入您的问题"></textarea>
		<div><a class="lastBtn" id="submitQuestion">提交</a>
		</div>
		</div></div>
	<%@ include file="../include/footer.jsp"%>
	<%@ include file="../include/commonjs.jsp"%>
<script>
$('#myTabs a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
})

$(function(){
	$("#questionDescription").focus();
});

$("#submitQuestion").click(function(){
	alert("提问功能正在开发中，敬请期待");
});
</script>
</body>
</html>