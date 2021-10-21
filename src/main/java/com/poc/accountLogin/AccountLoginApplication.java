package com.poc.accountLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccountLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountLoginApplication.class, args);
	}

}
