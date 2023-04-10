<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<title>index.jsp</title>

<script>

</script>
<style>
.board {
	margin-top: 15px;
	margin-bottom: 15px;
}
</style>

</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<h1>index.jsp</h1>
		<div>${sessionScope.loginMember }</div>

		<div class="readCountTop3">
			<h4>인기글</h4>
			<div class="board">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성 날짜</th>
							<th>조회수</th>
							<th>추천수</th>
							<th>ref</th>
							<th>step</th>
							<th>refOrder</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${requestScope.boardList }">
							<tr onclick="goBoardDetail(${board.no });">
								<td>${board.no }</td>
								<td><c:if test="${board.step>0 }">
										<c:forEach var="i" begin="1" end="${board.step }" step="1">
											<img src="${contextPath }/images/reply.png" class="replyImg" />
										</c:forEach>
									</c:if> ${board.title }</td>
								<td>${board.writer }</td>
								<td>${board.postDate }</td>
								<td>${board.readCount }</td>
								<td>${board.likeCount }</td>
								<td>${board.ref }</td>
								<td>${board.step }</td>
								<td>${board.refOrder}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
