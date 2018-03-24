<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/context.jsp" %>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<%--<link rel="icon" href="${baseURL}/common/images/favicon.ico">--%>

	<title>部门详情</title>

	<link href="${baseURL}/common/css/bootstrap.css" rel="stylesheet">
	<link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	<link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">

	<script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
	<script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>
<form action="" method="post" id="searchForm" onsubmit="return false;">
	<div class="topMainBar clearfix">
		<div class="fl flMainBar" style="margin-top: 6px;">
			<a id="sync" href="javascript:sync()" class="btn orangeBtn smallBtn">同步</a>
			<span class="borderline"></span>
			<a id="" href="javascript:void(0);" onclick="doAdd();return true;" class="btn orangeBtn smallBtn">新增</a>
		</div>
		<a class="senior_search_btn">高级搜索</a>
		<div class="fr">
			<div class="searchBox">
				<input placeholder="姓名\手机号" class="inputSearchBox" type="text" name="searchValue.personName" id="personName" onkeydown="keyDown13();" />
				<input class="submitSearchBtn" name="input" type="button" onclick="func_57a3d3ae613b4778a90b4fe725e883ba(1);  _redraw('cc94a9ec68ff4d93a7fd5934c581b647');  _redraw('af612ff2d20641c6b2a6d920f1bd8fc2');loadPageList();"  value=""/>
			</div>
		</div>
	</div>
</form>
<div class="contactTable mt10" id="cc94a9ec68ff4d93a7fd5934c581b647">
<table class="table table-line table-hover">
	<thead>
	<tr templateId="default" dqdpCheckPoint="list_title">
		<th width='20'><input type="checkbox" class="form-checkbox" onclick="_doCheck(this,'ids')"></th>
		<th width='100'>姓名</th>
		<th>账号</th>
		<th width="150">手机号</th>
		<th>职位</th>
		<th>状态</th>
		<th width="100">操作</th>
	</tr>
	</thead>
	<tbody>
	<tr name="pageData">
		<td><input type="checkbox" class="form-checkbox" value="@{id}" name="ids"></td>
		<td><span>@name</span></td>
		<td><span>@account</span></td>
		<td><span>@mobile</span></td>
		<td><span>@postion</span></td>
		<td><span>@status</span></td>
		<td class="lasttd">
			<span  class="contact_action">
				<a>操作</a>
			</span>
			<div class="lasttdpop" style="display: none">
				<a href="javascript:void(0)">详情</a>
				<a href="javascript:void(0)">删除</a>
			</div>
		</td>
	</tr>
	</tbody>
</table>
</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>
    $('.lasttd').on({
        mouseover:function(){
            if(navigator.userAgent.toUpperCase().indexOf("MSIE")>-1){
                $(this).find('.lasttdpop').css({'display':'block'});$(this).children('.contact_action').addClass('after');
            }else if(navigator.userAgent.toUpperCase().indexOf("SAFARI")>-1){
                $(this).find('.lasttdpop').css({'display':'-webkit-flex'});$(this).children('.contact_action').addClass('after');
            }
            else{
                $(this).find('.lasttdpop').css({'display':'flex'});$(this).children('.contact_action').addClass('after');}},
        mouseout:function(){$(this).find('.lasttdpop').hide();$(this).children('.contact_action').attr('class','contact_action');}
    });
    //同步微信的数据到服务器
    function sync(){
        if(!confirm("您确定要从微信端同步数据到服务器?")){
			return;
		}
        $.ajax({
            url: "${baseURL}/addressbook/syncDeptAndUser.action",
            type: "GET",
            success: function(result) {
				if(result.code == "0"){
                    parent.parent.showMsg(result.describe);
                } else {
                    parent.parent.showMsg(result.describe);
                }
            },
            error: function() {
                parent.parent.showMsg("","系统繁忙，请稍后重试");
            }
        })
	}
</script>
</body>
</html>
