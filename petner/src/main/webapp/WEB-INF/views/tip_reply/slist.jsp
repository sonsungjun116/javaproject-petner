<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> 
<title>댓글 목록</title>
<script>
$(function() {
	$('.edit1').click(function() {	// 수정 버튼 클릭
		var id  = $(this).attr('id');  // tipreply_no  .edit의 id값을 구해오라는 의미이다.
		var txt = $('#td_'+id).text(); // tipreply_content  $('#td_'+id)의 text값을 가져온다. 
		$('#td_'+id).html("<textarea rows='3' cols='30' id='tt_"+id+"'>"+txt
			+"</textarea>");
		$('#btn_'+id).html(
		   "<input type='button' value='확인' onclick='up("+id+")'> "
		  +"<input type='button' value='취소' onclick='lst()'>");
	});
});

//$(function(){
//	$(".add1").click(function(){
	
function a(ref,lev, seq, id){	
		alert(lev);
		alert(seq);
		alert(id);
		
//		var id = $(this).attr('id');
		$("#addtd_"+id).html("<textarea rows='3' cols='30' id='tt_"+id+"'></textarea>")
		$("#addbtn_"+id).html(
				"<input type='button' value='확인' onclick='add("+ref+","+lev+","+seq+","+id+")'>"
				  +"<input type='button' value='취소' onclick='lst()'>");		
}		
		
//	});
//});


function add(ref,lev, seq, id) {	// 댓글 대댓글 달기
	var replytext = $("#tt_"+id).val();
	var mem_id = $("#mem_id").val();
	var tip_no = $("#tip_no").val();
	var tipreply_seq = $("#tipreply_seq").val();
	var tipreply_lev = $("#tipreply_lev").val();
	var formData  = "tipreply_no="+id+'&tipreply_content='+replytext+'&mem_id='+mem_id+
					"&tip_no="+tip_no+"&tipreply_seq="+seq+"&tipreply_lev="+lev+"&tipreply_ref="+ref;
	$.post('addreply', formData, function(data){
		$("#slist").html(data);
	});
}

function up(id) {	// 내용 수정
	var replytext = $('#tt_'+id).val();
	var tip_no = $("#tip_no").val();
	var formData = "tipreply_no="+id+'&tipreply_content='+replytext+"&tip_no="+tip_no;
	$.post('updateTipreply',formData, function(data) {
		$('#slist').html(data);
	});
}
function lst() {
	var tip_no = $("#tip_no").val();
	$('#slist').load('slist?tip_no='+tip_no);
}
function del(tipreply_no, tip_no) {	// 삭제 버튼 클릭
	var formData="tipreply_no="+tipreply_no+"&tip_no="+tip_no;
	$.post("deleteTipreply",formData, function(data) {
		$('#slist').html(data);
	});
}


</script>
</head>
<body>


<h4>댓글</h4>
<table>

<tr>
	<td>작성자</td>
	<td>내용</td>
	<td>수정일</td>
	<td></td>
</tr>

<c:forEach var="list" items="${slist}">
<input type="hidden" name="tip_no" id="tip_no" value="${list.tip_no}">
<input type="hidden" name="mem_id" id="mem_id" value="${list.mem_id}">
<input type="hidden" name="tipreply_seq" id="tipreply_seq" value="${list.tipreply_seq}">
<input type="hidden" name="tipreply_lev" id="tipreply_lev" value="${list.tipreply_lev}">
<tr>
	
	<td>
		<div>
		<c:if test="${list.tipreply_lev != 0}"> 
			<c:forEach var="k" begin="1" end="${list.tipreply_lev}">
				&nbsp;&nbsp;			
			</c:forEach>
		</c:if>	
		${list.mem_id}
		</div>
	</td>
	<td id="td_${list.tipreply_no}">${list.tipreply_content}</td>
	<td>${list.tipreply_date}</td>
	<td id="btn_${list.tipreply_no}">
	<c:if test="${sessionScope.mem_id == list.mem_id}">
		<input type="button" value="수정" class="edit1" id="${list.tipreply_no}">
		<input type="button" value="삭제" onClick="del(${list.tipreply_no}, ${list.tip_no})">
	</c:if>
	</td>
	<td id="addtd_${list.tipreply_no}"></td>
	<td id="addbtn_${list.tipreply_no}"><input type="button" value="답변달기" class="add1" id="${list.tipreply_no}" 
		onClick="a(${list.tipreply_ref},${list.tipreply_lev},${list.tipreply_seq},${list.tipreply_no})"></td>
</tr>
</c:forEach>
</table>

</body>
</html>