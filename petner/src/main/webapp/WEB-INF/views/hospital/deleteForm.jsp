<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<script>
	function chk() {
		if (form.admin_pwd.value != form.hospital_pwd.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.hospital_pwd.focus();
			return false;
		}
	}
</script>

<body>
	<form action="hospital_delete" method="post" name="form" onSubmit="return chk()">
		<input type="hidden" name="page" value="${page}">
		<input type="hidden" name="admin_pwd" value="${admin.admin_pwd}">
		<input type="hidden" name="hospital_no" value="${hospital.hospital_no}">
		<table border=1 align=center>
		<caption><h2>삭제</h2></caption>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="hospital_pwd" name="hospital_pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="확인">
				<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>