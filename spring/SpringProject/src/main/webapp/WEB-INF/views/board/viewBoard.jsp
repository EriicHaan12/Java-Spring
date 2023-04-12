<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<title>게시판 상세 조회 페이지</title>
<script>
	$(function() {
		getAllReplies(); // 현재 글의 모든 댓글 가져오기.
	})

	function deleteBoard() {
		location.href = "remBoard?no=" + $
		{
			board.no
		}
		;
	}

	function saveReply() {
		let boardNo = '${board.no}';
		let replytext = $("#replyContent").val();
		let replier = '${sessionScope.loginMember.userId}';
		let reply = {
			"boardNo" : boardNo,
			"replier" : replier,
			"replytext" : replytext
		};

		console.log(JSON.stringify(reply));
		$.ajax({
			url : "/reply",
			type : "post",
			headers : {
				//헤더에도 마찬가지로 json형태로 보내겠다고 명시
				"Content-Type" : "application/json",
				//PUT,DELETE,PATH 등의 REST HTTP Method 가 동작 하지 않는 과거의 웹 브라우저에서는 "post"방식으로 동작하게끔 처리
				"X-HTTP-Method-Override" : "POST"
			//데이터를 전송할 때 packet Header에 추가 할 내용
			},
			//객체를 JSON화 시켜주는 메서드
			//전송할 데이터를 json 문자열로 바꾸어 전송
			data : JSON.stringify(reply),
			dataType : "text", //수신받을 데이터 타입
			success : function(data) {
				console.log(data);
				getAllReplies();// 전체 댓글 가져오는 함수
				$("#replyContent").val("");
			},
			error : function(data) {
				console.log(data)
				if (data.responseText === "notPermitted") {
					alert("댓글은 로그인 이후 작성 할 수 있습니다.");
				} else if (data.responseText === "notSaved") {
					alert("에러가 발생했습니다.");
				}
			}
		});
	}

	function getAllReplies() {
		let boardNo = '${board.no}';

		$.ajax({
			url : "/reply/all/" + boardNo,
			type : "get",
			async : false,
			dataType : "json", //수신받을 데이터 타입
			success : function(data) {
				console.log(data);
				displayAllReply(data);
			},
			error : function(data) {
				console.log(data);
			}
		});

	}

	function displayAllReply(data) {

		let output = "<div class='list-group replies'>";

		$.each(data, function(i, item) {
							output += "<a id='"+item.replyNo+"' href='#' class='list-group-item list-group-item-action'>";
							output += "<div>" + item.replytext + "</div>";
							output += "<div><span class='writer'>"+ item.replier + "</span>";
							output += "<span class='postdate'>"+ new Date(item.postdate).toLocaleString()+ "</span></div>";
							output += "<div class='icons'><img src='/resources/images/modify.png' class='icon'onclick='replyModi("+item.replyNo+");' />";
							output += "<img src='/resources/images/remove.png' class='icon' onclick='replyRemove("+item.replyNo+");'/></div>";
							output += "</a>";
						});
		output += "</div>";
		$(".allReplies").html(output);
	}
	
	function replyModi(replyNo){
		console.log(replyNo);
	}
	function replyRemove(replyNo){
		console.log(replyNo);	
	}
	
</script>
<style type="text/css">
.writer {
	float: right;
	margin-top: 4px;
	margin-right: 3px;
}

.postdate {
	margin-left: 4px;
}

.icons {
	float: right;
	margin-right: 3px;
}

.icon {
	width: 18px;
	margin-left: 5px;
}
</style>
</head>
<body>
	<c:set var="realPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h4 style="margin-top: 5px">게시판 상세 조회 페이지</h4>
		<div class="mb-3 mt-3">
			<label for="userId">번호 : </label> <input type="text"
				class="form-control" id="boardNo" value="${board.no }" readonly />
		</div>

		<div class="mb-3 mt-3">
			<label for="userId">작성자 : </label> <input type="text"
				class="form-control" id="writer" value="${board.writer }" readonly />
		</div>
		<div class="mb-3 mt-3">
			<label for="title">작성일 : </label> <input type="text"
				class="form-control" id="title" value="${board.postDate }" readonly />
		</div>

		<div class="mb-3 mt-3">
			<label for="title">제 목 : </label> <input type="text"
				class="form-control" id="title" value="${board.title }" readonly />
		</div>
		<div class="mb-3 mt-3">
			<label for="title">조회수 : </label> <input type="text"
				class="form-control" id="title" value="${board.readCount }" readonly />
		</div>
		<div class="mb-3 mt-3">
			<label for="title">추천수 : </label> <input type="text"
				class="form-control" id="title" value="${board.likeCount }" readonly />
		</div>

		<div class="form-check">
			<label for="content">본 문 :</label>
			<div>${board.content }</div>
		</div>

		<div class="form-check">
			<c:forEach var="file" items="${upFiles}">
				<c:choose>
					<c:when test="${empty file.thumbFileName}">
						<!-- 업로드 파일이 이미지가 아닐 때 -->
						<a href="/resources/upfiles/${file.fileName }">업로드 파일</a>
					</c:when>

					<c:otherwise>
						<!-- 업로드 파일이 이미지 일 때 -->
						<img src="/resources/upfiles/${file.fileName }">
					</c:otherwise>

				</c:choose>

			</c:forEach>
		</div>

		<div class="btns">

			<button type="button" class="btn btn-success"
				onclick="location.href='modiBoard?no=${board.no}&writer=${board.writer}'">수정</button>
			<button type="button" class="btn btn-success" onclick="">삭제</button>

			<!-- <button type="button" class="btn btn-success" onclick="location.href='modiBoard?no=${param.no}';">수정</button> -->



			<button type="button" class="btn btn-warning"
				onclick="location.href=listAll;">목록으로</button>
		</div>


		<div class="replyInputDiv">
			<label for="content">댓글 : </label>
			<textarea class="form-control" rows="5" id="replyContent"></textarea>
			<button type="button" class="btn btn-info" onclick="saveReply();">댓글달기</button>
			<button type="button" class="btn btn-danger" onclick="">취소</button>
		</div>
		<div class="allReplies" style="margin-top: 10px; padding: 5px;"></div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
