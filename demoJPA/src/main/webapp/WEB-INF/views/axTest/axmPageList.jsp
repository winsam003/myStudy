<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MemberPageList **</title>
<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle.css">


</head>
<body>

	<h2>** MemberPageList **</h2>

	<div id="searchBar">
		<select name="searchType" id="searchType" onchange="keywordClear()">
			<option value="all"
				${pageMaker.cri.searchType == 'all' ? 'selected' : ''}>전체</option>
			<option value="id"
				${pageMaker.cri.searchType == 'title' ? 'selected' : ''}>ID</option>
			<option value="name"
				${pageMaker.cri.searchType == 'content' ? 'selected' : ''}>Name</option>
			<option value="age"
				${pageMaker.cri.searchType == 'id' ? 'selected' : ''}>Age</option>
			<option value="birthday"
				${pageMaker.cri.searchType == 'regdate' ? 'selected' : ''}>Birthday</option>
			<option value="info"
				${pageMaker.cri.searchType == 'tc' ? 'selected' : ''}>Info</option>
			<option value="rid"
				${pageMaker.cri.searchType == 'tc' ? 'selected' : ''}>추천인</option>
		</select> <input type="text" name="keyword" id="keyword"
			value="${pageMaker.cri.keyword}">
		<button id="SearchBtn" onclick="searchDB()">Search</button>

		<hr>

 		<form action="axmcheck" method="get">
			<b>ID : </b>
			<!-- check 의 선택한 값 유지를 위한 코드 -->
			<c:set var="ck1" value="false" />
			<c:set var="ck2" value="false" />
			<c:set var="ck3" value="false" />
			<c:set var="ck4" value="false" />
			<c:set var="ck5" value="false" />
			<c:forEach var="jno" items="${pageMaker.cri.check}">
				<c:if test="${jno=='1'}">
					<c:set var="ck1" value="true" />
				</c:if>
				<c:if test="${jno=='2'}">
					<c:set var="ck2" value="true" />
				</c:if>
				<c:if test="${jno=='3'}">
					<c:set var="ck3" value="true" />
				</c:if>
				<c:if test="${jno=='4'}">
					<c:set var="ck4" value="true" />
				</c:if>
				<c:if test="${jno=='5'}">
					<c:set var="ck5" value="true" />
				</c:if>
			</c:forEach>
			<!-- --------------------------------- -->
			<input type="checkbox" name="check" class="clear" value="1" ${ck1 ? 'checked' : ''}>Bussiness&nbsp; 
			<input type="checkbox" name="check" class="clear" value="2" ${ck2 ? 'checked' : ''}>static&nbsp; 
			<input type="checkbox" name="check" class="clear" value="3" ${ck3 ? 'checked' : ''}>칭찬해조&nbsp;
			<input type="checkbox" name="check" class="clear" value="4" ${ck4 ? 'checked' : ''}>카톡으로얘기하조&nbsp; 
			<input type="checkbox" name="check" class="clear" value="7" ${ck5 ? 'checked' : ''}>칠면조&nbsp; 
			<button type="button" onclick="CheckDB()">Search</button>&nbsp; 
			<input type="reset" value="Clear" onclick="return checkClear()"><br>
 		</form> 

		<hr>
	</div>



	<table style="width: 100%">
		<tr bgcolor="HotPink ">
			<th>ID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Jno</th>
			<th>Info</th>
			<th>Point</th>
			<th>Birthday</th>
			<th>추천인</th>
			<th>Image</th>
		</tr>

		<c:if test="${!empty requestScope.banana}">
			<c:forEach var="list" items="${requestScope.banana}">
				<tr>
					<td>${list.id}</td>
					<td>${list.name}</td>
					<td>${list.age}</td>
					<td>${list.jno}</td>
					<td>${list.info}</td>
					<td>${list.point}</td>
					<td>${list.birthday}</td>
					<td>${list.rid}</td>
					<td><img alt="myImage"
						src="/resources/uploadImages/${list.uploadfile}"
						width="100" height="100"></td>
					<%-- <td>${list.password}</td> --%>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.banana}">
			<tr>
				<td colspan="9">출력자료가 1건도 없습니다.</td>
			</tr>
		</c:if>

	</table>
	<hr>
	<div align="center">
		<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성
   => ver02: makeQuery 메서드 -> searchQuery로 바꿔줌

     1) FirstPage, Prev  
     * Old 버전
          	<a href="bPageList?currPage=1&rowsPerPage=5">FP</a>&nbsp;
     		<a href="bPageList?currPage=${pageMaker.spageNo-1}&rowsPerPage=5">&LT;</a>&nbsp;&nbsp;
     
     -->
		<c:choose>
			<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
				<!-- ver01: makeQuery 메서드 사용 -->
				<%--      		<a href="bPageList${pageMaker.makeQuery(1)}">FP</a>&nbsp;
     		<a href="bPageList${pageMaker.makeQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp; --%>
				<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(1)}')">FP</span>&nbsp;
	     		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.spageNo-1)}')">&LT;</span>&nbsp;
     	</c:when>
			<c:otherwise>
				<font color="Gray">FP</font>&nbsp;
     		<font color="Gray">&LT;</font>&nbsp;&nbsp;
     	</c:otherwise>
		</c:choose>

		<!-- 2) Display PageNo 
	=> currPage를 제외한 PageNo 만 a 태그를 적용해야 함
-->
		<c:forEach var="i" begin="${pageMaker.spageNo}"
			end="${pageMaker.epageNo}">
			<c:if test="${i==pageMaker.cri.currPage}">
				<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
			<c:if test="${i!=pageMaker.cri.currPage}">
				<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(i)}')">${i}</span>&nbsp;
		</c:if>
		</c:forEach>

		<!-- 3) Next, LastPage -->
		<c:choose>
			<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
      		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.epageNo+1)}')">&GT;</span>&nbsp;
      		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.lastPageNo)}')">LP</span>&nbsp;
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

	<a href="/home">홈으로</a>
	<a href="javascript:history.go(-1)">뒤로가기</a>

</body>
</html>