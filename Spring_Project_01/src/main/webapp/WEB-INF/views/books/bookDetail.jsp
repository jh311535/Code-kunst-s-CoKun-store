<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="kr.co.soldesk.beans.BookDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
<link href="../css/bottom.css" rel="stylesheet" type="text/css" />
<link href="../css/bookdetail.css" rel="stylesheet" type="text/css" />
<style>
.purchase-options {
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 10px;
}

.purchase-options label {
	font-size: 14px;
	margin-bottom: 5px;
}

#cart_quantity {
	width: 50%;
	text-align: center;
	padding: 5px;
	margin-bottom: 10px;
}

hr {
	width: 100%;
	border: 1px solid #ddd;
	margin: 10px 0;
}

.cart-button, .buy-button {
	width: 130%; /* hr 태그의 길이와 동일하게 설정 */
	font-size: 16px !important;
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	text-align: center; /* 버튼 내부의 텍스트를 가운데 정렬 */
}

.cart-button {
	background-color: #f44336;
	margin-bottom: 10px;
}

.cart-button:hover {
	background-color: #e53935;
}

.buy-button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<c:forEach var="obj" items="${bookList}">
		<c:if test="${obj.book_id == param.book_id}">
			<div class="container">
				<div class="book-detail-container">
					<div>
						<img src="${obj.book_pic}" alt="${obj.book_name}"
							class="book-image-detail">
					</div>
					<div class="book-details">
						<h1>${obj.book_name}</h1>
						<p>저자: ${obj.author} | 출판사: ${obj.publisher}</p>
						<p>ISBN: ${obj.isbn}</p>
						<hr>
						<p>
							판매가격: <span class="book-price">${obj.book_price}</span>원
						</p>
						<p>배송일정: 내일 출고 가능</p>
					</div>
					<div class="purchase-options">
						<form id="cartForm">
							<label for="cart_quantity">주문수량 </label> <input type="number"
								id="cart_quantity" name="cart_quantity" value="1" min="1"
								max="10">
							<hr>
							<input type="hidden" id="user_idx" name="user_idx"
								value="${user.user_idx}"> <input type="hidden"
								id="book_id" name="book_id" value="${obj.book_id}">
							<button type="button" class="cart-button" onclick="addToCart()">장바구니
								담기</button>
							<button type="button" class="buy-button" onclick="buyNow()">바로구매</button>
						</form>
					</div>
				</div>
			</div>

			<div class="container">
				<div class="book-detail-bottom">
					<div>
						<h1>도서정보</h1>
						<a>"${obj.book_info}"</a>
					</div>
				</div>
			</div>
		</c:if>
	</c:forEach>
	<!-- ------------------추천도서----------------- -->
	<div class="container">
		<h2>추천도서</h2>
		<div class="recommendedBook">
			<c:forEach var="book" items="${randomBooks}">
				<div class="product">
					<a href="${root}books/bookDetail?book_id=${book.book_id}"> <img
						src="${book.book_pic}" alt="${book.book_name}" class="book-image">
					</a>
					<div class="recommendedBook_detail">
						<p>${book.book_name}</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>




	<!-- ---------------------------유튜브---------------------------- -->
	<!-- 유튜브 비디오 섹션 -->
	<div class="container">
		<h2>유튜브 큐레이팅</h2>
		<div class="youtube-videos">
			<c:forEach var="obj" items="${recommendedVideos}">
				<div class="youtube-video">
					<a href="https://www.youtube.com/watch?v=${obj.videoId}"
						target="_blank"> <img src="${obj.highThumbnailUrl}"
						alt="${obj.title}" class="youtube-thumbnail">
					</a>
					<div class="youtube-video-detail">
						<p>${obj.title}</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- -----------------------------큐레이팅------------------------------------- -->

	<div class="container">
		<h2>상품 큐레이팅</h2>
		<div class="youtube-videos">
			<c:forEach var="obj" items="${filteredCuration}">
				<div class="youtube-video">
					<a href="${obj.product_url}" target="_blank"> <img
						src="${obj.product_pic}" alt="${obj.product_name}"
						class="youtube-thumbnail">
					</a>
					<div class="youtube-video-detail">
						<p>${obj.product_name}</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- 리뷰 섹션 -->
	<div class="container review-section">
		<h2>리뷰</h2>
		<div>
			<a href="${root}/review/review_write?book_id=${param.book_id}"
				class="review-write-btn">리뷰쓰기</a>
		</div>
		<h2>리뷰 목록</h2>
		<div class="review-list-container">
			<c:forEach var="review" items="${reviews}">
				<div class="page-review-item">
					<img src="${review.book_pic}" alt="${obj.book_name}"
						class="review-book-pic" /> 
						<div class="page-review-content">
						
						<a href="${root}sns/myprofile?user_idx=${review.user_idx}">
						${review.userNickName} </a>
					<p class="rating">		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
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
                		</c:choose></p>
					<p>리뷰 내용: ${review.review_content}</p>
					<p>리뷰 날짜: ${review.review_date}</p>
						</div>
					<!-- 리뷰 삭제 및 수정 버튼 추가 -->
					<c:choose>
						<c:when test="${review.user_idx == loginUserBean.user_idx}">
							<div class="review-buttons">
								<button class="delete-review-btn"
									data-review-id="${review.review_id}">리뷰 삭제</button>
								<button class="modify-review-btn"
									onclick="location.href='${root}/review/review_modify?review_id=${review.review_id}'">리뷰
									수정</button>
							</div>
						</c:when>
					</c:choose>
				</div>
			</c:forEach>
		</div>
		</div>

		<div class="pagination">
			<c:if test="${pageNumber > 1}">
				<a href="?book_id=${book_id}&page=${pageNumber - 1}">이전</a>
			</c:if>
			<c:forEach begin="1" end="${totalPages}" var="i">
				<a href="?book_id=${book_id}&page=${i}"
					class="${i == pageNumber ? 'active' : ''}">${i}</a>
			</c:forEach>
			<c:if test="${pageNumber < totalPages}">
				<a href="?book_id=${book_id}&page=${pageNumber + 1}">다음</a>
			</c:if>
		</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
	<c:import url="/WEB-INF/views/include/sideBar.jsp" />
