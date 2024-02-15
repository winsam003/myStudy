package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface BoardService {
	
	// ** bCheckList
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	
	// ** bCheckRowsCount
	public int bCheckRowsCount(SearchCriteria cri);
	
	// ** selectList
	public List<BoardDTO> bPageList(SearchCriteria cri);
	
	// ** totalRowsCount
	public int totalRowsCount(SearchCriteria cri);

	// ** selectList
	public List<BoardDTO> selectList();
	
	// ** selectOne
	public BoardDTO selectOne(int seq);
	
	// ** insert
	public int insert(BoardDTO dto);
	
	// ** replyInsert
	public int rinsert(BoardDTO dto);
	
	// ** update
	public int update(BoardDTO dto);
	
	// ** delete
	public int delete(BoardDTO dto);

}
