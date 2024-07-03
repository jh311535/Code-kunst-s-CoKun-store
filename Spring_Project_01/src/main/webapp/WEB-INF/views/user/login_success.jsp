<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="kr.co.soldesk.beans.UserBean" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<% 
    // 세션에서 로그인된 사용자 정보를 가져옴
    UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean"); 
    String user_id = (loginUserBean != null && loginUserBean.isUserLogin()) ? loginUserBean.getUser_id() : "";
%>

<script>
    var user_id = "<%= user_id %>";
    alert('환영합니다. ' + user_id + ' 님!');
    location.href = '${root}/main';
</script>
