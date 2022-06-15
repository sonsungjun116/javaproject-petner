<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="header.jsp"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="../include/header.jsp" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 원래는 action으로 지정된 컨트롤러에서 암호비교를 했음 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	/* function chk() {
		if (frm.passwd.value != frm.passwd2.value) {
			alert("암호가 다르면 수정할 수 없습니다");
			frm.passwd2.value = '';
			frm.passwd2.focus();
			return false;
		}
	} */
	
	function openDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {				
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 우편번호와 주소 정보를 해당 필드에 넣고, 커서를 상세주소 필드로 이동한다.
				document.getElementById('seller_post').value = data.zonecode;
				document.getElementById('seller_addr1').value = data.address;				
			}
		}).open();
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<h2 class="text-primary">사업자 신청양식</h2>
		<!-- <form action="Transfer" method="post" name="frm" onsubmit="return chk()"> -->
		<form action="Transfer" method="post" name="frm">
			<%-- <input type="hidden" name="num" value="${board.num}"> 
			<input type="hidden" name="pageNum" value="${pageNum}"> 
			<input type="hidden" name="passwd" value="${board.passwd}"> --%>
			<input type="hidden" name="mem_id" value="${mem_id}">
			<!-- db에 저장된 비번 -->
			<table class="table table-striped" border="1" width=500 align=center
				height=350>
				<tr>
					<td>내 아이디</td>
					<td>${mem_id}</td>
				</tr>
				<tr>
					<td>회사명</td>
					<td><input type="text" name="seller_name" required="required"
						></td>
				</tr>
				<tr>
					<td>회사우편번호</td>
					<td><input type="text" name="seller_post" id="seller_post" required="required">
					<input type="button" value="우편번호검색" class="input_button"
      					onclick="openDaumPostcode()" />
						</td>
				</tr>
				<tr>
					<td>회사주소</td>
					<td><input type="text" name="seller_addr1" id="seller_addr1" required="required"
						></td>
				</tr>
				<tr>
					<td>회사 상세주소</td>
					<td><input type="text" name="seller_addr2" required="required"></td>
				</tr>
				<tr>
					<td>회사 전화번호</td>
					<td><%@ include file="tel_number.jsp"%>
						<select name="seller_tel1">
							<c:forEach var="t" items="${tel}" begin="0" end="18">
								<!-- request.setAttribute로 tel값을 공유하고 와야한다 -->
								<option value="${t}">${t}</option>
							</c:forEach>
					</select>-<input name="seller_tel2" id="seller_tel2" size="4" maxlength="4"
						class="input_box" />-<input name="seller_tel3" id="seller_tel3"
						size="4" maxlength="4" class="input_box" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="신청하기"></td>
				</tr>
			</table>
		</form>
	</div>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>