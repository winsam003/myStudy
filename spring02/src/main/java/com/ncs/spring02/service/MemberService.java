package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberService {

	// ** selectList
	List<MemberDTO> selectList(); // selectList

	// ** selectOne
	MemberDTO selectOne(String id); // selectOne

	// ** insert
	int insert(MemberDTO dto); // insert

	// ** update	
	int update(MemberDTO dto); // update

	// ** pwUpdate
	int pwUpdate(MemberDTO dto);
	
	// ** delete
	int delete(String id); // delete
	
	List<MemberDTO> selectJoList(int jno);

}