package springMybatis;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

import mapperInterface.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_interfaceMapper {
	
	   // ** interface Mapper 설정
	   // => Controller -> Service -> (DAO) -> interface Mapper : xml의 sql 구문을 이용해서 DB처리
	
	@Autowired
	MemberMapper mapper;
	
	@Autowired
	MemberDAO dao;
	
	   // => 성공: MemberMapper mapper = new MemberMapper 구현객체 ;
	   //    -> 구현객체 생성 부터는 Spring과 Mybatis가 규칙에 의해 처리해줌 
	   //    -> 규칙: 패키지 명과 클래스명을 interface , mapper xml, xml의 namespace 모두 동일하게 해줌.
	   //           이를 위한 경로 설정 
	   //           <mybatis-spring:scan base-package="mapperInterface"/> 
	@Autowired
	MemberDTO dto;
	
	// ** mapper 동작 Test
	
	   // => getClass().getName() : 실제동작하는 클래스(MemberMapper의 구현객체) 의 이름 확인가능
	   //    이를 통해 우리는 Mapper interface 만 작성했지만, 
	   //    내부적으로는 동일한 타입의 클래스가 만들어졌음을 알 수 있다.  
	public void mapperTest() {
		assertNotNull(mapper);
		System.out.println("** MemberMapper구현객체 => "+mapper.getClass().getName());
		System.out.println("** dto 인스턴스의 동작하는 클래스명 => "+dto.getClass().getName());
	}
	
	
	// ** mapper 매서드 Test
	// => Mybatis 사용 시 주의사항
	//	-> 참조형 매개변수 사용 시 매개변수 주소를 공유하지 않음
	//	-> 무슨말이냐면 selectOne이 예를들어 selectDTO(MemberDTO dto) 형식
	//	-> Mybatis는 매개변수는 1개만 사용가능함 그래서 주로 객체형으로 함
	//	-> 근데 사실 복수의 매개변수를 쓰려면 @Param을 사용할 순 있음
	//	-> xml 대신 @ 으로 Sql 구현 가능함
	
	public void selectOne() {
		String id="bamboo7";					// 존재하는 아이디
//		String id="black1";						// 존재하지않는 아이디
		
		dto=mapper.selectOne(id);
		System.out.println("** selectOne => "+dto);
		assertNotNull(dto);	// null인지 아닌지 체크해서 아이디가 존재하는지 확인
		
	}
	
	
	// 2) selectDTO(MemberDTO dto) 형식
	// MemberDAO 와 Mybatis 비교

	public void selectDTO() {
		
		// 1) MemberDAO 사용 시
		MemberDTO dto1 = new MemberDTO();
		dto1.setId("bamboo7");
		dao.selectDTO(dto1);
		System.out.println("** selectDTO => "+dto1);
		
		// 2) Mybatis 사용 시
		MemberDTO dto2 = new MemberDTO();
		
		// mapper.selectDTO(dto);
		dto2.setId("bamboo7");
		dto=mapper.selectDTO(dto2);
		System.out.println("** selectDTO => "+dto2);
	}
	
	

	// 3) 복수의 매개변수 사용 Test
	// => Mybatis 에서 2개 이상의 매개변수 처리
	// => Mapper interface 에서 @Param 적용 가능
	// => selectPram으로 id, jno 을 사용해서 해보겠음

	@Test
	public void paramTest() {
		dto = mapper.selectParam("bamboo7", 3);
		System.out.println("** Mybatis @Param Test => "+dto);
		assertNotNull(dto);
		
	}
	
	
	
	
} // class
