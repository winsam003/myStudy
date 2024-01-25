package jdbc02;

import java.util.List;

//** Controller
//=> 요청 : 요청분석 -> 담당 Service -> Service 는 DAO 
//=> 결과 : DAO -> Service -> Controller
//=> View : Controller -> View 담당 (Java:Console // Web:JSP, Html.., React)
//									지금은 Java로 하지만 나중엔 JSP.. 이런걸로 처리해야 함

public class StudentController {
	
	// ** 전역변수
	StudentService service = new StudentService();
	
	// ** View 역할 메서드 (원래는 메서드가 아님 지금은 컨트롤러가 Java로 공부하는 거니까 이렇게 하는 것임)
	// => selectList
	public void printList(List<StudentDTO> list) {
		System.out.println("** Student List **");
		// => 출력 자료 확인 존재 확인
		if(list!=null) {
			// => ** List 출력
			for(StudentDTO s : list) {
				System.out.println(s);
			}
		} else {
			System.out.println("** selectList 결과가 1건도 없음 **");
		}
	}	// selectList
	
	// => selectOne
	public void printOne(StudentDTO dto) {
		System.out.println("** Student Lint One **");
		if(dto!=null) {
			System.out.println(dto);			
		} else {
			System.out.println("** selectOne 결과가 1건도 없음 **");
		}
	}	// selectOne
	
		
	public void insert(int result) {
		if(result == 1) {
			System.out.println("insert 성공");
		} else {
			System.out.println("insert 실패");
		}
	}
	
	public void update(int result) {
		if(result == 1) {
			System.out.println("update 성공");
		} else {
			System.out.println("update 실패");
		}
	}
	
	public void delete(int result) {
		if(result == 1) {
			System.out.println("delete 성공");
		} else {
			System.out.println("delete 실패");
		}
	}
	
	
	// => join
	public void joinList(List<StudentDTO> list) {
		System.out.println("** Student List **");
		// => 출력 자료 확인 존재 확인
		if(list!=null) {
			// => ** List 출력
			for(StudentDTO s : list) {
				System.out.println(s);
			}
		} else {
			System.out.println("** selectList 결과가 1건도 없음 **");
		}
	}	// join
	
	public static void main(String[] args) {
		
		StudentController sc = new StudentController();
		
//		StudentDTO a = new StudentDTO();
		
		
		
		// selectList
		// => 요청에 해당하는 서비스를 호출
		// 	  -> 처리 결과를 view로 보내줘서 출력하도록 함
		
//		sc.printList(sc.service.selectList());
//		sc.printOne(sc.service.selectOne(16));
		
//		sc.insert(sc.service.insert(new StudentDTO("짭승삼", 20, 9, "최승삼은 최고다")));
//		sc.update(sc.service.update(new StudentDTO(24, "짭짭승삼", 30, 9, "최승삼은 최고의 최고다")));
//		sc.delete(sc.service.delete(24));
		
		
		// call by value 테스트
//		StudentDTO dto2 = new StudentDTO();
//		dto2.setSno(16);
//		sc.service.selectOne2(dto2);
//		sc.printOne(dto2);
		
		
		// Join Test
		sc.joinList(sc.service.joinList());
		
		
		// Transaction Test
//		sc.service.transactionList();
		
		
		
	} // main
}
