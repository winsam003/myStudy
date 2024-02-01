<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** LoginForm **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
<script src="/spring02/resources/myLib/inCheck.js"></script>
<script>
'use strict'

	let iCheck=false;	//id
	let pCheck=false;	//pw
	
	onload=function(){
	    document.getElementById('id').focus();
	    
		document.getElementById('id').addEventListener('keydown', (e)=>{
			if(e.which==13){
				e.preventDefault();
				 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
				 document.getElementById('password').focus();
			} // if
		});
		
		
		
		document.getElementById('id').addEventListener('focusout', ()=>{ iCheck=idCheck(); });
		
		// => password
		document.getElementById('password').addEventListener('keydown', (e)=>{
			if(e.which==13){
				//e.preventDefault();
				 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
				 document.getElementById('myform').submit();
			} // if
		});
		
		document.getElementById('password').addEventListener('focusout', ()=>{ pCheck=pwCheck(); });
	}
	
	function inCheck(){
		
		if(!iCheck) {document.getElementById('iMessage').innerHTML='*필수입력, id 를 확인하세요.';}
		if(!pCheck) {document.getElementById('pMessage').innerHTML='*필수입력, password 를 확인하세요.';}
		
		if(iCheck && pCheck){
			return true;
		}else{
			return false
		} 
		
	} // inCheck
</script>

</head>
<body>

<form action="login" method="post" id="myform">
	<table>
		<caption style="font-weight:bold">Login</caption>
		<tr height="40">
			<td bgcolor="indogo"><label for="id">I D</label></td>
			<td bgcolor="indogo"><input type="text" id="id" name="id" size="20">
				<br><span id="iMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<td bgcolor="indogo"><label for="password">Password&nbsp;</label></td>
			<td bgcolor="indogo"><input type="password" id="password" name="password" size="20">
				<br><span id="pMessage" class="eMessage"></span>
			</td>
		</tr>		
		<tr><td></td>
			<td><input type="submit" id="submitTag" value="로그인" onclick="return inCheck()">&nbsp;&nbsp;
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