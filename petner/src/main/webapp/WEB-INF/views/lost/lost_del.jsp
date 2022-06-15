<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" type="text/css" href="./css/admin.css" />
<link rel="stylesheet" type="text/css" href="./css/member.css" />
<script src="./js/jquery.js"></script>
<script>
 function check(){
	 if(frm.lost_pwd.value != frm.lost_pwd2.value) {
			alert("암호가 다르면 수정할 수 없습니다");
			frm.lost_pwd2.value = '';			
			frm.lost_pwd2.focus();
			return false;
		}
 }
</script>
</head>
<body>
 <div id="del_wrap">
  <h2 class="del_title">미아게시판 삭제</h2>
  <form method="post" action="lostDeleteFormOk" 
  					  onsubmit="return check()" name="frm" enctype="multipart/form-data">
  			<input type="hidden" name="lost_no" value="${lost.lost_no}"> 
			<input type="hidden" name="pageNum" value="${pageNum}"> 
			<input type="hidden" name="lost_pwd" value="${lost.lost_pwd}"> <!-- db에 저장된 비번 -->
    <table id="del_t">
     <tr>
      <th>회원아이디</th>
      <td>
      ${lost.mem_id}
      </td>
     </tr>
     
     
     
     <tr>
      <th>비밀번호</th>
      <td>
      <input type="password" name="lost_pwd2" id="lost_pwd2" size="14" 
      			class="input_box" required="required" />
      </td>
     </tr>
     
    </table>
    
    <div id="del_menu">
     <input type="submit" value="삭제" class="input_button" />
     <input type="reset" value="취소" class="input_button"
     	onClick="history.back(-1)" />
    </div>
  </form>
 </div>
</body>
</html>