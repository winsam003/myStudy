<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** BoardDetail **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
	<h2>** BoardDetail **</h2>

	<table border=1>
		<c:if test="${!empty requestScope.apple}">
			<caption>${requestScope.apple.title}</caption>
			<tr height="40">
				<th bgcolor="BlueViolet">seq</th>
				<td>${requestScope.apple.seq}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">id</th>
				<td>${requestScope.apple.id}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">title</th>
				<td>${requestScope.apple.title}</td>
			</tr>
			<tr height="200">
				<th bgcolor="BlueViolet">content</th>
				<td>${requestScope.apple.content}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">regdate</th>
				<td>${requestScope.apple.regdate}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">cnt</th>
				<td>${requestScope.apple.cnt}</td>
			</tr>
		</c:if>
		<c:if test="${empty requestScope.apple}">
			<tr>
				<th bgcolor="BlueViolet" size="10" colspan="9">출력 할 Data가 없습니다.</th>
			</tr>
		</c:if>
	</table>

	<hr>
	<br>
	<c:if test="${!empty sessionScope.loginID}">
		<a href="replyInsert?root=${apple.root}&step=${apple.step}&indent=${apple.indent}">답글등록</a>
	</c:if>
	<!-- 댓글등록을 위해 부모글의 root, step, indent 값이 필요하기 때문에
    서버로 보내주어야함 (퀴리스트링으로 작성)    -->
	<c:if test="${sessionScope.loginID eq requestScope.apple.id}">
		<a href="detail?jCode=U&seq=${requestScope.apple.seq}">글 수정</a>
		<a href="delete?seq=${requestScope.apple.seq}">글 삭제</a>
		<hr>
	</c:if>

	<a href="/spring02/home">홈으로</a>
	<a href="boardList">리스트로</a>
	<a href="javascript:history.go(-1)">뒤로가기</a>




</body>
</html>