<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }/"/>    
<script>
	alert('해당 정보를 가진 사용자를 찾을 수 없습니다.')
	location.href = "${root}user/find_id"
</script>