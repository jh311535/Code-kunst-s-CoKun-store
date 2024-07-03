<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>코쿤스토어</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link href="${root}css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="${root}css/bottom.css" rel="stylesheet" type="text/css" />
    <link href="${root}css/chat.css" rel="stylesheet" type="text/css" />
    <link href="${root}css/top_menu.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <c:import url="/WEB-INF/views/include/top_menu.jsp"/>

    <div class="container mt-5">
        <div class="chat-container mt-4">
           <h2>코쿤스토어 ChatBot</h2>
            <c:forEach var="message" items="${sessionScope.chatHistory}">
                <div class="chat-message ${message.sender eq 'user' ? 'right' : 'left'}">
                    <div class="message-info ${message.sender eq 'user' ? 'right' : 'left'}">
                        <span>${message.sender eq 'user' ? '나' : 'chat bot'}</span>
                    </div>
                    <div class="message-text">
                        ${message.text}
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="chat-input mt-3">
            <form class="input-group" action="${root}chat" method="get">
                <input type="text" class="form-control" placeholder="Type Message ..." name="prompt" required>
                <input type="hidden" name="chat" value="true">
                <div class="input-group-append">
                    <button class="btn btn-success" type="submit">Send</button>
                </div>
            </form>
        </div>
    </div>

    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>