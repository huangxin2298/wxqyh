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
    <link rel="stylesheet" href="${baseURL}/manager/zTree/css/demo.css" type="text/css">
    <link rel="stylesheet" href="${baseURL}/manager/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${baseURL}/manager/addressbook/css/ztree.css" type="text/css">
    <link href="${baseURL}/themes/manager/common/css/common.css" rel="stylesheet">

    <script src="${baseURL}/common/assets/js/ie8-responsive-file-warning.js"></script>
    <script src="${baseURL}/common/assets/js/ie-emulation-modes-warning.js"></script>

    <script src="${baseURL}/common/js/html5shiv.min.js"></script>
    <script src="${baseURL}/common/js/respond.min.js"></script>
</head>

<body>
<div>
    <div class="zTreeDemoBackground left" style="float:left;">
        <ul id="ztree" class="ztree"></ul>
    </div>
    <div style="width:77%;height:100%;float:left">
        <iframe style="margin-top: 20px" name="depFrame" id="depFrame" src="${baseURL}/manager/addressbook/detail.jsp" width="100%" height="96%" frameborder="0" ></iframe>
    </div>
</div>
<script src="${baseURL}/common/js/jquery.min.js"></script>
<script src="${baseURL}/common/assets/js/ie10-viewport-bug-workaround.js"></script>
<script type="text/javascript" src="${baseURL}/manager/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${baseURL}/manager/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${baseURL}/manager/zTree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${baseURL}/manager/zTree/js/jquery.ztree.exhide.js"></script>
<script>
    var zNodes,curMenu = null, zTree_Menu = null,IDMark_A = "_a",timer;
    var setting = {
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            addDiyDom: addDiyDom,
            showLine: false,
            showIcon: false,
            selectedMulti: false,
            dblClickExpand: false,
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick : beforeClick,
            onClick : zTreeOnClick,
        }
    };

    function addHoverDom(treeId, treeNode) {
        $(".diyBtn").unbind().remove();
        var obj = $("#" + treeNode.tId+IDMark_A);
        if ($("#diyBtn1_"+treeNode.id).length>0) return;
        if ($("#diyBtn2_"+treeNode.id).length>0) return;
        var editStr = "";
        if(treeNode.tId == "ztree_1"){
            editStr = "<span class=\"diyBtn\" style='margin:0 0 0 5px' onmouseover='keepDivBtn()'><a id='diyBtn1_" +treeNode.id+ "' onclick='addDept(\""+treeNode.id+"\")' style='margin:0'>新增</a></span>";
        }else{
            editStr = "<span class=\"diyBtn\"  style='margin:0 0 0 5px;' onmouseover='keepDivBtn()'><a id='diyBtn1_" +treeNode.id+ "' onclick='addDept(\""+treeNode.id+"\")' style='margin:0'>新增</a>" +
                "<a id='diyBtn2_" +treeNode.id+ "' onclick='editDept(\""+treeNode.id+"\")' style='margin:0'>编辑</a>" +
                "<a id='diyBtn3_" +treeNode.id+ "' onclick='delDept(\""+treeNode.id+"\")' style='margin:0'>删除</a></span>";
        }
        obj.after(editStr);
    }
    function removeHoverDom(treeId, treeNode) {
        timer = setTimeout(function(){
            $(".diyBtn").unbind().remove();
            /*$("#diyBtn1_"+treeNode.id).unbind().remove();
            $("#diyBtn2_"+treeNode.id).unbind().remove();
            $("#diyBtn3_"+treeNode.id).unbind().remove();*/
        },1000);
    }
    function keepDivBtn(){
        clearInterval(timer);
    }
    function hideDivBtn(){
        $(".diyBtn").unbind().remove();
    }


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
            var zTree = $.fn.zTree.getZTreeObj("ztree");
            zTree.expandNode(treeNode);
            return true;
        }
        return true;
    }

    function zTreeOnClick(event, treeId, treeNode) {
        loadDeptUser(treeNode.id)
    }

    $(document).ready(function(){
        getDeptList();
        window.setTimeout(function(){
            var treeObj = $("#ztree");
            $.fn.zTree.init(treeObj, setting, zNodes);
            zTree_Menu = $.fn.zTree.getZTreeObj("ztree");
            curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
            zTree_Menu.selectNode(curMenu);

            treeObj.hover(function () {
                if (!treeObj.hasClass("showIcon")) {
                    treeObj.addClass("showIcon");
                }
            }, function() {
                treeObj.removeClass("showIcon");
            });
            hideDivBtn();
        },500);
    });
    function getDeptList(){
        $.ajax({
            url: "${baseURL}/manager/addressbook/getDepartmentList.action",
            type: "GET",
            async: false,
            dataType: "json",
            success: function(result) {
                if(result.code == "0"){
                    var deptList = result.data;
                    if(typeof (deptList)!="undefined" && deptList != null && deptList.length>0){
                        var znodeStr = "[";
                        for(var i=0;i<deptList.length;i++){
                            znodeStr += "{ \"id\":\""+deptList[i].id+"\",\"pId\":\""+deptList[i].parentDepart+"\",\"name\":\""+deptList[i].departmentName+"\",\"open\":true},";
                        }
                        znodeStr = znodeStr.substring(0,znodeStr.length-1);
                        znodeStr += "]";
                        console.log(znodeStr)
                        zNodes = $.parseJSON(znodeStr);
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
    function loadDeptUser(deptId){
        $("#depFrame").attr("src","${baseURL}/manager/addressbook/detail.jsp?deptId="+deptId);
    }
    function addDept(deptId){
        hideDivBtn()
        $("#depFrame").attr("src","${baseURL}/manager/addressbook/dept/deptAdd.jsp?deptId="+deptId);
    }
    function editDept(deptId){
        hideDivBtn()
        $("#depFrame").attr("src","${baseURL}/manager/addressbook/dept/deptEdit.jsp?deptId="+deptId);
    }
    function delDept(deptId){
        hideDivBtn();
        if(confirm("您确定要删除该部门?")){
            $.ajax({
                url: "${baseURL}/manager/addressbook/delDepartmentById.action",
                type: "get",
                data: {"deptId":deptId},
                async: false,
                dataType: "json",
                success: function(result) {
                    if(result.code == "0"){
                        alert(result.describe);
                        parent.location.reload();
                    } else {
                        alert(result.describe);
                    }
                },
                error: function() {
                    alert("系统繁忙，请稍后重试");
                }
            })
        }
    }
</script>
</body>
</html>
