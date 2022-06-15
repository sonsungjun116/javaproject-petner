<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="header.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
 <%@ include file="../include/header.jsp" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
//$("input:checkbox[name='check']").attr("checked",true);

var check = false;
var payment_no1;
function check1() {
	if ($("input:checkbox[name='check']").is(":checked")==false) {
		
		alert("확인후 체크해주세요.");		
		
		return;
	}
	if ($("input:checkbox[name='check']").is(":checked")==true) {
		var frmData = $("#form7485").serialize();
		check = true;
	
	//	$.post('${path}/paymentapi', frmData, function(data) {
		
//			payment_no1 = payment_no1
//			alert(payment_no1);
	//	});
		
		myform.submit();	
//		$("input:checkbox[name='check']").attr("checked",true);
	}
	
}

function point(mem_point,product_no,product_ea) {
	var usemem_point = document.getElementById("usemem_point").value;	
	location.href="membermiletest?mem_point="+mem_point+"&usemem_point="+usemem_point+"&product_no="+product_no+"&product_ea="+product_ea;
//	location.href="lostlatlng?lat="+lat+"&lng="+lng+"&mem_id="+mem_id;
}

</script>
</head>
<body>
		 <script>
		 
		 
			
		 $(document).on("click","#button",function(){
//			 $("#button").click(function () { 이 방법을 사용하면 동적페이지로 변경되는 순간 동일한 id를 대상으로 이벤트가 적용되지 않는다 
//			if ($("input:checkbox[name='check']").is(":checked")==false) {
//				return;
//			}
			 if(check == false){
				alert("체크 버튼을 클릭 하세요.");
//				return false;
			} 			
			
			/* var payment_info = document.getElementById("payment_info").value;
			if (payment_info == "noaccount"){
				alert("무통장입금입니다.");
				return;
			} */
			
//			var payment_no1 = '${payment_no1}'
//			alert(payment_no1);
//			alert('${payment_no1}');			
			
			
			IMP.init("imp01626965");      
			IMP.request_pay({
    		pg : "html5_inicis", // version 1.1.0부터 지원.
    		pay_method : "card",
    		merchant_uid : ${payment_no1} + new Date().getTime(),
   			name : "주문명 : 결제테스트",
    		amount : ${payment.payment_price}, //판매 가격
    		buyer_email : '${pullemail}',
    		buyer_name : '${member.mem_name}',
    		buyer_tel : '${telall}',
    		buyer_addr : "서울특별시 강남구 삼성동",
   			buyer_postcode : "123-456"
			}, function(rsp) {
    		if ( rsp.success ) {
        		var msg = "결제가 완료되었습니다.";
        		msg += "고유ID : " + rsp.imp_uid
        		msg += "상점 거래ID : " + rsp.merchant_uid
        		msg += "결제 금액 : " + rsp.paid_amount
        		msg += "카드 승인번호 : " + rsp.apply_num
        		msg += "payment_no :" + ${payment_no1}
   			   alert(msg);
    
        		location.href = "paymenttest?payment_no="+'${payment_no1}'
    		} else {
        		var msg = "결제에 실패하였습니다.";
        		msg += "에러내용 : " + rsp.error_msg
   		 }
					});
					});
			 
		 </script>
	 <style>
	 
	 .payment{
	 	width: 900px;
	 	margin-left: auto;
	 	margin-right: auto;
	 }	 	 
	 </style>
	 
	 <br><br>
	  <form action="paymentapi" method="post" id="form7485" name="myform"> 
	 <div class="payment">
	  <input type="hidden" name="mem_id" id="mem_id" value="${member.mem_id}">
	  <input type="hidden" name="product_no" id="product_no" value="${payment.product_no}">
	  <input type="hidden" name="product_ea" id="product_ea" value="${payment.product_ea}">
	  <input type="hidden" name="product_name" id="product_name" value="${payment.product_name}">
	  <input type="hidden" name="product_price" id="product_price" value="${product.product_price}">
	  <input type="hidden" name="payment_price" id="payment_price" value="${payment.payment_price}">
	  <input type="hidden" name="payment_info1" id="payment_info1" value="${payment_info1}">
	  <input type="hidden" name="seller_no" id="seller_no" value="${payment.seller_no}">

	<%--  ${payment.product_no}${payment.mem_id}
	  	  ${sessionScope.mem_id = member.mem_id} --%>
	 주문자 명 : ${member.mem_name}
	
	 <br><br><br>
	 상품 이름 : ${payment.product_name}
	 <br><br><br>
	 상품 수량 : ${payment.product_ea}개
	 <br><br><br>
	 우편번호 
	 <input type="text" name="mem_post" required="required" value="${member.mem_post}">
	 <br><br><br>	 
	 배송지 주소
	 <input type="text" name="mem_addr1" required="required" value="${member.mem_addr1}">
	 <br><br><br>
	 주문자 상세 주소 
	 <input type="text" name="mem_addr2" required="required" value="${member.mem_addr2}">
	 <br><br><br>
	 연락처 : ${member.mem_phone1}-${member.mem_phone2 }-${member.mem_phone3 }
	
	 <br><br><br>
	 주문 가격 :  총주문금액은<fmt:formatNumber pattern="###,###,###" value = "${payment.payment_price}"/> 원입니다
	 <br><br><br>
	 <c:if test="${member.mem_grade == 'VIP' }">총배송비 0(VIP할인)</c:if>
	 <br><br><br>
	 <c:if test="${member.mem_grade != 'VIP' }">총배송비 2500</c:if>
	
	<c:if test="${transferpoint == null }">
	 내 보유적립금 : ${member.mem_point}입니다
	 </c:if>
	 <c:if test="${transferpoint != null }">
	 내 보유적립금 : ${transferpoint}입니다 ${payment_info1}
	 </c:if>
	 
	 <br><br><br>
	 <input type="text" id=usemem_point name=usemem_point value="${member.mem_point}"  > <input type="button" id="button1" name="button1" value="적립금 사용" onclick="point('${member.mem_point}','${payment.product_no}','${payment.product_ea}')">
	 	
	 <br><br><br>
	 마일리지를 적용한 총 주문금액은<fmt:formatNumber pattern="###,###,###" value = "${payment.payment_price}"/>원입니다<%--  ${payment.payment_no} --%>
	 <br><br><br>
	 결제수단
	 <select name="payment_info">
	 	<option value="" selected disabled hidden>결제수단을 선택하세요</option>
	 	<option value="card">카드</option>
	 	<option value="noaccount">무통장입금</option>
	 	
	 </select>
	
	 <br><br><br>
	<input type="checkbox" id="check" name="check" onclick="check1()"> 상기의 내용을 모두 확인했고 결제를 진행합니다.
	<!-- 체크박스는 name을 같게하고 value를 다르게 -->
	<br><br><br>
	<input type="button" value="확인" name="button" id="button">
	</div>
	 </form> 
</body>
</html>