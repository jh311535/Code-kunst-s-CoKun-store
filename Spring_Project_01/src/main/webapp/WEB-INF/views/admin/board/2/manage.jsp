<%-- File Location: /views/admin/board/2/manage.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>코쿤스토어</title>
    <script src="${root}js/adminlte.min.js"></script>
    <script src="${root}js/bootstrap.bundle.min.js"></script>
    <script src="${root}js/jquery.min.js"></script>
    <script src="${root}js/locales-all.min.js"></script>
    <script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="${root}css/adminlte.min.css">
    <link rel="stylesheet" href="${root}css/all.min.css">
    <link rel="stylesheet" href="${root}css/fontawesome.min.css">
    <link rel="stylesheet" href="${root}css/icheck-bootstrap.min.css">
    <link rel="stylesheet" href="${root}css/tempusdominus-bootstrap-4.min.css">
    <style>
        .break-word {
            word-break: break-all;
            max-width: 200px; /* 최대 width */
            white-space: normal;
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
                    <h1>QnA 게시판 관리</h1>
                    <div class="card">
                        <div class="card-header">
                            <div class="card-tools">
                                <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                                <button type="button" class="btn btn-success btn-sm" onclick="location.href='${root}board/main?board_info_idx=2'">
                                유저 QnA 게시판</button>
                            </div>
                            <div id="searchForm" style="display: none; margin-top: 10px;">
                                <form action="${root}admin/board/2/search?returnJSP=manageJSP" method="post" onsubmit="removeEmptyFields(event)">
                                    <div class="input-group input-group-sm" style="width: 100%;">
                                        <div class="form-group" style="width: 100%;">
                                            <label for="board_id">질문 ID</label>
                                            <input type="text" name="board_id" class="form-control" placeholder="질문 ID" value="${filter.board_id}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="user_idx">질문 작성자 ID</label>
                                            <input type="text" name="user_idx" class="form-control" placeholder="질문 작성자 ID" value="${filter.user_idx}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="board_title">질문 제목</label>
                                            <input type="text" name="board_title" class="form-control" placeholder="질문 제목" value="${filter.board_title}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="board_content">질문 내용</label>
                                            <input type="text" name="board_content" class="form-control" placeholder="질문 내용" value="${filter.board_content}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="startDate">질문 시작 날짜</label>
                                            <input type="date" name="startDate" class="form-control" value="${filter.startDate}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="endDate">질문 종료 날짜</label>
                                            <input type="date" name="endDate" class="form-control" value="${filter.endDate}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="minViews">질문 최소 조회수</label>
                                            <input type="number" name="minViews" class="form-control" placeholder="질문 최소 조회수" value="${filter.minViews}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="maxViews">질문 최대 조회수</label>
                                            <input type="number" name="maxViews" class="form-control" placeholder="질문 최대 조회수" value="${filter.maxViews}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="qna_idx">답변 ID</label>
                                            <input type="text" name="qna_idx" class="form-control" placeholder="답변 ID" value="${filter.qna_idx}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="admin_idx">관리자 ID</label>
                                            <input type="text" name="admin_idx" class="form-control" placeholder="관리자 ID" value="${filter.admin_idx}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="qna_comment">답변 내용</label>
                                            <input type="text" name="qna_comment" class="form-control" placeholder="답변 내용" value="${filter.qna_comment}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="qna_startDate">답변 시작 날짜</label>
                                            <input type="date" name="qna_startDate" class="form-control" value="${filter.qna_startDate}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="qna_endDate">답변 종료 날짜</label>
                                            <input type="date" name="qna_endDate" class="form-control" value="${filter.qna_endDate}">
                                        </div>
                                        <div class="input-group-append">
                                            <button type="submit" class="btn btn-default"><i class="fas fa-search"></i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card-body table-responsive p-0">
                            <table class="table table-hover text-nowrap">
                                <thead>
                                    <tr>
                                        <th><a href="${root}admin/board/2/sort?returnJSP=manageJSP&sortField=boardId">질문 ID <i class="fas ${filter.boardIdOrder == 'asc' ? 'fa-sort-up' : filter.boardIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th>질문 관리</th>
                                        <th>답변 관리</th>
                                        <th><a href="${root}admin/board/2/sort?returnJSP=manageJSP&sortField=userIdx">질문 작성자 ID <i class="fas ${filter.userIdxOrder == 'asc' ? 'fa-sort-up' : filter.userIdxOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th><a href="${root}admin/board/2/sort?returnJSP=manageJSP&sortField=title">질문 제목 <i class="fas ${filter.titleOrder == 'asc' ? 'fa-sort-up' : filter.titleOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th><a href="${root}admin/board/2/sort?returnJSP=manageJSP&sortField=content">질문 내용 <i class="fas ${filter.contentOrder == 'asc' ? 'fa-sort-up' : filter.contentOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th><a href="${root}admin/board/2/sort?returnJSP=manageJSP&sortField=views">질문 조회수 <i class="fas ${filter.viewsOrder == 'asc' ? 'fa-sort-up' : filter.viewsOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th><a href="${root}admin/board/2/sort?returnJSP=manageJSP&sortField=qnaComment">답변 내용 <i class="fas ${filter.qnaCommentOrder == 'asc' ? 'fa-sort-up' : filter.qnaCommentOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="board" items="${boardList}">
                                        <tr>
                                            <td><a href="${root}admin/board/2/detail/${board.board_id}">${board.board_id}</a></td>
                                            <td>
                                                <button class="btn btn-info btn-sm" onclick="location.href='${root}admin/board/2/modify/${board.board_id}'">질문 수정</button>
                                                <button class="btn btn-danger btn-sm" onclick="confirmDelete(${board.board_id})">질문 삭제</button>
                                            </td>
                                            <td><c:choose>
                                                    <c:when test="${board.hasAnswer}">
                                                        <button class="btn btn-primary btn-sm" onclick="location.href='${root}admin/board/2/answer/modify/${board.board_id}'">답변 수정</button>
                                                        <button class="btn btn-danger btn-sm" onclick="confirmDeleteAnswer(${board.board_id})">답변 삭제</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-warning btn-sm" onclick="location.href='${root}admin/board/2/answer/add/${board.board_id}'">답변 추가</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><a href="${root}admin/userInfo/detail/${board.user_idx}">${board.user_idx}</a></td>
                                            <td class="break-word">${board.board_title}</td>
                                            <td class="break-word">${board.board_content}</td>
                                            <td>${board.views}</td>
                                            <td class="break-word"><c:choose>
                                                    <c:when test="${board.qna_comment != null}">
                                                        ${board.qna_comment}
                                                    </c:when>
                                                    <c:otherwise>
                                                        X
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty boardList}">
                                        <tr>
                                            <td colspan="8" class="text-center">데이터 없음</td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer clearfix">
                            <ul class="pagination pagination-sm m-0 float-right">
                                <c:choose>
                                    <c:when test="${pageBean.prevPage <= 0}">
                                        <li class="paginate_button page-item previous disabled"><a class="page-link" href="#">이전</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="paginate_button page-item previous"><a class="page-link" href="?page=${pageBean.prevPage}&pageChange=true">이전</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="idx" begin="${pageBean.min}" end="${pageBean.max}">
                                    <li class="page-item ${pageBean.currentPage == idx ? 'active' : ''}">
                                        <a class="page-link" href="?page=${idx}&pageChange=true">${idx}</a>
                                    </li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${pageBean.max >= pageBean.pageCnt}">
                                        <li class="paginate_button page-item next disabled"><a class="page-link" href="#">다음</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="paginate_button page-item next"><a class="page-link" href="?page=${pageBean.nextPage}&pageChange=true">다음</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
    </div>
    <script>
        function toggleSearch() {
            var searchForm = document.getElementById('searchForm');
            if (searchForm.style.display === 'none') {
                searchForm.style.display = 'block';
            } else {
                searchForm.style.display = 'none';
            }
        }
        function removeEmptyFields(event) {
            const inputs = document.querySelectorAll('#searchForm input, #searchForm select');
            inputs.forEach(input => {
                if (!input.value) {
                    input.name = '';
                }
            });
        }
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
</body>
</html>
