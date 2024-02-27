package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@MapperScan("mapperInterface")
public class DemoJpaApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}

}