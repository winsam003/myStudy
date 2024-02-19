package springMybatis;


import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex01_SqlSession {
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	
	public void sqlSessionFactoryTest() {
		System.out.println("** sqlSessionFactory => "+sqlSessionFactory);
		assertNotNull(sqlSessionFactory);
	}
	
	   //** SqlSession
	   // => 실제 DB연결, Mapper의 Sql 구문을 이용해 Service의 요청을 처리.
	   // => 계층도 : SqlSession (interface) -> SqlSessionTemplate
	   
	   // test1) 정상 의 경우 sqlSessionTest() 만 Test
	   // test2) SqlSessionFactory 생성 안된 경우 Test
	   // test3) @Before 적용  sqlSessionFactoryTest() , sqlSessionTest() 모두 Test
	
	@Test
	public void sqlSessionTest() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// =>sqlSessionFactory가 notNull 이어야 실행가능 함
		System.out.println("** sqlSession => "+sqlSession);
		assertNotNull(sqlSession);
	}
	
	
	
}
