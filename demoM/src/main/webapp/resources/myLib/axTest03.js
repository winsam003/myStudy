/**
 * 
 */

'use strict'

// 2.3) JoDetail
// 2.3.1)  MouseOver: showJoDetail
// => jno 에 mouse를 over하게되면 joDetail을 출력 함 (content Div 에 출력) (마우스포인터 위치에)
// => request: axios, get, RESTContoller 에 "/jodetail" 요청
// => response: 성공시 JoDTO 객체
function showJoDetail(e, jno){
	// ** 마우스포인터 위치 확인
   // => 이벤트객체 활용
   //     - event객체 (이벤트핸들러 첫번째 매개변수) 가 제공
   //     - event객체 프로퍼티: type, target, preventDefault() 등 (JS 9장_Event.pptx 28p)   
   //    - e.pageX, e.pageY : 전체 Page 기준 (절대기준 좌표)
   //     - e.clientX, e.clientY : 보여지는 화면 기준-> page Scroll 시에 불편함 (내가 지금 어디를 보고있는가 기준)
	let url="/rest/jodetail/"+jno;
	let mleft=e.pageX+20;
	let mtop=e.pageY;
	
	axios.get(url
	).then(response => {
		
		
		// stringify를 하기전에 response.data를 그냥 출력하면 object로만 나오지만 아래와 같이 stringify로 data를 변환해서 console 찍으면 데이터값이 json형태로 나옴
		console.log(`** response 성공 => ${JSON.stringify(response.data)}`);
		let jo = response.data;
		
		
		let resultHtml = `
			<table>
				<tr><td>${jo.jno}</td></tr>
				<tr><td>${jo.jname}</td></tr>
				<tr><td>${jo.captain}</td></tr>
				<tr><td>${jo.project}</td></tr>
				<tr><td>${jo.slogan}</td></tr>
			</table>
			`;
			
			document.getElementById('content').innerHTML = resultHtml;
			document.getElementById('content').style.display = 'block';
			document.getElementById('content').style.visibility = 'visible';
			document.getElementById('content').style.left = mleft+'px';
			document.getElementById('content').style.top = mtop+'px';
					
	}).catch(err => {
		if(err.response.status == '502') alert(err.response.data);
		else alert("** Jno mouceOver 시스템 오류, 잠시후 다시 실행해주세요(￣ ‘i ￣;)(￣ ‘i ￣;) => " + err.message);
	});
	
	
} // showJoDetail



// 2.3.2)  MouseOut: hideJoDetail
// => 화면에 표시되어있단 content Div 가 사라짐
function hideJoDetail(){
	
	document.getElementById('content').innerHTML = '';
	document.getElementById('content').style.visibility = 'hidden';
	
	
} // hideJoDetail







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

function axiDelete(e, id){
	let url = "/rest/axiDelete/"+id;
	
	axios.delete(url
	).then(response=>{
		// => 삭제 성공
		alert(response.data);
		let del = document.getElementById(id);
		
		e.target.innerHTML = '';
		e.target.innerHTML = 'Deleted';
		e.target.style.color = 'gray';
		e.target.style.fontWeight = 'bold';
		
		e.target.classList.remove('textlink');
		e.target.removeAttribute('onclick');
		
/*		del.innerHTML = '';
		del.innerHTML = 'Deleted';
		del.style.color = 'gray';
		del.style.fontWeight = 'bold';
		
		del.classList.remove('textlink');
		del.removeAttribute('onclick');*/
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
      // ** for 간편출력 : of, in
      // => in: undifined 는 통과하고, 배열(index Return), 객체(속성명 Return)
      // => of: undifined 까지 모두출력 (순차출력과 동일), value 를 return, 
      //        ES6 에 for ~ in 의 단점을 보완 개선하여 추가됨.
      //        일반 객체에는 적용안되지만, (오류발생, 개발자모드로 확인가능)
      //         Array, String, Map, Set, function의 매개변수 객체 와
      //        이터러블 규약을 따르는 이터러블 객체 (Iterable Object) 는 적용됨
      // => 이터러블 규약
      //      내부에 Symbol.iterator (줄여서 @@iterator로 표현하기도함) 메서드가 구현되어 있어야 한다는 규약 





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