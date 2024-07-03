<%-- File Location: /views/admin/curation/detail.jsp --%>
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
                            <h3 class="card-title">큐레이션 상세보기</h3>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <label for="curation_category">카테고리</label>
                                <input type="text" id="curation_category" name="curation_category" class="form-control" value="${curation.curation_category}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="product_name">제품 이름</label>
                                <input type="text" id="product_name" name="product_name" class="form-control" value="${curation.product_name}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="product_url">제품 URL</label>
                                <input type="text" id="product_url" name="product_url" class="form-control" value="${curation.product_url}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="current_pic">제품 이미지</label>
                                <div>
                                    <c:choose>
                                        <c:when test="${curation.product_pic != null}">
                                            <img src="${curation.product_pic}" alt="이미지" class="img-thumbnail" style="max-width: 150px;">
                                        </c:when>
                                        <c:otherwise> X </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로 가기</button>
                            <button type="button" class="btn btn-info" onclick="location.href='${root}admin/curation/modify/${curation.curation_id}'">수정</button>
                            <button type="button" class="btn btn-danger" onclick="confirmDelete(${curation.curation_id})">삭제</button>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
    </div>
    <script>
        function confirmDelete(curationId) {
            if (confirm('정말 삭제하시겠습니까?')) {
                $.ajax({
                    url: '${root}admin/curation/delete',
                    type: 'POST',
                    data: { id: curationId },
                    success: function(response) {
                        alert('삭제되었습니다.');
                        window.location.href = '${root}admin/curation/manage';
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
