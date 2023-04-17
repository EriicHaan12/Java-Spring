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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>게시판 상세 조회 페이지</title>
<script>

function deleteBoard() {
	console.log('${board.no}');
	location.href ="remBoard?no="+${board.no};
}
	$(function() {
		getAllReplies(); // 현재 글의 모든 댓글 가져오기.
			    $( "i" ).click(function() {
					let user = '${sessionScope.loginMember.userId}';
					if (user ==='') {
						alert("좋아요는 로그인 해야 이용 가능 합니다.");
						return false;
					} else {
			   let isLike = $(this).attr("class")+"";
			   console.log(isLike);
			    if(isLike==='press'){
			    	likeClicked(false, user);
			    	}else{
			    		likeClicked(true, user);
			    	}
			    $( "i,span,likeSpan" ).toggleClass( "press", 1000 );
					}
				});
		});
	function likeClicked(isLike, user){
			$.ajax({
				url : "/board/like",
				type : "post",
				dataType : "text", //수신받을 데이터 타입
				data:{
					"isLike" : isLike,
					"boardNo": '${board.no}',
					"who" : user
				},
				success : function(data) {
					console.log(data);
					$("#likeCount").val(data); // 좋아요 갯수 넣기
				},
				error: function(data){
					console.log(data);
				}
			});
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

		let output = "<ul class='list-group replies'>";

		$.each(data,function(i, item) {
							output += "<li id='replyBody"+item.replyNo+"' class='list-group-item'>";
							if(item.replyNo!= item.ref){
								output+="<img src='/resources/images/reply.png' width:'15px'/>"
							}
							output += "<div id='replyText"+item.replyNo+"' class='reply'>"+ item.replytext + "</div>";
							output += "<div><span id='reply"+item.replyNo+"' class='writer'>"+ item.replier + "</span>";
							output += "<span class='postdate'>"+ elapesdTime(item.postdate)+ "</span></div>";
							output += "<div class='icons'><img src='/resources/images/modify.png' class='icon'onclick='replyModi("+ item.replyNo+ ");' />";
							output += "<img src='/resources/images/remove.png' class='icon' onclick='replyRemove("+ item.replyNo + ");'/></div></li>";
						});
		output += "</ul>";
		$(".allReplies").html(output);
		
		function elapesdTime(date){
			let postDate = new Date(date); // 넘겨진 시간(댓글 작성 날짜)
			let current = new Date();// 현재 시간
			
			let diff = (current-postDate)/1000; // 시간차를 s 단위로 
			let times = [
				//{name : "년", ms : 60*60*24*365 },
				//{name : "개월", ms : 60*60*24*30 },
				{name : "일", ms : 60*60*24 },
				{name : "시간", ms : 60*60 },
				{name : "분", ms : 60}
			];
			for(let val of times){
				// 글 작성된 시간과 현재시간의 초단위 시간차(val.ms)으로 나누어봄
				let betweenTime = Math.floor(diff / val.ms);
				
				if(betweenTime> 0){ //나눈것이 시차가 난다?(나눈 값이 몫이 있다)
					if(diff>60*60*24){
						
						return postDate.toLocaleString();
					}
						
						return betweenTime+ val.name + "전";
				}	
			}
			return "방금 전";
		}
		
	}
	//댓글 수정
	function replyModi(replyNo) {
		console.log(replyNo);
		
		let user = '${sessionScope.loginMember.userId}';
		console.log(user);
		
		console.log($("#reply"+replyNo).html());
		
		if (user ==='') {
			alert("댓글 수정은 로그인 해야 이용 가능 합니다.");
			return;
		} else {
			if (user !== $("#reply" + replyNo).html()) {
				alert("댓글 수정은 본인 글만 가능 합니다.");
				return;
			} else {
				let inputArea = '<textarea class="form-control" rows="5" id="modiContent'+replyNo+'">'+$("#replyText"+replyNo).html()+'</textarea>'
					 +'<button type="button" class="btn btn-info" onclick="modiReply('+replyNo+');">댓글수정</button>';
				 +'<button type="button" class="btn btn-danger" onclick="modiCancel();">취소</button>';

				$("#replyBody" + replyNo).html(inputArea);
			}
		}
	}
	
	function modiReply(replyNo){
		console.log(replyNo);
		let replytext = $("#modiContent"+replyNo).val();
		let reply={
				"replyNo" : replyNo,
				"replytext": replytext
		};
		console.log(JSON.stringify(reply));
		
		$.ajax({
			url : "/reply/" + replyNo,
			type : "put",
			async : false,
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
				if(data=="success"){
					getAllReplies();
				}
			},
			error : function(data) {
				console.log(data);
				alert("댓글 수정이 실패 하였습니다.");
				getAllReplies();
			}
		});
	
	}
	
	function modiCancel(){
		getAllReplies();
			
	}
	
	function replyRemove(replyNo) {
		location.href='';
	}
	
	
	
	
