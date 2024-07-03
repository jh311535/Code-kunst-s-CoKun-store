<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코쿤스토어</title>
<link href="../css/top_menu.css" rel="stylesheet" type="text/css" />
<link href="../css/bottom.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/myprofile.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        function updateProfileData(data) {
            $("#nickname").text(data.userNickname);
            $("#followingCount span").text(data.followingCount);
            $("#followersCount span").text(data.followersCount);
            $("#reviewsCount").text(data.reviewsCount);
        }

        function checkFollowStatus() {
            var followerId = $('#followerId').val();
            var followingId = $('#followingId').val();

            $.ajax({
                url: 'checkFollow',
                type: 'GET',
                data: {
                    follower_id: followerId,
                    following_id: followingId
                },
                success: function(response) {
                    if (response == 0) {
                        $("#followAndUpdateBtn").show();
                        $("#unfollowAndUpdateBtn").hide();
                    } else {
                        $("#followAndUpdateBtn").hide();
                        $("#unfollowAndUpdateBtn").show();
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error in checkFollowStatus:", error);
                }
            });
        }

        $("#followAndUpdateBtn").click(function() {
            var followData = {
                follower_id: $('#followerId').val(),
                following_id: $('#followingId').val()
            };

            $.ajax({
                url: 'addFollow',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(followData),
                success: function(response) {
                    if (response) {
                        updateProfileData(response);
                        checkFollowStatus();
                    } else {
                        alert("Failed to follow user.");
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error in followAndUpdateBtn click:", error);
                    alert("Failed to follow user.");
                }
            });
        });

        $("#unfollowAndUpdateBtn").click(function() {
            var followData = {
                follower_id: $('#followerId').val(),
                following_id: $('#followingId').val()
            };

            $.ajax({
                url: 'deleteFollow',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(followData),
                success: function(response) {
                    if (response) {
                        updateProfileData(response);
                        checkFollowStatus();
                    } else {
                        alert("Failed to unfollow user.");
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error in unfollowAndUpdateBtn click:", error);
                    alert("Failed to unfollow user.");
                }
            });
        });

        $(document).on('click', '.delete-review-btn', function() {
            var reviewId = $(this).data("review-id");
            console.log("Deleting review with ID:", reviewId);

            $.ajax({
                url: '${root}/review/deleteReview',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ review_id: reviewId }),
                success: function(response) {
                    alert("리뷰가 성공적으로 삭제되었습니다.");
                    location.reload();
                },
                error: function(xhr, status, error) {
                    console.error("Error in delete-review-btn click:", error);
                    alert("리뷰 삭제에 실패했습니다.");
                }
            });
        });

        checkFollowStatus();
    });

    function openFollowerModal() {
        console.log("openFollowerModal called"); // 디버깅을 위한 로그 추가
        document.getElementById('follower-modal').style.display = 'block';
    }

    function closeFollowerModal() {
        document.getElementById('follower-modal').style.display = 'none';
    }

    function openFollowingModal() {
        console.log("openFollowingModal called"); // 디버깅을 위한 로그 추가
        document.getElementById('following-modal').style.display = 'block';
    }

    function closeFollowingModal() {
        document.getElementById('following-modal').style.display = 'none';
    }

    window.onclick = function(event) {
        var followerModal = document.getElementById('follower-modal');
        if (event.target == followerModal) {
            followerModal.style.display = 'none';
        }
        var followingModal = document.getElementById('following-modal');
        if (event.target == followingModal) {
            followingModal.style.display = 'none';
        }
    }
