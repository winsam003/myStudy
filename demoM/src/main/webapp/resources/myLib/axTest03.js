/**
 * 
 */

'use strict'

// 1.2) idbList (id 별 boardList)
// => RESTContoller, PathVariable 처리, List_Data response
// => Server : service, Sql 구문이 필요
// => request : id 를 path 로 전송
// => response  
// 		-> 성공 : 반복문을 이용해서 Table로 List 출력하기
//			-> Data의 유/무 : Server 에서 502로 처리하도록 가정 함
// 		-> 실패 : 실패 resultArea2 clear처리, alert으로 메세지 출력하기



// => 요청명: "/rest/axiDelete/+id"
// => response: 성공/실패 여부만 전달 받음 그러므로 RESTController를 사용
// => 성공: Deleted 로 변경, onclick 이벤트 해제

function axiDelete(id){
	let url = "/rest/axiDelete/"+id;
	
	axios.delete(url
	).then(response=>{
		// => 삭제 성공
		alert(response.data);
		let del = document.getElementById(id);
		
		del.innerHTML = '';
		del.innerHTML = 'Deleted';
		del.style.color = 'gray';
		del.style.fontWeight = 'bold';
		
		del.classList.remove('textlink');
		del.removeAttribute('onclick');
	}).catch(err => {
		if(err.response.status == '502') alert(err.response.data);
		else alert("** 시스템 오류, 잠시후 다시 실행해주세요(￣ ‘i ￣;)(￣ ‘i ￣;) => " + err.message);
	});
}




function idbList(id) {
	let url = "/rest/idbList/" + id;

	axios.get(url
	).then(response => {
		alert("** resultArea2 에 List 작성 **");

		let listData = response.data;
		let resultHtml = `
		<table style="width:100%">
			<tr bgcolor="HotPink ">
				<th>Seq</th>
				<th>Title</th>
				<th>ID</th>
				<th>RegDate</th>
				<th>조회수</th>
			</tr>
		`;
		for(let b of listData){
			resultHtml += `
			<tr>
				<td>
					<td>${b.seq}</td>
					<td>${b.title}</td>
					<td>${b.id}</td>
					<td>${b.regdate}</td>
					<td>${b.cnt}</td>
			</tr>
			`;
		
		}
		resultHtml += `</table>`;
		document.getElementById('resultArea2').innerHTML = resultHtml;
		
	}).catch(err => {
		document.getElementById('resultArea2').innerHTML = "";

		if (err.response.status == '502') document.getElementById('resultArea2').innerHTML=err.response.data;
		else alert("** 시스템 오류, 잠시후 다시 실행해주세요(￣ ‘i ￣;)(￣ ‘i ￣;) => " + err.message);

	});
}





function CheckDB() {
	let checkData = document.querySelectorAll(".clear");
	let checkQ = '';
	/*for(let i=0 ; i<checkData.length ; i++){
		if(checkData[i].checked){
			checkQ = checkQ+"&check="+checkData[i].value
		}
	}*/
	checkData.forEach(check => {
		if (check.checked)
			checkQ += "&check=" + check.value;
	})
	/* 순회 메서드 find, reduce, forEach 등 많이 알아두자 */

	let url = 'axmcheck' + '?currPage=1&rowsPerPage=5'
		+ checkQ;
	axiMListCri(url); // axios 호출
} // searchDB()

// 1. List 출력
// 1.1) Page response
// => response 를 resultArea1 에 출력하기
// => 요청명: /member/axiMList
// => response: axMemberList.jsp
function axiMList() {
	let url = "/member/axiMList"

	axios.get(url
	).then(response => {
		console.log("** response : memberList성공 **");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		alert(`** response: axiMList 실패 => ${err.message}`);
	})
	document.getElementById('resultArea2').innerHTML = '';
} // rsJoinf

function keywordClear() {
	if (document.getElementById('searchType').value == 'all') {
		document.getElementById('keyword').value = '';
	}
}

function axiMListCri(url) {
	url = "/member/" + url;
	alert(`axiMListCri url=${url}`);
	axios.get(url
	).then(response => {
		console.log("** response 성공 **");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		document.getElementById('resultArea1').innerHTML = "axiMListCri 실패 =>" + err.message;
	})
	document.getElementById('resultArea2').innerHTML = "";
}

// 1. 검색조건 입력 후 버튼 클릭
//	=> 입력 값들을 서버로 보내서 입력값과 비교할 것임: location
// ** self.location   
// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
// 2) location 객체의 메서드
// => href, replace('...'), reload() 

function searchDB() {
	let url = 'axmcri' + '?currPage=1&rowsPerPage=5'
		/* +'${pageMaker.makeQuery(1)}' */
		+ '&searchType=' + document.getElementById('searchType').value
		+ '&keyword=' + document.getElementById('keyword').value;

	axiMListCri(url);
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

//** 검색 & 페이징 포함한 요청의 Ajax 처리
// => Ajax 요청 function 작성, url 을 매개변수로 전달 : axiMListCri(url) 
// => Page 요청 : aTag -> span 으로 변경하고 function 으로 처리 
// => Check 검색은 submit 을 사용하기 때문에 적용하지 않음(주석처리)

// => Ajax 처리시에는 문서내부의 function이 인식 안되므로
//    searchDB(), keywordClear() 모두 axTest03.js 에 작성  