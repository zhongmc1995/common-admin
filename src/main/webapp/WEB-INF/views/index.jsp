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
    <jsp:include page="common/css-resource.jsp" />


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="common/header.jsp" />
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="common/sidebar.jsp" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div style="text-align: center; vertical-align: middle">
                <h1>Common Admin</h1>
            </div>
        </section>

        <!-- Main content -->
        <section class="content">
        </section>
        <!-- /.content -->
    </div>


    <jsp:include page="common/footer.jsp" />

    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<jsp:include page="common/script-resource.jsp" />
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="static/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="static/plugins/knob/jquery.knob.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="static/plugins/daterangepicker/daterangepicker.js"></script>
<script src="static/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Morris.js charts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="static/plugins/morris/morris.min.js"></script><!-- Morris.js charts -->
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="static/dist/js/pages/dashboard.js"></script>
</body>
</html>
