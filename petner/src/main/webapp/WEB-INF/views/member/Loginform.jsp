<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/css/body.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="${path}/js/member.js"></script>
<script>

function pwd_find(){
	 window.open("pwd_find.nhn","비번찾기","width=450,height=500");
}

</script>

<title>로그인</title>
</head>


<body>

	<form method="post" action="Login" id="tab1">
		<h5 class="empty">ID</h5>
		<input type="text" name="mem_id" id="mem_id" placeholder="ID를 입력하세요.">

		<h5 class="empty">비밀번호</h5>
		<input type="password" name="mem_pwd" id="mem_pwd"
			placeholder="비밀번호를 입력하세요." class="mem1">

		<div class="position1">
			<input type="submit" value="로그인" id="sign_up">
		</div>
		<div class="position1">
			<input type="button" value="비번찾기" id="sign_up" onclick="pwd_find()"/>
		</div>
	</form>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>