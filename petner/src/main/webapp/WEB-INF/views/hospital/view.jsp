<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<script src="${path}/js/jquery.js"></script>
<!-- 게시판 목록 비동기 처리 -->
<script>
	$(function() {
		$('#list').load('hospital_list?page=${page} table');
	});
</script>

<body>
	<div>
		<h2 align=center>${hospital.hospital_name}</h2>
		<table border=1 align=center width=900 height=500>
			<tr colspan=3 align=center>
				<th rowspan=8 width=450>
				<c:if test="${empty hospital.hospital_file}">
       				&nbsp; </c:if>
        		<c:if test="${!empty hospital.hospital_file}">
					<img src="${path}/upload/hospital/${hospital.hospital_file}"
							width="100%" height="100%" />
				</c:if></th>
			</tr>
			<tr>
				<th>주소</th>
				<td>${hospital.hospital_addr}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${hospital.hospital_tel}</td>
			</tr>
			<tr>
				<th>운영시간</th>
				<td><pre>${hospital.hospital_time}</pre></td>
			</tr>
			<tr>
				<th>소개</th>
				<td><pre>${hospital.hospital_content}</pre></td>
			</tr>
			<tr>
				<th>링크</th>
				<td><a href="${hospital.hospital_link}" target="_blank"
					style="text-decoration: none">${hospital.hospital_link}</a></td>
			</tr>
			<tr>
				<th>비고</th>
				<td><c:if test="${hospital.hospital_24 == 'Y'}">
						<input type="button" value="24시간 운영"></c:if>
					<c:if test="${hospital.hospital_24 == 'N'}"></c:if>
					<c:if test="${hospital.hospital_holiday == 'Y'}">
						<input type="button" value="연중무휴"></c:if>
					<c:if test="${hospital.hospital_holiday == 'N'}"></c:if>
					<c:if test="${hospital.hospital_parking == 'Y'}">
						<input type="button" value="주차 가능"></c:if>
					<c:if test="${hospital.hospital_parking == 'N'}">
						<input type="button" value="주차 불가"></c:if>
				</td>
			</tr>
			<tr>
				<th>위치</th>
				<td height=250><div id="map" style="width: 100%; height: 100%;"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9d66e8b52537c3400ccfd83597873ca1&libraries=services,clusterer,drawing"></script>
					<script>
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						mapOption = {
							center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
							level : 2 // 지도의 확대 레벨
						};

						var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

						var geocoder = new kakao.maps.services.Geocoder();

						// 주소로 좌표를 검색
						geocoder.addressSearch('${hospital.hospital_addr}',
								function(result, status) { // 정상적으로 검색이 완료됐으면
									if (status === kakao.maps.services.Status.OK) {

									// 마커가 표시될 위치
									var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

									// 마커 생성(결과값으로 받은 위치를 마커로 표시)
									var marker = new kakao.maps.Marker({map : map, position : coords});

									// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능
									var content = '<div class="customoverlay">'
											+ '  <a href="https://map.kakao.com/link/search/${hospital.hospital_addr}" target="_blank">'
											+ '  <span class="title">${hospital.hospital_name}</span>'
											+ '  </a>' + '</div>';

									// 커스텀 오버레이가 표시될 위치
									var position = new kakao.maps.LatLng(result[0].y, result[0].x);

									// 커스텀 오버레이를 생성
									var customOverlay = new kakao.maps.CustomOverlay(
														{
															map : map,
															position : position,
															content : content,
															yAnchor : 0
														});

									map.setCenter(coords);
								}
							});
					</script></td>
			</tr>
		</table>
		<br>

		<div align="center">
			<c:if test="${sessionScope.mem_id == 'admin'}">
				<input type="button" value="수정"
					onClick="location.href='hospital_view?hospital_no=${hospital.hospital_no}&page=${page}&state=update'">
				<input type="button" value="삭제"
					onClick="location.href='hospital_view?hospital_no=${hospital.hospital_no}&page=${page}&state=delete'">
			</c:if>
				<input type="button" value="목록"
					onClick="location.href='hospital_list?page=${page}'">
		</div>
		<br>
		<br>
		<div id="list"></div>
		<br>
	</div>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>