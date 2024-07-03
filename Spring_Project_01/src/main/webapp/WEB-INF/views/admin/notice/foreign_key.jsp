<%-- File Location: /views/admin/notice/foreign_key.jsp --%>
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
                <h1>공지 외래키 검색</h1>
                <div class="card">
                    <div class="card-header">
                        <div class="card-tools">
                            <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                        </div>
                        <div id="searchForm" style="display:none; margin-top:10px;">
                            <form action="${root}admin/notice/search?returnJSP=foreignJSP" method="post" onsubmit="removeEmptyFields(event)">
                                <div class="input-group input-group-sm" style="width: 100%;">
                                    <div class="form-group" style="width: 100%;">
                                        <label for="notice_id">공지 ID</label>
                                        <input type="text" name="notice_id" class="form-control" placeholder="공지 ID" value="${filter.notice_id}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="admin_idx">관리자 ID</label>
                                        <input type="text" name="admin_idx" class="form-control" placeholder="관리자 ID" value="${filter.admin_idx}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="notice_title">제목</label>
                                        <input type="text" name="notice_title" class="form-control" placeholder="제목" value="${filter.notice_title}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="notice_content">내용</label>
                                        <input type="text" name="notice_content" class="form-control" placeholder="내용" value="${filter.notice_content}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="startDate">시작 날짜</label>
                                        <input type="date" name="startDate" class="form-control" placeholder="시작 날짜" value="${filter.startDate}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="endDate">종료 날짜</label>
                                        <input type="date" name="endDate" class="form-control" placeholder="종료 날짜" value="${filter.endDate}">
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
                                    <th><a href="${root}admin/notice/sort?returnJSP=foreignJSP&sortField=noticeId">ID <i class="fas ${filter.noticeIdOrder == 'asc' ? 'fa-sort-up' : filter.noticeIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/notice/sort?returnJSP=foreignJSP&sortField=adminIdx">관리자 ID <i class="fas ${filter.adminIdxOrder == 'asc' ? 'fa-sort-up' : filter.adminIdxOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/notice/sort?returnJSP=foreignJSP&sortField=noticeTitle">제목 <i class="fas ${filter.titleOrder == 'asc' ? 'fa-sort-up' : filter.titleOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/notice/sort?returnJSP=foreignJSP&sortField=noticeContent">내용 <i class="fas ${filter.contentOrder == 'asc' ? 'fa-sort-up' : filter.contentOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/notice/sort?returnJSP=foreignJSP&sortField=dateOrder">날짜 <i class="fas ${filter.dateOrder == 'asc' ? 'fa-sort-up' : filter.dateOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/notice/sort?returnJSP=foreignJSP&sortField=viewsOrder">조회수 <i class="fas ${filter.viewsOrder == 'asc' ? 'fa-sort-up' : filter.viewsOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>선택</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="notice" items="${noticeList}">
                                    <tr>
                                        <td>${notice.notice_id}</td>
                                        <td>${notice.admin_idx}</td>
                                        <td>${notice.notice_title}</td>
                                        <td>${notice.notice_content}</td>
                                        <td>${notice.formattedDate}</td>
                                        <td>${notice.notice_views}</td>
                                        <td>
                                            <button class="btn btn-primary btn-sm" onclick="selectNotice(${notice.notice_id})">선택</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty noticeList}">
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
    function selectNotice(noticeId) {
        if (window.opener && !window.opener.closed) {
            window.opener.selectNotice(noticeId); // 부모 창의 selectNotice 함수 호출
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
