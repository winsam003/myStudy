<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JoinForm **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
<script src="/spring02/resources/myLib/inCheck.js"></script>
<script>
"use strict"

// ** ID 중복확인
// => UI 개선사항
// => 중복확인 버튼 추가
//    처음 : 중복확인버튼_enable / submit_disable
// => 중복확인 완료후 submit 이 가능하도록
//    중복확인버튼_disable / submit_enable
// => 중복확인 기능 : function idDupCheck()
//    id입력값의 무결성점검 -> id 확인요청 -> 서버로 전송 -> id , selectOne 결과 -> response: 사용가능/불가능 
// => 서버측 : 컨트롤러에 idDupCheck 요청을 처리하는 매핑메서드, view_Page(팝업창) 작성   
function idDupCheck() {
   // 1) id입력값의 무결성점검
   if ( !iCheck ) { iCheck=idCheck();
   }else {
   // 2) 서버로 id 확인요청 -> 결과는 view_Page(팝업창) 으로
      let url="idDupCheck?id="+document.getElementById('id').value;
      window.open(url,'_blank','width=400,height=300,resizable=yes,scrollbars=yes,toolbar=no,menubar=yes');
   }
} //idDupCheck

// ** 화살표 함수
// => 익명함수를 간단하게 표기
//    function(){....}  
//    () => {....}  

// ** 입력값의 무결성 점검
// => ID 중복확인, 무결성 점검

// 1) 모든항목  focusout 이벤트핸들러
//    => 개별항목 점검확인하는 boolean Type 변수 (스위치변수) 
//    => 개별항목 점검 function() 작성
// 2) submit 진행전에 점검확인
//    => 모든항목의 점검이 완료된 경우에만  submit 진행
//    => function inCheck() 로 확인
//    => submit 버튼의 onclick 리스너에 등록
//       ( submit 의 default 이벤트 고려 )
// ----------------------------------------------------
// ** 실습
// ** 입력값의 무결성 확인
// => ID 중복확인, 입력값 확인

// ** 입력값의 무결성 확인
// 1) 전역변수 정의
// => 무결성 확인 결과 상태표시를 위한 switch 변수
	let iCheck=false;	//id
	let pCheck=false;	//pw
	let p2Check=false;	//pw2
	let nCheck=false;	//name
	let aCheck=false;	//age 정수 확인
	let oCheck=false;	//point 실수 확인
	let bCheck=false;	//bithday
	

	

// 2) 개별적인 점검 코드
// => 이벤트: focusout, keydown
// => 오류가 없다면 switch 변수를 true로 변경 후 메시지를 삭제함
// => 오류가 있다면 switch 변수를 false로 변경 후 메시지를 출력함
// => 순서: Tag인식 -> Tag의 value 값 가져오기 -> 무결성 확인


onload=function(){
	document.getElementById('iMessage').innerHTML='*필수입력';
	document.getElementById('pMessage').innerHTML='*필수입력';
	document.getElementById('p2Message').innerHTML='*필수입력';
	document.getElementById('nMessage').innerHTML='*필수입력';
	document.getElementById('aMessage').innerHTML='*필수입력';
	document.getElementById('oMessage').innerHTML='*필수입력';
	document.getElementById('bMessage').innerHTML='*필수입력';
	// => window.onload : window는 생략가능
	// => body 의 Tag 들을 인식가능한 상태일때 실행 되도록하기위함. 
	
	// => ID
	// -> keydown_EnterKey 에 포커스이동 적용
    // -> 제어문자의 ascii 코드 값(참고)
    //     esc=27, EnterKey=13, Space_Bar=32
	
    document.getElementById('id').focus();
    
	document.getElementById('id').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('password').focus();
		} // if
	});
	
	
	
	document.getElementById('id').addEventListener('focusout', ()=>{ iCheck=idCheck(); });
	
	// => password
	document.getElementById('password').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('password2').focus();
		} // if
	});
	
	
	
	document.getElementById('password').addEventListener('focusout', ()=>{ pCheck=pwCheck(); });
	
	// => password2
	document.getElementById('password2').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('name').focus();
		} // if
	});
	
	
	
	document.getElementById('password2').addEventListener('focusout', ()=>{ p2Check=pw2Check(); });
	
	
	// => name
	document.getElementById('name').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('age').focus();
		} // if
	});
	
	
	
	document.getElementById('name').addEventListener('focusout', ()=>{ nCheck=nmCheck(); });
	
	
	// => age
	document.getElementById('age').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('jno').focus();
		} // if
	});
	
	
	
	document.getElementById('age').addEventListener('focusout', ()=>{ aCheck=agCheck(); });
	
	
	// => jno
	document.getElementById('jno').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('info').focus();
		} // if
	});
	
	
	// => info
	document.getElementById('info').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('point').focus();
		} // if
	});
	//////////////////////////////////////////////////////////// 대기~
	
	// => point
	document.getElementById('point').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('birthday').focus();
		} // if
	});
	document.getElementById('point').addEventListener('focusout', ()=>{ oCheck=poCheck(); });
	
	
	// => birthday
	document.getElementById('birthday').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('rid').focus();
		} // if
	});
	
	
	
	document.getElementById('birthday').addEventListener('focusout', ()=>{ bCheck=bdCheck(); });
	
	
	// => rid
	document.getElementById('rid').addEventListener('keydown', (e)=>{
		if(e.which==13){
			e.preventDefault();
			 // => form 에서는 enter 누르면 자동 submit 발생되므로 이를 제거함
			 document.getElementById('submitTag').focus();
		} // if
	});
	
} // onload




