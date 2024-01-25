<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- 페이지 디렉티브 부분 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Hello Jsp **</title>
</head>
<body>
<h2>** Hello Jsp **</h2>
<h3>~~ 안녕 하세요 ~~</h3>
<h3>* Jsp 장점 : View 간편</h3>
<h3>* Jsp 단점 : JAVA코드가 조금 불편</h3>
<hr>

<h3>=> Java Code</h3>
<pre>
1) Scriptlet : 자바코드
1) Expression : 표현식 (출력문)
1) Declaration : 선언부 (메서드 등)
</pre>
=> Declaration : 선언부 (메서드 등)<br><br>
<%!	// Declaration : 선언부
	public int multiply(int a, int b){
		return a*b;
	}
	String name="갓승삼";
	int i=10;
	int j=20;
%>
=> Expression : 표현식(출력문) <br>
=> multiply(4, 5) 의 결과는 <%= multiply(4, 5) %><br> <!-- 세미콜론 안찍음! 주의!! -->
=> 변수출력: i=<%=i%>, j=<%=j%>, name=<%=name%><br>
=> 연산적용: i+j=<%=i+j%><br><br>

=> Scriptlet : 자바코드<br>
<%
	// multiply의 결과를 짝/홀 구분해서 출력하기
	if(multiply(i,j)%2==0){%>
		multiply(i,j) 의 결과는 짝수<%=multiply(i,j)%> 입니다.<br>
<%	}else {	%>
		multiply(i,j) 의 결과는 홀수<%=multiply(i,j)%> 입니다.<br>
<%	}
%>








</body>
</html>