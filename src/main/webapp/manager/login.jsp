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

	<script src="${baseURL}/common/js/html5shiv.min.js"></script>
	<script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body>

<div class="container">

	<form class="form-signin" id="userForm">
		<h2 class="form-signin-heading">管理后台登录</h2>
		<label for="account" class="sr-only">用户名</label>
		<input type="text" id="account" name="account" class="form-control" placeholder="用户名" required autofocus>
		<label for="password" class="sr-only">密码</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
		<input type="button" class="btn btn-lg btn-primary btn-block" value="登录" onclick="loginAdminUser()">
	</form>

</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>
	function loginAdminUser(){
		if($("#account")==""){
		    alert("帐号名不能为空");
		    return;
		}
		if($("#password")==""){
		    alert("密码不能为空");
		    return;
		}
		var form = $("#userForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/addressbook/authUser.action",
			data: form,
            type: "POST",
			dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    var addr = result.data;
					window.location.href = "${baseURL}"+addr;
                } else {
                    alert(result.describe);
                }
            },
            error: function() {
                alert("系统繁忙，请稍后重试");
            }
        })
	}
</script>
</body>
</html>
