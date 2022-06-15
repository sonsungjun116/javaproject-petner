<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>

<style>

span{
	display:inline-block;
	line-height: 60px;
}

</style>

<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	function ea(product_no){
		var product_ea = $("#product_ea").val();
		location.href="productea?product_no="+product_no+"&product_ea="+product_ea;
	};
	
	function updown(product_no, product_price){
		var product_ea = $("#product_ea").val();
		var frmData = "product_no="+product_no+"product_ea="+product_ea;
		$("#priceea").html(product_price * product_ea)
		location.href="productea?product_no="+product_no+"&product_ea="+product_ea;
	}
	
	$(function(){
		$('#loadreview').load('reviewnolist?product_no='+$("#product_no").val());
	});
</script>
<meta charset="UTF-8">
<title>상품상세</title>
</head>
<body>

<input type="hidden" name="product_no" id="product_no" value="${productdetail.product_no}">

<img src="<%=request.getContextPath()%>/upload/product/${productdetail.product_img}" width=500>
<span>
	<table align="center">
		<tr>
			<td colspan="2">${productdetail.product_name}</td>
		</tr>
		<tr>
			<td>가격</td>
			<td >${productdetail.product_price}</td>
		</tr>
		<tr>
			<td>수량</td>
			<td>
				<input type="number" id="product_ea" value="${productdetail.product_ea}" onclick="updown(${productdetail.product_no},${productdetail.product_price})">
				<%-- <input type="button" value="변경" onclick="ea(${productdetail.product_no})"> --%>
			</td>
		</tr>
		<tr>
			<td>총금액</td>
			<td id="priceea">${total}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="장바구니" onClick="location='insertBasket?mem_id=${sessionScope.mem_id}&product_no=${productdetail.product_no}&basket_ea=${productdetail.product_ea}&product_name=${productdetail.product_name}&product_price=${productdetail.product_price}'">	<!-- 아이디, 상품번호, 수량, 상품이름, 상품가격 -->
				<input type="button" value="결제하기" onClick="location='insertpayment?mem_id=${sessionScope.mem_id}&product_no=${productdetail.product_no}&product_ea=${productdetail.product_ea}'">	<!-- 아이디, 상품번호, 수량 -->
			</td>
		</tr>
	</table>
</span>

<div id="loadreview"></div>

<div>${productdetail.product_content}</div>

<c:forEach var="list" items="${list}">
	<div><img src="<%=request.getContextPath()%>/upload/product/${list}" width=900></div>
</c:forEach>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>