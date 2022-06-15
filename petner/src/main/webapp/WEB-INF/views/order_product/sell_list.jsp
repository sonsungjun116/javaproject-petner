<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<c:choose>
	<c:when test="${empty list}">
		<h2 align=center>판매된 상품이 없습니다.</h2>
	</c:when>
	<c:otherwise>
		<div>
			<table border=1 align=center width=900>
				<h2 align=center>판매 내역</h2>
				<tr>
					<th>판매일자</th>
					<th>판매번호</th>
					<th>상품명</th>
					<th>총 결제금액</th>
					<th>구매자</th>
					<th>판매상태</th>
				</tr>
				<c:forEach var="row" items="${list}">
					<tr>
						<td><fmt:formatDate value="${row.payment_regdate}"
								pattern="yyyy-MM-dd" /></td>
						<td><a href="${path}/order_view?payment_no=${row.payment_no}"
							style="text-decoration: none">${row.order_no}</a></td>
						<td>${row.product_name}</td>
						<td><fmt:formatNumber value="${row.payment_price}"
								pattern="###,###,###" />원</td>
						<td>${row.mem_id}</td>
						<td>${row.order_state}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
	</c:otherwise>
</c:choose>
</div>
<%@ include file="../include/footer.jsp" %>
