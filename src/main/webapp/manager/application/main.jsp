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

	<title>管理后台</title>

	<link href="${baseURL}/common/css/bootstrap.css" rel="stylesheet">
	<link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	<link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">


	<script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
	<script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>


	<script src="${baseURL}/common/js/html5shiv.min.js"></script>
	<script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body style="padding:0px;margin: 0px">
<nav class="navbar navbar-default" style="float:left;width:18%;min-height: 42px">
	<ul class="nav nav-tabs nav-stacked" id="menuList">

	</ul>
</nav>
<iframe  name="menuFrame" id="menuFrame" src="" width="81%" height="638px" frameborder="0"
		 style="margin-left:1%;float:left;overflow-x:hidden;overflow-y:auto;" >
</iframe>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>
	var menuItemHtml = "<li><a href=\"javascript:loadMenuPage('@menuUrl')\">@menuName</a></li>"

    $(document).ready(function () {
        loadMenuList();
    });
	function loadMenuList(){
        $.ajax({
            url: "${baseURL}/manager/application/getMenuList.action",
            type: "POST",
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    var menuList = result.data;
                    if(typeof (menuList)!="undefined" && menuList!=null && menuList.length>0){
                        var menuListHtml = "";
                        for(var i=0;i<menuList.length;i++){
                            var menuHtml = menuItemHtml;
                            menuHtml = menuHtml.replace("@menuUrl",menuList[i].menuUrl);
                            menuHtml = menuHtml.replace("@menuName",menuList[i].menuName);
                            menuListHtml +=menuHtml;
                            if(i==0){
                                $("#menuFrame").attr("src","${baseURL}/"+menuList[i].menuUrl);
                            }
                        }
                        $("#menuList").html(menuListHtml);
                    }else{
                        $("#menuList").html("暂无应用，请到系统管理后台添加");
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
    function loadMenuPage(menuSrc){
        $("#menuFrame").attr("src","${baseURL}/"+menuSrc);
    }
</script>
</body>
</html>
