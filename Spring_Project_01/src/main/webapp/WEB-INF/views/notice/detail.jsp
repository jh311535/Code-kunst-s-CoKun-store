<%-- File Location: /views/notice/detail.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>코쿤스토어</title>
    <link rel="stylesheet" href="${root}css/adminlte.min.css">
    <link rel="stylesheet" href="${root}css/all.min.css">
    <link rel="stylesheet" href="${root}css/fontawesome.min.css">
    <link rel="stylesheet" href="${root}css/icheck-bootstrap.min.css">
    <link rel="stylesheet" href="${root}css/tempusdominus-bootstrap-4.min.css">
	<link href="${root}css/top_menu_2.css" rel="stylesheet" type="text/css" />
	<link href="${root}css/bottom.css" rel="stylesheet" type="text/css" />
	<link href="${root}css/notice_detail.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
    <div class="notice-container">
        <div class="notice-title">${notice.notice_title}</div>
        <div class="notice-info">
            작성자: ${adminNickname} | 작성일: ${notice.formattedDate} | 조회수: ${notice.notice_views}
        </div>
        <div class="notice-content">
            <c:if test="${!empty notice.notice_pic}">
                <img src="${root}upload/${notice.notice_pic}" alt="공지 이미지" class="notice-image">
            </c:if>
            <p>${notice.notice_content}</p>
        </div>
        <button type="button" class="btn btn-secondary back-button" onclick="location.href='${root}notice/list'">목록으로</button>
    </div>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
