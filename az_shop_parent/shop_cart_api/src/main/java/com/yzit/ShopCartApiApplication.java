package com.yzit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopCartApiApplication {
	public static void main(String[] args) {
	    // 禁用devtools热部署
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ShopCartApiApplication.class, args);
	}
}
