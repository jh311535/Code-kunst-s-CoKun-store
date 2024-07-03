<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<link href="${root}/resources/css/top_menu.css" rel="stylesheet" type="text/css" />
<style>
    body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center; /* 수평 중앙 정렬 */
        align-items: center; /* 수직 중앙 정렬 */
        height: 100vh;
        margin: 0;
        background-color: #f7f7f7;
    }
    .container {
        width: 400px; /* 컨테이너의 너비를 늘림 */
        text-align: center;
        padding: 20px; /* 패딩을 늘림 */
        border: 1px solid #CCC;
        border-radius: 6px;
        background-color: #fff;
    }
    .form-group {
        margin-bottom: 15px; /* 마진을 늘림 */
        display: flex;
        align-items: center;
    }
    .form-group input {
        flex: 1;
        padding: 10px; /* 패딩을 늘림 */
        box-sizing: border-box;
        font-size: 16px; /* 폰트 크기를 늘림 */
    }
    .btn-submit {
        background-color: #4CAF50;
        color: white;
        padding: 10px; /* 패딩을 늘림 */
        border: 1px solid #4CAF50;
        border-radius: 6px;
        width: 100%;
        cursor: pointer;
        font-size: 16px; /* 폰트 크기를 늘림 */
    }
    .error-message {
        font-size: 14px; /* 폰트 크기를 늘림 */
        color: red;
    }
    h5 {
        font-size: 16px; /* 폰트 크기를 늘림 */
        margin-bottom: 20px; /* 하단 마진을 늘림 */
    }
</style>
</head>
<body>

<div class="container">
    <a href="${root}/main">
        <img src="${root}/image/logo.png" class="text-center" style="width: 128px; height: auto;"/>
    </a>
    <h5>개인정보 보호를 위해 회원님의 비밀번호를 한번 더 확인합니다.</h5>
    <form:form action="${root}/user/check_password_pro?action=${param.action}" method="post" modelAttribute="checkPasswordBean">
        <div class="form-group">
            <form:label path="user_pw"></form:label>
            <form:password path="user_pw" class='form-control' placeholder="비밀번호" />
            <form:errors path="user_pw" cssClass="error-message" />
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-submit">확인</button>
        </div>
    </form:form>
</div>

</body>
</html>
