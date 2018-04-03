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

    <title>应用管理</title>

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
            <a id="" href="javascript:addAgent();" onclick="" class="btn orangeBtn smallBtn">新增</a>
        </div>
        <%--<a class="senior_search_btn">高级搜索</a>--%>
        <div class="fr">
            <div class="searchBox">
                <input type="hidden" name="curPage" id="curPage">
                <input placeholder="" class="inputSearchBox" type="text" name="inputSearch" id="inputSearch"/>
                <input class="submitSearchBtn" type="button" onclick="getAgentPage()" value=""/>
            </div>
        </div>
    </div>
</form>
<div class="contactTable mt10" id="cc94a9ec68ff4d93a7fd5934c581b647">
    <table class="table table-line table-hover">
        <thead>
        <tr>
            <th width='20'><input type="checkbox" class="form-checkbox" onclick=""></th>
            <th width='200'>应用code</th>
            <th width='100'>应用名</th>
            <th width="350">应用微信secret</th>
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

    var agentTemplate = "<tr>" +
        "                <td><input type=\"checkbox\" class=\"form-checkbox\" value=\"@{id}\" name=\"ids\"></td>" +
        "                <td><span>@agentCode</span></td>" +
        "                <td><span>@agentName</span></td>" +
        "                <td><span>@secret</span></td>" +
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
        getAgentPage(1);
    });

    function getAgentPage(curPage) {

        $("#pageData").html("");
        $("#curPage").val(curPage);
        var form = $("#searchForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/admin/getAgentPage.action",
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
                                $("#lastPage").children("a").attr("href","javascript:getAgentPage(\""+(parseInt(pageData.curPage) - 1)+"\")");
                            }
                            if(pageData.curPage >= pageData.totalPages){
                                $("#nextPage").children("a").attr("href","javascript:void(0)");
                            }else{
                                $("#nextPage").children("a").attr("href","javascript:getAgentPage(\""+(parseInt(pageData.curPage) + 1)+"\")");
                            }
                            var agentPage = pageData.data;
                            if(typeof(agentPage)!="undefined" && agentPage != null && agentPage.length>0){
                                var pageDateHtml = "";
                                for(var i=0;i<agentPage.length;i++){
                                    var agentHtml = agentTemplate;
                                    agentHtml = agentHtml.replace(/@{id}/g,agentPage[i].id);
                                    agentHtml = agentHtml.replace("@agentCode",agentPage[i].agentCode);
                                    agentHtml = agentHtml.replace("@agentName",agentPage[i].agentName);
                                    agentHtml = agentHtml.replace("@secret",agentPage[i].secret);
                                    pageDateHtml += agentHtml;
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
    function doEdit(agentId){
        document.location.href = "${baseURL}/manager/admin/agent/agentEdit.jsp?agentId="+agentId;
    }
    function doDel(agentId){
        if(!confirm("您确定要删除该应用？")){
            return;
        }
        $.ajax({
            url: "${baseURL}/manager/admin/delAgentById.action",
            type: "POST",
            data: {"agentId":agentId},
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    getAgentPage(1);
                } else {
                    alert(result.describe);
                }
            },
            error: function() {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function addAgent(){
        document.location.href = "${baseURL}/manager/admin/agent/agentAdd.jsp";
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
</script>
</body>
</html>
