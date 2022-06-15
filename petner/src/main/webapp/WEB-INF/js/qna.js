// 기본 유효성 검사
function check() {
	if ($("#qna_category").val() == "") {
		alert("카테고리를 선택하세요.");
		return false;
	}
	if ($("#qna_title").val() == "") {
		alert("제목을 입력하세요.");
		$("#qna_name").focus();
		return false;
	}
	if($("#qna_content").val() == ""){
		alert("내용을 입력하세요.");
		$("#qna_content").focus();
		return false;
	}
	if ($("#qna_content").val().length > 1000){
		alert("문의 내용을 1000자 이하로 입력하세요.");
		$("#qna_content").focus();
		return false;
	}
	if ($("#qna_file").val() == "") {
		alert("이미지 파일을 업로드하세요.");
		return false;
	}
}
