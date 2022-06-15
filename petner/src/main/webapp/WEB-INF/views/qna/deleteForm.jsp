<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<script>
	function chk() {
		if (form.qna_pwd.value != form.qna_pwd2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.qna_pwd2.focus();
			form.qna_pwd2.value = '';
			return false;
		}
	}
</script>

<body>
	<form action="qna_delete" method="post" name="form" onSubmit="return chk()">
		<input type="hidden" id="page" name="page" value="${page}">
		<input type="hidden" id="qna_no" name="qna_no" value="${qna.qna_no}">
		<input type="hidden" id="qna_pwd" name="qna_pwd" value="${qna.qna_pwd}">
		<h2 align=center>삭제</h2>
		<table border=1 align=center>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="qna_pwd2" name="qna_pwd2"></td>
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