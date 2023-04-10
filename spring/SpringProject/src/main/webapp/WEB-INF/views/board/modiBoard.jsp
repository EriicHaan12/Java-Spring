<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>게시판 수정 페이지</title>
<script>
	
</script>
</head>
<body>
	<c:set var="realPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h4 style="margin-top: 5px">게시판 수정 페이지</h4>
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
				class="form-control" id="title" value="${board.title }" />
		</div>

		<div>
			<label for="content">본 문 :</label>
			<textarea class="form-control" rows="20" id="content" name="content">	${board.content }</textarea>
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
			<!-- <button type="button" class="btn btn-success" onclick="location.href='modiBoard?no=${param.no}';">수정</button> -->
			<button type="button" class="btn btn-success">저장</button>
			<button type="button" class="btn btn-success"
				onclick="isOwner('remove');">삭제</button>

			<button type="button" class="btn btn-warning"
				onclick="location.href=listAll;">목록으로</button>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
