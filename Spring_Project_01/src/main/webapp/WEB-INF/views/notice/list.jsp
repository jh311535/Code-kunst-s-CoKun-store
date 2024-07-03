<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
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
    <link href="${root}css/top_menu_2.css" rel="stylesheet" type="text/css" />
    <link href="${root}css/bottom.css" rel="stylesheet" type="text/css" />
    <link href="${root}css/notice_list.css" rel="stylesheet" type="text/css" />
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        .wrapper {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .content {
            flex: 1;
            display: flex;
            justify-content: center; /* 중앙 정렬 추가 */
        }

        .list-container {
            width: 80%; /* 원하는 너비로 설정 */
            margin: 75px auto; /* 중앙 정렬 */
        }

        .search-bar123 input[type="text"] {
            margin-right: 10px !important;
        }
    </style>
</head>
<body>
    <div class="wrapper">
        <c:import url="/WEB-INF/views/include/top_menu.jsp" />

        <div class="content">
            <div class="list-container">
                <h3 class="list-title">공지사항</h3>
                <div class="notice-search-bar">
                    <form action="${root}notice/list" method="get" class="form-inline">
                        <input type="hidden" name="sort" value="${sort}"/>
                        <select name="searchType" class="form-control mb-2 mr-sm-2">
                            <option value="title" ${filter.notice_title != null ? 'selected' : ''}>제목</option>
                            <option value="content" ${filter.notice_content != null ? 'selected' : ''}>내용</option>
                        </select>
                        <input type="text" name="query" placeholder="검색어"
                               value="${filter.notice_title != null ? filter.notice_title : filter.notice_content != null ? filter.notice_content : ''}"
                               class="form-control mb-2 mr-sm-2">
                        <button type="submit" class="btn btn-primary mb-2"><i class="fas fa-search"></i> 검색</button>
                    </form>
                </div>
                <div class="notice-sort-options">
                    <a href="${root}notice/list?sort=date_desc&sortChange=true"
                       class="btn btn-link ${filter.dateOrder == 'desc' ? 'btn-link-active' : 'btn-link-inactive'}">최신순</a>
                    <a href="${root}notice/list?sort=date_asc&sortChange=true"
                       class="btn btn-link ${filter.dateOrder == 'asc' ? 'btn-link-active' : 'btn-link-inactive'}">오래된순</a>
                </div>
                <div class="notice-list">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>날짜</th>
                            <th>조회수</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="notice" items="${noticeList}">
                            <tr>
                                <td>${notice.notice_id}</td>
                                <td><a href="${root}notice/detail/${notice.notice_id}" class="title-link">${notice.notice_title}</a></td>
                                <td>${notice.admin_nickname}</td>
                                <td>${notice.formattedDate}</td>
                                <td>${notice.notice_views}</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty noticeList}">
                            <tr>
                                <td colspan="5" class="text-center">데이터 없음</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <div class="pagination">
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

        <c:import url="/WEB-INF/views/include/bottom_info.jsp" />
    </div>
</body>
</html>
