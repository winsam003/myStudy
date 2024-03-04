package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Member;

public interface MyRepository {
	
	
	List<Member> emMemberList();
	List<Member> emMemberList2();
	Member emmemberDetail(String id);
	
	
}
