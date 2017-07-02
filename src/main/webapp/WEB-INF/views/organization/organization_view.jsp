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
                            <h3 class="box-title">组织列表</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="organization_tb" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>name</th>
                                    <th>parent_id</th>
                                    <th>parent_ids</th>
                                    <th>available</th>
                                    <th>create_time</th>
                                    <th>update_time</th>
                                    <th>create_by</th>
                                    <th>update_by</th>
                                    <th>operation</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${organizations}" var="organization">
                                    <tr>
                                        <td>${organization.name}</td>
                                        <td>${organization.parent_id}</td>
                                        <td>${organization.parent_ids}</td>
                                        <td>${organization.available}</td>
                                        <td>${organization.create_time}</td>
                                        <td>${organization.update_time}</td>
                                        <td>${organization.create_by}</td>
                                        <td>${organization.update_by}</td>
                                        <td>
                                            <shiro:hasPermission name="organization:update">
                                                <a href="${pageContext.request.contextPath}/sysuser/${user.id}/update" class="btn btn-primary btn-sm">修改</a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="organization:delete">
                                                <a href="${pageContext.request.contextPath}/sysuser/${user.id}/delete" class="btn btn-danger btn-sm">删除</a>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>name</th>
                                    <th>parent_id</th>
                                    <th>parent_ids</th>
                                    <th>available</th>
                                    <th>create_time</th>
                                    <th>update_time</th>
                                    <th>create_by</th>
                                    <th>update_by</th>
                                    <th>operation</th>
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
<!-- DataTables -->
<jsp:include page="../common/script-resource.jsp"/>
<script src="static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        $("#organization_tb").DataTable();
    });
</script>
</body>
</html>
