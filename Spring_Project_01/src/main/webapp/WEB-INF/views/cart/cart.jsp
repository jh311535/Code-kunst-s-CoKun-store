<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />

<!DOCTYPE html>
<html>
<head>
    <title>코쿤스토어</title>
    <link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../css/bottom.css" rel="stylesheet" type="text/css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style type="text/css">
        .content {
            margin-top: 50px;
            max-width: 1200px;
            margin: 0 auto;
        }

        table {
            width: 100%;
            text-align: center;
            border-collapse: collapse;
        }

        #thead {
            border-top: 1px solid #000;
            border-bottom: 1px solid #000;
        }

        #tfoot {
            border-bottom: 1px solid #000;
            border-top: 1px solid #000;
            width: 100%;
        }

        #tbody {
            display: block;
            height: 400px;
            overflow-y: scroll;
        }

        tr {
            display: table;
            width: 100%;
            table-layout: fixed;
            border-bottom: 1px solid #000;
        }

        th, td {
            padding: 10px;
        }

        button {
            height: 30px;
            background-color: white;
            border: none;
            border-radius: 10%;
            background-color: #4CAF50;
            color: white;
        }
        button:hover {
            background-color: #45a049;
        }
        .button-container {
            margin-top: 20px;
            margin-bottom: 50px;            
            text-align: center;
        }

        .order-button, .continue-shopping-button {
            margin: 10px;
            font-size: 20px;
            cursor: pointer;
            width: 150px;
            height: 50px;
            border-radius: 5%;
        }
    </style>
</head>
<body>
    <c:import url="/WEB-INF/views/include/top_menu.jsp" />
    <div class="content">
        <br />
        <h1>장바구니</h1>
        <br />
        <table>
            <thead id="thead">
                <tr>
                    <th width="95"><input type="checkbox" id="check_all" /></th>
                    <th></th>
                    <th width="490">상품명</th>
                    <th width="190">수량</th>
                    <th width="115">가격</th>
                    <th width="100"></th>
                </tr>
            </thead>
            <tbody id="tbody">
                <input type="hidden" id="user_idx" value="${user_idx}" />
                <c:forEach var="cart" items="${cartList}">
                    <tr id="cart_row_${cart.cart_id}">
                        <td><input type="checkbox" class="chk"
                            id="cart_check_${cart.cart_id}" style="margin-left: 6px;" value="${cart.cart_id}" checked />
                        </td>
                        <td><img src="${cart.book.book_pic}" width="100" height="100">
                        </td>
                        <td width="500"><a
                            href="${root}books/bookDetail?book_id=${cart.book.book_id}">${cart.book.book_name}</a></td>
                        <td width="200"><input type="number"
                            id="cart_quantity_${cart.cart_id}" class="cart_quantity"
                            style="width: 50px; height: 27px; border-radius: 10%; border: 1px solid;"
                            value="${cart.cart_quantity}" min="1" max="10">
                            <button type="button" onclick="updateCart(${cart.cart_id})">수정</button>
                        </td>
                        <td data-price-per-unit="${cart.book.book_price}">${cart.book.book_price * cart.cart_quantity}
                            원</td>
                        <td>
                            <button type="button" onclick="removeFromCart(${cart.cart_id})">삭제</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot id="tfoot">
                <tr>
                    <th width="50">
                        <button type="button" onclick="removeSelected()">선택 삭제</button>
                    </th>
                    <th width="150">총 상품 가격 : <span id="total_price">0</span> 원
                    </th>
                    <th width="50">+</th>
                    <th width="120">배송비 : 3000 원</th>
                    <th width="50">=</th>
                    <th width="150">총 결제 비용 : <span id="total_cost">3000</span> 원
                    </th>
                </tr>
            </tfoot>
        </table>

        <div class="button-container">
            <button class="continue-shopping-button" onclick="continueShopping()">계속
                쇼핑하기</button>
            <button class="order-button" onclick="order()">주문하기</button>
        </div>
    </div>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