</body>
<script>
	$(document).ready(function() {
		// 리뷰 삭제 버튼 클릭 이벤트 핸들러
		$(document).on('click', '.delete-review-btn', function() {
			var reviewId = $(this).data("review-id");
			console.log("Deleting review with ID:", reviewId);

			$.ajax({
				url : '${root}review/deleteReview', // 컨트롤러의 URL과 일치시킵니다.
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify({
					review_id : reviewId
				}),
				success : function(response) {
					alert("리뷰가 성공적으로 삭제되었습니다.");
					location.reload(); // 페이지를 새로고침하여 변경사항을 반영합니다.
				},
				error : function(xhr, status, error) {
					console.error("Error in delete-review-btn click:", error);
					alert("리뷰 삭제에 실패했습니다.");
				}
			});
		});
	});
	function addToCart() {
		var user_idx = $('#user_idx').val();
		var book_id = $('#book_id').val();
		var cart_quantity = $('#cart_quantity').val();

		$.ajax({
			url : "${root}cart/add",
			type : 'POST',
			data : {
				user_idx : user_idx,
				book_id : book_id,
				cart_quantity : cart_quantity
			},
			success : function(response) {
				if (response === "1") {
					alert("이미 장바구니에 있는 책입니다.");
				} else {
					if (confirm("장바구니에 책을 담았습니다. 바로 장바구니로 가시겠습니까?")) {
						window.location.href = "${root}cart/cart";
					}
				}
			},
			error : function(error) {
				console.error(error);
				alert('로그인하고 이용해주세요');
			}
		});
	}

	function buyNow() {
		var user = $
		{
			user != null ? 'true' : 'false'
		}
		;
		if (!user) {
			alert('로그인 후 구매할 수 있습니다.');
			return;
		}

		var book_id = $('#book_id').val();
		var order_quantity = $('#cart_quantity').val();

		$(
				'<form action="' + "${root}" + 'order/checkout" method="post">'
						+ '<input type="hidden" name="book_id" value="' + book_id + '">'
						+ '<input type="hidden" name="order_quantity" value="' + order_quantity + '">'
						+ '</form>').appendTo('body').submit();
	}
</script>
</html>