package springTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;

//*** DataSourceTest
//=> pom.xml 에 <dependency> spring-jdbc 추가
//=> 인터페이스 DataSource 구현객체 DriverManagerDataSource 를 bean 등록하고 (servlet~.xml 또는 root~.xml 에)
//=> DB Connection 생성 확인

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_DataSourceTest {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	MemberDTO dto;
	
	// 1) DBConnection 확인
	
	public void connectionTest() {
		
		try {
			assertNotNull(dataSource.getConnection());
			System.out.println("** DB Connection 성공 => "+dataSource.getConnection());
		} catch (Exception e) {
			System.out.println("** DB Connection 실패 => "+e.toString());
			// TODO: handle exception
		}
	}
	
	// 2) SQL 구문 실행 Test
	
	public int delete(String id) {
		String sql = "delete from member where id = ?";
		try {
			Connection cn = dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => "+e);
			return 0;
		} // try
		
	} // delete
	
	@Before
	public void deleteTest() {
//		String id = "bamboo7";			// 존재하는 아이디
		String id = "junit";			// 존재하지 않는 아이디
		assertEquals(delete(id), 1);	// id가 존재하면 1 => true
	}
	
	// 2) SQL 구문 실행 Test
	public int insert(MemberDTO dto) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection cn = dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());
			pst.setString(10, dto.getUploadfile());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** insert Exception => "+e.toString());
			return 0;
		} // try
		
	} // insert
	
	@Test
	public void insertTest() {
		dto.setId("junit");
		dto.setPassword("12345!");
		dto.setName("유니트");
		dto.setAge(20);
		dto.setJno(7);
		dto.setInfo("junit test");
		dto.setPoint(20.123);
		dto.setBirthday("2022-03-12");
		dto.setRid("bamboo7");
		dto.setUploadfile("aaa.gif");
		assertEquals(insert(dto), 1);	// id가 존재하면 1 => true
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
} // class
