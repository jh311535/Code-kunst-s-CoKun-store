<%-- File Location: /views/admin/board/2/foreign_key.jsp --%>
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
    <style>
        .break-word {
            word-break: break-all;
            max-width: 200px; /* 최대 width */
            white-space: normal;
        }
        .content-wrapper {
            margin-left: 0 !important;
            padding: 20px;
        }
        .container-fluid {
            padding: 0;
        }
    </style>
</head>
<body class="hold-transition">
<div class="wrapper">
    <div class="content-wrapper">
        <section class="content">
            <div class="container-fluid">
                <h1>QnA 게시판 외래키 검색</h1>
                <div class="card">
                    <div class="card-header">
                        <div class="card-tools">
                            <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                        </div>
                        <div id="searchForm" style="display:none; margin-top:10px;">
                            <form action="${root}admin/board/2/search?returnJSP=foreignJSP" method="post" onsubmit="removeEmptyFields(event)">
                                <div class="input-group input-group-sm" style="width: 100%;">
                                    <div class="form-group" style="width: 100%;">
                                        <label for="board_id">게시판 ID</label>
                                        <input type="text" name="board_id" class="form-control" placeholder="게시판 ID" value="${filter.board_id}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="user_idx">유저 ID</label>
                                        <input type="text" name="user_idx" class="form-control" placeholder="유저 ID" value="${filter.user_idx}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="board_title">제목</label>
                                        <input type="text" name="board_title" class="form-control" placeholder="제목" value="${filter.board_title}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="board_content">내용</label>
                                        <input type="text" name="board_content" class="form-control" placeholder="내용" value="${filter.board_content}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="startDate">시작 날짜</label>
                                        <input type="date" name="startDate" class="form-control" placeholder="시작 날짜" value="${filter.startDate}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="endDate">종료 날짜</label>
                                        <input type="date" name="endDate" class="form-control" placeholder="종료 날짜" value="${filter.endDate}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                    	<label for="minViews">최소 조회수</label>
                                    	<input type="number" name="minViews" class="form-control" placeholder="최소 조회수" value="${filter.minViews}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                    	<label for="maxViews">최대 조회수</label>
                                    	<input type="number" name="maxViews" class="form-control" placeholder="최대 조회수" value="${filter.maxViews}">
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
                                    <th><a href="${root}admin/board/2/sort?returnJSP=foreignJSP&sortField=boardId">ID <i class="fas ${filter.boardIdOrder == 'asc' ? 'fa-sort-up' : filter.boardIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/board/2/sort?returnJSP=foreignJSP&sortField=userIdx">유저 ID <i class="fas ${filter.userIdxOrder == 'asc' ? 'fa-sort-up' : filter.userIdxOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/board/2/sort?returnJSP=foreignJSP&sortField=boardTitle">제목 <i class="fas ${filter.titleOrder == 'asc' ? 'fa-sort-up' : filter.titleOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/board/2/sort?returnJSP=foreignJSP&sortField=boardContent">내용 <i class="fas ${filter.contentOrder == 'asc' ? 'fa-sort-up' : filter.contentOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/board/2/sort?returnJSP=foreignJSP&sortField=dateOrder">날짜 <i class="fas ${filter.dateOrder == 'asc' ? 'fa-sort-up' : filter.dateOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/board/2/sort?returnJSP=foreignJSP&sortField=viewsOrder">조회수 <i class="fas ${filter.viewsOrder == 'asc' ? 'fa-sort-up' : filter.viewsOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>선택</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="board" items="${boardList}">
                                    <tr>
                                        <td>${board.board_id}</td>
                                        <td>${board.user_idx}</td>
                                        <td class="break-word">${board.board_title}</td>
                                        <td class="break-word">${board.board_content}</td>
                                        <td>${board.formattedDate}</td>
                                        <td>${board.views}</td>
                                        <td>
                                            <button class="btn btn-primary btn-sm" onclick="selectBoard(${board.board_id})">선택</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty boardList}">
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
</div>
<script>
    function selectBoard(boardId) {
        if (window.opener && !window.opener.closed) {
            window.opener.selectBoard(boardId); // 부모 창의 selectBoard 함수 호출
            window.close(); // 현재 창 닫기
        } else {
            alert('부모 창을 찾을 수 없습니다.');
        }
    }
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
</script>
<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>

