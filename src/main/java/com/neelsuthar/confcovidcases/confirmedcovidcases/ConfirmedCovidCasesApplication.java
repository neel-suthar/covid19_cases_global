package com.neelsuthar.confcovidcases.confirmedcovidcases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConfirmedCovidCasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmedCovidCasesApplication.class, args);
	}
}
