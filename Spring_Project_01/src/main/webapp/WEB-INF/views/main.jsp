	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="root" value="${pageContext.request.contextPath}" />
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>코쿤스토어</title>
	<link href="css/top_menu.css" rel="stylesheet" type="text/css" />
	<link href="css/main.css" rel="stylesheet" type="text/css" />
	<link href="css/sidebar.css" rel="stylesheet" type="text/css" />
	<link href="css/bottom.css" rel="stylesheet" type="text/css" />
	<script src="js/main.js"></script>
	</head>
	<body>
	    <c:import url="/WEB-INF/views/include/top_menu.jsp" />
	
	    
	    <div class="container">
	        <br><br><br>
	        <h2>오늘의 책</h2>
	        <div class="book-slider">
	            <button class="btn prev" onclick="slide(-1)">&lt;</button>
	            
	            <div class="book-slider-inner">
	                <c:forEach var="obj" items="${bookList}">
	                    <div class="book-slide">
	                        <div class="book-box">
	                            <a href="books/bookDetail?book_id=${obj.book_id}">
	                            <img src="${obj.book_pic}" alt="${obj.book_name}" class="book-image"> </a>
	                            <div class="book-details">
	                            <p style="font-weight: bolder;">${obj.book_name}</p>
	                                <hr>
	                                <p>판매가: <span class="book-price">${obj.book_price}</span>원 (${obj.author}) </p>
	                                <br>
	                                <p>${obj.book_info}</p>                
	                            </div>
	                        </div>
	                    </div>
	                </c:forEach>
	            </div>
	            
	            <button class="btn next" onclick="slide(1)">&gt;</button>
	        </div>
	    </div>
<div class="carousel-container">
<h2>최신 리뷰</h2>
    <div class="carousel-inner">
        <c:forEach var="review" items="${userReview}">
            <div class="profile-page-review-item">
                	<div class="review-user_info">
    					<img src="${root}/upload/${review.user_pic}" alt="유저사진" class="review-user_pic"/>            		
						<a href="${root}/sns/myprofile?user_idx=${review.user_idx}" class="review-user_Nick">
	                		${review.userNickName}
						</a>
                	<div class="review-rating">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                		<c:choose>
                			<c:when test="${review.rating >=2}">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                			</c:when>
                			<c:otherwise>
		                		<img src="${root}/image/icon-star-0.png" alt="star" class="review-star"/>
                			</c:otherwise>
                		</c:choose>
                		<c:choose>
                			<c:when test="${review.rating >=3}">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                			</c:when>
                			<c:otherwise>
		                		<img src="${root}/image/icon-star-0.png" alt="star" class="review-star"/>
                			</c:otherwise>
                		</c:choose>
                		<c:choose>
                			<c:when test="${review.rating >=4}">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                			</c:when>
                			<c:otherwise>
		                		<img src="${root}/image/icon-star-0.png" alt="star" class="review-star"/>
                			</c:otherwise>
                		</c:choose>
                		<c:choose>
                			<c:when test="${review.rating >=5}">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                			</c:when>
                			<c:otherwise>
		                		<img src="${root}/image/icon-star-0.png" alt="star" class="review-star"/>
                			</c:otherwise>
                		</c:choose>

                		
                	</div>
                	</div>
                <div class="review-content">
                	<br />
                	<div class="review-image">
		                <img src="${review.book_pic}" alt="${review.bookName}" class="review-book-pic" />
		                </div>
		                <div class="review-all-content">
		                	<div class="review-content-bookname">
			                    <a href="${root}/books/bookDetail?book_id=${review.book_id}" class="book-title">
			                        ${review.bookName}
			                    </a>
		                	</div>
			                <div class="profile-page-review">
			                    ${review.review_content}
					        	<div class="profile-page-review">
					               	작성일: ${review.review_date}
					        	</div>
			                </div>
		                </div>
               		</div>
            </div>
        </c:forEach>
    </div>
</div>
	
	    <div class="container">
	        <h2>취미에 맞는 책</h2>
	        <div class="menu-bar">
	            <button class="tablink" onclick="openSection(event, 'md-recommended')">건강</button>
	            <div class="vertical-line"></div>
	            <button class="tablink" onclick="openSection(event, 'new-releases')">축구</button>
	            <div class="vertical-line"></div>
	            <button class="tablink" onclick="openSection(event, 'steady-sellers')">일본여행</button>
	            <div class="vertical-line"></div>
	            <button class="tablink" onclick="openSection(event, 'comics')">서양음악</button>
	        </div>
	
	        <div id="md-recommended" class="tabcontent">
	            <div class="products">
	                <c:forEach var="obj" items="${bookList}">
	                    <c:if test="${obj.book_category eq '건강'}"> <!-- 건강 -->
	                        <div class="product">
	                            <a href="books/bookDetail?book_id=${obj.book_id}"><img src="${obj.book_pic}" alt="${obj.book_name}"></a>
	                            <p style="font-weight: bolder;">${obj.book_name}</p>
	                            <hr>
	                            <p>저자: ${obj.author}</p>
	                            <p>판매가: <span class="book-price">${obj.book_price}</span>원</p>
	                        </div>
	                    </c:if>
	                </c:forEach>
	            </div>
	        </div>
	
	        <div id="new-releases" class="tabcontent">
	            <div class="products">
	                <c:forEach var="obj" items="${bookList}">
	                    <c:if test="${obj.book_category eq '축구'}"> <!--축구 -->
	                        <div class="product">
	                            <a href="books/bookDetail?book_id=${obj.book_id}"><img src="${obj.book_pic}" alt="${obj.book_name}"></a>
	                            <p style="font-weight: bolder;">${obj.book_name}</p>
	                            <hr>
	                            <p>저자: ${obj.author}</p>
	                            <p>판매가: <span class="book-price">${obj.book_price}</span>원</p>
	                        </div>
	                    </c:if>
	                </c:forEach>
	            </div>
	        </div>
	
	        <div id="steady-sellers" class="tabcontent">
	            <div class="products">
	                <c:forEach var="obj" items="${bookList}">
	                    <c:if test="${obj.book_category eq '일본여행'}"> <!-- 일본여행 -->
	                        <div class="product">
	                            <a href="books/bookDetail?book_id=${obj.book_id}"><img src="${obj.book_pic}" alt="${obj.book_name}"></a>
	                            <p style="font-weight: bolder;">${obj.book_name}</p>
	                            <hr>
	                            <p>저자: ${obj.author}</p>
	                            <p>판매가: <span class="book-price">${obj.book_price}</span>원</p>
	                        </div>
	                    </c:if>
	                </c:forEach>
	            </div>
	        </div>
	
	        <div id="comics" class="tabcontent">
	            <div class="products">
	                <c:forEach var="obj" items="${bookList}">
	                    <c:if test="${obj.book_category eq '서양음악'}"> <!-- 서양음악 -->
	                        <div class="product">
	                            <a href="books/bookDetail?book_id=${obj.book_id}"><img src="${obj.book_pic}" alt="${obj.book_name}"></a>
	                            <p style="font-weight: bolder;">${obj.book_name}</p>
	                            <hr>
	                            <p>저자: ${obj.author}</p>
	                            <p>판매가: <span class="book-price">${obj.book_price}</span>원</p>
	                        </div>
	                    </c:if>
	                </c:forEach>
	            </div>
	        </div>
	    </div>
	
	    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />
	    <c:import url="/WEB-INF/views/include/sideBar.jsp" />
	    

	</body>
	</html>
