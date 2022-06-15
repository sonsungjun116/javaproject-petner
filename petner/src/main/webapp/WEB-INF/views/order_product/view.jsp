<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>

<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="${path}/js/jquery.js"></script>

<body>
	<h2 align=center>주문 상품 확인</h2>
	<br>
	<table border=1 align=center width=700 height=220>
		<tr>
			<th colspan=5 height=20%>상품정보</th>
		</tr>
		<tr>
			<th colspan=2 width=65% height=16%>상품명</th>
			<th>단가</th>
			<th>수량</th>
			<th>주문금액</th>
		</tr>
		<c:forEach var="row" items="${list}">
			<tr>
				<td><img src="${path}/upload/product/${row.product_img}"
					width=120 height=120></td>
				<td>${row.product_name}</td>
				<td><fmt:formatNumber pattern="###,###,###"
						value="${row.product_price}"/>원</td>
				<td>${row.order_ea}개</td>
				<td><fmt:formatNumber pattern="###,###,###"
						value="${row.product_price * row.order_ea}" />원</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<table border=1 align=center width=700 height=50>
		<c:forEach var="row" items="${list}" varStatus="i">
			<c:if test="${sessionScope.mem_type == '일반회원' || sessionScope.mem_type == '일반'}">
			<tr>
				<th width=30%>주문상태</th>
				<td>${row.order_state}
						<c:if test="${order_product.order_state == '입금완료'}">
							<input type=button value="주문취소"
								onclick="location.href='${path}/change_state?payment_no=${order_product.payment_no}&state=cancel'">
						</c:if>
						<c:if test="${order_product.order_state == '배송완료'}">
							<input type=button value="구매확정"
								onclick="location.href='${path}/change_state?payment_no=${order_product.payment_no}&state=fix'">
							<input type=button value="주문취소"
								onclick="location.href='${path}/change_state?payment_no=${order_product.payment_no}&state=cancel'">
						</c:if>
						<c:if test="${order_product.order_state == '구매확정'}">
							<input type=button value="리뷰작성"
								onclick="location.href='reviewinsertform?product_no=${order_product.product_no}&mem_id=${sessionScope.mem_id}'">
						</c:if>
			</tr>
			</c:if>
			<c:if test="${sessionScope.mem_type == '사업자'}">
			<tr colspan=4>
				<th width=25%>주문상태</th>
				<td width=20%>${row.order_state}</td>
				<th width=25%>재고</th>
				<td width=30%>&nbsp;${product_stock}&nbsp;
					<c:if test="${order_product.order_state == '입금완료'}">
							<input type=button value="배송시작"
								onclick="location.href='${path}/change_state?payment_no=${order_product.payment_no}&state=delivery'">
							<input type=button value="주문취소"
								onclick="location.href='${path}/change_state?payment_no=${order_product.payment_no}&state=cancel'">
					</c:if>
				</td>
			</tr>
			</c:if>
		</c:forEach>
	</table>
	<br>
	<table border=1 align=center width=700 height=200>
		<tr>
			<th colspan=2>배송정보</th>
		</tr>
		<tr>
			<th width=30%>수취인</th>
			<td>${member.mem_name}</td>
		</tr>
		<tr>
			<th>배송주소</th>
			<td>(${member.mem_post}) ${member.mem_addr1} ${member.mem_addr2}</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>${member.mem_phone1} - ${member.mem_phone2} - ${member.mem_phone3}</td>
		</tr>
	</table>
	<br>
	<table border=1 align=center width=700 height=300>
		<tr>
			<th colspan=2>결제정보</th>
		</tr>
		<tr>
			<th width=30%>결제 수단</th>
			<td>카드</td>
		</tr>
		<tr>
			<th>결제 금액</th>
			<td><fmt:formatNumber pattern="###,###,###"
					value="${payment.payment_price}" />원</td>
		</tr>
		<tr>
			<th>상품 금액</th>
			<td><fmt:formatNumber pattern="###,###,###"
					value="${order_product.product_price * order_product.order_ea}" />원</td>
		</tr>
		<tr>
			<th>배송비</th>
			<td><fmt:formatNumber pattern="###,###,###"
					value="${shippingFee}" />원</td>
		</tr>
		<tr>
			<th>할인금액</th>
			<td><fmt:formatNumber pattern="###,###,###" value="0" />원</td>
		</tr>
		<tr>
			<th>총 결제금액</th>
			<td><fmt:formatNumber pattern="###,###,###"
					value="${payment.payment_price}" />원</td>
		</tr>
	</table>
	<br>
	<div align="center">
	<c:if test="${sessionScope.mem_type == '사업자'}">
		<input type="button" value="목록" onClick="location.href='sell_list'">
	</c:if>
	<c:if test="${sessionScope.mem_type == '일반회원' || sessionScope.mem_type == '일반'}">
		<input type="button" value="목록" onClick="location.href='order_list'">
	</c:if>
	</div>
	<br>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
