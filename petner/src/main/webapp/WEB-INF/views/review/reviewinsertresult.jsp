<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	alert("등록을 완료했습니다.");
	location.href="productdetail?product_no="+${sessionScope.product_no};
</script>