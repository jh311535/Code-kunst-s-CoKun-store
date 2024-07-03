<%-- File Location: /views/admin/cart/manage.jsp --%>
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
                <h1>장바구니 관리</h1>
                <div class="card">
                    <div class="card-header">
                        <div class="card-tools">
                            <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                        </div>
                        <div id="searchForm" style="display:none; margin-top:10px;">
                            <form action="${root}admin/cart/search?returnJSP=manageJSP" method="post" onsubmit="removeEmptyFields(event)">
                                <div class="input-group input-group-sm" style="width: 100%;">
                                    <div class="form-group" style="width: 100%;">
                                        <label for="cart_id">장바구니 ID</label>
                                        <input type="text" name="cart_id" class="form-control" placeholder="장바구니 ID" value="${filter.cart_id}">
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
                                        <label for="cart_quantity_min">최소 수량</label>
                                        <input type="text" name="cart_quantity_min" class="form-control" placeholder="최소 수량" value="${filter.cart_quantity_min}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="cart_quantity_max">최대 수량</label>
                                        <input type="text" name="cart_quantity_max" class="form-control" placeholder="최대 수량" value="${filter.cart_quantity_max}">
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
                                    <th><a href="${root}admin/cart/sort?returnJSP=manageJSP&sortField=cartId">ID <i class="fas ${filter.cartIdOrder == 'asc' ? 'fa-sort-up' : filter.cartIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>관리</th>
                                	<th><a href="${root}admin/cart/sort?returnJSP=manageJSP&sortField=userIdx">유저 ID <i class="fas ${filter.userIdxOrder == 'asc' ? 'fa-sort-up' : filter.userIdxOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/cart/sort?returnJSP=manageJSP&sortField=bookId">책 ID <i class="fas ${filter.bookIdOrder == 'asc' ? 'fa-sort-up' : filter.bookIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>책 이름</th>
                                    <th>책 사진</th>
                                    <th><a href="${root}admin/cart/sort?returnJSP=manageJSP&sortField=cartQuantity">수량 <i class="fas ${filter.cartQuantityOrder == 'asc' ? 'fa-sort-up' : filter.cartQuantityOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                            	</tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cart" items="${cartList}">
                                    <tr>
                                        <td><a href="${root}admin/cart/detail/${cart.cart_id}">${cart.cart_id}</a></td>
                                        <td>
                                            <button class="btn btn-info btn-sm" onclick="location.href='${root}admin/cart/modify/${cart.cart_id}'">수정</button>
                                            <button class="btn btn-danger btn-sm" onclick="confirmDelete(${cart.cart_id})">삭제</button>
                                        </td>
                                        <td><a href="${root}admin/userInfo/detail/${cart.user_idx}">${cart.user_idx}</a></td>
                                        <td><a href="${root}admin/book/detail/${cart.book_id}">${cart.book_id}</a></td>
                                        <td class="break-word">${cart.book_name}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${cart.book_pic != null}">
                                                    <img src="${cart.book_pic}" alt="사진" class="img-thumbnail" style="max-width: 150px;">
                                                </c:when>
                                                <c:otherwise> X </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${cart.cart_quantity}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty cartList}">
                                    <tr>
                                        <td colspan="5" class="text-center">데이터 없음</td>
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
    function confirmDelete(cartId) {
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '${root}admin/cart/delete',
                type: 'POST',
                data: { id: cartId },
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
