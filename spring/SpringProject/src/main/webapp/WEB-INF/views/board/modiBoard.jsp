<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>게시판 수정 페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>



$(function() {
	$(".fileDrop").on("dragenter dragover", function(evt) {
		evt.preventDefault(); // 진행 중인 이벤트 버블링을 캔슬
	});
	//
	$(".fileDrop").on("drop", function(evt) {
		evt.preventDefault(); // 진행 중인 이벤트 버블링을 캔슬
		//  웹 창에 파일을 드래그 할 시 
		//드래그한 파일을 실행 시키는 브라우저의 고유 기능이 있어서 그걸 막아주기 위해 쓰는 메서드

		let files = evt.originalEvent.dataTransfer.files; //드래그 드랍된 파일
		console.log(files);

		for ( let file of files) {

			//여러개의 파일을 한꺼번에 드래그 해서 업로드 할 수 있도록 반복문을 써준다.
		//반복문 시작
			let formData = new FormData();
			formData.append("upfiles", file);// form객체에 파일들 추가
			$.ajax({
				url : "/board/upfiles",
				type : "post",
				data : formData, // 서블릿에 전송할 데이터
				dataType : "json",
				
				processData : false, // 보낼 데이터를 wrapping (쿼리스트링 형태로 보낸다) -false 
				//일반적으로 form의 기본값은 aplication/x-www.form-urlencoded 인데 
				//false이면 기본값으로 전송 하지 않는다는 뜻
				contentType : false, // 
				async: false, // 동기식 전송
				success : function(data) {
					console.log(data);
					if(data!=null){
						displayUploadFile(data);
					}
				}
			});
			// 반복문 끝
		}
	});
	
});

function displayUploadFile(data){
	let output ="";
	if(data.image){
		output+="<img id='" + data.originFileName + "' class='uFile' src='/resources/upfiles/" + data.thumbImgName + "'/>";
		output+="<image class='delFile' src='/resources/images/delete.png' width='20px' onclick='delFile(this);'/>";
	
	}else{
		output+="<div><a id='" + data.originFileName + "' href='/resources/upfiles/"+data.fileNameWithExt+"'>"+data.originFileName+"</a>";
		output+="<image class='delFile' src='/resources/images/delete.png' width='20px' onclick='delFile(this);' />";
	}	
	$(".uploadFile").append(output);
}

	//삭제버튼 클릭시(동적 실행)
	function delFile(fileId) {
		let removeId = $(fileId).prev().attr("id");// 삭제될 파일의 originFileName
		console.log(removeId);
		$.ajax({
			url : "/board/remfile",
			type : "get",
			data : {
				"remFileName" : removeId,
			}, // 서블릿에 전송할 데이터
			dataType : "text",
			success : function(data) {
				console.log(data);
				if (data == "success") {
					$(fileId).prev().remove();
					$(fileId).remove();
				}
			},
			error : function() {
				// 서버의 controller 단에서 HttpStatus를 에러상태로 보내주면 여기서 처리된다.
				//상태코드로 받아와서 실행되는 콜백함수
				alert("삭제 되지 않았습니다.");
			}
		});

	}
</script>

<style type="text/css">
#imgPreview {
	width: 70px;
}

.btns {
	float: right;
	border-radius: 25px
}

.fileDrop {
	width: 100%;
	height: 100px;
	padding: 20px;
	border: 1px dotted gray;
	font-size: 20px;
	color: #333;
	text-align: center;
	line-height: 50px;
}

.uploadFile {
	margin: 20px;
}

.newFile {
	margin-top: 20px;
}
</style>
</head>
<body>
	<c:set var="realPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">
		<h4 style="margin-top: 5px">게시판 수정 페이지</h4>
		<form method="post" action="modifyBoard">
			<div class="mb-3 mt-3">
				<label for="userId">번호 : </label> <input type="text"
					class="form-control" id="boardNo" value="${board.no }" name="no"
					readonly />
			</div>
			<div class="mb-3 mt-3">
				<label for="userId">작성자 : </label> <input type="text"
					class="form-control" id="writer" value="${board.writer }"
					name="writer" readonly />
			</div>
			<div class="mb-3 mt-3">
				<label for="title">작성일 : </label> <input type="text"
					class="form-control" id="title" value="${board.postDate }" readonly />
			</div>

			<div class="mb-3 mt-3">
				<label for="title">제 목 : </label> <input type="text" name="title"
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
							<a id="${file.fileName}"
								href="/resources/upfiles/${file.fileName }">업로드 파일</a>
							<img src="/resources/images/delete.png" width='20px'
								onclick='delFile(this);' />
						</c:when>
						<c:otherwise>
							<!-- 업로드 파일이 이미지 일 때 -->
							<img id="${file.fileName}"
								src="/resources/upfiles/${file.fileName }">
							<img src="/resources/images/delete.png" width='20px'
								onclick='delFile(this);' />
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</div>
			<div class="form-check ">
				<div class="fileDrop">업로드 할 파일을 드래그 드랍 해 보세요!</div>
				<div class="uploadFile"></div>
			</div>
			<div class="btns">
				<!-- <button type="button" class="btn btn-success" onclick="location.href='modiBoard?no=${param.no}';">수정</button> -->
				<button type="submit" class="btn btn-success">저장</button>
				<button type="button" class="btn btn-success"
					onclick="isOwner('remove');">삭제</button>
				<button type="submit" class="btn btn-warning"
					onclick="location.href=listAll;">목록으로</button>
			</div>
		</form>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
