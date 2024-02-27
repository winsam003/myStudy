package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;

import pageTest.SearchCriteria;

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
	
	public List<MemberDTO> mPageList(SearchCriteria cri);
	
	public int totalRowsCount(SearchCriteria cri);
	
	// ** mCheckList
	public List<MemberDTO> mCheckList(SearchCriteria cri);
	
	// ** mCheckRowsCount
	public int mCheckRowsCount(SearchCriteria cri);
	

}