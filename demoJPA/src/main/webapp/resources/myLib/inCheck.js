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
	let special = /[a-z.0-9]/gi;
	let id = document.getElementById('id').value;
	
	if(id.length<4 || id.length>10){
		document.getElementById('iMessage').innerHTML='id는 4~10 글자 입니다.';
		return false;
		// => 영문과 숫자로만 입력했는지 : id 에서 영문과 숫자를 모두 '' 로 변경했을때 length 가 0 이면 OK  
	// 방법1) id를 영문과 숫자로 돼 있는 애들만 빈공간으로 바꾸고 남은 길이가 0보다 크다면? 의 방법으로 영어 숫자 외 문자 조사 가능
	}else if(id.replace(special,'').length>0){
	
	// 방법2) id가 정규식 조건에 해당하면 true 아니면 false를 주는데 true면 숫자, 영어만 쓰는 것에 만족하니까 not을 붙인 정규식 test를 함
//	}else if(!special.test(id)){
		document.getElementById('iMessage').innerHTML='id 는 영문과 숫자만 가능합니다.';
		return false;
	}else{
		document.getElementById('iMessage').innerHTML='';
		return true;
	}
} // idCheck


// 2) Password
// => Password : 길이(4~10), 영문,숫자,특수문자로 구성, 특수문자는 반드시 1개 이상 포함할것
function pwCheck(){
	let special = /[a-z.0-9.!-*.@]/gi;
	let pw = document.getElementById('password').value;
	
	// pw 글자길이 4~10
	if(pw.length<4 || pw.length>10){
		document.getElementById('pMessage').innerHTML='password는 4~10 글자 입니다.';
		return false;
	// 영문, 숫자, 특수문자로 구성
/*	}else if(pw.replace(/[a-z.0-9.!-*.@]/gi,'').length>0){*/
	}else if(pw.replace(special,'').length>0){
		document.getElementById('pMessage').innerHTML='password 는 영문과 숫자, 특수문자로 구성되어야 합니다.';
		return false;
	// 특수문자는 반드시 1개 이상 포함
	}else if(pw.replace(/[!-*.@]/gi,'').length >= pw.length){
		document.getElementById('pMessage').innerHTML='특수문자를 반드시 포함해야 합니다.';
		return false;
	}else{
		document.getElementById('pMessage').innerHTML='';
		return true;
	}
} // pwCheck


// 3) Password2
// Password2: 재입력후 Password 와 일치성 확인
function pw2Check(){
	let pw = document.getElementById('password').value;
	let pw2 = document.getElementById('password2').value;
	
	if(pw2 != pw){
		document.getElementById('p2Message').innerHTML='password와 동일해야 합니다.';
		return false;
		
	}else{
		document.getElementById('p2Message').innerHTML='';
		return true;
	}
} // pw2Check


// 4) Name
// Name : 길이(2이상), 영문 또는 한글로 만 입력
function nmCheck(){
	let name = document.getElementById('name').value;
	let special = /[ㄱ-ㅎ|가-힣.a-z]/gi;
	
	if(name.length<2){
		document.getElementById('nMessage').innerHTML='name은 2글자 이상이어야 합니다.';
		return false;
	}else if(name.replace(special, '').length>0){
		document.getElementById('nMessage').innerHTML='영문 또는 한글로만 입력해야합니다.';
		return false;
	}else{
		document.getElementById('nMessage').innerHTML='';
		return true;
	}
} // nmCheck

// 5) age
// Age: 정수의 범위  ( 숫자이면서, '.'이 없어야함 )
// => 정수의 조건: 숫자이면서 소수점이 없어야함
// => Number.isInteger(n) : n 이 정수일때만 true
//   -> 단, n 은 숫자Type 이어야함
//   -> value 속성의 값은 문자 Type 이므로 숫자화_parseInt 가 필요함 
//   -> 단, parseInt(age) 사용시 주의
//      - 실수의 경우에는 정수만 사용 (123.56 -> 123)
//      - 숫자 뒤쪽에 문자가 포함되면 앞쪽의 숫자만 가져와 정수 return (123abc -> 123)
//      - 문자로 시작하면 문자로 취급, NaN(Not a Nimber) 을 return
// => 숫자 아닌값이 있는지 확인, Number.isInteger(....) 확인
function agCheck(){
	let age = document.getElementById('age').value;
	console.log(`** parseInt(age) => ${parseInt(age)}`);
	console.log(`** Number.isInteger(age) => ${Number.isInteger(age)}`);
	console.log(`** Number.isInteger(parseInt(age)) => ${Number.isInteger(parseInt(age))}`);

	
	if(age.replace(/[^0-9]/,'').length<age.length || !Number.isInteger(parseInt(age))){
		document.getElementById('aMessage').innerHTML='정수만 입력하세요';
		return false;
	}else{
		document.getElementById('aMessage').innerHTML='';
		return true;
	}
} // agCheck

// 6) Point
// => 정수 또는 실수 허용
// => 범위: 100 ~ 10000
// => parseFloat(point)
//      -> 오류 또는 입력값이 없는 경우 NaN return
//      -> 확인 : Number.isNaN(parseFloat(point)) 
//    -> 단, 숫자로 시작하면 뒤쪽에 문자가 섞여있어도 숫자값만 사용함 ( NaN 을 return 하지않음 ) 
function poCheck(){
	let point = document.getElementById('point').value;
	console.log(`** parseFloat(point) => ${parseFloat(point)}`);
	console.log(`** Number.isNaN(point) => ${Number.isNaN(point)}`);
	console.log(`** Number.isNaN(parseFloat(point)) => ${Number.isNaN(parseFloat(point))}`);
	
	// => 숫자 아닌값이 있는지 확인해야 하고 Number.isNaN(...) 을 적용 함
	// => 여기서 주의할 점이 숫자가 아닌 값을 확인할 때 소수점은 허용해야 함
	
	if(point.replace(/[^0-9.\.]/g,'').length < point.length || Number.isNaN(parseFloat(point))){
		document.getElementById('oMessage').innerHTML='정수 또는 실수를 입력하세요.';
		return false;
	}else if(parseFloat(point)<100 || parseFloat(point)>10000){
		document.getElementById('oMessage').innerHTML='point 값이 범위(100~10000) 를 벗어납니다.';
		return false;
	}else{
		document.getElementById('oMessage').innerHTML='';
		return true;
	}
}


// 7) Birthday
function bdCheck(){
    let birthday = document.getElementById('birthday').value;

    if(birthday.length!=10){
        document.getElementById('bMessage').innerHTML='생년월일 입력 해주세요. (yyyy-mm-dd)';
        return false;
    }else{
        document.getElementById('bMessage').innerHTML='';
        return true;
    }
}






















