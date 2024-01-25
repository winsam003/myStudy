package iocDI01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser02 {

	public static void main(String[] args) {
		// 1. BeanFactory (스프리에서는 컨테이너 라고 함) 생성
		// 스프링에서는 컨테이너가 이미 많이 만들어져있고 그걸 활용해볼 것임
		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI01_xml/app02.xml");
	      // => Spring 컨테이너는 getBean 메서드를 실행해서 해당객체를 제공
	      // => 실시간으로 소스코드 수정없이 전달받음 

		//** 스프링이 제공하는 BeanFactory
		//=> 스프링 컨테이너
		//=> AbstractApplicationContext 와 GenericXmlApplicationContext 계층도 
		//=> Object -> DefaultResourceLoader (C) -> AbstractApplicationContext (A) 
//		         -> GenericApplicationContext (C) -> GenericXmlApplicationContext (C) 

		//public abstract class AbstractApplicationContext extends DefaultResourceLoader
//		      implements ConfigurableApplicationContext, DisposableBean {....

		//public class GenericXmlApplicationContext extends GenericApplicationContext {...

		//=> 컨테이너는 xml (설정화일), @, JavaCode (JavaConfig) 등의 
		// 전달사항을 통해 요구하는 클래스를 생성, 보관, 제공

		//** xml (설정화일)
		
		
		// 2. 필요한 객체를 전달받고 서비스 실행
		TV tv = (TV)sc.getBean("tv");
		if(tv!=null) {
			tv.powerOn();
			tv.powerOn();
			tv.volumeUp();
			tv.volumeDown();
			tv.powerOff();

		}else {
			System.out.println("** TV를 선택하지 않았습니다. **");
		}
		
	      // 3. singleton(싱글톤) Test
	      // => 스프링 프레임웤의 모든 작업은 싱글톤을 기본으로함.
	      // => 싱글톤 (한개의 인스턴스만 허용 하는것) 적용 Test
	      // => 설정화일의 scope 속성 에 "prototype"_ss / "singleton"_lg (default 는 싱글톤)
	      // => 생성자 실행횟수와 아래의 주소값 확인해보기
	      //    SsTVi 2개 (singleton), LgTVi 2개 (prototype) 씩 인스턴스 작성 후 확인    

		
		System.out.println("** singleton(싱글톤) Test **");
		TV tv1 = (TV)sc.getBean("tv");
		TV tv2 = (TV)sc.getBean("tv");
		System.out.println("** tv =>  "+tv);
		System.out.println("** tv1 => "+tv1);
		System.out.println("** tv2 => "+tv2);
		
		TV tvs1 =(TV)sc.getBean("tvs");
		TV tvs2 =(TV)sc.getBean("tvs");
		System.out.println("** tvs1 => "+tvs1);
		System.out.println("** tvs2 => "+tvs2);
		
		
		
		sc.close();
		
	} // main

} // class
