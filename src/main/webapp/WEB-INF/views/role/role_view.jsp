<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <%--
        <link rel="stylesheet" href="static/plugins/ztree/css/demo.css" type="text/css">--%>
    <link rel="stylesheet" href="static/plugins/ztree/css/metroStyle/metroStyle.css" type="text/css">

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
                角色列表
                <%--<small>Control panel</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">角色列表</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">
                                <shiro:hasPermission name="role:create">
                                    <button type="button" data-toggle="modal" data-target="#add_modal"
                                            class="btn btn-block btn-primary">新增
                                    </button>
                                </shiro:hasPermission>
                            </h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="role_tb" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>角色名称</th>
                                    <th>角色描述</th>
                                    <th>是否可用</th>
                                    <th>创建人</th>
                                    <th>创建时间</th>
                                    <th>更新人</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <input id="id_box" type="hidden" value="">
                                <tbody>
                                <c:forEach items="${roles}" var="role">
                                    <tr>
                                        <td>${role.name}</td>
                                        <td>${role.describtion}</td>
                                        <td>${role.available}</td>
                                        <td><fmt:formatDate value="${role.create_time}"
                                                            pattern="yyyy-MM-dd"></fmt:formatDate></td>
                                        <td>${role.create_by}</td>
                                        <td>${role.update_by}</td>
                                        <td><fmt:formatDate value="${role.update_time}"
                                                            pattern="yyyy-MM-dd"></fmt:formatDate></td>
                                        <td>
                                            <shiro:hasPermission name="role:update">
                                                <a href="javascript:void(0)" onclick="buildUpdateModal(this)"
                                                   data-myid="${role.id}" class="btn btn-primary btn-sm">修改</a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="role:grant">
                                                <a href="javascript:void(0)" onclick="grantInitModal(this)"
                                                   data-myid="${role.id}" class="btn btn-warning btn-sm">授权</a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="role:delete">
                                                <a href="#" data-myid="${role.id}" onclick="initDelId(this)"
                                                   data-toggle="modal" data-target="#del_modal"
                                                   class="btn btn-danger btn-sm">删除</a>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>角色名称</th>
                                    <th>角色描述</th>
                                    <th>是否可用</th>
                                    <th>创建人</th>
                                    <th>创建时间</th>
                                    <th>更新人</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </tfoot>
                            </table>
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
                <h4 class="modal-title">新增</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="add_form">
                    <div class="box-body">
                        <div class="form-group">
                            <label>角色名称</label>
                            <input type="text" name="name" class="form-control" placeholder="部门名称（必填）">
                        </div>
                        <div class="form-group">
                            <label>角色描述</label>
                            <input type="text" name="describtion" class="form-control" placeholder="描述（选填）">
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
                            <label>角色名称</label>
                            <input type="text" name="name" class="form-control" placeholder="部门名称（必填）">
                        </div>
                        <div class="form-group">
                            <label>角色描述</label>
                            <input type="text" name="describtion" class="form-control" placeholder="描述（选填）">
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

