<%-- File Location: /views/admin/user_info/modify.jsp --%>
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
                        <h3 class="card-title">유저 수정</h3>
                    </div>
                    <div class="card-body">
                        <form action="${root}admin/userInfo/modify" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                            <input type="hidden"  id="user_idx" name="user_idx" value="${user.user_idx}">
                            <div class="form-group">
                                <label for="user_name">이름</label>
                                <input type="text" id="user_name" name="user_name" class="form-control" value="${user.user_name}" readonly>
                            </div>
                            <div class="form-group inline-buttons">
                                <label for="user_nickname">닉네임</label>
                                <input type="text" id="user_nickname" name="user_nickname" class="form-control" value="${user.user_nickname}" required readonly>
                                <button type="button" class="btn btn-secondary" onclick="enableEdit('user_nickname')">수정</button>
                                <button type="button" class="btn btn-primary" onclick="checkNickname()">중복 체크</button>
                                <span id="nicknameCheckMessage" style="color: red;"></span>
                            </div>
                            <div class="form-group">
                                <label for="user_id">유저 ID</label>
                                <input type="text" id="user_id" name="user_id" class="form-control" value="${user.user_id}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="address">주소</label>
                                <input type="text" id="address" name="address" class="form-control" value="${user.address}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="detailaddress">상세 주소</label>
                                <input type="text" id="detailaddress" name="detailaddress" class="form-control" value="${user.detailaddress}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="addressnum">우편번호</label>
                                <input type="text" id="addressnum" name="addressnum" class="form-control" value="${user.addressnum}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="phone">전화번호</label>
                                <input type="text" id="phone" name="phone" class="form-control" value="${user.phone}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="email">이메일</label>
                                <input type="email" id="email" name="email" class="form-control" value="${user.email}" readonly>
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

function validateForm() {
    if (!nicknameChecked) {
        document.getElementById('nicknameCheckMessage').textContent = '닉네임 중복 체크를 해주세요.';
        return false;
    }
    return true;
}

function enableEdit(fieldId) {
    document.getElementById(fieldId).readOnly = false;
    if (fieldId === 'user_nickname') {
        nicknameChecked = false;
    }
}

// 닉네임 중복 체크
function checkNickname() {
    const nickname = document.getElementById('user_nickname').value;
    const userIdx = document.getElementById('user_idx').value;
    console.log(document.getElementById('user_nickname'));
    console.log(document.getElementById('user_idx'));
    $.get('${root}admin/userInfo/checkNickname', { user_nickname: nickname, user_idx: userIdx }, function(data) {
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
</script>
<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
