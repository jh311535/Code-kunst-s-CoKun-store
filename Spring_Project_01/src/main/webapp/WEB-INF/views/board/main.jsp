<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<link href="${root}css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="${root}css/bottom.css" rel="stylesheet" type="text/css" />
<style>
    body {
        /* font-family: Arial, sans-serif; */
    }

    .container {
        margin-top: 70px;
        margin-bottom: 70px;
        width: 100%;
        max-width: 1200px;
        margin-left: auto;
        margin-right: auto;
    }

    .card {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border: 1px solid #e0e0e0;
        border-radius: 0.25rem;
        background-color: #fff;
        margin-bottom: 20px;
    }

    .card-body {
        padding: 1.25rem;
    }

    .card-title {
        font-size: 1.5rem;
        margin-bottom: 1.25rem;
    }

    .table {
        width: 100%;
        margin-bottom: 1rem;
        background-color: transparent;
        border-collapse: collapse;
    }

    .table-hover tbody tr:hover {
        background-color: rgba(0, 0, 0, 0.075);
    }

    .table th,
    .table td {
        padding: 0.75rem;
        vertical-align: top;
        border-top: 1px solid #dee2e6;
    }

    .table thead th {
        vertical-align: bottom;
        border-bottom: 2px solid #dee2e6;
    }

    .table tbody + tbody {
        border-top: 2px solid #dee2e6;
    }

    .text-center {
        text-align: center;
    }

    .w-50 {
        width: 50%;
    }

    .pagination {
        display: flex;
        padding-left: 0;
        list-style: none;
        border-radius: 0.25rem;
        justify-content: center;
    }

    .page-item {
        margin: 0 2px;
    }

    .page-item.disabled .page-link {
        color: #fff;
        pointer-events: none;
        background-color: #28a745;
        border: 1px solid #dee2e6;
    }

    .page-item.active .page-link {
        z-index: 1;
        color: #fff;
        background-color: #28a745;
        border-color: #28a745;
    }

    .page-link {
        position: relative;
        display: block;
        color: #28a745;
        text-decoration: none;
        background-color: #fff;
        border: 1px solid #28a745;
        padding: 0.5rem 0.75rem;
        margin-left: -1px;
        line-height: 1.25;
        border-radius: 0.25rem;
        transition: all 0.15s ease-in-out;
    }

    .page-link:hover {
        color: #fff;
        background-color: #218838;
        border-color: #218838;
    }

    .text-right {
        text-align: right;
    }

    .btn {
        display: inline-block;
        font-weight: 400;
        color: #28a745;
        text-align: center;
        vertical-align: middle;
        user-select: none;
        background-color: #28a745;
        border: 1px solid #28a745;
        padding: 0.375rem 0.75rem;
        font-size: 1rem;
        line-height: 1.5;
        border-radius: 0.25rem;
        transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        color: #fff;
        text-decoration: none;
    }

    .btn:hover {
        color: #fff;
        background-color: #218838;
        border-color: #218838;
    }

    .atag {
        color: black !important;
        font-weight: normal !important;
        text-decoration: none !important; /* 밑줄 없애기 */
    }

    .atag:hover {
        color: black !important;
    }

    .btn-container {
        display: flex;
        justify-content: space-between;
    }
</style>

</head>
<body>
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<!-- 게시글 리스트 -->
<div class="container">
    <div class="card shadow">
        <div class="card-body">
            <h4 class="card-title">${boardInfoName}</h4>
            <table class="table table-hover" id='board_list'>
                <thead>
                    <tr>
                        <th class="text-center d-none d-md-table-cell">글번호</th>
                        <th class="w-50">제목</th>
                        <th class="text-center d-none d-md-table-cell">작성자</th>
                        <th class="text-center d-none d-md-table-cell">조회수</th>
                        <th class="text-center d-none d-md-table-cell">작성날짜</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="obj" items="${boardList}">
                    <tr>
                        <td class="text-center d-none d-md-table-cell">${obj.board_id}</td>
                        <td><a href="${root}board/read${board_info_idx}?board_info_idx=${board_info_idx}&board_id=${obj.board_id}&page=${page}" class="atag">${obj.board_title}</a></td> <%--이 page를 가지고 board/read 로 넘어감--%>
                        <td class="text-center d-none d-md-table-cell">${obj.user_nickname}</td>
                        <td class="text-center d-none d-md-table-cell">${obj.views}</td>
                        <td class="text-center d-none d-md-table-cell">${obj.board_date}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            
            <div class="d-block d-md-block">
                <ul class="pagination justify-content-center">
                    <c:choose>
                        <c:when test="${pageBean.prevPage <= 0}">
                            <li class="page-item disabled">
                                <a href="#" class="page-link">이전</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a href="${root}board/main?board_info_idx=${board_info_idx}&page=${pageBean.prevPage}" class="page-link">이전</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach var="idx" begin="${pageBean.min}" end="${pageBean.max}">
                        <c:choose>
                            <c:when test="${idx == pageBean.currentPage}">
                                <!--  현재 페이지 색변경(active) -->
                                <li class="page-item active">
                                    <a href="${root}board/main?board_info_idx=${board_info_idx}&page=${idx}" class="page-link">${idx}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a href="${root}board/main?board_info_idx=${board_info_idx}&page=${idx}" class="page-link">${idx}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${pageBean.max >= pageBean.pageCnt}">
                            <li class="page-item disabled">
                                <a href="#" class="page-link">다음</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a href="${root}board/main?board_info_idx=${board_info_idx}&page=${pageBean.nextPage}" class="page-link">다음</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>

            <div class="text-right btn-container">
                <%-- <a href="${root}board/home" class="btn">메인으로</a> --%>
                <a href="${root}board/write?board_info_idx=${board_info_idx}" class="btn">글쓰기</a>
            </div>
            
        </div>
    </div>
</div>
<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>


</body>
</html>
