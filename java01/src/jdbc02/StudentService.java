package jdbc02;

import java.util.List;

// 컨트롤러와 DTO 사이에서 처리를 담당하는 역할
// ** Service
// => 컨트롤러의 요청에 해당하는 DAO의 메서드를 실행
// -> 컨트롤러 와 DAO의 중간에 위치하면서 이 둘의 의존성을 낮춰줌 (의존성은 필요성임 컨트롤러에서는 DTO를 사용함 그럼 우리 컨트롤러는 StudentDTO가 필요함 -> 의존관계임)
// 왜냐하면 컨트롤러는 계속 요청을 받아야함 근데 뭐 유저의 요청을 받아서 컨트롤러가 다른 코드에 역할을 받아야 하거나 DAO를 수정해야하거나 하는 경우가 생기면 곤란해짐
// 그러니 유저의 요청과 컨트롤러 사이에 컨트롤러의 요청에 해당하는 DAO의 메서드를 실행함


// 지금은 딱히 할게 없기때문에 전달만 하게씀
public class StudentService {
	// 전역변수 정의
	StudentDAO dao = new StudentDAO();
	
	// selectList
	public List<StudentDTO> selectList(){
		return dao.selectList();
	}
	
	// ** selectOne 
	public StudentDTO selectOne(int sno) {
		return dao.selectOne(sno);
	}
	
	// ** selectOne2 
	public void selectOne2(StudentDTO dto) {
		dao.selectOne2(dto);
	}
	
	// ** insert
	public int insert(StudentDTO dto) {
		return dao.insert(dto);
	}
	
	// ** update
	public int update(StudentDTO dto) {
		return dao.update(dto);
	}
	
	// ** delete // 삭제같은경우 확장을 해도 결국 지우는거니까 sno만 해도 됨
	public int delete(int sno) {
		return dao.delete(sno);
	}
	
	// join
	public List<StudentDTO> joinList(){
		return dao.joinlist();
	}
	
	// Transaction Test
	public void transactionList() {
		dao.transactionTest();
	}
}
