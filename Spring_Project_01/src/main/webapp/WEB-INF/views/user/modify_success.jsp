<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코쿤스토어</title>
<script type="text/javascript">
    alert('정보 수정이 완료되었습니다.');
    window.location.href = '${root}/user/mypage'; // 정보확인 리다이렉트
</script>
</head>
<body>
</body>
</html>
