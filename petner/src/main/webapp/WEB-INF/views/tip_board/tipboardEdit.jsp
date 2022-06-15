<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> 
<script src="${path}/js/tipboard.js"></script>
</head>
<body>

	<form action="updateTip" method="post" enctype="multipart/form-data" id="tab1">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="tip_no" value="${tipboard.tip_no}">
	<input type="hidden" name="mem_id" value="${sessionScope.mem_id}">
	<input type="hidden" name="mem_pwd" value="${member.mem_pwd}" id="mem_pwd">
	<h5>제목</h5>
	<input type="text" name="tip_title" id="tip_title" value="${tipboard.tip_title}">
	<h5>팁 내용</h5>
	<textarea rows="5" cols="30" name="tip_content" id="tip_content">${tipboard.tip_content}</textarea>
	<h5>파일</h5>
	<input type="file" name="tip_file1">
	<h5>펫종류</h5>
	<input type="radio" name="tip_pet" value="cat" id="tip_pet1" <c:if test="${tipboard.tip_pet == 'cat'}">${'checked'}</c:if>>고양이
	<input type="radio" name="tip_pet" value="dog" id="tip_pet2" <c:if test="${tipboard.tip_pet == 'dog'}">${'checked'}</c:if>>강아지
	
	<h5>비밀번호 확인</h5>
	<input type="password" id="mem_pwd2">
	
	
	<div class="position1">
		<input type="submit" value="팁 게시판  수정" id="sign_up"/>
	</div>

</form>
</div>
<%@ include file="../include/footer.jsp" %>

</body>
</html>