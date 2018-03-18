<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/context.jsp" %>

<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<%--<link rel="icon" href="${baseURL}/common/images/favicon.ico">--%>

	<title>登录页面</title>

	<link href="${baseURL}/common/css/bootstrap.min.css" rel="stylesheet">
	<link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	<link href="${baseURL}/themes/manager/login/css/signin.css" rel="stylesheet">

	<script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
	<script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>

<div class="container">

	<form class="form-signin">
		<h2 class="form-signin-heading">管理后台登录</h2>
		<label for="inputUserName" class="sr-only">用户名</label>
		<input type="text" id="inputUserName" class="form-control" placeholder="用户名" required autofocus>
		<label for="inputPassword" class="sr-only">密码</label>
		<input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
		<button class="btn btn-lg btn-primary btn-block">登录</button>
	</form>

</div>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>

</script>
</body>
</html>
