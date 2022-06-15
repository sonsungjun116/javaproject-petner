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
				<h2 align=center>판매 관리</h2>
				<tr>
					<th>펫 종류</th>
					<th>상품 종류</th>
					<th>상품번호</th>
					<th>상품명</th>
					<th>가격</th>
					<th>제조사</th>
					<th>재고</th>
					<th>판매량</th>
					<th colspan=2>비고</th>
				</tr>
				<c:forEach var="row" items="${list}">
					<tr>
						<td>${row.product_pet}</td>
						<td>${row.product_category}</td>
						<td>${row.product_no}</td>
						<td><a href="${path}/productdetail?product_no=${row.product_no}"
							style="text-decoration: none">${row.product_name}</td>
						<td><fmt:formatNumber value="${row.product_price}"
								pattern="###,###,###"/>원</td>
						<td>${row.product_make}</td>
						<td>${row.product_stock}</td>
						<td>${row.product_sale}</td>
						<td><input type=button value="수정" onClick="location.href='${path}/productupdateform?product_no=${row.product_no}'">
						<input type=button value="삭제" onClick="location.href='${path}/productdeleteresult?product_no=${row.product_no}'"></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
	</c:otherwise>
</c:choose>
</div>
<%@ include file="../include/footer.jsp" %>