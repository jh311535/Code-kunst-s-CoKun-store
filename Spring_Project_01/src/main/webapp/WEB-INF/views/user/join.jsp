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

</script>

<style>
body {
	font-family: Arial, sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	background-color: #f7f7f7;
}

.container {
	width: 300px;
	text-align: center;
	padding: 15px;
	border: 1px solid #CCC;
	border-radius: 6px;
	background-color: #fff;
}

.form-group {
	margin-bottom: 10px;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	width: 100%;
}

.form-group .input-group {
	width: 100%;
	display: flex;
	align-items: center;
}

.form-group input {
	width: 100%;
	padding: 5px;
	box-sizing: border-box;
}

input#addressnum {
	margin-left: 6px;
}

input#user_pw {
	margin-left: 5px;
}

input#user_pw2 {
	margin-left: 5px;
}

input#user_id {
	margin-left: 5px;
}

input#user_name {
	margin-left: 5px;
}

input#phone {
	margin-left: 3.5px;
}

input#email {
	margin-left: 4px;
}

.form-group .input-group-prepend {
	margin-right: 5px;
	margin-left: -5px; /* 이 값을 조정하여 아이콘을 왼쪽으로 이동 */
}

.form-group .input-group-prepend .input-group-text {
	background: #fff;
	border: none;
	padding: 0;
}

.form-group .input-group-prepend .fas {
	font-size: 14px;
	color: #555;
}

.btn-primary {
	margin-left: 10px;
	padding: 5px 10px;
	font-size: 12px;
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
	border-radius: 6px;
	cursor: pointer;
	white-space: nowrap;
}

.btn-submit {
	background-color: #4CAF50;
	color: white;
	padding: 8px;
	border: 1px solid #4CAF50;
	border-radius: 6px;
	width: 100%;
	cursor: pointer;
}

.login-link {
	margin-top: 10px;
}

.error-message {
	font-size: 12px;
	color: red;
	margin-top: 2px;
}

.input-group {
	display: flex;
	width: 100%;
}

