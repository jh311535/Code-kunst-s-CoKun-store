<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath}/" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>코쿤스토어</title>
<link href="${root}css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="${root}css/bottom.css" rel="stylesheet" type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<style>
    body {
        font-family: Arial, sans-serif;
    }

    .container {
        margin-top: 70px;
        margin-bottom: 70px;
        width: 100%;
        max-width: 600px;
        margin-left: auto;
        margin-right: auto;
    }

    .row {
        display: flex;
        justify-content: center;
    }

    .col-sm-3, .col-sm-6 {
        padding: 15px;
    }

    .col-sm-6 {
        flex: 0 0 100%;
        max-width: 100%;
    }

    .card {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border: 1px solid #e0e0e0;
        border-radius: 0.25rem;
        background-color: #fff;
        margin-bottom: 20px;
        width: 100%;
    }

    .card-body {
        padding: 1.25rem;
    }

    .form-group {
        margin-bottom: 1rem;
    }

    .form-control {
        display: block;
        width: 95%;
        padding: 0.375rem 0.75rem;
        font-size: 1rem;
        line-height: 1.5;
        color: #495057;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #ced4da;
        border-radius: 0.25rem;
        transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
    }

    .form-control:focus {
        color: #495057;
        background-color: #fff;
        border-color: #80bdff;
        outline: 0;
        box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
    }

    .btn {
        display: inline-block;
        font-weight: 400;
        color: #fff;
        text-align: center;
        vertical-align: middle;
        user-select: none;
        background-color: #007bff;
        border: 1px solid #007bff;
        padding: 0.375rem 0.75rem;
        font-size: 1rem;
        line-height: 1.5;
        border-radius: 0.25rem;
        transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        text-decoration: none;
        margin-right: 0.1rem;
        margin-bottom: 0.5rem;
    }

    .btn-primary {
        background-color: #007bff;
        border-color: #007bff;
    }

    .btn-primary:hover {
        background-color: #0056b3;
        border-color: #004085;
    }

    .btn-info {
        background-color: #17a2b8;
        border-color: #17a2b8;
    }

    .btn-info:hover {
        background-color: #117a8b;
        border-color: #0d6efd;
    }

    .btn-danger {
        background-color: #dc3545;
        border-color: #dc3545;
    }

    .btn-danger:hover {
        background-color: #c82333;
        border-color: #bd2130;
    }

    .btn-success {
        background-color: #28a745;
        border-color: #28a745;
    }

    .btn-success:hover {
        background-color: #218838;
        border-color: #1e7e34;
    }

    .text-right {
        text-align: right;
    }

    .form-group label {
        display: block;
        margin-bottom: 0.5rem;
    }

    .form-group textarea {
        resize: none;
    }

    .form-group input[type="file"] {
        display: block;
        width: 100%;
    }

    .form-group img {
        max-width: 100%;
        height: auto;
        display: block;
        margin-top: 0.5rem;
    }

    .form-group .btn {
        width: auto;
    }

    .button-group {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        gap: 0.5rem;
    }

    .owner-buttons {
        display: flex;
        gap: 0.5rem;
    }

    .ml-auto {
        margin-left: auto;
    }

    /* 페이지네이션 스타일 */
    .pagination {
        display: flex;
        padding-left: 0;
        list-style: none;
        border-radius: 0.25rem;
        justify-content: center;
    }

    .page-item {
        margin: 0 2px;
    }

    .page-item.disabled .page-link {
        color: #fff;
        pointer-events: none;
        background-color: #28a745;
        border: 1px solid #dee2e6;
    }

    .page-item.active .page-link {
        z-index: 1;
        color: #fff;
        background-color: #28a745;
        border-color: #28a745;
    }

    .page-link {
        position: relative;
        display: block;
        color: #28a745;
        text-decoration: none;
        background-color: #fff;
        border: 1px solid #28a745;
        padding: 0.5rem 0.75rem;
        margin-left: -1px;
        line-height: 1.25;
        border-radius: 0.25rem;
        transition: all 0.15s ease-in-out;
    }

    .page-link:hover {
        color: #fff;
        background-color: #218838;
        border-color: #218838;
    }

    /* 댓글 삭제 버튼 위치 */
    .comment-delete-button {
        float: right;
        margin-top: -30px;
    }
    
    .user_nick {
    text-decoration: none;
    font-weight: bolder;
    color: black;
    }
