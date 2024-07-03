<%-- File Location: /views/admin/order/detail.jsp --%>
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
                        <h3 class="card-title">주문 상세보기</h3>
                    </div>
                    <div class="card-body">
                        <div class="form-group">
                            <label for="order_id">주문 ID</label>
                            <input type="text" id="order_id" name="order_id" class="form-control" value="${order.order_id}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="user_idx">유저 ID</label>
                            <input type="text" id="user_idx" name="user_idx" class="form-control" value="${order.user_idx}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="book_id">책 ID</label>
                            <input type="text" id="book_id" name="book_id" class="form-control" value="${order.book_id}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="book_name">책 이름</label>
                            <input type="text" id="book_name" name="book_name" class="form-control" value="${bookName}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="book_pic">책 사진</label>
                            <img src="${bookPic}" alt="책 사진" class="img-thumbnail" style="max-width: 150px;">
                        </div>
                        <div class="form-group">
                            <label for="order_number">주문 번호</label>
                            <input type="text" id="order_number" name="order_number" class="form-control" value="${order.order_number}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="order_quantity">주문 수량</label>
                            <input type="text" id="order_quantity" name="order_quantity" class="form-control" value="${order.order_quantity}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="order_price">주문 가격</label>
                            <input type="text" id="order_price" name="order_price" class="form-control" value="${order.order_price}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="orderer_name">수령인</label>
                            <input type="text" id="orderer_name" name="orderer_name" class="form-control" value="${order.orderer_name}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="addressnum">우편번호</label>
                            <input type="text" id="addressnum" name="addressnum" class="form-control" value="${order.addressnum}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="address">도로명 주소</label>
                            <input type="text" id="address" name="address" class="form-control" value="${order.address}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="detailaddress">상세 주소</label>
                            <input type="text" id="detailaddress" name="detailaddress" class="form-control" value="${order.detailaddress}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="phone">전화번호</label>
                            <input type="text" id="phone" name="phone" class="form-control" value="${order.phone}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="email">이메일</label>
                            <input type="text" id="email" name="email" class="form-control" value="${order.email}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="request">수령장소</label>
                            <input type="text" id="request" name="request" class="form-control" value="${order.request}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="request_text">배송 메모</label>
                            <textarea id="request_text" name="request_text" class="form-control" rows="3" readonly>${order.request_text}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="delivery_state">주문 상태</label>
                            <input type="text" id="delivery_state" name="delivery_state" class="form-control" value="${order.delivery_state}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="order_date">주문일</label>
                            <input type="text" id="order_date" name="order_date" class="form-control" value="${order.formattedDate}" readonly>
                        </div>
                        <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로 가기</button>
                        <button type="button" class="btn btn-info" onclick="location.href='${root}admin/order/modify/${order.order_id}'">수정</button>
                        <button type="button" class="btn btn-danger" onclick="confirmDelete(${order.order_id})">삭제</button>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
</div>
<script>
    function confirmDelete(orderId) {
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '${root}admin/order/delete',
                type: 'POST',
                data: { id: orderId },
                success: function(response) {
                    alert('삭제되었습니다.');
                    window.location.href = '${root}admin/order/manage';
                },
                error: function() {
                    alert('삭제에 실패했습니다.');
                }
            });
        }
    }
</script>
<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
