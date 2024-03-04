package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;


@Repository
@Transactional
public class MyRepositoryImpl implements MyRepository{

	private final EntityManager em;
	
	MyRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public List<Member> emMemberList2() {
		return em.createQuery("select m from Member m order by id asc", Member.class).getResultList();
		
		
		// => JPQL 적용
		//"select * from Member order by id asc" 으로 하면 500 오류 발생
		// antlr.NoViableAltException: unexpected token: * 토근이 없다고 콘솔에도 에러 발생 함
		// Entity 를 통해 접근하게 때문에 * 사용이 금지 됨, 앨리어스를 통해 접근해야 함.
		
	} // emMemberList2
	
	
	@Override
	public List<Member> emMemberList() {
		return em.createQuery("select m from Member m where jno < :jno order by jno", Member.class)
				.setParameter("jno", 7)
				.getResultList();
		
		
		// => Parameter 적용
		
	} // emMemberList
	
	
	@Override
	public Member emmemberDetail(String id) {
		return em.createQuery("select m from Member m where id=:id", Member.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	
	
	
}
