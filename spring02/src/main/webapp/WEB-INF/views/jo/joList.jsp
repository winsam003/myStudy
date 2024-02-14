<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JoList **</title>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
	<h2>JoList **</h2>

	<table border="1" style="width: 100%">
		<tr bgcolor="Chocolate">
			<th>Jno</th>
			<th>Jname</th>
			<th>Captain</th>
			<th>조장이름</th>
			<th>Project</th>
			<th>Slogan</th>
		</tr>

		<c:if test="${!empty requestScope.joList}">

			<c:forEach var="list" items="${requestScope.joList}">
				<tr>
					<td style="text-align:center"><a href="joDetail?jno=${list.jno}&jCode=D">${list.jno}</a></td>
					<td>${list.jname}</td>
					<td>${list.captain}</td>
					<td>${list.cname}</td>
					<td>${list.project}</td>
					<td>${list.slogan}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.joList}">
			<tr>
				<td colspan="9"><h2>~~ 출력 할 데이터가 없습니다. ~~</h2></td>
			</tr>
		</c:if>
	</table>

	<hr>
	<a href="/spring02/home">Home</a> &nbsp;
	<a href="javascript:history.go(-1)">이전으로</a>
	<hr>

</body>
</html>