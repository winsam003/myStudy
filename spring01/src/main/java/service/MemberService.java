package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import domain.MemberDTO;
import model.MemberDAO;


//@Component
@Service
public class MemberService {
	// ** 전역변수 선언
	
	@Autowired
	MemberDAO dao;
	
	//MemberDAO dao = new MemberDAO();
	
	// ** selectList
	public List<MemberDTO> selectList() {
		return dao.selectList();
	} // selectList
	
	// ** selectOne
	public MemberDTO selectOne(String id) {
		return dao.selectOne(id);
	} // selectOne
	
	// ** insert
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
	} // insert
	
	// ** update	
	public int update(MemberDTO dto) {
		return dao.update(dto);
	} // update
	
	// ** delete
	public int delete(String id) {
		return dao.delete(id);
	} // delete
	
	
	
} // class
