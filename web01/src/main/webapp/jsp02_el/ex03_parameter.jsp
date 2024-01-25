<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Parameter 활용 **</title>
</head>
<body>

<h2>** Parameter 활용 **</h2>

<pre><b>

=> 동질성 비교, null 확인
=> ~/ex03_parameter.jsp?id=admin&password=12345
1. 동질성 비교
* I D : ${param.id}
* password : ${param.password}

\${param.id=='admin'} => ${param.id=='admin'}
\${param.password=='12345'} => ${param.password=='12345'}


2. null(값의 존재) 확인: empty, ==
=> 쿼리스트링으로 비교 Test
	Test1: ~/ex03_parameter.jsp?id=admin&password=12345!
	Test2: ~/ex03_parameter.jsp?id=admin&password=
	Test3: ~/ex03_parameter.jsp?id=admin
	
2.1) ==null 으로 비교
=> 해당하는 Parameter가 존재하지않으면 true, 존재하지만 값이 없는 경우에는 false
\${param.id==null} => ${param.id==null}
\${param.password==null} => ${param.password==null}

2.2)  empty
=> 해당하는 Parameter가 존재하지않거나, 존재하지만 값이 없는 경우 모두 true
\${empty_param.id} => ${empty param.id}
\${empty_param.password} => ${empty param.password}


3. pageContext
=> Jsp 페이지에 대한 정보를 저장하는 객체 (pageScope)
=> 기본 객체를 return 하는 메서드를 제공
=> pageContext는 유일하게 el태그 안에서 사용가능 함
* 요청 URL: ${request.requestURL} <- 이거는 안됨
* 요청 URL: ${pageContext.request.requestURL} <- 이런식으로 써야 함
* 요청 URI: ${pageContext.request.requestURI}


</b></pre>


</body>
</html>