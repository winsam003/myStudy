<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** memberDetail **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
	<h2>** memberDetail **</h2>

	<table border=1>
		<c:if test="${!empty requestScope.userDetail}">
			<caption>회원정보</caption>
			<tr height="40">
				<th bgcolor="BlueViolet">ID</th>
				<td>${requestScope.userDetail.id}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Password</th>
				<td>${requestScope.userDetail.password}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Name</th>
				<td>${requestScope.userDetail.name}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Age</th>
				<td>${requestScope.userDetail.age}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Jno</th>
				<td>${requestScope.userDetail.jno}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Info</th>
				<td>${requestScope.userDetail.info}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Point</th>
				<td>${requestScope.userDetail.point}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Birthday</th>
				<td>${requestScope.userDetail.birthday}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">추천인</th>
				<td>${requestScope.userDetail.rid}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">추천인</th>
				<td><img alt="mainImage" src="/spring02/resources/uploadImages/${requestScope.userDetail.uploadfile}" width="300"height="200"></td>
			</tr>

		</c:if>
		<c:if test="${empty requestScope.userDetail}">
			<tr>
				<th bgcolor="BlueViolet" size="10" colspan="9">출력 할 Data가 없습니다.</th>
			</tr>
		</c:if>
	</table>

	<hr>
	&nbsp;
	<a href="/spring02/home">Home</a> &nbsp;
	<a href="javascript:history.go(-1)">이전으로</a>
<hr>
<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>


</body>
</html>