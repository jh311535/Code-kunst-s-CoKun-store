<%-- File Location: /views/admin/board/1/modify.jsp --%>
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
                            <h3 class="card-title">자유 게시글 수정</h3>
                        </div>
                        <div class="card-body">
                            <form action="${root}admin/board/1/modify" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="board_id" value="${board.board_id}">
                                <div class="form-group">
                                    <label for="board_title">제목</label>
                                    <input type="text" id="board_title" name="board_title" class="form-control" value="${board.board_title}" required>
                                </div>
                                <div class="form-group">
                                    <label for="board_content">내용</label>
                                    <textarea id="board_content" name="board_content" class="form-control" rows="5" required>${board.board_content}</textarea>
                                </div>
	                            <div class="form-group">
	                                <label for="board_date">게시 날짜</label>
	                                <input type="text" id="board_date" name="board_date" class="form-control" value="${board.formattedDate}" readonly>
	                            </div>
                                <div class="form-group">
                                    <label for="current_pic">현재 사진</label>
                                    <div>
                                        <c:choose>
                                            <c:when test="${board.board_pic != null}">
                                                <img src="${root}upload/${board.board_pic}" alt="사진" class="img-thumbnail" style="max-width: 150px;">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" id="delete_pic" name="delete_pic" value="true">
                                                    <label class="form-check-label" for="delete_pic">사진 삭제</label>
                                                </div>
                                            </c:when>
                                            <c:otherwise> X </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="board_pic">새로운 사진</label>
                                    <input type="file" id="board_pic" name="upload_file" class="form-control" />
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

