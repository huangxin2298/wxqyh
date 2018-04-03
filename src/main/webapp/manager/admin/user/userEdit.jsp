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

    <title>修改用户</title>

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
    <div class="cTitle"><b>修改用户</b></div>
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
                    <span class="mustInput"></span>开启密码修改：
                </div>
                <div class="form-field">
                    <div class="switch" onclick="changeSwitch(this)">
                        <p id="passwordStatus" class="off"></p>
                    </div>
                </div>
            </div>
            <div id="passwordDiv" style="display: none">
                <div class="form-item">
                    <div class="title">
                        <span class="mustInput">*</span>旧密码：
                    </div>
                    <div class="form-field">
                        <input type="password" name="oldPassword" placeholder="旧密码" id="oldPassword" class="form-text input-text">
                    </div>
                </div>
                <div class="form-item">
                    <div class="title">
                        <span class="mustInput">*</span>新密码：
                    </div>
                    <div class="form-field">
                        <input type="password" name="newPassword" placeholder="新密码" id="newPassword" class="form-text input-text">
                    </div>
                </div>
                <div class="form-item">
                    <div class="title">
                        <span class="mustInput">*</span>新密码确定：
                    </div>
                    <div class="form-field">
                        <input type="password" placeholder="新密码" id="newPasswordCheck" class="form-text input-text">
                    </div>
                </div>
            </div>
            <div class="form-action tcform_action">
                <input type="button" class="btn orangeBtn smallBtn" value="提交" onclick="publish()">
                <input type="button" class="btn orangeBtn smallBtn" value="取消" onclick="javascript:history.back();">
            </div>
            <input type="hidden" name="id" id="userId">
        </form>
    </div>

</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="${baseURL}/common/js/common.js"></script>
<script>
    $(document).ready(function () {
        getUserList();
    });
    var deptId;
    function getUserList() {
        $.ajax({
            url: "${baseURL}/manager/admin/getAdminUserInfo.action",
            type: "POST",
            async: false,
            data: {"userId":"${param.userId}"},
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    var adminUserPO = result.data;
                    if(typeof (adminUserPO)!="undefined" && adminUserPO!=null){
                        $("#userId").val(adminUserPO.id);
                        $("#userName").val(adminUserPO.userName);
                    }
                } else {
                    alert(result.describe);
                }
            },
            error: function () {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function publish(){
        if($("#userName").val()==""){
            alert("用户名不能为空");
            return;
        }
        var passwordStatus = $("#passwordStatus").attr("class");
        if(passwordStatus=="on"){
            if($("#newPasswordCheck").val()!=$("#newPassword").val()){
                alert("新密码两次输入不一致");
                return;
            }
        }
        var form = $("#userForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/admin/updateAdminUser.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    document.location.href="${baseURL}/manager/admin/user/userAdmin.jsp";
                } else {
                    alert(result.describe);
                }
            },
            error: function() {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function changeSwitch(elem){
        var passwordStatus = $(elem).children("#passwordStatus").attr("class");
        if("off" == passwordStatus){
            $(elem).children("#passwordStatus").attr("class","on");
            $("#passwordDiv").show();
        }else{
            $(elem).children("#passwordStatus").attr("class","off");
            $("#passwordDiv").hide();
        }
    }
</script>
</body>
</html>
