<%-- File Location: /views/admin/curation/manage.jsp --%>
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
	<script src="${root}js/adminlte.min.js"></script>
	<script src="${root}js/bootstrap.bundle.min.js"></script>
	<script src="${root}js/jquery.min.js"></script>
	<script src="${root}js/locales-all.min.js"></script>
	<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
    <style>
        .break-word {
            word-break: break-all;
            max-width: 200px; /* 최대 width */
            white-space: normal;
        }
    </style>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <c:import url="/WEB-INF/views/admin_include/top_menu.jsp"></c:import>
    <c:import url="/WEB-INF/views/admin_include/left_menu.jsp"></c:import>
    <div class="content-wrapper">
        <section class="content">
            <div class="container-fluid">
                <h1>큐레이션 관리</h1>
                <div class="card">
                    <div class="card-header">
                        <div class="card-tools">
                            <button type="button" class="btn btn-primary btn-sm" onclick="location.href='${root}admin/curation/add'">큐레이션 추가</button>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                        </div>
                        <div id="searchForm" style="display:none; margin-top:10px;">
                            <form action="${root}admin/curation/search?returnJSP=manageJSP" method="post" onsubmit="removeEmptyFields(event)">
                                <div class="input-group input-group-sm" style="width: 100%;">
                                    <div class="form-group" style="width: 100%;">
                                        <label for="curation_category">카테고리</label>
                                        <select name="curation_category" class="form-control">
                                            <option value="">전체</option>
                                            <option value="건강" ${filter.curation_category == '건강' ? 'selected' : ''}>건강</option>
                                            <option value="공예" ${filter.curation_category == '공예' ? 'selected' : ''}>공예</option>
                                            <option value="농구" ${filter.curation_category == '농구' ? 'selected' : ''}>농구</option>
                                            <option value="야구" ${filter.curation_category == '야구' ? 'selected' : ''}>야구</option>
                                            <option value="축구" ${filter.curation_category == '축구' ? 'selected' : ''}>축구</option>
                                            <option value="수영" ${filter.curation_category == '수영' ? 'selected' : ''}>수영</option>
                                            <option value="유럽여행" ${filter.curation_category == '유럽여행' ? 'selected' : ''}>유럽여행</option>
                                            <option value="일본여행" ${filter.curation_category == '일본여행' ? 'selected' : ''}>일본여행</option>
                                            <option value="중국요리" ${filter.curation_category == '중국요리' ? 'selected' : ''}>중국요리</option>
                                            <option value="다이어트 요리" ${filter.curation_category == '다이어트 요리' ? 'selected' : ''}>다이어트 요리</option>
                                            <option value="사찰요리" ${filter.curation_category == '사찰요리' ? 'selected' : ''}>사찰요리</option>
                                            <option value="생활요리" ${filter.curation_category == '생활요리' ? 'selected' : ''}>생활요리</option>
                                            <option value="서양음악" ${filter.curation_category == '서양음악' ? 'selected' : ''}>서양음악</option>
                                            <option value="재즈" ${filter.curation_category == '재즈' ? 'selected' : ''}>재즈</option>
                                        </select>
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="product_name">제품 이름</label>
                                        <input type="text" name="product_name" class="form-control" placeholder="제품 이름" value="${filter.product_name}">
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
                                    <th><a href="${root}admin/curation/sort?returnJSP=manageJSP&sortField=curationId">ID <i class="fas ${filter.curationIdOrder == 'asc' ? 'fa-sort-up' : filter.curationIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>관리</th>
                                    <th>이미지</th>
                                    <th><a href="${root}admin/curation/sort?returnJSP=manageJSP&sortField=curationCategory">카테고리 <i class="fas ${filter.curationCategoryOrder == 'asc' ? 'fa-sort-up' : filter.curationCategoryOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/curation/sort?returnJSP=manageJSP&sortField=productName">제품 이름 <i class="fas ${filter.productNameOrder == 'asc' ? 'fa-sort-up' : filter.productNameOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/curation/sort?returnJSP=manageJSP&sortField=productUrl">URL <i class="fas ${filter.productUrlOrder == 'asc' ? 'fa-sort-up' : filter.productUrlOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="curation" items="${curationList}">
                                    <tr>
                                        <td><a href="${root}admin/curation/detail/${curation.curation_id}">${curation.curation_id}</a></td>
                                        <td>
                                            <button class="btn btn-info btn-sm" onclick="location.href='${root}admin/curation/modify/${curation.curation_id}'">수정</button>
                                            <button class="btn btn-danger btn-sm" onclick="confirmDelete(${curation.curation_id})">삭제</button>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${curation.product_pic != null}">
                                                    <img src="${curation.product_pic}" alt="이미지" class="img-thumbnail" style="max-width: 150px;">
                                                </c:when>
                                                <c:otherwise> X </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${curation.curation_category}</td>
                                        <td class="break-word">${curation.product_name}</td>
                                        <td class="break-word"><a href="${curation.product_url}" target="_blank">${curation.product_url}</a></td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty curationList}">
                                    <tr>
                                        <td colspan="6" class="text-center">데이터 없음</td>
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
    <c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
</div>
<script>
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
    function confirmDelete(curationId) {
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '${root}admin/curation/delete',
                type: 'POST',
                data: { id: curationId },
                success: function(response) {
                    alert('삭제되었습니다.');
                    location.reload();
                },
                error: function() {
                    alert('삭제에 실패했습니다.');
                }
            });
        }
    }
</script>
</body>
</html>
