package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;

import mapperInterface.JoMapper;

//** Service
//=> 요청클래스 와 mapper클래스 사이의 연결(완충지대) 역할
//=> 요청클래스(컨트롤러) 와 mapper클래스 사이에서 변경사항이 생기더라도 서로 영향   받지않도록해주는 역할
// 결합도는 낮추고, 응집도는 높인다

//** interface 자동완성 
//=> Alt + Shift + T  
//=> 또는 마우스우클릭 PopUp Menu 의  Refactor - Extract Interface...


@Service
public class JoServiceImpl implements JoService {
	
//	@Autowired
//	JoDao dao;
	
	@Autowired
	JoMapper mapper;
	
	// ** selectList
	@Override
	public List<JoDTO> selectList(){
		return mapper.selectList();
	} // selectList
	
	// ** selectOne
	@Override
	public JoDTO selectOne(int jno){
		return mapper.selectOne(jno);
	} // selectOne
	
	// ** insert
	@Override
	public int insert(JoDTO dto){
		return mapper.insert(dto);
	} // insert
	
	// ** update
	@Override
	public int update(JoDTO dto){
		return mapper.update(dto);
	} // update
	
	// ** delete
	@Override
	public int delete(int jno){
		return mapper.delete(jno);
	} // delete
	
	
}
