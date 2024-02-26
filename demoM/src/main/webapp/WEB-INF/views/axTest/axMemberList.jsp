<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Axios MemberList **</title>
</head>
<body>
<h2>** Spring_Boot Axios MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
	=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%;">
	<tr bgcolor="hotpink">
		<th>ID</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th><th>Image</th><th>Delete</th>
	</tr>
	
<c:if test="${!empty requestScope.banana}"></c:if>
	<c:forEach var="list" items="${requestScope.banana}">
		<tr>
			<td><span class="textlink" onclick="idbList('${list.id}')">${list.id}</span></td>
			
			<!--
				 ** idbList : id별 boardList
				 	=> 선택된 id를 function 에 전달 (매개변수로 전달)
				 	idbList('${banana}');
			 -->
			
			<td>${list.name}</td>
			<td>${list.age}</td>
			<td>${list.jno}</td>
			<td>${list.info}</td>
			<td>${list.point}</td>
			<td>${list.birthday}</td>
			<td>${list.rid}</td>
			<td><img alt="myImage" src="/resources/uploadImages/${list.uploadfile}" width="100"height="100"></td>
			<td><span class="textlink" id="${list.id}" onclick="axiDelete('${list.id}')">Delete</span></td>
		</tr>
	</c:forEach>
<c:if test="${empty requestScope.banana}">
		<tr>
			<td colspan="10"><h2>~~ 출력 할 데이터가 없습니다. ~~</h2></td>
		</tr>
</c:if>
</table>
<hr>

</body>
</html>