"use strict"

function userDetail(id){
	let url = "/rest/userDetail/"+id;		// PathVariable 형태로 요청
	let resultHTML = "";
	
	
	axios.get(url
	).then(response => {
		
		console.log(JSON.stringify(response.data));			// 응답받은 데이터를 콘솔에 출력
		
		document.getElementById('textArea01').innerHTML=''; // UserDetail을 출력하기위해 textArea01의 userList 값을 삭제
		
		let userDetail = response.data;						// userDetail 값을 할당 받고
		resultHTML = 										// table을 만들어줌
		`
		<table border=1>
			<caption>회원정보</caption>
			<tr height="40">
				<th bgcolor="BlueViolet">ID</th>
				<td>${userDetail.id}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Password</th>
				<td>${userDetail.password}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Name</th>
				<td>${userDetail.name}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Age</th>
				<td>${userDetail.age}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Jno</th>
				<td>${userDetail.jno}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Info</th>
				<td>${userDetail.info}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Point</th>
				<td>${userDetail.point}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">Birthday</th>
				<td>${userDetail.birthday}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">추천인</th>
				<td>${userDetail.rid}</td>
			</tr>
			<tr height="40">
				<th bgcolor="BlueViolet">이미지</th>
				<td><img alt="mainImage" src="/resources/uploadImages/${userDetail.uploadfile}" width="300"height="200"></td>
			</tr>
		</table>
		`;
		
		document.getElementById('textArea01').innerHTML = resultHTML;			// 위에서 만든 최종 Table을 출력
		
	}).catch(err => {
		
		alert("UserDetail 요청 중 error 발생 => " + err.message);
		
	});
	
}

// ** userList
function userList(){
	let url = "/user/userList";
	
	axios.get(url).then(response => {
		console.log("List 생성 성공!");
		document.getElementById('textArea01').innerHTML=response.data;
	}).catch(err =>{
		console.log("List 생성 실패! => "+err.message);
	});
	document.getElementById('textArea01').innerHTML='';
}//userList


