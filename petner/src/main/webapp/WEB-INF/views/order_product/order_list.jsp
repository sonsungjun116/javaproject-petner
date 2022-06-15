<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<body>
<c:choose>
	<c:when test="${empty list}">
		<h2 align=center>주문한 상품이 없습니다.</h2>
	</c:when>
	<c:otherwise>
		<div>
			<table border=1 align=center width=900>
				<h2 align=center>주문 내역</h2>
				<tr>
					<th>주문일자</th>
					<th>주문번호</th>
					<th>상품명</th>
					<th>총결제금액</th>
					<th>주문상태</th>
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
</body>