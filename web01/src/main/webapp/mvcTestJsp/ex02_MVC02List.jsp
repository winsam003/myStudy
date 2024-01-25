<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MVC02_List JSTL **</title>
</head>
<body>

<h2>** MVC02_List JSTL **</h2>
<table border="1" style="width:100%">
<tr bgcolor="Aquamarine">
	<th>Sno</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th><th>Point</th>
</tr>

<c:if test="${!empty requestScope.myList}">

	<c:forEach var="s" items="${requestScope.myList}">
			<tr>
			<td>${s.sno}</td>
			<td>${s.name}</td>
			<td>${s.age}</td>
			<td>${s.jno}</td>
			<td>${s.info}</td>
			<td>${s.point}</td>
			</tr>
	</c:forEach>
			<!-- el태그에서는 알아서 getter를 불러줌-->

</c:if>
<c:if test="${empty requestScope.myList}">
<tr>
	<td colspan="6"><h3>** 출력할 Data가 없습니다. **</h3></td>
</tr>
</c:if>






<%-- <%
	if(!=null){
		for(StudentDTO s:list) {%>
			<tr>
			<td><%=s.getSno()%></td>
			<td><%=s.getName()%></td>
			<td><%=s.getAge()%></td>
			<td><%=s.getJno()%></td>
			<td><%=s.getInfo()%></td>
			<td><%=s.getPoint()%></td>
			</tr>
<%	}
} else{%>
	<h3>** 출력할 Data가 없습니다. **</h3>
<%}
%> --%>
</table>
<hr>
<h2><a href='javascript:history.go(-1)'>다시 입력하기</a></h2>


</body>
</html>