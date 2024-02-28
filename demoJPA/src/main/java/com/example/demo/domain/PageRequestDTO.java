package com.example.demo.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//** JPA Paging & Sort
//=> https://bnzn2426.tistory.com/135

//** PageList 요청 처리 DTO
//=> 재사용 가능 구조: 다양한 Table에 적용가능
//=> JPA 에서 사용하는 Pageable Type 객체 생성을 목표로함.

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
	
	
	
	private int page;			// 출력해야하는 페이지
	private int size;			// 페이지 당 몇 개 출력할지
	private String type;		// 검색 타입
	private String keyword; 	// 검색 키워드
	
	public PageRequestDTO() {
		this.page = 1;			// 첫 페이지는 1 페이지임
		this.size = 5;			// 출력 개수는 5개로 임시로 정하겠음
	}
	
	public Pageable getPageable(Sort sort) {
		// => Pageable
		// 스프링 데이터 JPA에서 제공하는 강력한 페이징 & 정렬 기능을 정의한 interface

		// => PageRequest ( Pageable interface의 구현클래스 )
		// - Sort type 을 인자로 전달 할 수 있음
		// - Sort 객체는 한개 이상의 컬럼값을 이용해서 정렬을 지정할수 있음.
		
		// => of: 페이징 위한 데이터의 조건을 적어주는 메서드
		// => JPA 에서는 pageNo 가 0 부터 시작하기 때문에 page-1
		// 단, application.properties에서 변경가능
		// # pageable : 1페이지부터 시작하도록 변경
		// spring.data.web.pageable.one-indexed-parameters=true

		// => sort: 필요시 사용을 위함.
		
		return PageRequest.of(page-1, size, sort);
		
	}//getPageable
	
	
	
	
	
}
