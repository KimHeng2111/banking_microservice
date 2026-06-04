package com.kimheng.account;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableFeignClients
public class AccountApplication {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Phnom_Penh"));
	}
	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
