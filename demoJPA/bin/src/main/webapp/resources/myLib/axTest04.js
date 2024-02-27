'use strict'

function axPageList(){
	
	url = "/member/axPageList";
	
	axios.get(url
	).then(response=>{
		console.log("** response : memberList성공 **");
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err=>{
		alert(`** response: axiMList 실패 => ${err.message}`);
	})
	document.getElementById('resultArea2').innerHTML='';
}