<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="../css/bottom.css" rel="stylesheet" type="text/css" />
<link href="../css/review_modify.css" rel="stylesheet" type="text/css" />
</head>
<body class="review-write-body">

    <c:import url="/WEB-INF/views/include/top_menu.jsp" />

    <div class="container review-write-container">
        <div class="card shadow">
            <div class="card-body">
                <h1>리뷰 수정</h1>
                <form:form modelAttribute="modifyReviewBean" action="${root}review/modifyReview_pro" method="post">
                    <form:hidden path="review_id" />
                    <form:hidden path="user_idx" />
                    <form:hidden path="book_id" />
                    <form:hidden path="review_date" />
                    
                    <div class="form-group">
                        <form:label path="bookName">책 이름:</form:label>
                        <form:input path="bookName" readonly="true" class="form-control readonly" />
                    </div>
                    <div class="form-group">
                        <form:label path="userNickName">작성자:</form:label>
                        <form:input path="userNickName" readonly="true" class="form-control readonly" />
                    </div>
                    <div class="form-group">
                        <form:label path="rating">평점</form:label>
                        <form:select path="rating" class="form-control form-control-rating">
                            <form:option value="1">1</form:option>
                            <form:option value="2">2</form:option>
                            <form:option value="3">3</form:option>
                            <form:option value="4">4</form:option>
                            <form:option value="5">5</form:option>
                        </form:select>
                        <form:errors path="rating" style="color:red" />
                    </div>
                    <div class="form-group form-group-textarea">
                        <form:label path="review_content" style="margin-bottom: 10px;">리뷰 내용</form:label>
                        <form:textarea path="review_content" class="text-form-control"/>
                        <form:errors path="review_content" style="color:red; display:block; width:100%; margin-top: 10px;" />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">수정</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>
