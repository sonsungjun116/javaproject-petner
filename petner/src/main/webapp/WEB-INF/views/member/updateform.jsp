<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> 
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
<!-- jquery ui가 최신버전에서는 쓸수 없다. -->
<script src="${path}/js/member.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet" href="${path}/css/body.css">
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

</script>

</head>
<body>

<form method="post" action="mem_update" enctype="multipart/form-data" id="tab2" name="tab" class="tab">
		<input type="hidden" name="mem_id" id="mem_id" value="${member.mem_id}">
		<input type="hidden" name="mem_pwd" id="mem_pwd" value="${member.mem_pwd}">

		<h5 class="empty1">아이디</h5>
		<strong>${member.mem_id}</strong>
				
		<h5 class="empty">이름</h5>
		<strong>${member.mem_name}</strong>
		
		<h5 class="empty">비밀번호 확인</h5>
		<input type="password" name="mem_pwd2" id="mem_pwd2" class="mem">
			 	
		<h5 class="empty">전화번호</h5>
		<select name="mem_phone1">
			<option value="010" <c:if test="${member.mem_phone1 == '010'}">${'selected'}</c:if>>010</option>
			<option value="011" <c:if test="${member.mem_phone1 == '011'}">${'selected'}</c:if>>011</option>
			<option value="012" <c:if test="${member.mem_phone1 == '012'}">${'selected'}</c:if>>012</option>
			<option value="013" <c:if test="${member.mem_phone1 == '013'}">${'selected'}</c:if>>013</option>
		</select>-
		<input type="text" name="mem_phone2" id="mem_phone2" value="${member.mem_phone2}"/>-
		<input type="text" name="mem_phone3" id="mem_phone3" value="${member.mem_phone3}"/>
		
		<h5 class="empty">우편번호</h5>
		<input type="text" name="mem_post" id="mem_post" readonly value="${member.mem_post}">
		<input type="button" value="우편번호검색" onClick="openDaumPostcode()"/>
		
		<h5 class="empty">주소</h5>
		<input type="text" name="mem_addr1" id="mem_addr1" class="mem" readonly value="${member.mem_addr1}">
		
		<h5 class="empty">상세주소</h5>
		<input type="text" name="mem_addr2" id="mem_addr2" class="mem" value="${member.mem_addr2}">
		
		<h5 class="empty">이메일</h5>
		<input type="text" name="mem_email" id="mem_email" value="${member.mem_email}"/>@<input type="text" name="mem_domain" id="mem_domain" value="${member.mem_domain}"/>
		<select id="mail_list" onchange="domain_list()">
			<option value="">직접입력</option>
			<option value="daum.net" <c:if test="${member.mem_domain == 'daum.net'}">${'selected'}</c:if>>daum.net</option>
			<option value="nate.com" <c:if test="${member.mem_domain == 'nate.com'}">${'selected'}</c:if>>nate.com</option>
			<option value="naver.com" <c:if test="${member.mem_domain == 'naver.com'}">${'selected'}</c:if>>naver.com</option>
			<option value="hotmail.com" <c:if test="${member.mem_domain == 'hotmail.com'}">${'selected'}</c:if>>hotmail.com</option>
			<option value="gmail.com" <c:if test="${member.mem_domain == 'gmail.com'}">${'selected'}</c:if>>gmail.com</option>
		</select>
		
		<h5 class="empty">프로필</h5>
		<input type="file" id="mem_profile" name="mem_profile1"/>
		
		<div class="position1">
		<input type="submit" value="회원수정" id="sign_up"/>
		</div>
	</form>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>