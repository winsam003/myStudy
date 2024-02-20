package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.MemberDTO;

import mapperInterface.MemberMapper;
import pageTest.SearchCriteria;



@Service
public class MemberServiceImpl implements MemberService {
	// ** 전역변수 선언
	
	//	@Autowired
	//	Membermapper dao;
	//  Membermapper dao = new dao();
	
	
	// ** Mybatis 적용
	// => mapper 구현제는 스프링이 실행 시 자동으로 주입해줌.
	// => 그러므로 개발자는 interface 와 xml만 구현하고 Service 와 연결해주면 됨.
	@Autowired
	MemberMapper mapper;
	
	// ** selectList
	@Override
	public List<MemberDTO> selectList() {
		return mapper.selectList();
	} // selectList
	
	// ** selectOne
	@Override
	public MemberDTO selectOne(String id) {
		return mapper.selectOne(id);
	} // selectOne
	
	// ** insert
	@Override
	public int insert(MemberDTO dto) {
		return mapper.insert(dto);
	} // insert
	
	// ** update	
	@Override
	public int update(MemberDTO dto) {
		return mapper.update(dto);
	} // update
	
	// ** delete
	@Override
	public int delete(String id) {
		return mapper.delete(id);
	} // delete
	
	@Override
	public List<MemberDTO> selectJoList(int jno) {
		// TODO Auto-generated method stub
		return mapper.selectJoList(jno);
	}
	
	@Override
	public int pwUpdate(MemberDTO dto) {
		return mapper.pwUpdate(dto);
	}
	
	@Override
	public List<MemberDTO> mPageList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return mapper.mSearchList(cri);
	}
	
	@Override
	public int totalRowsCount(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return mapper.mSearchRowsCount(cri);
	}
	
	@Override
	public List<MemberDTO> mCheckList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return mapper.mCheckList(cri);
	}
	
	@Override
	public int mCheckRowsCount(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return mapper.mCheckRowsCount(cri);
	}
	
	
} // class
