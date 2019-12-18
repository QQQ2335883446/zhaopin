package com.jiangyi.zhaopin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiangyi.zhaopin.dao")
public class ZhaopinApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ZhaopinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf("项目启动");
	}
}
