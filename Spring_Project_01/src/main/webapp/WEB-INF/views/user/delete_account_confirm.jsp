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
        display: flex;
        justify-content: space-between;
    }
    .form-group button {
        background-color: #4CAF50;
        color: white;
        padding: 6px; /* 버튼 패딩 크기를 줄임 */
        border: 1px solid #4CAF50;
        border-radius: 6px;
        width: 45%; /* 버튼 너비를 줄임 */
        cursor: pointer;
    }
    .form-group .btn-cancel {
        background-color: #f44336; /* 취소 버튼 색상 변경 */
        border: 1px solid #f44336;
    }
</style>
</head>
<body>

<div class="container" style="margin-top:100px">
<a href="${root}/main">
        <img src="${root}/image/logo.png" class="text-center" style="width: 128px; height: auto;"/>
    </a>
    <h5>정말로 회원탈퇴를 하시겠습니까?</h5>
    <form:form action="${root}/user/delete_account_pro" method="post" modelAttribute="deleteUserBean">
        <div class="form-group">
            <button type="submit" class="btn btn-submit">확인</button>
            <button type="button" class="btn btn-submit btn-cancel" onclick="location.href='${root}/user/mypage'">취소</button>
        </div>
    </form:form>
</div>

</body>
</html>
