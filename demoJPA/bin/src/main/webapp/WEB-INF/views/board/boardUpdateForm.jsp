<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Update Form **</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css">
</head>
<body>
<h2>** 게시글수정 Form **</h2>

	<form action="boardUpdate" method="post">
		<table>
			<caption>게시글수정</caption>

			<tr height="40">
				<th bgcolor="MediumPurple"><label for="seq">SEQ</label></th>
				<td><input type="number" id="seq" name="seq" size="20" value="${requestScope.apple.seq}" readonly></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="id">ID</label></th>
				<td><input type="id" id="id" name="id" size="20" value="${requestScope.apple.id}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="title">Title</label></th>
				<td><input type="text" id="title" name="title" size="20" value="${requestScope.apple.title}"></td>
			</tr>
			<tr height="300">
				<th bgcolor="MediumPurple"><label for="content">content</label></th>
				<td><textarea rows="20" cols="100" name="content"></textarea></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="regdate">작성시간</label></th>
				<td><input type="text" id="regdate" name="regdate" size="20" value="${requestScope.apple.regdate}" readonly></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="cnt">CNT</label></th>
				<td><input type="text" id="cnt" name="cnt" size="20" value="${requestScope.apple.cnt}" readonly></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="글수정">&nbsp;&nbsp;<input type="reset" value="재 입력"></td>
			</tr>
		</table>


	</form>

	<hr>
	&nbsp;
	<a href="/home">Home</a> &nbsp;
	<a href="boardList">리스트로</a>
	<a href="javascript:history.go(-1)">이전으로</a>

	<hr>
<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>


</body>
</html>