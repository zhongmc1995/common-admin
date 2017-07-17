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
                组织列表
                <%--<small>Control panel</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">组织列表</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">
                                <shiro:hasPermission name="organization:create">
                                    <button type="button" data-toggle="modal" data-target="#add_modal"
                                            class="btn btn-block btn-primary">新增</button>
                                </shiro:hasPermission>
                            </h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="organization_tb" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>部门名称</th>
                                    <th>描述</th>
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
                                <c:forEach items="${organizations}" var="organization">
                                    <tr>
                                        <td>${organization.name}</td>
                                        <td>${organization.describe}</td>
                                        <td>${organization.available}</td>
                                        <td>${organization.create_time}</td>
                                        <td>${organization.create_by}</td>
                                        <td>${organization.update_by}</td>
                                        <td>${organization.update_time}</td>
                                        <td>
                                            <shiro:hasPermission name="organization:update">
                                                <a href="javascript:void(0)" onclick="buildUpdateModal(this)" data-myid="${organization.id}" class="btn btn-primary btn-sm">修改</a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="organization:delete">
                                                <a href="#" data-myid="${organization.id}" onclick="initDelId(this)"
                                                   data-toggle="modal" data-target="#del_modal" class="btn btn-danger btn-sm">删除</a>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>部门名称</th>
                                    <th>描述</th>
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
                url:"organization/organization-create",
                data:getFormJson("#add_form"),
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
                url:"organization/"+id+"/delete",
                success:function (data) {
                    console.log(data);
                    if (data.meta.success){
                        //添加成功
                        window.location = "organization/organization-view.html";
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

    function buildUpdateModal(obj) {
        var tds = $(obj).parents('tr').find('td');
        //给modal设置值
        var inputs = $("#update_form input");
        console.log(inputs)
        $(inputs[0]).val($(obj).data('myid'));
        for (var i=1;i<inputs.length-2;i++){
            //设置input text
            $(inputs[i]).val(tds[i-1].innerHTML);
        }
        //设置input radio
        //$(inputs[3]).prop("checked",false);
        //$(inputs[4]).prop("checked",false);
        console.log(tds[2]);
        if (tds[2].innerHTML == 1){
            //禁用
            console.log("forbidden");
            $(inputs[3]).prop('checked','checked');

        }else {
            console.log("opened");
            $(inputs[4]).prop('checked','checked');
        }
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
        $("#update_modal").modal('show');
    }
</script>
</body>
</html>
