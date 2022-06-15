<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>


	<div class="search">
		<div class="title">
			<h4>반려동물 쇼핑몰 커뮤니티</h4>
			<h2>펫트너(PETNER)</h2>
		</div>
		<div class="searchtab">
			<input type="text">
		</div>
		<button type="button" class="button">
			<img src="${path}/img/magni.jpg">
		</button>
	</div>


	<span><h2>인기 지식정보</h2></span>
	<div>
		<p>🐱고양이</p>
		<p class="add">
			<a href="productlist">더보기</a>
		</p>
	</div>
	<div class="info1">
	<c:forEach var="list" items="${list}" begin="0" end="9" step="1">
		<div class="content">
			<a href="productdetail?product_no=${list.product_no}"> <img src="${path}/upload/product/${list.product_img}">${list.product_name}</a>
		</div>
	</c:forEach>	
	</div>
	<div class="info2">
		<p>팁게시판</p>
		<p class="add">
			<a href="tipboardList">더보기</a>
		</p>
	<c:forEach var="list" items="${tlist}" begin="0" end="4" step="1">
		<ol>
			<li><a href="tipboard_detail?tip_no=${list.tip_no}">${list.tip_title}</a></li>
			<li>${list.tip_content}</li>
		</ol>
	</c:forEach>	
	</div>
	<div class="info2">
		<p>미아게시판</p>
		<p class="add">
			<a href="hospital_list">더보기</a>
		</p>
	<c:forEach var="list" items="${llist}" begin="0" end="4" step="1">
		<ol>
			<li><a href="lostView?lost_no=${list.lost_no}&page=1">${list.lost_title}</a></li>
			<li>${list.lost_content}</li>
		</ol>
	</c:forEach>	
	</div>
	</div>
<%@include file="include/footer.jsp" %>
</body>

</html>