<%@page import="petner.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> 
</head>
<body>

<script>
$(function(){
	$(".btn").click(function(){
		var basket_no = $(this).attr('id');
		var basket_ea = $("#basket_ea_"+basket_no).val();
		var mem_id = $("#mem_id").val();
		var product_price = $("#product_price").val();
		var frmData = "basket_no="+basket_no+"&basket_ea="+basket_ea+"&mem_id="+mem_id+"&product_price="+product_price;
		$.post("update_ea", frmData, function(data){
//			alert(data);
			$('#slist').html(data);
			location.reload();
		});
		
	});
	
});

</script>


<table>
	<tr>
		<td>장바구니 번호</td>
		<td>상품번호</td>
		<td>이미지</td>
		<td>회원아이디</td>
		<td>수량</td>
	</tr>
	<tr>

<c:if test="${empty list}">
	<p>데이터가 없습니다.</p>
</c:if>	
	
<form action="payment" method="post">	
<c:if test="${not empty list}">
<c:forEach var="basket" items="${list}">
<c:set var="product" value="<%=new Product()%>"></c:set>
<input type="hidden" value="${basket.mem_id}" id="mem_id">	
<input type="hidden" value="${basket.product_price}" id="product_price">

	<%-- <div class="basketdiv">
		<div class="row head">
			<div class="subdiv">
				<div class="check">선택</div>
				<div class="img">이미지</div>
				<div class="pname">상품명</div>
			</div>
			<div class="subdiv">
				<div class="basketprice">가격</div>
				<div class="num">수량</div>
				<div class="sum">합계</div>				
			</div>
			<div class="subdiv">
				<div class="basketcmd">삭제</div>
			</div>
			<div class="split"></div>
		</div>
		
		<div class="row data">
			<div class="subdiv">
				<div class="check"><input type="checkbox" name="basket_payment" id="basket_${basket.basket_no}" value="${basket.basket_no}"></div>
			</div>
		
		</div>
	</div>
 --%>	
	<tr>
		<td><input type="checkbox" name="basket_payment" id="basket_${basket.basket_no}" value="${basket.basket_no}"></td>
		<td id="td_${basket.basket_no}">${basket.basket_no}</td>
		<td>${basket.product_no}</td>
		<td><img src="${path}/upload/product/${product.product_img}"></td>
		<td>${basket.mem_id}</td>
		<td>${basket.product_price}</td>
		<td>
			<input type="number" value="${basket.basket_ea}" name="basket_ea" id="basket_ea_${basket.basket_no}">
				<input type="button" value="수량변경" id="${basket.basket_no}" class="btn">
		</td>
		<td>${basket.basket_product_sum}</td>
	 </tr>
</c:forEach>
</c:if>
	<tr>
	 	<td><input type="submit" value="결제하기"></td>
	 </tr>
</form>
</tr>
</table>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>