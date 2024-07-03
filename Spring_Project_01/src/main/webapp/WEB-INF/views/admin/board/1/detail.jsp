<%-- File Location: /views/admin/board/1/detail.jsp --%>
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
                            <h3 class="card-title">자유 게시글 상세보기</h3>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <label for="board_id">자유 게시판 ID</label>
                                <input type="text" id="board_id" name="board_id" class="form-control" value="${board.board_id}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="user_idx">작성자 ID</label>
                                <input type="text" id="user_idx" name="user_idx" class="form-control" value="${board.user_idx}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="user_nickname">작성자 닉네임</label>
                                <input type="text" id="user_nickname" name="user_nickname" class="form-control" value="${board.user_nickname}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="board_title">제목</label>
                                <input type="text" id="board_title" name="board_title" class="form-control" value="${board.board_title}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="board_content">내용</label>
                                <textarea id="board_content" name="board_content" class="form-control" rows="5" readonly>${board.board_content}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="board_date">게시 날짜</label>
                                <input type="text" id="board_date" name="board_date" class="form-control" value="${board.formattedDate}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="views">조회수</label>
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
                            <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로 가기</button>
                            <button type="button" class="btn btn-info" onclick="location.href='${root}admin/board/1/modify/${board.board_id}'">수정</button>
                            <button type="button" class="btn btn-danger" onclick="confirmDelete(${board.board_id})">삭제</button>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
    </div>
    <script>
        function confirmDelete(boardId) {
            if (confirm('정말 삭제하시겠습니까?')) {
                $.ajax({
                    url: '${root}admin/board/1/delete',
                    type: 'POST',
                    data: { id: boardId },
                    success: function(response) {
                        alert('삭제되었습니다.');
                        window.location.href = '${root}admin/board/1/manage';
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

