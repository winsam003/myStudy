<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** ex03_MVC02Detail **</title>
</head>
<body>

<h2>** ex03_MVC02Detail **</h2>

<table border="1">

<c:if test="${!empty requestScope.dto}">
			<tr>
			<th bgcolor="skyblue" style="height:40">Sno</th>
			<td>${requestScope.dto.sno}</td>
			</tr>
			<tr>
			<th bgcolor="skyblue" style="height:40">Name</th>
			<td>${requestScope.dto.name}</td>
			</tr>
			<tr>
			<th bgcolor="skyblue" style="height:40">Age</th>
			<td>${requestScope.dto.age}</td>
			</tr>
			<tr>
			<th bgcolor="skyblue" style="height:40">Jno</th>
			<td>${requestScope.dto.jno}</td>
			</tr>
			<tr>
			<th bgcolor="skyblue" style="height:40">Info</th>
			<td>${requestScope.dto.info}</td>
			</tr>
			<tr>
			<th bgcolor="skyblue" style="height:40">Point</th>
			<td>${requestScope.dto.point}</td>
			</tr>
</c:if>
<c:if test="${empty requestScope.dto}">
<tr>
	<td colspan="6"><h3>** 출력할 Data가 없습니다. **</h3></td>
</tr>
</c:if>
</table>

</body>
</html>