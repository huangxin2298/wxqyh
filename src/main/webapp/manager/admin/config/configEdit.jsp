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

    <title>修改配置项</title>

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
    <div class="cTitle"><b>修改配置项</b></div>
    <div class="cMain">
        <form action="" method="post" id="configForm" onsubmit="return false;"class="form new_form">
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>配置项名：
                </div>
                <div class="form-field">
                    <input type="text" name="configName" placeholder="配置项名" id="configName" class="form-text input-text" readonly>
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>配置项值：
                </div>
                <div class="form-field">
                    <input type="text" name="configValue" placeholder="配置项值" id="configValue" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    描述：
                </div>
                <div class="form-field">
                    <textarea name="description" placeholder="描述" id="description" style="width:290px;height: 120px"></textarea>
                </div>
            </div>
            <div class="form-action tcform_action">
                <input type="button" class="btn orangeBtn smallBtn" value="提交" onclick="publish()">
                <input type="button" class="btn orangeBtn smallBtn" value="取消" onclick="javascript:history.back();">
            </div>
            <input type="hidden" name="id" id="configId">
        </form>
    </div>

</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="${baseURL}/common/js/common.js"></script>
<script>
    $(document).ready(function () {
        getAdminConfig();
    });
    function getAdminConfig() {
        $.ajax({
            url: "${baseURL}/manager/admin/getAdminConfig.action",
            type: "POST",
            async: false,
            data: {"configId":"${param.configId}"},
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    var adminConfig = result.data;
                    if(typeof (adminConfig)!="undefined" && adminConfig!=null){
                        $("#configId").val(adminConfig.id);
                        $("#configName").val(adminConfig.configName);
                        $("#configValue").val(adminConfig.configValue);
                        $("#description").val(adminConfig.description);
                    }else{
                        alert("获取配置项信息失败，请刷新重试");
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
        if($("#configName").val()==""){
            alert("配置项名不能为空");
            return;
        }
        if($("#configValue").val()==""){
            alert("配置项值不能为空");
            return;
        }
        var form = $("#configForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/admin/updateAdminConfig.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    document.location.href="${baseURL}/manager/admin/config/configAdmin.jsp";
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
