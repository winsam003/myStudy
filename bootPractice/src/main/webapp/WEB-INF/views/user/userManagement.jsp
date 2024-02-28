<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserManagement</title>
<script src="/resources/myLib/userManagement.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/myLib/style.css">
</head>
<body>
<h2>UserManagement</h2>
<a href="javascript:history.go(-1)">뒤로가기</a>
<hr>

<c:if test="${empty sessionScope.LoginID && empty sessionScope.LoginName}">
	&nbsp;로그인 후 이용하세요.<br>
	&nbsp;<span class="textlink" onclick="loginForm()">Login</span>&nbsp;
</c:if>
<c:if test="${!empty sessionScope.LoginID && !empty sessionScope.LoginName}">
	&nbsp;${sessionScope.LoginName} 님 안녕하세요. ^ㅇ^<br>
	&nbsp;<span class="textlink" onclick="logout()">Logout</span>&nbsp;
</c:if>

<hr>

&nbsp;<span class="textlink" onclick="userList()">UserList</span>&nbsp;

<hr>
<div id="textArea01"></div>
<div id="textArea02"></div>

</body>
</html>