<%-- File Location: /views/admin/admin_info/modify.jsp --%>
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
                        <h3 class="card-title">관리자 수정</h3>
                    </div>
                    <div class="card-body">
                        <form id="modifyAdminForm" action="${root}admin/adminInfo/modify" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                            <input type="hidden" id="admin_idx" name="admin_idx" value="${admin.admin_idx}">
                            <div class="form-group">
                                <label for="admin_name">이름</label>
                                <input type="text" id="admin_name" name="admin_name" class="form-control" value="${admin.admin_name}" required>
                            </div>
                            <div class="form-group inline-buttons">
                                <label for="admin_nickname">닉네임</label>
                                <input type="text" id="admin_nickname" name="admin_nickname" class="form-control" value="${admin.admin_nickname}" required readonly>
                                <button type="button" class="btn btn-secondary" onclick="enableEdit('admin_nickname')">수정</button>
                                <button type="button" class="btn btn-primary" onclick="checkNickname()">중복 체크</button>
                                <span id="nicknameCheckMessage" style="color: red;"></span>
                            </div>
                            <div class="form-group inline-buttons">
                                <label for="login_id">로그인 ID</label>
                                <input type="text" id="login_id" name="login_id" class="form-control" value="${admin.login_id}" required readonly>
                                <button type="button" class="btn btn-secondary" onclick="enableEdit('login_id')">수정</button>
                                <button type="button" class="btn btn-primary" onclick="checkLoginId()">중복 체크</button>
                                <span id="loginIdCheckMessage" style="color: red;"></span>
                            </div>
                            <div class="form-group">
                                <label for="currentPassword">현재 비밀번호</label>
                                <input type="password" id="currentPassword" name="currentPassword" class="form-control" required>
                            </div>
				            <c:if test="${not empty errorMessage}">
				                <div class="alert alert-danger mt-3" role="alert">
				                    ${errorMessage}
				                </div>
				            </c:if>
                            <div class="form-group">
                                <button type="button" class="btn btn-secondary" onclick="togglePasswordChange()">비밀번호 변경</button>
                            </div>
                            <div id="passwordChangeFields" style="display: none;">
                                <div class="form-group">
                                    <label for="newPassword">새 비밀번호</label>
                                    <input type="password" id="newPassword" name="newPassword" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="newPasswordConfirm">새 비밀번호 확인</label>
                                    <input type="password" id="newPasswordConfirm" name="newPasswordConfirm" class="form-control">
                                </div>
                            </div>
                            <input type="hidden" name="pwChange" id="pwChange" value="false">
                            <div class="form-group">
                                <label for="phone">전화번호</label>
                                <input type="text" id="phone" name="phone" class="form-control" value="${admin.phone}" required>
                            </div>
                            <div class="form-group">
                                <label for="email">이메일</label>
                                <input type="email" id="email" name="email" class="form-control" value="${admin.email}" required>
                            </div>
                            <div class="form-group">
                                <label for="current_pic">현재 사진</label>
                                <div>
                                    <c:choose>
                                        <c:when test="${admin.admin_pic != null}">
                                            <img src="${root}upload/${admin.admin_pic}" alt="사진" class="img-thumbnail" style="max-width: 150px;">
                                        </c:when>
                                        <c:otherwise>X</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="admin_pic">새로운 사진</label>
                                <input type="file" id="admin_pic" name="upload_file" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-primary">수정</button>
                            <button type="button" class="btn btn-secondary" onclick="history.back();">취소</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
</div>
<script>
	let nicknameChecked = true;
	let loginIdChecked = true;

    function validateForm() {
        const currentPassword = document.getElementById('currentPassword').value;
        if (!currentPassword) {
            alert('현재 비밀번호를 입력해 주세요.');
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

    function enableEdit(fieldId) {
        document.getElementById(fieldId).readOnly = false;
        if (fieldId === 'admin_nickname') {
            nicknameChecked = false;
        } else if (fieldId === 'login_id') {
            loginIdChecked = false;
        }
    }

    function togglePasswordChange() {
        const passwordChangeFields = document.getElementById('passwordChangeFields');
        const pwChange = document.getElementById('pwChange');
        const newPassword = document.getElementById('newPassword');
        const newPasswordConfirm = document.getElementById('newPasswordConfirm');
        if (passwordChangeFields.style.display === 'none') {
            passwordChangeFields.style.display = 'block';
            pwChange.value = 'true';
            newPassword.required = true;
            newPasswordConfirm.required = true;
        } else {
            passwordChangeFields.style.display = 'none';
            pwChange.value = 'false';
            newPassword.required = false;
            newPasswordConfirm.required = false;
        }
    }

    // 닉네임 중복 체크
    function checkNickname() {
        const nickname = document.getElementById('admin_nickname').value;
        const adminIdx = document.getElementById('admin_idx').value;
        $.get('${root}admin/adminInfo/checkNickname', { admin_nickname: nickname, admin_idx: adminIdx }, function(data) {
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
        });
    }

    // 로그인 아이디 중복 체크
    function checkLoginId() {
        const loginId = document.getElementById('login_id').value;
        const adminIdx = document.getElementById('admin_idx').value;
        $.get('${root}admin/adminInfo/checkLoginId', { login_id: loginId, admin_idx: adminIdx }, function(data) {
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
