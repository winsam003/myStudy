package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoService {

	// ** selectList
	List<JoDTO> selectList(); // selectList

	// ** selectOne
	JoDTO selectOne(int jno); // selectOne

	// ** insert
	int insert(JoDTO dto); // insert

	// ** update
	int update(JoDTO dto); // update

	// ** delete
	int delete(int jno); // delete

}