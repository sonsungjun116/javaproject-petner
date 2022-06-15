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
	<form action="deleteTip" method="post" enctype="multipart/form-data" id="tab3">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="tip_no" value="${tipboard.tip_no}">
	<input type="hidden" name="mem_id" value="${sessionScope.mem_id}">
	<input type="hidden" name="mem_pwd" value="${member.mem_pwd}" id="mem_pwd">
	<input type="hidden" name="tip_file" value="${tipboard.tip_file}">
	

	<h5>비밀번호 확인</h5>
	<input type="password" id="mem_pwd2">
	
	<div class="position1">
		<input type="submit" value="팁 게시판  삭제" id="sign_up"/>
	</div>

</form>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>