<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>添加公告</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="<c:url value="/resources/js/ckeditor/contents.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet" type="text/css" media="screen">
<jsp:include page="include/headTag.jsp" />

<script src="<c:url value="/resources/js/ckeditor/ckeditor.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/ckfinder/ckfinder.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/resources/datetimepicker/bootstrap-datetimepicker.min.js"/>" charset="UTF-8"></script>
<script src="<c:url value="/resources/js/ajaxfileupload.js"/>" type="text/javascript"></script>

<script type="text/javascript">
	
	function ajaxFileUpload() {
		//判断是否已选文件
		var fileUrl = $("#file").val();
		if (fileUrl == "") {
			messageHtml = "<div class='alert alert-error'>";
			messageHtml += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
			messageHtml += "<p>";
			messageHtml += "请选择图片！";
			messageHtml += "</p>";
			messageHtml += "</div>";
			document.getElementById("warning").innerHTML = messageHtml;
			$("#warning").show();
			return;
		}

		var messageHtml;//全局变量用于提示信息
		//判断文件格式
		var exp = fileUrl.substring(fileUrl.lastIndexOf('.'));
		var patn = /\.jpg$|\.jpeg$|\.gif$|\.png$/;
		if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(exp)) {
			messageHtml = "<div class='alert alert-error'>";
			messageHtml += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
			messageHtml += "<p>";
			messageHtml += "图片格式不正确！";
			messageHtml += "</p>";
			messageHtml += "</div>";
			document.getElementById("warning").innerHTML = messageHtml;
			$("#warning").show();
			return;
		}
		$
				.ajaxFileUpload({
					url : "<c:url value='/fileUpload?paramFile=file'/>", //需要链接到服务器地址
					secureuri : false,
					fileElementId : 'file', //文件选择框的id属性（必须）
					dataType : 'text',
					success : function(data, status) {
						var data = eval("(" + data + ")");
						if (data.paramFile == 'file') {
							messageHtml = "<div class='alert alert-success'>";
							messageHtml += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
							messageHtml += "<p>";
							messageHtml += "上传成功!" + data.fileName;
							messageHtml += "</p>";
							messageHtml += "</div>";
							document.getElementById("warning").innerHTML = messageHtml;
							$("#warning").show();
							$("#imgurl").attr("value", data.fileName);
						} else {
							messageHtml = "<div class='alert alert-error'>";
							messageHtml += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
							messageHtml += "<p>";
							messageHtml += "上传失败！";
							messageHtml += "</p>";
							messageHtml += "</div>";
							document.getElementById("warning").innerHTML = messageHtml;
							$("#warning").show();
						}

					},
					error : function(data, status, e) {
						messageHtml = "<div class='alert alert-error'>";
						messageHtml += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
						messageHtml += "<p>";
						messageHtml += "上传失败！";
						messageHtml += "</p>";
						messageHtml += "</div>";
						document.getElementById("warning").innerHTML = messageHtml;
						$("#warning").show();
					}
				});
	}
</script>

</head>
<body class="">
	<c:set var="operator" value="/createAnnouncement" />
	<c:if test="${announcement.announcementId > 0 }">
		<c:set var="operator" value="/createAnnouncement" />
	</c:if>
	<jsp:include page="include/header.jsp" />

	<jsp:include page="include/menues.jsp">
		<jsp:param value="${operator }" name="menu" />
	</jsp:include>
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span class="divider">/</span></li>
			<li class="active"><c:choose>
					<c:when test="${announcement.announcementId  > 0 }">
					编辑
				</c:when>
					<c:otherwise>
					添加
				</c:otherwise>
				</c:choose>公告</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<c:if test="${not empty result }">
					<c:choose>
						<c:when test="${result eq 'success' }">
							<div class="alert alert-success">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								保存成功！
							</div>
						</c:when>
						<c:otherwise>
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								保存失败, 请检查输入项！
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div id="warning" style="display: none;"></div>
				<div class="btn-toolbar">
					<button class="btn btn-primary" id="saveAnnouncement">
						<i class="icon-save"></i> 保存
					</button>
					<c:if test="${announcement.announcementId gt 0}">
						<a href="<c:url value="/createAnnouncement"/>" data-toggle="modal"
							class="btn">继续添加</a>
					</c:if>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form:form id="announcementForm" method="post"
								modelAttribute="announcement">
								<form:input type="hidden" path="announcementId"
									value="${announcement.announcementId}" />
								<label>标题</label>
								<form:input type="text" path="title"
									value="${announcement.title}" maxlength="100" />
