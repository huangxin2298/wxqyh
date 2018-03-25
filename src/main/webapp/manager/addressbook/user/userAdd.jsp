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
    <div class="cTitle"><b>新增成员</b></div>
    <div class="cMain">
        <form action="" method="post" id="userForm" onsubmit="return false;"class="form new_form">
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>姓名：
                </div>
                <div class="form-field">
                    <input type="text" name="userName" placeholder="成员姓名" id="userName" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    性别：
                </div>
                <div class="form-field">
                    <select name="sex" id="sex">
                        <option value="0">请选择</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </div>
            </div>
            <div class="dashed mt30 mb30"></div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>手机号：
                </div>
                <div class="form-field">
                    <input  class="form-text input-text" id="mobile" name="mobile">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    邮箱：
                </div>
                <div class="form-field">
                    <input class="form-text input-text" id="email" name="email">
                </div>
            </div>
            <div class="dashed mt30 mb30"></div>
            <div class="form-item">
                <div class="title">所属部门：</div>
                <div class="form-field">
                    <select name="deptId" id="deptId" onchange="setDeptName(this);">
                    </select>
                    <input type="hidden" name="deptName" id="deptName">
                </div>
            </div>

            <div class="form-item">
                <div class="title">
                    职位：
                </div>
                <div class="form-field">
                    <input class="form-text input-text" name="position" id="position">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    座机：
                </div>
                <div class="form-field">
                    <input  class="form-text input-text" name="telephone" id="telephone">
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
    $(document).ready(function () {
        getDeptList();;
    });
    function getDeptList(){
        $.ajax({
            url: "${baseURL}/manager/addressbook/getDepartmentList.action",
            type: "GET",
            async: false,
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    var deptList = result.data;
                    if(typeof (deptList)!="undefined" && deptList != null && deptList.length>0){
                        var deptIdHtml = "";
                        for(var i=0;i<deptList.length;i++){
                            deptIdHtml += "<option value=\""+deptList[i].id+"\">"+deptList[i].departmentName+"</option>";
                        }
                        $("#deptId").append(deptIdHtml);
                        var departmentName = $("#deptId").find("option:selected").text();
                        $("#deptName").val(departmentName);
                    }
                } else {
                    alert(result.describe);
                }
            },
            error: function() {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function publish(){
        if($("#userName").val()==""){
            alert("成员姓名不能为空");
            return;
        }
        if($("#mobile").val()==""){
            alert("成员手机号不能为空");
            return;
        }
        if(!isPhoneAvailable($("#mobile").val())){
            alert("成员手机号格式不正确");
            return;
        }
        if($("#email").val()!=""){
            if(!isEmailAvailable($("#email").val())){
                alert("成员邮箱格式不正确");
                return;
            }
        }
        var form = $("#userForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/addressbook/addUser.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    document.location.href="${baseURL}/manager/addressbook/detail.jsp";
                } else {
                    alert(result.describe);
                }
            },
            error: function() {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function setDeptName(elem){
        var deptName = $(elem).find("option:selected").text();
        $("#deptName").val(deptName);
    }
</script>
</body>
</html>
