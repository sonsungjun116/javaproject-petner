<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<%
	String[] loc = {"서울", "부산", "인천", "대구", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남",
			"제주", "세종"};
	request.setAttribute("loc", loc);
%>

<!-- 외부 자바스크립트 파일(유효성 검사) 불러오기 -->
<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="${path}/js/hospital.js"></script>

<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- 다음 주소 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function openDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				document.getElementById('hospital_post').value = data.zonecode;
				document.getElementById('hospital_addr').value = data.address;
			}
		}).open();
	}
</script>

<body>
	<form action="hospital_insert" method="post" onSubmit="return check()"
		enctype="multipart/form-data">
		<input type="hidden" name="admin_id" value="${admin.admin_id}">		
		<h2 align="center">병원 등록</h2>
		<table border=1 align=center>
		<tr>
			<th>병원명</th>
			<td><input type="text" size=40 id="hospital_name" name="hospital_name"></td>
		</tr>
		<tr>
			<th>지역</th>
			<td><select id="hospital_loc" name="hospital_loc">
				<option value="">지역선택</option>
					<c:forEach var="loc" items="${loc}">
						<option value="${loc}">${loc}</option>
					</c:forEach>
				</select></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type=text size=24 id="hospital_post" name="hospital_post">
				<input type=button value="우편번호 검색"
					onClick="openDaumPostcode()" class="click"></td>
		<tr>
			<th>주소</th>
			<td><input type=text size=40 id="hospital_addr"
					name="hospital_addr"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type=text size=40 id="hospital_tel"
					name="hospital_tel" maxlength=14></td>
		</tr>
		<tr>
			<th>운영시간</th>
			<td><textarea rows="5" cols="39" id="hospital_time" name="hospital_time"
				placeholder="평일 10:00 - 20:00 &#13;&#10;토요일 10:00 - 19:00&#13;&#10;점심시간 13:00 - 14:00&#13;&#10;일요일 휴무 &#13;&#10;위의 형식으로 입력해주세요."></textarea></td>
		</tr>
		<tr>
			<th>24시</th>
			<td><input type="radio" id="hospital_24Y" name="hospital_24" value="Y">24시간 운영
				<input type="radio" id="hospital_24N" name="hospital_24" value="N">24시간 운영 아님</td>
		</tr>
		<tr>
			<th>연중무휴</th>
			<td><input type="radio" id="hospital_holidayY" name="hospital_holiday" value="Y">연중무휴
				<input type="radio" id="hospital_holidayN" name="hospital_holiday" value="N">연중무휴 아님</td>
		</tr>
		<tr>
			<th>주차가능</th>
			<td><input type="radio" id="hospital_parkingY" name="hospital_parking" value="Y">주차가능
				<input type="radio" id="hospital_parkingN" name="hospital_parking" value="N">주차불가</td>
		</tr>
		<tr>
			<th>소개</th>
			<td><textarea rows="5" cols="39" id="hospital_content" name="hospital_content"></textarea></td>
		</tr>
		<tr>
			<th>링크</th>
			<td><input type="text" size=40 id="hospital_link" name="hospital_link"
				placeholder="https://www.petner.com의 형식으로 입력해주세요."></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><input type="file" id="hospital_file" name="hospital_file1"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="확인">
			<input type="reset" value="취소"></td>
		</tr>
	</table>
	</form>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>