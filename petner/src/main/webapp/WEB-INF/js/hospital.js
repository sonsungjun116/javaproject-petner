// 기본 유효성 검사
function check() {
	if ($("#hospital_name").val() == "") {
		alert("병원명을 입력하세요.");
		$("#hospital_name").focus();
		return false;
	}
	if ($("#hospital_loc").val() == "") {
		alert("지역을 선택하세요.");
		return false;
	}
	if ($("#hospital_addr").val() == "") {
		alert("주소를 입력하세요.");
		$("#hospital_addr").val("").focus();
		return false;
	}
	if ($("#hospital_tel").val() == "") {
		alert("전화번호를 입력하세요.");
		$("#hospital_tel").focus();
		return false;
	}
	if ($("#hospital_time").val() == "") {
		alert("운영 시간을 입력하세요.");
		$("#hospital_time").focus();
		return false;
	}
	if ($("#hospital_time").val().length > 250) {
		alert("운영 시간을 250자 이하로 입력하세요.");
		$("#hospital_time").focus();
		return false;
	}
	if ($("#hospital_24Y").is(":checked")==false && $("#hospital_24N").is(":checked")==false) {
		alert("24시 여부를 선택하세요.");
		return false;
	}
	if ($("#hospital_holidayY").is(":checked")==false && $("#hospital_holidayN").is(":checked")==false) {
		alert("연중무휴 여부를 선택하세요.");
		return false;
	}
	if ($("#hospital_parkingY").is(":checked")==false && $("#hospital_parkingN").is(":checked")==false) {
		alert("주차 가능 여부를 선택하세요.");
		return false;
	}
	if($("#hospital_content").val() == ""){
		alert("병원 소개 내용을 입력하세요.");
		$("#hospital_content").focus();
		return false;
	}
	if ($("#hospital_content").val().length > 1000){
		alert("병원 소개를 1000자 이하로 입력하세요.");
		$("#hospital_content").focus();
		return false;
	}
	if ($("#hos_link").val().length > 150) {
		alert("링크를 150자 이하로 입력하세요");
		$("#hos_link").focus();
		return false;
	}
	if ($("#hospital_file").val() == "") {
		alert("이미지 파일을 업로드하세요.");
		return false;
	}
}

// 주소 검사
function post_search() {
	alert("우편번호 검색 버튼을 클릭하세요.");
}
