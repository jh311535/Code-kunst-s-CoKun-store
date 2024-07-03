<%-- File Location: /views/admin/board/2/answer_add.jsp --%>
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
                            <h3 class="card-title">QnA 답변 추가</h3>
                        </div>
                        <div class="card-body">
                            <form action="${root}admin/board/2/answer/add" method="post">
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
	                                <input type="text" id="admin_idx" name="admin_idx" class="form-control" value="${qna.admin_idx}" readonly>
	                            </div>
                                <div class="form-group">
                                    <label for="qna_comment">답변 내용</label>
                                    <textarea id="qna_comment" name="qna_comment" class="form-control" rows="5"></textarea>
                                </div>
                                <input type="hidden" name="board_id" value="${qna.board_id}">
                                <input type="hidden" name="board_idx" value="${qna.board_info_idx}">
                                <button type="submit" class="btn btn-primary">추가</button>
                                <button type="button" class="btn btn-secondary" onclick="history.back();">취소</button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
    </div>
    <script src="${root}js/adminlte.min.js"></script>
    <script src="${root}js/bootstrap.bundle.min.js"></script>
    <script src="${root}js/jquery.min.js"></script>
    <script src="${root}js/locales-all.min.js"></script>
    <script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