.input-group-prepend {
	display: flex;
	align-items: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script>
    function checkUserIdExist() {
        var user_id = $("#user_id").val();
        if (user_id.length == 0) {
            alert('아이디를 입력해주세요');
            return;
        }

        $.ajax({
            url: '${root}user/checkUserIdExist/' + user_id,
            type: 'get',
            dataType: 'text',
            success: function(result) {
                if (result.trim() == 'true') {
                    alert('사용할 수 있는 아이디입니다');
                    $("#userIdExist").val('true');
                } else if (result.trim() == 'false') {
                    alert('사용할 수 없는 아이디 입니다');
                    $("#userIdExist").val('false');
                }
            }
        });
    }

    function checkUserNicknameExist() {
        var user_nickname = $("#user_nickname").val();
        if (user_nickname.length == 0) {
            alert('닉네임을 입력해주세요');
            return;
        }

        $.ajax({
            url: '${root}user/checkUserNicknameExist/' + user_nickname,
            type: 'get',
            dataType: 'text',
            success: function(result) {
                if (result.trim() == 'true') {
                    alert('사용할 수 있는 닉네임입니다');
                    $("#userNicknameExist").val('true');
                } else if (result.trim() == 'false') {
                    alert('사용할 수 없는 닉네임 입니다');
                    $("#userNicknameExist").val('false');
                }
            }
        });
    }

    function checkUserEmailExist() {
        var email = $("#email").val();
        if (email.length == 0) {
            alert('이메일을 입력해주세요');
            return;
        }

        $.ajax({
            url: '${root}user/checkUserEmailExist/' + email,
            type: 'get',
            dataType: 'text',
            success: function(result) {
                if (result.trim() == 'true') {
                    alert('사용할 수 있는 이메일입니다');
                    $("#userEmailExist").val('true');
                } else if (result.trim() == 'false') {
                    alert('사용할 수 없는 이메일 입니다');
                    $("#userEmailExist").val('false');
                }
            }
        });
    }

    function resetUserIdExist() {
        $("#userIdExist").val('false');
    }

    function resetUserEmailExist() {
        $("#userEmailExist").val('false');
    }

    function resetUserNicknameExist() {
        $("#userNicknameExist").val('false');
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
</head>
<body>

	<div class="container" style="margin-top: 50px">
		<a href="${root}/main"> <img src="${root}/image/logo.png"
			class="text-center" style="width: 128px; height: auto;" />
		</a>
		<h2 class="text-center">회원가입</h2>
		<form:form action="${root}user/join_pro" method="post"
			modelAttribute="joinUserBean">
			<form:hidden path="userIdExist" id="userIdExist" />
			<form:hidden path="userEmailExist" id="userEmailExist" />
			<form:hidden path="userNicknameExist" id="userNicknameExist" />

			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-envelope"></i>
						</div>
					</div>
					<form:input path="email" class='form-control'
						onkeypress="resetUserEmailExist()" placeholder="이메일 주소" />
					<button type="button" class="btn btn-primary"
						onclick="checkUserEmailExist()">중복확인</button>
				</div>
				<form:errors path="email" cssClass="error-message" />
			</div>

			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-phone"></i>
						</div>
					</div>
					<form:input path="phone" class='form-control'
						placeholder="휴대폰 번호 (-제외)" />
				</div>
				<form:errors path="phone" cssClass="error-message" />
			</div>

			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-user"></i>
						</div>
					</div>
					<form:input path="user_name" class='form-control' placeholder="성명" />
				</div>
				<form:errors path="user_name" cssClass="error-message" />
			</div>

			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-user"></i>
						</div>
					</div>
					<form:input path="user_id" class='form-control'
						onkeypress="resetUserIdExist()" placeholder="사용자 ID" />
					<button type="button" class="btn btn-primary"
						onclick="checkUserIdExist()">중복확인</button>
				</div>
				<form:errors path="user_id" cssClass="error-message" />
			</div>

			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-user-tag"></i>
						</div>
					</div>
					<form:input path="user_nickname" class='form-control'
						onkeypress="resetUserNicknameExist()" placeholder="유저 닉네임" />
					<button type="button" class="btn btn-primary"
						onclick="checkUserNicknameExist()">중복확인</button>
				</div>
				<form:errors path="user_nickname" cssClass="error-message" />
			</div>

			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-lock"></i>
						</div>
					</div>
					<form:password path="user_pw" id="user_pw" class='form-control'
						placeholder="비밀번호" />
				</div>
				<form:errors path="user_pw" cssClass="error-message" />
			</div>

			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-lock"></i>
						</div>
					</div>
					<form:password path="user_pw2" id="user_pw2" class='form-control'
						placeholder="비밀번호 확인" />
				</div>
				<form:errors path="user_pw2" cssClass="error-message" />
			</div>

			<div class="form-group">
				<form:label path="addressnum"></form:label>
				<div class="input-group mb-2">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-map-marker-alt"></i>
						</div>
					</div>
					<form:input path="addressnum" id="addressnum" placeholder="우편번호"
						class="form-control" />
					<div class="input-group-append">
						<button type="button" class="btn btn-primary"
							onclick="DaumPostCode()">우편번호 찾기</button>
					</div>
				</div>
				<form:errors path="addressnum" cssClass="error-message" />
				<br />
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-road"></i>
						</div>
					</div>
					<form:input path="address" id="address" placeholder="도로명 주소"
						class='form-control space-between-inputs' />
				</div>
				<form:errors path="address" cssClass="error-message" />
				<br />
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-home"></i>
						</div>
					</div>
					<form:input path="detailaddress" id="detailaddress"
						placeholder="상세 주소" class='form-control' />
				</div>
				<form:errors path="detailaddress" cssClass="error-message" />
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-submit">가입</button>
			</div>
			<div class="login-link">
				계정이 있으신가요? <a href="${root}user/login">로그인</a>
			</div>
		</form:form>
	</div>

</body>
</html>
