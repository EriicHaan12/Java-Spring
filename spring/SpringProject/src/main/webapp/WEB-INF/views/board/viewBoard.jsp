<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>게시판 상세 조회 페이지</title>
<script>
	
function deleteBoard(){
	location.href="remBoard?no="+${board.no};
}
	
	
</script>
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
			<button type="button" class="btn btn-success" onclick="">수정</button>
			<button type="button" class="btn btn-success" onclick="deleteBoard();">삭제</button>


			<button type="button" class="btn btn-warning"
				onclick="location.href=listAll;">목록으로</button>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
