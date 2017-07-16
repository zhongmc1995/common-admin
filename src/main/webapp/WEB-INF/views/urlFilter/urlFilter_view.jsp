<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <title>Common-Admin</title>
    <base href="<%=basePath%>">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- css -->
    <link rel="stylesheet" href="static/plugins/datatables/dataTables.bootstrap.css">
    <jsp:include page="../common/css-resource.jsp"/>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../common/header.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../common/sidebar.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                资源拦截列表
                <%--<small>Control panel</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">资源拦截列表</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">
                                <shiro:hasPermission name="urlFilter:create">
                                    <button type="button" data-toggle="modal" data-target="#add_modal"
                                            class="btn btn-block btn-primary">新增</button>
                                </shiro:hasPermission>
                            </h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="user_tb" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>name</th>
                                    <th>url</th>
                                    <th>roles</th>
                                    <th>permissions</th>
                                    <th>create_time</th>
                                    <th>create_by</th>
                                    <th>update_time</th>
                                    <th>update_by</th>
                                    <th>operation</th>
                                </tr>
                                </thead>
                                <input id="id_box" type="hidden" value="">
                                <tbody>
                                <c:forEach items="${urlFilters}" var="urlFilter">
                                    <tr>
                                        <td>${urlFilter.name}</td>
                                        <td>${urlFilter.url}</td>
                                        <td>${urlFilter.roles}</td>
                                        <td>${urlFilter.permissions}</td>
                                        <td><fmt:formatDate value="${urlFilter.create_time}" pattern="yyyy-MM-dd" /></td>
                                        <td>${urlFilter.create_by}</td>
                                        <td>${urlFilter.update_by}</td>
                                        <td><fmt:formatDate value="${urlFilter.update_time}" pattern="yyyy-MM-dd" /></td>
                                        <td>
                                            <shiro:hasPermission name="urlFilter:update">
                                                <a href="javascript:void(0)" onclick="updateUrlFilter(this)" data-myid="${urlFilter.id}" class="btn btn-primary btn-sm">修改</a>
                                            </shiro:hasPermission>

                                            <shiro:hasPermission name="urlFilter:delete">
                                                <a href="#" data-myid="${urlFilter.id}" onclick="initDelId(this)"
                                                   data-toggle="modal" data-target="#del_modal" class="btn btn-danger btn-sm">删除</a>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>

                                <tfoot>
                                <tr>
                                <tr>
                                    <th>name</th>
                                    <th>url</th>
                                    <th>roles</th>
                                    <th>permissions</th>
                                    <th>create_time</th>
                                    <th>create_by</th>
                                    <th>update_time</th>
                                    <th>update_by</th>
                                    <th>operation</th>
                                </tr>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../common/footer.jsp" />

    <div class="control-sidebar-bg"></div>
</div>

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
                            <label>资源名称</label>
                            <input type="text" name="name" class="form-control" placeholder="资源名称">
                        </div>
                        <div class="form-group">
                            <label>资源Url</label>
                            <input type="text" name="url" class="form-control" placeholder="资源url">
                        </div>
                        <div class="form-group">
                            <label>资源角色</label>
                            <input type="text" name="roles" class="form-control" placeholder="资源所有要的角色，多个用英文逗号隔开">
                        </div>
                        <div class="form-group">
                            <label>资源权限</label>
                            <input type="text" name="permissions" class="form-control" placeholder="资源对应权限字符串">
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
                <h4 class="modal-title">修改</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="update_form">
                    <div class="box-body">
                        <div class="form-group">
                            <label>编号</label>
                            <input type="text" name="id" class="form-control" placeholder="ID" readonly>
                        </div>
                        <div class="form-group">
                            <label>资源名称</label>
                            <input type="text" name="name" class="form-control" placeholder="资源名称">
                        </div>
                        <div class="form-group">
                            <label>资源Url</label>
                            <input type="text" name="url" class="form-control" placeholder="资源url">
                        </div>
                        <div class="form-group">
                            <label>资源角色</label>
                            <input type="text" name="roles" class="form-control" placeholder="资源所有要的角色，多个用英文逗号隔开">
                        </div>
                        <div class="form-group">
                            <label>资源权限</label>
                            <input type="text" name="permissions" class="form-control" placeholder="资源对应权限字符串">
                        </div>
                        <div class="form-group">
                            <label>创建时间</label>
                            <input type="text" name="create_time" class="form-control" placeholder="创建时间" readonly>
                        </div>
                        <div class="form-group">
                            <label>创建人</label>
                            <input type="text" name="create_by" class="form-control" placeholder="创建人" readonly>
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
<!-- ./wrapper -->

<!-- DataTables -->
<jsp:include page="../common/script-resource.jsp" />
<script src="static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        $("#user_tb").DataTable();
        /**
         * urlFilter添加
         */
        $("#add_submit").click(function () {
            $.ajax({
                type:"POST",
                url:"urlFilter/urlFilter-create",
                data:getFormJson("#add_form"),
                dataType:"json",
                success:function (data) {
                    if (data.meta.success){
                        //添加成功
                        $("#add_modal").modal('hide');
                        window.location = "urlFilter/urlFilter-view.html";
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
         * 删除
         */
        $("#del_submit").click(function () {
            var id = $("#id_box").val();
            console.log(id);
            $.ajax({
                type:"GET",
                url:"urlFilter/"+id+"/delete",
                success:function (data) {
                    if (data.meta.success){
                        //添加成功
                        window.location = "urlFilter/urlFilter-view.html";
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
         * 更新
         */
        /*$("#update_a").click(function () {
            alert("hello");
           var tds = $(this).parents('tr').find('td');
           console.log(tds);
        });*/
        $("#update_submit").click(function () {
            var data = getFormJson("#update_form");
            $.ajax({
                type:"POST",
                url:"urlFilter/"+data.id+"/update",
                data:data,
                dataType:"json",
                success:function (data) {
                    if (data.meta.success){
                        //添加成功
                        $("#add_modal").modal('hide');
                        window.location = "urlFilter/urlFilter-view.html";
                    }else{
                        modalShow("#warn_modal",data.meta.message);
                    }
                },
                error:function(error){
                    console.log(error);
                }
            });
        });
        
    });

    function updateUrlFilter(obj) {
        var tds = $(obj).parents('tr').find('td');
        console.log(tds);
        //给modal设置值
        var inputs = $("#update_form input");
        $(inputs[0]).val($(obj).data('myid'));
        console.log(inputs);
        for (var i=1;i<inputs.length;i++){
            $(inputs[i]).val(tds[i-1].innerHTML);
        }
        $("#update_modal").modal('show');
    }
    

    function modalShow(id,content) {
        $("#text").html(content);
        $(id).modal('show');
    }
</script>

</body>
</html>
