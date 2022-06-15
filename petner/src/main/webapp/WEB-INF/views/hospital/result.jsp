<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 병원 등록 -->
<c:if test="${insertResult == 1}">
	<script>
		alert("병원 등록이 완료되었습니다.");
		location.href = "hospital_list";
	</script>
</c:if>

<!-- 병원 수정 -->
<c:if test="${updateResult == 1}">
	<script>
		alert("병원 수정이 완료되었습니다.");
		location.href = "hospital_view?hospital_no=${hospital_no}&page=${page}&state=view";
	</script>
</c:if>
<c:if test="${updateResult == 0}">
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		history.go(-1);
	</script>
</c:if>

<!-- 병원 삭제 -->
<c:if test="${deleteResult == 1}">
	<script>
		alert("병원 삭제가 완료되었습니다.");
		location.href = "hospital_list?page=${page}";
	</script>
</c:if>
<c:if test="${deleteResult == 0}">
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		history.go(-1);
	</script>
</c:if>