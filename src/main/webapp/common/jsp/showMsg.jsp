<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <link href="${baseURL}/common/css/showMsg.css" rel="stylesheet">
</head>
<body style="padding:0px;margin: 0px">
<div style="display: none" id="msg">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title" id="title"></strong></div>
        <div class="weui_dialog_bd" id="content"></div>
        <div class="weui_dialog_ft" id="button">
            <a href="javascript:hideMsg()" class="weui_btn_dialog primary">确认</a>
        </div>
    </div>
</div>
<div class="loading" id="loading" style="display: none">
    <div>
        <div class="c1"></div>
        <div class="c2"></div>
        <div class="c3"></div>
        <div class="c4"></div>
    </div>
    <span id="loadingContent">加载中...</span>
</div>
<script>
    function showMsg(title,content){
        $("#title").html(title);
        $("#content").html(content);
        $("#msg").show();
    }
    function hideMsg(){
        $("#title").html("");
        $("#content").html("");
        $("#msg").hide();
    }
    function showLoading(title){
        if(!title){
            $("#loadingContent").text(title);
        }else{
            $("#loadingContent").text("加载中...");
        }
        $("#loading").show();
    }
    function hideLoading(){
        $("#loading").hide();
    }
    showLoading()
</script>
</body>
</html>