<script>
    $(document).ready(function() {
        updateTotalPrice();
        
        $('#check_all').click(function() {
            $('.chk').prop('checked', this.checked);
            updateTotalPrice();
        });

        $('.chk').change(function() {
            updateTotalPrice();
        });
    });

    function updateCart(cart_id) {
        var cart_quantity = $('#cart_quantity_'+cart_id).val();
        $.ajax({
            url: '${root}cart/update',
            type: 'POST',
            data: {
                cart_id: cart_id,
                cart_quantity: cart_quantity
            },
            success: function(response) {
                alert('수량이 변경 되었습니다.');
                // 수량 변경 후 해당 항목의 가격 업데이트
                var pricePerUnit = parseInt($('#cart_row_' + cart_id).find('td:nth-child(5)').data('price-per-unit'));
                var newPrice = pricePerUnit * cart_quantity;
                $('#cart_row_' + cart_id).find('td:nth-child(5)').text(newPrice + ' 원');
                updateTotalPrice();
            },
            error: function(error) {
                console.error(error);
                alert('수량을 변경하지 못했습니다.');
            }
        });
    }

    function removeFromCart(cart_id) {
        $.ajax({
            url: '${root}cart/remove',
            type: 'POST',
            data: {
                cart_id: cart_id
            },
            success: function(response) {
                alert('삭제되었습니다.');
                // 해당 항목만 삭제
                $('#cart_row_' + cart_id).remove();
                updateTotalPrice();
            },
            error: function(error) {
                console.error(error);
                alert('삭제를 실패했습니다.');
            }
        });
    }

    function removeSelected() {
        var selected = [];
        var user_idx = $('#user_idx').val();
        $('.chk:checked').each(function() {
            selected.push($(this).val());
        });

        console.log("Selected IDs:", selected); // 선택된 항목 출력
        console.log("User ID:", user_idx); // 유저 ID 출력

        if (selected.length > 0) {
            var requestData = {
                cart_ids: selected,
                user_idx: user_idx
            };
            console.log("Request Data:", requestData); // 요청 데이터 출력

            $.ajax({
                url: "${pageContext.request.contextPath}/cart/removeSelected",
                type: 'POST',
                data: requestData,
                traditional: true,
                success: function(response) {
                    alert('선택한 항목이 삭제되었습니다.');
                    // 선택된 항목들만 삭제
                    selected.forEach(function(cart_id) {
                        $('#cart_row_' + cart_id).remove();
                    });
                    updateTotalPrice();
                },
                error: function(error) {
                    console.error("Error:", error);
                    alert('삭제를 실패했습니다.');
                }
            });
        } else {
            alert('삭제할 항목이 없습니다.');
        }
    }

    function updateTotalPrice() {
        var total = 0;
        var itemCount = 0;

        $('.chk:checked').each(function() {
            var cart_id = $(this).val();
            var quantity = parseInt($('#cart_quantity_' + cart_id).val());
            var pricePerUnit = parseInt($('#cart_row_' + cart_id).find('td:nth-child(5)').data('price-per-unit'));
            var price = quantity * pricePerUnit;
            total += price;
            itemCount++;
        });

        var shippingCost = (itemCount > 0) ? 3000 : 0;

        $('#total_price').text(total);
        $('#total_cost').text(total + shippingCost);
    }
    
    function order() {
        var selectedBooks = [];
        var user_idx = $('#user_idx').val();

        $('.chk:checked').each(function() {
            var cart_id = $(this).val();
            var quantity = parseInt($('#cart_quantity_' + cart_id).val());
            var book_id = $(this).closest('tr').find('a').attr('href').split('book_id=')[1];

            console.log("Cart ID:", cart_id, "Book ID:", book_id, "Quantity:", quantity); // 각 항목의 값 출력

            selectedBooks.push({ cart_id: cart_id, book_id: book_id, quantity: quantity });
        });

        // 전체 selectedBooks 배열 출력
        console.log("Selected Books:", JSON.stringify(selectedBooks));

        if (selectedBooks.length > 0) {
            var form = $('<form action="' + "${root}" + 'order/checkout" method="post"></form>');
            $.each(selectedBooks, function(index, book) {
                form.append('<input type="hidden" name="book_ids" value="' + book.book_id + '">');
                form.append('<input type="hidden" name="order_quantities" value="' + book.quantity + '">');
                form.append('<input type="hidden" name="cart_ids" value="' + book.cart_id + '">');
            });

            // 폼 데이터를 로그로 출력
            console.log("Form Data:", form.serialize());

            form.appendTo('body').submit();
        } else {
            alert('구매할 항목을 선택해 주세요.');
        }
    }



    function continueShopping() {
    	window.location.href = '${root}main';
    }
</script>

</html>
