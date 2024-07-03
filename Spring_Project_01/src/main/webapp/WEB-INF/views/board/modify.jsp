<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value='${pageContext.request.contextPath }/'/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<link href="${root}css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="${root}css/bottom.css" rel="stylesheet" type="text/css" />
<style>
    body {
        font-family: Arial, sans-serif;
    }

    .container {
        margin-top: 70px;
        margin-bottom: 70px;
        width: 100%;
        max-width: 600px;
        margin-left: auto;
        margin-right: auto;
    }

    .row {
        display: flex;
        justify-content: center;
    }

    .col-sm-3, .col-sm-6 {
        padding: 15px;
    }

    .col-sm-6 {
        flex: 0 0 100%;
        max-width: 100%;
    }

    .card {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border: 1px solid #e0e0e0;
        border-radius: 0.25rem;
        background-color: #fff;
        margin-bottom: 20px;
        width: 100%;
    }

    .card-body {
        padding: 1.25rem;
    }

    .form-group {
        margin-bottom: 1rem;
    }

    .form-control {
        display: block;
        width: 95%;
        padding: 0.375rem 0.75rem;
        font-size: 1rem;
        line-height: 1.5;
        color: #495057;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #ced4da;
        border-radius: 0.25rem;
        transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
    }

    .form-control:focus {
        color: #495057;
        background-color: #fff;
        border-color: #80bdff;
        outline: 0;
        box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
    }

    .btn {
        display: inline-block;
        font-weight: 400;
        color: #fff;
        text-align: center;
        vertical-align: middle;
        user-select: none;
        background-color: #007bff;
        border: 1px solid #007bff;
        padding: 0.375rem 0.75rem;
        font-size: 1rem;
        line-height: 1.5;
        border-radius: 0.25rem;
        transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        text-decoration: none;
        margin-right: 0.5rem;
        margin-bottom: 0.5rem;
    }

    .btn-primary {
        background-color: #007bff;
        border-color: #007bff;
    }

    .btn-primary:hover {
        background-color: #0056b3;
        border-color: #004085;
    }

    .btn-info {
        background-color: #17a2b8;
        border-color: #17a2b8;
    }

    .btn-info:hover {
        background-color: #117a8b;
        border-color: #0d6efd;
    }

    .text-right {
        text-align: right;
    }

    .form-group label {
        display: block;
        margin-bottom: 0.5rem;
    }

    .form-group textarea {
        resize: none;
    }

    .form-group input[type="file"] {
        display: block;
        width: 95%;
    }

    .form-group img {
        max-width: 100%;
        height: auto;
        display: block;
        margin-top: 0.5rem;
    }

    .form-group .btn {
        width: auto;
    }

    .button-group {
        display: flex;
        justify-content: flex-end;
        flex-wrap: wrap;
        gap: 0.5rem;
    }
</style>
</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card shadow">
                <div class="card-body">
                    <form action="${root}board/modify_pro" method="post" modelAttribute="modifyBoardBean" enctype="multipart/form-data">
                        <input type="hidden" name="board_info_idx" value="${modifyBoardBean.board_info_idx}" />
                        <input type="hidden" name="board_id" value="${modifyBoardBean.board_id}" />
                        <input type="hidden" name="page" value="${page}">

                        <div class="form-group">
                            <label for="user_id">작성자</label>
                            <input type="text" id="user_id" name="user_id" class="form-control" value="${modifyBoardBean.user_nickname}" readonly="true"/>
                        </div>

                        <div class="form-group">
                            <label for="board_date">작성날짜</label>
                            <input type="text" id="board_date" name="board_date" class="form-control" value="${modifyBoardBean.board_date}" readonly="true"/>
                        </div>

                        <div class="form-group">
                            <label for="board_title">제목</label>
                            <input type="text" id="board_title" name="board_title" class="form-control" value="${modifyBoardBean.board_title}" />
                        </div>

                        <div class="form-group">
                            <label for="board_content">내용</label>
                            <textarea id="board_content" name="board_content" class="form-control" rows="10" style="resize:none">${modifyBoardBean.board_content}</textarea>
                        </div>

                        <div class="form-group">
                            <label for="board_pic">첨부 이미지</label>
                            <c:if test="${modifyBoardBean.board_pic != null}">
                                <img src="${root}upload/${modifyBoardBean.board_pic}" alt="첨부 이미지"/>
                                <input type="hidden" name="board_pic" value="${modifyBoardBean.board_pic}" />
                            </c:if>
                            <input type="file" id="upload_file" name="upload_file" class="form-control" accept="image/*" />
                        </div>

                        <div class="form-group button-group">
                            <button type="submit" class="btn btn-primary">수정완료</button>
                            <a href="${root}board/read?board_info_idx=${board_info_idx}&board_id=${board_id}&page=${page}" class="btn btn-info">취소</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>
