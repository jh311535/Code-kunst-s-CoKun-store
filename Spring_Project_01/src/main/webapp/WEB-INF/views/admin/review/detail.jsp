<%-- File Location: /views/admin/review/detail.jsp --%>
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
                        <h3 class="card-title">리뷰 상세 정보</h3>
                    </div>
                    <div class="card-body">
                        <div class="form-group">
                            <label for="review_id">리뷰 ID</label>
                            <input type="text" id="review_id" name="review_id" class="form-control" value="${review.review_id}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="user_idx">유저 ID</label>
                            <input type="text" id="user_idx" name="user_idx" class="form-control" value="${review.user_idx}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="userNickname">유저 닉네임</label>
                            <input type="text" id="userNickname" name="userNickname" class="form-control" value="${userNickname}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="book_id">책 ID</label>
                            <input type="text" id="book_id" name="book_id" class="form-control" value="${review.book_id}" readonly>
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
                            <label for="rating">별점</label>
                            <input type="text" id="rating" name="rating" class="form-control" value="${review.rating}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="review_content">리뷰 내용</label>
                            <textarea id="review_content" name="review_content" class="form-control" rows="3" readonly>${review.review_content}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="review_date">작성 날짜</label>
                            <input type="text" id="review_date" name="review_date" class="form-control" value="${review.review_date}" readonly>
                        </div>
                        <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로 가기</button>
                        <button type="button" class="btn btn-info" onclick="location.href='${root}admin/review/modify/${review.review_id}'">수정</button>
                        <button type="button" class="btn btn-danger" onclick="confirmDelete(${review.review_id})">삭제</button>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
</div>
<script>
    function confirmDelete(reviewId) {
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '${root}admin/review/delete',
                type: 'POST',
                data: { id: reviewId },
                success: function(response) {
                    alert('삭제되었습니다.');
                    window.location.href = '${root}admin/review/manage';
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
