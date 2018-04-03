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

    <title>课程管理</title>

    <link href="${baseURL}/common/css/bootstrap.css" rel="stylesheet">
    <link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">

    <script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
    <script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

    <script src="${baseURL}/common/js/html5shiv.min.js"></script>
    <script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body style="margin:0;padding: 0">
<div>
    <div class="tabs-primary" id="navigation">
        <a href="#" class="active" onclick="changeContentFrame(this,'0')">课程管理</a>
        <a href="#" onclick="changeContentFrame(this,'1')">课室管理</a>
        <a href="#" onclick="changeContentFrame(this,'2')">学期管理</a>
    </div>
    <iframe style="float:left;overflow-x:hidden;overflow-y:auto;" name="contentFrame" class="innerbox" id="contentFrame"
            src="${baseURL}/manager/application/course/list.jsp" width="100%" height="580px;" frameborder="0">
    </iframe>
</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>
    function changeContentFrame(elem, index){
        $(elem).siblings("a").removeClass("active");
        $(elem).addClass("active");
        if("0" == index){
            $("#contentFrame").attr("src","list.jsp");
        }else if("1" == index){
            $("#contentFrame").attr("src","classroom_list.jsp");
        }else if("2" == index){
            $("#contentFrame").attr("src","term_list.jsp");
        }
    }
</script>
</body>
</html>
