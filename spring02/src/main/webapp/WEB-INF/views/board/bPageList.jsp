<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** BoardList **</title>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>

<h2>** BoardList **</h2>

<table style="width:100%">
	<tr bgcolor="HotPink ">
		<th>Seq</th>
		<th>Title</th>
		<th>ID</th>
		<th>RegDate</th>
		<th>조회수</th>
	</tr>
	<c:if test="${!empty requestScope.banana}">
		<c:forEach var="b" items="${requestScope.banana}">
			<tr>
				<td style="text-align:center">${b.seq}</td>
				<td>
				<!-- 답글 등록 후 Title 출력 전에 들여쓰기 추가 -->
				<c:if test="${b.indent>0}">
					<c:forEach begin="1" end="${b.indent}">
						<span>&nbsp;&nbsp;</span>
					</c:forEach>
					<span style="color:blue; font-weight:bold">re..</span>
				</c:if>
					<c:if test="${!empty sessionScope.loginID}">
						<a href="detail?jCode=D&seq=${b.seq}">${b.title}</a>
					</c:if>
					<c:if test="${empty sessionScope.loginID}">
						${b.title}
					</c:if>
				</td>
				<td>${b.id}</td>
				<td>${b.regdate}</td>
				<td>${b.cnt}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.banana}">
		<tr><td colspan="5">출력자료가 1건도 없습니다.</td></tr>
	</c:if>

	
</table>
<hr>
<div align="center">
<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성

     1) FirstPage, Prev  -->
     <c:choose>
     	<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
     		<a href="bPageList?currPage=1&rowsPerPage=5">FP</a>&nbsp;
     		<a href="bPageList?currPage=${pageMaker.spageNo-1}&rowsPerPage=5">&LT;</a>&nbsp;&nbsp;
     	</c:when>
     	<c:otherwise>
     		<font color="Gray">FP</font>&nbsp;
     		<font color="Gray">&LT;</font>&nbsp;&nbsp;
     	</c:otherwise>
     </c:choose>
     
<!-- 2) Display PageNo 
	=> currPage를 제외한 PageNo 만 a 태그를 적용해야 함
-->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage}">
			<a href="bPageList?currPage=${i}&rowsPerPage=5">${i}</a>&nbsp;
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage -->
      <c:choose>
      	<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
      		&nbsp;<a href="bPageList?currPage=${pageMaker.epageNo+1}&rowsPerPage=5">&GT;</a>
      		&nbsp;<a href="bPageList?currPage=${pageMaker.lastPageNo}&rowsPerPage=5">LP</a>
      	</c:when>
      	<c:otherwise>
      	    &nbsp;<font color="Gray">&GT;</font>
     		&nbsp;<font color="Gray">LP</font>
      	</c:otherwise>
      </c:choose>
      
      

</div>
<hr>
<c:if test="${!empty requestScope.message}">
	<hr>
	${requestScope.message}
</c:if>

<c:if test="${!empty sessionScope.loginID}">
	<hr>
	<a href="insertForm">글쓰기</a>
</c:if>

<hr>

<a href="/spring02/home">홈으로</a>
<a href="javascript:history.go(-1)">뒤로가기</a>

</body>
</html>