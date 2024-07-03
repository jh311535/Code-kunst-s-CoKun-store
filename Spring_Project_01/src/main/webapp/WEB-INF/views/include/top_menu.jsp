<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<style>
    .nav-link123, .nav-menu123 a {
        text-decoration: none;
    }
    .container123 {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
    }
    .top123 {
        width: 100%;
        display: flex;
        justify-content: flex-end;
        margin: 10px 0;
        padding-right: 50px; /* 오른쪽으로 더 이동시키기 위해 padding-right 추가 */
    }
    .nav-item123 {
        list-style: none;
    }
    .search-bar123-container {
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 10px 0;
    }
    .search-bar123 {
        display: flex;
        align-items: center;
    }
    .search-bar123 input[type="text"] {
        padding: 5px;
        margin-right: 5px;
    }
    .search-bar123 input[type="image"] {
        width: 20px;
        height: 20px;
    }
    .nav-menu123 {
        display: flex;
        justify-content: center;
    }
</style>

<!-- 상단 메뉴 -->
<div class="top123">
  <c:choose>
     <c:when test="${sessionScope.loginUserBean.userLogin == true}">
        <li class="nav-item123">
           <a href="${root}user/mypage" class="nav-link123"><i class="fas fa-user"></i> 마이페이지</a>
           &nbsp;&nbsp;|&nbsp;&nbsp;
           <a href="${root}sns/myprofile" class="nav-link123"><i class="fas fa-users"></i> SNS페이지</a>
           &nbsp;&nbsp;|&nbsp;&nbsp;
           <a href="${root}cart/cart" class="nav-link123"><i class="fas fa-shopping-cart"></i> 장바구니</a>
           &nbsp;&nbsp;|&nbsp;&nbsp;
           <a href="${root}user/logout" class="nav-link123"><i class="fas fa-sign-out-alt"></i> 로그아웃</a>
        </li>
     </c:when>
     <c:otherwise>
        <li class="nav-item123">
           <a href="${root}user/login" class="nav-link123"><i class="fas fa-sign-in-alt"></i> 로그인</a>
           &nbsp;&nbsp;|&nbsp;&nbsp;
           <a href="${root}user/join" class="nav-link123"><i class="fas fa-user-plus"></i> 회원가입</a>
        </li>
     </c:otherwise>
  </c:choose>
</div>

<!-- 검색 바 -->
<div class="search-bar123-container">
    <a href="${root}" class="nav-link123">
        <img src="${root}image/logo.png" alt="로고" class="logo123">
    </a>
    <form action="${root}search/results" method="get" class="search-bar123">
        <input type="text" name="keyword" placeholder="통합검색" class="search-text123">
        <input type="image" src="${root}image/search.png" alt="submit">
        <input type="hidden" name="newSearch" value="true">
    </form>
</div>

<!-- 네비게이션 메뉴 -->
<div class="nav-menu123">
   <a href="${root}books/bestseller"><i class="fas fa-book">&nbsp;</i> 베스트셀러</a>
   <%-- <a href="${root}board/home">게시판</a> --%>
   <a href="${root}board/main?board_info_idx=1"><i class="fas fa-comments">&nbsp;</i> 자유 게시판</a>
   <a href="${root}board/main?board_info_idx=2"><i class="fas fa-question-circle">&nbsp;</i> Q&A 게시판</a>
   <a href="${root}notice/list"><i class="fas fa-bullhorn">&nbsp;</i> 공지사항</a>
   <a href="${root}chat"><i class="fas fa-comments">&nbsp;</i> 코쿤스토어 ChatBot</a>
</div>

</body>
