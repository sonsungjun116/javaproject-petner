<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="petner.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var="path" value="${pageContext.request.contextPath }" />


<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>게시판 목록</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bbs.css" type="text/css">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    
    <script type="text/javascript">
    
  
	$(function() {
		$('.edit1').click(function() {
			alert('눌려짐');		// class값이 edit1인 태그를 불러와라 -- 수정버튼
			var lostreply_no = $(this).attr('id');  // rno -- this는 이벤트를 발생 시킨 태그 즉 수정버튼 -의 아이디를 가져와라
			var txt = $('#td_'+lostreply_no).text(); // replytext
			alert("txt:"+txt);
			$('#td_'+lostreply_no).html("<textarea rows='4' cols='30' id='tt_"+lostreply_no+"'>"+txt //textarea를 구분하기 위해서 tt_를 사용
				+"</textarea>");// textarea로 바꾸어주는 코드
			$('#btn_'+lostreply_no).html(		// 확인 취소로 버튼을 바꾸어 주는 코드
			   "<input type='button' value='확인' onclick='up("+lostreply_no+")'> "
			  +"<input type='button' value='취소' onclick='lst()'>");
		});		
	});

	function up(lostreply_no) {	// 내용 수정
		alert('눌려짐2');
		var lostreply_content = $('#tt_'+lostreply_no).val();		// 수정된 글내용
		var formData = "lostreply_no="+lostreply_no+'&lostreply_content='+lostreply_content
			+"&lost_no=${lost.lost_no}"; // bno자기 부모글의 번호값
			alert("lostreply_no:"+lostreply_no);
			alert("lostreply_content:"+lostreply_content); //이상한 형태로 넘어오는 텍스트 형식 수정
			alert("lost.lost_no:"+${lost.lost_no});
			
		$.post('${path}/replyUpdate',formData, function(data) {			
			$('#lostreply').html(data);
		});
	}
	
	function lst() {	// 취소
		$('#lostreply').load('replyList?lost_no=${lost.lost_no}'); // 다시 댓글목록을 요청해서 출력
	}
	function del(lostreply_no,lost_no) {		// 삭제 버튼
		var formData="lostreply_no="+lostreply_no+"&lost_no="+lost_no;	
		$.post("${path}/replyDelete",formData, function(data) {
			$('#lostreply').html(data);
			
		});
	}
	function lst2(lostreply_no) {
		$('#son_'+lostreply_no).height('0');
		$('#lostreply').load('replyList?lost_no=${lost.lost_no}');
	}
	//댓글달기
	function rreply(lostreply_no) {
		
	$('#son_'+lostreply_no).height('50');
	$('#son2_'+lostreply_no).attr('style', "display:'';");
	$('#son4_'+lostreply_no).attr('style', "display:'';");

	/* $('#son2_'+lostreply_no).html("<textarea rows='4' cols='30' id='tt1_"+lostreply_no+"'>" //textarea를 구분하기 위해서 tt_를 사용
			"</textarea>"); */
	
	
	$('#son1_'+lostreply_no).html(		// 확인 취소로 버튼을 바꾸어 주는 코드
			   /* "<input type='button' value='확인1' id='son4' onclick='up("+lostreply_no+")'> "+ */
			  "<input type='button' value='댓글취소' onclick='lst2("+lostreply_no+")'>");				
	}		
	
	$(function() {
	$('#insert1').click(function() {
			alert("작동합니다");
		if (!frm1.lostreply_content.value) {
			alert('댓글 입력후에 클릭하시오');
			frm1.lostreply_content.focus();
			return false;
		}
		var frmData = $('form').serialize(); // name값이 변수 value값이 value로 뽑아옴
		// var frmData = 'replyer='+frm.replyer.value+'&bno='+
		//				  frm.bno.value+'&replytext='+frm.replytext.value;		
		// $.post("요청이름","값 전달","콜백함수")
		$.post('${path}/rreply', frmData, function(data) {
//			alert(data);
			$('#lostreply').html(data); // id가 slist로 되어있는 div영역에 콜백함수로 받은 값을 뿌려준다
			frm1.lostreply_content.value = '';
		});
	});	

});
	function lll(mem_id,lost_no) {	// 취소
		alert("mem_id:"+mem_id);
		alert("lost_no:"+lost_no);
	}
		
	
	function replyon(lostreply_no,lostreply_lev,lostreply_seq,lostreply_ref,lostreply_content,mem_id,lost_no) {
				
		var lostreply_content = $('#son3_'+lostreply_content).val();
		var formData="lostreply_no="+lostreply_no+"&lostreply_lev="+lostreply_lev+"&lostreply_seq="+lostreply_seq+"&lostreply_ref="+lostreply_ref+"&lostreply_content="+lostreply_content+"&mem_id="+mem_id+"&lost_no="+lost_no;	
		$.post("${path}/rreply",formData, function(data) {
			$('#lostreply').html(data);
			
		});
	}
	
</script>
       
</head>

