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

    <title>新增学期</title>

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
    <div class="cTitle"><b>新增学期</b></div>
    <div class="cMain">
        <form action="" method="post" id="termForm" onsubmit="return false;" class="form new_form">
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>学年：
                </div>
                <div class="form-field">
                    <select name="termYear" id="termYear">
                    </select>
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>学期：
                </div>
                <div class="form-field">
                    <select name="term" id="term">
                        <option value="第一学期">第一学期</option>
                        <option value="第二学期">第二学期</option>
                    </select>
                </div>
            </div>
            <div class="dashed mt30 mb30"></div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>开始时间：
                </div>

                <div class="form-field">
                    <input class="form-text input-text" id="startTime" name="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',minDate:'%y-01-01',maxDate:'#F{$dp.$D(\'endTime\')||\'{%y+1}-12-31\'}',onpicked:coutBtwWeek()})">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>结束时间：
                </div>
                <div class="form-field">
                    <input class="form-text input-text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'{%y+1}-12-31',onpicked:coutBtwWeek()})">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>总周数：
                </div>
                <div class="form-field">
                    <input class="form-text input-text" id="weekNum" name="weekNum" readonly>
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
    loadTermYear();

    function publish() {
        if ($("#termYear").val() == "") {
            alert("学年不能为空");
            return;
        }
        if ($("#term").val() == "") {
            alert("学期不能为空");
            return;
        }
        if ($("#startTime").val() == "") {
            alert("学期开始时间不能为空");
            return;
        }
        if ($("#endTime").val() == "") {
            alert("学期结束时间不能为空");
            return;
        }
        if ($("#term").val() == "") {
            alert("学期周数不能为空");
            return;
        }
        var form = $("#termForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/course/addCourseTerm.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    alert(result.describe);
                    document.location.href = "${baseURL}/manager/application/course/term_list.jsp";
                } else {
                    alert(result.describe);
                }
            },
            error: function () {
                alert("系统繁忙，请稍后重试");
            }
        })
    }

    function loadTermYear() {
        var date = new Date();
        var year = date.getFullYear();
        $("#termYear").html("<option value='" + year + "'>" + year + "</option><option value='"+(year+1)+"'>"+(year+1)+"</option>");
    }

    function coutBtwWeek(){
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if(startTime!=null && startTime!="" && endTime!=null && endTime!=""){
            var btwWeek = WeeksBetw(endTime.replace(/-/g,"/"),startTime.replace(/-/g,"/"));
            $("#weekNum").val(btwWeek);
        }
    }
    function WeeksBetw(date1, date2) {
        var _dt1 = new Date(date1);
        var _dt2 = new Date(date2);
        var dt1 = _dt1.getTime();
        var dt2 = _dt2.getTime();
        if (dt1 < dt2) {
            return 0;
        } else {
            return Math.ceil((dt1 - dt2)/ 1000 / 60 / 60 / 24 / 7);
        }
    }
</script>
</body>
</html>
