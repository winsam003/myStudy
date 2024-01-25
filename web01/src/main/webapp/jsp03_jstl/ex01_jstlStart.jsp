<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL Start **</title>
</head>
<body>

<h2>** JSTL Start **</h2>

<pre><b>
=> Jstl Library 를 정의 (현재문서_Page 가 인식할 수 있도록)
	디렉티브 taglib 에 uri=".." prifix=".."

1. 출력: out Tag
=> Java의 out객체와 같은 역할, 표현식, EL역할
<c:out value="~~ Hello JSTL !!! 안녕 ~~"/>

2. 변수정의
=> set
<c:set value="홍길동" var="name" />
<c:set value="22" var="age" />

3. 변수 출력 (out_Tag, EL 둘 다 가능)
=> out_Tag
* name = <c:out value="${name}" />
* age = <c:out value="${age}"/>
* age*100 = <c:out value="${age*100}"/>
=> EL
* name = ${name}
* age = ${age}
* age*100 = ${age*100}

=> Java는 JSPL 변수와 서로 호환이 되는가?
<%-- name = <%=name%> --%>


4. 연산
<c:set value="${age+age}" var="add"/>
\${add} = ${add}
<c:set value="${name==age}" var="bool"/>
\${bool} = ${bool}
<c:set value="${age>add ? age:add}" var="max"/>
\${max} = ${max}

5. 변수삭제
=> remove
<c:remove var="add"/>
\${empty_add} = ${empty add}
\${empty_age} = ${empty age}

5.1
=> 정의하지 않은 변수를 삭제
<c:remove var="password"/>

6. 우선순위
=> JSTL 변수와 pageScope Attribute 중 뭐가 더 우선순위가 높은가?
=> 나중에 정의한 값이 적용됨
=> 나중에 정의한 값이 덮어 씌운다고 보면 됨
<%-- <% pageContext.setAttribute("name", "킹승삼"); %> --%>

* Test1) name 정의 순서: set ->  pageScope
\${name} = ${name}

* Test2) set 의 name 을 재 정의
<c:set value="킹갓승삼" var="name"/>
\${name} = ${name}


</b></pre>

</body>
</html>