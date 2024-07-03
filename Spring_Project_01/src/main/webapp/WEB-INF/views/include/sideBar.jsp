<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이콘이 있는 사이드바</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        @charset "UTF-8";

        /* 노멀라이즈 */
        body, ul, li {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .sidebar-a {
            color: white; /* 폰트 색상 흰색 */
            text-decoration: none;
            transition: padding-left 0.3s ease; /* 부드러운 이동 효과 추가 */
        }

        /* 사이드바 설정 */
        .sidebar {
            width: 200px;
            padding: 15px;
            box-sizing: border-box;
            position: fixed;
            left: -150px;
            top: 50%;
            transform: translateY(-50%);
            background-color: #4CAF50;
            color: white;
            transition: left 0.3s ease;
            border-radius: 0px 20px 20px 0px;
            z-index: 1000;
        }

        .sidebar > .img {
            width: 32px;
            height: auto;
            float: right;
        }

        .sidebar:hover > .img {
            opacity: 0;
        }

        .sidebar:hover {
            left: 0;
        }

        .sidebar h1 {
            height: 100px;
            font-weight: bold;
            background-color: #4CAF50;
            text-shadow: 2px 2px 2px black;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            background-color: #4CAF50;
        }

        .sidebar ul li {
            margin-bottom: 10px;
            background-color: #4CAF50;
        }

        .sidebar ul li a {
            text-decoration: none;
            color: white; /* 폰트 색상 흰색 */
            display: block;
            padding: 10px 20px;
            background-color: #4CAF50;
            font-size: 18px; /* 폰트 사이즈 키움 */
            transition: padding-left 0.3s ease; /* 부드러운 이동 효과 추가 */
        }

        .sidebar ul li a:hover {
            color: #4CAF50;
            background-color: #2E7D32;
            padding-left: 30px; /* 호버 시 오른쪽으로 이동 */
        }

        .sidebar h3 {
            font-size: 14px; /* 폰트 사이즈 키움 */
        }

        .sidebar i {
            font-size: 20px; /* 아이콘 사이즈 키움 */
            margin-right: 10px; /* 아이콘과 텍스트 사이 간격 */
            color: white; /* 아이콘 색상 흰색 */
        }
    </style>
</head>
<body>
    <c:set var="root" value="${pageContext.request.contextPath}/" />
    <div class="sidebar">
        <img class="img" src="${root}image/menu.png" />
        <br>
        <h1>카테고리</h1>
        <ul>
            <c:forEach var="obj" items="${sidebarList}">
                <li>
                    <h3>
                        <a href="${root}books/page?sidebar_id=${obj.sidebar_id}" class="sidebar-a">
                            <c:choose>
                                <c:when test="${obj.sidebar_name == '건강'}">
                                    <i class="fas fa-heartbeat"></i> <!-- 건강 아이콘 -->
                                </c:when>
                                <c:when test="${obj.sidebar_name == '스포츠'}">
                                    <i class="fas fa-football-ball"></i> <!-- 스포츠 아이콘 -->
                                </c:when>
                                <c:when test="${obj.sidebar_name == '여행'}">
                                    <i class="fas fa-plane"></i> <!-- 여행 아이콘 -->
                                </c:when>
                                <c:when test="${obj.sidebar_name == '요리'}">
                                    <i class="fas fa-utensils"></i> <!-- 요리 아이콘 -->
                                </c:when>
                                <c:when test="${obj.sidebar_name == '음악'}">
                                    <i class="fas fa-music"></i> <!-- 음악 아이콘 -->
                                </c:when>
                                <c:otherwise>
                                    <i class="fas fa-book"></i> <!-- 기본 아이콘 -->
                                </c:otherwise>
                            </c:choose>
                            ${obj.sidebar_name}
                        </a>
                    </h3>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
