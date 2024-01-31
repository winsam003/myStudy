/**
** 입력값의 무결성 확인
** member 무결성 확인사항
// ID : 길이(4~10), 영문자,숫자 로만 구성
// Password : 길이(4~10), 영문,숫자,특수문자로 구성, 특수문자는 반드시 1개 이상 포함할것
// Password2: 재입력후 Password 와 일치성 확인
// Name : 길이(2이상), 영문 또는 한글로 만 입력
// Age: 정수의 범위  ( 숫자이면서, '.'이 없어야함 )  
// BirthDay : 입력 여부 확인  ( length == 10 )
// Point : 실수 ( 구간설정 100 ~ 10000 까지만 가능 )
// Jno : select 를 이용 (X)
// Info : (X)

** 작성 규칙
   => JavaScript function 으로 정의 하고 
      결과를 true or false 로 return
   => 정규식을 활용한다.
   
** match Test
   => 아래 조건에 true -> not (!)  match 적용해보면
   => 정확하지 않으므로 (부적절, replace 를 사용)
        ...       
        } else if (!id.match(/[a-z.0-9]/gi)) {
            alert(' ID는 영문자와 숫자로만 입력하세요. !!!')
            return false;
        }    
 */

"use strict"
// 1) ID
// => ID : 길이(4~10), 영문자,숫자 로만 구성
function idCheck(){
	let id = document.getElementById('id').value;
	
	if(id.length<4 || id.length>10){
		document.getElementById('iMessage').innerHTML='id는 4~10 글자 입니다.';
		return false;
		// => 영문과 숫자로만 입력했는지 : id 에서 영문과 숫자를 모두 '' 로 변경했을때 length 가 0 이면 OK  
	}else if(id.replace(/[a-z.0-9]/gi,'').length>0){
		document.getElementById('iMessage').innerHTML='id 는 영문과 숫자만 가능합니다.';
		return false;
	}else{
		document.getElementById('iMessage').innerHTML='';
		return true;
	}
}


// 2) Password
// => Password : 길이(4~10), 영문,숫자,특수문자로 구성, 특수문자는 반드시 1개 이상 포함할것
function pwCheck(){
	let pw = document.getElementById('password').value;
	
	if(pw.length<4 || pw.length>10){
		document.getElementById('pMessage').innerHTML='password는 4~10 글자 입니다.';
		return false;
	}else if(pw.replace(/[a-z.0-9.!-*.@]/gi,'').length>0){
		document.getElementById('pMessage').innerHTML='password 는 영문과 숫자, 특수문자로 구성되어야 합니다.';
		return false;
	}else if(/[!-*.@]/g.test(pw)){
		document.getElementById('pMessage').innerHTML='특수문자를 반드시 포함해야 합니다.';
		return false;
	}else{
		document.getElementById('pMessage').innerHTML='';
		return true;
	}
}


// 3) Password2
function pw2Check(){
	let pw2 = document.getElementById('password2').value;
	
	return true;
}


// 4) Name
function nmCheck(){
	let name = document.getElementById('name').value;
	
	return true;
}


// 5) age
function agCheck(){
	let age = document.getElementById('age').value;
	
	return true;
}

// 6) point
function poCheck(){
	let point = document.getElementById('point').value;
	
	return true;
}


// 7) Birthday
function bdCheck(){
	let birthday = document.getElementById('birthday').value;
	
	return true;
}





















