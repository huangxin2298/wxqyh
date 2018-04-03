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

    <title>菜单管理</title>

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
            <a id="" href="javascript:addMenu();" onclick="" class="btn orangeBtn smallBtn">新增</a>
        </div>
        <%--<a class="senior_search_btn">高级搜索</a>--%>
        <div class="fr">
            <div class="searchBox">
                <input type="hidden" name="curPage" id="curPage">
                <input placeholder="菜单名" class="inputSearchBox" type="text" name="inputSearch" id="inputSearch"/>
                <input class="submitSearchBtn" type="button" onclick="getMenuList(1)" value=""/>
            </div>
        </div>
    </div>
</form>
<div class="contactTable mt10" id="cc94a9ec68ff4d93a7fd5934c581b647">
    <table class="table table-line table-hover">
        <thead>
        <tr>
            <th width='20'><input type="checkbox" class="form-checkbox" onclick=""></th>
            <th width='200'>菜单名</th>
            <th width='100'>菜单地址</th>
            <th width="350">菜单状态</th>
            <th width="100">排序</th>
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

    var menuTemplate = "<tr>" +
        "                <td><input type=\"checkbox\" class=\"form-checkbox\" value=\"@{id}\" name=\"ids\"></td>" +
        "                <td><span>@menuName</span></td>" +
        "                <td><span>@menuUrl</span></td>" +
        "                <td><span>@status</span></td>" +
        "                <td><span>@showOrder</span></td>" +
        "                <td class=\"lasttd\">" +
        "                    <span class=\"contact_action\">" +
        "                        <a>操作</a>" +
        "                    </span>" +
        "                    <div class=\"lasttdpop\" style=\"display: none\">" +
        "                        <a href=\"javascript:doEdit('@{id}')\">编辑</a>" +
        "                        <a href=\"javascript:updateMenuStatus('@{id}','@menuStatus')\">@oper</a>" +
        "                        <a href=\"javascript:doDel('@{id}')\">删除</a>" +
        "                    </div>" +
        "                </td>" +
        "            </tr>";

    $(document).ready(function () {
        getMenuList(1);
    });

    function getMenuList(curPage) {

        $("#pageData").html("");
        $("#curPage").val(curPage);
        var form = $("#searchForm").serialize();
        $.ajax({
            url: "${baseURL}/manager/admin/getAdminMenuPage.action",
            type: "POST",
            async: false,
            data: form,
            dataType: "json",
            success: function (result) {
                if (result.code == "0") {
                    console.log(result.data);
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
                                $("#lastPage").children("a").attr("href","javascript:getMenuList(\""+(parseInt(pageData.curPage) - 1)+"\")");
                            }
                            if(pageData.curPage >= pageData.totalPages){
                                $("#nextPage").children("a").attr("href","javascript:void(0)");
                            }else{
                                $("#nextPage").children("a").attr("href","javascript:getMenuList(\""+(parseInt(pageData.curPage) + 1)+"\")");
                            }
                            var menuPage = pageData.data;
                            if(typeof(menuPage)!="undefined" && menuPage != null && menuPage.length>0){
                                var pageDateHtml = "";
                                for(var i=0;i<menuPage.length;i++){
                                    var menuHtml = menuTemplate;
                                    menuHtml = menuHtml.replace(/@{id}/g,menuPage[i].id);
                                    menuHtml = menuHtml.replace("@menuName",menuPage[i].menuName);
                                    menuHtml = menuHtml.replace("@menuUrl",menuPage[i].menuUrl);
                                    menuHtml = menuHtml.replace("@showOrder",menuPage[i].showOrder);
                                    var status = "";
                                    if(menuPage[i].status=="0"){
                                        status = "停用";
                                        menuHtml = menuHtml.replace("@menuStatus","1");
                                        menuHtml = menuHtml.replace("@oper","开启");
                                    }else if(menuPage[i].status=="1"){
                                        status = "启用";
                                        menuHtml = menuHtml.replace("@menuStatus","0");
                                        menuHtml = menuHtml.replace("@oper","停用");
                                    }
                                    menuHtml = menuHtml.replace("@status",status);
                                    pageDateHtml += menuHtml;
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
    function doEdit(menuId){
        document.location.href = "${baseURL}/manager/admin/menu/menuEdit.jsp?menuId="+menuId;
    }
    function doDel(menuId){
        if(!confirm("您确定要删除该菜单？")){
            return;
        }
        $.ajax({
            url: "${baseURL}/manager/admin/delMenuById.action",
            type: "POST",
            data: {"menuId":menuId},
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    getMenuList(1);
                } else {
                    alert(result.describe);
                }
            },
            error: function() {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function updateMenuStatus(menuId,status){
        $.ajax({
            url: "${baseURL}/manager/admin/updateMenuStatus.action",
            type: "POST",
            data: {"menuId":menuId,"status":status},
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    alert(result.describe);
                    getMenuList(1);
                } else {
                    alert(result.describe);
                }
            },
            error: function() {
                alert("系统繁忙，请稍后重试");
            }
        })
    }
    function addMenu(){
        document.location.href = "${baseURL}/manager/admin/menu/menuAdd.jsp";
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
