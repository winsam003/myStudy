<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Update Form **</title>
</head>
<body>
<h2>** Update Form **</h2>

	<form action="update" method="post">
		<table>
			<caption>정보수정</caption>
			<%-- passwordEncoder 적용 후 분리 됨 -> password 수정하기를 따로 만들 것임 
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="password">Password</label></th>
				<td><input type="password" id="password" name="password"
					size="20" value="${requestScope.userDetail.password}"></td>
			</tr> --%>
			

			<tr height="40">
				<th bgcolor="MediumPurple"><label for="id">I D</label></th>
				<td><input type="text" id="id" name="id" size="20"
					value="${requestScope.userDetail.id}" readonly></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="name">이름</label></th>
				<td><input type="text" id="name" name="name" size="20" value="${requestScope.userDetail.name}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="age">나이</label></th>
				<td><input type="text" id="age" name="age" size="20" value="${requestScope.userDetail.age}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="jno">조 번호</label></th>
				<td><select id="jno" name="jno">
						<option value="1" ${requestScope.userDetail.jno==1?"selected":""}>1조: Business</option>
						<option value="2" ${requestScope.userDetail.jno==2?"selected":""}>2조: static</option>
						<option value="3" ${requestScope.userDetail.jno==3?"selected":""}>3조: 칭찬해조</option>
						<option value="4" ${requestScope.userDetail.jno==4?"selected":""}>4조: 카톡으로얘기하조</option>
						<option value="7" ${requestScope.userDetail.jno==7?"selected":""}>7조: 칠면조</option>
						<option value="9" ${requestScope.userDetail.jno==9?"selected":""}>9조" 내테스트조</option>
				</select></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="info">Info</label></th>
				<td><input type="text" id="info" name="info" size="20"
					value="${requestScope.userDetail.info}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="point">point</label></th>
				<td><input type="text" id="point" name="point" size="20"
					value="${requestScope.userDetail.point}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="birthday">생년월일</label></th>
				<td><input type="date" id="birthday" name="birthday" value="${requestScope.userDetail.birthday}"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="rid">추천인</label></th>
				<td><input type="text" id="rid" name="rid" size="20" value="${requestScope.userDetail.rid}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="정보수정">&nbsp;&nbsp; 
				<input type="reset" value="재 입력"></td>
			</tr>
		</table>


	</form>

	<hr>
	<a href="pwUpdate">Password수정</a><br>
	&nbsp;
	<a href="/spring02/home">Home</a> &nbsp;
	<a href="javascript:history.go(-1)">이전으로</a>

	<hr>
<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>


</body>
</html>