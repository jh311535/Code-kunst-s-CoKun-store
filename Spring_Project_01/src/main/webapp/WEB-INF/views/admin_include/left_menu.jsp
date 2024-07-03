<%-- File Location: /views/admin_include/left_menu.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<style>
.logo {
	display: flex; /* Flex 컨테이너로 설정 */
	align-items: center; /* 수직 가운데 정렬 */
	justify-content: center; /* 수평 가운데 정렬 */
}

.logo img {
	max-height: 100px; /* 로고 이미지의 높이 */
}

.user-panel.mt-3.pb-3.mb-3.d-flex {
	justify-content: center; /* 수평 가운데 정렬 */
}
</style>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<div class="logo">
		<a href="${root}admin/home" class="brand-link"> <img
			src="${root}image/logo_dark.png" alt="로고">
		</a>
	</div>
	<div class="sidebar">
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<c:if test="${not empty sessionScope.admin.admin_pic}">
				<div class="image">
					<img src="${root}upload/${sessionScope.admin.admin_pic}"
						class="img-circle elevation-2" alt="Admin Image">
				</div>
			</c:if>
			<div class="info">
				<a
					href="${root}admin/adminInfo/detail/${sessionScope.admin.admin_idx}"
					class="d-block"> 관리자 : ${sessionScope.admin.admin_name}</a>
			</div>
		</div>
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<li class="nav-item"><a href="${root}admin/userInfo/manage"
					class="nav-link"> <i class="nav-icon fas fa-user"></i>
						<p>유저 정보 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/adminInfo/manage"
					class="nav-link"> <i class="nav-icon fas fa-user-shield"></i>
						<p>관리자 정보 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/notice/manage"
					class="nav-link"> <i class="nav-icon fas fa-bell"></i>
						<p>공지사항 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/book/manage"
					class="nav-link"> <i class="nav-icon fas fa-book"></i>
						<p>책 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/curation/manage"
					class="nav-link"> <i class="nav-icon fas fa-cube"></i>
						<p>큐레이션 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/cart/manage"
					class="nav-link"> <i class="nav-icon fas fa-shopping-cart"></i>
						<p>장바구니 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/order/manage"
					class="nav-link"> <i class="nav-icon fas fa-truck"></i>
						<p>주문 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/review/manage"
					class="nav-link"> <i class="nav-icon fas fa-star"></i>
						<p>리뷰 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/board/1/manage"
					class="nav-link"> <i class="nav-icon fas fa-th"></i>
						<p>유저 게시글 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/userComment/manage"
					class="nav-link"> <i class="nav-icon fas fa-comments"></i>
						<p>유저 댓글 관리</p>
				</a></li>
				<li class="nav-item"><a href="${root}admin/board/2/manage"
					class="nav-link"> <i class="nav-icon fas fa-question-circle"></i>
						<p>Q&A 게시판 관리</p>
				</a></li>
			</ul>
		</nav>
	</div>
</aside>
