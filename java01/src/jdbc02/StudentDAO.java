package jdbc02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC01.DBConnection;


//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
// Create(Insert), Read(selectList, selectOne), Update, Detete

// ** 첫번째 예제 DBStart 와 ~~~DAO의 차이점
// => DAO는 결과를 직접 처리하지않고 요청자에게 결과를 제공해야 함
// => 즉, 메서드 역할별로 처리 결과를 리턴해줘야 함
// => 그러므로 특히 select 결과를 잘 전달하기위해 결과를 객체화 해야 함


public class StudentDAO {
	// ** JDBC API 에 정의된 필요한 객체들을 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** Join TEST
	// => sno, name, age, jno, jname, project, captain, 조장이름 을 출력하기
	// => JoDTO 작성, joinList() 메서드 작성(Contoller, Service, DAO)
	// => 
	

	
	// ** selectList
	public List<StudentDTO> selectList(){
		sql = "select * from student";
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();
			// => 결과의 존재 여부를 확인 먼저 함
			// => 존재가 있을때는 list에 담기
			// => 존재가 없을때는 return null
			if(rs.next()) {
				do {
					// 방법 1
//					StudentDTO dto = new StudentDTO();
//					dto.setSno(rs.getInt(1));
//					dto.setName(rs.getString(2));
//					dto.setAge(rs.getInt(3));
//					dto.setJno(rs.getInt(4));
//					dto.setInfo(rs.getString(5));
//					dto.setPoint(rs.getDouble(6));
//					list.add(dto);
					
					
//					StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
					list.add(dto);
					
				} while(rs.next());
				return list;
			} else {
				return null;
			}
			
			
			
			
		} catch (Exception e){
			System.out.println("** insert Exception => " + e.toString());
			return null;
		}
		
	}
	
	
	
	
	// ** selectOne // 여기서 매개변수로 그냥 sno만 필요하긴한데 그냥 dto 를 쓰는경우가 많음 (나중에 확장되거나 하면 새로 만들어야 하니까)
	public StudentDTO selectOne(int sno) {
		sql = "select * from student where sno = ?";
		
		try {
		pst=cn.prepareStatement(sql);
		pst.setInt(1, sno);
		rs=pst.executeQuery();
		
		if (rs.next()) {
			StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
			return dto;
		}
			
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
		return null;
	}
	
	
	// => 참조 자료형 매개변수를 test 하기 위한 selectOne2 (Call By Reference)
	// => 						( 비교: 기본자료형 매개변수 (Call By Value)
	public void selectOne2(StudentDTO dto) {
		sql = "select * from student where sno = ?";
		
		try {
		pst=cn.prepareStatement(sql);
		pst.setInt(1, dto.getSno());
		rs=pst.executeQuery();
		
		if (rs.next()) {
			dto.setName(rs.getString(2));
			dto.setAge(rs.getInt(3));
			dto.setJno(rs.getInt(4));
			dto.setInfo(rs.getString(5));
			dto.setPoint(rs.getDouble(6));
		}
			
		} catch (Exception e) {
			System.out.println("** selectOne2 Exception => " + e.toString());
		}
	}
	
	
	
	// ** insert
	public int insert(StudentDTO dto) {
		sql = "insert into Student(name, age, jno, info) Value(?, ?, ?, ?)";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			
			if(pst.executeUpdate() > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	}
	





	// ** update
	public int update(StudentDTO dto) {
		sql = "update Student set name = ?, age = ?, jno = ?, info = ? where sno = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			pst.setInt(5, dto.getSno());
			
			if(pst.executeUpdate() > 0) {
				return 1;
			} else {
				return 0;
			}
			
		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
		
	}
	
	// ** delete // 삭제같은경우 확장을 해도 결국 지우는거니까 sno만 해도 됨
	public int delete(int sno) {
		sql = "delete from Student where sno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, sno);
			
			if(pst.executeUpdate() > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	}
	
	
	//Join List
	public List<StudentDTO> joinlist(){
		sql="select s1.sno, s1.name, s1.age, s1.jno, jname, project, captain," 
				+"(select name from student where sno=captain) cname"
				+" from student s1 Left Outer Join  jo j ON s1.jno=j.jno";
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()) {
				do {
					StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
//					dto.setSno(rs.getInt(1));
//					dto.setName(rs.getString(2));
//					dto.setAge(rs.getInt(3));
//					dto.setJno(rs.getInt(4));
//					dto.setJname(rs.getString(5));
//					dto.setProject(rs.getString(6));
//					dto.setCaptain(rs.getInt(7));
//					dto.setCname(rs.getString(8));
					
					
					
					list.add(dto);
				} while(rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e){
			System.out.println("** joinList Exception => " + e.toString());
			return null;
		}
		
	} // joinList
	
	// ** Transaction Test
	   // => Connection 객체가 관리
	   // => 기본값은 AutoCommit  true 임.
	   // => setAutoCommit(false) -> commit 또는 rollback 
	   // => Test 사항
	   //   - 동일자료를 2번 입력 -> 2번째 입력에서 p.key 중복 오류발생 

	   // 1) Transaction 적용전
	   // => 동일자료를 2번 입력
	   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생 
	   //   - Rollback 불가능
	   //   - MySql Command 로 1번째 입력 확인 가능 
	      
	   // 2) Transaction 적용후 
	   // => 동일자료를 2번 입력 
	   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
	   //   - Rollback 가능 -> 둘다 취소됨
	
	
	public void transactionTest() {
		sql = "insert into student values(31, '갓승삼', 24, 9, 'Transaction 적용전', 123.45)";
		
		// 1) Transaction 적용 전
//		try {
//			pst=cn.prepareStatement(sql);
//			pst.executeUpdate();
//			pst.executeUpdate();	// 일부러 실행을 두번 시킴
//			/*
//			 * 위와 같은 경우 1 번째는 Table은 입력완료되고
//			 * 			   2 번째는 p.key 중복 오류 방생으로 catch블록으로 넘어감 Exception 발생
//			 * */
//			
//		} catch (Exception e) {
//			System.out.println("** Transaction 적용전 => "+e.toString());
//		}
		
		// 2) Transaction 적용 후
		try {
			cn.setAutoCommit(false);		// mysql에서 start transaction 명령어랑 같음 
											// 이걸 실행하면 이 다음부터는 데이터를 바로 커밋하지 않고 버퍼에 저장 함
			pst=cn.prepareStatement(sql);
			pst.executeUpdate();			// 1번째는 입력이 완료되었지만 buffer에 데이터가 보관 됨
			pst.executeUpdate();			// 2번째는 p.key 중복오류 발생 -> catch 블럭으로 넘어감 -> 롤백을 해줘야 함
			
			cn.commit();					// 커밋!
			// 커밋이나 롤백을 만나면 setAutoCommit 은 끝남
			
		} catch (Exception e) {
			System.out.println("** Transaction 적용후 => "+e.toString());
			
			try {
				cn.rollback();
				// cn이 그 자체만으로 체크드라서 rollback을 사용하려면 try~catch가 필요함
				// 그래서 rollback을 위한 try~catch문을 새로 써줘야 함
				// 커밋이나 롤백을 만나면 setAutoCommit 은 끝남
				System.out.println("** Rollback 성공 **");
			} catch (Exception e2) {
				System.out.println("** Rollback Exception => "+e.toString());
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	} // transactionTest
	
} // class
