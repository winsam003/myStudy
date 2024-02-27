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
			<%-- <td><a href="#resultArea2" onclick="idbList('${list.id}')">${list.id}</a></td> --%>
			<td><span class="textlink" onclick="idbList('${list.id}')">${list.id}</span></td>
			
			<!--
				 ** idbList : id별 boardList
				 	=> 선택된 id를 function 에 전달 (매개변수로 전달)
				 	idbList('${banana}');
			 -->
			
			<td>${list.name}</td>
			<td align="center">${list.age}</td>
			<!-- ** Jo정보 Div에 출력하기 -->
        	<td align="center">
            	<span class="textlink" onmouseover="showJoDetail(event, ${list.jno})" onmouseout="hideJoDetail()">${list.jno}</span>
        	</td>
			<td>${list.info}</td>
			<td>${list.point}</td>
			<td>${list.birthday}</td>
			<td>${list.rid}</td>
			<td><img alt="myImage" src="/resources/uploadImages/${list.uploadfile}" width="100"height="100"></td>
			
			   <!--  ** Delete 기능 추가 
            => 선택된 id를 function 에 전달 (매개변수를 활용)
            => 결과는 성공/실패 여부만 전달: RESTController 로 
            => 성공 : Deleted 로 변경, onclick 이벤트 해제 
                     이를 위해 Delete Tag 를 function 에서 인식할수있어야함. 
                     
            ** function 에 이벤트객체 전달
            => 이벤트핸들러의 첫번째 매개변수에 event 라는 이름으로 전달함.
             => a Tag 와 span 사용시 e.target 값 비교
                -> a Tag : "javascript:;" 
                -> span  : [object HTMLSpanElement]          
         -->
			
			<td><span class="textlink" id="${list.id}" onclick="axiDelete(event, '${list.id}')">Delete</span></td>
		</tr>
	</c:forEach>
<c:if test="${empty requestScope.banana}">
		<tr>
			<td colspan="10"><h2>~~ 출력 할 데이터가 없습니다. ~~</h2></td>
		</tr>
</c:if>
</table>
<div id="content"></div>
<hr>

</body>
</html>