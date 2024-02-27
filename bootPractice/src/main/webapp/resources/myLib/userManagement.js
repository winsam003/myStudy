"use strict"

function userList(){
	let url = "/user/userList";
	
	axios.get(url).then(response => {
		console.log("List 생성 성공!");
		document.getElementById('textArea01').innerHTML=response.data;
	}).catch(err =>{
		console.log("List 생성 실패! => "+err.message);
	});
	document.getElementById('textArea01').innerHTML='';
}