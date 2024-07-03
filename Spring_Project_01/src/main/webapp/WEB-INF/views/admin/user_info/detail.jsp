<%-- File Location: /views/admin/user_info/detail.jsp --%>
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
                        <h3 class="card-title">유저 상세 정보</h3>
                    </div>
                    <div class="card-body">
                        <div class="form-group">
                            <label for="user_idx">유저 ID</label>
                            <input type="text" id="user_idx" name="user_idx" class="form-control" value="${user.user_idx}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="user_name">이름</label>
                            <input type="text" id="user_name" name="user_name" class="form-control" value="${user.user_name}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="user_nickname">닉네임</label>
                            <input type="text" id="user_nickname" name="user_nickname" class="form-control" value="${user.user_nickname}" readonly>
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
						<div class="form-group">
							<label for="admin_pic">사진</label>
							<c:choose>
								<c:when test="${user.user_pic != null}">
									<img src="${root}upload/${user.user_pic}" alt="사진"
										class="img-thumbnail" style="max-width: 150px;">
								</c:when>
								<c:otherwise> X </c:otherwise>
							</c:choose>
						</div>
                        <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로 가기</button>
                        <button type="button" class="btn btn-info" onclick="location.href='${root}admin/userInfo/modify/${user.user_idx}'">수정</button>
                        <button type="button" class="btn btn-danger" onclick="confirmDelete(${user.user_idx})">삭제</button>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
</div>
<script>
function confirmDelete(userIdx) {
    if (confirm('정말 삭제하시겠습니까?')) {
        $.ajax({
            url: '${root}admin/userInfo/delete',
            type: 'POST',
            data: { id: userIdx },
            success: function(response) {
                alert('삭제되었습니다.');
                location.reload();
            },
            error: function() {
                alert('삭제에 실패했습니다.');
            }
        });
    }
}
</script>
<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
