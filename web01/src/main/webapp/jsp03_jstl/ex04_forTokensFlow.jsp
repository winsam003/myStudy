<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** forTokens, PageFlow (import, redirect) **</title>
</head>
<body>

<h2>1. forTokens</h2>
<pre><b>

=> 구분자로 분리된 각각의 토큰을 처리할 때 사용 됨.
=> test 1.1) 단일 구분자
<c:forTokens var="city" items="성남,용인, 서울# 부산, Paris, NewYork" delims=",">
${city}
</c:forTokens>

=> test 1.2) 복수 구분자
<c:forTokens var="city" items="성남,용인,서울#부산,Paris!NewYork" delims=",#!">
${city}
</c:forTokens>

<h2>2. import</h2><!-- 다른 문서를 추가 -->
=> directive: include -> 소스코드포함, 변수공유가능
=> jsp:include -> 웹Page포함, 변수공유 불가능
=> jstl:import -> 웹Page포함, 변수공유 불가능
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
<%-- <c:import url="/jsp01/ex01_HelloJsp.jsp"></c:import> --%>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<h2>3. Redirect</h2>
=> 이전에 했던 response.sendRedirect(); 와 동일한 기능임
=> 웹브라우저의 URL을 보면 URL이 변경 된 것으로 확인 할 수 있음
<%-- <c:redirect url="/jsp01/ex01_HelloJsp.jsp"/> --%>

<h2>4. url</h2>
=> Value 를 url로 인식 시켜줌_set 으로 정의해도 결과는 동일
=> test 4.1) a_Tag Link
<c:url value="/jsp01/ex01_HelloJsp.jsp" var="urlTest"/>
<a href="${urlTest}">urlTest</a>
<c:set value="/web01/jsp01/ex01_HelloJsp.jsp" var="SetUrl"/>
<a href="${SetUrl}">SetUrl</a>

=> test 4.2) image
<c:url value="../images/aaa.gif" var="aaa"/>
<img alt="urlTest" src="${aaa}">













</b></pre>

</body>
</html>