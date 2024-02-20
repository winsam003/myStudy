<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MemberPageList **</title>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css">


<script>
	"use strict"
	// 1. 검색조건 입력 후 버튼 클릭
	//	=> 입력 값들을 서버로 보내서 입력값과 비교할 것임: location
	// ** self.location   
	// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
	// 2) location 객체의 메서드
	// => href, replace('...'), reload() 

	function searchDB() {
		self.location = 'mPageList' + '?currPage=1&rowsPerPage=5'
		/* +'${pageMaker.makeQuery(1)}' */
		+ '&searchType=' + document.getElementById('searchType').value
				+ '&keyword=' + document.getElementById('keyword').value;
	} // 현재출력할페이지 + 출력할페이지의게시글수 + 검색한타입 + 검색한단어

	// 2. searchType을 '전체' 로 변경하면 keyword는 clear
	function keywordClear() {
		if (document.getElementById('searchType').value == 'all') {
			document.getElementById('keyword').value = '';
		}
	}

	function checkClear() {
		let ck = document.querySelectorAll('.clear');

		for (let i = 0; i < ck.length; i++) {
			ck[i].checked = false;
		}
		return false; // reset의 기본이벤트 제거

	}
</script>

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

		<form action="mCheckList" method="get">
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
			<input type="checkbox" name="check" class="clear" value="5" ${ck5 ? 'checked' : ''}>칠면조&nbsp; 
			<input type="submit" value="Search">&nbsp; 
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
						src="/spring02/resources/uploadImages/${list.uploadfile}"
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
				<a href="${pageMaker.searchQuery(1)}">FP</a>&nbsp;
     		<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp;
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
				<a href="${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
		</c:if>
		</c:forEach>

		<!-- 3) Next, LastPage -->
		<c:choose>
			<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
      		&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;</a>
      		&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo)}">LP</a>
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