<!-- 								<label>关键词</label> -->
								<form:input type="hidden" path="keyWord"
									value="${announcement.keyWord}" maxlength="100" />
<!-- 								<label>描述</label> -->
								<form:input type="hidden" path="description"
									value="${announcement.description}" maxlength="255" />
								<label>状态</label>
								<form:select path="status" id="status">
									<c:forEach items="${announcementStatus }" var="item">
										<option value="${item.value }"
											<c:if test="${item.value eq announcement.status.value}">selected</c:if>>${item.description }</option>
									</c:forEach>
								</form:select>
<!-- 								<label>关联词</label> -->
								<form:input type="hidden" path="ralateWord"
									value="${announcement.ralateWord}" maxlength="255" />
<!-- 								<label>创建时间</label> -->
								<input size="16" type="hidden" name="createTime"
									value='${announcement.createTime }'
									readonly id="effectiveTime" class="form_effectiveTime" />
<!-- 								<label>图片地址</label> -->
<!-- 								<input type="file" class="btn btn-default" id="file" name="file" /> -->
								<input type="hidden" name="imageUrl" id="imgurl"
									value="${announcement.imageUrl }" />
								<input type="hidden" class="btn " value="上传图片"
									onclick="ajaxFileUpload()" />
								<label>栏目</label>
								<form:select path="lanmu" id="lanmu">
									<c:forEach items="${announcementLanmu }" var="item">
										<option value="${item.value }"
											<c:if test="${item.value eq announcement.lanmu.value}">selected</c:if>>${item.description }</option>
									</c:forEach>
								</form:select>
								<label>内容</label>
								<textarea rows="30" cols="70" id="content" name="detailInfo">${announcement.detailInfo}</textarea>
								<script type="text/javascript">
									if (CKEDITOR.instances['content']) {
										CKEDITOR.remove(CKEDITOR.instances['content']);
									} //解决 例外被抛出且未被接住 问题

									var editor = CKEDITOR.replace("content",
									{
										height: '500px',
										filebrowserBrowseUrl : '<c:url value="/ckfinder/ckfinder.html"/>',
										filebrowserImageBrowseUrl : '<c:url value="/ckfinder/ckfinder.html?type=Images"/>',
										filebrowserFlashBrowseUrl : '<c:url value="/ckfinder/ckfinder.html?type=Flash"/>',
										filebrowserUploadUrl : '<c:url value="/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files"/>',
										filebrowserImageUploadUrl : '<c:url value="/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files"/>',
										filebrowserImageUploadUrl : '<c:url value="/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images"/>',
										filebrowserFlashUploadUrl : '<c:url value="/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash"/>'
									});//引号中的字符串要和文本域中name的值一致
									CKFinder.setupCKEditor(editor,
											'<c:url value="/ckfinder/"/>');
								</script>
							</form:form>
						</div>
					</div>
				</div>
				<jsp:include page="include/footer.jsp" />
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#effectiveTime").datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
			minuteStep : 1,
			language : 'zh-CN',
			todayBtn : "linked"
		});
		
		var areaValue = "<c:out value='${announcement.area}'/>";
		var areaTemp = areaValue.split(',');
		for(var i = 0; i < areaTemp.length; i++){
			$("input:checkbox[value='"+areaTemp[i]+"']").attr('checked','true');
		}; 
		
	});
	
	$('#saveAnnouncement').click(function() {
		
		var areaTemp = "";
	    $('input:checkbox[name=box]:checked').each(function(i){
	       if(0==i){
	    	   areaTemp = $(this).val();
	       }else{
	    	   areaTemp += (","+$(this).val());
	       }
	    });
	    
	    var isMoreManger = "<c:url value='${isMoreManger}'/>";
	    
	    var areaLength = $("input[name=box]:checked").length; 
	  	
	    
//	    $("#areaList").val(areaTemp);
//	    alert($("#areaList").val());
	    
		$('#announcementForm').submit();
		return false;
	});

</script>
</html>


