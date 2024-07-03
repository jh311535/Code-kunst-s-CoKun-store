<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html> 
<html> 
<head> 
<meta charset ="UTF-8 "> 
<title>코쿤스토어</title>
<link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="../css/bottom.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<style>
.container {
	
	max-width: 100%;
	background-color: #f7f7f7;
	display: flex;
	
}

.sidebar {
	width: 170px;
	background-color: #34495E;
	height: 130vh; /* 화면 높이 전체를 사용 */
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

.order-details-section {
    max-width: 800px;
    display: flex;
    flex-direction: column;
    align-items: left;
    margin-top: 130px;
	margin-right : auto;
	margin-left : auto;
	margin-bottom: auto;
}

.order-details-section h1,
.order-details-section h2 {
    align-items: left;
}

.order-details-section table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.order-details-section th,
.order-details-section td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: left;
}

.order-details-section th {
    background-color: #f2f2f2;
    text-align: center; /* 가운데 정렬 */
}

.order-details-section .bold-label {
    font-weight: bold;
    width: 150px;
    background-color: #f2f2f2;
}

.order-details-section .book-info {
    display: flex;
    align-items: center;
}

.order-details-section .book-info img {
    width: 50px;
    height: auto;
    margin-right: 10px;
}

.order-details-section .book-title {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: 300px;
}

.order-details-section .center-text {
    text-align: center; /* 중앙 정렬 */
}

.button-container {
	display: flex;
	justify-content: center;
	gap: 20px; /* 버튼 사이의 간격을 20px로 설정 */
	margin-top: 20px;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

.cancel-button {
	background-color: #f44336;
}

.cancel-button:hover {
	background-color: #e53935;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1;
	padding-top: 100px;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgb(0, 0, 0);
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	max-width: 400px;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- jQuery 추가 -->
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="container">
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
		<div class="order-details-section">
    <h2>제품 정보</h2>
    <table>
        <thead>
            <tr>
                <th>상품명</th>
                <th>가격</th>
                <th>수량</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orderDetails}">
                <tr>
                    <td class="book-info">
                        <img src="${order.book_pic}" alt="${order.book_name}">
                        <span class="book-title"><c:out value="${order.book_name}" /></span>
                    </td>
                    <td class="center-text">${order.book_price}원</td>
                    <td class="center-text">${order.order_quantity}권</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br /> <br />
    <h2>배송 정보</h2>
    <table>
        <tr>
            <th>주문번호</th>
            <td><c:out value="${orderDetails[0].order_number}" /></td>
            <th>주문 상태</th>
            <td><c:out value="${orderDetails[0].delivery_state}" /></td>
        </tr>
        <tr>
            <th>수령인</th>
            <td colspan="3"><c:out value="${orderDetails[0].orderer_name}" /></td>
        </tr>
        <tr>
            <th>주소</th>
            <td colspan="3">
                <c:out value="${orderDetails[0].address}" /><br />
                <c:out value="${orderDetails[0].detailaddress}" />
            </td>
        </tr>
        <tr>
            <th>휴대전화</th>
            <td colspan="3"><c:out value="${orderDetails[0].phone}" /></td>
        </tr>
        <tr>
            <th>이메일</th>
            <td colspan="3"><c:out value="${orderDetails[0].email}" /></td>
        </tr>
        <tr>
            <th>수령장소</th>
            <td colspan="3"><c:out value="${orderDetails[0].request}" /></td>
        </tr>
        <tr>
            <th>배송메모</th>
            <td colspan="3"><c:out value="${orderDetails[0].request_text}" /></td>
        </tr>
    </table>
		<br />
		<div class="button-container">
			<button
				onclick="location.href='${pageContext.request.contextPath}/order/order_list'">돌아가기</button>
			<c:if
				test="${orderDetails[0].delivery_state != '환불 대기' && orderDetails[0].delivery_state != '교환 대기' && orderDetails[0].delivery_state != '반품 대기'}">
				<button class="cancel-button"
					onclick="openCancelModal('${orderDetails[0].order_number}', ${orderDetails[0].book_id})">주문
					취소</button>
			</c:if>
		</div>
	</div>
</div>

	<!-- 주문 취소 모달 -->
	<div id="cancelModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeCancelModal()">&times;</span>
			<h2>취소 사유</h2>
			<br>
			<form id="cancelForm">
				<label> <input type="radio" name="cancelReason"
					value="환불 대기" required> 환불
				</label><br>
				<br> <label> <input type="radio" name="cancelReason"
					value="교환 대기"> 교환
				</label><br>
				<br> <label> <input type="radio" name="cancelReason"
					value="반품 대기"> 반품
				</label><br>
				<br>
				<br>
				<button class="cancel-button" type="button"
					onclick="submitCancel('${orderDetails[0].order_number}', ${orderDetails[0].book_id})">주문
					취소</button>
			</form>
		</div>
	</div>

	<script>
        function openCancelModal(orderNumber, bookId) {
            document.getElementById("cancelModal").style.display = "block";
        }

        function closeCancelModal() {
            document.getElementById("cancelModal").style.display = "none";
        }

        function submitCancel(orderNumber, bookId) {
            var cancelReason = $('input[name="cancelReason"]:checked').val();

            $.ajax({
                url: "${pageContext.request.contextPath}/order/cancel",
                type: 'POST',
                data: {
                    order_number: orderNumber,
                    book_id: bookId,
                    cancel_reason: cancelReason
                },
                success: function(response) {
                    alert('주문이 취소되었습니다.');
                    window.location.href = "${pageContext.request.contextPath}/order/order_list";
                },
                error: function(error) {
                    console.error(error);
                    alert('주문 취소에 실패하였습니다.');
                }
            });
        }
    </script>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
