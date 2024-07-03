<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<link href="${root}css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="${root}css/bottom.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<style>
    body {
        font-family: Arial, sans-serif;
    }

    .container {
        margin-top: 100px;
        margin-bottom: 100px;
        width: 100%;
        max-width: 1200px;
        margin-left: auto;
        margin-right: auto;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }

    .board-section {
        width: 48%;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border: 1px solid #e0e0e0;
        border-radius: 0.25rem;
        margin-bottom: 1.5rem;
        background-color: #fff;
    }

    .card-body {
        padding: 1.25rem;
    }

    .card-title {
        font-size: 1.25rem;
        margin-bottom: 0.75rem;
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

    .w-25 {
        width: 25%;
    }

    .d-none {
        display: none;
    }

    .d-xl-table-cell {
        display: table-cell;
    }

    .btn {
        display: inline-block;
        font-weight: 400;
        color: #fff;
        text-align: center;
        vertical-align: middle;
        background-color: #28a745;
        border: 1px solid #28a745;
        padding: 0.375rem 0.75rem;
        font-size: 1rem;
        line-height: 1.5;
        border-radius: 0.25rem;
        transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        text-decoration: none; /* 밑줄 없애기 */
    }

    .btn:hover {
        color: #fff;
        background-color: #218838;
        border-color: #1e7e34;
    }

    .atag {
        color: black !important;
        font-weight: normal !important;
    }

    .atag:hover {
        color: black !important;
    }

    @media (min-width: 1200px) {
        .d-xl-table-cell {
            display: table-cell !important;
        }
    }
    .atag {
    text-decoration: none; /* 밑줄 없애기 */
    color: black !important; /* 색상 고정 */
}

.atag:hover {
    text-decoration: none; /* 호버 시에도 밑줄 없애기 */
    color: black !important; /* 호버 시 색상 고정 */
}
</style>
</head>

<body>
    
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<!-- 게시판 미리보기 부분 -->
<div class="container">
    <c:forEach var="sub_list" items="${list}" varStatus="idx">
    <div class="board-section">
        <div class="card-body">
            <h4 class="card-title">${board_list[idx.index].board_info_name}</h4>
            <table class="table table-hover" id='board_list'>
                <thead>
                    <tr>
                        <th class="text-center w-25">글번호</th>
                        <th>제목</th>
                        <th class="text-center w-25 d-none d-xl-table-cell">작성날짜</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="obj" items="${sub_list}">
                    <tr>
                        <td class="text-center">${obj.board_id}</td>
                        <th><a href='${root}board/read${board_list[idx.index].board_info_idx}?board_info_idx=${board_list[idx.index].board_info_idx}&board_id=${obj.board_id}&page=1' class="atag">${obj.board_title}</a></th>
                        <td class="text-center d-none d-xl-table-cell">${obj.board_date}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="${root}board/main?board_info_idx=${board_list[idx.index].board_info_idx}" class="btn">더보기</a>
        </div>
    </div>
    </c:forEach>
</div>
    
<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>
