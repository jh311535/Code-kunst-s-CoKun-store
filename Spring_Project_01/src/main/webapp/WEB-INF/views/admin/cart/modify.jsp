<%-- File Location: /views/admin/cart/modify.jsp --%>
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
                        <h3 class="card-title">장바구니 수정</h3>
                    </div>
                    <div class="card-body">
                        <div class="form-group">
                            <label for="book_name">책 이름</label>
                            <input type="text" id="book_name" name="book_name" class="form-control" value="${bookName}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="book_pic">책 사진</label>
                            <img src="${bookPic}" alt="책 사진" class="img-thumbnail" style="max-width: 150px;">
                        </div>
                        <form action="${root}admin/cart/modify" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="cart_id" value="${cart.cart_id}">
                            <div class="form-group">
                                <label for="cart_quantity">담긴 수량</label>
                                <input type="number" id="cart_quantity" name="cart_quantity" class="form-control" value="${cart.cart_quantity}" required>
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
