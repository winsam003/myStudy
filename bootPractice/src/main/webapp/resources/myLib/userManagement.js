"use strict"

// ** logout
function logout(){
	let url = "/user/logout";
	
	axios.get(url
	).then(response => {
		alert("로그아웃 되었습니다.");
		location.reload();
	}).catch(err => {
		alert("시스템 오류, 관리자에게 문의하세요. ["+err.response.status+"]");
		console.log(err.message);
	});
	
} // logout


// ** login
function login(){
	
	
	let url = "/rest/login"
	axios({
		url:url,
		method:'post',
		headers:{'content-type': 'application/json'},
		data:{
			id: document.getElementById('id').value,
			password: document.getElementById('password').value
		}
	}).then(response =>{
		alert(response.data);
		location.reload();
	}).catch(err => {
		alert(err.response.data + " ["+err.response.status+"]");		
	});
	
	
	
}// login



// ** loginForm
function loginForm(){
	let resultHTML=
	`
		<table>
			<caption style="font-weight:bold">Login</caption>
			<tr height="40">
				<td bgcolor="indogo"><label for="id">I D</label></td>
				<td bgcolor="indogo"><input type="text" id="id" name="id" size="20">
				<br><span id="iMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<td bgcolor="indogo"><label for="password">Password&nbsp;</label></td>
				<td bgcolor="indogo"><input type="password" id="password" name="password" size="20">
					<br><span id="pMessage" class="eMessage"></span>
				</td>
			</tr>		
			<tr><td></td>
				<td><input type="submit" id="submitTag" value="로그인" onclick="login()">&nbsp;&nbsp;
				<input type="reset" value="취소"></td>
			</tr>
		</table>
	`;
	
	if(document.getElementById('textArea01').innerHTML==''){
		document.getElementById('textArea01').innerHTML=resultHTML;
	}else{
		document.getElementById('textArea01').innerHTML='';
	}
	
	
}// loginForm


// ** userDetail
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
	
} // userDetail

// ** userList
function userList(){
	if(document.getElementById('textArea01').innerHTML==''){
		let url = "/user/userList";

		axios.get(url).then(response => {
			console.log("List 생성 성공!");
			document.getElementById('textArea01').innerHTML = response.data;
		}).catch(err => {
			console.log("List 생성 실패! => " + err.message);
		});
	}else{
		document.getElementById('textArea01').innerHTML='';
	}
	

}//userList


