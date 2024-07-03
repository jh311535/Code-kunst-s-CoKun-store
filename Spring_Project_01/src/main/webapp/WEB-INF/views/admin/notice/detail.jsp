<%-- File Location: /views/admin/notice/detail.jsp --%>
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
							<h3 class="card-title">공지 상세보기</h3>
						</div>
						<div class="card-body">
							<div class="form-group">
								<label for="notice_id">공지 ID</label> <input type="text"
									id="notice_id" name="notice_id" class="form-control"
									value="${notice.notice_id}" readonly>
							</div>
							<div class="form-group">
								<label for="admin_idx">관리자 ID</label> <input type="text"
									id="admin_idx" name="admin_idx" class="form-control"
									value="${notice.admin_idx}" readonly>
							</div>
							<div class="form-group">
								<label for="notice_title">제목</label> <input type="text"
									id="notice_title" name="notice_title" class="form-control"
									value="${notice.notice_title}" readonly>
							</div>
							<div class="form-group">
								<label for="notice_content">내용</label>
								<textarea id="notice_content" name="notice_content"
									class="form-control" rows="5" readonly>${notice.notice_content}</textarea>
							</div>
							<div class="form-group">
								<label for="notice_date">공지 날짜</label> <input type="text"
									id="notice_date" name="notice_date" class="form-control"
									value="${notice.formattedDate}" readonly>
							</div>
							<div class="form-group">
								<label for="notice_views">조회수</label> <input type="text"
									id="notice_views" name="notice_views" class="form-control"
									value="${notice.notice_views}" readonly>
							</div>
							<div class="form-group">
								<label for="current_pic">현재 사진</label>
								<div>
									<c:choose>
										<c:when test="${notice.notice_pic != null}">
											<img src="${root}upload/${notice.notice_pic}" alt="사진"
												class="img-thumbnail" style="max-width: 150px;">
										</c:when>
										<c:otherwise> X </c:otherwise>
									</c:choose>
								</div>
							</div>
							<button type="button" class="btn btn-secondary"
								onclick="history.back();">뒤로 가기</button>
							<button type="button" class="btn btn-info"
								onclick="location.href='${root}admin/notice/modify/${notice.notice_id}'">수정</button>
							<button type="button" class="btn btn-danger"
								onclick="confirmDelete(${notice.notice_id})">삭제</button>
							<button type="button" class="btn btn-success"
							onclick="location.href='${root}notice/detail/${notice.notice_id}'">
								유저 공지게시판
							</button>
						</div>
					</div>
				</div>
			</section>
		</div>
		<c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
	</div>
	<script>
        function confirmDelete(noticeId) {
            if (confirm('정말 삭제하시겠습니까?')) {
                $.ajax({
                    url: '${root}admin/notice/delete',
                    type: 'POST',
                    data: { id: noticeId },
                    success: function(response) {
                        alert('삭제되었습니다.');
                        window.location.href = '${root}admin/notice/manage';
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
