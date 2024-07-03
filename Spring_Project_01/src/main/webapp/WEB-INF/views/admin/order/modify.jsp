<%-- File Location: /views/admin/order/modify.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                        <h3 class="card-title">주문 수정</h3>
                    </div>
                    <div class="card-body">
                        <form action="${root}admin/order/modify" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="order_id" value="${order.order_id}">
                            <div class="form-group">
                                <label for="order_quantity">주문 수량</label>
                                <input type="number" id="order_quantity" name="order_quantity" class="form-control" value="${order.order_quantity}" required>
                            </div>
                            <div class="form-group">
                                <label for="order_price">주문 가격</label>
                                <input type="number" id="order_price" name="order_price" class="form-control" value="${order.order_price}" required>
                            </div>
                            <div class="form-group">
                                <label for="request_text">배송 메모</label>
                                <textarea id="request_text" name="request_text" class="form-control" rows="3">${order.request_text}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="delivery_state">주문 상태</label>
                                <select id="delivery_state" name="delivery_state" class="form-control" required>
                                    <option value="배송 준비중" ${order.delivery_state == '배송 준비중' ? 'selected' : ''}>배송 준비중</option>
                                    <option value="환불 대기" ${order.delivery_state == '환불 대기' ? 'selected' : ''}>환불 대기</option>
                                    <option value="교환 대기" ${order.delivery_state == '교환 대기' ? 'selected' : ''}>교환 대기</option>
                                    <option value="반품 대기" ${order.delivery_state == '반품 대기' ? 'selected' : ''}>반품 대기</option>
                                    <option value="환불 완료" ${order.delivery_state == '환불 완료' ? 'selected' : ''}>환불 완료</option>
                                    <option value="교환 완료" ${order.delivery_state == '교환 완료' ? 'selected' : ''}>교환 완료</option>
                                    <option value="반품 완료" ${order.delivery_state == '반품 완료' ? 'selected' : ''}>반품 완료</option>
                                    <option value="배송 완료" ${order.delivery_state == '배송 완료' ? 'selected' : ''}>배송 완료</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">수정</button>
                            <button type="button" class="btn btn-secondary" onclick="history.back();">취소</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
</div>
<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
