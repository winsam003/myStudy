package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;

import mapperInterface.BoardMapper;
import pageTest.Criteria;

@Service
public class BoardServiceImpl implements BoardService {
	
//	@Autowired
//	BoardDao dao;
	
	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<BoardDTO> selectList() {
		return mapper.selectList();
	}
	
	
	@Override
	public BoardDTO selectOne(int seq) {
		return mapper.selectOne(seq);
	}
	
	
	@Override
	public int insert(BoardDTO dto) {
		return mapper.insert(dto);
	}
	
	@Override
	public int rinsert(BoardDTO dto) {
		// TODO Auto-generated method stub
		if(mapper.rinsert(dto)>0) {
			System.out.println("** stepUpdate Count => "+mapper.stepUpdate(dto));
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public int update(BoardDTO dto) {
		return mapper.update(dto);
	}
	
	@Override
	public int delete(BoardDTO dto) {
		return mapper.delete(dto);
	}

	
	@Override
	public List<BoardDTO> bPageList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.bPageList(cri);
	}
	
	@Override
	public int totalRowsCount(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.totalRowsCount(cri);
	}
}
