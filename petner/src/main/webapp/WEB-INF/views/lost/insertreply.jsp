<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<script type="text/javascript">
			alert("댓글 입력 성공");
			history.go(-1)
//			location.href = "lostListView";
		</script>
	
	<%-- <c:if test="${result <= 0 }">
		<script type="text/javascript">
			alert("입력 실패");
			history.go(-1);
		</script>
	</c:if> --%>
</body>
</html>