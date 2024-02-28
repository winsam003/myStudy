package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

//** BaseEntity
//=> 자료 등록시간, 수정시간 등 자동으로 추가되고 변경되는 값들을 자동으로 처리하기위한 BaseEntity 클래스 
//=> 추상클래스로 작성     
//=> @MappedSuperclass: 테이블로 생성되지않음
//=> @EntityListeners : 엔티티객체의 변화를 감지하는 리스너설정 (AuditingEntityListener.class 가 담당)
// AuditingEntityListener 를 활성화 시키기 위해서는 
//  DemoJpaApplication.java 에 @EnableJpaAuditing 설정추가해야함.

@MappedSuperclass	// 이 객체는 테이블이 아닙니다! 라는 것을 알려줌
@EntityListeners(value={ AuditingEntityListener.class }) // 자동으로 감지해서 엔티티의 객체의 변화를 감지해줌 Demo에서 Enable을 해줘야 class를 사용할 수 있음
@Getter
abstract class BaseEntity {
	
	@CreatedDate // 하나의 컬럼이 만들어질때의 시간
	@Column(name="regdate", updatable = false)
	private LocalDateTime regDate;			// 작성시간
	
	@LastModifiedDate // 수정되었을 때의 시간
	@Column(name="modDate")
	private LocalDateTime modDate;			// 수정시간
	
	
}
