<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 **</title>
</head>
<body>
<h2>** Web02_MVC02 **</h2>



<c:choose>
	<c:when test="${empty sessionScope.name}">
		<h3 style="color:purple;">로그인을 해주세요</h3>
		&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>
		&nbsp;<a href="/web02/member/joinForm.jsp">Join</a><br>
	</c:when>
	<c:when test="${!empty sessionScope.name}">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" src="./images/basicman2.jpg" width="100" height="100">
		<h3><%= session.getAttribute("name")%> 님 안녕하세요</h3><br>
		&nbsp;<a href="/web02/mdetail">myInfo</a>&nbsp;
		&nbsp;<a href="/web02/mdetail?jCode=U">정보수정</a>&nbsp;
	 	&nbsp;<a href="/web02/logout">Logout</a>&nbsp;
	 	&nbsp;<a href="/web02/mdelete">회원탈퇴</a><br><br>
	 </c:when>
</c:choose>

<br>
<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>


<hr>
<img alt="" src="./images/white01.gif" width="300" height="200">
<img alt="" src="./images/jjang9.gif" width="300" height="200">
<img alt="" src="./images/IMG_0834.gif" width="300" height="200">
<img alt="" src="./images/jjangjjangjjang.gif" width="300" height="200">
<img alt="" src="./images/JJang.gif" width="300" height="200">
<img alt="" src="./images/j1.gif" width="300" height="200">
<br>
<hr>
&nbsp;<a href="mlist">MList</a>&nbsp;
&nbsp;<a href="mdetail">MDetail</a>&nbsp;



</body>
</html>