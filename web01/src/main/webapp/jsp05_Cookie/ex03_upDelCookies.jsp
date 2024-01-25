<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Update, Delete Cookies  **</title>
</head>
<body>
<h2>** Update, Delete, 유효시간 **</h2>
<pre>
=> request 의 쿠키목록을 확인해서
   수정.삭제 하려는 쿠키를 찾아,
   수정.삭제 후 재전송함.
   
=> 쿠키의 유효시간 설정
   -> setMaxAge(10) -> 초단위 (1시간: 60*60)
   -> 유효시간 0 을 주면 삭제됨.    
   -> 음수 사용시에는 오류는 없지만 삭제안됨     

=> 실습
   -> MyCookie1 value 수정후, MaxAge 10초 : 수정 & 자동삭제
   -> MyCookie2 MaxAge 0 으로 삭제 
<%
	Cookie[] ck = request.getCookies();
   if(ck!=null && ck.length>0){
	   for(Cookie c:ck) {
		  if(c.getName().equals("MyCookie1")){		// 해당 쿠키를 찾아서
			  c.setValue("BlueCom");				// 수정하고
			  c.setMaxAge(10);						// 시간도 정해주고
			  response.addCookie(c);				// 다시 보내준다
		  }else if(c.getName().equals("MyCookie2")){					// 해당 쿠키를 찾아서
			  c.setMaxAge(0);											// 시간을 0으로 정해서 삭제하고
			  response.addCookie(c);									// 다시 보내준다
		  }
	   } // for
   }else {
	   out.print("** Cookie NotFound **");
   } // if
%>   
<hr>
=> <a href="ex01_viewCookies.jsp">ViewCookies</a>
=> <a href="ex02_makeCookies.jsp">MakeCookies</a>
</body>
</html>