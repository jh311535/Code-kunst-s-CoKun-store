<%-- File Location: /views/admin/review/manage.jsp --%>
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
            max-width: 200px;
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
                <h1>리뷰 관리</h1>
                <div class="card">
                    <div class="card-header">
                        <div class="card-tools">
                            <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                        </div>
                        <div id="searchForm" style="display:none; margin-top:10px;">
                            <form action="${root}admin/review/search?returnJSP=manageJSP" method="post" onsubmit="removeEmptyFields(event)">
                                <div class="input-group input-group-sm" style="width: 100%;">
                                    <div class="form-group" style="width: 100%;">
                                        <label for="review_id">리뷰 ID</label>
                                        <input type="text" name="review_id" class="form-control" placeholder="리뷰 ID" value="${filter.review_id}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="user_idx">유저 ID</label>
                                        <input type="text" name="user_idx" class="form-control" placeholder="유저 ID" value="${filter.user_idx}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="book_id">책 ID</label>
                                        <input type="text" name="book_id" class="form-control" placeholder="책 ID" value="${filter.book_id}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="rating_min">최소 별점</label>
                                        <input type="text" name="rating_min" class="form-control" placeholder="최소 별점" value="${filter.rating_min}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="rating_max">최대 별점</label>
                                        <input type="text" name="rating_max" class="form-control" placeholder="최대 별점" value="${filter.rating_max}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="review_content">리뷰 내용</label>
                                        <input type="text" name="review_content" class="form-control" placeholder="리뷰 내용" value="${filter.review_content}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="startDate">작성 시작일</label>
                                        <input type="date" name="startDate" class="form-control" placeholder="작성 시작일" value="${filter.startDate}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="endDate">작성 종료일</label>
                                        <input type="date" name="endDate" class="form-control" placeholder="작성 종료일" value="${filter.endDate}">
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
                                    <th><a href="${root}admin/review/sort?returnJSP=manageJSP&sortField=reviewId">ID <i class="fas ${filter.reviewIdOrder == 'asc' ? 'fa-sort-up' : filter.reviewIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>관리</th>
                                	<th><a href="${root}admin/review/sort?returnJSP=manageJSP&sortField=userIdx">유저 ID <i class="fas ${filter.userIdxOrder == 'asc' ? 'fa-sort-up' : filter.userIdxOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/review/sort?returnJSP=manageJSP&sortField=bookId">책 ID <i class="fas ${filter.bookIdOrder == 'asc' ? 'fa-sort-up' : filter.bookIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>책 이름</th>
                                    <th>책 사진</th>
                                    <th><a href="${root}admin/review/sort?returnJSP=manageJSP&sortField=rating">별점 <i class="fas ${filter.ratingOrder == 'asc' ? 'fa-sort-up' : filter.ratingOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>리뷰 내용</th>
                                    <th><a href="${root}admin/review/sort?returnJSP=manageJSP&sortField=reviewDate">작성 날짜 <i class="fas ${filter.reviewDateOrder == 'asc' ? 'fa-sort-up' : filter.reviewDateOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                            	</tr>
                            </thead>
                            <tbody>
                                <c:forEach var="review" items="${reviewList}">
                                    <tr>
                                        <td><a href="${root}admin/review/detail/${review.review_id}">${review.review_id}</a></td>
                                        <td>
                                            <button class="btn btn-info btn-sm" onclick="location.href='${root}admin/review/modify/${review.review_id}'">수정</button>
                                            <button class="btn btn-danger btn-sm" onclick="confirmDelete(${review.review_id})">삭제</button>
                                        </td>
                                        <td><a href="${root}admin/userInfo/detail/${review.user_idx}">${review.user_idx}</a></td>
                                        <td><a href="${root}admin/book/detail/${review.book_id}">${review.book_id}</a></td>
                                        <td class="break-word">${review.book_name}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${review.book_pic != null}">
                                                    <img src="${review.book_pic}" alt="사진" class="img-thumbnail" style="max-width: 150px;">
                                                </c:when>
                                                <c:otherwise> X </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${review.rating}</td>
                                        <td class="break-word">${review.review_content}</td>
                                        <td>${review.review_date}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty reviewList}">
                                    <tr>
                                        <td colspan="9" class="text-center">데이터 없음</td>
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
    function confirmDelete(reviewId) {
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '${root}admin/review/delete',
                type: 'POST',
                data: { id: reviewId },
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
