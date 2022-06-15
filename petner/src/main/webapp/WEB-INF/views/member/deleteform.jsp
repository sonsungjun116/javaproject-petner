<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원삭제</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> 
<script src="${path}/js/member.js"></script>
<link rel="stylesheet" href="${path}/css/body.css">
</head>
<body>
<form action="mem_delete" method="post" id="tab3">
	<input type="hidden" name="mem_id" value="${member.mem_id}" id="mem_id">
	<input type="hidden" name="mem_pwd" value="${member.mem_pwd}" id="mem_pwd">
	<input type="hidden" name="mem_profile" value="${member.mem_profile}">
	<h4 class="empty">회원 삭제</h4>
	<h5 class="empty">비밀번호</h5>
	<input type="password" id="mem_pwd2" class="mem">	
	
	<div class="position1">
		<input type="submit" value="회원삭제" id="sign_up"/>
	</div>
	
</form>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>