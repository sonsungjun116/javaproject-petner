<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<%-- <c:set var="path" value="${pageContext.request.contextPath}" /> --%>
<link rel="stylesheet" href="${path}/css/body.css"> 
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> 
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
<!-- jquery ui가 최신버전에서는 쓸수 없다. -->
<script src="${path}/js/member.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
//우편번호, 주소 Daum API
function openDaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {				
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			// 우편번호와 주소 정보를 해당 필드에 넣고, 커서를 상세주소 필드로 이동한다.
			document.getElementById('mem_post').value = data.zonecode;
			document.getElementById('mem_addr1').value = data.address;				
		}
	}).open();
	
}

$(function() {
	   $( "#datepicker" ).datepicker();
});

</script>
</head>
<body>
	
	<c:set var="now" value="<%=new Date()%>"/>
	<form method="post" action="sign1" enctype="multipart/form-data" id="tab" name="tab" class="tab">
		<h5 class="empty1">아이디</h5>
		<input type="text" name="mem_id" id="mem_id"/>
		<input type="button" value="중복확인" onClick="idCheck()"/>
		<div id="tex"></div>
		
		<h5 class="empty">이름</h5>
		<input type="text" name="mem_name" id="mem_name" class="mem"/>
		
		<h5 class="empty">비밀번호</h5>
		<input type="password" name="mem_pwd" id="mem_pwd" class="mem"/>
		
		<h5 class="empty">비밀번호 확인</h5>
		<input type="password" name="mem_pwd2" id="mem_pwd2" class="mem"/>
	 	
		<h5 class="empty">전화번호</h5>
		<select name="mem_phone1">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="012">012</option>
			<option value="013">013</option>
		</select>-
		<input type="text" name="mem_phone2" id="mem_phone2"/>-<input type="text" name="mem_phone3" id="mem_phone3"/>
		
		<h5 class="empty">성별</h5>
		<input type="radio" name="mem_gender" id="mem_gender1" value="male"/>남자
		<input type="radio" name="mem_gender" id="mem_gender2" value="female"/>여자

		<h5 class="empty">회원유형</h5>
		<input type="radio" name="mem_type" id="mem_type1" value="사업자"/>사업자
		<input type="radio" name="mem_type" id="mem_type2" value="일반회원"/>일반회원
		
		<h5 class="empty">우편번호</h5>
		<input type="text" name="mem_post" id="mem_post" readonly>
		<input type="button" value="우편번호검색" onClick="openDaumPostcode()"/>
		
		<h5 class="empty">주소</h5>
		<input type="text" name="mem_addr1" id="mem_addr1" class="mem" readonly/>
		
		<h5 class="empty">상세주소</h5>
		<input type="text" name="mem_addr2" id="mem_addr2" class="mem"/>
		
		 <h5 class="empty">생일</h5>
		<input type="text" name="mem_birth" id="datepicker"/>
 		<%-- <input type="date" name="mem_birth"  value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/>" max="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/>">  --%>
		
		<h5 class="empty">이메일</h5>
		<input type="text" name="mem_email" id="mem_email"/>@<input type="text" name="mem_domain" id="mem_domain"/>
		<select id="mail_list" onchange="domain_list()">
			<option value="">직접입력</option>
			<option value="daum.net">daum.net</option>
			<option value="nate.com">nate.com</option>
			<option value="naver.com">naver.com</option>
			<option value="hotmail.com">hotmail.com</option>
			<option value="gmail.com">gmail.com</option>
		</select>
		
		<h5 class="empty">프로필</h5>
		<input type="file" id="mem_profile" name="mem_profile1" />
		
		<div class="position1">
		<input type="submit" value="회원가입" id="sign_up"/>
		</div>
	</form>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>