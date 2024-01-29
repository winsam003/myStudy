package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;



@Service
public class MemberServiceImpl implements MemberService {
	// ** 전역변수 선언
	
	@Autowired
	MemberDAO dao;
	
	//MemberDAO dao = new MemberDAO();
	
	// ** selectList
	@Override
	public List<MemberDTO> selectList() {
		return dao.selectList();
	} // selectList
	
	// ** selectOne
	@Override
	public MemberDTO selectOne(String id) {
		return dao.selectOne(id);
	} // selectOne
	
	// ** insert
	@Override
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
	} // insert
	
	// ** update	
	@Override
	public int update(MemberDTO dto) {
		return dao.update(dto);
	} // update
	
	// ** delete
	@Override
	public int delete(String id) {
		return dao.delete(id);
	} // delete
	
	
	
} // class
