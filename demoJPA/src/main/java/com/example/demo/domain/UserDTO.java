package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	private String token;				// 나중에 토큰을 사용할 수도 있음
	private String id;
	private String username;
	private String email;
	private String password;
	
	//** 빌더 패턴(Builder pattern)
	//=> 객체 생성시 보통 생성자를 사용해서 맴버변수를 초기화 하지만,
	//   이 경우 몇 가지 단점이 있어 객체를 생성하는 별도의 builder를 두는 방법을 의미함.
	//   (예: 인자가 많은 경우 생성자로 생성할때 가독성 떨어짐, 인자의 순서를 지켜야함)
	//=> @Builder
	//	    lombok 제공, 간편하게 사용가능
	//	    이 @ 만 적용하면, 빌더를 통해 간편하게 객체 생성가능
	/*
	   UserDTO bag = UserDTO.builder()
	         .token("token")
	         .email("banana@green.com")
	         .username("홍길동")
	         .password("12345!")
	         .id("banana")
	         .build();
	*/
	
}
