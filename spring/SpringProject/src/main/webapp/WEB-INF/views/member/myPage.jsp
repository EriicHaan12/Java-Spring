<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
	function showMessage() {

		$(".msg").show();
		$(".card").hide();
		//		message/view+{LoginMember.userId}
		$.ajax({
			url : "/message/view/" + '${memberInfo.userId}',
			type : "get",
			dataType : "json",
			success : function(data) {
				console.log(data);
				if (data != null) {
					let output = "<ul class='list-group'>";
					$.each(data, function(i, msg) {
						output += " <li class='list-group-item'>";
						if(msg.isRead=='N'){
							output+="<img src='/resources/images/new.png' width='15px;'>";
						}
						output += "<span class = 'msgContent'>"+ new Date(msg.sendTime).toLocaleString() + "</span>";
						output += "<span class = 'msgContent'>" + msg.sender+ "</span>";
						output += "<span class = 'msgContent'>"+ msg.messageText + "</span>";
						output += "</li>";
						$(".memberMessage").html(output);
					});

				}
			},
			error : function(data) {
				console.log(data);
			}
		});
	}
	function showBasic() {
		$(".msg").hide();
		$(".card").show();
	}

	function messageSend() {

		let receiver = $("#receiver").val();
		let messageText = $("#messageContent").val();

		$.ajax({
			url : "/message/send",
			type : "post",
			dataType : "text",
			headers : {
				"Content-Type" : "application/json",
				// PUT, DELETE, PATCH 등의 REST HTTP Method가 동작하지 않는 과거의 웹브라우저에서는 
				// "POST" 방식으로 동작하도록 한다
				"X-HTTP-Method-Override" : "POST"
			},
			data : JSON.stringify({
				"receiver" : receiver,
				"messageText" : messageText
			}),

			success : function(data) {
				console.log(data);
				if (data === "success") {
					$("#receiver").val('');
					$("#messageContent").val('');
				}
			},

			error : function(data) {
				console.log(data);

			}
		});

	}
</script>
<style type="text/css">
.memberPoint {
	display: none;
}

.profileImg {
	width: 100px;
}

.card {
	text-align: center;
}

.profileImg {
	border-left: 30px;
}

.msgContent {
	margin-right: 5px;
}

.sendMsg {
	margin-top: 5px;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">

		<ul class="nav">
			<li class="nav-item"><a class="nav-link active" href="#"
				onclick="showBasic();">기본정보</a>
				<div class="card" style="width: 200px; margin: 20px auto;">
					<div class="profileImg">

						<img class="card-img-top" src="/resources/${memberInfo.userImg }"
							alt="${memberInfo.userId }">

					</div>
					<div class="card-body">
						<h4 class="card-title">${memberInfo.userId }</h4>
						<p class="card-text">
						<div class="userEmail">${memberInfo.userEmail }</div>
						<div class="userMobile">${memberInfo.userMobile }</div>
						<div class="userGender">${memberInfo.userGender}</div>
						<div class="userHobbies">${memberInfo.hobbies }</div>
						<div class="job">${memberInfo.job }</div>
						<div class="memo">${memberInfo.memo }</div>

						<a href="#" class="btn btn-primary">See Profile</a>
						</p>
					</div>
				</div></li>

			<li class="nav-item"><a class="nav-link memberPoint" href="#"
				onclick="showMessage();">쪽지 주고 받기</a>
				<div class="msg">
					<div class="memberMessage"></div>

					<div class="sendMsg">
						<div class="replyInputDiv">
							<div class="form-check">
								<label for="messageContent">받는 사람 :</label> <input type="text"
									class="form-control" id="receiver" /> <label
									for="messageContent">보낼 메세지 :</label>
								<textarea class="form-control" rows="2" id="messageContent"></textarea>
								<button type="button" class="btn btn-info"
									onclick="messageSend();">SEND</button>
								<button type="button" class="btn btn-danger">취소</button>
							</div>
						</div>

					</div>
					<!-- 
				<div class="container-fluid">
					<div class="row">
						<div class="col-3">
							<h4>현재 보유 포인트</h4>
						</div>

					</div>
					 -->
				</div>
	</div>
	</li>
	<!-- 	<li class="nav-item"><a class="nav-link" href="#">내가 쓴 글</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
			</li>
			 -->
	</ul>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>