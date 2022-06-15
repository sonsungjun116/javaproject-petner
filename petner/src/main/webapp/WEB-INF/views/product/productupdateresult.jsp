<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	alert("상품수정을 완료했습니다.");
	location.href="productmylist?seller_no="+${sessionScope.seller_no}+"&pageNum="+${pageNum};
</script>