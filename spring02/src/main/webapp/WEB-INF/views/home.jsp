<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
	<h2>Hello Spring_MVC02 !!!</h2>

	<P>* Home_time: ${serverTime}.</P>

	<hr>




<c:if test="${!empty requestScope.message}">
	<hr>${requestScope.message}<br>
</c:if>
<!-- 로그인 전 -->

<c:if test="${empty sessionScope.loginID && empty requestScope.message}">
		&nbsp;로그인 후 이용하세요 <(＿　＿)><(＿　＿)><br><br>
		&nbsp;<a href="member/loginForm">LoginF</a>&nbsp;
		&nbsp;<a href="member/joinForm">JoinF</a>&nbsp;
</c:if>

<!-- 로그인 후 -->
<c:if test="${!empty sessionScope.loginName}">
		${sessionScope.loginName} 님 안녕하세요 \(￣︶￣*\))\(￣︶￣*\))<br>
		&nbsp;<a href="member/logout">Logout</a>&nbsp;
		&nbsp;<a href="member/memberDetail?jCode=D">나의정보</a>&nbsp;
		&nbsp;<a href="member/memberDetail?jCode=U">내정보수정</a>&nbsp;
		&nbsp;<a href="member/delete">회원탈퇴</a>&nbsp;
</c:if>
<br><hr>

&nbsp;<a href="member/memberList">MList</a> &nbsp;
&nbsp;<a href="jo/joList">JList</a> &nbsp;
&nbsp;<a href="board/boardList">BList</a> &nbsp;

<br><hr>

<img alt="mainImage" src="/spring02/resources/images/www.gif" width="300"height="200">
<img alt="mainImage" src="/spring02/resources/images/jjang9.gif" width="300"height="200">
<img alt="mainImage" src="/spring02/resources/images/IMG_0834.gif" width="300"height="200">
<img alt="mainImage" src="/spring02/resources/images/jjangjjangjjang.gif"width="300" height="200">
<img alt="mainImage" src="/spring02/resources/images/JJang.gif" width="300"height="200">
<img alt="mainImage" src="/spring02/resources/images/j1.gif" width="300"height="200">

<br><hr>
</body>
</html>
