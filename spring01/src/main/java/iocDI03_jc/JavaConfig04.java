package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//** Java Bean Configuration class를 이용한 DI
//=> Test04 : 스피커 2개중 선택 
//=> xml 에서 JC 화일 import , @ 병행 사용
//=> JC 에서는 LgTVsi, AiTVsi, SpeakerB 생성

@Configuration
public class JavaConfig04 {
	
	@Bean
	public TV lgtv() {return new LgTVsi(new SpeakerB(), "Pink", 400000);}
	
	@Bean
	public TV aitv() {return new AiTVsi();}
	
	
} // class
