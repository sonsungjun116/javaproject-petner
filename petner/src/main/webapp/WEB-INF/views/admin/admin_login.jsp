<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>

.a {
width: 177px;
margin: 0 auto;
}

.a h4 {
	text-align: left;
	margin-bottom: 5px;
	}

#sign_up {
	margin-top: 20px;
	}

</style>


</head>
<body>
	<h2 align="center">PETNER 관리자</h2>
	<form method="post" action="admin_login" id="tab1" align="center">
	<div class="a">
		<h4 class="empty">ID</h4>
		<input type="text" name="admin_id" id="admin_id" placeholder="ID를 입력하세요.">

		<h4 class="empty">비밀번호</h4>
		<input type="password" name="admin_pwd" id="admin_pwd"
			placeholder="비밀번호를 입력하세요." class="mem1">

		<div class="position1">
			<input type="submit" value="로그인" id="sign_up">
		</div>
	</div>
	</form>
</body>
</html>