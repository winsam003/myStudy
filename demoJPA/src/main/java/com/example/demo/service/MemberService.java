package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {

	// ** selectList
	List<Member> selectList(); // selectList

	// ** selectOne
	Member selectOne(String id); // selectOne

	// ** insert, update
	Member save(Member entity); // insert

	// ** pwUpdate
	// => @Query 적용
	void updatePassword(String id, String password);
	
	// ** delete
	void deleteById(String id); // delete
	
	List<Member> findByJno(int jno);
	
	public List<MemberDTO> findMemberJoin();
	
}