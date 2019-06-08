package com.lxy.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.lxy.mp.mapper")
public class MybatisPlusDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusDemoApplication.class, args);
	}

}
