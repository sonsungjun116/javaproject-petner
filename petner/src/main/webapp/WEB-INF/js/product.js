$(function() {
	$("#insertform").submit(function() {
		if ($("#cat").is(":checked") == false &&
			$("#dog").is(":checked") == false){
			alert("펫종류를 선택하세요.");
			return false;
		}
		
		if ($("#product_categorycat").val() == "" &&
			$("#product_categorydog").val() == "") {
			alert("상품종류를 선택하세요.");
			return false;
		}
		
		if ($("#product_name").val() == "") {
			alert("상품명을 입력하세요.");
			$("#product_name").focus();
			return false;
		}
		
		if ($("#product_img").val() == "") {
			alert("상품이미지를 등록하세요.");			
			return false;
		}
		
		if ($("#product_content").val() == "" &&
			$("#product_contentimg").val() == "") {
			alert("설명글 또는 설명이미지를 입력하세요.");
			$("#product_content").focus();
			return false;
		}
		
		if ($("#product_price").val() == "") {
			alert("상품가격을 입력하세요.");
			$("#product_price").focus();
			return false;
		}
		
		if(isNaN($("#product_price").val())) {
			alert("상품가격은 숫자로 입력하세요.")
			$("#product_price").focus();
			return false;
		}
		
		if ($("#product_stock").val() == "") {
			alert("수량을 입력하세요.");
			$("#product_stock").focus();
			return false;
		}
		
		if(isNaN($("#product_stock").val())) {
			alert("수량은 숫자로 입력하세요.")
			$("#product_stock").focus();
			return false;
		}
		
	});
});

$(function() {
	$("#updateform").submit(function() {
		if ($("#cat").is(":checked") == false &&
			$("#dog").is(":checked") == false){
			alert("펫종류를 선택하세요.");
			return false;
		}
		
		if ($("#product_categorycat").val() == "" &&
			$("#product_categorydog").val() == "") {
			alert("상품종류를 선택하세요.");
			return false;
		}
		
		if ($("#product_name").val() == "") {
			alert("상품명을 입력하세요.");
			$("#product_name").focus();
			return false;
		}
		
		if ($("#product_price").val() == "") {
			alert("상품가격을 입력하세요.");
			$("#product_price").focus();
			return false;
		}
		
		if(isNaN($("#product_price").val())) {
			alert("상품가격은 숫자로 입력하세요.")
			$("#product_price").focus();
			return false;
		}
		
		if ($("#product_stockadd").val() == "") {
			alert("+ 또는 -를 선택하세요.");
			return false;
		}
		
		if ($("#oldStock").val() < $("#product_stock").val()) {
			alert("남아있는 재고보다 더 많이 감소시킬 수 없습니다.");
			$("#product_stock").focus();
			return false;
		}
		
		if ($("#product_stock").val() == "") {
			alert("수량을 입력하세요.");
			$("#product_stock").focus();
			return false;
		}
		
		if(isNaN($("#product_stock").val())) {
			alert("수량은 숫자로 입력하세요.")
			$("#product_stock").focus();
			return false;
		}
		
	});
});