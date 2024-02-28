package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Table(name = "guestbook")		// 근데 클래스명과 동일해서 생략 가능
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long gno;		// Auto_increment (primary_key 가 될 것임)
	
	@Column(length=100, nullable=false)
	private String title;
	
	@Column(length=2000, nullable=false)
	private String content;
	
	@Column(length=50, nullable=false)
	private String writer;
	
	public void changeTitle(String title) {
		this.title=title;
	}
	public void changeContent(String content) {
		this.content=content;
	}
	
	
	
	
	
}
