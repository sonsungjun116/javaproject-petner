<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 문의글 등록 -->
<c:if test="${mem_type == '사업자' && result == 1}">
	<script>
		alert("완료되었습니다.");
		location.href = "sell_list";
	</script>
</c:if>
<c:if test="${(mem_type == '일반회원' || mem_type == '일반') && result == 1}">
	<script>
		alert("완료되었습니다.");
		location.href = "order_list";
	</script>
</c:if>
