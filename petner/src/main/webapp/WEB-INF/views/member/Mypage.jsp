<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>

   <div>

      <h4>마이페이지</h4>
      <h5>프로필</h5>
      <img src="${path}/upload/member/${member.mem_profile}">
      <h5>회원등급</h5>
      <p>${member.mem_grade}</p>
      <h5>이름</h5>
      <p>${member.mem_name}</p>
      <h5>이메일</h5>
      <p>${member.mem_email}@${member.mem_domain}</p>
      <h5>전화번호</h5>
      <p>${member.mem_phone1}-${member.mem_phone2}-${member.mem_phone3}</p>
      <h5>주소</h5>
      <p>${member.mem_addr1}-${member.mem_addr2}</p>

      <input type="button" value="회원 정보 수정"
         onClick="location='updateMember?mem_id=${sessionScope.mem_id}'">
      <input type="button" value="회원 삭제"
         onClick="location='deleteMember?mem_id=${sessionScope.mem_id}'">
      <input type="button" value="장바구니"
         onClick="location.href='basketList?mem_id=${sessionScope.mem_id}'">
   
      <c:if test="${member.mem_type=='일반회원'}">
      <input type="button" value="사업자 승인신청"
         onClick="location.href='TransferForm?mem_id=${sessionScope.mem_id}'">
      </c:if>
      <input type="button" onClick="location.href='order_list'" value="주문내역">


      <c:if test="${member.mem_type =='사업자'}">
         <input type="button" onClick="location.href='sell_list'" value="판매내역">
         <input type="button" onClick="location.href='sell_manage'" value="판매관리">
      </c:if>
   </div>
   </div>
   <%@ include file="../include/footer.jsp"%>
</body>
</html>