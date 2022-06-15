<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
table, td, th {
  text-align: center;
};

li {
   display: inline-block;
}

li a {
	text-decoration: none;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border=1 align=center width=1000 height=550>
	<h2 align="center">팁 게시판</h2>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
		<td>펫</td>
	</tr>
	
	<c:if test="${empty list}">
		<tr>
			<td colspan=5>데이터가 없습니다.</td>
		</tr>
	</c:if>
	
	<c:if test="${not empty list}">
		<c:set var="no1" value="${no}"/>
		<c:forEach var="tiplist" items="${list}">
			<tr>
				<td>${no1}</td>
				<td><a href="tipboard_detail?tip_no=${tiplist.tip_no}&pageNum=${pp.currentPage}">${tiplist.tip_title}</a></td>
				<td>${tiplist.mem_id}</td>
				<td>${tiplist.tip_readcount}</td>
				<td>${tiplist.tip_pet}</td>
			</tr>
			<c:set var="no1" value="${no1 -1}"/>
		</c:forEach>
	
	</c:if>

</table>

<form action="tipboardList" align=center>
			<input type="hidden" name="pageNum" value="1"> 
			<select	name="search">
				<option value="tip_title"	<c:if test="${search=='tip_title'}">selected="selected" </c:if>>제목</option>
				<option value="tip_pet"	<c:if test="${search=='tip_pet'}">selected="selected" </c:if>>펫종류</option>
				<option value="mem_id"	<c:if test="${search=='mem_id'}">selected="selected" </c:if>>작성자</option>
			</select> 
			<input type="text" name="keyword"> 
			<input type="submit" value="확인">
		</form>
		
		<div class="pagination" align=center style="list-style:none">
			<!-- 검색 했을 경우의 페이징 처리 -->
			<c:if test="${not empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk}">
					<li><a
						href="tipboardList?pageNum=${pp.startPage - 1}&search=${search}&keyword=${keyword}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}"></c:if>>
					<a href="tipboardList?pageNum=${i}&search=${search}&keyword=${keyword}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a
						href="tipboardList?pageNum=${pp.endPage + 1}&search=${search}&keyword=${keyword}">다음</a></li>
				</c:if>
			</c:if>
			
			<!-- 전체 목록의 페이징 처리 -->
			<c:if test="${empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="tipboardList?pageNum=${pp.startPage - 1}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>>
						<a href="tipboardList?pageNum=${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="tipboardList?pageNum=${pp.endPage + 1}">다음</a></li>
				</c:if>
			</c:if>
		</div>
		<br>
		<div align="center">
			<a href="insertTipboard">글 입력</a>
		</div>
		</div>
		<%@ include file="../include/footer.jsp" %>

</body>
</html>