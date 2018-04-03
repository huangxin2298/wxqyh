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

    <title>修改应用</title>

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
    <div class="cTitle"><b>修改菜单</b></div>
    <div class="cMain">
        <form action="" method="post" id="agentForm" onsubmit="return false;"class="form new_form">
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>应用名：
                </div>
                <div class="form-field">
                    <input type="text" name="agentName" placeholder="应用名" id="agentName" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>应用code：
                </div>
                <div class="form-field">
                    <input type="text" name="agentCode" placeholder="应用code" id="agentCode" class="form-text input-text" readonly>
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>应用secret：
                </div>
                <div class="form-field">
                    <input type="text" name="secret" placeholder="应用secret" id="secret" class="form-text input-text">
                </div>
            </div>
            <div class="form-action tcform_action">
                <input type="button" class="btn orangeBtn smallBtn" value="提交" onclick="publish()">
                <input type="button" class="btn orangeBtn smallBtn" value="取消" onclick="javascript:history.back();">
            </div>
            <input type="hidden" name="id" id="agentId">
        </form>
    </div>

</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="${baseURL}/common/js/common.js"></script>
<script>
    $(document).ready(function () {
        getAgentInfo();
    });
    function getAgentInfo() {
        $.ajax({
            url: "${baseURL}/manager/admin/getAgentInfo.action",
            type: "GET",
            async: false,
            data: {"agentId":"${param.agentId}"},
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    var agentInfo = result.data;
                    if(typeof (agentInfo)!="undefined" && agentInfo!=null){
                        $("#agentId").val(agentInfo.id);
                        $("#agentName").val(agentInfo.agentName);
                        $("#agentCode").val(agentInfo.agentCode);
                        $("#secret").val(agentInfo.secret);
                    }else{
                        alert("获取应用信息失败，请刷新重试");
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
        if($("#agentName").val()==""){
            alert("应用名不能为空");
            return;
        }
        if($("#agentCode").val()==""){
            alert("应用code不能为空");
            return;
        }
        if($("#secret").val()==""){
            alert("secret不能为空");
            return;
        }
        var form = $("#agentForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/admin/updateAgentInfo.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    document.location.href="${baseURL}/manager/admin/agent/agentAdmin.jsp";
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
