<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="header.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--  <%@ include file="../include/header.jsp"%>  --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">
	$(function() {
		$('#list').load('replyList?lost_no=${lost.lost_no}'); // table을 적으면 게시판목록 문구를 제외한 테이블만 불러옴
	
		$('#repInsert').click(function() {
			if (!frm.lostreply_content.value) {
				alert('댓글 입력후에 클릭하시오');
				frm.lostreply_content.focus();
				return false;
			}
			var frmData = $('form').serialize(); // name값이 변수 value값이 value로 뽑아옴
			// var frmData = 'replyer='+frm.replyer.value+'&bno='+
			//				  frm.bno.value+'&replytext='+frm.replytext.value;		
			// $.post("요청이름","값 전달","콜백함수")
			$.post('${path}/replyLostInsertOk', frmData, function(data) {
//				alert(data);
//				alert(frm.lostreply_content.value);
				$('#list').html(data); // id가 slist로 되어있는 div영역에 콜백함수로 받은 값을 뿌려준다
				frm.lostreply_content.value = '';
			});
		});	
	
	});
</script>
</head>
<body>
	<div class="container" align="center">
		<h2 class="text-primary">게시글 상세정보</h2>
		<table class="table table-bordered" border="1">
			<tr>
			 
				<th>작성자</th>
				<td style="font-family: Tahoma; font-size: 10pt;">${lost.mem_id}<%-- ${lost.lat}${lost.lng} --%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td style="font-family: Tahoma; font-size: 10pt;">${lost.lost_title}</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td style="font-family: Tahoma; font-size: 10pt;">${lost.lost_date}</td>
			</tr>
			<tr>
				<th>잃어버린 위치</th>
				<td width="350">
				<input type="hidden" name="lat" id="lat" value="${lost.lat}">  
			 	<input type="hidden" name="lng" id="lng" value="${lost.lng}">
			 	
				<div id="map" style="width:100%;height:350px;"></div> 
				<div id="clickLatlng"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6afa2b45b865af0845103e126c4bc00e"></script>
<script>

//var lat = document.getElementById("lat");
//var lng = document.getElementById("lng");

var lat = $('#lat').val()
var lng = $('#lng').val()


/* var lat;
var lng; */

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(lat,lng), // 지도의 중심좌표
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
    
//  lat = latlng.getLat();
//	lng = latlng.getLng();
	alert("lat1:"+lat);
	alert("lng1:"+lng);
    
});

/* function myson(mem_id){
	alert("lat2:"+lat);
	alert("lng2:"+lng);
	/* var lat = latlng.getLat();
	var lng = latlng.getLng(); */
//	$("#mem_id").val(mem_id);
//	 var mem_id = document.getElementById("mem_id") 
//	var mem_id = $('#mem_id').val();
//	 location.href="lostlatlng?lat="+lat+"&lng="+lng+"&mem_id="+mem_id; 
	/* location.href="insertAction?lat="+lat+"&lng="+lng; */
	/* document.getElementById(lat).value = lat; 
	document.getElementById(lng).value = lng; */ 
//	$("#lat").val(lat);
//	$("#lng").val(lng);
	/* self.close(); }*/

</script>								
				</td>
			</tr>
			<tr>
				<th>이미지 사진</th>
				<td><c:if test="${empty lost.lost_file}">
       					&nbsp;
       				</c:if> 
       				<c:if test="${!empty lost.lost_file}">
						<img src="<%=request.getContextPath() %>/upload/lost/${lost.lost_file}"
							height="100" width="100" />
					</c:if></td>
			</tr>
			<tr>
				<th>내용</th>
				<td style="font-family: Tahoma; font-size: 10pt;"><pre>${lost.lost_content}</pre></td>
			</tr>
		</table>
													
		<a href="lostListView?pageNum=${pageNum}" class="btn btn-info">목록</a> <a
			href="lostUpdateForm2?num=${lost.lost_no}&pageNum=${pageNum}&lat=${lost.lat}&lng=${lost.lng}"
			class="btn btn-info">수정</a> <a
			href="lostDeleteForm?num=${lost.lost_no}&pageNum=${pageNum}"
			class="btn btn-info">삭제</a> 
			
			<form name="frm" id="frm"> <!-- 비동기적인 처리이므로 action이 없음 -->
			<input type="hidden" name="mem_id" value="${mem_id}"> <!-- 로그인해서 댓글을 다는 아이디의 아이디값 -->
			<input type="hidden" name="lost_no" value="${lost.lost_no}"> 댓글 :
			<textarea rows="4" cols="30" name="lostreply_content"></textarea>
			<input type="button" value="확인" id="repInsert">
		</form>
		<div id="list"></div>
	</div>
</body>
</html>