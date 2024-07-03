<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<script>
    window.onload = function() {
        alert("회원님의 아이디는: ${user_id}"+" 입니다.");
        window.location.href = "${pageContext.request.contextPath}/user/login";
    }
</script>
</head>
<body>
</body>
</html>
