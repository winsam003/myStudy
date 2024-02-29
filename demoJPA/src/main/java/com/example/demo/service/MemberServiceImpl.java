package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Service
@RequiredArgsConstructor		// final의 경우 선언과 동시 값을 줘야하는데 RequiredArgsConstructor 는 추후에 주입받을 수 있게 해줌
@Log4j2
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository repository;
	
	// ** selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	} // selectList
	
	// ** selectOne
	@Override
	public Member selectOne(String id) {
		
		Optional<Member> result = repository.findById(id);
		if(result.isPresent()) return result.get();
		else return null;
	} // selectOne
	
	// ** insert, update
	@Override
	public Member save(Member entity) {
		return repository.save(entity);
	} // insert
	
	// ** delete
	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	} // delete
	
	
	@Override
	public List<Member> findByJno(int jno) {
		return repository.findByJno(jno);
	}
	
	@Override
	public void updatePassword(String id, String password) {
		repository.updatePassword(id, password);
	}	
} // class
