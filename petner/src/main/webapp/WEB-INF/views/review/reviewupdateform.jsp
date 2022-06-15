<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="${path}/js/review.js"></script>
<meta charset="UTF-8">
<title>리뷰등록</title>
</head>
<body>

	<form action="reviewupdateresult" method="post" id="reviewupdate" enctype="multipart/form-data" align="center">
		<input type="hidden" name="mem_id" value="${member.mem_id}">
		<input type="hidden" name="mem_name" value="${member.mem_name}">
		<input type="hidden" name="product_no" value="${product.product_no}">
		<input type="hidden" name="review_no" value="${old.review_no}">
		
		<table border=1 align="center">
			<tr>
				<th>제목</th>
				<td><input type="text" name="review_title" id="review_title" value="${old.review_title}"></td>
				<th>작성자명</th>
				<td>${old.mem_name}</td>
			</tr>
			<tr>
				<th colspan="2">내용</th>
				<td colspan="2">
					좋아요<input type="radio" name="review_like" id="good" value="좋아요" <c:if test="${old.review_like == '좋아요'}">${'checked'}</c:if>>
					별로예요<input type="radio" name="review_like" id="bed" value="별로예요" <c:if test="${old.review_like == '별로예요'}">${'checked'}</c:if>>
				</td>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="10" cols="50" name="review_content" id="review_content">${old.review_content}</textarea></td>
			</tr>
			<tr>
				<th>사진첨부</th>
				<td colspan="3">
				<input type="file" multiple="multiple" name="review_file_" id="review_file">
				<c:forEach var="flist" items="${flist}">
					<div><img src="<%=request.getContextPath()%>/upload/review/${flist}" width=300></div>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="수정하기">
					<input type="button" value="삭제하기" 
						onClick="if(confirm('정말 삭제하시겠습니까?'))location='reviewdeleteresult?review_no=${old.review_no}&pageNum=${pageNum}'">
					<input type="button" value="돌아가기" onClick="javascript:history.go(-1)">
				</td>
			</tr>
		</table>
	</form>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>