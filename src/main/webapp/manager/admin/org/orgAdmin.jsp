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

    <title>机构管理</title>

    <link href="${baseURL}/common/css/bootstrap.css" rel="stylesheet">
    <link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">

    <script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
    <script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

    <script src="${baseURL}/common/js/html5shiv.min.js"></script>
    <script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body>
<div>
    <div class="cTitle"><b>机构管理</b></div>
    <div class="cMain">
        <form action="" method="post" id="userForm" onsubmit="return false;"class="form new_form">
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>机构名称：
                </div>
                <div class="form-field">
                    <input type="text" name="orgName" placeholder="机构名称" id="orgName" class="form-text input-text" style="width: 400px">
                </div>
            </div>
            <div class="form-item">
                <div style="color:grey;margin-left: 150px">请控制在20字以内</div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>CorpId：
                </div>
                <div class="form-field">
                    <input type="text" name="corpId" placeholder="企业CorpId" id="corpId" class="form-text input-text" style="width: 400px">
                </div>
            </div>
            <div class="form-item">
                <div style="color:grey;margin-left: 150px">企业微信创建的企业的corpId，可在企业微信管理后台获取到</div>
            </div>
            <div class="dashed mt30 mb30"></div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>CorpSecret：
                </div>
                <div class="form-field">
                    <input type="text" name="corpSecret" placeholder="" id="corpSecret" class="form-text input-text" style="width: 400px">
                </div>
            </div>
            <div class="form-item">
                <div style="color:grey;margin-left: 150px">企业微信创建的企业的Secret，即通讯录接口的Secret，可在企业微信管理后台获取到</div>
            </div>
            <div class="form-action tcform_action">
                <input type="button" class="btn orangeBtn smallBtn" value="提交" onclick="publish()">
                <input type="button" class="btn orangeBtn smallBtn" value="取消" onclick="javascript:history.back();">
            </div>
            <input type="hidden" name="id" id="orgId">
        </form>
    </div>

</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="${baseURL}/common/js/common.js"></script>
<script>
    $(document).ready(function () {
        getOrgInfo();
    });
    function getOrgInfo() {
        $.ajax({
            url: "${baseURL}/manager/admin/getOrgInfo.action",
            type: "GET",
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    var orgInfo = result.data;
                    if(typeof (orgInfo)!="undefined" && orgInfo!=null){
                        $("#orgId").val(orgInfo.id);
                        $("#orgName").val(orgInfo.orgName);
                        $("#corpId").val(orgInfo.corpId);
                        $("#corpSecret").val(orgInfo.corpSecret);
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
        if($("#orgName").val()==""){
            alert("机构名称不能为空");
            return;
        }
        if($("#corpId").val()==""){
            alert("corpId不能为空");
            return;
        }
        if($("#corpSecret").val()==""){
            alert("corpSecret不能为空");
            return;
        }
        var form = $("#userForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/admin/updateOrgInfo.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
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
