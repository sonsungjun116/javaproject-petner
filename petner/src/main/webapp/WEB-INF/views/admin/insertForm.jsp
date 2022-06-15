<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<form action="admin_insert" method="post">
		<table border=1 align=center>
			<caption><h2>관리자 등록</h2></caption>
			<tr>
				<th>아이디</th>
				<td><input type="text" size=30 id="admin_id"
					name="admin_id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" size=30 id="admin_pwd"
					name="admin_pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="확인">
					<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>