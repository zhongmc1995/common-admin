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
                系统日志
                <%--<small>Control panel</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">系统日志</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">
                            </h3>
                        </div>
                        <!-- /.box-header ddd-->
                        <div class="box-body">
                            <table id="user_tb" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>类型</th>
                                    <th>详情</th>
                                    <th>描述</th>
                                    <th>时间</th>
                                    <th>操作人</th>
                                    <th>删除</th>
                                </tr>
                                </thead>
                                <input id="id_box" type="hidden" value="">
                                <tbody>
                                <c:forEach items="${logs}" var="log">
                                    <tr>
                                        <td>${log.type}</td>
                                        <td>${log.content}</td>
                                        <td>${log.operation}</td>
                                        <td><fmt:formatDate value="${log.create_time}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
                                        <td>${log.create_by}</td>
                                        <td>
                                            <shiro:hasPermission name="log:delete">
                                                <a href="#" data-myid="${log.id}" onclick="initDelId(this)"
                                                   data-toggle="modal" data-target="#del_modal" class="btn btn-danger btn-sm">删除</a>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>

                                <tfoot>
                                <tr>
                                    <th>类型</th>
                                    <th>详情</th>
                                    <th>描述</th>
                                    <th>时间</th>
                                    <th>操作人</th>
                                    <th>删除</th>
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

<!-- DataTables -->
<jsp:include page="../common/script-resource.jsp" />
<script src="static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        $("#user_tb").DataTable();

        /**
         * 删除
         */
        $("#del_submit").click(function () {
            var id = $("#id_box").val();
            console.log(id);
            $.ajax({
                type:"GET",
                url:"log/"+id+"/delete",
                success:function (data) {
                    console.log(data);
                    if (data.meta.success){
                        //添加成功
                        window.location = "log/log-view.html";
                    }else{
                        modalShow("#warn_modal",data.meta.message);
                    }
                },
                error:function(error){
                    console.log(error);
                }
            })
        });

    });


</script>

</body>
</html>
