<%-- File Location: /views/admin/notice/modify.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
							<h3 class="card-title">공지 수정</h3>
						</div>
						<div class="card-body">
							<form action="${root}admin/notice/modify" method="post" enctype="multipart/form-data">
								<input type="hidden" name="notice_id" value="${notice.notice_id}">
								<div class="form-group">
									<label for="admin_idx">관리자 ID</label>
									<div class="input-group">
										<input type="text" id="admin_idx" name="admin_idx" value="${notice.admin_idx}" class="form-control" readonly />
										<div class="input-group-append">
											<button type="button" class="btn btn-secondary" onclick="openAdminSearch()">검색</button>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="notice_title">제목</label>
									<input type="text" id="notice_title" name="notice_title" class="form-control" value="${notice.notice_title}" required>
								</div>
								<div class="form-group">
									<label for="notice_content">내용</label>
									<textarea id="notice_content" name="notice_content" class="form-control" rows="5" required>${notice.notice_content}</textarea>
								</div>
								<div class="form-group">
									<label for="notice_date">공지 날짜</label>
									<input type="date" id="notice_date" name="notice_date" class="form-control" value="${fn:substring(notice.notice_date, 0, 10)}" required>
								</div>
								<div class="form-group">
									<label for="notice_views">조회수</label>
									<input type="text" id="notice_views" name="notice_views" class="form-control" value="${notice.notice_views}" readonly>
								</div>
								<div class="form-group">
								    <label for="current_pic">현재 사진</label>
								    <div>
								        <c:choose>
								            <c:when test="${notice.notice_pic != null}">
								                <img src="${root}upload/${notice.notice_pic}" alt="사진" class="img-thumbnail" style="max-width: 150px;">
								                <div class="form-check">
								                    <input class="form-check-input" type="checkbox" id="delete_pic" name="delete_pic" value="true">
								                    <label class="form-check-label" for="delete_pic">사진 삭제</label>
								                </div>
								            </c:when>
								            <c:otherwise> X </c:otherwise>
								        </c:choose>
								    </div>
								</div>
								<div class="form-group">
									<label for="notice_pic">새로운 사진</label>
									<input type="file" id="notice_pic" name="upload_file" class="form-control" />
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
		function openAdminSearch() {
			window.open('${root}admin/adminInfo/foreign_key', '관리자 검색', 'width=1200,height=750,scrollbars=yes');
		}

		function selectAdmin(adminIdx) {
			document.getElementById('admin_idx').value = adminIdx;
		}
	</script>
	<script src="${root}js/adminlte.min.js"></script>
	<script src="${root}js/bootstrap.bundle.min.js"></script>
	<script src="${root}js/jquery.min.js"></script>
	<script src="${root}js/locales-all.min.js"></script>
	<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
