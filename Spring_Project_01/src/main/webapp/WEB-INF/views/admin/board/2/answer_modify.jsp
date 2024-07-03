<%-- File Location: /views/admin/board/2/answer_modify.jsp --%>
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
                            <h3 class="card-title">QnA 답변 수정</h3>
                        </div>
                        <div class="card-body">
                            <form action="${root}admin/board/2/answer/modify" method="post">
                                <div class="form-group">
                                    <label for="board_title">질문 제목</label>
                                    <input type="text" id="board_title" name="board_title" class="form-control" value="${qna.board_title}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="board_content">질문 내용</label>
                                    <textarea id="board_content" name="board_content" class="form-control" rows="5" readonly>${qna.board_content}</textarea>
                                </div>
								<div class="form-group">
									<label for="admin_idx">관리자 ID</label>
									<div class="input-group">
										<input type="text" id="admin_idx" name="admin_idx" value="${qna.admin_idx}" class="form-control" readonly />
										<div class="input-group-append">
											<button type="button" class="btn btn-secondary" onclick="openAdminSearch()">검색</button>
										</div>
									</div>
								</div>
                                <div class="form-group">
                                    <label for="qna_comment">답변 내용</label>
                                    <textarea id="qna_comment" name="qna_comment" class="form-control" rows="5">${qna.qna_comment}</textarea>
                                </div>
                                <input type="hidden" name="qna_idx" value="${qna.qna_idx}">
                                <input type="hidden" name="board_id" value="${qna.board_id}">
                                <input type="hidden" name="board_idx" value="${qna.board_info_idx}">
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
