<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>添加办件须知</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="<c:url value="/resources/js/ckeditor/contents.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>"
	rel="stylesheet" type="text/css" media="screen">
<jsp:include page="../include/headTag.jsp" />

<script src="<c:url value="/resources/js/ckeditor/ckeditor.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/ckfinder/ckfinder.js"/>"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<c:url value="/resources/datetimepicker/bootstrap-datetimepicker.min.js"/>"
	charset="UTF-8"></script>
<script src="<c:url value="/resources/js/ajaxfileupload.js"/>"
	type="text/javascript"></script>



</head>
<body class="">
	<jsp:include page="../include/header.jsp" />

	<jsp:include page="../include/menues.jsp">
		<jsp:param value="/createNotice" name="menu" />
	</jsp:include>
	<div class="content">

		<ul class="breadcrumb">
			<li><a href="<c:url value='/user/welcome.htm'/>">Home</a> <span
				class="divider">/</span>
			</li>
			<li class="active"><c:choose>
					<c:when test="${notice.id  > 0 }">
					编辑
				</c:when>
					<c:otherwise>
					添加
				</c:otherwise>
				</c:choose>办件须知</li>
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
					<button class="btn btn-primary" id="saveNotice">
						<i class="icon-save"></i> 保存
					</button>
					<c:if test="${notice.id gt 0}">
						<a href="<c:url value="/createNotice"/>" data-toggle="modal"
							class="btn">继续添加</a>
					</c:if>
					<!-- 					<a href="<c:url value="/categorys/1"/>" data-toggle="modal" -->
					<!-- 						class="btn">录入主业务类型</a> -->
					<a href="<c:url value="/notices/1/1"/>" class="btn btn-primary">
						返回 </a>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form:form id="noticeForm" method="post" modelAttribute="notice">
								<input type="hidden" name="id" value="${notice.id}" />
								<label>须知标题(注：也是子业务类型)</label>
								<input type="text" name="noticeName"
									value="${notice.noticeName}" maxlength="100" />
								<c:if test="${isMoreManger eq 'true' }">
									<label>所属区县</label>
									<input id="area" type="hidden" name="area" value="1">
									<c:forEach items="${areas }" var="item" varStatus="status">
										<label class="checkbox inline"> <input type="radio"
											name="radio" value="${item.areaCode }"
											onclick="selectType(${item.handleOrgId })" />${item.areaDesc
											}</label>
										<c:if test="${status.index eq 7}">
											<br />
										</c:if>
									</c:forEach>
								</c:if>
								<p></p>
								<label>主业务类型</label>
								<select name="noticeTypeId" id="noticeTypeId">
									<c:forEach items="${noticeTypes }" var="item">
										<option value="${item.id }"
											<c:if test="${item.id == notice.noticeTypeId}">selected</c:if>>${item.dictionaryValue
											}</option>
									</c:forEach>
								</select>
								<label>须知描述</label>
								<textarea rows="30" cols="70" id="content"
									name="noticeDescription">${notice.noticeDescription}</textarea>
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
								<c:if test="${notice.isUseful eq '1' }">
									<input type="checkbox" id="isUseful" name="isUseful" value="1"
										checked="checked" onclick="ischecked(this)" />是否显示在前台的子业务类型
								</c:if>
								<c:if test="${notice.isUseful eq '0' }">
									<input type="checkbox" id="isUseful" name="isUseful" value="0"
										onclick="ischecked(this)" />是否显示在前台的子业务类型
								</c:if>

								<!-- 这里只有新增时才能看到 -->
								<c:choose>
									<c:when test="${notice.id  <= 0 }">
										<label>类型限制</label>
										<table>
											<thead>
												<tr>
													<th>时间段</th>
													<th>设置限制数目（默认999）</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${workTimes }" var="item"
													varStatus="status">
													<tr>
														<td>${item.startTime }-${item.endTime }</td>
														<td><input type="text" name="numLimit${item.id }"
															id="numLimit${item.id }" value="999" />
														</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
										<label>类型限制</label>
										
									</c:when>
								</c:choose>
								<input type="text" name="numLimit"
											value="${notice.numLimit}" maxlength="100"
											onkeyup="this.value=this.value.replace(/\D/g,'')"
											onafterpaste="this.value=this.value.replace(/\D/g,'')" />
							</form:form>
						</div>
					</div>
				</div>
				<jsp:include page="../include/footer.jsp" />
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	var areaValue = "<c:out value='${notice.area}'/>";
	$("input:radio[value="+areaValue+"]").attr('checked','true');
	
});
//供复选框使用
function ischecked(obj)
{
	if(obj.checked==false)
	{
		obj.value=0;
	}
	else
	{
		obj.value=1;
	}
}
$('#saveNotice').click(function() {
	
	var areaTemp = $('input:radio:checked').val();
    $("#area").val(areaTemp);
    
    var noticeTypeId = $("#noticeTypeId").val();
    if(noticeTypeId == null || noticeTypeId == ""){
    	alert("请选择主业务类型！");
    	return;
    }
    
	$('#noticeForm').submit();
	return false;
});

function selectType(handleOrgId){
	var url = '<c:url value="/notice/selectTypeByhandleOrgId"/>';
	$.post(url,{'handleOrgId':handleOrgId}, function(result){
		if(result.noticeTypes != ""){
			
			var noticeTypes = '<option value="-1">请选择</option>';
			$.each(result.noticeTypes, function(i,item){
				
				noticeTypes += '<option value="'+item.id+'">'+item.dictionaryValue+'</option>';
			});
			
			$("select[name='noticeTypeId']").empty().append(noticeTypes);
		}else{
			alert("该区县下没有录入主业务类型！请先录入（注：主业务类型在字典管理中录入）");
			$("select[name='noticeTypeId']").empty();
		}
	});
}


</script>
</html>


