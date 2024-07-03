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
        justify-content: center; /* 수평 중앙 정렬 */
        align-items: center; /* 수직 중앙 정렬 */
        height: 100vh;
        margin: 0;
        background-color: #f7f7f7;
        flex-direction: column; /* 수직으로 정렬 */
    }
    .container {
        width: 400px; /* 너비를 300px에서 400px로 변경 */
        text-align: center;
        padding: 20px; /* 패딩을 약간 늘림 */
        border: 1px solid #CCC;
        border-radius: 6px;
        background-color: #fff;
        margin-top: 20px; /* 이미지와의 간격을 위해 추가된 여백 */
    }
    .form-group {
        margin-bottom: 15px; /* 폼 그룹 간의 간격을 늘림 */
        position: relative; /* 아이콘 위치를 위해 추가 */
    }
    .form-group input {
        width: 100%;
        padding: 10px; /* 입력 필드의 패딩을 늘림 */
        box-sizing: border-box;
        padding-left: 35px; /* 아이콘 공간 확보 */
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
        padding: 10px; /* 버튼의 패딩을 늘림 */
        border: 1px solid #4CAF50;
        border-radius: 6px;
        width: 100%;
        cursor: pointer;
    }
    .login-link {
        margin-top: 15px; /* 상단 마진을 늘림 */
    }
    .find-link {
        margin-top: 15px; /* 상단 마진을 늘림 */
        margin: 10px;
        font-size: 14px;
    }
    .find-link a:first-child {
        margin-right: 25px;
    }
    .error-message {
        font-size: 12px;
        color: red;
        margin-top: 2px;
    }
    .btn-google {
        background-color: #DB4437;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 6px;
        width: 100%;
        cursor: pointer;
        margin-top: 10px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .btn-google i {
        margin-right: 10px;
    }
    .or-divider {
        margin: 20px 0;
        text-align: center;
        position: relative;
    }
    .or-divider::before,
    .or-divider::after {
        content: '';
        position: absolute;
        top: 50%;
        width: 40%;
        height: 1px;
        background-color: #ccc;
    }
    .or-divider::before {
        left: 0;
    }
    .or-divider::after {
        right: 0;
    }
    .or-divider span {
        background: #fff;
        padding: 0 10px;
        color: #666;
    }
</style>
<!-- Bootstrap CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <a href="${root}/main">
        <img src="${root}/image/logo.png" class="text-center" style="width: 128px; height: auto;"/>
    </a>
    <h2 class="text-center">로그인</h2>
    <form:form action="${root}/user/login_pro" method="post" modelAttribute="tempUserLogin">
        <div class="form-group">
            <form:label path="user_id"></form:label>
            <i class="fas fa-user"></i>
            <form:input path="user_id" class="form-control" placeholder="아이디"/>
            <form:errors path="user_id" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="user_pw"></form:label>
            <i class="fas fa-lock"></i>
            <form:password path="user_pw" class="form-control" placeholder="비밀번호"/>
            <form:errors path="user_pw" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-submit">로그인</button>
        </div>
        <div class="find-link">
            <a href="${root}/user/find_id">아이디 찾기</a>
            <a href="${root}/user/find_pw">비밀번호 찾기</a>
        </div>
        <hr/>
        <div class="login-link">
            아직 계정이 없으신가요? <a href="${root}/user/join">회원가입</a>
        </div>
    </form:form>
    <div class="or-divider"><span>또는</span></div>
    <a href="${root}/user/getGoogleAuthUrl">
        <button class="btn-google">
            <i class="fab fa-google"></i> Google로 로그인
        </button>
    </a>
</div>
</body>
</html>
