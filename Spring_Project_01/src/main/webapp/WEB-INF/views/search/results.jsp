<%-- File Location: /views/search/results.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>코쿤스토어</title>
	<link href="${root}css/top_menu.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${root}css/adminlte.min.css">
    <link rel="stylesheet" href="${root}css/all.min.css">
    <link rel="stylesheet" href="${root}css/fontawesome.min.css">
    <link rel="stylesheet" href="${root}css/icheck-bootstrap.min.css">
    <link rel="stylesheet" href="${root}css/tempusdominus-bootstrap-4.min.css">
	<link href="${root}css/top_menu_2.css" rel="stylesheet" type="text/css" />
	<link href="${root}css/bottom.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${root}css/search_result.css">
</head>
<body>
    <c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>
    <div class="wrapper777">
        <div class="main-content777">
            <div id="searchForm" class="search-box777">
    			<h4 class="search-title777">상세 검색</h4>
                <form action="${root}search/results" method="get" onsubmit="removeEmptyFields(event)">
                    <div class="form-group">
                        <label for="book_name">책 이름</label>
                        <input type="text" name="book_name" class="form-control" placeholder="책 이름" value="${filter.book_name}">
                    </div>
                    <div class="form-group">
                        <label for="author">저자</label>
                        <input type="text" name="author" class="form-control" placeholder="저자" value="${filter.author}">
                    </div>
					<div class="form-group">
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
                    <div class="form-group">
                        <label for="publisher">출판사</label>
                        <input type="text" name="publisher" class="form-control" placeholder="출판사" value="${filter.publisher}">
                    </div>
                    <div class="form-group">
                        <label for="minPrice">최소 가격</label>
                        <input type="text" name="minPrice" class="form-control" placeholder="최소 가격" value="${filter.minPrice}">
                    </div>
                    <div class="form-group">
                        <label for="maxPrice">최대 가격</label>
                        <input type="text" name="maxPrice" class="form-control" placeholder="최대 가격" value="${filter.maxPrice}">
                    </div>
                    <div class="search-btn777">
                        <button type="submit" class="btn btn-default" style="width: 100%;"><i class="fas fa-search"></i></button>
                    </div>
                </form>
            </div>
            <div class="content-wrapper777">
                <section class="content777">
                    <div class="container777">
                        <h2>'<c:out value="${filter.keyword}"/>'의 검색 결과: <c:out value="${totalBooks}"/>권</h2><br>
                        <div class="card777">
                            <div class="card-header777">
                                <div class="card-tools777">
                                    <div class="search-header777">
                                        <div class="sort-buttons777">
                                            <a href="#" onclick="sortToggle('book_name')">책 이름 <i class="fas ${filter.sortField == 'book_name' && filter.sortOrder == 'asc' ? 'fa-sort-up' : filter.sortField == 'book_name' && filter.sortOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a>
                                            <a href="#" onclick="sortToggle('book_category')">카테고리 <i class="fas ${filter.sortField == 'book_category' && filter.sortOrder == 'asc' ? 'fa-sort-up' : filter.sortField == 'book_category' && filter.sortOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a>
                                            <a href="#" onclick="sortToggle('book_price')">가격 <i class="fas ${filter.sortField == 'book_price' && filter.sortOrder == 'asc' ? 'fa-sort-up' : filter.sortField == 'book_price' && filter.sortOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a>
                                            <a href="#" onclick="sortToggle('author')">저자 <i class="fas ${filter.sortField == 'author' && filter.sortOrder == 'asc' ? 'fa-sort-up' : filter.sortField == 'author' && filter.sortOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a>
                                            <a href="#" onclick="sortToggle('publisher')">출판사 <i class="fas ${filter.sortField == 'publisher' && filter.sortOrder == 'asc' ? 'fa-sort-up' : filter.sortField == 'publisher' && filter.sortOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body777 table-responsive777 p-0777">
                                <div class="book-list777">
                                    <c:forEach var="book" items="${bookList}">
                                        <div class="book-item777">
                                            <a href="${root}books/bookDetail?book_id=${book.book_id}">
                                                <img src="${book.book_pic}" alt="${book.book_name}" class="book-image777">
                                            </a>
                                            <div class="book-details777">
                                                <p class="book-title777">${book.book_name}</p>
                                                <hr>
                                                <p>판매가: <span class="book-price777">${book.book_price}</span>원 (${book.author})</p>
                                                <p>${book.book_info}</p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="card-footer777 clearfix777">
                                <ul class="pagination pagination-sm m-0 float-right">
                                    <c:choose>
                                        <c:when test="${pageBean.prevPage <= 0}">
                                            <li class="paginate_button page-item previous disabled"><a class="page-link" href="#">이전</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="paginate_button page-item previous"><a class="page-link" href="?page=${pageBean.prevPage}&${paramString}">이전</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="idx" begin="${pageBean.min}" end="${pageBean.max}">
                                        <li class="page-item ${pageBean.currentPage == idx ? 'active' : ''}">
                                            <a class="page-link" href="?page=${idx}&${paramString}">${idx}</a>
                                        </li>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${pageBean.max >= pageBean.pageCnt}">
                                            <li class="paginate_button page-item next disabled"><a class="page-link" href="#">다음</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="paginate_button page-item next"><a class="page-link" href="?page=${pageBean.nextPage}&${paramString}">다음</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>
    <script>
        function removeEmptyFields(event) {
            const inputs = document.querySelectorAll('#searchForm input, #searchForm select');
            inputs.forEach(input => {
                if (!input.value) {
                    input.name = '';
                }
            });
        }
        
        function sortToggle(field) {
            const urlParams = new URLSearchParams(window.location.search);
            const currentSortField = urlParams.get('sortField');
            const currentSortOrder = urlParams.get('sortOrder');
            let newSortOrder = 'asc';
            if (currentSortField === field) {
                if (currentSortOrder === 'asc') {
                    newSortOrder = 'desc';
                } else if (currentSortOrder === 'desc') {
                    newSortOrder = '';
                }
            }
            urlParams.set('sortField', field);
            urlParams.set('sortOrder', newSortOrder);
            window.location.search = urlParams.toString();
        }
    </script>
    <script src="${root}js/adminlte.min.js"></script>
    <script src="${root}js/bootstrap.bundle.min.js"></script>
    <script src="${root}js/jquery.min.js"></script>
</body>
</html>
