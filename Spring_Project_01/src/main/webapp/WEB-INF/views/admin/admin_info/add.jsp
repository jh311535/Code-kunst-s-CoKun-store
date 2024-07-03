<%-- File Location: /views/admin/admin_info/add.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.inline-buttons {
    display: flex;
    align-items: center;
}

.inline-buttons input {
    flex: 1;
}

.inline-buttons button {
    margin-left: 10px;
    white-space: nowrap;
}
</style>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <c:import url="/WEB-INF/views/admin_include/top_menu.jsp"></c:import>
    <c:import url="/WEB-INF/views/admin_include/left_menu.jsp"></c:import>
    <div class="content-wrapper">
        <section class="content">
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">관리자 추가</h3>
                    </div>
                    <div class="card-body">
                        <form id="addAdminForm" action="${root}admin/adminInfo/add" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                            <div class="form-group">
                                <label for="admin_name">이름</label>
                                <input type="text" id="admin_name" name="admin_name" class="form-control" required />
                            </div>
                            <div class="form-group inline-buttons">
                                <label for="admin_nickname">닉네임</label>
                                <input type="text" id="admin_nickname" name="admin_nickname" class="form-control" required />
                                <button type="button" class="btn btn-primary" onclick="checkNickname()">중복 체크</button>
                                <span id="nicknameCheckMessage" style="color: red;"></span>
                            </div>
                            <div class="form-group inline-buttons">
                                <label for="login_id">로그인 ID</label>
                                <input type="text" id="login_id" name="login_id" class="form-control" required />
                                <button type="button" class="btn btn-primary" onclick="checkLoginId()">중복 체크</button>
                                <span id="loginIdCheckMessage" style="color: red;"></span>
                            </div>
                            <div class="form-group">
                                <label for="login_pwd">비밀번호</label>
                                <input type="password" id="login_pwd" name="login_pwd" class="form-control" required />
                            </div>
                            <div class="form-group">
                                <label for="passwordConfirm">비밀번호 확인</label>
                                <input type="password" id="passwordConfirm" name="passwordConfirm" class="form-control" required />
                            </div>
                            <div class="form-group">
                                <label for="phone">전화번호</label>
                                <input type="text" id="phone" name="phone" class="form-control" required />
                            </div>
                            <div class="form-group">
                                <label for="email">이메일</label>
                                <input type="email" id="email" name="email" class="form-control" required />
                            </div>
                            <div class="form-group">
                                <label for="admin_pic">사진</label>
                                <input type="file" id="admin_pic" name="upload_file" class="form-control" />
                            </div>
                            <button type="submit" class="btn btn-primary">추가</button>
                            <button type="button" class="btn btn-secondary" onclick="history.back();">취소</button>
                        </form>
                        <c:if test="${not empty errorMessage}">
                            <div style="color: red;">${errorMessage}</div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
</div>
<script>
    let nicknameChecked = false;
    let loginIdChecked = false;

    function validateForm() {
        const password = document.getElementById('login_pwd').value;
        const passwordConfirm = document.getElementById('passwordConfirm').value;
        if (password !== passwordConfirm) {
            alert('비밀번호가 일치하지 않습니다.');
            return false;
        }
        if (!nicknameChecked) {
            document.getElementById('nicknameCheckMessage').textContent = '닉네임 중복 체크를 해주세요.';
            if (!loginIdChecked) {
                document.getElementById('loginIdCheckMessage').textContent = '로그인 ID 중복 체크를 해주세요.';
                return false;
            }
            return false;
        }
        if (!loginIdChecked) {
            document.getElementById('loginIdCheckMessage').textContent = '로그인 ID 중복 체크를 해주세요.';
            return false;
        }
        return true;
    }

    function checkNickname() {
        const nickname = document.getElementById('admin_nickname').value;
        $.ajax({
            url: '${root}admin/adminInfo/checkNickname',
            data: { admin_nickname: nickname },
            type: 'GET',
            success: function(data) {
                const messageElement = document.getElementById('nicknameCheckMessage');
                if (data) {
                    messageElement.textContent = '이미 사용 중인 닉네임입니다.';
                    messageElement.style.color = 'red';
                    nicknameChecked = false;
                } else {
                    messageElement.textContent = '사용 가능한 닉네임입니다.';
                    messageElement.style.color = 'green';
                    nicknameChecked = true;
                }
            },
            error: function() {
                alert('닉네임 중복 체크 중 오류가 발생했습니다.');
            }
        });
    }

    function checkLoginId() {
        const loginId = document.getElementById('login_id').value;
        $.ajax({
            url: '${root}admin/adminInfo/checkLoginId',
            data: { login_id: loginId },
            type: 'GET',
            success: function(data) {
                const messageElement = document.getElementById('loginIdCheckMessage');
                if (data) {
                    messageElement.textContent = '이미 사용 중인 로그인 ID입니다.';
                    messageElement.style.color = 'red';
                    loginIdChecked = false;
                } else {
                    messageElement.textContent = '사용 가능한 로그인 ID입니다.';
                    messageElement.style.color = 'green';
                    loginIdChecked = true;
                }
            },
            error: function() {
                alert('로그인 ID 중복 체크 중 오류가 발생했습니다.');
            }
        });
    }
</script>

<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
