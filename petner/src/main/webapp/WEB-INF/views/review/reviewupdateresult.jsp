<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	alert("수정을 완료했습니다.");
	location.href="reviewdetail?review_no="+${sessionScope.review_no}+"&pageNum="+${pageNum};
</script>