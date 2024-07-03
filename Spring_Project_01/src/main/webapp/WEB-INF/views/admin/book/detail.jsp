<%-- File Location: /views/admin/book/detail.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="${root}css/tempusdominus-bootstrap-4.min.css">
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<c:import url="/WEB-INF/views/admin_include/top_menu.jsp"></c:import>
		<c:import url="/WEB-INF/views/admin_include/left_menu.jsp"></c:import>
		<div class="content-wrapper">
			<section class="content">
				<div class="container-fluid">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">책 상세보기</h3>
						</div>
						<div class="card-body">
							<div class="form-group">
								<label for="book_name">책 이름</label> <input type="text"
									id="book_name" name="book_name" class="form-control"
									value="${book.book_name}" readonly>
							</div>
							<div class="form-group">
								<label for="author">저자</label> <input type="text" id="author"
									name="author" class="form-control" value="${book.author}"
									readonly>
							</div>
							<div class="form-group">
								<label for="isbn">ISBN</label> <input type="text" id="isbn"
									name="isbn" class="form-control" value="${book.isbn}" readonly>
							</div>
							<div class="form-group">
								<label for="book_category">카테고리</label> <input type="text"
									id="book_category" name="book_category" class="form-control"
									value="${book.book_category}" readonly>
							</div>
							<div class="form-group">
								<label for="publisher">출판사</label> <input type="text"
									id="publisher" name="publisher" class="form-control"
									value="${book.publisher}" readonly>
							</div>
							<div class="form-group">
								<label for="publish_date">출판일</label> <input type="text"
									id="publish_date" name="publish_date" class="form-control"
									value="${book.formattedDate}" readonly>
							</div>
							<div class="form-group">
								<label for="book_price">가격</label> <input type="text"
									id="book_price" name="book_price" class="form-control"
									value="${book.book_price}" readonly>
							</div>
							<div class="form-group">
								<label for="inventory">재고</label> <input type="text"
									id="inventory" name="inventory" class="form-control"
									value="${book.inventory}" readonly>
							</div>
							<div class="form-group">
								<label for="book_info">책 정보</label>
								<textarea id="book_info" name="book_info" class="form-control"
									rows="3" readonly>${book.book_info}</textarea>
							</div>
							<div class="form-group">
								<label for="current_pic">책 사진</label>
								<div>
									<c:choose>
										<c:when test="${book.book_pic != null}">
											<img src="${book.book_pic}" alt="사진" class="img-thumbnail"
												style="max-width: 150px;">
										</c:when>
										<c:otherwise> X </c:otherwise>
									</c:choose>
								</div>
							</div>
							<button type="button" class="btn btn-secondary"
								onclick="history.back();">뒤로 가기</button>
							<button type="button" class="btn btn-info"
								onclick="location.href='${root}admin/book/modify/${book.book_id}'">수정</button>
							<button type="button" class="btn btn-danger"
								onclick="confirmDelete(${book.book_id})">삭제</button>
						</div>
					</div>
				</div>
			</section>
		</div>
		<c:import url="/WEB-INF/views/admin_include/footer.jsp"></c:import>
	</div>
	<script>
		function confirmDelete(bookId) {
			if (confirm('정말 삭제하시겠습니까?')) {
				$.ajax({
					url: '${root}admin/book/delete',
					type: 'POST',
					data: { id: bookId },
					success: function(response) {
						alert('삭제되었습니다.');
						window.location.href = '${root}admin/book/manage';
					},
					error: function() {
						alert('삭제에 실패했습니다.');
					}
				});
			}
		}
	</script>
	<script src="${root}js/adminlte.min.js"></script>
	<script src="${root}js/bootstrap.bundle.min.js"></script>
	<script src="${root}js/jquery.min.js"></script>
	<script src="${root}js/locales-all.min.js"></script>
	<script src="${root}js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>
