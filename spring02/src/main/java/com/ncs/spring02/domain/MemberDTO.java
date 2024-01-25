package com.ncs.spring02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ** DTO
// => private 멤버변수
// => getter / setter
// => toString

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {
	// 1) private 멤버변수
	private String id; // primary_key
	private String password; // not null
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String birthday;
	private String rid; // 추천인

	// 2) getter / setter
	// 3) toString
}
