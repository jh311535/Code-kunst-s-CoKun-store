<%-- File Location: /views/admin/admin_info/foreign_key.jsp --%>
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
    <style>
        .content-wrapper {
            margin-left: 0 !important;
            padding: 20px;
        }
        .container-fluid {
            padding: 0;
        }
    </style>
</head>
<body class="hold-transition">
<div class="wrapper">
    <div class="content-wrapper">
        <section class="content">
            <div class="container-fluid">
                <h1>관리자 외래키 검색</h1>
                <div class="card">
                    <div class="card-header">
                        <div class="card-tools">
                            <button type="button" class="btn btn-secondary btn-sm" onclick="toggleSearch()">상세 검색</button>
                        </div>
                        <div id="searchForm" style="display:none; margin-top:10px;">
                            <form action="${root}admin/adminInfo/search?returnJSP=foreignJSP" method="post" onsubmit="removeEmptyFields(event)">
                                <div class="input-group input-group-sm" style="width: 100%;">
                                    <div class="form-group" style="width: 100%;">
                                        <label for="admin_name">이름</label>
                                        <input type="text" name="admin_name" class="form-control" placeholder="이름" value="${filter.admin_name}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="admin_nickname">닉네임</label>
                                        <input type="text" name="admin_nickname" class="form-control" placeholder="닉네임" value="${filter.admin_nickname}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="login_id">로그인 ID</label>
                                        <input type="text" name="login_id" class="form-control" placeholder="로그인 ID" value="${filter.login_id}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="admin_type">관리자 등급</label>
                                        <select name="admin_type" class="form-control">
                                            <option value="">전체</option>
                                            <option value="level1" ${filter.admin_type == 'level1' ? 'selected' : ''}>level1</option>
                                            <option value="level2" ${filter.admin_type == 'level2' ? 'selected' : ''}>level2</option>
                                            <option value="level3" ${filter.admin_type == 'level3' ? 'selected' : ''}>level3</option>
                                            <option value="withdraw" ${filter.admin_type == 'withdraw' ? 'selected' : ''}>withdraw</option>
                                        </select>
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="phone">전화번호</label>
                                        <input type="text" name="phone" class="form-control" placeholder="전화번호" value="${filter.phone}">
                                    </div>
                                    <div class="form-group" style="width: 100%;">
                                        <label for="email">이메일</label>
                                        <input type="text" name="email" class="form-control" placeholder="이메일" value="${filter.email}">
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
                                    <th><a href="${root}admin/adminInfo/sort?returnJSP=foreignJSP&sortField=adminIdx">ID <i class="fas ${filter.adminIdxOrder == 'asc' ? 'fa-sort-up' : filter.adminIdxOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/adminInfo/sort?returnJSP=foreignJSP&sortField=adminName">이름 <i class="fas ${filter.adminNameOrder == 'asc' ? 'fa-sort-up' : filter.adminNameOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/adminInfo/sort?returnJSP=foreignJSP&sortField=nickName">닉네임 <i class="fas ${filter.nickNameOrder == 'asc' ? 'fa-sort-up' : filter.nickNameOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/adminInfo/sort?returnJSP=foreignJSP&sortField=loginId">로그인 ID <i class="fas ${filter.loginIdOrder == 'asc' ? 'fa-sort-up' : filter.loginIdOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/adminInfo/sort?returnJSP=foreignJSP&sortField=type">관리자 권한 <i class="fas ${filter.adminTypeOrder == 'asc' ? 'fa-sort-up' : filter.adminTypeOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/adminInfo/sort?returnJSP=foreignJSP&sortField=phone">전화번호 <i class="fas ${filter.phoneOrder == 'asc' ? 'fa-sort-up' : filter.phoneOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th><a href="${root}admin/adminInfo/sort?returnJSP=foreignJSP&sortField=email">이메일 <i class="fas ${filter.emailOrder == 'asc' ? 'fa-sort-up' : filter.emailOrder == 'desc' ? 'fa-sort-down' : 'fa-sort'}"></i></a></th>
                                    <th>선택</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="admin" items="${adminList}">
                                    <tr>
                                        <td>${admin.admin_idx}</td>
                                        <td>${admin.admin_name}</td>
                                        <td>${admin.admin_nickname}</td>
                                        <td>${admin.login_id}</td>
                                        <td>${admin.phone}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <button class="btn btn-primary btn-sm" onclick="selectAdmin(${admin.admin_idx})">선택</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty adminList}">
                                    <tr>
                                        <td colspan="7" class="text-center">데이터 없음</td>
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
</div>
<script>
    function selectAdmin(adminIdx) {
        if (window.opener && !window.opener.closed) {
            window.opener.selectAdmin(adminIdx); // 부모 창의 selectAdmin 함수 호출
            window.close(); // 현재 창 닫기
        } else {
            alert('부모 창을 찾을 수 없습니다.');
        }
    }
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
</script>
<script src="${root}js/adminlte.min.js"></script>
<script src="${root}js/bootstrap.bundle.min.js"></script>
<script src="${root}js/jquery.min.js"></script>
<script src="${root}js/locales-all.min.js"></script>
<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
