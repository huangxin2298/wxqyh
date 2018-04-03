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

    <title>新增课程</title>

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
    <div class="cTitle"><b>新增课程</b></div>
    <div class="cMain">
        <form action="" method="post" id="courseForm" onsubmit="return false;" class="form new_form">
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>课程名：
                </div>
                <div class="form-field">
                    <input type="text" name="courseName" placeholder="课程名" id="courseName" class="form-text input-text">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    学期：
                </div>
                <div class="form-field">
                    <select name="termId" id="termId" onchange="setTermName()">
                    </select>
                    <input type="hidden" name="termName" id="termName">
                </div>
            </div>
            <div class="dashed mt30 mb30"></div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>开始周：
                </div>
                <div class="form-field">
                    <select id="startWeek" name="startWeek">
                    </select>
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>结束周：
                </div>
                <div class="form-field">
                    <select id="endWeek" name="endWeek">
                    </select>
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>上课时间：
                </div>
                <div class="form-field">
                    <select id="classTime" name="classTime">
                        <option value="1">周一</option>
                        <option value="2">周二</option>
                        <option value="3">周三</option>
                        <option value="4">周四</option>
                        <option value="5">周五</option>
                        <option value="6">周六</option>
                        <option value="7">周日</option>
                    </select>
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>节数：
                </div>
                <div class="form-field">
                    <input type="checkbox" name="sessions" value="1">1
                    <input type="checkbox" name="sessions" value="2">2
                    <input type="checkbox" name="sessions" value="3">3
                    <input type="checkbox" name="sessions" value="4">4
                    <input type="checkbox" name="sessions" value="5">5
                    <input type="checkbox" name="sessions" value="6">6
                    <input type="checkbox" name="sessions" value="7">7
                    <input type="checkbox" name="sessions" value="8">8
                    <input type="checkbox" name="sessions" value="9">9
                    <input type="checkbox" name="sessions" value="10">10
                    <input type="checkbox" name="sessions" value="11">11
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>课室：
                </div>
                <div class="form-field">
                    <input type="button" value="选择" onclick="searchClassroom()">
                    <input type="text" name="classroomName" id="classroomName" readonly style="border: 0">
                    <input type="hidden" name="classroomId" id="classroomId"">
                </div>
            </div>
            <div class="form-item">
                <div class="title">
                    <span class="mustInput">*</span>任课教师：
                </div>
                <div class="form-field">
                    <input type="text" name="tearcher" placeholder="任课教师" id="tearcher" class="form-text input-text">
                </div>
            </div>
            <div class="form-action tcform_action" style="margin-top: 12px">
                <input type="button" class="btn orangeBtn smallBtn" value="新增" onclick="publish()">
                <input type="button" class="btn orangeBtn smallBtn" value="取消" onclick="javascript:history.back();">
            </div>
        </form>
        <div class="pop_wrap6" style="display: none">
            <div class="SS_tit"><span>选择课室</span><i onclick="closePop()">×</i></div>
            <div class="pop_wrap6_main">
                <div class="SS_main_top">
                    <div class="selected">
                        <div class="personnelList"></div>
                        <input type="text" placeholder="输入搜索条件" class="P_search ss_searchCreator"
                               style="width:100%;height:30px;margin-top: 0px;">
                        <input type="hidden" class="currPage">
                    </div>
                    <div class="content">
                        <table class="table table-line table-hover">
                            <thead>
                            <tr>
                                <th width='8%'>勾选</th>
                                <th width='20%'>课室名</th>
                                <th width="16%">可容纳人数</th>
                                <th width="41%">地址</th>
                                <th width="10%">多媒体</th>
                            </tr>
                            </thead>
                            <tbody id="popPageData">
                            <tr>
                                <td><input type="radio" class="form-checkbox" name="classroomId" onclick=""></td>
                                <td>课室1</td>
                                <td>100</td>
                                <td>教学楼</td>
                                <td>有</td>
                            </tr>
                            <tr>
                                <td><input type="radio" class="form-checkbox" name="classroomId" onclick=""></td>
                                <td>课室2</td>
                                <td>100</td>
                                <td>教学楼</td>
                                <td>有</td>
                            </tr>
                            <tr>
                                <td><input type="radio" class="form-checkbox" name="classroomId" onclick=""></td>
                                <td>课室3</td>
                                <td>100</td>
                                <td>教学楼</td>
                                <td>无</td>
                            </tr>
                            <tr>
                                <td><input type="radio" class="form-checkbox" name="classroomId" onclick=""></td>
                                <td>课室4</td>
                                <td>100</td>
                                <td>教学楼</td>
                                <td>有</td>
                            </tr>
                            <tr>
                                <td><input type="radio" class="form-checkbox" name="classroomId" onclick=""></td>
                                <td>课室5</td>
                                <td>100</td>
                                <td>教学楼</td>
                                <td>无</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="SS_btn"><input type="button" value="确定" class="btn orangeBtn smallBtn mr10 mb0"></div>
        </div>
    </div>

</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="${baseURL}/common/js/common.js"></script>
<script>
    $(document).ready(function () {
        getTermList();
    });
    var weekNumMap = {}

    function getTermList() {
        $.ajax({
            url: "${baseURL}/manager/course/getCourseTermList.action",
            type: "GET",
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    var termList = result.data;
                    if (typeof (termList) != "undefined" && termList != null && termList.length > 0) {
                        var termOptionHtml = "";
                        for (var i = 0; i < termList.length; i++) {
                            weekNumMap[termList[i].id] = termList[i].weekNum;
                            termOptionHtml += "<option value=\"" + termList[i].id + "\">" + termList[i].termYear + termList[i].term + "</option>";
                        }
                        $("#termId").append(termOptionHtml);
                        setTermName();
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

    function searchClassroom() {
        $(".pop_wrap6").show();
    }

    function publish() {
        if ($("#courseName").val() == "") {
            alert("课程名不能为空");
            return;
        }
        if ($("#termId").val() == "") {
            alert("学期不能为空");
            return;
        }
        if ($("#startWeek").val() == "") {
            alert("开始周不能为空");
            return;
        }
        if ($("#endWeek").val() == "") {
            alert("结束周不能为空");
            return;
        }
        if (parseInt($("#startWeek").val()) > parseInt($("#endWeek").val())) {
            alert("开始周不能大于结束周");
            return;
        }
        if ($("#tearcher").val() != "") {
            alert("任课教师不能为空");
            return;
        }
        var form = $("#courseForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/course/addCourseInfo.action",
            type: "POST",
            data: form,
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    alert(result.describe);
                    document.location.href = "${baseURL}/manager/application/course/list.jsp";
                } else {
                    alert(result.describe);
                }
            },
            error: function () {
                alert("系统繁忙，请稍后重试");
            }
        })
    }

    function setTermName() {
        var termName = $("#termId").html();
        $("#termName").val(termName);
        setWeekOption();
    }

    function setWeekOption() {
        var termId = $("#termId").val();
        var weekNum = weekNumMap[termId];
        var weekOptionHtml = "";
        for (var i = 1; i <= parseInt(weekNum); i++) {
            weekOptionHtml += "<option value=\"" + i + "\">" + i + "</option>";
        }
        $("#startWeek").html(weekOptionHtml);
        $("#endWeek").html(weekOptionHtml);
    }

    function closePop() {
        $(".pop_wrap6").hide();
    }
</script>
</body>
</html>
