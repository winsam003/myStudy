<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jo Update **</title>
</head>
<body>
<h2>** Jo Update **</h2>

	<form action="joUpdate" method="post">
		<table>
			<caption>조 정보 수정</caption>

			<tr height="40">
				<th bgcolor="MediumPurple"><label for="jno">Jno</label></th>
				<td><input type="text" id="jno" name="jno" size="20"
					value="${requestScope.joDetail.jno}" readonly></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="jname">Jname</label></th>
				<td><input type="text" id="jname" name="jname"
					size="20" value="${requestScope.joDetail.jname}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="captain">Captain</label></th>
				<td><input type="text" id="captain" name="captain" size="20" value="${requestScope.joDetail.captain}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="project">Project</label></th>
				<td><input type="text" id="project" name="project" size="20" value="${requestScope.joDetail.project}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="slogan">Slogan</label></th>
				<td><input type="text" id="slogan" name="slogan" size="20" value="${requestScope.joDetail.slogan}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="정보수정">&nbsp;&nbsp; <input
					type="reset" value="재 입력"></td>
			</tr>
		</table>


	</form>

	<hr>
	&nbsp;
	<a href="/home">Home</a> &nbsp;
	<a href="javascript:history.go(-1)">이전으로</a>

	<hr>
<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>


</body>
</html>