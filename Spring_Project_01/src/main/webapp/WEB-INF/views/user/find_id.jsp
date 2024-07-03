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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<style>
    body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        background-color: #f7f7f7;
    }
    .logo {
        margin-bottom: 20px;
        padding: 10px;
    }
    .container {
        width: 300px;
        text-align: center;
        padding: 15px;
        border: 1px solid #CCC;
        border-radius: 6px;
        background-color: #fff;
    }
    .form-group {
        margin-bottom: 10px;
        position: relative;
    }
    .form-group input {
        width: 100%;
        padding: 10px;
        box-sizing: border-box;
        padding-left: 35px;
    }
    .form-group .fas {
        position: absolute;
        left: 10px;
        top: 50%;
        transform: translateY(-50%);
        color: #999;
    }
    .btn-submit {
        background-color: #4CAF50;
        color: white;
        padding: 8px;
        border: 1px solid #4CAF50;
        border-radius: 6px;
        width: 100%;
        cursor: pointer;
    }
    .login-link, .find-link {
        margin-top: 10px;
        font-size: 14px;
    }
    .find-link a:first-child {
        margin-right: 25px;
    }
    .error-message {
        font-size: 12px;
        color: red;
    }
</style>
<link href="${root}/resources/css/top_menu.css" rel="stylesheet" type="text/css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <a href="${root}/main" class="logo">
        <img src="${root}/image/logo.png" class="text-center" style="width: 128px; height: auto;"/>
    </a>
    <h2 class="text-center">아이디 찾기</h2>
    <form:form action="${root}user/find_id_pro" method="post" modelAttribute="FindIdBean">
        <div class="form-group">
            <form:label path="user_name"></form:label>
            <i class="fas fa-user"></i>
            <form:input path="user_name" class="form-control" placeholder="이름"/>
            <form:errors path="user_name" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="email"></form:label>
            <i class="fas fa-envelope"></i>
            <form:input path="email" class="form-control" placeholder="이메일"/>
            <form:errors path="email" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="phone"></form:label>
            <i class="fas fa-phone"></i>
            <form:input path="phone" class="form-control" placeholder="전화번호"/>
            <form:errors path="phone" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-submit">아이디 찾기</button>
        </div>
        <div class="login-link">
            계정이 있으신가요? <a href="${root}user/login">로그인</a>
        </div>
        <div class="find-link">
            <a href="${root}/user/find_pw">비밀번호 찾기</a>
        </div>
    </form:form>
</div>
</body>
</html>
