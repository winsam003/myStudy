<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** memberInsert **</title>
<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle.css">
</head>
<body>
	<h2>** memberInsert **</h2>

	<form action="joInsert" method="get">
		<table border=1>
			<caption>조 등록</caption>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="jno">Jno</label></th>
				<td><input type="text" id="jno" name="jno" size="20"placeholder=""></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="jname">Jname</label></th>
				<td><input type="text" id="jname" name="jname"
					size="20" placeholder=""></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="captain">조장</label></th>
				<td><input type="text" id="captain" name="captain" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="project">프로젝트이름</label></th>
				<td><input type="text" id="project" name="project" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="slogan">슬로건</label></th>
				<td><input type="text" id="slogan" name="slogan" size="20"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="조 등록">&nbsp;&nbsp; <input type="reset" value="재 입력"></td>
			</tr>
		</table>
	</form>


	<hr>
	<a href="/home">Home</a> &nbsp;
	<a href="javascript:history.go(-1)">이전으로</a>
	<hr>
	
	<c:if test="${!empty requestScope.message}">
		${requestScope.message}
	</c:if>
</body>
</html>