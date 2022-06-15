<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="header.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> <!-- 원래는 action으로 지정된 컨트롤러에서 암호비교를 했음 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function chk() {
		if(frm.lost_pwd.value != frm.lost_pwd2.value) {
			alert("암호가 다르면 수정할 수 없습니다");
			frm.passwd2.value = '';			
			frm.passwd2.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<h2 class="text-primary">게시글 글수정</h2>
		<form action="lostUpdateFormOk" method="post" name="frm"
			  onsubmit="return chk()" enctype="multipart/form-data">			  
			   <input type="hidden" name="lat" id="lat" value="${lat}">  
			 <input type="hidden" name="lng" id="lng" value="${lng}">
			<input type="hidden" name="lost_no" value="${lost.lost_no}"> 
			<input type="hidden" name="pageNum" value="${pageNum}"> 
			<input type="hidden" name="lost_pwd" value="${lost.lost_pwd}"> <!-- db에 저장된 비번 -->
			<table class="table table-striped" border="1">
				<tr>
					<th>작성자</th>
					<td>${lost.mem_id}<%-- ${lat}${lng} --%></td>
				</tr>
				<tr>
					<th>위치 수정하기</th>
					<td>
									 <c:if test="${lat==null}">
				 <div id="map" style="width:100%;height:350px;"></div> 
				<div id="clickLatlng"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6afa2b45b865af0845103e126c4bc00e"></script>
<script>

var lat;
var lng;

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도를 클릭한 위치에 표출할 마커입니다
var marker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng; 
    
    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);
    
    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';
    
    var resultDiv = document.getElementById('clickLatlng'); 
    resultDiv.innerHTML = message;
    
    lat = latlng.getLat();
	lng = latlng.getLng();
//	alert("lat1:"+lat);
//	alert("lng1:"+lng);
    
});

function myson(mem_id){
//	alert("lat2:"+lat);
//	alert("lng2:"+lng);
	/* var lat = latlng.getLat();
	var lng = latlng.getLng(); */
//	$("#mem_id").val(mem_id);
//	 var mem_id = document.getElementById("mem_id") 
//	var mem_id = $('#mem_id').val();
	 location.href="lostlatlng?lat="+lat+"&lng="+lng+"&mem_id="+mem_id; 
	/* location.href="insertAction?lat="+lat+"&lng="+lng; */
	/* document.getElementById(lat).value = lat; 
	document.getElementById(lng).value = lng; */ 
//	$("#lat").val(lat);
//	$("#lng").val(lng);
	/* self.close(); */
} 

</script>

</c:if>

<c:if test="${lat!=null}">
				 <div id="map" style="width:100%;height:350px;"></div> 
				<div id="clickLatlng"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6afa2b45b865af0845103e126c4bc00e"></script>
<script>

var lat = $('#lat').val()
var lng = $('#lng').val()

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도를 클릭한 위치에 표출할 마커입니다
var marker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng; 
    
    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);
    
  //  var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
  //  message += '경도는 ' + latlng.getLng() + ' 입니다';
    
    var resultDiv = document.getElementById('clickLatlng'); 
    resultDiv.innerHTML = message;
    
    lat = latlng.getLat();
	lng = latlng.getLng();
//	alert("lat1:"+lat);
//	alert("lng1:"+lng);
    
});

function myson(pageNum,lost_no){
//	alert("lat2:"+lat);
//	alert("lng2:"+lng);
//	 var lat = latlng.getLat();
//	var lng = latlng.getLng(); 
//	$("#mem_id").val(mem_id);
//	 var mem_id = document.getElementById("mem_id") 
//	var mem_id = $('#mem_id').val();
	 location.href="lostUpdateForm?lat="+lat+"&lng="+lng+"&pageNum="+pageNum+"&num="+lost_no; 
	/* location.href="insertAction?lat="+lat+"&lng="+lng; */
	/* document.getElementById(lat).value = lat; 
	document.getElementById(lng).value = lng; */ 
//	$("#lat").val(lat);
//	$("#lng").val(lng);
	/* self.close(); */
} 

</script>

</c:if>
					<input type="button" value="위치수정하기" onclick="myson('${pageNum}','${lost.lost_no}')"/>
					</td>				
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="lost_pwd2" required="required"
								></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="lost_title" id="lost_title" required="required"
								value="${lost.lost_title}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="40" name="lost_content" id="lost_content"
							required="required">${lost.lost_content}</textarea></td>
				</tr>
				<tr>
					<th>사진첨부</th>
					<td><input type="file" name="lost_file1" id="lost_file1" ></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><input type="submit" value="수정">
					<input type="button" value="취소" onClick="history.back(-1)">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>