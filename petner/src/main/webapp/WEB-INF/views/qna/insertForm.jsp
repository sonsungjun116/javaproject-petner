<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<!-- 외부 자바스크립트 파일(유효성 검사) 불러오기 -->
<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="${path}/js/qna.js"></script>
<script src="${path}/js/jquery.js"></script>

<script src="http://code.jquery.com/jquery-latest.js"></script>

<body>
	<form action="qna_insert" method="post" enctype="multipart/form-data" onSubmit="return check()">
		<input type="hidden" id="product_no" name="product_no" value="${product_no}">
		<h2 align=center>문의글 작성</h2>
		<table border=1 align=center  width=500 height=400>
			<tr>
				<th>작성자</th>
				<td><input type="hidden" id="mem_id" name="mem_id" value="${mem_id}">${mem_id}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="qna_pwd" name="qna_pwd" autofocus="autofocus">
					<input type="checkbox" onclick="$('#qna_secret').val('Y')">비밀글
					<input type="hidden" id="qna_secret" name="qna_secret" value="N"></td>
			</tr>
			<tr>
				<th>문의종류</th>
				<td><select id="qna_category" id="qna_category" name="qna_category">
						<option value="">카테고리선택</option>
						<option value="상품">상품</option>
						<option value="결제">결제</option>
						<option value="배송">배송</option>
						<option value="교환/환불">교환/환불</option>
						<option value="기타">기타</option>
					</select></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" size=52 id="qna_title" name="qna_title"></td>
				<%-- <td><c:if test="${product_no != null}">
						<input type="hidden" id="qna_title" name="qna_title"
							value="상품번호 ${product_no}번에 대한 ${qna.qna_category} 문의입니다.">
							상품번호 ${product_no}번에 대한 ${qna.qna_category} 문의입니다.</c:if>
					<c:if test="${product_no == null}">
						<input type="hidden" id="qna_title" name="qna_title"
							value="${qna.qna_category} 문의입니다.">${qna.qna_category} 문의입니다.</c:if>
				</td> --%>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="15" cols="50" id="qna_content" name="qna_content"></textarea></td>
			</tr>
			<tr>
				<th>파일첨부</th>
				<td><input type="file" id="qna_file1" name="qna_file1"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
	</div>
   <%@ include file="../include/footer.jsp" %>
</body>
</html>