<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/css/style.css">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

<c:if test="${sessionScope.mem_id != 'admin'}">
<div class="top-header">
<div class="header">
</c:if>

<c:if test="${sessionScope.mem_id == 'admin'}">
<div class="top-header">
<div class="header">

		<a href="sellerList">사업자 목록</a>
		<a href="reviewlist">리뷰 목록</a>

</c:if>
<c:if test="${sessionScope.mem_id == null}">
	<div class="login">
		<a href="Sign">회원가입</a>
		<a href="Loginform">로그인</a>
	</div>	
</c:if>
<c:if test="${sessionScope.mem_id != null}">
	<div class="login">
		<a href="Mypage?mem_id=${sessionScope.mem_id}">마이페이지(${sessionScope.mem_id})</a>
		<a href="Logout">로그아웃</a>
	</div>	
</c:if>

</div>
</div>

<div class="main">
<div class="logo-line">
	<div class="logo">
		<a href="productlist1"><img src="${path}/images/petner.png"></a>
	</div>
	<div class="info">
		<a href="productlist">스토어</a>
	</div>
	<div class="info">
		<a href="qna_list">Q&A게시판</a>
	</div>
	<div class="info">
		<a href="tipboardList">팁게시판</a>
	</div>
	<div class="info">
		<a href="lostList">미아게시판</a>
	</div>
	<div class="info">
		<a href="hospital_list">병원안내</a>
	</div>
</div>
