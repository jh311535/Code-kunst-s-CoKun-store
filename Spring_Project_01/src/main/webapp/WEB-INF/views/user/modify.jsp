<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="../css/bottom.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function DaumPostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = data.roadAddress;
            document.getElementById('addressnum').value = data.zonecode;
            document.getElementById('address').value = addr ? addr : data.jibunAddress;
            document.getElementById('detailaddress').focus();
        }
    }).open();
}

function validatePassword() {
    var password = document.getElementById("user_pw").value;
    var confirmPassword = document.getElementById("user_pw2").value;
    if (password != confirmPassword) {
        alert("비밀번호 확인이 일치하지 않습니다.");
        return false;
    }
    return true;
}
</script>
<style>
body{
   width: 100%;
   }

.container {
	display: flex;
	flex: 1;
	background-color: #f7f7f7;
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

.main-content {
	margin: 0 auto;
	padding: 15px;
	flex: 1;
	display: flex;
	justify-content: center;
	align-items: center;
	height: calc(100vh - 40px);
}

.form-container {
	width: 100%;
	max-width: 700px;
}

.form-group {
	margin-bottom: 10px;
	display: flex;
	align-items: center;
}

.form-group label {
	flex: 1;
	text-align: right;
	margin-right: 10px;
}

.form-group input, .form-group button {
	flex: 2;
	padding: 5px;
	margin-right: 5px;
}

.btn-submit {
	background-color: #4CAF50;
	color: white;
	padding: 8px;
	border: 1px solid #4CAF50;
	border-radius: 6px;
	width: 100px;
	cursor: pointer;
	text-align: center;
}

.btn-primary {
	background-color: #4CAF50;
	color: white;
	padding: 6px;
	border: 1px solid #4CAF50;
	border-radius: 6px;
	cursor: pointer;
}

.table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	font-size: 16px;
}

.table th, .table td {
	border: 1px solid #CCC;
	padding: 12px 14px 12px 12px;
	text-align: left;
	background-color: #f0f0f0;
}

.table th {
	text-align: center;
}

.table td {
	background-color: #fff;
}

.text-center {
	text-align: center;
}

.input-group {
	display: flex;
}

.input-group-append {
	margin-left: 5px;
}

.error-message {
	font-size: 12px;
	color: red;
	margin-top: 2px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
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

		<div class="main-content">
			<div class="form-container">
				<h2 class="text-center">회원정보수정</h2>
				<form:form action="${root}/user/modify_pro" method="post"
					modelAttribute="modifyUserBean"
					onsubmit="return validatePassword();">
					<table class="table">
						<tr>
							<th>아이디</th>
							<td><form:input path="user_id" class='form-control'
									disabled="true" readonly="readonly" placeholder="사용자 ID" /> <form:hidden
									path="user_id" /></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td><form:input path="user_nickname" class='form-control'
									disabled="true" readonly="readonly" placeholder="닉네임" /> <form:hidden
									path="user_nickname" /></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><form:input path="user_name" class='form-control'
									placeholder="성명" /> <form:errors path="user_name"
									cssClass="error-message" /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><form:input path="email" class='form-control'
									disabled="true" readonly="readonly" placeholder="이메일 주소" /> <form:hidden
									path="email" /></td>
						</tr>
						<tr>
							<th>휴대폰 번호</th>
							<td><form:input path="phone" class='form-control'
									placeholder="휴대폰 번호 (-제외)" /> <form:errors path="phone"
									cssClass="error-message" /></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td>
								<div class="input-group">
									<form:input path="addressnum" id="addressnum"
										placeholder="우편번호" class="form-control" />
									<form:errors path="addressnum" cssClass="error-message" />
									<div class="input-group-append">
										<button type="button" class="btn btn-primary"
											onclick="DaumPostCode()">우편번호 찾기</button>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th>주소</th>
							<td><form:input path="address" id="address"
									placeholder="도로명 주소" class='form-control space-between-inputs' />
								<form:errors path="address" cssClass="error-message" /></td>
						</tr>
						<tr>
							<th>상세주소</th>
							<td><form:input path="detailaddress" id="detailaddress"
									placeholder="상세 주소" class='form-control' /> <form:errors
									path="detailaddress" cssClass="error-message" /></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><form:password path="user_pw" class='form-control'
									id="user_pw" placeholder="비밀번호" /> <form:errors path="user_pw"
									cssClass="error-message" /></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><form:password path="user_pw2" class='form-control'
									id="user_pw2" placeholder="비밀번호 확인" /> <form:errors
									path="user_pw2" cssClass="error-message" /></td>
						</tr>
					</table>
					<div class="text-center" style="margin-top: 20px;">
						<button type="submit" class="btn btn-submit">정보수정</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
