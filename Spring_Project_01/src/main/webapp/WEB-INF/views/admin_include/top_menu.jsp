<%-- File Location: /views/admin_include/top_menu.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="${root}" class="nav-link">유저 홈페이지</a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="${root}admin/home" class="nav-link">관리자 홈페이지</a>
        </li>
    </ul>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <c:choose>
                <c:when test="${sessionScope.admin == null}">
                    <a class="nav-link" href="${root}admin/login">
                        <i class="fas fa-sign-in-alt"></i> 로그인
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="nav-link" href="${root}admin/logout">
                        <i class="fas fa-sign-out-alt"></i> 로그아웃
                    </a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</nav>
