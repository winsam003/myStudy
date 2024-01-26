<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jo Detail **</title>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
	<h2>** Jo Detail **</h2>

	<table border=1>
		<c:if test="${!empty requestScope.joDetail}">
			<caption>조 정보</caption>
			<tr height="40">
				<th bgcolor="BlueViolet">ID</th>
				<td>${requestScope.joDetail.jno}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Password</th>
				<td>${requestScope.joDetail.jname}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Name</th>
				<td>${requestScope.joDetail.captain}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Age</th>
				<td>${requestScope.joDetail.project}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Jno</th>
				<td>${requestScope.joDetail.slogan}</td>
			</tr>
		</c:if>
		<c:if test="${empty requestScope.joDetail}">
			<tr>
				<th bgcolor="BlueViolet" size="10" colspan="9">출력 할 Data가 없습니다.</th>
			</tr>
		</c:if>
	</table>
	<hr>
	<a href="joInsertForm">조 등록</a> &nbsp;
	<a href="joDetail?jCode=U&jno=${requestScope.joDetail.jno}">조 수정</a> &nbsp;
	<a href="delete?jno=${requestScope.joDetail.jno}">조 삭제</a> &nbsp;

	<hr>
	<a href="/spring02/home">Home</a> &nbsp;
	<a href="javascript:history.go(-1)">이전으로</a>
	<hr>
</body>
</html>