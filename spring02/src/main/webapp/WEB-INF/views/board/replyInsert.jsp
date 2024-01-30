<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Reply Insert **</title>
</head>
<body>
<h2>** Spring MVC2 Reply Insert **</h2>
<form action="replyInsert" method="post">
<table>
   <tr height="40"><th bgcolor="lime">I D</th>
      <td><input type="text" name="id" value="${sessionScope.loginID}" readonly size="20"></td></tr>
   <tr height="40"><th bgcolor="lime">Title</th>
      <td><input type="text" name="title" size="50"></td></tr>   
   <tr height="40"><th bgcolor="lime">Content</th>
      <td><textarea rows="5" cols="50" name="content"></textarea>
      </td></tr>
   <!-- 부모글의 root, step, indent 값이 있어야 댓글을 등록할 수 있음 
      => 그러므로 이 값들을 hidden 으로 보관했다가 서버로 전달되도록 함 -->   
   <tr height="40"><td></td>
      <td><input type="hidden" name="root" value="${boardDTO.root}">
         <input type="hidden" name="step" value="${boardDTO.step}">
         <input type="hidden" name="indent" value="${boardDTO.indent}">
      </td>
   </tr>
      
   <tr height="40"><th></th>
      <td><input type="submit" value="등록">&nbsp;&nbsp;&nbsp;
         <input type="reset" value="취소">      
      </td>
   </tr>   
</table>
</form>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<hr>
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
</body>
</html>