<%--
  Created by IntelliJ IDEA.
  User: zhongmc
  Date: 2017/6/29
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <title>index</title>
</head>
<body>
    欢迎
</body>
</html>