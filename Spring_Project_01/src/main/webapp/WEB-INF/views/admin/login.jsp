<%-- File Location: /views/admin/login.jsp --%>
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
    <style>
        .login-box {
            width: 400px;
            margin: 7% auto;
        }
        .login-logo {
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-logo .logo img {
            max-height: 100px;
            margin-right: 10px;
        }
        .login-logo a {
            color: #333;
            font-size: 30px; /* Adjust the font size as needed */
        }
        .btn-block {
            display: flex;
            justify-content: space-between;
        }
        .btn-block .btn {
            flex: 1;
            margin-right: 10px;
        }
        .btn-block .back-button {
            flex: 2;
        }
    </style>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <div class="logo">
	        <a href="${root}" class="nav-link123">
	        	<img src="${root}image/logo_grey.png" alt="로고">
	        </a>
        </div>
        <a href="#"><b>관리자 로그인</b></a>
    </div>
    <div class="card">
        <div class="card-body login-card-body">
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-3" role="alert">
                    ${errorMessage}
                </div>
            </c:if>
            <form action="${root}admin/login" method="post">
                <div class="input-group mb-3">
                    <input type="text" name="login_id" class="form-control" placeholder="아이디" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" name="login_pwd" class="form-control" placeholder="비밀번호" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 btn-block">
                        <button type="submit" class="btn btn-primary">로그인</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
