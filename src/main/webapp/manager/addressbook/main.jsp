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

    <title>管理后台</title>

    <link href="${baseURL}/common/css/bootstrap.css" rel="stylesheet">
    <link href="${baseURL}/common/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link rel="stylesheet" href="${baseURL}/manager/zTree/css/demo.css" type="text/css">
    <link rel="stylesheet" href="${baseURL}/manager/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${baseURL}/manager/addressbook/css/ztree.css" type="text/css">
    <link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">

    <script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
    <script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>
<div>
    <div class="zTreeDemoBackground left" style="float:left;">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
    <div style="width:77%;height:100%;float:left">
        <iframe style="margin-top: 20px" name="depFrame" id="depFrame" src="${baseURL}/manager/addressbook/detail.jsp" width="100%" height="96%" frameborder="0" ></iframe>
    </div>
</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script type="text/javascript" src="${baseURL}/manager/zTree/js/jquery.ztree.core.js"></script>
<script>
    var curMenu = null, zTree_Menu = null;
    var setting = {
        view: {
            showLine: false,
            showIcon: false,
            selectedMulti: false,
            dblClickExpand: false,
            addDiyDom: addDiyDom
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick
        }
    };

    var zNodes =[
        { id:1, pId:0, name:"文件夹", open:true},
        { id:11, pId:1, name:"收件箱"},
        { id:111, pId:11, name:"收件箱1"},
        { id:112, pId:111, name:"收件箱2"},
        { id:113, pId:112, name:"收件箱3"},
        { id:114, pId:113, name:"收件箱4"},
        { id:12, pId:1, name:"垃圾邮件"},
        { id:13, pId:1, name:"草稿"},
        { id:14, pId:1, name:"已发送邮件"},
        { id:15, pId:1, name:"已删除邮件"},
        { id:3, pId:0, name:"快速视图"},
        { id:31, pId:3, name:"文档"},
        { id:32, pId:3, name:"照片"}
    ];

    function addDiyDom(treeId, treeNode) {
        var spaceWidth = 5;
        var switchObj = $("#" + treeNode.tId + "_switch"),
            icoObj = $("#" + treeNode.tId + "_ico");
        switchObj.remove();
        icoObj.before(switchObj);

        if (treeNode.level > 1) {
            var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
            switchObj.before(spaceStr);
        }
    }

    function beforeClick(treeId, treeNode) {
        if (treeNode.level == 0 ) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.expandNode(treeNode);
            return false;
        }
        return true;
    }

    $(document).ready(function(){
        var treeObj = $("#treeDemo");
        $.fn.zTree.init(treeObj, setting, zNodes);
        zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
        curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
        zTree_Menu.selectNode(curMenu);

        treeObj.hover(function () {
            if (!treeObj.hasClass("showIcon")) {
                treeObj.addClass("showIcon");
            }
        }, function() {
            treeObj.removeClass("showIcon");
        });
    });

</script>
</body>
</html>
