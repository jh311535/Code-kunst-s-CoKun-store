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
                        <h3 class="card-title">책 추가</h3>
                    </div>
                    <div class="card-body">
                        <form action="${root}admin/book/add" method="post">
                            <div class="form-group">
                                <label for="book_name">책 이름</label>
                                <input type="text" id="book_name" name="book_name" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="isbn">ISBN</label>
                                <input type="text" id="isbn" name="isbn" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="book_price">가격</label>
                                <input type="text" id="book_price" name="book_price" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="publish_date">출판일</label>
                                <input type="date" id="publish_date" name="publish_date" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="book_pic">책 사진(URL)</label>
                                <input type="text" id="book_pic" name="book_pic" class="form-control" value="https://i.ibb.co/6sH4Jhj/book.png" required>
                            </div>
                            <div class="form-group">
                                <label for="book_info">책 정보</label>
                                <textarea id="book_info" name="book_info" class="form-control" rows="5" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="inventory">재고</label>
                                <input type="text" id="inventory" name="inventory" class="form-control" value="100" required>
                            </div>
                            <div class="form-group">
                                <label for="publisher">출판사</label>
                                <input type="text" id="publisher" name="publisher" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="book_category">카테고리</label>
                                <select id="book_category" name="book_category" class="form-control" required>
                                    <option value="">전체</option>
                                    <option value="건강">건강</option>
                                    <option value="공예">공예</option>
                                    <option value="농구">농구</option>
                                    <option value="야구">야구</option>
                                    <option value="축구">축구</option>
                                    <option value="수영">수영</option>
                                    <option value="유럽여행">유럽여행</option>
                                    <option value="일본여행">일본여행</option>
                                    <option value="중국요리">중국요리</option>
                                    <option value="다이어트 요리">다이어트 요리</option>
                                    <option value="사찰요리">사찰요리</option>
                                    <option value="생활요리">생활요리</option>
                                    <option value="서양음악">서양음악</option>
                                    <option value="재즈">재즈</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="author">저자</label>
                                <input type="text" id="author" name="author" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-primary">추가</button>
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