<body>
	<!-- 게시판 리스트 -->
	<div id="lostreply">
	<%-- <form name="frm1" id="frm1">
	<input type="hidden" name="mem_id" value="${lost.mem_id}"> <!-- 로그인해서 댓글을 다는 아이디의 아이디값 -->
			<input type="hidden" name="lost_no" value="${lost.lost_no}"> --%>
	
		<h2 class="bbslist_title">댓글 목록</h2>
		
		
		 <table id="bbslist_t" width="1000" > 
		
			<tr align="center" valign="middle" bordercolor="#333333">
				<td style="font-family: Tahoma; font-size: 11pt;" width="10%"
					height="26">
					<div align="center">작성자</div>
				</td>
				<td style="font-family: Tahoma; font-size: 11pt;" width="40%">
					<div align="center">댓글내용</div>
				</td>
				<td style="font-family: Tahoma; font-size: 11pt;" width="10%">
					<div align="center">날짜</div>
				</td>	
				<td width="40%">
				</td>			
			</tr>					
			<!-- 반복문 시작 -->
			<c:forEach var="b" items="${replylist}">
			<form name="form_${b.lostreply_no}" id="form_${b.lostreply_no}">
			<tr  valign="middle" bordercolor="#333333"
				onmouseover="this.style.backgroundColor='F8F8F8'"
				onmouseout="this.style.backgroundColor=''">
				<%-- <td height="23" style="font-family: Tahoma; font-size: 10pt;">					
 					<!-- 번호 출력 부분 -->	
 					<c:out value="${num}"/>			
					<c:set var="num" value="${num-1}"/>	 
				</td> --%>
				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="center">${b.mem_id}</div>
				</td>
								
				 <%-- <td style="font-family: Tahoma; font-size: 10pt;" id="td_${b.lostreply_no}">
					<div align="left">						
						
					<c:if test="${b.lostreply_lev != 0}"> <!-- 댓글이라면 -->
						<c:forEach var="k" begin="1" end="${b.lostreply_lev}">
							&nbsp;&nbsp;			
						</c:forEach>
						<img src="./images/AnswerLine.gif">	
					</c:if>										
					<!-- 제목 출력 부분 -->	<!-- 상태값 state를 부여해서 상황에 따라 다르게 사용한다 -> 상세페이지,수정폼,삭제폼,댓글폼 -->
					<a href="board_cont.do?board_num=${b.board_num}&page=${page}&state=cont"> 
							${b.lostreply_content}
					</a>
					${b.lostreply_content}					
					</div>
				</td>  --%>
				
				<td id="td_${b.lostreply_no}">
					<c:if test="${b.lostreply_lev > 0}">
						&nbsp;&nbsp;&nbsp; Re.
					</c:if>
						${b.lostreply_content}
				</td>
				<!-- <td id="live">
				
				</td> -->
				
				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="center">
					
					<fmt:formatDate value="${b.lostreply_date}"
						 pattern="yyyy-MM-dd HH:mm:ss"/>										
					</div>
				</td>
				<td id="btn_${b.lostreply_no}" >
					
							<input type="button" value="댓글달기" onclick="rreply(${b.lostreply_no})">
							<%-- <input type="button" value="수정" class="edit1" id="${b.lostreply_no}"> <!-- rb.rno  댓글번호, 각자의 댓글을 구별하기 위해서 사용-->							
							<input type="button" value="삭제"	 onclick="del(${b.lostreply_no},${b.lost_no})"> <!-- rno자식의 글번호(댓글) bno부모의 글번호 --> --%>
					
					<c:if test="${mem_id == b.mem_id }">
					<%-- <input type="button" value="수정" class="edit1" id="${b.lostreply_no}"> --%> <!-- rb.rno  댓글번호, 각자의 댓글을 구별하기 위해서 사용-->							
					<input type="button" value="삭제"	 onclick="del(${b.lostreply_no},${b.lost_no})"> <!-- rno자식의 글번호(댓글) bno부모의 글번호 -->
					
					</c:if>
					
					</td>			
			</tr>

			
			<tr height="0" id="son_${b.lostreply_no}">
							
			<td></td>
			<td><div style="display:none" id="son2_${b.lostreply_no}"><textarea id="son3_${b.lostreply_no}" rows="4" cols="30" name="lostreply_content"></textarea></div></td>
			<td><div style="display:none" id="son4_${b.lostreply_no}"><input type="button" value="댓글입력" id="son4_${b.lostreply_no}"
				onclick="replyon('${b.lostreply_no}','${b.lostreply_lev}','${b.lostreply_seq}','${b.lostreply_ref}','${b.lostreply_no}','${mem_id}','${b.lost_no}')"></div></td>
			<td id="son1_${b.lostreply_no}"></td>			
			</tr>
				
			</c:forEach>
			<!-- 반복문 끝 -->			
		</table>
				</form>
		<%-- <div id="bbslist_w">
			<input type="button" value="댓글쓰기" class="input_button"
				onclick="location='board_write.do?page=${page}'">
				onclick="location='replyLostInsert'">
		</div> --%>
		<!-- </form> -->
	</div>
</body>
</html>
<!-- rno 댓글의 프라이머리 번호
bno 부모의 번호 -->
