<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰목록</title>
</head>
<body>

	<c:if test="${sessionScope.order_state == '구매확정'}">
	<div align="center">
		<input type="button" value="리뷰등록" onClick="location='reviewinsertform?mem_id=${sessionScope.mem_id}&product_no=${product_no}'">
	</div>
	</c:if>
	
	<table border=1 align="center" width=1000 height=550>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>첨부</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>추천여부</th>
		</tr>
	<c:if test="${empty nolist}">
		<tr>
			<td colspan="6">등록된 리뷰가 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${not empty nolist}">
	<c:set var="num" value="${no}"></c:set>
	<c:forEach var="nlist" items="${nolist}">
	<c:if test="${nlist.review_del == 'n'}">
		<tr>
			<td>${num}</td>
			<td><a href="reviewdetail?review_no=${nlist.review_no}&pageNum=${pp.currentPage}">${nlist.review_title}</a></td>
			<td><img src="<%=request.getContextPath()%>/upload/review/${nlist.review_file}" width=200></td>
			<td>${nlist.mem_name}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${nlist.review_date}"/></td>
			<td>${nlist.review_like}</td>
		</tr>
	</c:if>
	<c:set var="num" value="${num - 1}"></c:set>
	</c:forEach>
	</c:if>
	</table>
	
		<div align="center">
		<c:if test="${pp.startPage > pp.pagePerBlk}">
			<a href="productdetail?product_no=${product_no}&pageNum=${pp.startPage - 1}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
			<c:if test="${pp.currentPage == i}"></c:if>
			<a href="productdetail?product_no=${product_no}&pageNum=${i}">${i}</a>
		</c:forEach>
		<c:if test="${pp.endPage < pp.totalPage}">
			<a href="productdetail?product_no=${product_no}&pageNum=${pp.endPage + 1}">다음</a>
		</c:if>
		</div>
	</div>
</body>
</html>