</script>
</head>
<body class="profile-page-body">
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>
<div class="profile-page-container">
    <c:choose>
        <c:when test="${requestUserIdx == loginUserBean.user_idx}">
            <button class="profile-modify-button"
                    onclick="location.href='${root}/sns/profile_modify?user_idx=${requestUserIdx}'">
                <img src="${root}/image/setting.png" alt="설정">
            </button>
        </c:when>
    </c:choose>
    <img class="user-img" src="${root}/upload/${profile.user_pic}" alt="유저사진"/>
    <h1 id="nickname">${profile.userNickname}</h1>
    <c:if test="${not empty profile.introduce}">
        <p>${profile.introduce}</p>
    </c:if>
    <c:if test="${not empty profile}">
        <ul class="profile-page-profile-info">
            <li>
                <a id="followingCount" href="javascript:void(0);" onclick="openFollowerModal()" class="follow_count">
                    팔로워: <span>${profile.followingCount}</span>
                </a>
            </li>
            <li>|</li>
            <li>
                <a id="followersCount" href="javascript:void(0);" onclick="openFollowingModal()" class="follow_count">
                    팔로잉: <span>${profile.followersCount}</span>
                </a>
            </li>
        </ul>
        <p>
            리뷰: <span id="reviewsCount">${profile.reviewsCount}</span>
        </p>
    </c:if>

    <c:if test="${empty profile}">
        <p>No profile information available.</p>
    </c:if>

    <c:if test="${loginUserBean != null}">
        <input type="hidden" id="followerId" value="${loginUserBean.user_idx}">
        <input type="hidden" id="followingId" value="${requestUserIdx}">
        <input type="hidden" id="requestedUserIdx" value="${requestedUserIdx}">
        <c:choose>
            <c:when test="${requestUserIdx == loginUserBean.user_idx}">
                <a href="${root}/user/mypage" class="profile-page-nav-link">마이페이지</a>
            </c:when>
            <c:otherwise>
                <button id="followAndUpdateBtn" class="profile-page-nav-link" style="display: none;">팔로우</button>
                <button id="unfollowAndUpdateBtn" class="profile-page-nav-link" style="display: none;">언팔로우</button>
            </c:otherwise>
        </c:choose>
    </c:if>
    
    <!-- 리뷰쓰기 -->
    <c:forEach var="review" items="${userReview}">
        <div class="profile-page-review-item">
            <img src="${review.book_pic}" alt="${review.bookName}" class="review-book-pic" />
            <div class="review-content">
                <a href="${root}/books/bookDetail?book_id=${review.book_id}">
                    ${review.bookName}
                </a>
                <p class="profile-page-rating"><img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                		<c:choose>
                			<c:when test="${review.rating >=2}">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                			</c:when>
                			<c:otherwise>
		                		<img src="${root}/image/icon-star-0.png" alt="star" class="review-star"/>
                			</c:otherwise>
                		</c:choose>
                		<c:choose>
                			<c:when test="${review.rating >=3}">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                			</c:when>
                			<c:otherwise>
		                		<img src="${root}/image/icon-star-0.png" alt="star" class="review-star"/>
                			</c:otherwise>
                		</c:choose>
                		<c:choose>
                			<c:when test="${review.rating >=4}">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                			</c:when>
                			<c:otherwise>
		                		<img src="${root}/image/icon-star-0.png" alt="star" class="review-star"/>
                			</c:otherwise>
                		</c:choose>
                		<c:choose>
                			<c:when test="${review.rating >=5}">
		                		<img src="${root}/image/icon-star-1.png" alt="star" class="review-star"/>
                			</c:when>
                			<c:otherwise>
		                		<img src="${root}/image/icon-star-0.png" alt="star" class="review-star"/>
                			</c:otherwise>
                		</c:choose></p>
                <p>리뷰 내용: ${review.review_content}</p>
                <p>작성일: ${review.review_date}</p>
                <c:choose>
                    <c:when test="${requestUserIdx == loginUserBean.user_idx}">
                        <div class="review-buttons">
                            <button class="delete-review-btn" data-review-id="${review.review_id}">리뷰 삭제</button>
                            <button class="modify-review-btn" onclick="location.href='${root}/review/review_modify?review_id=${review.review_id}'">리뷰 수정</button>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

<!-- 팔로워 모달 -->
<div id="follower-modal" class="modal">
    <div class="modal-content">
        <span class="close-button" onclick="closeFollowerModal()">&times;</span>
        <div class="follower-list">
        <h2>${profile.userNickname}</h2>
            <c:forEach var="follower" items="${getFollower}">
                <div class="follower">
                    <img src="${root}/upload/${follower.user_pic}" alt="" />
                    <a href="${root}/sns/myprofile?user_idx=${follower.user_idx}"> 
                        ${follower.userNickname}
                    </a>
                    <br />
                    <p>
                    ${follower.user_Name}
                    </p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<!-- 팔로잉 모달 -->
<div id="following-modal" class="modal">
    <div class="modal-content">
        <span class="close-button" onclick="closeFollowingModal()">&times;</span>
        <div class="follower-list">
        <h2>${profile.userNickname}</h2>
            <c:forEach var="following" items="${getFollow}">
                <div class="follower">
                    <img src="${root}/upload/${following.user_pic}" alt="" />
                    <a href="${root}/sns/myprofile?user_idx=${following.user_idx}"> 
                        ${following.userNickname}
                    </a>
                    <br />
                    <p>
                    ${following.user_Name}
                    </p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
