<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팁게시판 작성폼</title>
<%-- <link rel="stylesheet" href="${path}/css/body.css"> --%> 
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> 
<script src="${path}/js/tipboard.js"></script>
</head>
<body>

<form action="insertTip" method="post" enctype="multipart/form-data" id="tab">
	<input type="hidden" name="mem_id" value="${sessionScope.mem_id}">
	<h5>제목</h5>
	<input type="text" name="tip_title" id="tip_title">
	<h5>팁 내용</h5>
	<textarea rows="5" cols="30" name="tip_content" id="tip_content"></textarea>
	<h5>파일</h5>
	<input type="file" name="tip_file1">
	<h5>펫종류</h5>
	<input type="radio" name="tip_pet" value="cat" id="tip_pet1">고양이
	<input type="radio" name="tip_pet" value="dog" id="tip_pet2">강아지
	
	
	<div class="position1">
		<input type="submit" value="팁 게시판 작성" id="sign_up"/>
	</div>

</form>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>