package JDBC01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * ** 순서로 실습을 해볼 것임
 * 1) JDBC API 에 정의된 필요한 객체들을 전역변수 정의
 * 2) CRUD 기능 처리 메서드 생성
 * 3) main에서 CRUD 기능을 사용
 * 
 * 
 * */ 


public class DBStart {

	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;		// sql 구문을 여기에 넣음 CRUD
	
	/*
	 * JDBC 는 다 Connection 클래스를 통해 객체를 생성 함
	 * st = cn.createStatment(); 이런 식임 (아래 코드에서 좀 더 볼 것)
	 * 
	 * */
	
	// ** StudentList를 콘솔로 찍어보기
	/*
	 * 해야하는거 순서대로 쓰면
	 * mysql에서는
	 * 1. login (mysql -u root -p)
	 * 2. DB선택 (use mydb)
	 * 3. sql 구문 실행 (select * from student);
	 * 
	 * 이걸 JDBC에서는
	 * 1. Connection 객체 생성
	 * 2. sql 구문 실행 -> statement나 PreparedStatement 둘 중 하나를 선택해서 실행 함
	 * 3. 결과를 ResultSet에 전달 함
	 * 
	 * */
	public static void selectList() {
		
		sql = "select * from student";
		
		try {
			st=cn.createStatement();	// st에 select문 실행준비
			rs =st.executeQuery(sql);	// st에 excuteQuery로 sql 문 실행
			
		     // ** 결과출력
	         // => 결과 존재 확인
	         // => ResultSet 객체는 이를 위한 메서드 제공 
	         // => next() : 다음에 Data가 존재하면 true, 현재Data를 제공
			// next()는 ResultSet
			
			
			System.out.println("                             ** Student List **");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("sno | name     | age  | jno  | info                                            | point");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			
			if(rs.next()) {
				// selectList 결과가 존재 함
				// for는 증감이있고 카운트가 정확히 있을때라서 for보단 while이나 do~while을 쓰는게 나을 것임
				// do~while이 나을 거 같음 이미 조건에 만족한 애가 들어온거니까 ㅇㅇ
				
				
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
					// rs.getInt(숫자) -> 컬럼의 순서대로의 숫자 // index는 보통 0부터 시작하는데 얘는 1부터 시작함 // 오라클에서 rownum이 항상 0번이기때문에 1번으로 시작하는게 아닐까 하는 샘의 추측
					// re.getInt(이름) -> 컬럼의 이름 
				} while(rs.next());
				
				
				
			} else {
				// 여기에 왔다는건 selectList 결과가 1건도 없음을 의미함
				System.out.println("** selectList 결과가 1건도 없음 **");
			} // if_else
			
					
		} catch(Exception e) {
			System.out.println("** selectList Excemtion => "+ e.toString());
		} // try
		
	} // selectList
	
	
	
	
	// * insert (문제점)
	// 필요한 컬럼은 6개임 sno 는 incement, point는 100디폴트 라고 쳐도 4개는 입력해야함
	// => 입력에 필요한 컬럼을 모두 매개변수로 전달 받아야 함
	// => 그리고 입력에 필요한 컬럼의 매개변수 또한 많으면 처리가 불편함.. 100개면 100개를 다 적어야함 그러니 그 자체를 객체화 하면 편함
	//		-> 엔티티(Table) (학원을 예를들면, 학생, 강사, 학원직원 테이블이 필요함 -> 3개의 엔티티가 필요함)
	//			-> 이러한 엔티티(Table)을 Java Class 로 객체화 함
	//			-> 이러한 것을 DTO(Data Transfer Object), VO(Value Object), entity(JPA에서 엔티티라고 부름) 라고 부름
	//			-> 지금 하는 것에서 예를들면 Student.DTO, Student.VO ... 이렇게 쓸 수 있음
	//			-> 그리고 이거는 예습사항이긴한데 아래 try~catch문은 실질적으로 어플리케이션을 실행하는문 -> DAO라고 함 (Data Access Object)
	//			-> 여기 쓰고있는 것들은 예습사항임 알고만 있어
	//			-> 보통 MVC (Model(DB를 의미함), View, Controller) 패턴으로 작업 함
	//		-> 강사를 관리하기위해서는, 학생을 관리하기위해서는, 직원을 관리하기위해서는 어떤항목이 필요하다 -> 관리하기위해 어떤 어트리뷰트(컬럼)들이 필요하다 라고 할 수 있음
	// => sql 구문을 만들기위해 문자열 더하기 연산을 처리해야 함 => 너무 불편함...
	// ex) insert into student(name, age, jno, info) values('홍길동', 10, 9, '동에서 번쩍 서에서 번쩍') 이런 구문이 필요한데 매개변수를 values 안에서 + 연산을 해야함 -> 개불편
	// ex) "insert into student(name, age, jno, info) values('" + name + "'," + age + "'," + jno + "'," + info + ")"; -> 개불편해..
	
	// 이런 점을 보완하기 위해서 제공된 객체가 PreparedStatement 임
	// 변수의 위치에 ?(바인딩변수) 를 사용할 수 있음
	// ex) insert into student(name, age, jno, info) values(?, ?, ?, ?); // 편안
	// ? 에 대응값은 JavaCode 로 처리함 (PreparedStatement 제공 메서드)
	
	/*=======================================================================*/
	// 조별 List 1
	// Statement 활용 : 매개변수를 활용한 조건문 추가
	public static void joList(int jno) {
		sql = "select * from student where jno = " + jno;
		
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			
			System.out.println("** jo List => "+jno);
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
					// rs.getInt(숫자) -> 컬럼의 순서대로의 숫자 // index는 보통 0부터 시작하는데 얘는 1부터 시작함 // 오라클에서 rownum이 항상 0번이기때문에 1번으로 시작하는게 아닐까 하는 샘의 추측
					// re.getInt(이름) -> 컬럼의 이름 
				} while(rs.next());
			} else {
				System.out.println("** selectList 결과가 1건도 없음 **");				
			}
		} catch(Exception e) {
			
		}
		
		
	}
	
	
	// 조별 List 2
	// PreparedStatement 활용 : 매개변수를 활용한 조건문 추가
	public static void joListPS(int jno) {
		sql = "select * from student where jno = ?";
		
		try {
//			st = cn.createStatement();		// 비교용 (지워도 됨)
//			rs = st.executeQuery(sql);		// 비교용 (지워도 됨)
			
			
			pst=cn.prepareStatement(sql);
									// () 에 sql 문을 미리 줘야함 -> 그래서 ? 를 처리 할 수 있음
			pst.setInt(1, jno);
			
			rs=pst.executeQuery();
								// sql 을 위에서 이미 줬기때문에 여기서는 sql을 또 주지 않음
			
			
			
			
			
			System.out.println("** jo ListPS => "+jno);
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
					// rs.getInt(숫자) -> 컬럼의 순서대로의 숫자 // index는 보통 0부터 시작하는데 얘는 1부터 시작함 // 오라클에서 rownum이 항상 0번이기때문에 1번으로 시작하는게 아닐까 하는 샘의 추측
					// re.getInt(이름) -> 컬럼의 이름 
				} while(rs.next());
			} else {
				System.out.println("** selectList 결과가 1건도 없음 **");				
			}
		} catch(Exception e) {
			
		}
		
		
	}
	
	
	// * insert 해보기
	
	public static void insert(String name, int age, int jno, String info) {
		sql = "insert into student(name, age, jno, info) values(?, ?, ?, ?)";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setInt(3, jno);
			pst.setString(4, info);
			
			// insert, update, delete
			int cnt = pst.executeUpdate();
			// executeUpdate() 는 업데이트 한 행의 숫자를 리턴함
			// 정상적으로 안되는 경우 catch (Exception e) 로 넘어가는게 아니라 걍 안해버림
			
			
//			if(cnt == 1) System.out.println("insert 성공 => "+cnt);
//			else System.out.println("insert 실패 => "+cnt);
			
			
			// 위의 문장을 아래처럼 간단하게 할 수 있음
			if(pst.executeUpdate() > 0) System.out.println("insert 성공 => ");
			else System.out.println("insert 실패 => ");
			
			
			
			System.out.println("** insert 성공 => " + cnt);
			
		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());	
		}
	}
	
	
	public static void main(String[] args) {
		//1) Connection 확인
		// => cn은 Object 클래스로부터 toString을 쓴 것 임 즉 cn.toString(); 을 한 것과 같음
		// 근데 toString() 은 생략가능하기때문에 cn만 써도 cn의 값이 문자열 값으로 나오는 것임
		// 즉, 출력문에서 인스턴스명을 사용하면 toString() 를 호출하는 것임
		System.out.println("** Connection 확인 => "+cn);
		
		//2) Student List
//		selectList();
		
		//3) 조별 List 출력해보기
//		joList(3);
		//3-1) PreparedStatement 를 활용해서 List 출력해보기
//		joListPS(3);
		
		//4) 새로운 조원 입력해보기
//		insert("차승삼", 21, 9, "최승삼의 클론");
		
		
	} // main

} // class
