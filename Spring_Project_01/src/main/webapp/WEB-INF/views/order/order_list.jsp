<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코쿤스토어</title>
<link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="../css/bottom.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<style>
body {
	width: 100%;
}

.content {
	max-width: 100%;
	box-sizing: border-box;
	background-color: #f7f7f7;
	display: flex;
}

.order-list-container {
	width: 100%;
	max-width: 1000px;
	border-radius: 10px;
	background-color: #f7f7f7;
	text-align: center;
	margin-top: 130px;
	margin-right : auto;
	margin-left : auto;
	margin-bottom: auto;
}

.sidebar {
	width: 170px;
	background-color: #34495E;
	height: 100vh; /* 화면 높이 전체를 사용 */
	padding-top: 120px;
	display: flex;
	flex-direction: column;
}

.sidebar a {
	padding: 15px 20px;
	text-decoration: none;
	font-size: 9px;
	color: white;
	display: flex;
	align-items: center;
	transition: background-color 0.3s, padding-left 0.3s;
}

.sidebar a:hover {
	background-color: #1a252f;
	padding-left: 30px;
}

.sidebar a.no-hover {
	pointer-events: none;
	font-size: 13px;
	border: none;
}

.sidebar a.no-hover:hover {
	background-color: #2C3E50;
}

.sidebar a.small {
	font-size: 14px;
}

.sidebar a.margin-bottom {
	margin-bottom: 40px;
}

.sidebar a+a {
	margin-top: 0;
}

.sidebar i {
	margin-right: 10px;
}

.order-list-container h2 {
	background-color: #f7f7f7;
	margin-bottom: 20px;
}

.order-list-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
	border-top: 2px solid #ddd;
	border-bottom: 2px solid #ddd;
}

.order-list-table th, .order-list-table td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
}

.order-list-table th {
	background-color: #f2f2f2;
	position: sticky;
	top: 0;
	z-index: 1;
}
.order-list-table td {
	background-color:white;
}
.order-list-scrollable {
	max-height: 500px;
	overflow-y: auto;
	background-color: #f7f7f7;
}

.order-list-table .order-info {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	max-width: 200px;
	text-align: left;
}

.order-list-table .narrow {
	width: 60px;
}

.order-list-table .medium {
	width: 100px;
}

.order-list-table .wide {
	width: 300px;
}

tr {
	cursor: pointer;
}
</style>
<script>
        function goToOrderDetail(orderNumber, bookId) {
            window.location.href = '${pageContext.request.contextPath}/order/order_detail?order_number=' + orderNumber + '&book_id=' + bookId;
        }
    </script>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="content">
		<div class="sidebar">
			<a class="no-hover">주문관리</a> <a href="${root}order/order_list"
				class="small margin-bottom"><i class="fas fa-list-alt"></i> 주문내역</a>
			<a class="no-hover">회원정보</a> <a href="${root}user/mypage"
				class="small"><i class="fas fa-user"></i> 회원정보조회</a> <a
				href="${root}user/check_password?action=modify" class="small"><i
				class="fas fa-user-edit"></i> 회원정보수정</a> <a
				href="${root}user/check_password?action=delete" class="small"><i
				class="fas fa-user-times"></i> 회원탈퇴</a>
		</div>
		<div class="order-list-container">
			<h2>주문 내역</h2>
			<div class="order-list-scrollable">
				<table class="order-list-table">
					<thead>
						<tr>
							<th class="medium">주문일/주문번호</th>
							<th class="wide">상품정보</th>
							<th class="narrow">결제 금액</th>
							<th class="narrow">주문 상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orderList}">
							<tr
								onclick="goToOrderDetail('${order.order_number}', '${order.book_id}')">
								<td class="medium"><c:out
										value="${order.formattedOrderDate}" /><br /> <c:out
										value="${order.order_number}" /></td>
								<td class="order-info"><c:out value="${order.book_name}" />
								</td>
								<td class="narrow"><c:out value="${order.order_price}" />
									원</td>
								<td class="narrow"><c:out value="${order.delivery_state}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
