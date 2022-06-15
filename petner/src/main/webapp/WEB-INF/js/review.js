$(function() {
	$("#reviewinsert").submit(function() {
		if ($("#review_title").val() == "") {
			alert("제목을 입력하세요.");
			$("#product_name").focus();
			return false;
		}
		
		if ($("#review_like").val() == "" &&
			$("#review_like").val() == "") {
			alert("추천여부를 선택하세요.");
			return false;
		}
		
		if ($("#review_content").val() == "") {
			alert("내용을 입력하세요.");			
			return false;
		}
		
	});
});

$(function() {
	$("#reviewupdate").submit(function() {
		if ($("#review_title").val() == "") {
			alert("제목을 입력하세요.");
			$("#product_name").focus();
			return false;
		}
		
		if ($("#review_like").val() == "" &&
			$("#review_like").val() == "") {
			alert("추천여부를 선택하세요.");
			return false;
		}
		
		if ($("#review_content").val() == "") {
			alert("내용을 입력하세요.");			
			return false;
		}
		
	});
});