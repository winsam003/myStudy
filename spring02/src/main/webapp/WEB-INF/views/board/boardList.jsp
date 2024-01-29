<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** BoardList **</title>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>

<h2>** BoardList **</h2>

<table border="1" style="width:100%">
	<tr bgcolor="HotPink ">
		<th>Seq</th>
		<th>Title</th>
		<th>ID</th>
		<th>RegDate</th>
		<th>조회수</th>
	</tr>
	<c:if test="${!empty requestScope.banana}">
		<c:forEach var="b" items="${requestScope.banana}">
			<tr>
				<td>${b.seq}</td>
				<td>
					<c:if test="${!empty sessionScope.loginID}">
						<a href="detail?jCode=D&seq=${b.seq}">${b.title}</a>
					</c:if>
					<c:if test="${empty sessionScope.loginID}">
						${b.title}
					</c:if>
				</td>
				<td>${b.id}</td>
				<td>${b.regdate}</td>
				<td>${b.cnt}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.banana}">
		<tr><td colspan="5">출력자료가 1건도 없습니다.</td></tr>
	</c:if>

	
</table>
<c:if test="${!empty requestScope.message}">
	<hr>
	${requestScope.message}
</c:if>

<c:if test="${!empty sessionScope.loginID}">
	<hr>
	<a href="insertForm">글쓰기</a>
</c:if>

<hr>

<a href="/spring02/home">홈으로</a>
<a href="javascript:history.go(-1)">뒤로가기</a>

</body>
</html>