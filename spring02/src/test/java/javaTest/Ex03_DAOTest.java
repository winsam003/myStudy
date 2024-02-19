package javaTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

// ** DAO Test 시나리오
// => Detail 정확성 
//    -> Test Data
//    -> 정확한 id 를 사용하면 not null : Green_Line
//    -> 없는 id 를 사용하면 null : Red_Line

// => Insert 정확성
//    -> 입력 가능한 Data 적용 : 1 return : Green_Line
//    -> 입력 불가능한 Data 적용 : 0 return : Red_Line

public class Ex03_DAOTest {
	
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();
	
	// 1) Detail 정확성
	
	public void detailTest() {
		String id = "jjang9";
		dto = dao.selectOne(id);
		assertNotNull(dto);
		
		System.out.println("** dto => " + dto);
	}
	
	// 2) Insert 정확성
	@Test
	public void insertTest() {
		dto.setId("zizizi");
		dto.setPassword("12345!");
		dto.setName("지지지");
		dto.setAge(20);
		dto.setJno(7);
		dto.setInfo("insert Test");
		dto.setPoint(150);
		dto.setBirthday("2000-01-01");
		dto.setRid("jjang9");
		// => 성공 : 1, 실패 : 0
		assertEquals(dao.insert(dto), 1);
		
	}
} //class