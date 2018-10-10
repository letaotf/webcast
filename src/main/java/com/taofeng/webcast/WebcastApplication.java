package com.taofeng.webcast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.taofeng.webcast.dao.mapper")//加载mapper类的路径
public class WebcastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcastApplication.class, args);
	}
}
