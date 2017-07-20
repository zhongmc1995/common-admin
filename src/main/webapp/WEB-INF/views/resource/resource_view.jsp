<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <base href="<%=basePath%>">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- css -->
    <jsp:include page="../common/css-resource.jsp"/>
    <link rel="stylesheet" href="static/plugins/datatables/dataTables.bootstrap.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="static/plugins/iCheck/all.css">
    <link rel="stylesheet" href="static/plugins/ztree/css/metroStyle/metroStyle.css" type="text/css">
    <style>
        /*按钮*/
        .icon_div {
            display: inline-block;
            height: 25px;
            width: 35px;
            background: url(http://c.csdnimg.cn/public/common/toolbar/images/f_icon.png) no-repeat 12px -127px;
        }

        .icon_div a {
            display: inline-block;
            width: 27px;
            height: 20px;
            cursor: pointer;
        }

        /*end--按钮*/

        /*ztree表格*/
        .ztree {
            padding: 0;
            border: 2px solid #CDD6D5;
        }

        .ztree li a {
            vertical-align: middle;
            height: 30px;
        }

        .ztree li > a {
            width: 100%;
        }

        .ztree li > a,
        .ztree li a.curSelectedNode {
            padding-top: 0px;
            background: none;
            height: auto;
            border: none;
            cursor: default;
            opacity: 1;
        }

        .ztree li ul {
            padding-left: 0px
        }

        .ztree div.diy span {
            line-height: 30px;
            vertical-align: middle;
        }

        .ztree div.diy {
            height: 100%;
            width: 20%;
            line-height: 30px;
            text-indent: 10px;
            border-top: 1px dotted #ccc;
            border-left: 1px solid #eeeeee;
            display: inline-block;
            box-sizing: border-box;
            color: #3c8dbc;
            font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
            font-size: 12px;
            overflow: hidden;
        }

        .ztree div.diy:first-child {
            text-align: left;
            text-indent: 10px;
            border-left: none;
        }

        .ztree .head {
            background: #3c8dbc;
        }

        .ztree .head div.diy {
            border-top: none;
            text-indent: 10px;
            color: #fff;
            font-family: "Microsoft YaHei";
            font-size: 14px;
        }

        /*end--ztree表格*/
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../common/header.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../common/sidebar.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                系统资源列表
                <%--<small>Control panel</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">系统资源列表</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">
                                系统资源列表
                            </h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="layer">
                                <div id="tableMain">
                                    <ul id="dataTree" class="ztree">

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>

    <jsp:include page="../common/footer.jsp"/>
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<!--add modal-->
<div class="modal fade" id="add_modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增节点</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="add_form">
                    <div class="box-body">
                        <div class="form-group">
                            <label>父级菜单</label>
                            <input type="hidden" name="parent_ids" id="parent_ids">
                            <input type="hidden" name="parent_id" id="parent_id">
                            <input type="text" id="parent_name" name="parent_name" class="form-control" placeholder="父级菜单">
                        </div>
                        <div class="form-group">
                            <label>资源名称</label>
                            <input type="text" name="name" class="form-control" placeholder="资源名称（必填）">
                        </div>
                        <div class="form-group">
                            <label>资源类型</label>
                            <input type="text" name="type" class="form-control" placeholder="menu/button">
                        </div>
                        <div class="form-group">
                            <label>资源地址</label>
                            <input type="text" name="url" class="form-control" placeholder="资源地址（选填）">
                        </div>
                        <div class="form-group">
                            <label>权限字符串</label>
                            <input type="text" name="permission" class="form-control" placeholder="资源地址（选填）">
                        </div>
                        <div class="form-group">
                            <label>是否可用</label>
                            <br>
                            <label>
                                <input type="radio" name="available" value="1" class="flat-red" checked="checked">是
                            </label>
                            <label>
                                <input type="radio" name="available" value="0" class="flat-red">否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" id="add_submit" class="btn btn-primary">提交</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!--update modal-->
<div class="modal fade" id="update_modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">更新</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="update_form">
                    <div class="box-body">
                        <div class="form-group">
                            <label>编号</label>
                            <input type="text" name="id" class="form-control" placeholder="部门编号（必填）" readonly>
                        </div>
                        <div class="form-group">
                            <label>部门名称</label>
                            <input type="text" name="name" class="form-control" placeholder="部门名称（必填）">
                        </div>
                        <div class="form-group">
                            <label>部门描述</label>
                            <input type="text" name="describe" class="form-control" placeholder="描述（选填）">
                        </div>
                        <div class="form-group">
                            <label>是否可用</label>
                            <br>
                            <label>
                                <input type="radio" name="available" value="1" class="flat-red">是
                            </label>
                            <label>
                                <input type="radio" name="available" value="0" class="flat-red">否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" id="update_submit" class="btn btn-primary">提交</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!--del modal-->
<div class="modal modal-danger fade" id="del_modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">警告</h4>
            </div>
            <div class="modal-body">
                你确定要删除这条记录吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" id="del_submit" class="btn btn-outline" data-dismiss="modal">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!--warn modal-->
<div class="modal modal-danger fade" id="warn_modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">警告</h4>
            </div>
            <div class="modal-body" id="text">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline" data-dismiss="modal">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- DataTables -->
<jsp:include page="../common/script-resource.jsp"/>
<script src="static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="static/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="static/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="static/plugins/ztree/js/jquery.ztree.excheck.js"></script>

<script>
    $(function () {
        $("#organization_tb").DataTable();

        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });

        $("#add_submit").click(function () {
            console.log("submit...");
            $.ajax({
                type:"POST",
                url:"resource/resource-create",
                data:getFormJson("#add_form"),
                dataType:"json",
                success:function (data) {
                    if (data.meta.success){
                        //添加成功
                        $("#add_modal").modal('hide');
                        window.location = "resource/resource-view.html";
                    }else{
                        modalShow("#warn_modal",data.meta.message);
                    }
                },
                error:function(error){
                    console.log(error);
                }
            });
        });


        /**
         * 更新
         **/
        $("#update_submit").click(function () {
            var data = getFormJson("#update_form");
            $.ajax({
                type:"POST",
                url:"organization/"+data.id+"/update",
                data:data,
                dataType:"json",
                success:function (data) {
                    if (data.meta.success){
                        //添加成功
                        $("#add_modal").modal('hide');
                        window.location = "organization/organization-view.html";
                    }else{
                        modalShow("#warn_modal",data.meta.message);
                    }
                },
                error:function(error){
                    console.log("出错了");
                    console.log(error.message);
                }
            });
        });

        /**
         * 删除
         */
        $("#del_submit").click(function () {
            console.log("submit..");
            var id = $("#id_box").val();
            console.log(id);
            $.ajax({
                type:"GET",
                url:"resource/"+id+"/delete",
                success:function (data) {
                    console.log(data);
                    if (data.meta.success){
                        //添加成功
                        window.location = "resource/resource-view.html";
                    }else{
                        modalShow("#warn_modal",data.meta.message);
                    }
                },
                error:function(error){
                    console.log(error);
                }
            })
        });
        /**
         * 部门添加提交
         */
        function modalShow(id,content) {
            $("#text").html(content);
            $(id).modal('show');
        }

    });
    function initDelId(obj) {
        var id = $(obj).data("myid");
        $('#id_box').val(id);
    }
    /**
     * 初始化新增modal框
     * @param obj
     */
    function initAddModal(obj) {
        var parentName = $(obj).data("myname");
        var parentId = $(obj).data("myid");
        var parentParentIds = $(obj).data("myparentids")+parentId;
        $("#parent_ids").val(parentParentIds);
        $("#parent_id").val(parentId);
        $("#parent_name").val(parentName);
        console.log(parentName+","+parentId+","+parentParentIds);
        $("#add_modal").modal('show');
    }
