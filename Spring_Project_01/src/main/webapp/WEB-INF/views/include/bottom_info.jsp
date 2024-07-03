<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!-- bottom.css -->

<!-- 하단 정보 부분 -->
<div class="bottom-container-fluid">
    <div class="bottom-container">
        <h3 class="h3-tag">코쿤스토어</h3>
        <p>http://www.cokunStore.co.kr</p>
        <p>대표 이도형</p>
        <p>서울 종로구 종로12길 15 코아빌딩1</p>
        <p>사업자번호 : 000-111-2222</p>

        <!-- 네비게이션 메뉴 -->
        <div class="separator"></div>
        <div class="admin-menu">
            <a href="${root}admin/home">관리자 페이지</a>
            <c:choose>
                <c:when test="${sessionScope.admin == null}">
                    <a href="${root}admin/login">관리자 로그인</a>
                </c:when>
                <c:otherwise>
                    <a href="${root}admin/logout">관리자 로그아웃</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
