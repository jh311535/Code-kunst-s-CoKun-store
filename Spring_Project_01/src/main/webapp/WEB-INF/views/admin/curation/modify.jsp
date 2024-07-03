<%-- File Location: /views/admin/curation/modify.jsp --%>
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
                        <h3 class="card-title">큐레이션 수정</h3>
                    </div>
                    <div class="card-body">
                        <form action="${root}admin/curation/modify" method="post">
                            <input type="hidden" name="curation_id" value="${curation.curation_id}">
                            <div class="form-group">
                                <label for="curation_category">카테고리</label>
                                <select id="curation_category" name="curation_category" class="form-control" required>
                                    <option value="건강" ${curation.curation_category == '건강' ? 'selected' : ''}>건강</option>
                                    <option value="공예" ${curation.curation_category == '공예' ? 'selected' : ''}>공예</option>
                                    <option value="농구" ${curation.curation_category == '농구' ? 'selected' : ''}>농구</option>
                                    <option value="야구" ${curation.curation_category == '야구' ? 'selected' : ''}>야구</option>
                                    <option value="축구" ${curation.curation_category == '축구' ? 'selected' : ''}>축구</option>
                                    <option value="수영" ${curation.curation_category == '수영' ? 'selected' : ''}>수영</option>
                                    <option value="유럽여행" ${curation.curation_category == '유럽여행' ? 'selected' : ''}>유럽여행</option>
                                    <option value="일본여행" ${curation.curation_category == '일본여행' ? 'selected' : ''}>일본여행</option>
                                    <option value="중국요리" ${curation.curation_category == '중국요리' ? 'selected' : ''}>중국요리</option>
                                    <option value="다이어트 요리" ${curation.curation_category == '다이어트 요리' ? 'selected' : ''}>다이어트 요리</option>
                                    <option value="사찰요리" ${curation.curation_category == '사찰요리' ? 'selected' : ''}>사찰요리</option>
                                    <option value="생활요리" ${curation.curation_category == '생활요리' ? 'selected' : ''}>생활요리</option>
                                    <option value="서양음악" ${curation.curation_category == '서양음악' ? 'selected' : ''}>서양음악</option>
                                    <option value="재즈" ${curation.curation_category == '재즈' ? 'selected' : ''}>재즈</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="product_name">제품 이름</label>
                                <input type="text" id="product_name" name="product_name" class="form-control" value="${curation.product_name}" required>
                            </div>
                            <div class="form-group">
                                <label for="product_pic">제품 사진</label>
                                <div>
                                    <c:choose>
                                        <c:when test="${curation.product_pic != null}">
                                            <img src="${curation.product_pic}" alt="이미지" class="img-thumbnail" style="max-width: 150px;">
                                        </c:when>
                                        <c:otherwise> X </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="product_pic">제품 사진 URL</label>
                                <input type="text" id="product_pic" name="product_pic" class="form-control" value="${curation.product_pic}" required>
                            </div>
                            <div class="form-group">
                                <label for="product_url">제품 링크</label>
                                <input type="text" id="product_url" name="product_url" class="form-control" value="${curation.product_url}" required>
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
