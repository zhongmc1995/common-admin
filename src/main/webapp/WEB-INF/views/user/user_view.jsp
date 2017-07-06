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
    <title>Common-Admin</title>
    <base href="<%=basePath%>">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- css -->
    <link rel="stylesheet" href="static/plugins/datatables/dataTables.bootstrap.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="static/plugins/select2/select2.min.css">
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
                用户列表
                <%--<small>Control panel</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">用户列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">
                                <shiro:hasPermission name="sysuser:create">
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
                                    <th>用户名</th>
                                    <th>所属组织</th>
                                    <th>角色列表</th>
                                    <th>创建时间</th>
                                    <th>创建人人</th>
                                    <th>更新时间</th>
                                    <th>更新人</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.username}</td>
                                        <td>${user.organization.name}</td>
                                        <td>${user.rolesToStr}</td>
                                        <td>${user.create_time}</td>
                                        <td>${user.create_by}</td>
                                        <td>${user.update_time}</td>
                                        <td>${user.update_by}</td>
                                        <td>
                                            <shiro:hasPermission name="sysuser:update">
                                                <a href="${pageContext.request.contextPath}/sysuser/${user.id}/update" class="btn btn-primary btn-sm">修改</a>
                                            </shiro:hasPermission>

                                            <shiro:hasPermission name="sysuser:delete">
                                                <a href="${pageContext.request.contextPath}/sysuser/${user.id}/delete" class="btn btn-danger btn-sm">删除</a>
                                            </shiro:hasPermission>

                                            <shiro:hasPermission name="sysuser:update">
                                                <a href="${pageContext.request.contextPath}/sysuser/${user.id}/changePassword" class="btn btn-warning btn-sm">改密</a>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>

                                <tfoot>
                                <tr>
                                    <th>用户名</th>
                                    <th>所属组织</th>
                                    <th>角色列表</th>
                                    <th>创建时间</th>
                                    <th>创建人人</th>
                                    <th>更新时间</th>
                                    <th>更新人</th>
                                    <th>操作</th>
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
                            <label>用户名</label>
                            <input type="text" name="username" class="form-control" placeholder="用户名（必填）">
                        </div>
                        <div class="form-group">
                            <label>初始密码</label>
                            <input type="text" name="password" class="form-control" placeholder="初始密码（必填）">
                        </div>
                        <div class="form-group">
                            <label>邮箱</label>
                            <input type="text" name="email" class="form-control" placeholder="邮箱（选填）">
                        </div>
                        <div class="form-group">
                            <label>联系电话</label>
                            <input type="text" name="phone" class="form-control" placeholder="联系电话（选填）">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <select name="roles" class="form-control select2" multiple="multiple" data-placeholder="角色（选填）" style="width: 100%;">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>所属部门</label>
                            <select class="form-control select2" data-placeholder="所属部门（选填）" style="width: 100%;">
                                <c:forEach items="${organizations}" var="o">
                                    <option value="${o.id}">${o.name}</option>
                                </c:forEach>
                            </select>
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
<jsp:include page="../common/script-resource.jsp" />
<script src="static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- Select2 -->
<script src="static/plugins/select2/select2.full.min.js"></script>
<script>
    $(function () {
        $("#user_tb").DataTable();
    });
    $(".select2").select2();
    /**
     * 系统用户添加提交
     */
    $("#add_submit").click(function () {
        console.log("submit...")
        $.ajax({
            type:"POST",
            url:"sysuser/sysuser-create",
            data:getFormJson("#add_form"),
            dataType:"json",
            success:function (data) {
                if (data.meta.success){
                    //添加成功
                    $("#add_modal").modal('hide');
                    window.location = "sysuser/sysuser-view.html";
                }else{
                    modalShow("#warn_modal",data.meta.message);
                }
            },
            error:function(error){
                console.log(error);
            }
        });
    });

    function modalShow(id,content) {
        $("#text").html(content);
        $(id).modal('show');
    }
</script>

</body>
</html>
