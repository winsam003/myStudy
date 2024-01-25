<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h2>Hello Spring !!!</h2>

	<P>The time on the server is ${serverTime}.</P>

	<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>

&nbsp;<a href="mlist">MList</a>
&nbsp;<a href="mdetail">MDetail</a>;
&nbsp;<a href="mlistsp">MListsp</a>
&nbsp;<a href="mdetailsp">MDetailsp</a>;
<br>

	<hr>
	<img alt="" src="resources/images/white01.gif" width="300" height="200">
	<img alt="" src="resources/images/jjang9.gif" width="300" height="200">
	<img alt="" src="resources/images/IMG_0834.gif" width="300" height="200">
	<img alt="" src="resources/images/jjangjjangjjang.gif" width="300" height="200">
	<img alt="" src="resources/images/JJang.gif" width="300" height="200">
	<img alt="" src="resources/images/j1.gif" width="300" height="200">
	<br>
	<hr>
</body>
</html>
