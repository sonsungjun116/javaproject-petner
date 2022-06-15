<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- <c:forEach var="plist" items="${list}">
	
	<p>${plist.basket_no}</p>
	<p>${plist.product_no}</p>
	<p>${plist.mem_id}</p>
	<p>${plist.basket_product_sum}</p>
	<p>${plist.product_name}</p>
	<p>${plist.product_price}</p>
	<p>${plist.basket_ea}</p>


</c:forEach> --%>

<p>${payment.mem_id}</p>
<p>${payment.product_ea}</p>
<p>${payment.product_no}</p>



</body>
</html>