<%-- File Location: /views/admin/order/foreign_key.jsp --%>
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
                <h1>주문 외래키 검색</h1>
                <div class="card">
                    <div class="card-header">
                        <div class="card-tools">
                            <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                        </div>
                        <div id="searchForm" style="display:none; margin-top:10px;">
                            <form action="${root}admin/order/search?returnJSP=foreignJSP" method="post" onsubmit="removeEmptyFields(event)">
                                <div class="input-group input-group-sm" style="width: 100%;">
                                    <div class="form-group" style="width: 100%;">
                                        <label for="order_id">주문 ID</label>
                                        <input type="text" name="order_id" class="form-control" placeholder="주문 ID" value="${filter.order_id}">
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
                                        <label for="orderer_name">수령인</label>
                                        <input type="text" name="orderer_name" class="form-control" placeholder="수령인" value="${filter.orderer_name}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="order_number">주문 번호</label>
                                        <input type="text" name="order_number" class="form-control" placeholder="주문 번호" value="${filter.order_number}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="phone">전화번호</label>
                                        <input type="text" name="phone" class="form-control" placeholder="전화번호" value="${filter.phone}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="email">이메일</label>
                                        <input type="email" name="email" class="form-control" placeholder="이메일" value="${filter.email}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="delivery_state">주문 상태</label>
                                        <select name="delivery_state" class="form-control">
                                            <option value="">전체</option>
                                            <option value="배송 준비중" ${filter.delivery_state == '배송 준비중' ? 'selected' : ''}>배송 준비중</option>
                                            <option value="환불 대기" ${filter.delivery_state == '환불 대기' ? 'selected' : ''}>환불 대기</option>
                                            <option value="교환 대기" ${filter.delivery_state == '교환 대기' ? 'selected' : ''}>교환 대기</option>
                                            <option value="반품 대기" ${filter.delivery_state == '반품 대기' ? 'selected' : ''}>반품 대기</option>
                                            <option value="환불 완료" ${filter.delivery_state == '환불 완료' ? 'selected' : ''}>환불 완료</option>
                                            <option value="교환 완료" ${filter.delivery_state == '교환 완료' ? 'selected' : ''}>교환 완료</option>
                                            <option value="반품 완료" ${filter.delivery_state == '반품 완료' ? 'selected' : ''}>반품 완료</option>
                                            <option value="배송 완료" ${filter.delivery_state == '배송 완료' ? 'selected' : ''}>배송 완료</option>
                                        </select>
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="order_quantity_min">최소 수량</label>
                                        <input type="text" name="order_quantity_min" class="form-control" placeholder="최소 수량" value="${filter.order_quantity_min}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="order_quantity_max">최대 수량</label>
                                        <input type="text" name="order_quantity_max" class="form-control" placeholder="최대 수량" value="${filter.order_quantity_max}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="order_price_min">최소 가격</label>
                                        <input type="text" name="order_price_min" class="form-control" placeholder="최소 가격" value="${filter.order_price_min}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="order_price_max">최대 가격</label>
                                        <input type="text" name="order_price_max" class="form-control" placeholder="최대 가격" value="${filter.order_price_max}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="startDate">주문 시작일</label>
                                        <input type="date" name="startDate" class="form-control" placeholder="주문 시작일" value="${filter.startDate}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="endDate">주문 종료일</label>
                                        <input type="date" name="endDate" class="form-control" placeholder="주문 종료일" value="${filter.endDate}">
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
                                    <th><a href="${root}admin/order/sort?returnJSP=foreignJSP&sortField=orderId">ID <i class="fas ${filter.orderIdOrder == 'asc' ? 'fa-sort-up' : filter.orderIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/order/sort?returnJSP=foreignJSP&sortField=orderNumber">주문 번호 <i class="fas ${filter.orderNumberOrder == 'asc' ? 'fa-sort-up' : filter.orderNumberOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/order/sort?returnJSP=foreignJSP&sortField=userIdx">유저 ID <i class="fas ${filter.userIdxOrder == 'asc' ? 'fa-sort-up' : filter.userIdxOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/order/sort?returnJSP=foreignJSP&sortField=bookId">책 ID <i class="fas ${filter.bookIdOrder == 'asc' ? 'fa-sort-up' : filter.bookIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/order/sort?returnJSP=foreignJSP&sortField=orderQuantity">수량 <i class="fas ${filter.orderQuantityOrder == 'asc' ? 'fa-sort-up' : filter.orderQuantityOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/order/sort?returnJSP=foreignJSP&sortField=orderPrice">가격 <i class="fas ${filter.orderPriceOrder == 'asc' ? 'fa-sort-up' : filter.orderPriceOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/order/sort?returnJSP=foreignJSP&sortField=orderDate">주문일 <i class="fas ${filter.orderDateOrder == 'asc' ? 'fa-sort-up' : filter.orderDateOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>선택</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${orderList}">
                                    <tr>
                                        <td>${order.order_id}</td>
                                        <td>${order.order_number}</td>
                                        <td>${order.user_idx}</td>
                                        <td>${order.book_id}</td>
                                        <td>${order.order_quantity}</td>
                                        <td>${order.order_price}</td>
                                        <td>${order.formattedDate}</td>
                                        <td>
                                            <button class="btn btn-primary btn-sm" onclick="selectOrder(${order.order_id})">선택</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty orderList}">
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
</div>
<script>
    function selectOrder(orderId) {
        if (window.opener && !window.opener.closed) {
            window.opener.selectOrder(orderId); // 부모 창의 selectOrder 함수 호출
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
