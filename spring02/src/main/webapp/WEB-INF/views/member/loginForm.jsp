<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** LoginForm **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>

<form action="login" method="post">
	<table>
		<caption style="font-weight:bold">Login</caption>
		<tr height="40">
			<td bgcolor="indogo"><label for="id">I D</label></td>
			<td bgcolor="indogo"><input type="text" id="id" name="id" size="20"></td>
		</tr>
		<tr height="40">
			<td bgcolor="indogo"><label for="password">Password&nbsp;</label></td>
			<td bgcolor="indogo"><input type="password" id="password" name="password" size="20"></td>
		</tr>		
		<tr><td></td>
			<td><input type="submit" value="로그인">&nbsp;&nbsp;
			<input type="reset" value="취소"></td>
		</tr>
	</table>
</form>
<hr>
&nbsp;<a href="/spring02/home">Home</a>
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>
<hr>
<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>
<c:if test="${empty requestScope.message}">
	${requestScope.message}
</c:if>


</body>
</html>