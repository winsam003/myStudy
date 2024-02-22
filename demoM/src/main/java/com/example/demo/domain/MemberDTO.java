package com.example.demo.domain;

import org.springframework.web.multipart.MultipartFile;

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
	private String uploadfile;	// Table 보관용 -> file의 file 이름이 들어가야 함
	
	private MultipartFile uploadfilef;
	   // => form 의 Upload_File 의 정보를 전달받기위한 컬럼
	   //   MultipartFile (interface) -> CommonsMultipartFile
	   //      -> pom.xml 에 dependency를 추가해야 함
	

	// 2) getter / setter
	// 3) toString
}
