<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<script type="text/javascript">

$(function(){
	let user = '${sessionScope.loginMember.userId}';
	if(user!=''){
		updateMsgCnt(user);	
	}
	setInterval(() => {
		if(user!=''){
			updateMsgCnt(user);	
		}
	}, 1000 * 60 * 1);
	//ms(밀리세컨드)* s(초)* m(분)
});

function updateMsgCnt(user){
	$.ajax({
		url : "/message/updateMsgCnt/"+ user, 
		type : "get", 
		dataType : "text",  // 수신받을 데이터 타입
		success : function(data) { 
			console.log(data);
		 $(".badge").html(data);
		}, error : function(data) {
			console.log(data);
		}
	});
	
	
}

</script>

<title>Insert title here</title>

<style type="text/css">
.userImg {
	width: 50px;
	height: 50px;
	border-radius: 20px;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">Home</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">

					<li class="nav-item"><a class="nav-link" href="">회원가입</a></li>


					<li class="nav-item"><a class="nav-link" href="/board/listAll">게시판</a></li>



					<c:choose>
						<c:when test="${ sessionScope.loginMember!=null }">


							<li class="nav-item"><a class="nav-link" href="/logout">로그아웃
									<img class="userImg"
									src="/resources/${sessionScope.loginMember.userImg}">
									${sessionScope.loginMember.userId }
							</a></li>
							

							<li class="nav-item"><a class="nav-link"
								href="/member/myPage?userId=${sessionScope.loginMember.userId}">Mypage
							</a></li>
						
						<button type="button" class="btn btn-primary">
								Messages <span class="badge bg-danger"></span>
							</button>
						</c:when>

						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>