<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>

<style>

.b{
   width: 50%;
   margin: 0 auto;
}

</style>

<meta charset="UTF-8">
<title>상세 게시판</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> 
<script src="${path}/js/tipboard.js"></script>
</head>
<body>


	<div class="b">		
			
		<c:if test="${not empty tipboard.tip_file}">
			<img src="${path}/upload/tipboard/${tipboard.tip_file}">
		</c:if>

		<c:if test="${empty tipboard.tip_file}">
			<p>이미지가 없습니다.</p>
		</c:if>

		<p style="display: block">${tipboard.tip_title}</p>
		<p style="display: block">${tipboard.tip_content}</p>
		<p>${tipboard.tip_readcount}</p>
		<p>${tipboard.tip_pet}</p>

		<c:if test="${sessionScope.mem_id == tipboard.mem_id}">
			<input type="button" value="팁게시판 삭제"
				onClick="location.href='statusTipboard?tip_no=${tipboard.tip_no}&pageNum=${pageNum}&state=del'">
			<input type="button" value="팁게시판 수정"
				onClick="location.href='statusTipboard?tip_no=${tipboard.tip_no}&pageNum=${pageNum}&state=edit'">
		</c:if>
		<input type="button" value="팁게시판 목록"
			onClick="location.href='tipboardList?pageNum=${pageNum}'">

	</div>

	<div id="slist"></div>

	<form name="frm" id="frm">
		<input type="hidden" name="tip_no" id="tip_no"
			value="${tipboard.tip_no}"> <input type="hidden"
			name="mem_id" id="mem_id" value="${sessionScope.mem_id}">

		<textarea rows="5" cols="100" name="tipreply_content"
			id="tipreply_content"></textarea>
		
		<input type="button" value="댓글달기" id="repInsert">
	</form>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>