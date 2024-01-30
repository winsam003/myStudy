<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** InsertForm **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>InsertForm</h2>

	<form action="insert" method="post">
		<table>
			<caption>글쓰기</caption>

			<tr height="40">
				<th bgcolor="MediumPurple"><label for="id">ID</label></th>
				<td><input type="text" id="id" name="id" size="20" value="${sessionScope.loginID}" readonly></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="title">글제목</label></th>
				<td><input type="text" id="title" name="title" size="20" placeholder="글 제목을 입력해주세요."></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="content">content</label></th>
				<td><input type="text" name="content" size="20"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="글쓰기">&nbsp;&nbsp;<input type="reset" value="재 입력"></td>
			</tr>
		</table>
	</form>

<br><hr>

<a href="/spring02/home">홈으로</a>
<a href="boardList">리스트로</a>
<a href="javascript:history.go(-1)">뒤로가기</a>


</body>
</html>