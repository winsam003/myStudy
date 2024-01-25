package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	// ** DB 연결
	//=> Connection 객체가 DB 연결및 연결정보를 관리함
	//   즉, Connection 객체를 생성해야함

	//** Connection 객체 생성
	// => 일반적인 생성문 
//	    Connection cn = new Connection_구현클래스() -> XXX
	// => DB 연결정보를이용해서 생성후 그 생성값을 전달받음   

	//** Connection 생성과정
	// => Class.forName : JDBC 드라이버 로딩
	// => DriverManager
	//	  getConnection() 메서드로 해당 JDBC 드라이버를 찾아 필요한 기본값으로 컨넥션을 생성해서 제공

	// ** JDBC 드라이버
	// => DBMS와 통신을 담당하는 자바 클래스
	//	  DBMS 별로 알맞은 JDBC 드라이버 필요
	//	  보통 jar 파일로 제공
	//    db를 새로 만들면 그 새로운 db로 자바코딩하려면 당연히 JDBC드라이버와 connector를 만들어서 연동이 가능하게 만들어야 함
	// 	  보통 드라이버, 커넥터는 당연히 각 db사에서 만듬 (자바랑 연동시켜야하 걔네 db를 팔테니까)

	// ** DriverManager
	// => JDK(Java SE Development Kit)의 정적 클래스이며,
	//	사용할 애플리케이션에 대해 사용가능한 JDBC(Java Database Connectivity) 드라이버 세트를 관리함.
	
	public static Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
//			Connection cn = new Connection(); // 보통 이렇게하지만 Connection은 이렇게 생성하면 안됨
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";	// 찾아가는 그 주소의 자원을 보통 url이라고 함
						//mysql경로://localhost(IP address -> 내컴퓨터주소임):3306(포트번호) (만약 웹(구글, 아마존 등..)에서 서버를 호스트해놨다면 거기서 준 아이피를 여기에 적으면 됨):3306(포트번호 설치할때 포트번호 보관해놨음 (나는 3306))/mydb(지금 데이터베이스 이름이 mydb라서 이렇게함)?설정값임 (파라메터라고 함)-> UTF-8이라던가.. &으로 구분함 잘보면 파라메터를 4개 전달했음 그리고 마지막에 allowPublicKey.. 이걸 꼭 해줘야함 이걸 연결하면 mydb 창을 굳이 열어놓고있지않아도 java로 sql의 mydb가 켜져있지 않아도 킬 수 있게 true 해줌
		     // => allowPublicKeyRetrieval=true : local DB open 하지 않아도 connection 허용
	         // => localhost -> 동일값(ip주소) @127.0.0.1	// 내 피씨 안에서 찾을적에는 127.0.0.1 임 (웹에서 상식임!!!!)
			Connection cn = DriverManager.getConnection(url, "root", "mysql");
			
			System.out.println("** DB Connection 성공 **");
			return cn;
		} catch (Exception e) {
			System.out.println("** DB Connection Exception => "+e.toString());
			return null;
		} // try
		
	} // getConnection
	
} // class
