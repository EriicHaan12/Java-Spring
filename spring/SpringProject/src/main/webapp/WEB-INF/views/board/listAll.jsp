<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<title>Insert title here</title>

<script>
	function goBoardDetail(no){
		location.href='viewBoard?no='+no;
	}
		
	function getVpc() {
		let vpc = Number('${param.viewPost}');
		if (vpc == '') {
			vpc = 3;
		} 
		$(".viewPostCnt").val(vpc); // select 태그의 value값 변경
	}
	
	$(function(){
		getVpc();
		$(".viewPostCnt").change(function() {
			let vpc = $(this).val();
			location.href="listAll.bo?pageNo=${param.pageNo}&viewPost=" + vpc;
		});
	});
	

	function searchValid() {  
		// 검색어가 입력 되지 않거나, sql injection 공격요소가 있다면 검색이 되지 않도록	
		let sw = $("#searchWord").val();
		if (sw.length == 0) {
			alert("검색어를 입력하세요!");
			return false;
		} 
		let expText = /[%=><]/;  // 데이터베이스에서 조건연산자에 해당
		if (expText.test(sw) == true) {
			alert("특수문자를 입력 할 수 없습니다.");
			return false;
		} 
		const sql = new Array(
			"or", "select", "insert", "update", "delete", "create", "alter", "drop", "exec", 
			"union", "fetch", "declare", "truncate"
		);
		let regEx = "";
		for (let i = 0; i < sql.length; i++) {
			regEx = new RegExp(sql[i], "gi");
			
			if (regEx.test(sw) == true) {
				alert("특정 문자로 검색할 수 없습니다");
				return false;
			}
		}
		
		return true;
	}
	
	
	
	
</script>
<style>
.board {
	margin-top: 15px;
	margin-bottom: 15px;
}

.writeBtn {
	float: right;
	margin-right: 10px;
}

.paging {
	clear: both;
}

.replyImg {
	width: 15px;
}
</style>


</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />

	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">

		<h4 style="margin-top: 5px;">게시판 글 목록 페이지</h4>

		<div>
			<select class="viewPostCnt" name="viewPostCnt">
				<option value="3" selected>3개씩</option>
				<option value="5">5개씩</option>
				<option value="10">10개씩</option>
				<option value="20">20개씩</option>
			</select>
		</div>

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
					</tr>
				</thead>
				<tbody>

					<c:forEach var="board" items="${boardList }">
						<tr onclick="goBoardDetail(${board.no });">
							<td>${board.no }</td>
							<td>${board.title }</td>
							<td>${board.writer }</td>
							<td>${board.postDate }</td>
							<td>${board.readCount }</td>
							<td>${board.likeCount }</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>

			<form class="searchBoard" action="listAll">
				<select name="searchType">
					<option value="">--검색어 선택--</option>
					<option value="title">제목</option>
					<option value="writer">글쓴이</option>
					<option value="content">본문</option>
				</select> <input type="text" name="searchWord" id="searchWord" />
				<button type="submit" onclick="return searchValid();">검색</button>
			</form>

			${pagingInfo}
			<div class="paging">
				<ul class="pagination">
					<c:if test="${pagingInfo.startNumOfCurrentPagingBlock > 1 }">
						<li class="page-item"><a class="page-link"
							href="/listAll?pageNo=${param.pageNo - 1 }&viewPost=${param.viewPost}&searchType=${param.searchType }&searchWord=${param.searchWord}">Previous</a></li>

					</c:if>
					<c:forEach var="i"
						begin="${pagingInfo.startNumOfCurrentPagingBlock }"
						end="${pagingInfo.endNumOfCurrentPagingBlock}" step="1">

						<c:choose>
							<c:when test="${pagingInfo.pageNo == i }">
								<li class="page-item active"><a class="page-link"
									href="/listAll?pageNo=${i }&viewPost=${param.viewPost}&searchType=${param.searchType }&searchWord=${param.searchWord}">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="/listAll?pageNo=${i }&viewPost=${param.viewPost}&searchType=${param.searchType }&searchWord=${param.searchWord}">${i }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pagingInfo.pageNo <  pagingInfo.totalPageCnt}">
						<li class="page-item"><a class="page-link"
							href="/listAll?pageNo=${param.pageNo + 1 }&viewPost=${param.viewPost}&searchType=${param.searchType }&searchWord=${param.searchWord}">Next</a></li>
					</c:if>
				</ul>
			</div>



			<div class="btns">
				<!-- 출력 날짜형식을 바꾸고 싶다면...  fmt:formatDate를 쓰자 -->
				<button type="button" class="btn btn-secondary writeBtn"
					onclick="location.href='writeBoard';">글쓰기</button>
			</div>


		</div>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>