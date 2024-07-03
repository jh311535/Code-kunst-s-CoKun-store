<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>코쿤스토어</title>
    <style>
        body {
            background-color: #f7f7f7;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .order-complete-container {
            max-width: 600px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #fff;
            text-align: center;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .order-complete-container h1 {
            margin-bottom: 20px;
        }
        .order-complete-container table {
            width: 100%;
            margin-bottom: 20px;
            border-collapse: collapse;
        }
        .order-complete-container table th,
        .order-complete-container table td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        .order-complete-container .scrollable {
            max-height: 150px; /* 적절한 높이 설정 */
            overflow-y: auto;
            text-align: left;
        }
        .order-complete-container .book-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 5px; /* 책 항목 사이에 간격 추가 */
        }
        .order-complete-container .book-title {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: 300px; /* 적절한 너비 설정 */
            text-align: left;
        }
        .order-complete-container .book-quantity {
            margin-left: 10px; /* 수량과 제목 사이의 간격 */
            text-align: right;
            flex-shrink: 0;
        }
        .order-complete-container .btn-home {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .order-complete-container .btn-home:hover {
            background-color: #45a049;
        }
        .order-complete-container .left-align {
            text-align: left;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        // 뒤로가기 방지
        function preventBack() {
            history.pushState(null, document.title, location.href);
            window.addEventListener('popstate', function(event) {
                history.pushState(null, document.title, location.href);
            });
        }

        // 페이지가 로드되었을 때 뒤로가기 방지 함수 호출
        $(document).ready(function() {
            preventBack();
        });
    </script>
</head>
<body>
    <div class="order-complete-container">
        <h1>주문완료</h1>
        <table>
            <tr>
                <th>주문번호</th>
                <td><c:out value="${orderDetails[0].order_number}"/></td>
            </tr>
            <tr>
                <th>주문 상품</th>
                <td class="scrollable">
                    <c:forEach var="order" items="${orderDetails}">
                        <div class="book-info">
                            <span class="book-title"><c:out value="${order.book_name}"/></span>
                            <span class="book-quantity">${order.order_quantity}권</span>
                        </div>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>배송지</th>
                <td class="left-align">
                    <c:out value="${orderDetails[0].orderer_name}"/> <br /> 
                    <c:out value="${orderDetails[0].phone.substring(0, 3)}"/>-
                    <c:out value="${orderDetails[0].phone.substring(3, 7)}"/>-
                    <c:out value="${orderDetails[0].phone.substring(7)}"/><br />
                    <c:out value="${orderDetails[0].address}"/> <c:out value="${orderDetails[0].detailaddress}"/>
                </td>
            </tr>
            <tr>
                <th>배송 메모</th>
                <td class="left-align"><c:out value="${orderDetails[0].request_text}"/></td>
            </tr>
        </table>
        <button class="btn-home" onclick="location.href='${pageContext.request.contextPath}/main'">홈으로</button>
    </div>
</body>
</html>
