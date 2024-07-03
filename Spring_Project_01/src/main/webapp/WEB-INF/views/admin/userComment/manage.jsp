<%-- File Location: /views/admin/userComment/manage.jsp --%>
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
    <script src="${root}js/adminlte.min.js"></script>
    <script src="${root}js/bootstrap.bundle.min.js"></script>
    <script src="${root}js/jquery.min.js"></script>
    <script src="${root}js/locales-all.min.js"></script>
    <script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
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
                    <h1>유저 댓글 관리</h1>
                    <div class="card">
                        <div class="card-header">
                            <div class="card-tools">
                                <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                            </div>
                            <div id="searchForm" style="display: none; margin-top: 10px;">
                                <form action="${root}admin/userComment/search?returnJSP=manageJSP" method="post" onsubmit="removeEmptyFields(event)">
                                    <div class="input-group input-group-sm" style="width: 100%;">
                                        <div class="form-group" style="width: 100%;">
                                            <label for="comment_id">댓글 ID</label>
                                            <input type="text" name="comment_id" class="form-control" placeholder="댓글 ID" value="${filter.comment_id}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="board_id">게시글 ID</label>
                                            <input type="text" name="board_id" class="form-control" placeholder="게시글 ID" value="${filter.board_id}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="user_idx">작성자 ID</label>
                                            <input type="text" name="user_idx" class="form-control" placeholder="작성자 ID" value="${filter.user_idx}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="board_title">게시판 제목</label>
                                            <input type="text" name="board_title" class="form-control" placeholder="게시글 제목" value="${filter.board_title}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="comment_content">댓글 내용</label>
                                            <input type="text" name="comment_content" class="form-control" placeholder="댓글 내용" value="${filter.comment_content}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="start_date">시작 날짜</label>
                                            <input type="date" name="start_date" class="form-control" value="${filter.start_date}">
                                        </div>
                                        <div class="form-group" style="width: 100%;">
                                            <label for="end_date">종료 날짜</label>
                                            <input type="date" name="end_date" class="form-control" value="${filter.end_date}">
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
                                        <th><a href="${root}admin/userComment/sort?returnJSP=manageJSP&sortField=commentId">댓글 ID<i class="fas ${filter.commentIdOrder == 'asc' ? 'fa-sort-up' : filter.commentIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th>관리</th>
                                        <th><a href="${root}admin/userComment/sort?returnJSP=manageJSP&sortField=boardId">게시글 ID <i class="fas ${filter.boardIdOrder == 'asc' ? 'fa-sort-up' : filter.boardIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th><a href="${root}admin/userComment/sort?returnJSP=manageJSP&sortField=userIdx">댓글 작성자 ID <i class="fas ${filter.userIdxOrder == 'asc' ? 'fa-sort-up' : filter.userIdxOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th><a href="${root}admin/userComment/sort?returnJSP=manageJSP&sortField=title">게시글 제목 <i class="fas ${filter.titleOrder == 'asc' ? 'fa-sort-up' : filter.titleOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th><a href="${root}admin/userComment/sort?returnJSP=manageJSP&sortField=content">댓글 내용 <i class="fas ${filter.contentOrder == 'asc' ? 'fa-sort-up' : filter.contentOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                        <th><a href="${root}admin/userComment/sort?returnJSP=manageJSP&sortField=date">댓글 작성 날짜 <i class="fas ${filter.dateOrder == 'asc' ? 'fa-sort-up' : filter.dateOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="comment" items="${commentList}">
                                        <tr>
                                            <td><a href="${root}admin/userComment/detail/${comment.comment_id}">${comment.comment_id}</a></td>
                                            <td>
                                                <button class="btn btn-info btn-sm" onclick="location.href='${root}admin/userComment/modify/${comment.comment_id}'">수정</button>
                                                <button class="btn btn-danger btn-sm" onclick="confirmDelete(${comment.comment_id})">삭제</button>
                                            </td>
                                            <td><a href="${root}admin/board/1/detail/${comment.board_id}">${comment.board_id}</a></td>
                                            <td><a href="${root}admin/userInfo/detail/${comment.user_idx}">${comment.user_idx}</a></td>
                                            <td class="break-word">${comment.board_title}</td>
                                            <td class="break-word">${comment.comment_content}</td>
                                            <td>${comment.comment_date}</td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty commentList}">
                                        <tr>
                                            <td colspan="7" class="text-center">데이터 없음</td>
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
        function confirmDelete(commentId) {
            if (confirm('정말 삭제하시겠습니까?')) {
                $.ajax({
                    url: '${root}admin/userComment/delete',
                    type: 'POST',
                    data: { id: commentId },
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
</body>
</html>
