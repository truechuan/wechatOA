<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>二维码测试页</title>
    <meta name="description"  content="" />
    <meta name="keywords"  content=""/>
</head>
<body >
<div id="code"></div> 
</body>
<script src="http://cdn.bootcss.com/jquery/3.0.0-beta1/jquery.js"></script>
<script src="http://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>

<script type="text/javascript">
$(function(){
	$("#code").qrcode({
	    render: "canvas", //table/canvas方式 
	    width: 200, //宽度 
	    height:200, //高度 
  	    text: "http://www.tjsbdcdjzx.com/hardware/u/689db4aa9299aaec8b27c3497e8bc6fc"
		//text: "http://192.168.0.116:9088/projectmasterweb/hardware/u/hFEnHE3JfUfAnxRnVG+nMA=="
	});
});

 
</script>
</html>