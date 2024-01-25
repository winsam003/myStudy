package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java Bean Configuration class를 이용한 DI
//=> Test02 : 스피커 1개 사용 TV 
// -> 생성자를 이용한 주입,
// -> JC에서 xml 병행사용

//** JC 에서 xml 병행 사용하기 
//=> @ImportResource("iocDI03_jc/app09.xml")
//=> AiTVs 생성은 xml 로 

@ImportResource("iocDI03_jc/app09.xml")
@Configuration
public class JavaConfig02 {

	// 1) 고전적 방법 (직접 new : 소스 재컴파일)
	@Bean
	// => Bean을 붙여야 컨테이너에 의해 해당되는 메서드가 실행되어 생성된 인스턴스가 컨테이너에 return
	public TV sstv() {return new SsTVs();}
	
	// 2) IOC/DI: 생성자 주입
	// 방법1
//	public TV lgtv() {
//		return new LgTVs(new Speaker(), "blue", 20000);
//	}
	// 방법2
	@Bean
	public TV lgtv() {
		return new LgTVs(sp(), "blue", 20000);
	}

//	@Bean
	public Speaker sp() {return new Speaker();}
	
	// 3) IOC/DI: JC 에서 xml 병행 사용
	// => 3-1)jc 에서 생성 후 Speaker는 @Autowired 해보기 => error~
	// => 3-2)JavaConfig02.java에 @Bean 붙여서 다시해보기 => 됨
	//		-> Speaker 를 jc 로 생성 후 @Bean 으로 담아 두면 컨테이너에 담게 됨 -> TVUser09에서 @AutoWired를 해주면 자동으로 컨테이너에서 같은 이름의 클래스를 찾게되는데 Bean으로 담아놓으면 찾을 수 있음
	@Bean
	public TV aitv() {return new AiTVs();}

} // class
