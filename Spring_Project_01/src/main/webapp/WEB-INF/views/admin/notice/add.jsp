<%-- File Location: /views/admin/notice/add.jsp --%>
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
                        <h3 class="card-title">공지 추가</h3>
                    </div>
                    <div class="card-body">
                        <form action="${root}admin/notice/add" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="admin_idx">관리자 ID</label>
                                <input type="text" id="admin_idx" name="admin_idx" class="form-control" value="${adminIdx}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="notice_title">제목</label>
                                <input type="text" id="notice_title" name="notice_title" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="notice_content">내용</label>
                                <textarea id="notice_content" name="notice_content" class="form-control" rows="5" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="notice_pic">사진</label>
                                <input type="file" id="notice_pic" name="upload_file" class="form-control" />
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
