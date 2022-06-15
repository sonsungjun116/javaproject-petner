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
<!-- <script>
  	function chk() {
		if(form.admin_pwd.value != form.hospital_pwd.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.hospital_pwd.focus();
			return false;
			
		}
	} 
</script> -->
</head>

<body>
	<form action="hospital_update" method="post" name="form" onSubmit="return check()"
		enctype="multipart/form-data">
		<input type="hidden" id="hospital_no" name="hospital_no" value="${hospital.hospital_no}">
		<input type="hidden" id="page" name="page" value="${page}">
		<input type="hidden" id="admin_pwd" name="admin_pwd" value="${admin.admin_pwd}">
		<table border=1 align=center>
			<caption><h2>병원 수정</h2></caption>
			<tr>
				<th>병원명</th>
				<td><input type="text" size=40 id="hospital_name" name="hospital_name" value="${hospital.hospital_name}"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" size=40 id="hospital_pwd" name="hospital_pwd"
					autofocus="autofocus">
			</tr>
			<tr>
				<th>지역</th>
				<td><select id="hospital_loc" name="hospital_loc">
						<option value="${hospital.hospital_loc}">${hospital.hospital_loc}</option>
						<c:forEach var="loc" items="${loc}">
							<option value="${loc}">${loc}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type=text size=24 id="hospital_post" name="hospital_post" value="${hospital.hospital_post}">
					<input type=button value="우편번호 검색"
					onClick="openDaumPostcode()" class="click"></td>
			<tr>
				<th>주소</th>
				<td><input type=text size=40 id="hospital_addr"
					name="hospital_addr" value="${hospital.hospital_addr}"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type=text size=40 id="hospital_tel"
					name="hospital_tel" maxlength=14 value="${hospital.hospital_tel}"></td>
			</tr>
			<tr>
				<th>운영시간</th>
				<td><textarea rows="5" cols="39" id="hospital_time" name="hospital_time"
				 	value="${hospital.hospital_time}">${hospital.hospital_time}</textarea></td>
			</tr>
			<tr>
				<th>24시</th>
				<td>
				<c:if test="${hospital.hospital_24 == 'Y'}">
					<input type="radio" id="hospital_24Y" name="hospital_24" value="Y" checked>여
					<input type="radio" id="hospital_24N" name="hospital_24" value="N">부
				</c:if>
				<c:if test="${hospital.hospital_24 == 'N'}">
					<input type="radio" id="hospital_24Y" name="hospital_24" value="Y">여
					<input type="radio" id="hospital_24N" name="hospital_24" value="N" checked>부
				</c:if>
				</td>
			</tr>
			<tr>
				<th>연중무휴</th>
				<td>
				<c:if test="${hospital.hospital_holiday == 'Y'}">
					<input type="radio" id="hospital_holidayY" name="hospital_holiday" value="Y" checked>여
					<input type="radio" id="hospital_holidayN" name="hospital_holiday" value="N">부
				</c:if>
				<c:if test="${hospital.hospital_holiday == 'N'}">
					<input type="radio" id="hospital_holidayY" name="hospital_holiday" value="Y">여
					<input type="radio" id="hospital_holidayN" name="hospital_holiday" value="N" checked>부
				</c:if>
				</td>
			</tr>
			<tr>
				<th>주차가능</th>
				<td>
				<c:if test="${hospital.hospital_parking == 'Y'}">
					<input type="radio" id="hospital_parkingY" name="hospital_parking" value="Y" checked>여
					<input type="radio" id="hospital_parkingN" name="hospital_parking" value="N">부
				</c:if>
				<c:if test="${hospital.hospital_parking == 'N'}">
					<input type="radio" id="hospital_parkingY" name="hospital_parking" value="Y">여
					<input type="radio" id="hospital_parkingN" name="hospital_parking" value="N" checked>부
				</c:if>
				</td>
			</tr>
			<tr>
				<th>소개</th>
				<td><textarea rows="5" cols="39" id="hospital_content"
				name="hospital_content">${hospital.hospital_content}</textarea></td>
			</tr>
			<tr>
				<th>링크</th>
				<td><input type="text" size=40 id="hospital_link" name="hospital_link" value="${hospital.hospital_link}"></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><input type="file" id="hospital_file1" name="hospital_file1"></td>
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