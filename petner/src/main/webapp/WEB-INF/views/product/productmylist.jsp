<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<style>
table, td, th {
  text-align: center;
};
</style>
<meta charset="UTF-8">
<title>내상품목록</title>
</head>
<body>
	<h2 align="center">상품 목록</h2>
	<table border=1 align="center" width=1000 height=550>
		<tr>
			<th>번호</th>
			<th colspan="2">종류</th>
			<th>사진</th>
			<th>이름</th>
			<th>가격</th>
			<th>재고</th>
			<th>변경</th>
		</tr>
	<c:if test="${empty mylist}">
		<tr>
			<td colspan="8">등록된 상품이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${not empty mylist}">
		<!-- 내 등록목록 -->
		<c:forEach var="mlist" items="${mylist}">
		<c:if test="${mlist.product_del == 'n'}">
		<tr>
			<td>${mlist.product_no }</td>
			<td>${mlist.product_pet }</td>
			<td>${mlist.product_category }</td>
			<td><a href="productdetail?product_no=${mlist.product_no}&pageNum=${pp.currentPage}">
				<img src="<%=request.getContextPath()%>/upload/product/${mlist.product_img}" width=300></a>
			</td>
			<td>
				${mlist.product_make }<br>
				${mlist.product_name }
			</td>
			<td>${mlist.product_price }</td>
			<td>${mlist.product_stock }</td>
			<td><input type="button" value="수정/삭제" onClick="location='productupdateform?product_no=${mlist.product_no}&pageNum=${pageNum}'"></td>
		</tr>
		</c:if>
		</c:forEach>
	</c:if>
	</table>
	
	<form action="productmylist" align="center">
		<input type="hidden" name="pageNum" value="1">
		<select name="search">
			<option value="product_no" <c:if test="${search=='product_no'}">selected="selected"</c:if>>상품번호</option>
			<option value="product_name" <c:if test="${search=='product_name'}">selected="selected"</c:if>>상품명</option>
			<option value="product_make" <c:if test="${search=='product_make'}">selected="selected"</c:if>>제조사명</option>
			<option value="seller_name" <c:if test="${search=='seller_name'}">selected="selected"</c:if>>판매자명</option>
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="확인">
	</form>
	
	<div align="center">
	<!-- 전체 페이징 -->
	<c:if test="${empty keyword}">
		<c:if test="${pp.startPage > pp.pagePerBlk}">
			<a href="productmylist?pageNum=${pp.startPage - 1}&seller_no=${seller_no}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
			<c:if test="${pp.currentPage == i}"></c:if>
			<a href="productmylist?pageNum=${i}&seller_no=${seller_no}">${i}</a>
		</c:forEach>
		<c:if test="${pp.endPage < pp.totalPage}">
			<a href="productmylist?pageNum=${pp.endPage + 1}&seller_no=${seller_no}">다음</a>
		</c:if>
	</c:if>
	
	<!-- 검색 페이징 -->
	<c:if test="${not empty keyword}">
		<c:if test="${pp.startPage > pp.pagePerBlk}">
			<a href="productmylist?pageNum=${pp.startPage - 1}&search=${search}&keyword=${keyword}&seller_no=${seller_no}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
			<c:if test="${pp.currentPage == i}"></c:if>
			<a href="productmylist?pageNum=${i}&search=${search}&keyword=${keyword}&seller_no=${seller_no}">${i}</a>
		</c:forEach>
		<c:if test="${pp.endPage < pp.totalPage}">
			<a href="productmylist?pageNum=${pp.endPage + 1}&search=${search}&keyword=${keyword}&seller_no=${seller_no}">다음</a>
		</c:if>
	</c:if>
	</div>
	<c:if test="${sessionScope.seller_no != null}">
		<div align="center"><a href="productinsertform?seller_no=${sessionScope.seller_no}">상품등록</a></div>
	</c:if>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>