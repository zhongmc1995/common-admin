<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach items="${menus}" var="menu">
    <c:if test="${menu.type eq 'menu'}"><!-- 判断是否为menu，是menu才继续遍历 -->
        <li class="treeview">
            <a href="${not empty menu.child ? '#' : menu.url}">
                <c:if test="${not empty menu.child}">
                    <i class="fa fa-share"></i>
                    <span>${menu.name}</span>
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </c:if>
                <c:if test="${empty menu.child}">
                    <i class="fa fa-circle-o"></i>${menu.name}
                </c:if>
            </a>

            <c:if test="${not empty menu.child}">
                <ul class="treeview-menu">
                    <c:set var="menus" value="${menu.child}" scope="request" />
                    <jsp:include page="recursive.jsp" />
                </ul>
            </c:if>

        </li>
    </c:if>
</c:forEach>

