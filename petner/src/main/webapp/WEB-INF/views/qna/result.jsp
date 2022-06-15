<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 문의글 등록 -->
<c:if test="${insertResult == 1}">
	<script>
		alert("등록이 완료되었습니다.");
		location.href = "qna_list?page=${page}";
	</script>
</c:if>

<!-- 문의글 수정 -->
<c:if test="${updateResult == 1}">
	<script>
		alert("수정이 완료되었습니다.");
		location.href = "qna_view?qna_no=${qna_no}&page=${page}&state=view";
	</script>
</c:if>
<c:if test="${updateResult == 0}">
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		history.go(-1);
	</script>
</c:if>

<!-- 문의글 삭제 -->
<c:if test="${deleteResult == 1}">
	<script>
		alert("삭제가 완료되었습니다.");
		location.href = "qna_list?page=${page}";
	</script>
</c:if>

<!-- 문의글 답변 -->
<c:if test="${replyResult == 1}">
	<script>
		alert("답변이 완료되었습니다.");
		location.href = "qna_list?page=${page}";
	</script>
</c:if>

<!-- 비밀글 메시지 -->
<c:if test="${msg == 1}">
	<script>
		alert("열람 권한이 없습니다.");
		location.href = "qna_list?page=${page}";
	</script>
</c:if>