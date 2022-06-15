<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰상세</title>
</head>
<body>
	<c:if test="${reviewdetail.mem_id == mem_id}">
	<div align="center">
		<input type="button" value="수정/삭제" onClick="location='reviewupdateform?product_no=${reviewdetail.product_no}&review_no=${reviewdetail.review_no}&pageNum=${pageNum}'">
	</div>
	</c:if>
	
	<table border="1" align="center">
		<tr>
			<td>${reviewdetail.review_title}</td>
			<td>${reviewdetail.review_like}</td>
		</tr>
		<tr>
			<td colspan="2">
			<c:forEach var="flist" items="${flist}">
				<span><img src="<%=request.getContextPath()%>/upload/review/${flist}" width=300></span>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="2">${reviewdetail.review_content}</td>
		</tr>
	</table>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>