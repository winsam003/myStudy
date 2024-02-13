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

	<form action="update" method="post" enctype="multipart/form-data">
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
			
			<!-- Image Update 추가 
         => form Tag : method, enctype 확인
         => new Image 를 선택하는 경우 -> uploadfilef 사용
         => new Image 를 선택하지않는 경우 
            -> 본래 Image 를 사용 -> uploadfile 값이 필요함
   -->   
			
			<tr height="40">
				<td bgcolor="MediumPurple"><labe for="uploadfilef">Image</labe></td>
				<td><img alt="MyImage" width="80" height="100" src="/spring02/resources/uploadImages/${requestScope.userDetail.uploadfile}" class="select_img">
					<br>
					<input type="hidden" name="uploadfile" id="uploadfile" value="${requestScope.userDetail.uploadfile}">
					<input type="file" name="uploadfilef" id="uploadfilef" size="20">
				</td>

				<script>
					document.getElementById('uploadfilef').onchange = function(
							e) {
						if (this.files && this.files[0]) {
							let reader = new FileReader;
							reader.readAsDataURL(this.files[0]);
							reader.onload = function(e) {
								// => jQuery를 사용하지 않는경우 
								document.getElementsByClassName('select_img')[0].src = e.target.result;

								//$(".select_img").attr("src", e.target.result)
								//            .width(70).height(90); 
							} // onload_function
						} // if   
					}; //change
				</script>



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