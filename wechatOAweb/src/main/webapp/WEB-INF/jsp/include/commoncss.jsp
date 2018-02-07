<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/oaCss.css'/>">
<script type="text/javascript">
	    var arrParam = window.location.href.split('rtnCode=');
	    if(arrParam.length > 1){
	    	var code = arrParam[1].substr(0,1);
	    	if(code != ''){
	    		var msg = window.location.href.split('rtnMsg=')[1];
	    		alert(decodeURI(msg));
	    	}
	    }
</script>