</script>
<script>
    var zTreeNodes;
    var setting = {
        view: {
            showLine: false,
            showIcon: false,
            addDiyDom: addDiyDom
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parent_id"
            }
        }
    };
    /**
     * 自定义DOM节点
     */
    function addDiyDom(treeId, treeNode) {
        console.log(treeNode);
        var spaceWidth = 15;
        var liObj = $("#" + treeNode.tId);
        var aObj = $("#" + treeNode.tId + "_a");
        var switchObj = $("#" + treeNode.tId + "_switch");
        var icoObj = $("#" + treeNode.tId + "_ico");
        var spanObj = $("#" + treeNode.tId + "_span");
        aObj.attr('title', '');
        aObj.append('<div class="diy swich"></div>');
        var div = $(liObj).find('div').eq(0);
        switchObj.remove();
        spanObj.remove();
        icoObj.remove();
        div.append(switchObj);
        div.append(spanObj);
        var spaceStr = "<span style='height:1px;display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
        switchObj.before(spaceStr);
        var editStr = '';
        editStr += '<div class="diy">' + ((treeNode.type == '' || treeNode.type == null) ? '&nbsp;' : treeNode.type) + '</div>';
        var corpCat = '<div title="' + treeNode.url + '">' + treeNode.url + '</div>';
        editStr += '<div class="diy">' + ((treeNode.url == '' || treeNode.url == null) ? '&nbsp;' : treeNode.url ) + '</div>';
        editStr += '<div class="diy">' + ((treeNode.permission == '' || treeNode.permission == null) ? '&nbsp;' : treeNode.permission ) + '</div>';
        editStr += '<div class="diy">' + formatHandle(treeNode) + '</div>';
        aObj.append(editStr);
    }
    /**
     * 查询数据
     */
    var allMenu;
    function query() {
        //var data = [{"CONTACT_USER":"张三","CONTACT_PHONE":"18888888888","addFlag":true,"ORG_ID":1,"id":"o1","pId":"onull","open":true,"name":"单位1","modFlag":true,"CORP_CAT":"港口-天然液化气,港口-液化石油气","TYPE":"org","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"部门1","addFlag":true,"CONTACT_PHONE":null,"SECTOR_ID":1,"ORG_ID":1,"id":"s1","pId":"o1","name":"部门1","modFlag":true,"PARENT_ID":null,"CORP_CAT":"港口-天然液化气","TYPE":"sector","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"部门12","addFlag":true,"CONTACT_PHONE":"0","SECTOR_ID":2,"ORG_ID":1,"id":"s2","pId":"s1","name":"部门12","modFlag":true,"PARENT_ID":1,"CORP_CAT":"港口-集装箱","TYPE":"sector","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"部门13","addFlag":true,"CONTACT_PHONE":"0","SECTOR_ID":3,"ORG_ID":1,"id":"s3","pId":"s1","name":"部门13","modFlag":true,"PARENT_ID":1,"CORP_CAT":"港口-集装箱","TYPE":"sector","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"部门24","addFlag":true,"CONTACT_PHONE":"0","SECTOR_ID":4,"ORG_ID":1,"id":"s4","pId":"s2","name":"部门24","modFlag":true,"PARENT_ID":2,"CORP_CAT":"港口-集装箱","TYPE":"sector","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"部门35","addFlag":true,"CONTACT_PHONE":"0","SECTOR_ID":5,"ORG_ID":1,"id":"s5","pId":"s3","name":"部门35","modFlag":true,"PARENT_ID":3,"CORP_CAT":"港口-集装箱","TYPE":"sector","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"部门22","addFlag":true,"CONTACT_PHONE":"0","SECTOR_ID":8,"ORG_ID":1,"id":"s8","pId":"s2","name":"部门22","modFlag":true,"PARENT_ID":2,"CORP_CAT":"-","TYPE":"sector","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"部门23","addFlag":true,"CONTACT_PHONE":"0","SECTOR_ID":9,"ORG_ID":1,"id":"s9","pId":"s2","name":"部门23","modFlag":true,"PARENT_ID":2,"CORP_CAT":"-","TYPE":"sector","delFlag":true},{"CONTACT_USER":"打撒","SECTOR_NAME":"不爱的是分散","addFlag":true,"CONTACT_PHONE":"1231231","SECTOR_ID":21,"ORG_ID":1,"id":"s21","pId":"o1","name":"不爱的是分散","modFlag":true,"PARENT_ID":null,"CORP_CAT":"港口-天然液化气","TYPE":"sector","delFlag":true},{"CONTACT_USER":"111","SECTOR_NAME":"test","addFlag":true,"CONTACT_PHONE":"222","SECTOR_ID":29,"ORG_ID":1,"id":"s29","pId":"s2","name":"test","modFlag":true,"PARENT_ID":2,"CORP_CAT":"港口-集装箱","TYPE":"sector","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"22","addFlag":true,"CONTACT_PHONE":null,"SECTOR_ID":38,"ORG_ID":1,"id":"s38","pId":"o1","name":"22","modFlag":true,"PARENT_ID":null,"CORP_CAT":"-","TYPE":"sector","delFlag":true},{"CONTACT_USER":null,"SECTOR_NAME":"部门9","addFlag":true,"CONTACT_PHONE":null,"SECTOR_ID":61,"ORG_ID":1,"id":"s61","pId":"o1","name":"部门9","modFlag":true,"PARENT_ID":null,"CORP_CAT":"港口-天然液化气","TYPE":"sector","delFlag":true},{"CONTACT_USER":"sara","SECTOR_NAME":"流星雨","addFlag":true,"CONTACT_PHONE":"11111111111","SECTOR_ID":141,"ORG_ID":1,"id":"s141","pId":"s1","name":"流星雨","modFlag":true,"PARENT_ID":1,"CORP_CAT":"-","TYPE":"sector","delFlag":true},{"CONTACT_USER":"流星","SECTOR_NAME":"1级部门","addFlag":true,"CONTACT_PHONE":"11111111111","SECTOR_ID":142,"ORG_ID":1,"id":"s142","pId":"o1","name":"1级部门","modFlag":true,"PARENT_ID":null,"CORP_CAT":"港口-天然液化气","TYPE":"sector","delFlag":true},{"CONTACT_USER":"11","SECTOR_NAME":"11","addFlag":true,"CONTACT_PHONE":"11111111111","SECTOR_ID":145,"ORG_ID":1,"id":"s145","pId":"s1","name":"11","modFlag":true,"PARENT_ID":1,"CORP_CAT":"港口-集装箱","TYPE":"sector","delFlag":true},{"CONTACT_USER":"ff","SECTOR_NAME":"fff","addFlag":true,"CONTACT_PHONE":"11","SECTOR_ID":146,"ORG_ID":1,"id":"s146","pId":"s1","name":"fff","modFlag":true,"PARENT_ID":1,"CORP_CAT":"港口-油码头","TYPE":"sector","delFlag":true},{"CONTACT_USER":"1","SECTOR_NAME":"1","addFlag":true,"CONTACT_PHONE":"1","SECTOR_ID":161,"ORG_ID":1,"id":"s161","pId":"o1","name":"1","modFlag":true,"PARENT_ID":null,"CORP_CAT":"港口-天然液化气","TYPE":"sector","delFlag":true}];
        getAllMenu();
        //初始化列表
        zTreeNodes = allMenu;
        console.log(zTreeNodes);
        //初始化树
        $.fn.zTree.init($("#dataTree"), setting, zTreeNodes);
        //添加表头
        var li_head = ' <li class="head"><a><div class="diy">资源名称</div><div class="diy">资源类型</div><div class="diy">资源地址</div>' +
            '<div class="diy">资源权限字符串</div><div class="diy">操作</div></a></li>';
        var rows = $("#dataTree").find('li');
        if (rows.length > 0) {
            rows.eq(0).before(li_head)
        } else {
            $("#dataTree").append(li_head);
            $("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
        }
    }
    /**
     * ajax获取menu的json
     */
    function getAllMenu() {
        $.ajax({
            url: 'resource/getAllMenu',
            type: 'GET',
            async: false,
            success: function (data) {
                data.forEach(function (element) {
                   element.open = true;
                });
                allMenu = data;
            },
            error: function (error) {
                console.log("error");
                console.log(error);
            }
        });
        return allMenu;
    }
    /**
     * 根据权限展示功能按钮
     * @param treeNode
     * @returns {string}
     */
    function formatHandle(treeNode) {
        var htmlStr = '';
        htmlStr += '<shiro:hasPermission name="resource:update"><button type="button" onclick="buildUpdateModal(this)" data-myid=\"'+treeNode.id+'\" class="btn btn-primary btn-xs">修改</button></shiro:hasPermission>';
        htmlStr += '<shiro:hasPermission name="resource:create"> <button onclick="initAddModal(this)" data-myparentids=\"'+treeNode.parent_ids+'\" data-myname=\"'+treeNode.name+'\" data-myid=\"'+treeNode.id+'\" class="btn btn-info btn-xs">添加</button></shiro:hasPermission>';
        htmlStr += '<shiro:hasPermission name="resource:delete"> <button data-myid=\"'+treeNode.id+'\" onclick="initDelId(this)"data-toggle="modal" data-target="#del_modal"class="btn btn-danger btn-xs">删除</button></shiro:hasPermission>';
        return htmlStr;
    }
    $(function () {
        //初始化数据
        query();
    })
</script>

</body>
</html>