</script>

<style type="text/css">
span.writer {
	float: right;
	margin-top: 4px;
	margin-right: 3px;
}

span.postdate {
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

body {
	margin: 0;
	text-align: center;
	font-family: 'open sans', sans-serif;
	background: #ddd;
	height: 100%;
}

.likeDiv {
	height: 100px;
	margin: 0 auto;
	position: relative;
}

i {
	cursor: pointer;
	padding: 10px 12px 8px;
	background: #fff;
	border-radius: 50%;
	display: inline-block;
	margin: 0 0 15px;
	color: #aaa;
	transition: .2s;
}

i:hover {
	color: #666;
}

i:before {
	font-family: fontawesome;
	content: '\f004';
	font-style: normal;
}

span.likeSpan {
	position: absolute;
	bottom: 70px;
	left: 0;
	right: 0;
	visibility: hidden;
	transition: .6s;
	z-index: -2;
	font-size: 2px;
	color: transparent;
	font-weight: 400;
}

i.press {
	animation: size .4s;
	color: #e23b3b;
}

#likeSpan.press {
	bottom: 120px;
	font-size: 14px;
	visibility: visible;
	animation: fade 1s;
}

@
keyframes fade { 0% {
	color: #transparent;
}

50




%
{
color




:




#e23b3b


;
}
100




%
{
color




:




#transparent


;
}
}
@
keyframes size { 0% {
	padding: 10px 12px 8px;
}
50




%
{
padding




:




14px




16px




12px


;
margin-top




:




-4px


;
}
100




%
{
padding




:




10px




12px




8px


;
}
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
			<label for="title">조회수 : </label> <input type="text"
				class="form-control" id="readCount" value="${board.readCount }"
				readonly />
		</div>
		
		<div class="mb-3 mt-3">
			<label for="title">좋아요 : </label> <input type="text"
				class="form-control" id="likeCount" value="${board.likeCount }"
				readonly />
		${likeList }
		</div>

	<c:set var="isHeart" value="false"/>
	<div class= "likeDiv">
	<c:forEach items="${likeList }" var="likedUser">
	<c:when test="${sessionScope.loginMember.userId==likedUser.who }">
		<!-- 로그인한 유저가 이 글을 좋아했다면 -->
		<i class="press"></i>
		<span class="likeSpan press">liked!</span>
		<!-- 좋아요 출력 -->
		<c:set var="isHeart" value="true"/>
	</c:when>
	</c:forEach>
	<!-- 좋아요를 출력하지 않았다면, 좋아요 취소 -->
	<c:if test="${isHeart==false }">
		<i></i>
		<span class="likeSpan">liked!</span>
	</c:if>
	</div>
	<div>
	이글을 
	<c:forEach items="${likeList }" var="likeUser">
		${likeUser.who }, 
	</c:forEach>
	가 좋아합니다.
	</div>
	
	
			
				
		<div class="mb-3 mt-3">
			<label for="title">제 목 : </label> <input type="text"
				class="form-control" id="title" value="${board.title }" readonly />
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
			<button class="btn btn-success" class="btn btn-primary"
				data-bs-toggle="modal" data-bs-target="#myModal">삭제</button>
			<!-- -->
			<!-- <button type="button" class="btn btn-success" onclick="location.href='modiBoard?no=${param.no}';">수정</button> -->
			<button type="button" class="btn btn-warning"
				onclick="location.href='listAll';">목록으로</button>
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


	<!-- 삭제 버튼 클릭시 나오는 모달-->

	<div class="modal" id="myModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">${board.no }번글을삭제하시겠습니까?</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">삭제를 원하시면 "예" 버튼을 눌러주세요...</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="deleteBoard();">예</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