<!--grant modal-->
<div class="modal fade" id="grant_modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">更新</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="grant_form">
                    <div class="box-body">
                        <div class="form-group">
                            <ul id="treeDemo" class="ztree"></ul>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" id="grant_submit" class="btn btn-primary">提交</button>
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
        $("#role_tb").DataTable();

        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
        /**
         * 新增
         * */
        $("#add_submit").click(function () {
            console.log("submit...");
            $.ajax({
                type: "POST",
                url: "role/role-create",
                data: getFormJson("#add_form"),
                dataType: "json",
                success: function (data) {
                    if (data.meta.success) {
                        //添加成功
                        $("#add_modal").modal('hide');
                        window.location = "role/role-view.html";
                    } else {
                        modalShow("#warn_modal", data.meta.message);
                    }
                },
                error: function (error) {
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
                type: "POST",
                url: "role/" + data.id + "/update",
                data: data,
                dataType: "json",
                success: function (data) {
                    if (data.meta.success) {
                        //添加成功
                        $("#add_modal").modal('hide');
                        window.location = "role/role-view.html";
                    } else {
                        modalShow("#warn_modal", data.meta.message);
                    }
                },
                error: function (error) {
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
                type: "GET",
                url: "role/" + id + "/delete",
                success: function (data) {
                    console.log(data);
                    if (data.meta.success) {
                        //添加成功
                        window.location = "role/role-view.html";
                    } else {
                        modalShow("#warn_modal", data.meta.message);
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            })
        });
        /**
         * 部门添加提交
         */
        function modalShow(id, content) {
            $("#text").html(content);
            $(id).modal('show');
        }

    });

    function buildUpdateModal(obj) {
        var tds = $(obj).parents('tr').find('td');
        //给modal设置值
        var inputs = $("#update_form input");
        console.log(inputs)
        $(inputs[0]).val($(obj).data('myid'));
        for (var i = 1; i < inputs.length - 2; i++) {
            //设置input text
            $(inputs[i]).val(tds[i - 1].innerHTML);
        }
        //设置input radio
        //$(inputs[3]).prop("checked",false);
        //$(inputs[4]).prop("checked",false);
        console.log(tds[2]);
        if (tds[2].innerHTML == 1) {
            //禁用
            console.log("forbidden");
            $(inputs[3]).prop('checked', 'checked');

        } else {
            console.log("opened");
            $(inputs[4]).prop('checked', 'checked');
        }
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
        $("#update_modal").modal('show');
    }


</script>
<SCRIPT type="text/javascript">
    var setting = {
        view: {
            selectedMulti: false
        },
        check: {
            enable: true,
            chkboxType: {"Y": "s", "N": "ps"}
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parent_id"
            }
        },
        edit: {
            enable: true
        }
    };
    /**
     * 授权modal初始化
     */
    var allMenu, menuForRole;
    function grantInitModal(Obj) {
        //获取所有的Menu
        getAllMenu();
        console.log(allMenu);
        //获取对应角色的ID
        var roleId = $(Obj).data('myid');
        $('#id_box').val(roleId);
        console.log(roleId);
        getMenuForRole(roleId);
        console.log(menuForRole);
        allMenu.forEach(function (element, index, array) {
            // element: 指向当前元素的值
            // index: 指向当前索引
            // array: 指向Array对象本身
            element.open = true;
            element.name = element.name+" => 权限字符串 : "+element.permission;
            menuForRole.forEach(function (item, index, array) {
                if (element.id == item.id) {
                    element.checked = true;
                }
            });
        });
        $.fn.zTree.init($("#treeDemo"), setting, allMenu);
        $("#grant_modal").modal('show');
    }
    /**
     * 授权完毕提交
     **/
     $('#grant_submit').click(function(){
         var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
         //获取改变了的节点（这个改变相对于初始化的时候）
         var changedNodes = treeObj.getChangeCheckedNodes();
         var roleId = $('#id_box').val();
         console.log(roleId);
         console.log(changedNodes);
         var data = [];
         changedNodes.forEach(function (element){
            console.log(element.id+":"+element.checked);
            var node = new Object();
            node.id = element.id;
            node.checked = element.checked;
            data.push(node);
         });
         console.log(JSON.stringify(data));
         $.ajax({
             url:"resource/grant/"+roleId,
             type:"POST",
             dataType:"json",
             contentType:"application/json",
             data:JSON.stringify(data),
             success:function(){
                 window.location = "role/role-view.html";
             },
             error:function(){
                 modalShow("#warn_modal", data.meta.message);
             }
         });
     });


    /**
     * ajax获取menu的json
     */
    function getAllMenu() {
        $.ajax({
            url: 'resource/getAllMenu',
            type: 'GET',
            async: false,
            success: function (data) {
                allMenu = data;
            },
            error: function (error) {
                console.log("error");
                console.log(error);
            }
        });
        return allMenu;
    }
    function getMenuForRole(roleId) {
        $.ajax({
            url: 'resource/getMenuForRole?id=' + roleId,
            type: 'GET',
            async: false,
            success: function (data) {
                menuForRole = data;
            },
            error: function (error) {
                console.log("error");
                console.log(error);
            }
        });
        return menuForRole;
    }

    //-->
</SCRIPT>
</body>
</html>
