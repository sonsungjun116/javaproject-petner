<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <%-- <%@ include file="header.jsp"%> --%> 
   <%@ include file="../include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<style>
table, td, th {
  text-align: center;
};
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" align="center">
		<h2 class="text-primary">미아 게시판</h2>
		<table class="table table-striped" border="1" width=1000 height=550>
			<tr>
				<td>번호</td>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:set var="no1" value="${no }"></c:set>
				<c:forEach var="lost" items="${list}">
					<tr>
						<td align=center>${no1}</td>
						<c:if test="${lost.lost_del =='y'}">
							<td colspan="4">여러분의 관심덕분에 아이를 찾았습니다 감사합니다.</td>
						</c:if>
						 <c:if test="${lost.lost_del !='y' }">	<!-- ${path}를 사용할 수 없다면 제거 -->
							<td><a href="${path}/lostView?lost_no=${lost.lost_no}&pageNum=${pp.currentPage}"    
						<%-- 	<td><a href="view.do?num=${board.num}&pageNum=${pp.currentPage}" --%>
									class="btn btn-default"> 
									<%-- <c:if test="${board.re_level >0 }">
										<img alt="" src="${path }/images/level.gif" height="2" width="${board.re_level *5 }">
										<img alt="" src="${path }/images/re.gif">
									</c:if>  --%>
									${lost.lost_title} 
									<%-- <c:if test="${board.readcount > 30 }">
										<img alt="" src="${path }/images/hot.gif">
									</c:if></a></td>  --%></a></td>
							<td align=center>${lost.mem_id}</td>
							<td align=center>${lost.lost_date}</td>
							<td align=center>${lost.lost_readcount}</td>
						</c:if>
					</tr>
					<c:set var="no1" value="${no1 - 1}"></c:set>
				</c:forEach>
			</c:if>
		</table>
		<br>
		<form action="${path}/lostListView?pageNum=1"> 
		<%-- <form action="${path}/list/pageNum/1"> <!-- ${path}는 header.jsp에 현재프로젝트 명 --> --%>
			<select name="search">
				<option value="lost_title"
					<c:if test="${search=='lost_title'}">selected="selected" </c:if>>제목</option>
				<option value="lost_content"
					<c:if test="${search=='lost_content'}">selected="selected" </c:if>>내용</option>
				<option value="mem_id"
					<c:if test="${search=='mem_id'}">selected="selected" </c:if>>작성자</option>
				<option value="subcon"
					<c:if test="${search=='subcon'}">selected="selected" </c:if>>제목+내용</option>
			</select> 
			<input type="text" name="keyword"> 
			<input type="submit" value="확인">
		</form>
		<br>
	<!-- 	<ul class="pagination"> -->
			<div align=center>
			<!-- 검색 했을 경우의 페이징 처리 -->
			<c:if test="${not empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a
						href="lostListView?pageNum=${pp.startPage - 1}&search=${search}&keyword=${keyword}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<!-- <li --> <c:if test="${pp.currentPage==i}"><!-- class="active" --></c:if><!-- > --><a
						href="lostListView?pageNum=${i}&search=${search}&keyword=${keyword}">${i}</a><!-- </li> -->
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a
						href="lostListView?pageNum=${pp.endPage + 1}&search=${search}&keyword=${keyword}">다음</a></li>
				</c:if>
			</c:if>
			
			<!-- 전체 목록의 페이징 처리 -->
			<c:if test="${empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">	<!-- 두번째 블록에서는 startpage가 11이다 -->
					<li><a href="lostListView?pageNum=${pp.startPage - 1}">이전</a></li> <!-- pageNum만 넘김 -->
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<!-- <li --> <c:if test="${pp.currentPage==i}"><!-- class="active" --></c:if>
						<a href="lostListView?pageNum=${i}">${i}</a><!-- </li> -->
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">	<!-- endpage 첫번째블럭10 두번째20 세번째30... -->
					<li><a href="lostListView?pageNum=${pp.endPage + 1}">다음</a></li>
				</c:if>
			</c:if>
			</div>
		<!-- </ul> -->
		<br>
		<div align="center">
			<a href="lostInsertForm">글 작성</a>
		</div>
	</div>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>