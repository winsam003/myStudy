package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.MemberDTO;

import pageTest.SearchCriteria;

//** Mybatis 적용
//=> CRUD 처리를 Mapper 를 이용
//=> DAO 대신 Mapper interface ->  ~Mapper.xml

//** Mybatis interface 방식으로 적용
//=> MemberDAO 대신 MemberMapper 사용
//=> MemberMapper 의 인스턴스를 스프링이 생성해주고 이를 주입받아 실행함
//(스프링이 생성해주는 동일한 타입의 클래스는 JUnit Test 로 확인가능, 추후 실습) 
//=> 단, 설정화일에 <mybatis-spring:scan base-package="mapperInterface"/> 반드시 추가해야함
//  MemberDAO의 Sql구문 처리를 mapperInterface 사용으로 MemberMapper 가 역할을 대신함

//=> SQL 구문 : xml 로작성 -> 이 화일을 Mapper 라 함 
//=> Mapper 작성규칙
// -> mapperInterface 와 패키지명, 화일명이 동일해야함
// -> 즉, Java Interface, Mapper, Mapper 의 namespace 값이 모두 동일해야함
// -> 그리고 해당하는 메서드는 Mapper 의 xml 구문의 id 속성값으로 찾음

public interface MemberMapper {
	
	@Select("select * from member where id = #{id}")
	MemberDTO selectDTO(MemberDTO dto);
	
	
	@Select("select * from member where id = #{ii} and jno=#{jno}")
	MemberDTO selectParam(@Param("ii") String id, @Param("jno") int jno);
	
	// ** selectList
	List<MemberDTO> selectList(); // selectList

	// ** selectOne
	MemberDTO selectOne(String id); // selectOne

	// ** insert
	int insert(MemberDTO dto); // insert

	// ** update	
	int update(MemberDTO dto); // update

	// ** pwUpdate
	int pwUpdate(MemberDTO dto);
	
	// ** delete
	int delete(String id); // delete
	
	List<MemberDTO> selectJoList(int jno);
	
	public List<MemberDTO> mSearchList(SearchCriteria cri);
	
	public int mSearchRowsCount(SearchCriteria cri);
	
	// ** mCheckList
	public List<MemberDTO> mCheckList(SearchCriteria cri);
	
	// ** mCheckRowsCount
	public int mCheckRowsCount(SearchCriteria cri);
	
}
