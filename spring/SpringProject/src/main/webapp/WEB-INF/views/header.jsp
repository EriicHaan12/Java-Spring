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
<title>Insert title here</title>

<style type="text/css">
.userImg {
	width: 40px;
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


					<li class="nav-item"><a class="nav-link" href="">로그인</a></li>

				</ul>
			</div>
		</div>
	</nav>
</body>
</html>