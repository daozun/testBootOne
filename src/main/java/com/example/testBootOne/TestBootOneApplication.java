package com.example.testBootOne;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@MapperScan("com.example.testBootOne.mapper")
public class TestBootOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBootOneApplication.class, args);
	}

}
