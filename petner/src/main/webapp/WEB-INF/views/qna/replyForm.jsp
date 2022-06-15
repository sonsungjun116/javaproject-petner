<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<!-- 외부 자바스크립트 파일(유효성 검사) 불러오기 -->
<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="${path}/js/qna.js"></script>
<script src="${path}/js/jquery.js"></script>

<script src="http://code.jquery.com/jquery-latest.js"></script>

<body>
	<form action="qna_reply" method="post" enctype="multipart/form-data"
		onSubmit="return check()">
		<input type="hidden" id="page" name="page" value="${page}"> <input
			type="hidden" id="qna_no" name="qna_no" value="${qna.qna_no}">
		<input type="hidden" id="qna_ref" name="qna_ref"
			value="${qna.qna_ref}">
		<h2 align=center>답변 작성</h2>
		<table border=1 align=center  width=500 height=400>
			<tr>
				<th>작성자</th>
				<td><c:if test="${sessionScope.mem_id == 'admin'}">
   						<input type="hidden" id="mem_id" name="mem_id" value="관리자">관리자</c:if>
  					<c:if test="${sessionScope.mem_id != 'admin'}">
   						<input type="hidden" id="mem_id" name="mem_id"
							value="${sessionScope.mem_id}">${sessionScope.mem_id}</c:if></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="hidden" size=52 id="qna_title" name="qna_title"
					value="${qna.mem_id}님 문의에 대한 답변입니다.">${qna.mem_id}님 문의에 대한 답변입니다.
					<c:if test="${qna.qna_secret == 'Y'}">
						<input type="checkbox" onclick="$('#qna_secret').val('Y')" checked>비밀글
						<input type="hidden" id="qna_secret" name="qna_secret" value="N"></c:if>
					<c:if test="${qna.qna_secret == 'N'}">
						<input type="checkbox" onclick="$('#qna_secret').val('Y')">비밀글
						<input type="hidden" id="qna_secret" name="qna_secret" value="N"></c:if>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="15" cols="52" id="qna_content" name="qna_content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
	</div>
   <%@ include file="../include/footer.jsp" %>
</body>