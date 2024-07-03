<%-- File Location: /views/admin/board/2/detail.jsp --%>
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
                            <h3 class="card-title">QnA 게시글 상세보기</h3>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <label for="board_id">QnA 게시글 ID</label>
                                <input type="text" id="board_id" name="board_id" class="form-control" value="${board.board_id}" readonly>
                            </div>
                            <br><hr><br><!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
                            <div class="form-group">
                                <label for="user_idx">질문 작성자 ID</label>
                                <input type="text" id="user_idx" name="user_idx" class="form-control" value="${board.user_idx}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="board_title">질문 제목</label>
                                <input type="text" id="board_title" name="board_title" class="form-control" value="${board.board_title}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="board_content">질문 내용</label>
                                <textarea id="board_content" name="board_content" class="form-control" rows="5" readonly>${board.board_content}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="board_date">질문 게시 날짜</label>
                                <input type="text" id="board_date" name="board_date" class="form-control" value="${board.formattedDate}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="views">질문 조회수</label>
                                <input type="text" id="views" name="views" class="form-control" value="${board.views}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="current_pic">현재 사진</label>
                                <div>
                                    <c:choose>
                                        <c:when test="${board.board_pic != null}">
                                            <img src="${root}upload/${board.board_pic}" alt="사진" class="img-thumbnail" style="max-width: 150px;">
                                        </c:when>
                                        <c:otherwise> X </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <button type="button" class="btn btn-info" onclick="location.href='${root}admin/board/2/modify/${board.board_id}'">질문 수정</button>
                            <button type="button" class="btn btn-danger" onclick="confirmDelete(${board.board_id})">질문 삭제</button>
                        	<br><hr><br><!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
                            <c:choose>
                                <c:when test="${board.hasAnswer}">
                                    <div class="form-group">
                                        <label for="admin_idx">답변 작성자 ID</label>
                                        <input type="text" id="admin_idx" name="admin_idx" class="form-control" value="${board.admin_idx}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="qna_comment">답변 내용</label>
                                        <textarea id="qna_comment" name="qna_comment" class="form-control" rows="5" readonly>${board.qna_comment}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="qna_comment_date">답변 날짜</label>
                                        <input type="text" id="qna_comment_date" name="qna_comment_date" class="form-control" value="${board.formattedQnaCommentDate}" readonly>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <label>답변 상태</label>
                                        <input type="text" class="form-control" value="답변 없음" readonly>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            
                            <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로 가기</button>
                            <c:choose>
                                <c:when test="${board.hasAnswer}">
		                            <button class="btn btn-primary" onclick="location.href='${root}admin/board/2/answer/modify/${board.board_id}'">답변 수정</button>
		                            <button class="btn btn-danger" onclick="confirmDeleteAnswer(${board.board_id})">답변 삭제</button>
                                </c:when>
                                <c:otherwise>
                                	<button class="btn btn-warning" onclick="location.href='${root}admin/board/2/answer/add/${board.board_id}'">답변 추가</button>
                                </c:otherwise>
                            </c:choose>
							<button type="button" class="btn btn-success"
							onclick="location.href='${root}board/read2?board_info_idx=2&board_id=${board.board_id}&page=1'">
								유저 Q&A 게시판
							</button>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
    </div>
    <script>
    function confirmDelete(boardId) {
        if (confirm('질문을 삭제하시겠습니까?')) {
            $.ajax({
                url: '${root}admin/board/2/delete',
                type: 'POST',
                data: { id: boardId },
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
    function confirmDeleteAnswer(boardId) {
        if (confirm('답변을 삭제하시겠습니까?')) {
            $.ajax({
                url: '${root}admin/board/2/answer/delete',
                type: 'POST',
                data: { board_id: boardId },
                success: function(response) {
                    alert('답변이 삭제되었습니다.');
                    location.reload();
                },
                error: function() {
                    alert('답변 삭제에 실패했습니다.');
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

