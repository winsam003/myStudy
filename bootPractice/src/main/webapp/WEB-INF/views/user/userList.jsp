<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserList</title>
</head>
<body>

<h2>** UserList **</h2>

<table border="1" style="width:100%;">
	<tr bgcolor="DeepSkyBlue">
		<th>ID</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th><th>Image</th>
		<!-- <th>Password</th> -->
	</tr>
	
<c:if test="${!empty requestScope.userList}"></c:if>
	<c:forEach var="list" items="${requestScope.userList}">
		<tr>
			<td>${list.id}</td>
			<td>${list.name}</td>
			<td>${list.age}</td>
			<td>${list.jno}</td>
			<td>${list.info}</td>
			<td>${list.point}</td>
			<td>${list.birthday}</td>
			<td>${list.rid}</td>
			<td><img alt="myImage" src="/resources/uploadImages/${list.uploadfile}" width="100"height="100"></td>
		</tr>
	</c:forEach>
<c:if test="${empty requestScope.userList}">
		<tr>
			<td colspan="9"><h2>~~ 출력 할 데이터가 없습니다. ~~</h2></td>
		</tr>
</c:if>
</table>

</body>
</html>