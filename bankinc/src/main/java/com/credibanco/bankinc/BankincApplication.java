package com.credibanco.bankinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class BankincApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankincApplication.class, args);
	}

}
