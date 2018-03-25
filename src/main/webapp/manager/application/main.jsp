<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/context.jsp" %>
<%@include file="/common/jsp/authUser.jsp" %>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<%--<link rel="icon" href="${baseURL}/common/images/favicon.ico">--%>

	<title>管理后台</title>

	<link href="${baseURL}/common/css/bootstrap.css" rel="stylesheet">
	<link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	<link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">


	<script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
	<script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>


	<script src="${baseURL}/common/js/html5shiv.min.js"></script>
	<script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body style="padding:0px;margin: 0px">
<nav class="navbar navbar-default" style="float:left;width:18%">
	<ul class="nav nav-tabs nav-stacked">
		<li><a href="#">Tutorials</a></li>
		<li><a href="#">Practice Editor </a></li>
		<li><a href="#">Gallery</a></li>
		<li><a href="#">Contact</a></li>
		<li><a href="#">Gallery</a></li>
		<li><a href="#">Contact</a></li>
	</ul>
</nav>
<iframe  name="appFrame" id="appFrame" src="" width="81%" height="560px" frameborder="0"
		 style="margin-left:1%;float:left;overflow-x:hidden;overflow-y:auto;" >
</iframe>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>
	function loadAppPage(appSrc){
		$("#appFrame").attr("src","${baseURL}/"+appSrc);
	}
</script>
</body>
</html>
