<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Home **</title>
</head>
<body>
<h2>** Dynamic Web Project **</h2>
<h2> 안녕하세요 ~~  홈페이지입니다!!!!</h2>


<c:choose>
	<c:when test="${empty sessionScope.name}">
		<h3 style="color:purple;">로그인을 해주세요</h3>
		<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>
	</c:when>
	<c:when test="${!empty sessionScope.name}">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" src="./images/basicman2.jpg" width="100" height="100">
		<h3><%= session.getAttribute("name")%> 님 안녕하세요</h3><br>
		&nbsp;<a href="/web01/Detail">myInfo</a>&nbsp;
	 	<a href="/web01/logout">Logout</a><br><br>
	 </c:when>
</c:choose>

<%-- <%
if(session.getAttribute("name") != null){%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" src="./images/basicman3.jpg" width="100" height="100">
<h3><%= session.getAttribute("name")%> 님 안녕하세요</h3><br>
<%
} else{%>
	<h3>로그인을 해주세요</h3>
<%}
%> --%>
<hr>

<form action = "GetPost" method = "get">
	<input type = "text" name = "id" value="banana"> &nbsp;
	<input type = "text" name = "name" value="바나나">
	<input type = "text" name = "password">
	<input type = "submit" value = "Test">
</form>
<hr>
<!-- 
	** 경로표기
	=> 절대경로 : / 로 시작, 프로젝트 명부터 표기 해야 함
	=> 풀 경로를 다 써줘야 함 ex) /web01/images/letsgo.png
	=> 생각해보면 /web01/src/main/... 이렇게 써야하는데
	=> 기본적으로 webapp에 이미지가 들어가기때문에 web01 하고 webapp 까진 생략하고
	=> 바로 images 써도 무방함
	=> 그래서 예시가 /web01/images/letsgo.png 이런 것임
	
	=> 상대경로 : 현재 위치에서 시작, / 로 시작하면 안됨
		-> ./ : 현재위치를 의미, ../ 1단계 상위로 가기
		-> ./images/letsgo.png, images/letsgo.png 은 동일 함
--> 


<img alt="" src="./images/white01.gif" width="300" height="200">
<img alt="" src="./images/jjang9.gif" width="300" height="200">
<img alt="" src="./images/IMG_0834.gif" width="300" height="200">
<img alt="" src="./images/jjangjjangjjang.gif" width="300" height="200">
<img alt="" src="./images/JJang.gif" width="300" height="200">
<img alt="" src="./images/j1.gif" width="300" height="200">

<hr>



&nbsp;<a href="/web01/hello">Hello</a>&nbsp;
&nbsp;<a href="/web01/list">MVC01List</a>&nbsp;
&nbsp;<a href="/web01/life">LifeCycle</a><br>

&nbsp;<a href="/web01/servletTestForm/form01_Adder.html">adder</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form02_Radio.jsp">Radio</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form03_Check.jsp">Check</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form04_Select.jsp">Select</a>&nbsp;<br>

&nbsp;<a href="/web01/flow01">Flow01</a>&nbsp;
&nbsp;<a href="/web01/sessioni">SessionI</a><br>

&nbsp;<a href="/web01/jsp01/ex01_HelloJsp.jsp">HelloJ</a>&nbsp;
&nbsp;<a href="/web01/jsp01/ex02_mvc01List.jsp">M01ListJ</a>&nbsp;
&nbsp;<a href="/web01/list2">M02List</a>&nbsp;
&nbsp;<a href="/web01/list2">M02List2</a>&nbsp;



</body>
</html>