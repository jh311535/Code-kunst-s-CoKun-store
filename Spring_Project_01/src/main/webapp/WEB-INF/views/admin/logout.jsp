<%-- File Location: /views/admin/logout.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>코쿤스토어</title>
    <script>
        alert("로그아웃 되었습니다.");
        window.location.href = "${root}admin/login";
    </script>
</head>
<body>
</body>
</html>
