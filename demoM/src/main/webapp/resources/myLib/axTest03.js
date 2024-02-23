/**
 * 
 */

'use strict'


// 1. List 출력
// 1.1) Page response
// => response 를 resultArea1 에 출력하기
// => 요청명: /member/axiMList
// => response: axMemberList.jsp
function axiMList(){
	let url = "/member/axiMList"
	
	axios.get(url
	).then(response=>{
		console.log("** response : memberList성공 **");
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err=>{
		alert(`** response: axiMList 실패 => ${err.message}`);
	})
	document.getElementById('resultArea2').innerHTML='';
} // rsJoinf
