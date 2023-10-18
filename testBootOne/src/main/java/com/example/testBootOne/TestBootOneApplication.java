package com.example.testBootOne;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.testBootOne.mapper")
public class TestBootOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBootOneApplication.class, args);
	}

}