// 3) submit 실행 여부 판단 & 실행
// => 모든항목의 무결성을 확인
// => 오류가 없으면 : return true
// => 오류가 1항목이라도 있으면 : return false  


function inCheck(){
	
	if(!iCheck) {document.getElementById('iMessage').innerHTML='*필수입력, id 를 확인하세요.';}
	if(!pCheck) {document.getElementById('pMessage').innerHTML='*필수입력, password 를 확인하세요.';}
	if(!p2Check) {document.getElementById('p2Message').innerHTML='*필수입력, password2 를 확인하세요.';}
	if(!nCheck) {document.getElementById('nMessage').innerHTML='*필수입력, name 를 확인하세요.';}
	if(!aCheck) {document.getElementById('aMessage').innerHTML='*필수입력, age 를 확인하세요.';}
	if(!oCheck) {document.getElementById('oMessage').innerHTML='*필수입력, point 를 확인하세요.';}
	if(!bCheck) {document.getElementById('bMessage').innerHTML='*필수입력, birthday 를 확인하세요.';}
	
	if(iCheck && pCheck && p2Check && nCheck && aCheck && oCheck && bCheck){
		// submit 확인
		if(confirm("정말 가입하십니까? (Yes: 확인/No: 취소)")){
			// submit 진행
			return true;
		}else{
			alert("가입이 취소되었습니다!");
			return false;
		} // confirm
		
	}else{
		return false
	} // 조건 check
	
} // inCheck

</script>
</head>
<body>

	<form action="join" method="post" class="form">
		<table>
			<caption>회원가입</caption>

			<tr height="40">
				<th bgcolor="MediumPurple"><label for="id">I D</label></th>
				<td><input type="text" id="id" name="id" size="70" placeholder="영문과 숫자로 4~10글자" style="height:40px;">
					<button type="button" id="idDup" onclick="idDupCheck()">중복확인</button>
					<br><span id="iMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="password2">Password</label></th>
				<td><input type="password" id="password" name="password" size="70" placeholder="특수문자 필수" style="height:40px;">
					<br><span id="pMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="YellowGreen"><label for="password">PW 확인</label></th>
				<td><input type="password" id="password2" size="70" placeholder="PW 재입력" style="height:40px;">
					<br><span id="p2Message" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="name">이름</label></th>
				<td><input type="text" id="name" name="name" size="70" style="height:40px;">
					<br><span id="nMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="age">나이</label></th>
				<td><input type="text" id="age" name="age" size="70" style="height:40px;">
					<br><span id="aMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="jno" style="height:40px;">조 번호</label></th>
				<td><select id="jno" name="jno">
						<option value="1">1조: Business</option>
						<option value="2">2조: static</option>
						<option value="3">3조: 칭찬해조</option>
						<option value="4">4조: 카톡으로얘기하조</option>
						<option value="7">7조: 칠면조</option>
						<option value="9">9조" 내테스트조</option>
				</select>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="info">Info</label></th>
				<td><input type="text" id="info" name="info" size="70" placeholder="자기소개 & 희망사항" style="height:200px;"></td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="point">point</label></th>
				<td><input type="text" id="point" name="point" size="70" placeholder="실수 입력" style="height:40px;">
					<br><span id="oMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="birthday">생년월일</label></th>
				<td><input type="date" id="birthday" name="birthday" style="height:40px;">
					<br><span id="bMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="MediumPurple"><label for="rid">추천인</label></th>
				<td><input type="text" id="rid" name="rid" size="70" style="height:40px;"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" id="submitTag" value="회원가입" onclick="return inCheck()" disabled>&nbsp;&nbsp; 
				      <!-- => Tag 의 onclick 이벤트를 작성하고, onclick 이벤트핸들러가 가지고있던
                 기본동작인 submit 을 선택적으로 진행되도록 해준다. 
                 - submit 진행 : default (또는 return true)
                 - submit 정지 : submit 이벤트를 무효화 해야함 (return false 또는 이벤트.preventDefault())  -->
				<input type="reset" value="재 입력">
				<!-- <button type="submit" onclick="inCheck()">ButtonTest</button></td> -->
				         <!-- Button Test
            => default : form 내부에서는  submit 와  동일하게 작동됨 
                        inCheck() 의 return 값에 따라 (true 면) submit 진행됨 
            => 단, type 속성을 선택하면 (button, reset, submit 등) 속성에 맞게 실행됨
               예) button 을 선택하면 submit 은 실행되지않음   
            => Enter_Key : form 내부에서 누르면 submit 와 동일하게 작동 됨
               -->
               
			</tr>
		</table>


	</form>
				<!-- <button onclick="inCheck()">ButtonTest</button></td> -->

	<hr>
	<a href="/spring02/home">Home</a> &nbsp;
	<a href="javascript:history.go(-1)">이전으로</a>
	<hr>
<c:if test="${!empty requestScope.message}">
	${requestScope.message}
</c:if>


</body>
</html>