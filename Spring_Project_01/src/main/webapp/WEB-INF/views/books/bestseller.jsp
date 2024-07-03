<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!---- CSS영역 ---->
<!-- 탑메뉴css -->
<link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
<!-- 메인메뉴css -->
<!-- 사이드바css -->
<link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
<link href="../css/bottom.css" rel="stylesheet" type="text/css" />
<!-- 페이지css -->
<link href="../css/page.css" rel="stylesheet" type="text/css" />

<title>코쿤스토어</title>
</head>

<body>
	<!-- top_menu -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<div class="main-container">
		<div class="content">
			<div class="page">
				<div class="book-list">
					<br>
					<h2 class="fancy">베스트셀러</h2>
					<br>
					<br>

					<c:forEach var="obj" items="${bestseller}" varStatus="status">
						<c:if test="${status.index < 10}">
							<div class="book-item">
								<a href="${root}/books/bookDetail?book_id=${obj.book_id}"> 
								<img src="${obj.book_pic}" alt="${obj.book_name}" class="book-image"></a>
								<div class="book-details">
									<p style="font-weight: bolder;">${obj.book_name}</p>
									<hr>
									<p>
										판매가: <span class="book-price">${obj.book_price}</span>원
										(${obj.author})
									</p>
									<br>
									<p>${obj.book_info}</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<!-- 사이드바 -->
	<c:import url="/WEB-INF/views/include/sideBar.jsp" />
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
