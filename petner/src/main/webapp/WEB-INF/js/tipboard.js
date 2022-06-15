$(function(){
	$("#tab").submit(function(){
		
		if($.trim($("#tip_title").val()) == ""){
			alert("제목을 입력하세요.");
			$("#tip_title").focus();
			return false;
		}
		if($.trim($("#tip_content").val()) == ""){
			alert("내용을 입력하세요.");
			$("#tip_content").focus();
			return false;
		}
		
		if($.trim($("#tip_content").val()).length >= 500){
			alert("500자 이내로 써주세요.");
			$("#tip_content").focus();
			return false;
		}
		
		if ($("#tip_pet1").is(":checked") == false
				&& $("#tip_pet2").is(":checked") == false) {
			alert("동물을 선택하세요.");
			return false;
		}
		
	});
	
});

$(function(){
	$("#tab1").submit(function(){
		
		if($.trim($("#tip_title").val()) == ""){
			alert("제목을 입력하세요.");
			$("#tip_title").focus();
			return false;
		}
		if($.trim($("#tip_content").val()) == ""){
			alert("내용을 입력하세요.");
			$("#tip_content").focus();
			return false;
		}
		
		if($.trim($("#tip_content").val()).length >= 500){
			alert("500자 이내로 써주세요.");
			$("#tip_content").focus();
			return false;
		}
		
		if ($("#tip_pet1").is(":checked") == false
				&& $("#tip_pet2").is(":checked") == false) {
			alert("동물을 선택하세요.");
			return false;
		}
		
		if($.trim($("#mem_pwd").val()) != $.trim($("#mem_pwd2").val())){
			alert("비밀번호을 다시 확인하세요");
			$("#mem_pwd2").val("").focus();
			return false;
		}
		
		if($.trim($("#mem_pwd2").val()) == ""){
			alert("비밀번호를 입력하세요");
			$("#mem_pwd2").focus();
			return false;
		}
		
	});
	
});


$(function(){
	$("#tab3").submit(function(){
		if($.trim($("#mem_pwd").val()) != $.trim($("#mem_pwd2").val())){
			alert("비밀번호을 다시 확인하세요");
			$("#mem_pwd2").val("").focus();
			return false;
		}
		
		if($.trim($("#mem_pwd2").val()) == ""){
			alert("비밀번호를 입력하세요");
			$("#mem_pwd2").focus();
			return false;
		}
	});
});

$(function(){
	
	$('#slist').load('slist?tip_no='+$("#tip_no").val());
	
	$("#repInsert").click(function(){
		if($.trim($("#tipreply_content").val()) == ""){
			alert("댓글 입력후에 클릭하세요.");
			$("#tipreply_content").focus();
			return false;
		}
		
		var frmData = $("#frm").serialize();
		
		$.post("insertTipreply", frmData, function(data){
			/*alert(data);*/
			$("#slist").html(data);
			$("#tipreply_content").val("");
		});
	});
	
});