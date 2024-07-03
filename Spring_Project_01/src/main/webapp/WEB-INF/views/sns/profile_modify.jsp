<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="../css/bottom.css" rel="stylesheet" type="text/css" />
<link href="../css/profile_modify.css" rel="stylesheet" type="text/css" />
</head>
<body>

    <c:import url="/WEB-INF/views/include/top_menu.jsp" />

<div class="container" style="margin-top:50px">
    <h1 class="text-center">프로필 수정</h1>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <form:form modelAttribute="profile" action="${root}/sns/profile_modify_pro" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <form:label path="userNickname">닉네임:</form:label>
                            <form:input path="userNickname" class="form-control"/>
                            <form:errors path="userNickname" cssClass="text-danger"/>
                        </div>
                        <div class="form-group">
                            <form:label path="introduce">소개:</form:label>
                            <form:textarea path="introduce" class="form-control" rows="5"/>
                            <form:errors path="introduce" cssClass="text-danger"/>
                        </div>
                        <div class="form-group">
  							<form:label path="upload_file">첨부 이미지</form:label>
                            <div class="mb-2">
                                <img class="user-img img-thumbnail" src="${root}/upload/${profile.user_pic}" alt="User Image"/>
                            </div>
                  			<form:input type='file' path='upload_file' class="form-control" accept="upload/*"/>
                        </div>
                        <div class="form-group text-right">
                            <button type="submit" class="btn btn-primary">수정</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>
