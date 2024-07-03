<%-- File Location: /views/admin/password_check.jsp --%>
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
    <script>
        function submitPasswordForm() {
            document.getElementById("passwordForm").submit();
        }
    </script>
</head>
<body class="hold-transition">
<div class="wrapper">
    <div class="content-wrapper">
        <section class="content">
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">비밀번호 확인</h3>
                    </div>
                    <div class="card-body">
                        <form id="passwordForm" action="${root}admin/checkPassword" method="post">
                            <div class="form-group">
                                <label for="login_pwd">비밀번호</label>
                                <input type="password" id="login_pwd" name="login_pwd" class="form-control" required />
                            </div>
                            <input type="hidden" name="admin_idx" value="${admin_idx}" />
                            <input type="hidden" name="action" value="${action}" />
                            <input type="hidden" name="returnFolder" value="${returnFolder}" />
                            <button type="button" class="btn btn-primary" onclick="submitPasswordForm()">확인</button>
                            <button type="button" class="btn btn-secondary" onclick="window.close();">취소</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
