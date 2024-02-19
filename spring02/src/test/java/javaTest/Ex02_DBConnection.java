package javaTest;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class Ex02_DBConnection {
	
	// 1) static, return 값이 있는 경우
	//    => Test 메서드를 작성해서 Test
	
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 해당하는 클래스를 찾아서. 
			
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			// => allowPublicKeyRetrieval=true : local DB open 하지 않아도 connection 허용
			// => localhost -> 동일값(ip주소) @127.0.0.1
			// ( localhost : IP address / db주소를 찾는 주소 )
			// 파라미터와 파라미터 &로 구분. 위에는 파라미터 4개 전달.
			// allowPublicKeyRetrieval 페이지가 열리지 않아도 connection 할 수 있게. 
			
			Connection cn = DriverManager.getConnection(url, "root", "mysql"); 
			// root 계정 mysql 비번.
			
			System.out.println("** DB Connection 성공 **" );
			return cn;
			
		} catch (Exception e) {
			System.out.println("** DB Connection Exception => " + e.toString());
			return null;			
		} // try
	} // getConnection
	
	@Test
	public void connectionTest() {
		System.out.println("** DB_Connection => " + getConnection());
		assertNotNull(getConnection());
	}
	
}