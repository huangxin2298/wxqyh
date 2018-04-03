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

    <title>课程列表</title>

    <link href="${baseURL}/common/css/bootstrap.css" rel="stylesheet">
    <link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">

    <script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
    <script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

    <script src="${baseURL}/common/js/html5shiv.min.js"></script>
    <script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body>
<form action="" method="post" id="searchForm" onsubmit="return false;">
    <div class="topMainBar clearfix">
        <div class="fl flMainBar" style="margin-top: 6px;">
            <a id="" href="javascript:addUser();" onclick="" class="btn orangeBtn smallBtn">新增</a>
        </div>
        <%--<a class="senior_search_btn">高级搜索</a>--%>
        <div class="fr">
            <div class="searchBox">
                <input type="hidden" name="curPage" id="curPage">
                <input placeholder="课程名" class="inputSearchBox" type="text" name="inputSearch" id="inputSearch"/>
                <input class="submitSearchBtn" type="button" onclick="getCourseList()" value=""/>
            </div>
        </div>
    </div>
</form>
<div class="contactTable mt10" id="cc94a9ec68ff4d93a7fd5934c581b647">
    <table class="table table-line table-hover">
        <thead>
        <tr>
            <th width='20'><input type="checkbox" class="form-checkbox" onclick=""></th>
            <th width='200'>课程名</th>
            <th width="100">学期</th>
            <th width='100'>开始周</th>
            <th width="100">结束周</th>
            <th width="100">上课时间</th>
            <th width="150">课室</th>
            <th width="150">课程教师</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody id="pageData">

        </tbody>
    </table>
    <div class="bottomPage" id="page">
        <!-- 分页 -->
        <div class="pager-nav">
            <ul id="pageList" style="display: none;">
                <li>总计<span id="totalRows"></span>条</li>
                <li class="pager-prev" id="lastPage"><a href="javascript:void(0)">上一页</a></li>
                <li class="pager-next" id="nextPage"><a href="javascript:void(0)">下一页</a></li>
                <li>第<span id="currentPage"></span>页</li>
            </ul>
            <div id="noPage" style="display: none;">暂无数据，换个关键字搜索试一下</div>
        </div>
    </div>

</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>

    var courseTemplate = "<tr>" +
        "                <td><input type=\"checkbox\" class=\"form-checkbox\" value=\"@{id}\" name=\"ids\"></td>" +
        "                <td><span>@courseName</span></td>" +
        "                <td><span>@termName</span></td>" +
        "                <td><span>@startWeek</span></td>" +
        "                <td><span>@endWeek</span></td>" +
        "                <td><span>@classTime</span></td>" +
        "                <td><span>@classroomName</span></td>" +
        "                <td><span>@teacherName</span></td>" +
        "                <td class=\"lasttd\">" +
        "                    <span class=\"contact_action\">" +
        "                        <a>操作</a>" +
        "                    </span>" +
        "                    <div class=\"lasttdpop\" style=\"display: none\">" +
        "                        <a href=\"javascript:doEdit('@{id}')\">编辑</a>" +
        "                        <a href=\"javascript:doDel('@{id}')\">删除</a>" +
        "                    </div>" +
        "                </td>" +
        "            </tr>";

    $(document).ready(function () {
        getCoursePage(1);
    });

    function getCoursePage(curPage) {

        $("#pageData").html("");
        $("#curPage").val(curPage);
        var form = $("#searchForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/course/getCoursePage.action",
            type: "POST",
            async: false,
            data: form,
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    var pageData = result.data;
                    if(typeof (pageData)!="undefined" && pageData!=null){
                        if(parseInt(pageData.totalRows)>0){
                            $("#pageList").show();
                            $("#noPage").hide();
                            $("#totalRows").html(pageData.totalRows);
                            $("#currentPage").html(pageData.curPage);
                            if(pageData.curPage <= 1){
                                $("#lastPage").children("a").attr("href","javascript:void(0)");
                            }else{
                                $("#lastPage").children("a").attr("href","javascript:getCoursePage(\""+(parseInt(pageData.curPage) - 1)+"\")");
                            }
                            if(pageData.curPage >= pageData.totalPages){
                                $("#nextPage").children("a").attr("href","javascript:void(0)");
                            }else{
                                $("#nextPage").children("a").attr("href","javascript:getCoursePage(\""+(parseInt(pageData.curPage) + 1)+"\")");
                            }
                            var coursePage = pageData.data;
                            if(typeof(coursePage)!="undefined" && coursePage != null && coursePage.length>0){
                                var pageDateHtml = "";
                                for(var i=0;i<coursePage.length;i++){
                                    var courseHtml = courseTemplate;
                                    courseHtml = courseHtml.replace(/@{id}/g,coursePage[i].id);
                                    courseHtml = courseHtml.replace("@courseName",coursePage[i].courseName);
                                    courseHtml = courseHtml.replace("@termName",coursePage[i].termName);
                                    courseHtml = courseHtml.replace("@startWeek",coursePage[i].startWeek);
                                    courseHtml = courseHtml.replace("@endWeek",coursePage[i].endWeek);
                                    courseHtml = courseHtml.replace("@classTime",numToWeek(coursePage[i].classTime)+"第"+coursePage[i].sessions+"节");
                                    courseHtml = courseHtml.replace("@classroomName",coursePage[i].classroomName);
                                    courseHtml = courseHtml.replace("@teacherName",coursePage[i].teacherName);
                                    pageDateHtml += courseHtml;
                                }
                                $("#pageData").html(pageDateHtml);
                                loadOpt();
                            }else{
                                $("#pageData").html("");
                            }
                        }else{
                            $("#pageData").html("");
                            $("#pageList").hide();
                            $("#noPage").show();
                        }
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
    function doEdit(courseId){
        document.location.href = "${baseURL}/manager/application/course/edit.jsp?courseId="+courseId;
    }
    function doDel(courseId){
        if(!confirm("您确定要删除该课程？")){
            return;
        }
        $.ajax({
            url: "${baseURL}/manager/course/delCourseById.action",
            type: "POST",
            data: {"courseId":courseId},
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    getCoursePage(1);
                } else {
                    alert(result.describe);
                }
            },
            error: function() {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function addUser(){
        document.location.href = "${baseURL}/manager/application/course/add.jsp";
    }
    function loadOpt(){
        $('.lasttd').on({
            mouseover: function () {
                if (navigator.userAgent.toUpperCase().indexOf("MSIE") > -1) {
                    $(this).find('.lasttdpop').css({'display': 'block'});
                    $(this).children('.contact_action').addClass('after');
                } else if (navigator.userAgent.toUpperCase().indexOf("SAFARI") > -1) {
                    $(this).find('.lasttdpop').css({'display': '-webkit-flex'});
                    $(this).children('.contact_action').addClass('after');
                }
                else {
                    $(this).find('.lasttdpop').css({'display': 'flex'});
                    $(this).children('.contact_action').addClass('after');
                }
            },
            mouseout: function () {
                $(this).find('.lasttdpop').hide();
                $(this).children('.contact_action').attr('class', 'contact_action');
            }
        });
    }
    function numToWeek(num){
        if(isNaN(num)){
            return "";
        }
        var myNum = parseInt(num);
        var week="";
        switch(myNum)
        {
            case 1:
                week="周一";
                break;
            case 2:
                week="周二";
                break;
            case 3:
                week="周三";
                break;
            case 4:
                week="周四";
                break;
            case 5:
                week="周五";
                break;
            case 6:
                week="周六";
                break;
            case 7:
                week="周日";
                break;
        }
        return week;
    }
</script>
</body>
</html>
