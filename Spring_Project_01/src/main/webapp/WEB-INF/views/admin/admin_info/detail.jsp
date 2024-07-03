<%-- File Location: /views/admin/admin_info/detail.jsp --%>
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
<link rel="stylesheet"
	href="${root}css/tempusdominus-bootstrap-4.min.css">
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
							<h3 class="card-title">관리자 상세 정보</h3>
						</div>
						<div class="card-body">
							<div class="form-group">
								<label for="admin_name">이름</label> <input type="text"
									id="admin_name" name="admin_name" class="form-control"
									value="${admin.admin_name}" readonly />
							</div>
							<div class="form-group">
								<label for="admin_nickname">닉네임</label> <input type="text"
									id="admin_nickname" name="admin_nickname" class="form-control"
									value="${admin.admin_nickname}" readonly />
							</div>
							<div class="form-group">
								<label for="login_id">로그인 ID</label> <input type="text"
									id="login_id" name="login_id" class="form-control"
									value="${admin.login_id}" readonly />
							</div>
							<div class="form-group">
								<label for="admin_type">관리자 권한</label> <input type="text"
									id="admin_type" name="admin_type" class="form-control"
									value="${admin.admin_type}" readonly />
							</div>
							<div class="form-group">
								<label for="phone">전화번호</label> <input type="text" id="phone"
									name="phone" class="form-control" value="${admin.phone}"
									readonly />
							</div>
							<div class="form-group">
								<label for="email">이메일</label> <input type="email" id="email"
									name="email" class="form-control" value="${admin.email}"
									readonly />
							</div>
							<div class="form-group">
								<label for="admin_pic">사진</label>
								<c:choose>
									<c:when test="${admin.admin_pic != null}">
										<img src="${root}upload/${admin.admin_pic}" alt="사진"
											class="img-thumbnail" style="max-width: 150px;">
									</c:when>
									<c:otherwise> X </c:otherwise>
								</c:choose>
							</div>
							<button type="button" class="btn btn-secondary"
								onclick="history.back();">뒤로 가기</button>
							<button type="button" class="btn btn-info"
								onclick="location.href='${root}admin/adminInfo/modify/${admin.admin_idx}'">수정</button>
							<button type="button" class="btn btn-danger"
								onclick="confirmDelete(${admin.admin_idx})">삭제</button>
						</div>
					</div>
				</div>
			</section>
		</div>
		<c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
	</div>
    <script>
        function confirmDelete(adminIdx) {
            if (confirm('정말 삭제하시겠습니까?')) {
                location.href = '${root}admin/adminInfo/delete_check/' + adminIdx;
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
