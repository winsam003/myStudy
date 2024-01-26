<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JoinForm **</title>
</head>
<body>

	<form action="join" method="post">
		<table>
			<caption>회원가입</caption>

			<tr height="40">
				<th bgcolor="MediumPurple"><label for="id">I D</label></th>
				<td><input type="text" id="id" name="id" size="20"
					placeholder="영문과 숫자로 4~10글자"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="password">Password</label></th>
				<td><input type="password" id="password" name="password"
					size="20" placeholder="특수문자 필수"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="name">이름</label></th>
				<td><input type="text" id="name" name="name" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="age">나이</label></th>
				<td><input type="text" id="age" name="age" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="jno">조 번호</label></th>
				<td><select id="jno" name="jno">
						<option value="1">1조: Business</option>
						<option value="2">2조: static</option>
						<option value="3">3조: 칭찬해조</option>
						<option value="4">4조: 카톡으로얘기하조</option>
						<option value="7">7조: 칠면조</option>
						<option value="9">9조" 내테스트조</option>
				</select></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="info">Info</label></th>
				<td><input type="text" id="info" name="info" size="20"
					placeholder="자기소개 & 희망사항"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="point">point</label></th>
				<td><input type="text" id="point" name="point" size="20"
					placeholder="실수 입력"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="birthday">생년월일</label></th>
				<td><input type="date" id="birthday" name="birthday"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="rid">추천인</label></th>
				<td><input type="text" id="rid" name="rid" size="20"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="회원가입">&nbsp;&nbsp; 
				<input type="reset" value="재 입력"></td>
			</tr>
		</table>


	</form>

	<hr>
	<a href="/spring02/home">Home</a> &nbsp;
	<a href="javascript:history.go(-1)">이전으로</a>
	<hr>
<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>


</body>
</html>