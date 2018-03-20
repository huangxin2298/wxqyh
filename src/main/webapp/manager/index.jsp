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

	<title>管理后台</title>

	<link href="${baseURL}/common/css/bootstrap.min.css" rel="stylesheet">
	<link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	<link href="${baseURL}/themes/manager/login/css/navbar.css" rel="stylesheet">

	<script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
	<script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>

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
			</div>
		</div>
	</nav>
	<iframe  name="topFrame" id="topFrame" src="application/main.jsp" width="100%" height="560px;" frameborder="0" style="overflow-x:hidden;overflow-y:auto;z-index:600" >
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

</script>
</body>
</html>
