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
<link href="../css/reviewWrite.css" rel="stylesheet" type="text/css" />
<!-- Bootstrap CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body class="review-write-body">

    <c:import url="/WEB-INF/views/include/top_menu.jsp" />

    <div class="container review-write-container">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <div class="card shadow">
                    <div class="card-body">
                        <form:form action="${root}review/review_write_pro" method="post" modelAttribute="reviewBean">
                            <form:hidden path="book_id" />

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
                                <form:label path="review_content" style="margin-bottom: 10px;">내용</form:label>
                                <form:textarea path="review_content" class="form-control" style="resize:none"/>
                                <button type="submit" class="btn btn-primary">작성하기</button>
                                <form:errors path="review_content" style="color:red; display:block; width:100%; margin-top: 10px;" />
                            </div>

                        </form:form>
                    </div>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>

    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>