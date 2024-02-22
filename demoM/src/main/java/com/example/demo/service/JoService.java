package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.JoDTO;


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