<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <%@ include file="header.jsp"%>  


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>


</head>
<body>
<!-- <div id="map" style="width:100%;height:350px;"></div> -->


	 <div class="container" align="center"> 
		<h2 class="text-primary">미아 게시판 글쓰기</h2>
		<form action="insertAction" method="post" enctype="multipart/form-data">
			 <input type="hidden" name="lat" id="lat" value="${lat}">  
			 <input type="hidden" name="lng" id="lng" value="${lng}"> 
			 <input type="hidden" name="mem_id" id="mem_id" value="${mem_id}"> 
			<table class="table table-striped"  border="1" height="400">
				 <tr>
					<td width="30%">작성자</td>
					<%-- <td><input type="text" name="mem_id" required="required" readonly value="${sessionScope.id}"></td> --%>
					<td width="70%">${mem_id}</td>
				</tr> 
				 
				<tr  width="350" height="400">
				<td  width="150" height="400">위치 선택하기</td>
				<td  width="350" height="400">
				<!-- <td><div id="map"></div></td> -->
				 
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

			<input type="button" value="위치 확인" onclick="myson('${mem_id}')"/>	
								
				</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="lost_title" required="required"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="lost_pwd" required="required"></td>
				</tr> 
				
				<tr>
					<td>내용</td>
					<td><textarea rows="5" cols="40" name="lost_content"
							required="required"></textarea></td>					
				</tr>
				<tr>
					<td>사진첨부</td>
					<td><input type="file" name="lost_file1" ></td>
				</tr> 
				<tr>
					<td colspan="2" align="center"><input type="submit" value="글쓰기">
					<input type="button" value="취소" onClick="history.back(-1)"></td>
				</tr>
			
			</table>
			
		</form>		 
	 </div> 
		
</body>
</html>