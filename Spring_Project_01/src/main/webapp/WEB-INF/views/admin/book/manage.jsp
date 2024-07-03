<%-- File Location: /views/admin/book/manage.jsp --%>
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
                <h1>책 관리</h1>
                <div class="card">
                    <div class="card-header">
                        <div class="card-tools">
                            <button type="button" class="btn btn-primary btn-sm" onclick="location.href='${root}admin/book/add'">책 추가</button>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                        </div>
                        <div id="searchForm" style="display:none; margin-top:10px;">
                            <form action="${root}admin/book/search?returnJSP=manageJSP" method="post" onsubmit="removeEmptyFields(event)">
                                <div class="input-group input-group-sm" style="width: 100%;">
                                    <div class="form-group" style="width: 100%;">
                                        <label for="book_name">책 이름</label>
                                        <input type="text" name="book_name" class="form-control" placeholder="책 이름" value="${filter.book_name}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="author">저자</label>
                                        <input type="text" name="author" class="form-control" placeholder="저자" value="${filter.author}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="book_category">카테고리</label>
                                        <select name="book_category" class="form-control">
                                            <option value="">전체</option>
                                            <option value="건강" ${filter.book_category == '건강' ? 'selected' : ''}>건강</option>
                                            <option value="공예" ${filter.book_category == '공예' ? 'selected' : ''}>공예</option>
                                            <option value="농구" ${filter.book_category == '농구' ? 'selected' : ''}>농구</option>
                                            <option value="야구" ${filter.book_category == '야구' ? 'selected' : ''}>야구</option>
                                            <option value="축구" ${filter.book_category == '축구' ? 'selected' : ''}>축구</option>
                                            <option value="수영" ${filter.book_category == '수영' ? 'selected' : ''}>수영</option>
                                            <option value="유럽여행" ${filter.book_category == '유럽여행' ? 'selected' : ''}>유럽여행</option>
                                            <option value="일본여행" ${filter.book_category == '일본여행' ? 'selected' : ''}>일본여행</option>
                                            <option value="중국요리" ${filter.book_category == '중국요리' ? 'selected' : ''}>중국요리</option>
                                            <option value="다이어트 요리" ${filter.book_category == '다이어트 요리' ? 'selected' : ''}>다이어트 요리</option>
                                            <option value="사찰요리" ${filter.book_category == '사찰요리' ? 'selected' : ''}>사찰요리</option>
                                            <option value="생활요리" ${filter.book_category == '생활요리' ? 'selected' : ''}>생활요리</option>
                                            <option value="서양음악" ${filter.book_category == '서양음악' ? 'selected' : ''}>서양음악</option>
                                            <option value="재즈" ${filter.book_category == '재즈' ? 'selected' : ''}>재즈</option>
                                        </select>
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="isbn">ISBN</label>
                                        <input type="text" name="isbn" class="form-control" placeholder="ISBN" value="${filter.isbn}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="publisher">출판사</label>
                                        <input type="text" name="publisher" class="form-control" placeholder="출판사" value="${filter.publisher}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="startDate">출판 시작일</label>
                                        <input type="date" name="startDate" class="form-control" placeholder="출판 시작일" value="${filter.startDate}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="endDate">출판 종료일</label>
                                        <input type="date" name="endDate" class="form-control" placeholder="출판 종료일" value="${filter.endDate}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="minPrice">최소 가격</label>
                                        <input type="text" name="minPrice" class="form-control" placeholder="최소 가격" value="${filter.minPrice}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="maxPrice">최대 가격</label>
                                        <input type="text" name="maxPrice" class="form-control" placeholder="최대 가격" value="${filter.maxPrice}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="minInventory">최소 재고</label>
                                        <input type="text" name="minInventory" class="form-control" placeholder="최소 재고" value="${filter.minInventory}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="maxInventory">최대 재고</label>
                                        <input type="text" name="maxInventory" class="form-control" placeholder="최대 재고" value="${filter.maxInventory}">
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
                                    <th><a href="${root}admin/book/sort?returnJSP=manageJSP&sortField=bookId">ID <i class="fas ${filter.bookIdOrder == 'asc' ? 'fa-sort-up' : filter.bookIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>관리</th>
                                	<th>사진</th>
                                    <th><a href="${root}admin/book/sort?returnJSP=manageJSP&sortField=bookName">책 이름 <i class="fas ${filter.bookNameOrder == 'asc' ? 'fa-sort-up' : filter.bookNameOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/book/sort?returnJSP=manageJSP&sortField=category">카테고리 <i class="fas ${filter.categoryOrder == 'asc' ? 'fa-sort-up' : filter.categoryOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/book/sort?returnJSP=manageJSP&sortField=price">가격 <i class="fas ${filter.priceOrder == 'asc' ? 'fa-sort-up' : filter.priceOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/book/sort?returnJSP=manageJSP&sortField=inventory">재고 <i class="fas ${filter.inventoryOrder == 'asc' ? 'fa-sort-up' : filter.inventoryOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/book/sort?returnJSP=manageJSP&sortField=author">저자 <i class="fas ${filter.authorOrder == 'asc' ? 'fa-sort-up' : filter.authorOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/book/sort?returnJSP=manageJSP&sortField=publisher">출판사 <i class="fas ${filter.publisherOrder == 'asc' ? 'fa-sort-up' : filter.publisherOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                            	</tr>
                            </thead>
                            <tbody>
                                <c:forEach var="book" items="${bookList}">
                                    <tr>
                                        <td><a href="${root}admin/book/detail/${book.book_id}">${book.book_id}</a></td>
                                        <td>
                                            <button class="btn btn-info btn-sm" onclick="location.href='${root}admin/book/modify/${book.book_id}'">수정</button>
                                            <button class="btn btn-danger btn-sm" onclick="confirmDelete(${book.book_id})">삭제</button>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${book.book_pic != null}">
                                                    <img src="${book.book_pic}" alt="사진" class="img-thumbnail" style="max-width: 150px;">
                                                </c:when>
                                                <c:otherwise> X </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="break-word">${book.book_name}</td>
                                        <td>${book.book_category}</td>
                                        <td>${book.book_price}원</td>
                                        <td>${book.inventory}권</td>
                                        <td class="break-word">${book.author}</td>
                                        <td>${book.publisher}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty bookList}">
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
    function confirmDelete(bookId) {
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '${root}admin/book/delete',
                type: 'POST',
                data: { id: bookId },
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
