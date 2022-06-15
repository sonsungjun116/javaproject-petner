<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 관리자 등록 -->
<c:if test="${insertResult == 1}">
	<script>
		alert("관리자 등록이 완료되었습니다.");
		location.href = "admin_list";
	</script>
</c:if>

<!-- 관리자 수정 -->
<c:if test="${updateResult == 1}">
	<script>
		alert("관리자 수정이 완료되었습니다.");
		location.href = "admin_view?admin_no=${admin_no}&page=${page}";
	</script>
</c:if>

<!-- 관리자 삭제 -->
<c:if test="${deleteResult == 1}">
	<script>
		alert("관리자 삭제가 완료되었습니다.");
		location.href = "admin_list?page=${page}";
	</script>
</c:if>