<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="${path}/js/product.js"></script>
<meta charset="UTF-8">
<title>상품등록</title>
</head>

<style>

h5 {
margin-bottom: 10px;
}
form {
margin-bottom: 50px;
}

</style>

<body>
	<script>
		$(document).ready(function(){
			$("#select_cat").hide();
			$("#select_dog").hide();
			
			$("#cat").click(function(){
				$("#select_cat").show();
				$("#product_categorycat").attr("disabled", false);			
				$("#select_dog").hide();
				$("#product_categorydog").attr("disabled", true);
			});
			
			$("#dog").click(function(){
				$("#select_cat").hide();
				$("#product_categorycat").attr("disabled", true);
				$("#select_dog").show();
				$("#product_categorydog").attr("disabled", false);
			});
			
		});
	</script>
	
	<div>
	등록번호 : ${seller.seller_no}<br>
	상호명 : ${seller.seller_name}
	</div>
	
	<form action="productinsertresult" method="post" id="productinsert" enctype="multipart/form-data">
		<input type="hidden" name="seller_no" value="${seller.seller_no}">
		<input type="hidden" name="seller_name" value="${seller.seller_name}">
		
		<h5>펫종류</h5>
		<input type="radio" name="product_pet" value="고양이" id="cat">고양이
		<input type="radio" name="product_pet" value="개" id="dog">개
		
		<h5>상품종류</h5>
		<div id="select_cat">
		<select name="product_category" id="product_categorycat">
			<option value="">선택하세요</option>
			<option value="사료">사료</option>
			<option value="간식">간식</option>
			<option value="배변">배변</option>
			<option value="미용">미용</option>
			<option value="급식기">급식기</option>
			<option value="장난감">장난감</option>
			<option value="이동장">이동장</option>
			<option value="캣타워">캣타워</option>
			<option value="스크래쳐">스크래쳐</option>
			<option value="영양제">영양제</option>
		</select>
		</div>
	
		<div id="select_dog">
		<select name="product_category" id="product_categorydog">
			<option value="">선택하세요</option>
			<option value="사료">사료</option>
			<option value="간식">간식</option>
			<option value="배변">배변</option>
			<option value="미용">미용</option>
			<option value="급식기">급식기</option>
			<option value="장난감">장난감</option>
			<option value="이동장">이동장</option>
			<option value="캣타워">하우스</option>
			<option value="스크래쳐">산책줄</option>
			<option value="영양제">영양제</option>
		</select>
		</div>
		
		<h5>상품명</h5>
		<input type="text" name="product_name" id="product_name">
		
		<h5>제조사</h5>
		<input type="text" name="product_make" id="product make">
		
		<h5>상품이미지</h5>
		<input type="file" name="product_img_" id="product_img">
		
		<h5>상품설명</h5>
		<textarea rows="10" cols="50" name="product_content" id="product_content"></textarea>
		<input type="file" multiple="multiple" name="product_contentimg_" id="product_contentimg">
		
		<h5>상품가격</h5>
		<input type="text" name="product_price" id="product_price">
		
		<h5>재고</h5>
		<select name="product_stockadd" id="product_stockadd" disabled>
			<option value="">선택</option>
			<option value="+" selected>+</option>
			<option value="-">-</option>
		</select>
		<input type="text" name="product_stock" id="product_stock">
		
		<input type="submit" value="등록하기">
		<input type="reset" value="다시작성">
		<input type="button" value="목록으로" onClick="location='productlist'">
	</form>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>