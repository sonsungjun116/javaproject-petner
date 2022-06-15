<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 
<form action="update_seller_accept" method="post">
	<table>
		<tr>
			<th>사업자번호</th>
			<th>회사명</th>
			<th>회사주소</th>
			<th>회사연락처</th>
			<th>회사사업자 아이디</th>
			<th>승인상태</th>		
		</tr>

		<c:forEach var="list" items="${list}">		
		<tr>
			<td>${list.seller_no}</td>
			<td>${list.seller_name}</td>
			<td>${list.seller_addr1}-${list.seller_addr2}</td>
			<td>${list.seller_tel1}-${list.seller_tel2}-${list.seller_tel3}</td>
			<td>${list.mem_id}</td>
			<td>${list.seller_accept}</td>
			<c:if test="${list.seller_accept == 0}">
			<td><input type="checkbox" name="update_seller_accept" id="update_seller_accept" value="${list.seller_no}"></td>
			</c:if>		
		</tr>
		</c:forEach>
		
	
	</table>
		<input type="submit" value="승인허용">
</form>
</div>
<%@ include file="../include/footer.jsp" %>


</body>
</html>