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

    <title>新增课室</title>

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
    <div class="cTitle"><b>新增课室</b></div>
    <div class="cMain">
        <form action="" method="post" id="classroomForm" onsubmit="return false;" class="form new_form">
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>课室名：
                </div>
                <div class="form-field">
                    <input type="text" name="classroomName" placeholder="课室名" id="classroomName" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>可容纳人数：
                </div>
                <div class="form-field">
                    <input type="number" name="admissNum" placeholder="可容纳人数" id="admissNum" class="form-text input-text" onchange="limitNum(this)">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>地址：
                </div>
                <div class="form-field">
                    <input type="text" name="address" placeholder="地址" id="address" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>是否有多媒体：
                </div>
                <div class="form-field">
                    <input id="ismultime0" type="radio" name="ismultime" value="0" checked="checked">否</input>
                    <input id="ismultime1" type="radio" name="ismultime" value="1">是</input>
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
<script src="${baseURL}/common/js/bootstrap.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="${baseURL}/common/js/common.js"></script>
<script src="${baseURL}/themes/manager/My97DatePicker/WdatePicker.js"></script>
<script>
    function publish() {
        if ($("#classroomName").val() == "") {
            alert("课室名不能为空");
            return;
        }
        if ($("#admissNum").val() == "") {
            alert("可容纳人数不能为空");
            return;
        }
        if (!isPositiveInteger($("#admissNum").val()) || parseInt($("#admissNum").val())<=0) {
            alert("可容纳人数必须为正整数");
            return;
        }
        if ($("#address").val() == "") {
            alert("课室地址不能为空");
            return;
        }
        if ($("#ismultime").val() == "") {
            alert("请选择是否有多媒体");
            return;
        }
        var form = $("#classroomForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/course/addCourseClassroom.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    alert(result.describe);
                    document.location.href = "${baseURL}/manager/application/course/classroom_list.jsp";
                } else {
                    alert(result.describe);
                }
            },
            error: function () {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function limitNum(elem){
        if(parseInt($(elem).val())<0){
            $(elem).val(0);
        }
    }
</script>
</body>
</html>
