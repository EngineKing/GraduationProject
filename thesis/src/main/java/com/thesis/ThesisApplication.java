package com.thesis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.thesis.dao")
public class ThesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThesisApplication.class, args);
	}

}
