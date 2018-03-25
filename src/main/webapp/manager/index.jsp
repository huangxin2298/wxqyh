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
	<link href="${baseURL}/themes/manager/login/css/navbar.css" rel="stylesheet">
	<link href="${baseURL}/themes/manager/main/css/main.css" rel="stylesheet">
	<link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">

	<script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
	<script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

	<script src="${baseURL}/common/js/html5shiv.min.js"></script>
	<script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body style="margin-top:20px;margin-bottom: 20px">

<div class="container">

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">管理后台</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="javascript:changePage(0)">我的应用</a></li>
					<li><a href="javascript:changePage(1)">通讯录</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="javascript:changePage(2)">设置</a></li>
				</ul>
				<span style="float: right;margin-top: 15px">${adminUser.userName}<a href="javascript:loginOutAdminUser()" style="margin-left: 6px">退出</a></span>
			</div>
		</div>
	</nav>
	<iframe name="topFrame" class="innerbox" id="topFrame" src="application/main.jsp" width="100%" height="640px;" frameborder="0" >
	</iframe>
</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>
	function changePage(num){
        if(num==0){
			$("#topFrame").attr("src","application/main.jsp");
        }else if(num==1){
            $("#topFrame").attr("src","addressbook/main.jsp");
		}else if(num==2){
            $("#topFrame").attr("src","setting/main.jsp");
		}
	}
	function loginOutAdminUser(){
        $.ajax({
            url: "${baseURL}/manager/addressbook/loginOutAdminUser.action",
            type: "POST",
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    window.location.href = "${baseURL}/manager/login.jsp";
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
