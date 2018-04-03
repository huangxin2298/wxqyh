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

    <title>新增用户</title>

    <link href="${baseURL}/common/css/bootstrap.css" rel="stylesheet">
    <link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">

    <script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
    <script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

    <script src="${baseURL}/common/js/html5shiv.min.js"></script>
    <script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body>
<div class="topMainBar clearfix">
    <div class="fl"><a href="javascript:history.back();" class="btn smallBtn">返回</a>
    </div>
</div>
<div>
    <div class="cTitle"><b>新增用户</b></div>
    <div class="cMain">
        <form action="" method="post" id="userForm" onsubmit="return false;"class="form new_form">
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>用户名：
                </div>
                <div class="form-field">
                    <input type="text" name="userName" placeholder="用户名" id="userName" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>帐号：
                </div>
                <div class="form-field">
                    <input type="text" name="account" placeholder="帐号" id="account" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>登录密码：
                </div>
                <div class="form-field">
                    <input type="password" name="password" placeholder="登录密码" id="password" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>确认密码：
                </div>
                <div class="form-field">
                    <input type="password" name="passwordCheck" placeholder="登录密码" id="passwordCheck" class="form-text input-text">
                </div>
            </div>
            <div class="form-action tcform_action">
                <input type="button" class="btn orangeBtn smallBtn" value="新增" onclick="publish()">
                <input type="button" class="btn orangeBtn smallBtn" value="取消" onclick="javascript:history.back();">
            </div>
        </form>
    </div>

</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="${baseURL}/common/js/common.js"></script>
<script>
    function publish(){
        if($("#userName").val()==""){
            alert("成员姓名不能为空");
            return;
        }
        if($("#account").val()==""){
            alert("帐号不能为空");
            return;
        }
        if($("#password").val()==""){
            alert("密码不能为空");
            return;
        }
        if($("#password").val() != $("#passwordCheck").val()){
            alert("两次密码不一致");
            return;
        }
        var form = $("#userForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/admin/addAdminUser.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    document.location.href="userAdmin.jsp";
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