</style>
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
						<div class="form-group">
							<label for="user_id">작성자</label> <input type="text" id="user_id"
								name="user_id" class="form-control"
								value="${readBoardBean.user_nickname}" disabled="disabled" />
						</div>
						<div class="form-group">
							<label for="board_date">작성날짜</label> <input type="text"
								id="board_date" name="board_date" class="form-control"
								value="${readBoardBean.board_date}" disabled="disabled" />
						</div>
						<div class="form-group">
							<label for="board_title">제목</label> <input type="text"
								id="board_title" name="board_title" class="form-control"
								value="${readBoardBean.board_title}" disabled="disabled" />
						</div>
						<div class="form-group">
							<label for="board_content">내용</label>
							<textarea id="board_content" name="board_content"
								class="form-control" rows="10" style="resize: none"
								disabled="disabled">${readBoardBean.board_content}</textarea>
						</div>
						<c:if test="${readBoardBean.board_pic != null}">
							<div class="form-group">
								<label for="board_pic">첨부 이미지</label> <img
									src="${root}upload/${readBoardBean.board_pic}"
									alt="첨부 이미지" />
							</div>
						</c:if>
						<div class="form-group button-group">
							<c:if test="${isOwner}">
								<div class="owner-buttons">
									<a
										href="${root}board/modify?board_info_idx=${board_info_idx}&board_id=${board_id}&page=${page}"
										class="btn btn-info">수정하기</a> <a
										href="${root}board/delete?board_info_idx=${board_info_idx}&board_id=${board_id}"
										class="btn btn-danger">삭제하기</a>
								</div>
							</c:if>
							<a
								href="${root}board/main?board_info_idx=${board_info_idx}&page=${page}"
								class="btn btn-primary ml-auto">목록보기</a>
						</div>
						<div class="form-group">
							<label for="comment_content">댓글 작성</label>
							<textarea id="comment_content" name="comment_content"
								class="form-control" rows="5"></textarea>
						</div>
						<div class="form-group text-right">
							<button type="button" class="btn btn-success"
								onclick="commentWrite()">댓글 작성</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>

		<c:if test="${not empty commentList}">
			<div class="card shadow" id="commentCard">
				<div class="card-body" id="commentSection">
					<c:forEach var="comment" items="${commentList}">
						<div class="form-group">
							<a href="${root}sns/myprofile?user_idx=${review.user_idx}" class="user_nick">
							${comment.user_nickname}
							</a>
							<p>${comment.comment_content}</p>
							<small>${comment.comment_date}</small>
							<c:if test="${comment.user_idx == loginUserBean.user_idx}">
								<button type="button"
									class="btn btn-danger btn-sm comment-delete-button"
									onclick="deleteComment(${comment.comment_id})">삭제</button>
							</c:if>
						</div>
						<hr />
					</c:forEach>

					<div class="d-block d-md-block">
						<ul class="pagination justify-content-center">
							<c:choose>
								<c:when test="${commentPageBean.prevPage <= 0}">
									<li class="page-item disabled"><a href="#"
										class="page-link">이전</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a
										href="${root}board/read1?board_info_idx=${board_info_idx}&board_id=${board_id}&page=${page}&commentPage=${commentPageBean.prevPage}"
										class="page-link">이전</a></li>
								</c:otherwise>
							</c:choose>

							<c:forEach var="idx" begin="${commentPageBean.min}"
								end="${commentPageBean.max}">
								<c:choose>
									<c:when test="${idx == commentPageBean.currentPage}">
										<li class="page-item active"><a
											href="${root}board/read1?board_info_idx=${board_info_idx}&board_id=${board_id}&page=${page}&commentPage=${idx}"
											class="page-link">${idx}</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a
											href="${root}board/read1?board_info_idx=${board_info_idx}&board_id=${board_id}&page=${page}&commentPage=${idx}"
											class="page-link">${idx}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${commentPageBean.max >= commentPageBean.pageCnt}">
									<li class="page-item disabled"><a href="#"
										class="page-link">다음</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a
										href="${root}board/read1?board_info_idx=${board_info_idx}&board_id=${board_id}&page=${page}&commentPage=${commentPageBean.nextPage}"
										class="page-link">다음</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</c:if>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

	<input type="hidden" id="login_user_id"
		value="${loginUserBean.user_idx}">

	<script>
	const commentWrite = () => {
	    const user_idx = document.getElementById("login_user_id").value;
	    const comment_content = document.getElementById("comment_content").value;
	    const board_id = '${readBoardBean.board_id}';
	    const board_info_idx = '${board_info_idx}';

	    console.log("Sending request with data:", {
	        user_idx: user_idx,
	        comment_content: comment_content,
	        board_id: board_id,
	        board_info_idx: board_info_idx
	    });

	    $.ajax({
	        type: "post",
	        url: "${root}board/comment/save",
	        data: {
	            user_idx: user_idx,
	            comment_content: comment_content,
	            board_id: board_id,
	            board_info_idx: board_info_idx
	        },
	        dataType: "json",
	        success: function(response) {
	            console.log("Response received:", response);
	            if (response) {
	                alert("댓글 작성 성공!");
	                location.reload();  // 새로고침 추가
	            } else {
	                alert("댓글 작성 실패!");
	            }
	        },
	        error: function (xhr, status, error) {
	            console.error("Error response:", xhr, status, error);
	            alert("댓글 작성 실패!");
	        }
	    });
	}

