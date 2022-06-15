<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>게시판 입력폼</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bbs.css" />
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="<%=request.getContextPath() %>/js/board.js"></script>
</head>

<body>
 <div id="bbswrite_wrap">
  <h2 class="bbswrite_title">댓글 입력폼</h2>
  <form method="post" action="<%=request.getContextPath() %>/replyLostInsertOk" onSubmit="return board_check()">
   <input type="hidden" name="mem_id" value="${mem_id}"> 
   <input type="hidden" name="lost_no" value="${num}"> 
   <table id="bbswrite_t">
    <tr>
     <th>글쓴이</th>
     <td>
     ${mem_id }
     </td>
    </tr>
       
    <tr>
     <th>댓글내용</th>
     <td>
      <textarea name="lostreply_content"  id="lostreply_content" rows="3" cols="50"
      class="input_box" required="required"></textarea>
     </td>
    </tr> 
    
   </table>
   
   <div id="bbswrite_menu">
    <input type="submit" value="등록" class="input_button" />
    <input type="reset" value="취소" class="input_button"
    onclick="$('#board_name').focus();" />
   </div>
  </form>
 </div>
</body>
</html>