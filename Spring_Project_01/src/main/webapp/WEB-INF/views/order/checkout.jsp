<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>코쿤스토어</title>
    <link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../css/bottom.css" rel="stylesheet" type="text/css" />
    <style>
        .container {
            margin: 0 auto;
            margin-bottom: 50px;
            max-width: 800px;  
            padding: 20px;
        }
        .table-container {
            max-height: 300px; /* 원하는 높이로 조정 */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        thead, tfoot {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        .product_info {
            display: block;
            max-height: 300px; /* 원하는 높이로 조정 */
            overflow-x: hidden;
            overflow-y: scroll;
            width: 100%;
        }
        tr {
            display: table;
            width: 800px;
            table-layout: fixed;
            border-bottom: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .book-image {
            width: 50px;
            height: auto;
            margin-right: 10px;
        }
        .book-info {
            display: flex;
            align-items: center;
            justify-content: left;
        }
        .book-info img {
            margin-left: 30px;
            margin-right: 30px;
        }
        .container h1, h2 {
            color: #333;
        }
        .container form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        form input[type="text"],
        form input[type="email"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
        }
        .user_name .username {        
            width: 190px;
            margin-right: 415;
        }       
        .phone_number .phone-part {
            width: 60px;
            margin-right: 5px;
            display: inline-block;
        }
        .email_css .email {
            width: 238px;
        }
        .address-wrapper {
            display: flex;
            flex-direction: column;
        }
        .address-wrapper input {
            margin-bottom: 5px;
        }
        .address-wrapper .postal-code {
            width: 100px;
            height: 30px;
        }
        .address-wrapper .address {
            width: 350px;
            height: 30px;
        }
        .radio-wrapper {
            display: flex;
            flex-direction: row;
            align-items: center;
        }
        .radio-wrapper label {
            margin-right: 20px;
        }
        .radio-wrapper input[type="text"] {
            width: 100%;
            margin-top: 5px;
            box-sizing: border-box;
        }
        .textarea-wrapper {
            display: none;
            width: 100%;
            margin-top: 5px;
        }
        .textarea-wrapper textarea {
            width: 100%;
            height: 60px;
            resize: none;
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
        .bold-label {
            font-weight: bold;
            width: 150px;
            background-color: #f2f2f2;
        }
        .address-search-btn {
            margin-left: 10px;
            padding: 8px 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .address-search-btn:hover {
            background-color: #45a049;
        }
        .book-info span {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: 350px; /* 필요한 경우 너비 조정 */            
            display: inline-block;
        }
       
        .price-column, .quantity-column {
            width: 100px; /* 필요한 경우 너비 조정 */
            display:fixed;
        }
    </style>
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        $(document).ready(function() {
            $('input[name="delivery_location"]').on('click', function() {
                toggleTextarea(this);
            });

            // 기본 선택된 라디오 버튼에 맞게 텍스트 영역 표시
            toggleTextarea(document.querySelector('input[name="delivery_location"]:checked'));

            $('#payButton').on('click', function() {
                requestPay();
            });

            // 총 결제 금액 계산
            updateTotalAmount();
        });

        function toggleTextarea(radio) {
            var textareas = document.querySelectorAll('.textarea-wrapper');
            textareas.forEach(function(textarea) {
                textarea.style.display = 'none';
            });
            if (radio.checked) {
                var textareaId = 'textarea_' + radio.value;
                var textareaElement = document.getElementById(textareaId);
                if (textareaElement) {
                    textareaElement.style.display = 'block';
                }
            }
        }

        function DaumPostCode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    var addr = data.roadAddress;
                    document.getElementById('postal_code').value = data.zonecode;
                    document.getElementById('address').value = addr ? addr : data.jibunAddress;
                    document.getElementById('detailed_address').focus();
                }
            }).open();
        }

        function combinePhone() {
            var phone1 = document.getElementById('phone1').value;
            var phone2 = document.getElementById('phone2').value;
            var phone3 = document.getElementById('phone3').value;
            document.getElementById('phone').value = phone1 + phone2 + phone3;
        }

        function updateTotalAmount() {
            var totalAmount = 0;
            <%-- 단일 구매 --%>
            <c:if test="${not empty book}">
                totalAmount = ${book.book_price} * ${orderBean.order_quantity};
            </c:if>
            <%-- 장바구니 구매 --%>
            <c:if test="${not empty books}">
                <c:forEach var="book" items="${books}">
                    totalAmount += ${book.book_price} * ${book.quantity};
                </c:forEach>
            </c:if>
            $('#totalAmount').val(totalAmount);
        }

        function requestPay() {
            var IMP = window.IMP;
            IMP.init('YOUR API KEY');

            var totalAmount = 0;
            <c:if test="${not empty books}">
                <c:forEach var="book" items="${books}">
                    totalAmount += ${book.book_price} * ${book.quantity};
                </c:forEach>
            </c:if>
            <c:if test="${not empty book}">
                totalAmount = ${book.book_price} * ${orderBean.order_quantity};
            </c:if>

            IMP.request_pay({
                pg: 'kakaopay',
                pay_method: 'card',
                merchant_uid: 'merchant_' + new Date().getTime(),
                name: '코드쿤스트 결제 서비스',
                amount: totalAmount,
                buyer_email: $('#email').val(),
                buyer_name: $('#ordererName').val(),
                buyer_tel: $('#phone').val(),
                buyer_addr: $('#address').val(),
                buyer_postcode: $('#postal_code').val()
            }, function (rsp) {
                if (rsp.success) {
                    var data = {
                        imp_uid: rsp.imp_uid,
                        merchant_uid: rsp.merchant_uid,
                        paid_amount: rsp.paid_amount,
                        ordererName: $('#ordererName').val(),
                        addressnum: $('#postal_code').val(),
                        address: $('#address').val(),
                        detailaddress: $('#detailed_address').val(),
                        phone: $('#phone').val(),
                        email: $('#email').val(),
                        request: $('input[name="delivery_location"]:checked').val(),
                        request_text: $('textarea:visible').val(),
                        user_idx: ${user.user_idx},
                        cart_ids: []
                    };

                    // 장바구니에서 구매 시 book_ids, order_quantities, cart_ids 추가
                    <c:if test="${not empty orderBean.book_id}">
                        data.book_id = ${orderBean.book_id};
                        data.order_quantity = ${orderBean.order_quantity};
                    </c:if>

                    <c:if test="${not empty orderBean.cart_ids}">
                        data.book_ids = [];
                        data.order_quantities = [];
                        data.cart_ids = [];

                        <c:forEach var="book" items="${books}">
                            data.book_ids.push(${book.book_id});
                            data.order_quantities.push(${book.quantity});
                        </c:forEach>
                        
                        <c:forEach var="cart_id" items="${orderBean.cart_ids}">
                            data.cart_ids.push(${cart_id});
                        </c:forEach>
                        
                        // JSON으로 배열을 문자열로 변환
                        data.book_ids = JSON.stringify(data.book_ids);
                        data.order_quantities = JSON.stringify(data.order_quantities);
                        data.cart_ids = JSON.stringify(data.cart_ids);
                    </c:if>

                    $.ajax({
                        url: "${root}order/submit",
                        method: "POST",
                        data: data,
                        success: function(response) {
                        	alert("결제가 정상 처리 되었습니다.");
                            window.location.href = "${root}order/order_success?order_number=" + response.order_number;
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.error("AJAX Error: ", textStatus, errorThrown);
                            console.error("Response Text: ", jqXHR.responseText);
                            alert("주문 처리 중 오류가 발생했습니다.");
                        }
                    });
                } else {
                    var msg = '결제에 실패하였습니다.';
                    msg += '에러내용 : ' + rsp.error_msg;
                    alert(msg);
                }
            });
        }



    </script>
</head>
<body>
    <c:import url="/WEB-INF/views/include/top_menu.jsp" />
    <div class="container">
        <h1>주문</h1>
        <hr />
        <br />
        <h2>제품 정보</h2>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>상품명</th>
                        <th class="price-column">가격</th>
                        <th class="quantity-column">수량</th>
                    </tr>
                </thead>
                <tbody class="product_info">
                    <c:if test="${not empty book}">
                        <tr>
                            <td class="book-info"><img src="${book.book_pic}" class="book-image" alt="${book.book_name}"><span>${book.book_name}</span></td>
                            <td class="price-column">${book.book_price} 원</td>
                            <td class="quantity-column">${orderBean.order_quantity}</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty books}">
                        <c:forEach var="book" items="${books}">
                            <tr>
                                <td class="book-info"><img src="${book.book_pic}" class="book-image" alt="${book.book_name}"><span>${book.book_name}</span></td>
                                <td class="price-column">${book.book_price} 원</td>
                                <td class="quantity-column">${book.quantity}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
        <br />
        <br />
        <br />
        <br />
        <h2>배송 정보</h2>
        <form id="orderForm">
            <input type="hidden" id="totalAmount" name="totalAmount">
            <table>
                <tr>
                    <td class="bold-label">수령인</td>
                    <td class="user_name">
                        <div style="display: flex; align-items: center;">
                            <input type="text" id="ordererName" name="ordererName" class="username" value="${user.user_name}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="bold-label">주소</td>
                    <td class="address-wrapper">
                        <div style="display: flex; align-items: center;">
                            <input type="text" id="postal_code" name="addressnum" class="postal-code" placeholder="우편번호" value="${user.addressnum}">
                            <button type="button" class="address-search-btn" onclick="DaumPostCode()" style="vertical-align: middle;">주소 검색</button>
                        </div>
                        <input type="text" id="address" name="address" class="address" placeholder="주소" value="${user.address}">
                        <input type="text" id="detailed_address" name="detailaddress" class="address" placeholder="상세주소" value="${user.detailaddress}">
                    </td>
                </tr>
                <tr>
                    <td class="bold-label">휴대전화</td>
                    <td class="phone_number">
                        <div style="display: flex; align-items: center;">
                            <input type="text" id="phone1" class="phone-part" maxlength="3" value="${user.phone.substring(0, 3)}">&nbsp- &nbsp
                            <input type="text" id="phone2" class="phone-part" maxlength="4" value="${user.phone.substring(3, 7)}">&nbsp- &nbsp
                            <input type="text" id="phone3" class="phone-part" maxlength="4" value="${user.phone.substring(7)}">
                            <input type="hidden" id="phone" name="phone" value="${user.phone}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="bold-label">이메일</td>
                    <td class="email_css">
                        <div style="display: flex; align-items: center;">
                            <input type="email" id="email" name="email" class="email" value="${user.email}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="bold-label">수령장소</td>
                    <td>
                        <div class="radio-wrapper">
                            <label><input type="radio" name="delivery_location" value="문앞" onclick="toggleTextarea(this)" checked> 문앞</label>
                            <label><input type="radio" name="delivery_location" value="경비실" onclick="toggleTextarea(this)"> 경비실</label>
                            <label><input type="radio" name="delivery_location" value="택배함" onclick="toggleTextarea(this)"> 택배함</label>
                            <label><input type="radio" name="delivery_location" value="직접입력" onclick="toggleTextarea(this)"> 직접입력</label>
                        </div>
                        <div id="textarea_문앞" class="textarea-wrapper">
                            <textarea name="request_text_문앞" placeholder="공동현관 비밀번호를 입력하세요"></textarea>
                        </div>
                        <div id="textarea_경비실" class="textarea-wrapper">
                            <textarea name="request_text_경비실" placeholder="경비실 비밀번호를 입력하세요"></textarea>
                        </div>
                        <div id="textarea_택배함" class="textarea-wrapper">
                            <textarea name="request_text_택배함" placeholder="택배함 비밀번호를 입력하세요"></textarea>
                        </div>
                        <div id="textarea_직접입력" class="textarea-wrapper">
                            <textarea name="request_text_직접입력" placeholder="수령장소를 입력하세요"></textarea>
                        </div>
                    </td>
                </tr>
            </table>
            <button type="button" id="payButton">결제하기</button>
        </form>
    </div>
    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />
    <c:import url="/WEB-INF/views/include/sideBar.jsp" />
</body>
</html>
