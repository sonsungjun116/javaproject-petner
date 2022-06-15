<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰목록</title>
</head>
<body>

	<table border=1 align="center" width=1000 height=550>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>첨부</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>추천여부</th>
		</tr>
	<c:if test="${empty list}">
		<tr>
			<td colspan="6">등록된 리뷰가 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${not empty list}">
	<c:set var="num" value="${no}"></c:set>
	<c:forEach var="rlist" items="${list}">
	<c:if test="${rlist.review_del == 'n'}">
		<tr>
			<td>${num}</td>
			<td><a href="reviewdetail?review_no=${rlist.review_no}&pageNum=${pp.currentPage}">${rlist.review_title}</a></td>
			<td><img src="<%=request.getContextPath()%>/upload/review/${rlist.review_file}" width=200></td>
			<td>${rlist.mem_name}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${rlist.review_date}"/></td>
			<td>${rlist.review_like}</td>
		</tr>
	</c:if>
	</c:forEach>
	</c:if>
	</table>
	
		<div align="center">
		<c:if test="${pp.startPage > pp.pagePerBlk}">
			<a href="reviewlist?pageNum=${pp.startPage - 1}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
			<c:if test="${pp.currentPage == i}"></c:if>
			<a href="reviewlist?pageNum=${i}">${i}</a>
		</c:forEach>
		<c:if test="${pp.endPage < pp.totalPage}">
			<a href="reviewlist?pageNum=${pp.endPage + 1}">다음</a>
		</c:if>
		</div>

</body>
</html>