const deleteComment = (comment_id) => {
    console.log("Deleting comment with ID:", comment_id);

    $.ajax({
        type: "post",
        url: "${root}board/comment/delete",
        data: {
            comment_id: comment_id
        },
        success: function(response) {
            console.log("Response received:", response);
            if (response === "success") {
                alert("댓글 삭제 성공!");
                location.reload(); // 페이지 새로고침
            } else {
                alert("댓글 삭제 실패!");
            }
        },
        error: function (xhr, status, error) {
            console.error("Error response:", xhr, status, error);
            alert("댓글 삭제 실패!");
        }
    });
}

const updateCommentList = (comments, pageBean) => {
    const commentSection = document.getElementById("commentSection");
    const commentCard = document.getElementById("commentCard");

    if (commentCard.style.display === "none") {
        commentCard.style.display = "block";
    }

    commentSection.innerHTML = "";  // 기존 댓글 초기화

    comments.forEach(comment => {
        const commentDiv = document.createElement("div");
        commentDiv.className = "form-group";
        
        const strong = document.createElement("strong");
        strong.textContent = comment.user_nickname;
        
        const p = document.createElement("p");
        p.textContent = comment.comment_content;
        
        const small = document.createElement("small");
        small.textContent = comment.comment_date;

        commentDiv.appendChild(strong);
        commentDiv.appendChild(p);
        commentDiv.appendChild(small);

        // 댓글 삭제 버튼 추가
        if (comment.user_idx === parseInt(document.getElementById("login_user_id").value)) {
            const deleteButton = document.createElement("button");
            deleteButton.className = "btn btn-danger btn-sm comment-delete-button";
            deleteButton.textContent = "삭제";
            deleteButton.onclick = () => deleteComment(comment.comment_id);
            commentDiv.appendChild(deleteButton);
        }

        commentSection.appendChild(commentDiv);

        const hr = document.createElement("hr");
        commentSection.appendChild(hr);
    });

    const pagination = document.createElement("ul");
    pagination.className = "pagination justify-content-center";

    const createPageItem = (page, label, disabled = false, active = false) => {
        const li = document.createElement("li");
        li.className = `page-item${disabled ? " disabled" : ""}${active ? " active" : ""}`;
        const a = document.createElement("a");
        a.className = "page-link";
        a.href = `${root}board/read1?board_info_idx=${board_info_idx}&board_id=${board_id}&page=${page}&commentPage=${page}`;
        a.textContent = label;
        li.appendChild(a);
        return li;
    };

    // 이전 페이지 버튼
    pagination.appendChild(createPageItem(pageBean.prevPage, "이전", pageBean.prevPage <= 0));

    // 페이지 번호 버튼
    for (let idx = pageBean.min; idx <= pageBean.max; idx++) {
        pagination.appendChild(createPageItem(idx, idx, false, idx === pageBean.currentPage));
    }

    // 다음 페이지 버튼
    pagination.appendChild(createPageItem(pageBean.nextPage, "다음", pageBean.max >= pageBean.pageCnt));

    commentSection.appendChild(pagination);
}
</script>

</body>
</html>
