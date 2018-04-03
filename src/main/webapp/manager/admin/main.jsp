<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/context.jsp" %>
<%@include file="/common/jsp/authAdmin.jsp" %>
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
				<a class="navbar-brand" href="#">系统管理后台</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<%--<ul class="nav navbar-nav">
					<li><a href="javascript:changePage(0)">我的应用</a></li>
					<li><a href="javascript:changePage(1)">通讯录</a></li>
				</ul>--%>
				<span style="float: right;margin-top: 15px">${admin.userName}<a href="javascript:loginOutAdmin()" style="margin-left: 6px">退出</a></span>
			</div>
		</div>
	</nav>
	<nav class="navbar navbar-default" style="float:left;width:18%">
		<ul class="nav nav-tabs nav-stacked">
			<li><a href="javascript:changePage('0')">机构管理</a></li>
			<li><a href="javascript:changePage('1')">用户管理</a></li>
			<li><a href="javascript:changePage('2')">配置管理</a></li>
			<li><a href="javascript:changePage('3')">菜单管理</a></li>
			<li><a href="javascript:changePage('4')">应用管理</a></li>
		</ul>
	</nav>
	<iframe name="adminFrame" id="adminFrame" src="${baseURL}/manager/admin/org/orgAdmin.jsp" width="81%" height="620px" frameborder="0"
			style="margin-left:1%;float:left;overflow-x:hidden;overflow-y:auto;" >
	</iframe>
</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>

	function changePage(index){
		if(index == "0"){
            $("#adminFrame").attr("src","${baseURL}/manager/admin/org/orgAdmin.jsp");
        }else if(index == "1"){
            $("#adminFrame").attr("src","${baseURL}/manager/admin/user/userAdmin.jsp");
        }else if(index == "2"){
            $("#adminFrame").attr("src","${baseURL}/manager/admin/config/configAdmin.jsp");
        }else if(index == "3"){
            $("#adminFrame").attr("src","${baseURL}/manager/admin/menu/menuAdmin.jsp");
        }else if(index == "4"){
            $("#adminFrame").attr("src","${baseURL}/manager/admin/agent/agentAdmin.jsp");
        }else{
            $("#adminFrame").attr("src","${baseURL}/manager/admin/org/orgAdmin.jsp");
        }
	}
    function loginOutAdmin(){
        $.ajax({
            url: "${baseURL}/manager/admin/loginOutAdmin.action",
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
